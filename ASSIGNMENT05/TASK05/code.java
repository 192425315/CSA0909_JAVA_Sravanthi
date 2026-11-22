
import java.sql.*;

public class Main {

    static final String URL =
        "jdbc:mysql://localhost:3306/assignment5";

    static final String USER = "root";
    static final String PASSWORD = "your_password";

    // Establish database connection
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            URL, USER, PASSWORD);
    }

    // Transfer money using transaction
    static void transferMoney(Connection con,
                              int from, int to,
                              double amount) throws SQLException {

        con.setAutoCommit(false);

        try {
            String debit =
                "UPDATE wallet SET balance = balance - ? " +
                "WHERE wallet_id = ? AND balance >= ?";

            try (PreparedStatement ps =
                     con.prepareStatement(debit)) {

                ps.setDouble(1, amount);
                ps.setInt(2, from);
                ps.setDouble(3, amount);

                if (ps.executeUpdate() != 1) {
                    throw new SQLException(
                        "Insufficient balance or invalid source wallet");
                }
            }

            String credit =
                "UPDATE wallet SET balance = balance + ? " +
                "WHERE wallet_id = ?";

            try (PreparedStatement ps =
                     con.prepareStatement(credit)) {

                ps.setDouble(1, amount);
                ps.setInt(2, to);

                if (ps.executeUpdate() != 1) {
                    throw new SQLException(
                        "Destination wallet not found");
                }
            }

            con.commit();

            System.out.println(
                "Money transferred successfully!");

        } catch (SQLException e) {

            con.rollback();

            System.out.println(
                "Transaction failed. Rollback completed.");

            throw e;

        } finally {
            con.setAutoCommit(true);
        }
    }

    // Unsafe login demonstration
    static boolean unsafeLogin(Connection con,
                               String id,
                               String password)
                               throws SQLException {

        String sql =
            "SELECT * FROM users WHERE id='" +
            id + "' AND pwd='" + password + "'";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            return rs.next();
        }
    }

    // Safe login using PreparedStatement
    static boolean safeLogin(Connection con,
                             String id,
                             String password)
                             throws SQLException {

        String sql =
            "SELECT * FROM users WHERE id=? AND pwd=?";

        try (PreparedStatement ps =
                 con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Display wallet balances
    static void displayWallets(Connection con)
                               throws SQLException {

        String sql =
            "SELECT * FROM wallet ORDER BY wallet_id";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                    "Wallet ID: " + rs.getInt("wallet_id") +
                    ", Balance: Rs." + rs.getDouble("balance"));
            }
        }
    }

    public static void main(String[] args) {

        try (Connection con = getConnection()) {

            System.out.println("===== INITIAL BALANCE =====");
            displayWallets(con);

            System.out.println("\n===== MONEY TRANSFER =====");

            try {
                transferMoney(con, 1, 2, 500);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("\n===== UPDATED BALANCE =====");
            displayWallets(con);

            System.out.println("\n===== SQL INJECTION TEST =====");

            String attackId = "' OR '1'='1' -- ";
            String wrongPassword = "wrong123";

            System.out.println(
                "Unsafe Login: " +
                unsafeLogin(con, attackId, wrongPassword));

            System.out.println(
                "Safe Login: " +
                safeLogin(con, attackId, wrongPassword));

        } catch (SQLException e) {
            System.out.println(
                "Database Error: " + e.getMessage());
        }
    }
}

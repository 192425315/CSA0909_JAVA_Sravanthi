
import java.util.HashMap;
import java.util.Map;

public class Main {

    // Simulated wallet database
    static Map<Integer, Double> wallets = new HashMap<>();

    // Simulated user database
    static Map<String, String> users = new HashMap<>();

    // Initialize database
    static void initialize() {
        wallets.put(1, 5000.0);
        wallets.put(2, 3000.0);

        users.put("student1", "pass123");
        users.put("student2", "pass456");
    }

    // Display wallet balances
    static void displayWallets() {
        for (int id : wallets.keySet()) {
            System.out.println(
                "Wallet ID: " + id +
                ", Balance: Rs." + wallets.get(id)
            );
        }
    }

    // Transfer money with transaction simulation
    static void transferMoney(int from, int to,
                              double amount, boolean simulateFailure) {

        // Save original balances as transaction backup
        double oldFrom = wallets.get(from);
        double oldTo = wallets.get(to);

        System.out.println("Starting Transaction...");

        try {
            if (amount <= 0) {
                throw new Exception("Invalid transfer amount");
            }

            if (oldFrom < amount) {
                throw new Exception("Insufficient balance");
            }

            // Debit source wallet
            wallets.put(from, oldFrom - amount);

            System.out.println("Amount debited: Rs." + amount);

            // Simulate failure between updates
            if (simulateFailure) {
                throw new Exception("Simulated transfer failure");
            }

            // Credit destination wallet
            wallets.put(to, oldTo + amount);

            // Commit
            System.out.println("Transaction committed!");

        } catch (Exception e) {

            // Rollback
            wallets.put(from, oldFrom);
            wallets.put(to, oldTo);

            System.out.println("Transaction failed!");
            System.out.println("Rollback completed!");
            System.out.println("Reason: " + e.getMessage());
        }
    }

    // Unsafe login demonstration
    static boolean unsafeLogin(String id, String pwd) {

        // Simulates unsafe SQL string concatenation
        if (id.contains("' OR '1'='1'")) {
            return true;
        }

        return users.containsKey(id)
                && users.get(id).equals(pwd);
    }

    // Safe login demonstration
    static boolean safeLogin(String id, String pwd) {

        // Input is treated as literal data
        return users.containsKey(id)
                && users.get(id).equals(pwd);
    }

    public static void main(String[] args) {

        initialize();

        System.out.println("===== INITIAL WALLET BALANCE =====");
        displayWallets();

        // Successful transaction
        System.out.println("\n===== SUCCESSFUL TRANSFER =====");

        transferMoney(1, 2, 500, false);

        displayWallets();

        // Failed transaction
        System.out.println("\n===== FAILED TRANSFER TEST =====");

        transferMoney(1, 2, 1000, true);

        displayWallets();

        // SQL injection demonstration
        System.out.println("\n===== SQL INJECTION TEST =====");

        String attackId = "' OR '1'='1' -- ";
        String wrongPassword = "wrong123";

        System.out.println(
            "Unsafe Login Result: " +
            unsafeLogin(attackId, wrongPassword)
        );

        System.out.println(
            "Safe Login Result: " +
            safeLogin(attackId, wrongPassword)
        );

        // Valid login
        System.out.println("\n===== VALID LOGIN TEST =====");

        System.out.println(
            "Safe Valid Login: " +
            safeLogin("student1", "pass123")
        );

        System.out.println("\nProgram executed successfully!");
    }
}

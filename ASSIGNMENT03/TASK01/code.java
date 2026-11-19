
import java.io.*;

class Main {

    static double balance = 5000;

    static void withdraw(String account, String amountText)
            throws IOException, InsufficientFundsException {

        System.out.println("\nAccount: " + account);

        try {
            FileInputStream file =
                new FileInputStream("cardreader.txt");
            file.close();

            double amount = Double.parseDouble(amountText);

            if (amount <= 0) {
                throw new IllegalArgumentException(
                    "Amount must be positive");
            }

            if (amount > balance) {
                throw new InsufficientFundsException(
                    "Insufficient account balance");
            }

            balance -= amount;

            System.out.println("Cash withdrawn: Rs." + amount);
            System.out.println("Remaining balance: Rs." + balance);

        } finally {
            System.out.println("ATM transaction completed.");
        }
    }

    public static void main(String[] args) {

        try {
            FileWriter writer =
                new FileWriter("cardreader.txt");
            writer.write("Card reader ready");
            writer.close();

        } catch (IOException e) {
            System.out.println("File creation error.");
            return;
        }

        String[][] tests = {
            {"A101", "1000"},
            {"A102", "abc"},
            {"A103", "-500"},
            {"A104", "9000"},
            {"A105", "200"}
        };

        for (String[] test : tests) {

            try {
                withdraw(test[0], test[1]);

            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric input.");

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid amount: "
                    + e.getMessage());

            } catch (InsufficientFundsException e) {
                System.out.println("Transaction failed: "
                    + e.getMessage());

            } catch (IOException e) {
                System.out.println("Card reader file error.");

            } catch (Exception e) {
                System.out.println("Unexpected error: "
                    + e.getMessage());
            }
        }
    }
}

class InsufficientFundsException extends Exception {

    InsufficientFundsException(String message) {
        super(message);
    }
}

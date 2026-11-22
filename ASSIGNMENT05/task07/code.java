

import java.io.*;
import java.util.*;

public class Main {

    static Map<String, String> users = new HashMap<>();

    // Initialize sample users
    static void initializeUsers() {
        users.put("student1", "pass123");
        users.put("student2", "pass456");
    }

    // Simulate unsafe login
    static boolean unsafeLogin(String userId, String password) {

        if (userId.contains("' OR '1'='1'")) {
            return true;
        }

        return users.containsKey(userId)
                && users.get(userId).equals(password);
    }

    // Simulate safe login using exact matching
    static boolean safeLogin(String userId, String password) {

        return users.containsKey(userId)
                && users.get(userId).equals(password);
    }

    // Validate bus pass records
    static void validateFile(String fileName) {

        int validCount = 0;
        int damagedCount = 0;

        try (BufferedReader br =
                     new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                try {
                    if (line.trim().isEmpty()) {
                        throw new IllegalArgumentException(
                                "Empty record");
                    }

                    String[] data = line.split(",", -1);

                    if (data.length != 3) {
                        throw new IllegalArgumentException(
                                "Incorrect number of fields");
                    }

                    String passId = data[0].trim();
                    String studentName = data[1].trim();
                    int amount = Integer.parseInt(data[2].trim());

                    if (passId.isEmpty() || studentName.isEmpty()
                            || amount <= 0) {
                        throw new IllegalArgumentException(
                                "Invalid record values");
                    }

                    validCount++;

                    System.out.println("Valid record: " + line);

                } catch (IllegalArgumentException e) {
                    damagedCount++;
                    System.out.println(
                            "Damaged record skipped: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }

        System.out.println("\nFile Validation Summary");
        System.out.println("Valid records: " + validCount);
        System.out.println("Damaged records: " + damagedCount);
    }

    public static void main(String[] args) {

        initializeUsers();

        System.out.println("===== SQL INJECTION TEST =====");

        String attackUser = "' OR '1'='1'";
        String attackPassword = "anything";

        System.out.println("Unsafe Login Result: "
                + unsafeLogin(attackUser, attackPassword));

        System.out.println("Safe Login Result: "
                + safeLogin(attackUser, attackPassword));

        System.out.println("\n===== NORMAL LOGIN TEST =====");

        System.out.println("Unsafe Login Result: "
                + unsafeLogin("student1", "pass123"));

        System.out.println("Safe Login Result: "
                + safeLogin("student1", "pass123"));

        System.out.println("\n===== DAMAGED FILE TEST =====");

        try {
            File file = File.createTempFile(
                    "buspasses", ".txt");

            try (PrintWriter writer =
                         new PrintWriter(file)) {

                writer.println("101,Sravanthi,500");
                writer.println("102,Ravi");
                writer.println("103,Anu,abc");
                writer.println("");
                writer.println("104,Kiran,600");
            }

            validateFile(file.getAbsolutePath());

            file.deleteOnExit();

        } catch (IOException e) {
            System.out.println("Unable to create file: "
                    + e.getMessage());
        }
    }
}

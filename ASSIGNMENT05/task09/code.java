

import java.io.*;
import java.util.*;

public class Main {

    static class Pass {
        String passId;
        String regNo;
        String name;
        int routeNo;
        double amount;

        Pass(String id, String reg, String name,
             int route, double amount) {
            this.passId = id;
            this.regNo = reg;
            this.name = name;
            this.routeNo = route;
            this.amount = amount;
        }

        public String toString() {
            return passId + " | " + regNo + " | "
                    + name + " | " + routeNo
                    + " | " + amount;
        }
    }

    // Simulated database table
    static List<Pass> database = new ArrayList<>();

    // Read and validate records from file
    static List<Pass> readValidRecords(
            String fileName, List<String> failedRecords) {

        List<Pass> validRecords = new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                try {
                    String[] data = line.split(",", -1);

                    if (data.length != 5) {
                        throw new IllegalArgumentException(
                                "Incorrect number of fields");
                    }

                    String id = data[0].trim();
                    String reg = data[1].trim();
                    String name = data[2].trim();
                    int route = Integer.parseInt(data[3].trim());
                    double amount =
                            Double.parseDouble(data[4].trim());

                    if (id.isEmpty() || reg.isEmpty()
                            || name.isEmpty()
                            || route <= 0 || amount <= 0) {
                        throw new IllegalArgumentException(
                                "Invalid field values");
                    }

                    validRecords.add(
                            new Pass(id, reg, name, route, amount));

                } catch (IllegalArgumentException e) {
                    failedRecords.add(line);
                }
            }

        } catch (IOException e) {
            System.out.println("File reading error: "
                    + e.getMessage());
        }

        return validRecords;
    }

    // Insert records in batches using a simulated transaction
    static int migrateRecords(List<Pass> records) {

        List<Pass> transactionData = new ArrayList<>();

        int batchSize = 2;

        try {
            for (int i = 0; i < records.size(); i++) {

                transactionData.add(records.get(i));

                if (transactionData.size() == batchSize) {
                    System.out.println(
                            "Batch prepared: "
                            + transactionData.size()
                            + " records");

                    database.addAll(transactionData);
                    transactionData.clear();
                }
            }

            // Insert remaining records
            if (!transactionData.isEmpty()) {
                database.addAll(transactionData);
            }

            System.out.println(
                    "Transaction committed successfully.");

            return database.size();

        } catch (RuntimeException e) {

            database.clear();

            System.out.println(
                    "Transaction failed. Rollback completed.");

            return 0;
        }
    }

    // Display simulated database metadata
    static void showMetadata() {

        System.out.println("\n===== DATABASE METADATA =====");

        System.out.println("Database Product: MySQL (planned)");
        System.out.println("JDBC Driver: MySQL Connector/J (planned)");
        System.out.println("Table Name: bus_pass");

        System.out.println("Columns:");
        System.out.println("pass_id  - VARCHAR");
        System.out.println("reg_no   - VARCHAR");
        System.out.println("name     - VARCHAR");
        System.out.println("route_no - INT");
        System.out.println("amount   - DOUBLE");
    }

    static void displayDatabase() {

        System.out.println("\n===== MIGRATED RECORDS =====");

        if (database.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Pass p : database) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {

        List<String> failedRecords = new ArrayList<>();

        try {
            File file = File.createTempFile(
                    "buspasses", ".csv");

            try (PrintWriter writer =
                         new PrintWriter(file)) {

                writer.println("P101,192425315,Sravanthi,5,4500");
                writer.println("P102,192425316,Anu,7,4000");
                writer.println("P103,192425317,Ravi,3,3500");
                writer.println("P104,192425318,Meena,abc,4200");
                writer.println("P105,192425319,Kiran,8,5000");
                writer.println("P106,192425320,Divya,6,-100");
                writer.println("P107,192425321,Arun,4,3800");
                writer.println("P108,192425322,John,2,4100");
                writer.println("DAMAGED RECORD");
            }

            System.out.println("===== FILE MIGRATION =====");

            List<Pass> validRecords =
                    readValidRecords(
                            file.getAbsolutePath(),
                            failedRecords);

            System.out.println(
                    "Valid records: " + validRecords.size());

            System.out.println(
                    "Invalid records: " + failedRecords.size());

            int migrated = migrateRecords(validRecords);

            System.out.println(
                    "\nTotal migrated records: " + migrated);

            displayDatabase();

            System.out.println("\n===== FAILED RECORD REPORT =====");

            if (failedRecords.isEmpty()) {
                System.out.println("No failed records.");
            } else {
                for (String record : failedRecords) {
                    System.out.println(record);
                }
            }

            showMetadata();

            file.deleteOnExit();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

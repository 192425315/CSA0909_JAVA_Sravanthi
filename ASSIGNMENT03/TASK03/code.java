
import java.io.*;

class Main {

    // Create sample store files
    static void createFiles() throws IOException {

        try (PrintWriter p1 =
                 new PrintWriter("store1.txt");
             PrintWriter p2 =
                 new PrintWriter("store2.txt")) {

            p1.println("Store 1 Sales: 1200");
            p2.println("Store 2 Sales: 1800");
        }
    }

    // Manual resource closing
    static void manualReport() throws IOException {

        BufferedReader reader1 = null;
        BufferedReader reader2 = null;
        PrintWriter writer = null;

        try {
            reader1 = new BufferedReader(
                new FileReader("store1.txt"));

            reader2 = new BufferedReader(
                new FileReader("store2.txt"));

            writer = new PrintWriter(
                new FileWriter("manual-report.txt"));

            String line;

            while ((line = reader1.readLine()) != null) {
                writer.println(line);
            }

            while ((line = reader2.readLine()) != null) {
                writer.println(line);
            }

        } finally {

            if (writer != null) {
                writer.close();
            }

            if (reader2 != null) {
                reader2.close();
            }

            if (reader1 != null) {
                reader1.close();
            }
        }
    }

    // Try-with-resources
    static void automaticReport() throws IOException {

        try (
            BufferedReader reader1 =
                new BufferedReader(
                    new FileReader("store1.txt"));

            BufferedReader reader2 =
                new BufferedReader(
                    new FileReader("store2.txt"));

            PrintWriter writer =
                new PrintWriter(
                    new FileWriter("automatic-report.txt"))
        ) {

            String line;

            while ((line = reader1.readLine()) != null) {
                writer.println(line);
            }

            while ((line = reader2.readLine()) != null) {
                writer.println(line);
            }
        }
    }

    public static void main(String[] args) {

        try {
            createFiles();

            manualReport();
            automaticReport();

            System.out.println(
                "Manual report generated successfully.");

            System.out.println(
                "Automatic report generated successfully.");

            System.out.println(
                "Both reports generated successfully.");

        } catch (IOException e) {

            System.out.println(
                "File error: " + e.getMessage());
        }
    }
}

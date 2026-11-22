
import java.io.*;

public class BusPass {

    // Create a temporary writable file
    static final String FILE;

    static {
        try {
            FILE = File.createTempFile("buspasses", ".txt").getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Add a new bus pass
    static void addPass(String id, String regNo, String name,
                        int route, String stop, String date,
                        double amount) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE, true))) {

            bw.write(id + "," + regNo + "," + name + "," +
                    route + "," + stop + "," + date + "," + amount);

            bw.newLine();

            System.out.println("Bus pass added successfully!");
        }
    }

    // Find a pass using Pass ID
    static void findPass(String id) throws IOException {

        try (BufferedReader br = new BufferedReader(
                new FileReader(FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",", -1);

                // Skip damaged records
                if (data.length != 7) {
                    continue;
                }

                try {
                    Integer.parseInt(data[3]);
                    Double.parseDouble(data[6]);
                } catch (NumberFormatException e) {
                    continue;
                }

                if (data[0].equals(id)) {
                    System.out.println("Pass Found:");
                    System.out.println(line);
                    return;
                }
            }
        }

        System.out.println("Pass Not Found!");
    }

    // Display passes by route number
    static void listByRoute(int route) throws IOException {

        try (BufferedReader br = new BufferedReader(
                new FileReader(FILE))) {

            String line;
            boolean found = false;

            System.out.println("Bus Passes for Route " + route + ":");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length != 7) {
                    continue;
                }

                try {
                    int routeNo = Integer.parseInt(data[3]);
                    Double.parseDouble(data[6]);

                    if (routeNo == route) {
                        System.out.println(line);
                        found = true;
                    }

                } catch (NumberFormatException e) {
                    continue;
                }
            }

            if (!found) {
                System.out.println("No passes found for this route.");
            }
        }
    }

    // Main method
    public static void main(String[] args) throws IOException {

        System.out.println("===== BUS PASS MANAGEMENT SYSTEM =====");

        // Add records
        addPass("P1001", "192425315", "Sravanthi",
                12, "Avadi", "2027-03-31", 4500);

        addPass("P1002", "192425316", "Arun",
                7, "Chennai", "2027-04-30", 3800);

        addPass("P1003", "192425317", "Divya",
                12, "Ambattur", "2027-05-31", 4200);

        // Search record
        System.out.println("\n===== SEARCH BUS PASS =====");
        findPass("P1001");

        // Search non-existing record
        System.out.println("\n===== SEARCH INVALID PASS =====");
        findPass("P9999");

        // List by route
        System.out.println("\n===== ROUTE-WISE BUS PASSES =====");
        listByRoute(12);

        System.out.println("\nProgram executed successfully!");
    }
}

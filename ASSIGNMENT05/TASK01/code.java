
import java.io.*;
import java.util.*;

public class BusPass {

    static final String FILE = "buspasses.txt";

    // Add a new bus pass
    static void addPass(String id, String regNo, String name,
                        int route, String stop, String date,
                        double amount) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE, true))) {

            bw.write(String.join(",",
                    id, regNo, name, String.valueOf(route),
                    stop, date, String.valueOf(amount)));

            bw.newLine();
            System.out.println("Bus pass added successfully!");
        }
    }

    // Find bus pass using ID
    static void findPass(String id) throws IOException {

        File file = new File(FILE);

        if (!file.exists()) {
            System.out.println("File not found!");
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);

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

    // List bus passes by route
    static void listByRoute(int route) throws IOException {

        File file = new File(FILE);

        if (!file.exists()) {
            System.out.println("File not found!");
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(file))) {

            String line;

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
                    }
                } catch (NumberFormatException e) {
                    // Skip damaged record
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        addPass("P1001", "192425315", "Sravanthi",
                12, "Avadi", "2027-03-31", 4500);

        addPass("P1002", "192425316", "Arun",
                7, "Chennai", "2027-04-30", 3800);

        System.out.println("\nSearching Pass:");
        findPass("P1001");

        System.out.println("\nPasses in Route 12:");
        listByRoute(12);
    }
}

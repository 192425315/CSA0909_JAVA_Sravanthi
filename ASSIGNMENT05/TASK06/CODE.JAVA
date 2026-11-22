
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    static class PassStore {
        File file;

        PassStore(File file) {
            this.file = file;
        }

        // Add a bus pass
        void add(String id, String regNo, String name,
                 int route, double amount) throws IOException {

            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(file, true))) {

                bw.write(id + "," + regNo + "," + name +
                        "," + route + "," + amount);
                bw.newLine();
            }
        }

        // Find pass by ID
        String[] find(String id) throws IOException {

            if (!file.exists()) {
                return null;
            }

            try (BufferedReader br = new BufferedReader(
                    new FileReader(file))) {

                String line;

                while ((line = br.readLine()) != null) {
                    try {
                        String[] data = line.split(",", -1);

                        if (data.length != 5) {
                            continue;
                        }

                        Integer.parseInt(data[3]);
                        Double.parseDouble(data[4]);

                        if (data[0].equals(id)) {
                            return data;
                        }

                    } catch (NumberFormatException e) {
                        // Skip damaged record
                    }
                }
            }

            return null;
        }

        // List passes by route
        void listByRoute(int route) throws IOException {

            try (BufferedReader br = new BufferedReader(
                    new FileReader(file))) {

                String line;
                boolean found = false;

                while ((line = br.readLine()) != null) {
                    try {
                        String[] data = line.split(",", -1);

                        if (data.length != 5) {
                            continue;
                        }

                        int routeNo = Integer.parseInt(data[3]);
                        Double.parseDouble(data[4]);

                        if (routeNo == route) {
                            System.out.println(line);
                            found = true;
                        }

                    } catch (NumberFormatException e) {
                        // Skip damaged record
                    }
                }

                if (!found) {
                    System.out.println("No passes found!");
                }
            }
        }
    }

    // Count lines using unbuffered reading
    static long unbufferedCount(File file) throws IOException {

        long lines = 0;
        int last = -1;

        try (FileInputStream in =
                     new FileInputStream(file)) {

            int data;

            while ((data = in.read()) != -1) {
                last = data;

                if (data == '\n') {
                    lines++;
                }
            }
        }

        if (last != -1 && last != '\n') {
            lines++;
        }

        return lines;
    }

    // Count lines using buffered reading
    static long bufferedCount(File file) throws IOException {

        long lines = 0;
        int last = -1;

        try (BufferedInputStream in =
                     new BufferedInputStream(
                             new FileInputStream(file), 8192)) {

            byte[] buffer = new byte[8192];
            int count;

            while ((count = in.read(buffer)) != -1) {

                for (int i = 0; i < count; i++) {
                    last = buffer[i] & 0xff;

                    if (buffer[i] == '\n') {
                        lines++;
                    }
                }
            }
        }

        if (last != -1 && last != '\n') {
            lines++;
        }

        return lines;
    }

    // Create an attendance file of required size
    static File createAttendanceFile(int sizeMB)
            throws IOException {

        File file = File.createTempFile(
                "attendance", ".txt");

        long targetSize = sizeMB * 1024L * 1024L;
        long written = 0;

        byte[] record = (
                "192425315,Student,Present\n"
        ).getBytes(StandardCharsets.UTF_8);

        try (OutputStream out =
                     new BufferedOutputStream(
                             new FileOutputStream(file))) {

            while (written + record.length <= targetSize) {
                out.write(record);
                written += record.length;
            }

            while (written < targetSize) {
                out.write('A');
                written++;
            }
        }

        return file;
    }

    // Measure file reading performance
    static void benchmarkBuffering() throws IOException {

        System.out.println("\n===== BUFFERING PERFORMANCE =====");

        int[] sizes = {1, 10, 50};

        for (int size : sizes) {

            File file = createAttendanceFile(size);

            long start = System.nanoTime();
            long lines1 = unbufferedCount(file);
            long time1 = System.nanoTime() - start;

            start = System.nanoTime();
            long lines2 = bufferedCount(file);
            long time2 = System.nanoTime() - start;

            System.out.println("\nFile Size: " + size + " MB");

            System.out.println(
                    "Unbuffered Lines: " + lines1);

            System.out.println(
                    "Unbuffered Time: " +
                    time1 / 1_000_000.0 + " ms");

            System.out.println(
                    "Buffered Lines: " + lines2);

            System.out.println(
                    "Buffered Time: " +
                    time2 / 1_000_000.0 + " ms");

            file.delete();
        }
    }

    // Measure searching as records grow
    static void benchmarkSearch() throws IOException {

        System.out.println("\n===== SEARCH PERFORMANCE =====");

        int[] sizes = {1000, 10000, 50000, 100000};

        for (int size : sizes) {

            File file = File.createTempFile(
                    "passes", ".csv");

            PassStore store = new PassStore(file);

            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(file))) {

                for (int i = 1; i <= size; i++) {
                    bw.write("P" + i + ",REG" + i +
                            ",Student" + i + ",12,4500.0");
                    bw.newLine();
                }
            }

            long start = System.nanoTime();

            String[] result = store.find("P" + size);

            long time = System.nanoTime() - start;

            System.out.println(
                    "Records: " + size +
                    " | Found: " + (result != null) +
                    " | Time: " + time / 1_000_000.0 + " ms"
            );

            file.delete();
        }
    }

    public static void main(String[] args) throws IOException {

        File file = File.createTempFile(
                "buspasses", ".txt");

        PassStore store = new PassStore(file);

        System.out.println("===== BUS PASS FILE STORE =====");

        // Add records
        store.add("P1001", "192425315",
                "Sravanthi", 12, 4500);

        store.add("P1002", "192425316",
                "Arun", 7, 3800);

        store.add("P1003", "192425317",
                "Divya", 12, 4200);

        // Add damaged record
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(file, true))) {

            bw.write("P1004,DAMAGED LINE");
            bw.newLine();
        }

        System.out.println("\nRecords added successfully!");

        // Find existing pass
        System.out.println("\n===== SEARCH EXISTING PASS =====");

        System.out.println(
                Arrays.toString(store.find("P1003")));

        // Find missing pass
        System.out.println("\n===== SEARCH MISSING PASS =====");

        System.out.println(
                Arrays.toString(store.find("P9999")));

        // List by route
        System.out.println("\n===== ROUTE 12 PASSES =====");

        store.listByRoute(12);

        // Normal Programiz run stops here
        if (args.length == 0 ||
                !args[0].equalsIgnoreCase("benchmark")) {

            System.out.println(
                    "\nBasic file-store tests completed!");

            System.out.println(
                    "Run with argument benchmark for performance tests.");

            file.delete();
            return;
        }

        // Full performance tests
        benchmarkBuffering();
        benchmarkSearch();

        file.delete();

        System.out.println(
                "\nAll experiments completed successfully!");
    }
}

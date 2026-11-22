
import java.io.*;
import java.nio.file.*;

public class Main {

    // Unbuffered reading
    static void unbufferedRead(String file) throws IOException {

        long lines = 0;
        long calls = 0;
        long start = System.nanoTime();
        int last = -1;

        try (FileInputStream fis =
                     new FileInputStream(file)) {

            int data;

            while ((data = fis.read()) != -1) {
                calls++;
                last = data;

                if (data == '\n') {
                    lines++;
                }
            }

            calls++; // Final EOF read
        }

        if (last != -1 && last != '\n') {
            lines++;
        }

        long end = System.nanoTime();

        System.out.println("\nUNBUFFERED READING");
        System.out.println("Total Lines: " + lines);
        System.out.println("Read Calls: " + calls);
        System.out.println("Time: " +
                (end - start) / 1_000_000.0 + " ms");
    }

    // Buffered reading
    static void bufferedRead(String file) throws IOException {

        long lines = 0;
        long calls = 0;
        long start = System.nanoTime();
        int last = -1;

        byte[] buffer = new byte[8192];

        try (BufferedInputStream bis =
                     new BufferedInputStream(
                             new FileInputStream(file))) {

            int bytesRead;

            while ((bytesRead = bis.read(buffer)) != -1) {

                calls++;

                for (int i = 0; i < bytesRead; i++) {

                    last = buffer[i] & 0xff;

                    if (buffer[i] == '\n') {
                        lines++;
                    }
                }
            }

            calls++; // Final EOF read
        }

        if (last != -1 && last != '\n') {
            lines++;
        }

        long end = System.nanoTime();

        System.out.println("\nBUFFERED READING");
        System.out.println("Total Lines: " + lines);
        System.out.println("Read Calls: " + calls);
        System.out.println("Time: " +
                (end - start) / 1_000_000.0 + " ms");
    }

    // Create sample attendance file
    static String createFile() throws IOException {

        File file = File.createTempFile(
                "attendance", ".txt");

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(file))) {

            for (int i = 1; i <= 40000; i++) {

                bw.write("192425315,Student" + i +
                        ",Present");

                bw.newLine();
            }
        }

        return file.getAbsolutePath();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("ATTENDANCE FILE READING");
        System.out.println("=======================");

        String file = createFile();

        System.out.println("Sample file created successfully!");

        unbufferedRead(file);

        bufferedRead(file);

        System.out.println("\nComparison Completed!");

        new File(file).delete();
    }
}

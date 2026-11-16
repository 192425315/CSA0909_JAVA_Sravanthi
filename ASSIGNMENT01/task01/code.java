
import java.util.Arrays;
import java.util.StringJoiner;

public class Main {

    private static final int REPEATS = 5;
    private static volatile long blackHole = 0;

    static class Workload {
        String name;
        String[][] tickets;

        Workload(String name, int ticketCount, int fieldCount) {
            this.name = name;
            tickets = new String[ticketCount][fieldCount];

            for (int i = 0; i < ticketCount; i++) {
                for (int j = 0; j < fieldCount; j++) {
                    tickets[i][j] =
                            "Ticket" + (i + 1) +
                            "_Field" + (j + 1);
                }
            }
        }

        int ticketCount() {
            return tickets.length;
        }

        int fieldCount() {
            return tickets[0].length;
        }
    }

    interface Test {
        long execute();
    }

    static long usingString(Workload w) {
        long totalLength = 0;

        for (String[] fields : w.tickets) {
            String ticket = "";

            for (String field : fields) {
                ticket = ticket + field + "\n";
            }

            totalLength += ticket.length();
        }

        return totalLength;
    }

    static long usingStringBuilder(Workload w) {
        long totalLength = 0;

        for (String[] fields : w.tickets) {
            StringBuilder ticket = new StringBuilder();

            for (String field : fields) {
                ticket.append(field).append('\n');
            }

            totalLength += ticket.length();
        }

        return totalLength;
    }

    static long usingStringBuffer(Workload w) {
        long totalLength = 0;

        for (String[] fields : w.tickets) {
            StringBuffer ticket = new StringBuffer();

            for (String field : fields) {
                ticket.append(field).append('\n');
            }

            totalLength += ticket.length();
        }

        return totalLength;
    }

    static long averageTime(Test test) {

        // Warm-up
        blackHole += test.execute();

        long totalTime = 0;

        for (int i = 0; i < REPEATS; i++) {
            long start = System.nanoTime();

            blackHole += test.execute();

            long end = System.nanoTime();
            totalTime += (end - start);
        }

        return totalTime / REPEATS;
    }

    static long usedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    static void memoryTest(String label, Test test) {

        System.gc();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long before = usedMemory();

        blackHole += test.execute();

        long after = usedMemory();

        System.out.printf(
                "%s memory: Before = %.2f MB, After = %.2f MB%n",
                label,
                before / (1024.0 * 1024.0),
                after / (1024.0 * 1024.0)
        );
    }

    static void testWorkload(Workload w) {

        System.out.println("\n======================================");
        System.out.println("WORKLOAD: " + w.name);
        System.out.println("Tickets: " + w.ticketCount());
        System.out.println("Fields per ticket: " + w.fieldCount());
        System.out.println("======================================");

        Test stringTest = () -> usingString(w);
        Test builderTest = () -> usingStringBuilder(w);
        Test bufferTest = () -> usingStringBuffer(w);

        long stringTime = averageTime(stringTest);
        long builderTime = averageTime(builderTest);
        long bufferTime = averageTime(bufferTest);

        System.out.println("\nAverage execution time (5 runs):");

        System.out.println("String (+): "
                + stringTime + " ns");

        System.out.println("StringBuilder: "
                + builderTime + " ns");

        System.out.println("StringBuffer: "
                + bufferTime + " ns");

        long stringObjects =
                (long) w.ticketCount() * w.fieldCount();

        long builderObjects =
                (long) w.ticketCount() * 2;

        long bufferObjects =
                (long) w.ticketCount() * 2;

        System.out.println("\nEstimated intermediate objects:");
        System.out.println("String (+): approximately "
                + stringObjects);

        System.out.println("StringBuilder: approximately "
                + builderObjects);

        System.out.println("StringBuffer: approximately "
                + bufferObjects);

        System.out.println("\nApproximate heap memory observation:");
        memoryTest("String (+)", stringTest);
        memoryTest("StringBuilder", builderTest);
        memoryTest("StringBuffer", bufferTest);
    }

    public static void main(String[] args) {

        System.out.println("RAILWAY E-TICKET GENERATION");
        System.out.println("String vs StringBuilder vs StringBuffer");

        Workload shortWorkload =
                new Workload("Short Ticket", 1, 6);

        Workload heavyWorkload =
                new Workload("Heavy Workload", 10000, 50);

        Workload customWorkload =
                new Workload("Custom Workload", 2000, 20);

        testWorkload(shortWorkload);
        testWorkload(heavyWorkload);
        testWorkload(customWorkload);

        System.out.println("\nSAMPLE TICKET");

        String[] sample = {
                "PNR=123456",
                "Passenger=Arun",
                "Coach=S1",
                "Berth=25",
                "Fare=500",
                "Destination=Chennai"
        };

        StringBuilder ticket = new StringBuilder();

        for (String field : sample) {
            ticket.append(field).append('\n');
        }

        System.out.println(ticket);

        System.out.println("STRING JOINER EXAMPLE");

        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add("PNR=123456");
        joiner.add("Passenger=Arun");
        joiner.add("Coach=S1");

        System.out.println(joiner);

        System.out.println("\nSTRING JOIN EXAMPLE");

        System.out.println(String.join(", ", Arrays.asList(sample)));

        System.out.println("\nBenchmark completed.");
    }
}

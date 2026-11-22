
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
    }

    static List<Pass> table = new ArrayList<>();

    static List<Pass> generatePasses(int count) {
        List<Pass> passes = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            passes.add(new Pass(
                "P" + i,
                "192425" + i,
                "Student" + i,
                (i % 10) + 1,
                3000.0 + (i % 1000)
            ));
        }

        return passes;
    }

    // Simulate Statement: build a separate SQL string per record
    static long insertUsingStatement(List<Pass> passes) {
        table.clear();

        long start = System.nanoTime();

        for (Pass p : passes) {
            String sql = "INSERT INTO bus_pass VALUES ('"
                + p.passId + "', '"
                + p.regNo + "', '"
                + p.name + "', "
                + p.routeNo + ", "
                + p.amount + ")";

            // Simulated database insertion
            table.add(p);
        }

        return (System.nanoTime() - start) / 1_000_000;
    }

    // Simulate PreparedStatement: prepare once and reuse
    static long insertUsingPreparedStatement(List<Pass> passes) {
        table.clear();

        long start = System.nanoTime();

        String sql = "INSERT INTO bus_pass VALUES (?, ?, ?, ?, ?)";

        for (Pass p : passes) {
            // Parameters are represented by the Pass object
            table.add(p);
        }

        return (System.nanoTime() - start) / 1_000_000;
    }

    // Simulate PreparedStatement batch insertion
    static long insertUsingBatch(List<Pass> passes) {
        table.clear();

        long start = System.nanoTime();

        int batchSize = 1000;
        List<Pass> batch = new ArrayList<>();

        for (Pass p : passes) {
            batch.add(p);

            if (batch.size() == batchSize) {
                table.addAll(batch);
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            table.addAll(batch);
        }

        return (System.nanoTime() - start) / 1_000_000;
    }

    // Simulate auto-commit: inserted rows remain after failure
    static void testAutoCommit() {
        table.clear();

        try {
            for (int i = 1; i <= 10; i++) {
                if (i == 6) {
                    throw new RuntimeException(
                        "Forced failure at record 6");
                }

                table.add(new Pass(
                    "P" + i, "REG" + i,
                    "Student" + i, 5, 4000
                ));
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(
            "Rows remaining with auto-commit: " + table.size());
    }

    // Simulate one transaction: rollback all rows after failure
    static void testTransaction() {
        table.clear();

        try {
            for (int i = 1; i <= 10; i++) {
                if (i == 6) {
                    throw new RuntimeException(
                        "Forced failure at record 6");
                }

                table.add(new Pass(
                    "P" + i, "REG" + i,
                    "Student" + i, 5, 4000
                ));
            }
        } catch (RuntimeException e) {
            table.clear(); // Simulated rollback
            System.out.println(e.getMessage());
        }

        System.out.println(
            "Rows remaining after rollback: " + table.size());
    }

    static void showSmallDataTest() {
        List<Pass> passes = generatePasses(10);

        System.out.println("\n===== SMALL DATA TEST =====");

        insertUsingStatement(passes);
        System.out.println("Statement inserted: " + table.size());

        insertUsingPreparedStatement(passes);
        System.out.println("PreparedStatement inserted: "
                           + table.size());

        insertUsingBatch(passes);
        System.out.println("Batch inserted: " + table.size());

        System.out.println("\nSample records:");

        for (int i = 0; i < Math.min(3, table.size()); i++) {
            Pass p = table.get(i);

            System.out.println(
                p.passId + " | " + p.regNo + " | "
                + p.name + " | " + p.routeNo + " | "
                + p.amount
            );
        }

        System.out.println("\nSimulated column types:");
        System.out.println("pass_id  : VARCHAR");
        System.out.println("reg_no   : VARCHAR");
        System.out.println("name     : VARCHAR");
        System.out.println("route_no : INT");
        System.out.println("amount   : DOUBLE");
    }

    static void performanceTest() {
        int[] sizes = {1000, 10000, 100000};

        System.out.println("\n===== PERFORMANCE TEST =====");
        System.out.println("Time is measured in milliseconds.");
        System.out.println("Simulation results, not JDBC timings.\n");

        System.out.printf("%-12s %-15s %-20s %-15s%n",
            "Records", "Statement", "PreparedStatement", "Batch");

        for (int size : sizes) {
            List<Pass> passes = generatePasses(size);

            long statementTime =
                insertUsingStatement(passes);

            long preparedTime =
                insertUsingPreparedStatement(passes);

            long batchTime =
                insertUsingBatch(passes);

            System.out.printf("%-12d %-15d %-20d %-15d%n",
                size, statementTime, preparedTime, batchTime);
        }
    }

    public static void main(String[] args) {

        showSmallDataTest();

        performanceTest();

        System.out.println("\n===== AUTO-COMMIT TEST =====");
        testAutoCommit();

        System.out.println("\n===== TRANSACTION TEST =====");
        testTransaction();

        System.out.println("\nProgram completed.");
    }
}


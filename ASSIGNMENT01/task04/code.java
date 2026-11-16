
import java.util.*;

class Medicine {
    String code;
    String name;
    int stock;

    Medicine(String code, String name, int stock) {
        this.code = code;
        this.name = name;
        this.stock = stock;
    }

    public String toString() {
        return code + " - " + name + " - Stock: " + stock;
    }
}

public class Main {

    static final int LOOKUPS = 100000;
    static final int REPEATS = 5;

    static Map<String, Medicine> hashMap;
    static Map<String, Medicine> treeMap;
    static Map<String, Medicine> linkedMap;

    static List<String> insertionOrder;

    static void createInventory(int size) {
        hashMap = new HashMap<>();
        treeMap = new TreeMap<>();
        linkedMap = new LinkedHashMap<>();
        insertionOrder = new ArrayList<>();

        // Insert in reverse order to make ordering differences visible.
        for (int i = size - 1; i >= 0; i--) {
            String code = String.format("MED%06d", i);
            Medicine medicine =
                new Medicine(code, "Medicine" + i, 100 + i);

            hashMap.put(code, medicine);
            treeMap.put(code, medicine);
            linkedMap.put(code, medicine);

            insertionOrder.add(code);
        }
    }

    static long lookupTime(Map<String, Medicine> map, int size) {
        long total = 0;

        for (int r = 0; r < REPEATS; r++) {
            long start = System.nanoTime();

            int stockSum = 0;

            for (int i = 0; i < LOOKUPS; i++) {
                String code =
                    String.format("MED%06d", i % size);

                Medicine m = map.get(code);

                if (m != null) {
                    stockSum += m.stock;
                }
            }

            long end = System.nanoTime();
            total += (end - start);

            // Prevent unused-result optimization.
            if (stockSum == -1) {
                System.out.println(stockSum);
            }
        }

        return total / REPEATS;
    }

    static long codeOrderTime(
        Map<String, Medicine> map, boolean alreadySorted) {

        long total = 0;

        for (int r = 0; r < REPEATS; r++) {
            long start = System.nanoTime();

            Map<String, Medicine> sorted;

            if (alreadySorted) {
                sorted = map;
            } else {
                sorted = new TreeMap<>(map);
            }

            int count = 0;
            for (Medicine m : sorted.values()) {
                count += m.stock;
            }

            long end = System.nanoTime();
            total += (end - start);

            if (count == -1) {
                System.out.println(count);
            }
        }

        return total / REPEATS;
    }

    static long insertionOrderTime(Map<String, Medicine> map) {
        long total = 0;

        for (int r = 0; r < REPEATS; r++) {
            long start = System.nanoTime();

            int count = 0;

            for (String code : insertionOrder) {
                Medicine m = map.get(code);
                if (m != null) {
                    count += m.stock;
                }
            }

            long end = System.nanoTime();
            total += (end - start);

            if (count == -1) {
                System.out.println(count);
            }
        }

        return total / REPEATS;
    }

    static void printFirstFive(
        Map<String, Medicine> map, boolean codeOrder) {

        Map<String, Medicine> report;

        if (codeOrder) {
            report = new TreeMap<>(map);
        } else {
            report = new LinkedHashMap<>();

            for (String code : insertionOrder) {
                report.put(code, map.get(code));
            }
        }

        int count = 0;

        for (Medicine m : report.values()) {
            System.out.println(m);

            count++;
            if (count == 5) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000};

        System.out.println("PHARMACY INVENTORY MAP COMPARISON");
        System.out.println("Lookups per test: " + LOOKUPS);
        System.out.println("Average of " + REPEATS + " runs");
        System.out.println("-----------------------------------");

        for (int size : sizes) {

            createInventory(size);

            System.out.println("\nInventory Size: " + size);

            System.out.println("\n100,000 Lookup Time (average):");
            System.out.println("HashMap: "
                + lookupTime(hashMap, size) + " ns");

            System.out.println("TreeMap: "
                + lookupTime(treeMap, size) + " ns");

            System.out.println("LinkedHashMap: "
                + lookupTime(linkedMap, size) + " ns");

            System.out.println("\nCode Order Report Time:");
            System.out.println("HashMap: "
                + codeOrderTime(hashMap, false) + " ns");

            System.out.println("TreeMap: "
                + codeOrderTime(treeMap, true) + " ns");

            System.out.println("LinkedHashMap: "
                + codeOrderTime(linkedMap, false) + " ns");

            System.out.println("\nInsertion Order Report Time:");
            System.out.println("HashMap: "
                + insertionOrderTime(hashMap) + " ns");

            System.out.println("TreeMap: "
                + insertionOrderTime(treeMap) + " ns");

            System.out.println("LinkedHashMap: "
                + insertionOrderTime(linkedMap) + " ns");

            if (size == 1000) {
                System.out.println("\nFirst 5 Medicines - Code Order:");
                printFirstFive(hashMap, true);

                System.out.println("\nFirst 5 Medicines - Insertion Order:");
                printFirstFive(linkedMap, false);
            }
        }

        System.out.println("\nComparison completed.");
    }
}

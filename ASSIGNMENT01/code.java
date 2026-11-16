
import java.util.*;

class Medicine {
    String code;
    String name;
    int quantity;

    Medicine(String code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }
}

class OriginalInventory {
    Map<String, Medicine> stock = new HashMap<>();

    void add(Medicine m) {
        stock.put(m.code, m);
    }

    long createReport() {
        Map<String, Medicine> sorted = new TreeMap<>(stock);
        long checksum = 0;

        for (Medicine m : sorted.values()) {
            checksum += m.code.hashCode();
        }
        return checksum;
    }
}

class RedesignedInventory {
    Map<String, Medicine> stock = new HashMap<>();
    List<Medicine> cachedReport = new ArrayList<>();

    void add(Medicine m) {
        stock.put(m.code, m);
        rebuildReport();
    }

    void rebuildReport() {
        cachedReport = new ArrayList<>(stock.values());
        cachedReport.sort(Comparator.comparing(m -> m.code));
    }

    long createReport() {
        long checksum = 0;

        for (Medicine m : cachedReport) {
            checksum += m.code.hashCode();
        }
        return checksum;
    }
}

public class Main {

    static long measureOriginal(OriginalInventory inv, int reports) {
        long checksum = 0;
        long start = System.nanoTime();

        for (int i = 0; i < reports; i++) {
            checksum += inv.createReport();
        }

        long end = System.nanoTime();
        System.out.println("Original checksum: " + checksum);
        return end - start;
    }

    static long measureRedesigned(RedesignedInventory inv, int reports) {
        long checksum = 0;
        long start = System.nanoTime();

        for (int i = 0; i < reports; i++) {
            checksum += inv.createReport();
        }

        long end = System.nanoTime();
        System.out.println("Redesigned checksum: " + checksum);
        return end - start;
    }

    static void test(int size) {
        OriginalInventory original = new OriginalInventory();
        RedesignedInventory redesigned = new RedesignedInventory();

        for (int i = size - 1; i >= 0; i--) {
            String code = String.format("MED%05d", i);
            Medicine m = new Medicine(code, "Medicine" + i, 100 + i);
            original.add(m);
            redesigned.add(m);
        }

        int reports = 500;

        // Warm-up runs
        original.createReport();
        redesigned.createReport();

        long originalTotal = 0;
        long redesignedTotal = 0;

        for (int run = 1; run <= 5; run++) {
            originalTotal += measureOriginal(original, reports);
            redesignedTotal += measureRedesigned(redesigned, reports);
        }

        System.out.println("\nInventory size: " + size);
        System.out.println("Reports per run: " + reports);
        System.out.println("Average original time: "
                + (originalTotal / 5) + " ns");
        System.out.println("Average redesigned time: "
                + (redesignedTotal / 5) + " ns");

        System.out.println("Both designs produce the same report: "
                + (original.createReport() == redesigned.createReport()));
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {
        test(1000);
        test(5000);
        test(10000);
    }
}

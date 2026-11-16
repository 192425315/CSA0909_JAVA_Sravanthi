
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

class Patient {
    final String name;
    final int severity;
    final long arrival;

    Patient(String name, int severity, long arrival) {
        this.name = name;
        this.severity = severity;
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return name + "(S" + severity + ")";
    }
}

class Triage {
    private long counter = 0;

    // Lower severity number first, then earlier arrival first.
    private final PriorityQueue<Patient> queue =
        new PriorityQueue<>(
            Comparator.comparingInt((Patient p) -> p.severity)
                .thenComparingLong(p -> p.arrival)
        );

    void admit(String name, int severity) {
        if (severity < 1 || severity > 5) {
            throw new IllegalArgumentException(
                "Severity must be between 1 and 5."
            );
        }

        queue.add(new Patient(name, severity, counter++));
    }

    Patient next() {
        return queue.poll();
    }

    int waitingCount() {
        return queue.size();
    }

    void displayTreatmentOrder() {
        StringBuilder order = new StringBuilder();

        Patient p;
        while ((p = next()) != null) {
            order.append(p).append(" ");
        }

        System.out.println(order.toString().trim());
    }
}

public class Main {

    static void originalAssignmentTest() {
        System.out.println("ASSIGNMENT TEST");

        Triage er = new Triage();

        er.admit("Ravi", 3);
        er.admit("Meena", 1);
        er.admit("John", 3);
        er.admit("Asha", 5);
        er.admit("Kumar", 1);
        er.admit("Lata", 2);

        System.out.println("Treatment Order:");
        er.displayTreatmentOrder();
    }

    static void eightPatientTest() {
        System.out.println("\nEIGHT PATIENT TEST");

        Triage er = new Triage();

        er.admit("Arun", 3);
        er.admit("Divya", 1);
        er.admit("Kiran", 5);
        er.admit("Meena", 2);
        er.admit("Ravi", 1);
        er.admit("Priya", 4);
        er.admit("Sita", 3);
        er.admit("Vijay", 2);

        System.out.println("Treatment Order:");
        er.displayTreatmentOrder();
    }

    static long benchmark(int size) {
        long totalTime = 0;
        int repetitions = 5;

        for (int r = 0; r < repetitions; r++) {

            PriorityQueue<Patient> pq =
                new PriorityQueue<>(
                    Comparator.comparingInt(
                        (Patient p) -> p.severity
                    ).thenComparingLong(p -> p.arrival)
                );

            long start = System.nanoTime();

            for (int i = 0; i < size; i++) {
                pq.add(new Patient(
                    "Patient" + i,
                    (i % 5) + 1,
                    i
                ));
            }

            while (!pq.isEmpty()) {
                pq.poll();
            }

            long end = System.nanoTime();
            totalTime += end - start;
        }

        return totalTime / repetitions;
    }

    public static void main(String[] args) {

        originalAssignmentTest();
        eightPatientTest();

        System.out.println("\nPRIORITY QUEUE PERFORMANCE");
        System.out.println("Average of 5 runs");
        System.out.println("Time includes admission and removal.");

        int[] sizes = {1000, 5000, 10000};

        for (int size : sizes) {
            System.out.println(
                size + " patients: " + benchmark(size) + " ns"
            );
        }

        System.out.println("\nFAIRNESS ANALYSIS");
        System.out.println(
            "A severity-5 patient may wait longer if critical "
            + "patients continue arriving."
        );

        System.out.println(
            "A waiting-time rule can gradually increase "
            + "the priority of patients who wait too long."
        );
    }
}

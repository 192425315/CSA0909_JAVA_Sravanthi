
import java.util.*;

public class Main {

    static final int RUNS = 5;
    static final int OPERATIONS = 100;

    interface ListTest {
        long run(List<Integer> list);
    }

    interface QueueTest {
        long run(Deque<Integer> deque);
    }

    static volatile long resultHolder = 0;

    static List<Integer> createList(
            String type, int size) {

        List<Integer> list;

        if (type.equals("ArrayList")) {
            list = new ArrayList<>();
        } else {
            list = new LinkedList<>();
        }

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        return list;
    }

    static long measureList(
            String type, int size, ListTest test) {

        long total = 0;

        for (int r = 0; r < RUNS; r++) {

            List<Integer> list = createList(type, size);

            long start = System.nanoTime();
            long result = test.run(list);
            long end = System.nanoTime();

            resultHolder += result;
            total += end - start;
        }

        return total / RUNS;
    }

    static long measureDeque(
            int size, QueueTest test) {

        long total = 0;

        for (int r = 0; r < RUNS; r++) {

            Deque<Integer> deque = new ArrayDeque<>();

            for (int i = 0; i < size; i++) {
                deque.addLast(i);
            }

            long start = System.nanoTime();
            long result = test.run(deque);
            long end = System.nanoTime();

            resultHolder += result;
            total += end - start;
        }

        return total / RUNS;
    }

    static void testList(String type, int size) {

        System.out.println("\n" + type + " - Size: " + size);

        System.out.println("Add at end: "
                + measureList(type, size, list -> {
                    for (int i = 0; i < OPERATIONS; i++) {
                        list.add(i);
                    }
                    return list.size();
                }) + " ns");

        System.out.println("Insert at front: "
                + measureList(type, size, list -> {
                    for (int i = 0; i < OPERATIONS; i++) {
                        list.add(0, i);
                    }
                    return list.size();
                }) + " ns");

        System.out.println("Insert in middle: "
                + measureList(type, size, list -> {
                    for (int i = 0; i < OPERATIONS; i++) {
                        list.add(list.size() / 2, i);
                    }
                    return list.size();
                }) + " ns");

        System.out.println("Remove from front: "
                + measureList(type, size, list -> {
                    long sum = 0;
                    for (int i = 0; i < OPERATIONS; i++) {
                        sum += list.remove(0);
                    }
                    return sum;
                }) + " ns");

        System.out.println("Remove from middle: "
                + measureList(type, size, list -> {
                    long sum = 0;
                    for (int i = 0; i < OPERATIONS; i++) {
                        sum += list.remove(list.size() / 2);
                    }
                    return sum;
                }) + " ns");

        System.out.println("Access k-th element: "
                + measureList(type, size, list -> {
                    long sum = 0;
                    for (int i = 0; i < OPERATIONS; i++) {
                        sum += list.get(size / 2);
                    }
                    return sum;
                }) + " ns");

        System.out.println("Iterate all elements: "
                + measureList(type, size, list -> {
                    long sum = 0;
                    for (int value : list) {
                        sum += value;
                    }
                    return sum;
                }) + " ns");
    }

    static void testUpNext(int size) {

        System.out.println("\nArrayDeque - Up Next Size: " + size);

        long time = measureDeque(size, deque -> {

            long sum = 0;

            for (int i = 0; i < OPERATIONS; i++) {
                deque.addLast(10000 + i);
                deque.addFirst(20000 + i);

                sum += deque.removeFirst();
                sum += deque.removeLast();
            }

            return sum;
        });

        System.out.println("Add/remove at both ends: "
                + time + " ns");
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000};

        System.out.println("PLAYLIST COLLECTION COMPARISON");
        System.out.println("Average of " + RUNS + " runs");
        System.out.println("Operations per test: " + OPERATIONS);

        for (int size : sizes) {

            System.out.println("\n================================");
            System.out.println("PLAYLIST SIZE: " + size);
            System.out.println("================================");

            testList("ArrayList", size);
            testList("LinkedList", size);
            testUpNext(size);
        }

        System.out.println("\nBenchmark completed.");
    }
}

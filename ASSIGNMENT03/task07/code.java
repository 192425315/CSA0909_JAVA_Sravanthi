
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

class Main {

    static final int TOTAL_FILES = 10000;
    static final int POOL_SIZE = 4;

    static class Result {
        String file;
        boolean success;
        int score;

        Result(String file, boolean success, int score) {
            this.file = file;
            this.success = success;
            this.score = score;
        }
    }

    static class ScoreTask implements Callable<Result> {

        String file;

        ScoreTask(String file) {
            this.file = file;
        }

        public Result call() {

            try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

                String data = reader.readLine();

                if (data == null) {
                    throw new IOException("Empty file");
                }

                int score = Integer.parseInt(data.trim());

                if (score < 0 || score > 100) {
                    throw new Exception("Invalid score");
                }

                return new Result(file, true, score);

            } catch (Exception e) {
                return new Result(file, false, 0);
            }
        }
    }

    public static void main(String[] args)
            throws Exception {

        File folder = new File("scores");
        folder.mkdirs();

        // Create sample files
        for (int i = 1; i <= TOTAL_FILES; i++) {

            if (i > 9995) {
                continue;
            }

            File file = new File(
                folder, "student" + i + ".txt");

            try (PrintWriter writer =
                     new PrintWriter(file)) {

                if (i > 9990) {
                    writer.println("invalid");
                } else {
                    writer.println(80);
                }
            }
        }

        ExecutorService executor =
            Executors.newFixedThreadPool(POOL_SIZE);

        ConcurrentMap<String, Result> results =
            new ConcurrentHashMap<>();

        List<Future<Result>> futures =
            new ArrayList<>();

        long start = System.currentTimeMillis();

        try {

            for (int i = 1; i <= TOTAL_FILES; i++) {

                String file =
                    "scores/student" + i + ".txt";

                futures.add(
                    executor.submit(new ScoreTask(file)));
            }

            for (Future<Result> future : futures) {

                Result result = future.get();

                results.put(result.file, result);
            }

        } finally {

            executor.shutdown();

            if (!executor.awaitTermination(
                    1, TimeUnit.MINUTES)) {

                executor.shutdownNow();
            }
        }

        int success = 0;
        int failure = 0;
        long totalScore = 0;

        for (Result result : results.values()) {

            if (result.success) {
                success++;
                totalScore += result.score;
            } else {
                failure++;
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("Result Processing Completed.");
        System.out.println("Total Files: " + TOTAL_FILES);
        System.out.println("Successful: " + success);
        System.out.println("Failed: " + failure);
        System.out.println("Total Valid Scores: " + totalScore);
        System.out.println("Thread Pool Size: " + POOL_SIZE);

        System.out.println(
            "Execution Time: " + (end - start) + " ms");
    }
}



class Main {

    public static void main(String[] args)
            throws InterruptedException {

        TokenCounter counter = new TokenCounter();

        int[] collected = new int[2];

        // Producer thread
        Thread cook = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    counter.put(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer threads
        Thread[] students = new Thread[2];

        for (int i = 0; i < 2; i++) {
            final int id = i;

            students[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        collected[id] += counter.take();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        cook.start();

        for (Thread t : students) {
            t.start();
        }

        cook.join();

        for (Thread t : students) {
            t.join();
        }

        System.out.println(
            "Sum of tokens collected: "
            + (collected[0] + collected[1])
            + " (expected 210)");

        // Wallet transfer demonstration
        Wallet w1 = new Wallet(1, 1000);
        Wallet w2 = new Wallet(2, 500);

        Thread t1 = new Thread(
            () -> WalletTransfer.transfer(w1, w2, 100));

        Thread t2 = new Thread(
            () -> WalletTransfer.transfer(w2, w1, 50));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(
            "Total wallet balance: "
            + (w1.balance + w2.balance));
    }
}

// Bounded buffer
class TokenCounter {

    private final int[] buffer = new int[5];

    private int count = 0;
    private int in = 0;
    private int out = 0;

    // Producer
    public synchronized void put(int token)
            throws InterruptedException {

        while (count == buffer.length) {
            wait();
        }

        buffer[in] = token;
        in = (in + 1) % buffer.length;
        count++;

        notifyAll();
    }

    // Consumer
    public synchronized int take()
            throws InterruptedException {

        while (count == 0) {
            wait();
        }

        int token = buffer[out];

        out = (out + 1) % buffer.length;
        count--;

        notifyAll();

        return token;
    }
}

// Wallet account
class Wallet {

    int id;
    int balance;

    Wallet(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}

// Deadlock prevention using lock ordering
class WalletTransfer {

    static void transfer(
            Wallet from, Wallet to, int amount) {

        Wallet first = from.id < to.id ? from : to;
        Wallet second = from.id < to.id ? to : from;

        synchronized (first) {
            synchronized (second) {

                if (from.balance >= amount) {
                    from.balance -= amount;
                    to.balance += amount;

                    System.out.println(
                        "Transfer completed: Rs." + amount);
                }
            }
        }
    }
}

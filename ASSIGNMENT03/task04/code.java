
class ScannerStation extends Thread {

    int scripts;

    ScannerStation(String name, int scripts) {
        super(name);
        this.scripts = scripts;
    }

    public void run() {
        try {
            for (int i = 1; i <= scripts; i++) {

                System.out.println(
                    getName() + " scanning script " + i);

                Thread.sleep(500);
            }

            System.out.println(
                getName() + " completed " + scripts + " scripts.");

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}

class RunnableStation implements Runnable {

    String name;
    int scripts;

    RunnableStation(String name, int scripts) {
        this.name = name;
        this.scripts = scripts;
    }

    public void run() {
        try {
            for (int i = 1; i <= scripts; i++) {

                System.out.println(
                    name + " scanning script " + i);

                Thread.sleep(500);
            }

            System.out.println(
                name + " completed " + scripts + " scripts.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Main {

    public static void main(String[] args)
            throws InterruptedException {

        Thread t1 = new ScannerStation("Station-1", 3);

        Thread t2 = new Thread(
            new RunnableStation("Station-2", 3));

        Thread t3 = new ScannerStation("Station-3", 3);

        Thread t4 = new Thread(
            new RunnableStation("Station-4", 3));

        // Start all threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.out.println(
            "Station 1 alive: " + t1.isAlive());

        // Wait for all threads
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("All stations completed.");

        System.out.println(
            "Station 1 alive: " + t1.isAlive());

        System.out.println("Total scripts scanned: 12");
    }
}


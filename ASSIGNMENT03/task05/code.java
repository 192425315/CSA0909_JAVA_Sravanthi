
class SeatBooking {

    private boolean[] booked;
    private int bookingsMade = 0;

    SeatBooking(int seats) {
        booked = new boolean[seats];
    }

    // Synchronized method prevents double booking
    public synchronized void book(int seat) {

        if (!booked[seat]) {

            booked[seat] = true;
            bookingsMade++;

            System.out.println(
                Thread.currentThread().getName()
                + " booked seat " + seat);

        } else {

            System.out.println(
                Thread.currentThread().getName()
                + " could not book seat " + seat);
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        SeatBooking screen = new SeatBooking(10);

        Thread[] users = new Thread[100];

        for (int i = 0; i < 100; i++) {

            final int seat = i % 10;

            users[i] = new Thread(
                () -> screen.book(seat),
                "User-" + (i + 1));

            users[i].start();
        }

        for (Thread t : users) {
            t.join();
        }

        System.out.println(
            "Seats sold: " + screen.bookingsMade);
    }
}


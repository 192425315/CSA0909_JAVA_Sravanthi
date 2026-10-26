
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingTicket {
    int ticketNo;
    String regNo;
    LocalDateTime entryTime;

    static int nextTicketNo = 1001;

    ParkingTicket() {
        this("NOT-ASSIGNED");
    }

    ParkingTicket(String regNo) {
        ticketNo = nextTicketNo++;
        this.regNo = regNo;
        entryTime = LocalDateTime.now();
    }

    void displayTicket() {
        DateTimeFormatter f =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Ticket Number: " + ticketNo);
        System.out.println("Registration Number: " + regNo);
        System.out.println("Entry Time: " + entryTime.format(f));
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        ParkingTicket t1 = new ParkingTicket();
        ParkingTicket t2 = new ParkingTicket("TN01AB1234");
        ParkingTicket t3 = new ParkingTicket("TN02CD5678");

        System.out.println("CAMPUS SMART-PARKING SYSTEM");
        System.out.println("===========================");

        t1.displayTicket();
        t2.displayTicket();
        t3.displayTicket();
    }
}


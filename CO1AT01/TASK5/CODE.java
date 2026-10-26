
class ParkingTicket {
    private static int nextTicketNumber;
    private static int totalTicketsIssued;

    private final int ticketNumber;
    private final String registrationNumber;
    private final String vehicleType;

    // Static block runs once when the class is initialized.
    static {
        nextTicketNumber = 1001;
        totalTicketsIssued = 0;
    }

    public ParkingTicket(String registrationNumber, String vehicleType) {
        this.ticketNumber = nextTicketNumber++;
        totalTicketsIssued++;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
    }

    public void displayTicket() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("---------------------------");
    }

    public static int getTotalTicketsIssued() {
        return totalTicketsIssued;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Campus Smart-Parking System");
        System.out.println("===========================");

        ParkingTicket ticket1 =
                new ParkingTicket("TN01AB1234", "Car");

        ParkingTicket ticket2 =
                new ParkingTicket("TN02CD5678", "Two-Wheeler");

        ParkingTicket ticket3 =
                new ParkingTicket("TN03EF9012", "Electric Car");

        System.out.println("\nParking Tickets:");

        ticket1.displayTicket();
        ticket2.displayTicket();
        ticket3.displayTicket();

        System.out.println("Total Tickets Issued: "
                + ParkingTicket.getTotalTicketsIssued());

        // Remove the reference to ticket3.
        ticket3 = null;

        System.out.println("Ticket 3 reference set to null.");
        System.out.println("Total Tickets Issued: "
                + ParkingTicket.getTotalTicketsIssued());
    }
}


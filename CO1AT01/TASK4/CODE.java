
class ParkingTicket {
    private int ticketNumber;
    private String registrationNumber;
    private String vehicleType;

    // Default constructor
    public ParkingTicket() {
        this(1001, "TN01AB1234", "Car");
    }

    // Parameterized constructor
    public ParkingTicket(int ticketNumber, String registrationNumber,
                         String vehicleType) {
        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
    }

    // Copy constructor
    public ParkingTicket(ParkingTicket other) {
        this(other.ticketNumber, other.registrationNumber,
             other.vehicleType);
    }

    // Display ticket details
    public void displayTicket() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("---------------------------");
    }
}

public class Main {
    public static void main(String[] args) {
        // Create an object using the default constructor
        ParkingTicket ticket1 = new ParkingTicket();

        // Create an object using the parameterized constructor
        ParkingTicket ticket2 = new ParkingTicket(
            1002, "TN02CD5678", "Two-Wheeler"
        );

        // Create a copy of ticket2
        ParkingTicket ticket3 = new ParkingTicket(ticket2);

        System.out.println("Campus Smart-Parking System");
        System.out.println("===========================");

        System.out.println("Default Constructor:");
        ticket1.displayTicket();

        System.out.println("Parameterized Constructor:");
        ticket2.displayTicket();

        System.out.println("Copy Constructor:");
        ticket3.displayTicket();
    }
}


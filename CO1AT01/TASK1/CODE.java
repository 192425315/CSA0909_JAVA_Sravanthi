
public class Main {
    public static void main(String[] args) {
        String registrationNumber = "TN01AB1234";
        String vehicleType = "Car";
        int floorNumber = 0;
        int slotNumber = 1;
        int ticketNumber = 1001;

        System.out.println("Campus Smart-Parking System");
        System.out.println("---------------------------");
        System.out.println("Vehicle parked successfully.");
        System.out.println("Floor: " + floorNumber);
        System.out.println("Slot: " + slotNumber);
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Entry Time: " +
                java.time.LocalDateTime.now());
    }
}


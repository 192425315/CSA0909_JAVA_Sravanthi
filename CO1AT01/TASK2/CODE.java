
public class Main {
    public static void main(String[] args) {
        // Vehicle details
        String registrationNumber = "TN01AB1234";
        String vehicleType = "Car";

        // Parking details
        int floorNumber = 0;
        int slotNumber = 1;

        // Entry and exit time in minutes
        int entryTime = 600; // 10:00 AM
        int exitTime = 730;  // 12:10 PM

        // Parking fee per started hour
        double ratePerHour = 30.0;

        // Calculate parking duration
        int duration = exitTime - entryTime;

        // Calculate billable hours using ceiling division
        int billableHours = (duration + 59) / 60;

        // Calculate parking fee
        double parkingFee = billableHours * ratePerHour;

        // Jagged array: floors can have different numbers of slots
        String[][] parkingSlots = {
            new String[3],
            new String[2],
            new String[4]
        };

        // Allocate the vehicle to the selected slot
        parkingSlots[floorNumber][slotNumber] = registrationNumber;

        // Display results
        System.out.println("Campus Smart-Parking System");
        System.out.println("---------------------------");
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Floor Number: " + floorNumber);
        System.out.println("Slot Number: " + slotNumber);
        System.out.println("Parking Duration: " + duration + " minutes");
        System.out.println("Billable Hours: " + billableHours);
        System.out.printf("Parking Fee: Rs %.2f%n", parkingFee);
        System.out.println("Slot Allocation: Successful");
    }
}



class ParkingSlot {
    private int floorNumber;
    private int slotNumber;
    private boolean occupied;

    public ParkingSlot(int floorNumber, int slotNumber) {
        setFloorNumber(floorNumber);
        setSlotNumber(slotNumber);
        this.occupied = false;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        if (floorNumber < 0) {
            throw new IllegalArgumentException(
                "Floor number cannot be negative."
            );
        }
        this.floorNumber = floorNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        if (slotNumber < 0) {
            throw new IllegalArgumentException(
                "Slot number cannot be negative."
            );
        }
        this.slotNumber = slotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void displayDetails() {
        System.out.println("Floor Number: " + floorNumber);
        System.out.println("Slot Number: " + slotNumber);
        System.out.println("Occupied: " + occupied);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Campus Smart-Parking System");
        System.out.println("===========================");

        ParkingSlot slot = new ParkingSlot(0, 5);

        System.out.println("\nInitial Slot Details:");
        slot.displayDetails();

        slot.setOccupied(true);

        System.out.println("\nAfter Parking a Vehicle:");
        slot.displayDetails();

        // Wrapper class and autoboxing
        Integer floor = slot.getFloorNumber();
        Integer number = slot.getSlotNumber();

        System.out.println("\nWrapper Class Example:");
        System.out.println("Floor as Integer: " + floor);
        System.out.println("Slot as Integer: " + number);

        // Safe comparison: compare values, not Integer references.
        Integer first = 1000;
        Integer second = 1000;

        System.out.println("Integer values are equal: "
                + first.equals(second));
    }
}

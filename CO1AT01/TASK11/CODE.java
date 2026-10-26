
import java.util.HashMap;
import java.util.Map;

class ParkingTicket {
    private final String registrationNumber;
    private final String vehicleType;
    private final int floor;
    private final int slot;
    private final int entryMinute;
    private final boolean monthlyPass;

    public ParkingTicket(String registrationNumber, String vehicleType,
                         int floor, int slot, int entryMinute,
                         boolean monthlyPass) {
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.floor = floor;
        this.slot = slot;
        this.entryMinute = entryMinute;
        this.monthlyPass = monthlyPass;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getFloor() {
        return floor;
    }

    public int getSlot() {
        return slot;
    }

    public int getEntryMinute() {
        return entryMinute;
    }

    public boolean hasMonthlyPass() {
        return monthlyPass;
    }
}

class ParkingFacility {
    private final String[][] slots = {
        new String[2],
        new String[1]
    };

    private final Map<String, ParkingTicket> activeTickets =
            new HashMap<>();

    private double dailyCollection = 0.0;

    public boolean enter(String regNo, String type,
                         int entryMinute, boolean monthlyPass) {
        if (regNo == null || regNo.isBlank()) {
            System.out.println("Invalid registration number.");
            return false;
        }

        if (entryMinute < 0) {
            System.out.println("Invalid entry time.");
            return false;
        }

        if (activeTickets.containsKey(regNo)) {
            System.out.println("Duplicate registration: " + regNo);
            return false;
        }

        if (!type.equals("TWO_WHEELER") &&
            !type.equals("CAR") &&
            !type.equals("STUDENT") &&
            !type.equals("STAFF") &&
            !type.equals("VISITOR")) {
            System.out.println("Unknown vehicle type: " + type);
            return false;
        }

        if (monthlyPass &&
            !type.equals("STUDENT") && !type.equals("STAFF")) {
            System.out.println("Monthly pass is not valid for " + type);
            return false;
        }

        for (int f = 0; f < slots.length; f++) {
            for (int s = 0; s < slots[f].length; s++) {
                if (slots[f][s] == null) {
                    slots[f][s] = regNo;

                    ParkingTicket ticket = new ParkingTicket(
                        regNo, type, f, s, entryMinute, monthlyPass
                    );

                    activeTickets.put(regNo, ticket);
                    System.out.println(regNo + " parked at Floor "
                            + f + ", Slot " + s);
                    return true;
                }
            }
        }

        System.out.println("Parking facility is full.");
        return false;
    }

    public double exit(String regNo, int exitMinute) {
        ParkingTicket ticket = activeTickets.get(regNo);

        if (ticket == null) {
            System.out.println("No active ticket for " + regNo);
            return -1;
        }

        if (exitMinute < ticket.getEntryMinute()) {
            System.out.println("Invalid exit time for " + regNo);
            return -1;
        }

        int duration = exitMinute - ticket.getEntryMinute();
        double fee = calculateFee(ticket, duration);

        slots[ticket.getFloor()][ticket.getSlot()] = null;
        activeTickets.remove(regNo);
        dailyCollection += fee;

        System.out.println(regNo + " parked for " + duration
                + " minutes. Fee: Rs " + fee);

        return fee;
    }

    private double calculateFee(ParkingTicket ticket, int minutes) {
        if (ticket.hasMonthlyPass()) {
            return 0.0;
        }

        if (minutes == 0) {
            return 0.0;
        }

        int hours = (minutes + 59) / 60;
        double rate;

        switch (ticket.getVehicleType()) {
            case "TWO_WHEELER":
                rate = 10.0;
                break;
            case "CAR":
            case "VISITOR":
                rate = 30.0;
                break;
            case "STUDENT":
            case "STAFF":
                rate = 20.0;
                break;
            default:
                throw new IllegalArgumentException("Unsupported type");
        }

        return hours * rate;
    }

    public boolean releaseSlot(int floor, int slot) {
        if (floor < 0 || floor >= slots.length ||
            slot < 0 || slot >= slots[floor].length) {
            return false;
        }

        if (slots[floor][slot] == null) {
            return false;
        }

        return true;
    }

    public double getDailyCollection() {
        return dailyCollection;
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingFacility parking = new ParkingFacility();

        System.out.println("Campus Smart-Parking System");
        System.out.println("===========================");

        System.out.println("\n1. Normal Parking Operations");
        parking.enter("TN01AB1234", "TWO_WHEELER", 0, false);
        parking.enter("TN02CD5678", "CAR", 0, false);
        parking.enter("TN03EF9012", "STUDENT", 0, true);

        System.out.println("\n2. Invalid Cases");
        parking.enter("TN02CD5678", "CAR", 10, false);
        parking.enter("TN04GH3456", "TRUCK", 10, false);

        System.out.println("Invalid slot position accepted: "
                + parking.releaseSlot(5, 0));

        System.out.println("\n3. Vehicle Exit and Fee Calculation");
        parking.exit("TN01AB1234", 60);
        parking.exit("TN02CD5678", 61);
        parking.exit("TN03EF9012", 120);

        System.out.println("\n4. Zero-Minute Parking");
        parking.enter("TN05JK7890", "CAR", 200, false);
        parking.exit("TN05JK7890", 200);

        System.out.printf("%nTotal Daily Collection: Rs %.2f%n",
                parking.getDailyCollection());
    }
}

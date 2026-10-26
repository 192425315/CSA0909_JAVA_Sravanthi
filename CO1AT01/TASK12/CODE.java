
import java.util.HashMap;
import java.util.Map;

interface PricingRule {
    double calculateFee(int entryMinute, int exitMinute);
}

class StandardPricing implements PricingRule {
    private final double hourlyRate;

    public StandardPricing(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateFee(int entryMinute, int exitMinute) {
        int duration = exitMinute - entryMinute;
        if (duration <= 0) {
            return 0.0;
        }
        int hours = (duration + 59) / 60;
        return hours * hourlyRate;
    }
}

class EBIkePricing implements PricingRule {
    private final double flatFee;

    public EBIkePricing(double flatFee) {
        this.flatFee = flatFee;
    }

    @Override
    public double calculateFee(int entryMinute, int exitMinute) {
        return exitMinute <= entryMinute ? 0.0 : flatFee;
    }
}

class DeliveryVanPricing implements PricingRule {
    private final double carRate;

    public DeliveryVanPricing(double carRate) {
        this.carRate = carRate;
    }

    @Override
    public double calculateFee(int entryMinute, int exitMinute) {
        if (exitMinute <= entryMinute) {
            return 0.0;
        }

        double fee = 0.0;

        // Charge each started hour at the rate applicable
        // at the beginning of that hour.
        for (int start = entryMinute; start < exitMinute; start += 60) {
            int rateTime = start % 1440;
            boolean peakHours = rateTime >= 9 * 60
                    && rateTime < 17 * 60;

            fee += peakHours ? 2 * carRate : carRate;
        }

        return fee;
    }
}

class OneDayPass {
    private final String visitorRegistration;
    private final int validDay;

    public OneDayPass(String visitorRegistration, int validDay) {
        this.visitorRegistration = visitorRegistration;
        this.validDay = validDay;
    }

    public boolean isValid(String registration, int day) {
        return visitorRegistration.equals(registration)
                && validDay == day;
    }
}

class ParkingTicket {
    final String registration;
    final String type;
    final int floor;
    final int slot;
    final int entryMinute;
    final int entryDay;
    final OneDayPass pass;

    ParkingTicket(String registration, String type, int floor, int slot,
                  int entryMinute, int entryDay, OneDayPass pass) {
        this.registration = registration;
        this.type = type;
        this.floor = floor;
        this.slot = slot;
        this.entryMinute = entryMinute;
        this.entryDay = entryDay;
        this.pass = pass;
    }
}

class ParkingFacility {
    private final String[][] slots = {
        new String[3],
        new String[2],
        new String[4],
        new String[3],
        new String[5]
    };

    private final Map<String, ParkingTicket> activeTickets =
            new HashMap<>();

    private final PricingRule carPricing = new StandardPricing(30.0);
    private final PricingRule bikePricing = new StandardPricing(10.0);
    private final PricingRule eBikePricing = new EBIkePricing(25.0);
    private final PricingRule vanPricing = new DeliveryVanPricing(30.0);

    public boolean enter(String registration, String type, int entryMinute,
                         int entryDay, OneDayPass pass) {
        if (registration == null || registration.isBlank()
                || entryMinute < 0 || entryMinute >= 1440) {
            System.out.println("Invalid entry details.");
            return false;
        }

        if (activeTickets.containsKey(registration)) {
            System.out.println("Duplicate active registration: " + registration);
            return false;
        }

        if (!type.equals("CAR") && !type.equals("TWO_WHEELER")
                && !type.equals("E_BIKE") && !type.equals("DELIVERY_VAN")
                && !type.equals("VISITOR")) {
            System.out.println("Unknown vehicle type: " + type);
            return false;
        }

        if (pass != null && !pass.isValid(registration, entryDay)) {
            System.out.println("Invalid one-day pass.");
            return false;
        }

        for (int f = 0; f < slots.length; f++) {
            for (int s = 0; s < slots[f].length; s++) {
                if (slots[f][s] == null) {
                    slots[f][s] = registration;
                    activeTickets.put(registration,
                            new ParkingTicket(registration, type, f, s,
                                    entryMinute, entryDay, pass));
                    System.out.println(registration + " allocated Floor "
                            + f + ", Slot " + s);
                    return true;
                }
            }
        }

        System.out.println("Parking facility is full.");
        return false;
    }

    public double exit(String registration, int exitMinute, int exitDay) {
        ParkingTicket ticket = activeTickets.get(registration);

        if (ticket == null) {
            System.out.println("No active ticket for " + registration);
            return -1.0;
        }

        if (exitDay < ticket.entryDay
                || (exitDay == ticket.entryDay
                    && exitMinute < ticket.entryMinute)) {
            System.out.println("Invalid exit time.");
            return -1.0;
        }

        double fee;

        if (ticket.pass != null
                && ticket.pass.isValid(registration, ticket.entryDay)
                && exitDay == ticket.entryDay) {
            fee = 0.0;
        } else if (ticket.type.equals("E_BIKE")) {
            fee = eBikePricing.calculateFee(
                    ticket.entryMinute, exitMinute);
        } else if (ticket.type.equals("DELIVERY_VAN")) {
            fee = vanPricing.calculateFee(
                    ticket.entryMinute, exitMinute);
        } else if (ticket.type.equals("TWO_WHEELER")) {
            fee = bikePricing.calculateFee(
                    ticket.entryMinute, exitMinute);
        } else {
            fee = carPricing.calculateFee(
                    ticket.entryMinute, exitMinute);
        }

        slots[ticket.floor][ticket.slot] = null;
        activeTickets.remove(registration);

        System.out.printf("%s: Parking fee Rs %.2f%n",
                registration, fee);
        return fee;
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingFacility parking = new ParkingFacility();

        System.out.println("Campus Smart-Parking System - Task 12");
        System.out.println("=====================================");

        // Times are minutes from midnight; day 1 is the test day.
        parking.enter("TN01EB1234", "E_BIKE", 8 * 60, 1, null);

        parking.enter("TN02DV5678", "DELIVERY_VAN",
                10 * 60, 1, null);

        OneDayPass visitorPass = new OneDayPass("TN03VS9012", 1);
        parking.enter("TN03VS9012", "VISITOR",
                11 * 60, 1, visitorPass);

        parking.enter("TN04CA3456", "CAR", 13 * 60, 1, null);

        System.out.println("\nVehicle Exit and Fee Calculation:");
        double total = 0.0;
        total += parking.exit("TN01EB1234", 9 * 60, 1);
        total += parking.exit("TN02DV5678", 12 * 60, 1);
        total += parking.exit("TN03VS9012", 14 * 60, 1);
        total += parking.exit("TN04CA3456", 14 * 60 + 1, 1);

        System.out.printf("%nTotal Collection: Rs %.2f%n", total);
    }
}

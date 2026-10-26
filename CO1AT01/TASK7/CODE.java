
import java.util.Objects;

class Vehicle {
    private final String registrationNumber;

    public Vehicle(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException(
                "Registration number cannot be empty."
            );
        }
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    protected double ratePerHour() {
        return 20.0;
    }

    public double calculateFee(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException(
                "Parking duration cannot be negative."
            );
        }

        int hours = minutes == 0 ? 0 : (minutes + 59) / 60;
        return hours * ratePerHour();
    }

    @Override
    public String toString() {
        return "Vehicle{registrationNumber='" + registrationNumber + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vehicle)) {
            return false;
        }

        Vehicle other = (Vehicle) obj;
        return registrationNumber.equals(other.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}

class TwoWheeler extends Vehicle {
    public TwoWheeler(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    protected double ratePerHour() {
        return 10.0;
    }
}

class Car extends Vehicle {
    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    protected double ratePerHour() {
        return 30.0;
    }
}

class ElectricCar extends Car {
    private static final double CHARGING_FEE_PER_KWH = 12.0;

    public ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }

    public double chargingFee(int kWh) {
        if (kWh < 0) {
            throw new IllegalArgumentException(
                "Charging units cannot be negative."
            );
        }
        return kWh * CHARGING_FEE_PER_KWH;
    }

    @Override
    public String toString() {
        return "ElectricCar{registrationNumber='"
                + getRegistrationNumber() + "'}";
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle bike = new TwoWheeler("TN01AB1234");
        Vehicle car = new Car("TN02CD5678");
        ElectricCar ev = new ElectricCar("TN03EF9012");

        System.out.println("Campus Smart-Parking System");
        System.out.println("===========================");

        System.out.printf("Two-Wheeler Fee: Rs %.2f%n",
                bike.calculateFee(120));

        System.out.printf("Car Fee: Rs %.2f%n",
                car.calculateFee(120));

        System.out.printf("Electric Car Fee: Rs %.2f%n",
                ev.calculateFee(120) + ev.chargingFee(5));

        System.out.println("\nVehicle Details:");
        System.out.println(bike);
        System.out.println(car);
        System.out.println(ev);

        Vehicle anotherCar = new Car("TN02CD5678");

        System.out.println("\nSame Registration Number: "
                + car.equals(anotherCar));

        System.out.println("Hash Codes Match: "
                + (car.hashCode() == anotherCar.hashCode()));
    }
}

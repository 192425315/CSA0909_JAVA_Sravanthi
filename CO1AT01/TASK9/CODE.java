
interface Chargeable {
    double chargingFee(int kWh);

    default void showChargingMessage() {
        System.out.println("Charging service is available.");
    }
}

interface PassHolder {
    boolean hasValidPass();
}

abstract class Vehicle {
    private final String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract double calculateParkingFee(int hours);
}

class TwoWheeler extends Vehicle {
    public TwoWheeler(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateParkingFee(int hours) {
        return hours * 10.0;
    }
}

class Car extends Vehicle {
    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateParkingFee(int hours) {
        return hours * 30.0;
    }
}

class ElectricCar extends Vehicle implements Chargeable, PassHolder {
    private final boolean validPass;

    public ElectricCar(String registrationNumber, boolean validPass) {
        super(registrationNumber);
        this.validPass = validPass;
    }

    @Override
    public double calculateParkingFee(int hours) {
        return hours * 30.0;
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 12.0;
    }

    @Override
    public boolean hasValidPass() {
        return validPass;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Campus Smart-Parking System");
        System.out.println("===========================");

        Vehicle bike = new TwoWheeler("TN01AB1234");
        Vehicle car = new Car("TN02CD5678");
        ElectricCar ev = new ElectricCar("TN03EF9012", true);

        System.out.printf("Two-Wheeler Parking Fee: Rs %.2f%n",
                bike.calculateParkingFee(2));

        System.out.printf("Car Parking Fee: Rs %.2f%n",
                car.calculateParkingFee(2));

        System.out.printf("Electric Car Parking Fee: Rs %.2f%n",
                ev.calculateParkingFee(2));

        System.out.printf("Electric Car Charging Fee: Rs %.2f%n",
                ev.chargingFee(5));

        ev.showChargingMessage();

        System.out.println("Valid Pass: " + ev.hasValidPass());
    }
}

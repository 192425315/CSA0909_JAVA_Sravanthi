
class Vehicle {
    protected String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        System.out.println("Vehicle constructor called");
    }

    public void displayDetails() {
        System.out.println("Registration Number: " + registrationNumber);
    }
}

class TwoWheeler extends Vehicle {
    public TwoWheeler(String registrationNumber) {
        super(registrationNumber);
        System.out.println("TwoWheeler constructor called");
    }
}

class Car extends Vehicle {
    public Car(String registrationNumber) {
        super(registrationNumber);
        System.out.println("Car constructor called");
    }
}

class ElectricCar extends Car {
    public ElectricCar(String registrationNumber) {
        super(registrationNumber);
        System.out.println("ElectricCar constructor called");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Campus Smart-Parking System");
        System.out.println("===========================");

        System.out.println("\nCreating a Two-Wheeler:");
        TwoWheeler bike = new TwoWheeler("TN01AB1234");
        bike.displayDetails();

        System.out.println("\nCreating a Car:");
        Car car = new Car("TN02CD5678");
        car.displayDetails();

        System.out.println("\nCreating an Electric Car:");
        ElectricCar electricCar = new ElectricCar("TN03EF9012");
        electricCar.displayDetails();
    }
}

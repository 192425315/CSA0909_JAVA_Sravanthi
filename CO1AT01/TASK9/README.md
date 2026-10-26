# Task 09 – Introduce Abstraction

## Aim
To understand abstraction in Java by using abstract classes and interfaces in the Campus Smart-Parking System.

## Problem Description
The Campus Smart-Parking System supports different types of vehicles, including two-wheelers, cars and electric cars. Each vehicle has a parking fee, and electric cars also require a charging facility.

In this program, I used an abstract class to define common vehicle behaviour and interfaces to provide additional capabilities.

## Classes and Interfaces Used

**1. Vehicle**

The `Vehicle` class is an abstract class. It stores the vehicle registration number and declares the abstract method `calculateParkingFee()`.

**2. TwoWheeler**

The `TwoWheeler` class extends `Vehicle` and calculates the parking fee at Rs 10 per hour.

**3. Car**

The `Car` class extends `Vehicle` and calculates the parking fee at Rs 30 per hour.

**4. ElectricCar**

The `ElectricCar` class extends `Vehicle` and implements the `Chargeable` and `PassHolder` interfaces. It calculates parking fees, charging fees and checks pass validity.

**5. Chargeable**

The `Chargeable` interface defines the `chargingFee()` method. It also contains a default method that displays a charging service message.

**6. PassHolder**

The `PassHolder` interface defines the `hasValidPass()` method to check whether a valid pass is available.

## Why I Used Abstraction
An abstract class is useful when different classes share common information and behaviour. In this program, all vehicles have a registration number, but their parking-fee calculations can differ.

Interfaces are useful for defining capabilities. For example, an electric car can support charging and hold a valid pass without requiring every vehicle to implement those features.

Java allows a class to extend one class and implement multiple interfaces. This allows `ElectricCar` to inherit vehicle behaviour and provide additional capabilities.

## Algorithm
1. Start the program.
2. Create objects for a two-wheeler, car and electric car.
3. Calculate and display their parking fees.
4. Calculate the electric car's charging fee.
5. Display the charging service message.
6. Check and display the pass validity.
7. Stop the program.

## How to Run
1. Open an online Java compiler.
2. Copy the complete Java code into the editor.
3. Make sure the main class is named `Main`.
4. Run the program.
5. Check the output.
6. Take a screenshot and save it as `output.png`.

## Expected Output

```text
Campus Smart-Parking System
===========================
Two-Wheeler Parking Fee: Rs 20.00
Car Parking Fee: Rs 60.00
Electric Car Parking Fee: Rs 60.00
Electric Car Charging Fee: Rs 60.00
Charging service is available.
Valid Pass: true
```

## Alternative Approach
Instead of using an abstract `Vehicle` class and a `Chargeable` interface, the design could use interfaces for vehicle behaviour and an abstract class for charging behaviour.

However, an interface cannot have constructors or ordinary per-object instance fields in the same way that an abstract class can. An abstract class is therefore useful for storing common vehicle state, while interfaces allow a class to implement multiple capabilities.

Both abstract-class and interface method calls have constant-time dispatch overhead in the usual case. Each vehicle object stores its own instance fields, while static and default interface methods do not require a separate object for each method.

## Result
I implemented abstraction using the `Vehicle` abstract class and the `Chargeable` and `PassHolder` interfaces. The program demonstrates different parking fees, electric-vehicle charging, a default interface method and pass validation.

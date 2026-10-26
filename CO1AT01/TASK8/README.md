# Task 08 – Apply Polymorphism

## Aim
To understand polymorphism in Java using method overriding, dynamic method dispatch and an interface in the Campus Smart-Parking System.

## Problem Description
The Campus Smart-Parking System supports different vehicle types, such as two-wheelers, cars and electric cars. Each vehicle type has a different hourly parking rate. Electric cars also have an additional charging fee.

In this program, I used a common `Vehicle` reference to store different types of vehicle objects and calculate their parking fees.

## Classes and Interface Used

- `Vehicle`: An abstract class that stores the registration number and provides the parking-fee calculation method.
- `TwoWheeler`: Returns a parking rate of Rs 10 per hour.
- `Car`: Returns a parking rate of Rs 30 per hour.
- `ElectricCar`: Inherits from `Car` and implements the `Chargeable` interface.
- `Chargeable`: Defines the `chargingFee()` method for vehicles that support charging.
- `FeeDemo`: Contains the main method and demonstrates polymorphism.

## Runtime Polymorphism
Runtime polymorphism occurs when an overridden method is selected according to the actual object type at runtime.

In this program, the array `Vehicle[] parked` stores objects of `TwoWheeler`, `Car` and `ElectricCar`. When `calculateFee(130)` is called, Java uses the appropriate `ratePerHour()` implementation for each object.

Every started hour is charged in full. Therefore, 130 minutes is charged as three hours.

## Compile-Time Polymorphism
Compile-time polymorphism can be achieved through method overloading. For example, a class could provide both `calculateFee(int minutes)` and `calculateFee(LocalTime entry, LocalTime exit)` methods. The compiler selects the appropriate method according to the arguments.

The supplied `FeeDemo` implementation demonstrates runtime polymorphism through overriding. The overloaded method example above describes how compile-time polymorphism could be added to the design.

## Use of the Chargeable Interface
The `Chargeable` interface defines a charging capability. The `ElectricCar` class implements this interface and calculates the charging fee at Rs 12 per kWh.

The program uses `instanceof` to check whether a vehicle supports charging before adding the charging fee.

## Algorithm
1. Create a two-wheeler, car and electric car.
2. Store all three objects in a `Vehicle` array.
3. Calculate the parking fee for 130 minutes.
4. Add the charging fee for vehicles that implement `Chargeable`.
5. Display the vehicle type, registration number and total fee.
6. Compare two vehicle objects using `equals()`.
7. Display the comparison result.

## How to Run
1. Open an online Java compiler.
2. Copy the complete code into the editor.
3. Make sure the main class is `FeeDemo`, if the compiler asks.
4. Run the program.
5. Check the output and take a screenshot.
6. Save the screenshot as `output.png`.

## Expected Output

```text
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```

## Why Does the Last Line Print true?
The last line compares a `Car` and an `ElectricCar` that have the same registration number, `TN02B2`.

The `equals()` method compares vehicle registration numbers. Since the registration numbers match, the method returns `true`.

In a real parking system, the design should consider whether registration numbers alone are sufficient for equality and ensure that duplicate active registrations are not allowed.

## Alternative Approach
Instead of using dynamic method dispatch, the program could use `instanceof` checks and casting to identify each vehicle type and calculate its fee.

With method overriding, the appropriate rate method is selected through dynamic dispatch. With explicit type checks, the code may need additional conditions whenever a new vehicle type is introduced.

Both approaches can calculate the fee in O(1) time when each calculation uses a fixed number of operations. Overriding generally makes the code easier to maintain as the number of vehicle types grows.

## Result
The program demonstrates runtime polymorphism using a `Vehicle` array, method overriding and the `Chargeable` interface. It calculates the correct parking and charging fees and demonstrates equality based on registration numbers.

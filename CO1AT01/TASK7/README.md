# Task 07 – Override and Specialise Behaviour

## Aim
To implement method overriding in Java and understand how subclasses provide specialised behaviour in the Campus Smart-Parking System.

## Problem Description
Different vehicle types have different parking rates. A two-wheeler, a car and an electric car should not necessarily pay the same parking fee.

To solve this problem, I created a `Vehicle` superclass and specialised subclasses that override the `ratePerHour()` method.

## Classes Used

- `Vehicle`: Stores the registration number and provides the common parking-fee calculation.
- `TwoWheeler`: Overrides `ratePerHour()` to return Rs 10 per hour.
- `Car`: Overrides `ratePerHour()` to return Rs 30 per hour.
- `ElectricCar`: Inherits the car rate and provides an additional charging-fee calculation.

## Method Overriding
Method overriding occurs when a subclass provides its own implementation of an inherited method with the same method signature.

In this program, `TwoWheeler` and `Car` override `ratePerHour()`. Java calls the appropriate implementation according to the actual object type.

The `@Override` annotation helps the compiler identify mistakes in method signatures.

## Use of the `final` Keyword
I used `final` for the vehicle registration number so that it cannot be reassigned after construction. The charging rate is also declared as a `static final` constant because it is shared and should not change during program execution.

## Object-Class Methods

### toString()
The `toString()` method returns a readable description of an object. The `ElectricCar` class overrides it to display its specific class name.

### equals()
The `equals()` method compares vehicles using their registration numbers. Two vehicle objects with the same registration number are considered equal in this implementation.

### hashCode()
The `hashCode()` method uses the registration number to generate a hash code. Equal vehicle objects must have equal hash codes, which is important when using hash-based collections.

## Algorithm
1. Create objects for a two-wheeler, car and electric car.
2. Calculate the parking fee for each vehicle.
3. Add the charging fee for the electric car.
4. Display each vehicle's details using `toString()`.
5. Create another car with an existing registration number.
6. Compare the two cars using `equals()`.
7. Compare their hash codes.
8. Display the results.

## How to Run
1. Open an online Java compiler.
2. Copy the complete Java code into the editor.
3. Run the program.
4. Check the fees and equality results.
5. Take a screenshot of the actual output and save it as `output.png`.

## Expected Output

```text
Campus Smart-Parking System
===========================
Two-Wheeler Fee: Rs 20.00
Car Fee: Rs 60.00
Electric Car Fee: Rs 120.00

Vehicle Details:
Vehicle{registrationNumber='TN01AB1234'}
Vehicle{registrationNumber='TN02CD5678'}
ElectricCar{registrationNumber='TN03EF9012'}

Same Registration Number: true
Hash Codes Match: true
```

## Alternative Approach
Instead of overriding `ratePerHour()`, the program could use a single method containing a `switch` statement based on the vehicle type.

However, adding a new vehicle type would require updating the switch statement. With method overriding, a new subclass can provide its own rate without changing the existing subclasses.

Both approaches calculate a fee in O(1) time when the rate lookup and fee calculation are constant-time operations. Overriding keeps vehicle-specific behaviour organised within each subclass.

## Result
I implemented method overriding for vehicle parking rates and demonstrated `toString()`, `equals()` and `hashCode()`. The program calculates different fees for different vehicle types and treats vehicles with the same registration number as equal.

# Task 06 – Design the Inheritance Hierarchy

## Aim
To implement inheritance in Java and understand how superclasses, subclasses and constructors work in the Campus Smart-Parking System.

## Problem Description
The Campus Smart-Parking System supports different vehicle types, including two-wheelers, cars and electric cars. These vehicles share common information, such as their registration numbers.

Instead of writing the same code separately for every vehicle type, I created a common `Vehicle` superclass and derived subclasses from it.

## Classes Used

**1. Vehicle**

The `Vehicle` class is the superclass. It stores the registration number and provides a method to display vehicle details.

**2. TwoWheeler**

The `TwoWheeler` class inherits from `Vehicle` and represents a two-wheeler.

**3. Car**

The `Car` class inherits from `Vehicle` and represents a car.

**4. ElectricCar**

The `ElectricCar` class inherits from `Car`. It demonstrates multilevel inheritance because it indirectly inherits the properties and methods of `Vehicle`.

## Types of Inheritance

- **Hierarchical inheritance:** `TwoWheeler` and `Car` inherit from the same superclass, `Vehicle`.
- **Multilevel inheritance:** `ElectricCar` inherits from `Car`, which inherits from `Vehicle`.

## Use of the `super` Keyword
I used `super(registrationNumber)` inside the subclass constructors to call the constructor of the parent class. This initializes the registration number without repeating the initialization code.

## Constructor Execution Order
When an `ElectricCar` object is created, its constructors execute in the following order:

1. `Vehicle` constructor
2. `Car` constructor
3. `ElectricCar` constructor

The parent constructor executes before the subclass constructor because Java calls the superclass constructor first.

## UML Class Diagram

```text
          Vehicle
             |
      +------+------+
      |             |
  TwoWheeler       Car
                     |
                 ElectricCar
```

The arrows represent inheritance from the parent class to its subclasses.

## Algorithm
1. Start the program.
2. Create a `TwoWheeler` object.
3. Display its registration number.
4. Create a `Car` object.
5. Display its registration number.
6. Create an `ElectricCar` object.
7. Observe the order of constructor execution.
8. Display its registration number.
9. Stop the program.

## How to Run
1. Open an online Java compiler.
2. Copy the complete Java code into the editor.
3. Run the program.
4. Check the constructor messages and vehicle details.
5. Take a screenshot of the actual output and save it as `output.png`.

## Expected Output

```text
Campus Smart-Parking System
===========================

Creating a Two-Wheeler:
Vehicle constructor called
TwoWheeler constructor called
Registration Number: TN01AB1234

Creating a Car:
Vehicle constructor called
Car constructor called
Registration Number: TN02CD5678

Creating an Electric Car:
Vehicle constructor called
Car constructor called
ElectricCar constructor called
Registration Number: TN03EF9012
```

## Alternative Approach: Composition
Instead of inheritance, a single `Vehicle` class could contain a `VehicleType` object that stores the vehicle category and hourly rate.

With inheritance, a new vehicle type may require a new subclass. With composition, a new type can often be introduced by creating a new type object without changing the main `Vehicle` class.

Both approaches can calculate a fee in O(1) time when the rate is directly available. Inheritance stores the common fields in each vehicle object, while composition also stores a reference to its vehicle-type object.

## Result
I implemented the `Vehicle`, `TwoWheeler`, `Car` and `ElectricCar` classes using hierarchical and multilevel inheritance. The program demonstrates code reuse, the `super` keyword and the order in which constructors execute.

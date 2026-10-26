# Task 10 – Enforce Encapsulation through Packages and Access Modifiers

## Aim
To implement encapsulation in Java using private fields, public getters and setters, access modifiers, and wrapper classes.

## Problem Description
The Campus Smart-Parking System stores information about parking floors and slots. This information must be protected so that invalid values cannot be assigned directly.

In this program, I created a `ParkingSlot` class with private fields and public methods to access and update the data safely.

## Class Used

### ParkingSlot
The `ParkingSlot` class stores the floor number, slot number and occupancy status. It provides getters to read the values and setters to update them.

The setters validate the floor and slot numbers and reject negative values.

## Encapsulation
Encapsulation means combining data and methods in a class and restricting direct access to the internal data.

I declared the fields as `private`. This prevents other classes from changing them directly. Public getters and setters provide controlled access to the fields.

## Access Modifiers Used

- `private`: Used for fields that should not be accessed directly outside the class.
- `public`: Used for constructors and methods that other classes need to access.
- `protected`: Can be used when subclasses need access to inherited members.
- Default access: Can be used for members that should be accessible only within the same package.

## Package Structure
In a complete Java project, the classes can be organized into packages such as:

```text
parking/
├── model/
│   └── ParkingSlot.java
├── service/
│   └── ParkingService.java
└── app/
    └── Main.java
```

The `model` package stores data classes, the `service` package manages parking operations, and the `app` package contains the main program.

This demonstration keeps the code in one file to make it easier to execute in an online compiler.

## Wrapper Classes and Autoboxing
Java provides wrapper classes such as `Integer` for `int` and `Double` for `double`.

In the program, an `int` value is assigned to an `Integer` variable. Java automatically converts the primitive value into its wrapper object. This is called autoboxing.

When comparing `Integer` objects, I used `equals()` to compare their values. Using `==` may compare object references instead of numeric values.

## Algorithm
1. Start the program.
2. Create a parking slot object.
3. Display the initial slot details.
4. Mark the slot as occupied.
5. Display the updated slot details.
6. Convert the floor and slot numbers into `Integer` objects.
7. Compare two `Integer` values using `equals()`.
8. Stop the program.

## How to Run
1. Open an online Java compiler.
2. Copy the complete Java code into the editor.
3. Ensure the main class is named `Main`.
4. Run the program.
5. Check the output and take a screenshot.
6. Save the screenshot as `output.png`.

## Expected Output

```text
Campus Smart-Parking System
===========================

Initial Slot Details:
Floor Number: 0
Slot Number: 5
Occupied: false

After Parking a Vehicle:
Floor Number: 0
Slot Number: 5
Occupied: true

Wrapper Class Example:
Floor as Integer: 0
Slot as Integer: 5
Integer values are equal: true
```

## Alternative Approach
If the fields were public, other classes could directly assign invalid values without using validation methods. For example, a negative floor number could be stored without being rejected.

Using private fields with validating setters ensures that every update passes through the required checks. Each validation takes O(1) time. If validation were repeated in multiple calling classes, the same checks would have to be maintained in several places.

## Result
I implemented encapsulation using private fields, public getters and setters, validation, and wrapper classes. The program demonstrates controlled access to parking-slot data and safe comparison of integer wrapper values.

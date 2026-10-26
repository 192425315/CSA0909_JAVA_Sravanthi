# Task 04 – Design Classes, Objects and Constructors

## Aim
To create classes and objects in Java and understand how constructors are used to initialize objects in the Campus Smart-Parking System.

## Problem Description
In a smart-parking system, every parking ticket contains information such as the ticket number, vehicle registration number and vehicle type.

In this program, I created a `ParkingTicket` class to store ticket details. I used different constructors to create ticket objects in different ways.

## Class Used

### ParkingTicket
The `ParkingTicket` class stores the ticket number, registration number and vehicle type. It also contains a method to display the ticket details.

## Constructors Used

**1. Default Constructor**

The default constructor initializes the ticket with predefined details. It uses `this()` to call the parameterized constructor.

**2. Parameterized Constructor**

The parameterized constructor accepts the ticket number, registration number and vehicle type as arguments. It assigns these values to the object's fields.

**3. Copy Constructor**

The copy constructor creates a new ticket object using the details of an existing ticket object.

## Use of the `this` Keyword
I used `this` to refer to the current object's fields. I also used `this()` to call another constructor in the same class. This avoids repeating the initialization code.

## Object Creation
In the `main()` method, I created three objects:

- `ticket1` uses the default constructor.
- `ticket2` uses the parameterized constructor.
- `ticket3` uses the copy constructor to copy the details of `ticket2`.

Each object has its own instance fields stored as part of its object in memory. The local reference variables in `main()` refer to these objects.

## Algorithm
1. Start the program.
2. Create a ticket using the default constructor.
3. Create another ticket using the parameterized constructor.
4. Create a third ticket by copying the second ticket.
5. Display the details of all three tickets.
6. Stop the program.

## How to Run
1. Open an online Java compiler.
2. Copy the program into the editor.
3. Run the program.
4. Check the displayed ticket details.
5. Take a screenshot of the output and save it as `output.png`.

## Expected Output

```text
Campus Smart-Parking System
===========================
Default Constructor:
Ticket Number: 1001
Registration Number: TN01AB1234
Vehicle Type: Car
---------------------------
Parameterized Constructor:
Ticket Number: 1002
Registration Number: TN02CD5678
Vehicle Type: Two-Wheeler
---------------------------
Copy Constructor:
Ticket Number: 1002
Registration Number: TN02CD5678
Vehicle Type: Two-Wheeler
---------------------------
```

## Alternative Approach
Instead of using overloaded constructors, I could create an object using a default constructor and then assign values using setter methods. However, this approach may allow an object to exist with incomplete information before all the setters are called.

Constructors help initialize an object when it is created. A static factory method could also be used to create and return a properly initialized ticket object.

Creating n ticket objects takes O(n) total time when each object requires a fixed number of initialization operations. The objects require O(n) total space. Constructor chaining avoids duplicated initialization code but does not change the overall time or space complexity.

## Result
I implemented the `ParkingTicket` class using default, parameterized and copy constructors. The program successfully creates ticket objects and displays their details, demonstrating object creation and constructor overloading in Java.

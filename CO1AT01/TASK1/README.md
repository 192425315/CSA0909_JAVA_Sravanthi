# Task 01 – Understand the Problem and Identify the Objects

## Aim
To understand the Campus Smart-Parking System and identify the classes and objects needed to develop the system using Java.

## Problem Description
In a college campus, many students, staff members and visitors use the parking facility. The parking area contains different floors and parking slots for vehicles.

When a vehicle enters the campus, the system should assign an available parking slot and generate a parking ticket. The system should also store the vehicle details and entry time. When the vehicle leaves, the slot should become available for another vehicle.

To implement this system, I identified the important objects and created Java classes to represent them.

## Classes Used

**1. Vehicle**

The `Vehicle` class stores the vehicle's registration number and type. It also checks that the details are not empty.

**2. ParkingSlot**

The `ParkingSlot` class represents an individual parking slot. It checks whether the slot is available and prevents another vehicle from occupying an already occupied slot.

**3. ParkingTicket**

The `ParkingTicket` class generates a ticket number and stores the vehicle details and entry time. It also displays the parking ticket information.

## Input
- Vehicle registration number
- Vehicle type
- Floor number
- Slot number

## Output
- Vehicle parking status
- Assigned floor and slot number
- Parking ticket number
- Vehicle details
- Entry time

## Business Rules
1. Every vehicle must have a registration number and vehicle type.
2. A parking slot can contain only one vehicle at a time.
3. A ticket can be generated only for a valid vehicle.
4. Floor and slot numbers cannot be negative.
5. An occupied parking slot cannot accept another vehicle.

## Algorithm
1. Create a vehicle object with its registration number and type.
2. Create a parking slot object.
3. Check whether the parking slot is available.
4. If the slot is available, park the vehicle.
5. Generate a parking ticket and display the details.
6. If the slot is occupied, display a message that the slot is unavailable.

## How to Run the Program

I used Java to implement the program.

Open the terminal in the `Task01` folder and execute the following commands:

```bash
javac Task01.java
java Task01
```

## Expected Output

```text
Vehicle parked successfully.
Floor: 0, Slot: 1
Ticket Number: 1001
Registration Number: TN01AB1234
Vehicle Type: Car
Entry Time: <runtime date and time>
```

The entry time will be different each time the program runs.

## Alternative Approach

Instead of creating a separate `Vehicle` object, we could store vehicle details in parallel arrays such as `regNo[]`, `type[]` and `entryTime[]`.

Both approaches require a linear search, which takes O(n) time in the worst case when searching through n vehicles.

However, the object-oriented approach keeps the details of each vehicle together. If I need to add another property, such as the vehicle colour, I can add it to the `Vehicle` class. With parallel arrays, I would need to maintain another array and ensure that all the indices remain consistent.

## Result

I identified the main objects required for the Campus Smart-Parking System and implemented the `Vehicle`, `ParkingSlot` and `ParkingTicket` classes in Java. The program demonstrates basic object-oriented programming, data validation and parking-slot management.

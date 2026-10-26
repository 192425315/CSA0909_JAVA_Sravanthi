# Task 05 – Apply Static Members and the Object Life Cycle

## Aim
To understand the use of static variables, static methods, static blocks and the object life cycle in Java through a Campus Smart-Parking System.

## Problem Description
In a parking facility, every vehicle entering the campus receives a parking ticket. Each ticket must have a unique ticket number.

Instead of maintaining a separate ticket counter for every object, I used a static variable to share the counter among all `ParkingTicket` objects.

## Static Members Used

### 1. Static Variables
I used `nextTicketNumber` to generate ticket numbers starting from 1001. The variable `totalTicketsIssued` stores the total number of tickets created during the current program execution.

These variables belong to the class rather than individual ticket objects, so all objects share them.

### 2. Static Block
The static block initializes the ticket counter and total number of issued tickets. It executes once when the class is initialized.

### 3. Static Method
The `getTotalTicketsIssued()` method returns the total number of tickets created. I call it using the class name:

`ParkingTicket.getTotalTicketsIssued()`

## Object Life Cycle
When a new `ParkingTicket` object is created, memory is allocated for the object and its instance fields. The local reference variable points to that object.

In the program, I set the `ticket3` reference to `null`. If no other references point to that object, it becomes eligible for garbage collection. Garbage collection is managed by the Java Virtual Machine, so setting a reference to `null` does not immediately destroy the object.

The static ticket counter remains unchanged because it belongs to the class, not to an individual ticket object.

## Algorithm
1. Start the program.
2. Initialize the static ticket counter to 1001.
3. Create three parking ticket objects.
4. Assign a unique ticket number to each object.
5. Display the details of all tickets.
6. Display the total number of tickets issued.
7. Set the reference to the third ticket to `null`.
8. Display the total number of tickets again.
9. Stop the program.

## How to Run
1. Open an online Java compiler.
2. Copy the Java code into the editor.
3. Run the program.
4. Check the ticket numbers and total count.
5. Take a screenshot of the output and save it as `output.png`.

## Expected Output

```text
Campus Smart-Parking System
===========================

Parking Tickets:
Ticket Number: 1001
Registration Number: TN01AB1234
Vehicle Type: Car
---------------------------
Ticket Number: 1002
Registration Number: TN02CD5678
Vehicle Type: Two-Wheeler
---------------------------
Ticket Number: 1003
Registration Number: TN03EF9012
Vehicle Type: Electric Car
---------------------------
Total Tickets Issued: 3
Ticket 3 reference set to null.
Total Tickets Issued: 3
```

## Alternative Approach
If the ticket counter were an instance variable, every `ParkingFacility` object could maintain its own counter. For example, two facilities might both generate ticket number 1001, causing duplicate numbers if tickets are managed across both facilities.

A shared static counter avoids this problem within one running application and one shared class definition. For a multi-facility system, the counter should be managed centrally or ticket numbers should include a facility identifier. A static counter alone does not guarantee uniqueness across separate application runs.

For k facility objects, an instance counter requires O(k) counter storage, while one shared static counter requires O(1) counter storage. Creating n tickets takes O(n) total time when each ticket requires constant-time initialization.

## Result
The program demonstrates static variables, a static initialization block and a static method. It generates sequential ticket numbers and shows that setting an object reference to `null` does not automatically decrease the total number of tickets issued.

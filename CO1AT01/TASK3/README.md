# Task 03 – Design the Control Logic

## Aim
To design the control logic for allocating available parking slots and releasing occupied slots using Java loops and conditional statements.

## Problem Description
The Campus Smart-Parking System has multiple floors, and each floor contains a fixed number of parking slots. When a vehicle enters, the program must find the first available slot and allocate it. When a vehicle leaves, the program must release its slot.

If all slots are occupied, the program should return `null`. It should also reject invalid floor or slot numbers and prevent a slot from being released more than once.

## Algorithm for Slot Allocation
1. Start from the first floor.
2. Check every slot on that floor.
3. If a slot contains `null`, store the vehicle number in that slot.
4. Return the floor and slot indices.
5. Stop searching after finding the first available slot.
6. If no available slot is found, return `null`.

## Algorithm for Releasing a Slot
1. Receive the floor and slot numbers.
2. Check whether the floor number is valid.
3. Check whether the slot number is valid for that floor.
4. Check whether the slot is occupied.
5. If the position is valid and occupied, set its value to `null` and return `true`.
6. Otherwise, return `false`.

## Pseudocode for Allocation

```text
BEGIN
    FOR each floor f
        FOR each slot s in floor f
            IF slot[f][s] is empty THEN
                slot[f][s] = vehicle number
                RETURN floor f and slot s
            END IF
        END FOR
    END FOR
    RETURN null
END
```

## Pseudocode for Parking Fee Calculation

The assignment describes a fee structure with an initial hourly charge, additional hourly charges and a daily maximum. The following pseudocode demonstrates this logic. The exact rates must be decided by the system's pricing rules.

```text
BEGIN
    INPUT parking duration in minutes
    INPUT hourly rate
    INPUT daily maximum

    IF duration <= 0 THEN
        fee = 0
    ELSE
        billable hours = ceiling(duration / 60)
        fee = billable hours * hourly rate

        IF fee > daily maximum THEN
            fee = daily maximum
        END IF
    END IF

    RETURN fee
END
```

## Java Concepts Used
- **Two-dimensional jagged array:** Stores parking slots on different floors.
- **Nested loops:** Search through floors and slots.
- **if statements:** Check whether a slot is free and whether a position is valid.
- **return:** Returns the allocated position or stops the search.
- **null:** Represents a free slot or indicates that no slot is available.
- **Boolean values:** Indicate whether a slot was released successfully.

## How to Run
1. Create a file named `ParkingFacility.java`.
2. Copy the Java code into the file.
3. Compile and run it using Java.

```bash
javac ParkingFacility.java
java ParkingFacility
```

For an online compiler, paste the complete code and run it. The public class name is `ParkingFacility`.

## Expected Output

```text
[0, 0]
[0, 1]
[1, 0]
null
true false false
[0, 1]
```

## Complexity Analysis
Let F be the number of floors and S be the maximum number of slots on any floor.

- **Allocation:** O(F × S) in the worst case because the program may examine every slot.
- **Release:** O(1) because the program directly checks the requested floor and slot.
- **Space complexity:** O(N) for the slot array, where N is the total number of slots.

## Alternative Approach
Instead of searching every slot, the program could maintain a stack of free slot positions. Allocation and release could then take O(1) time on average, assuming the free-slot stack is maintained correctly. However, additional storage and careful handling are required to prevent duplicate free-slot entries.

## Result
The program successfully allocates the first available parking slot, returns `null` when the facility is full, and releases a valid occupied slot. It also rejects invalid positions and repeated release attempts.

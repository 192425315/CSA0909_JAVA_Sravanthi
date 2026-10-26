# Task 11 – Implement, Test and Defend the Design

## Aim
To implement and test a Campus Smart-Parking System in Java and evaluate its correctness using normal operations, boundary cases and invalid inputs.

## Problem Description
The Campus Smart-Parking System manages vehicle entry, parking-slot allocation, ticket creation, vehicle exit and parking-fee calculation.

In this program, I used a parking facility with two floors containing two slots on the first floor and one slot on the second floor. The system stores active parking tickets and prevents duplicate active registrations.

## Classes Used

**1. ParkingTicket**

The `ParkingTicket` class stores the vehicle registration number, vehicle type, allocated floor and slot, entry time and monthly-pass status.

**2. ParkingFacility**

The `ParkingFacility` class manages parking slots, allocates available spaces, maintains active tickets, validates vehicle entry and calculates parking fees when vehicles exit.

**3. Main**

The `Main` class runs the test scenarios and displays the results.

## Features Implemented
- Allocates the first available parking slot.
- Rejects duplicate active registration numbers.
- Rejects unknown vehicle types.
- Supports monthly passes for students and staff.
- Calculates fees using started-hour billing.
- Releases a slot when a vehicle exits.
- Maintains the total collection for the day.
- Rejects invalid exit times and invalid slot positions.

## Parking Fee Rules
- Two-wheeler: Rs 10 per started hour.
- Car: Rs 30 per started hour.
- Student or staff with a valid monthly pass: free parking.
- Zero-minute parking: Rs 0.

For positive durations, the number of billable hours is calculated using:

`(minutes + 59) / 60`

For example, 61 minutes is billed as two hours.

## Test Cases

| Test case | Expected result |
|---|---|
| Vehicle enters an available slot | Slot allocated successfully |
| Duplicate active registration | Entry rejected |
| Unknown vehicle type | Entry rejected |
| Invalid slot position | Rejected |
| Parking for 60 minutes | One hour charged |
| Parking for 61 minutes | Two hours charged |
| Valid monthly pass | Parking fee is zero |
| Parking for zero minutes | Parking fee is zero |
| Vehicle exits | Slot becomes available |

## Manual Calculation of Daily Collection

The program uses three parking scenarios to calculate the daily collection.

1. Two-wheeler parked for 60 minutes: 1 hour × Rs 10 = Rs 10.
2. Car parked for 61 minutes: 2 hours × Rs 30 = Rs 60.
3. Student with a valid monthly pass parked for 120 minutes: Rs 0.

Total collection = Rs 10 + Rs 60 + Rs 0 = **Rs 70**.

The program should display a daily collection of Rs 70.00 before any additional fee-paying vehicles are included.

## How to Run
1. Open an online Java compiler.
2. Copy the complete Java program into the editor.
3. Ensure that the main class is named `Main`.
4. Run the program.
5. Compare the actual output with the expected output.
6. Take a screenshot and save it as `output.png`.

## Alternative Approach: ArrayList and HashMap
An array can store active parking tickets, but searching for a ticket by registration number takes O(n) time in the worst case.

An `ArrayList<ParkingTicket>` also requires O(n) time to search for a ticket by registration number.

A `HashMap<String, ParkingTicket>` stores tickets using the registration number as the key. It provides O(1) average-time lookup, insertion and removal, although worst-case lookup can be O(n).

For n active vehicles, all three approaches require O(n) storage. I used a `HashMap` because it makes finding an active ticket by registration number convenient and efficient.

## Design Evaluation
The implementation uses encapsulation by keeping ticket fields private. It uses a separate ticket class to store parking information and a facility class to manage parking operations.

One limitation is that the current implementation uses a small fixed parking layout and simplified pricing rules. A production system would need persistent storage, stronger validation, configurable pricing, and appropriate handling of concurrent vehicle entries.

## Result
The program demonstrates vehicle entry and exit, slot allocation, ticket management, parking-fee calculation, monthly-pass handling and invalid-input testing. The manually calculated collection matches the expected total of Rs 70.00 for the three fee scenarios.

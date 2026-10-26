# Task 02 – Choose the Data Representation

## Aim
To select suitable Java data types and arrays for storing vehicle details, parking slots, parking duration and parking fees.

## Problem Description
The Campus Smart-Parking System needs to store vehicle information and manage parking slots on different floors. Each floor may have a different number of slots. The system must also calculate the parking duration and fee correctly.

## Data Types Used

- `String` is used to store the vehicle registration number and vehicle type.
- `int` is used for floor number, slot number, entry time, exit time and parking duration.
- `double` is used to store the hourly rate and parking fee.
- `String[][]` is used to represent the parking slots on different floors.

## Why I Chose a Jagged Array
I used a jagged array because every floor can have a different number of parking slots. For example, the first floor can have three slots, the second floor can have two slots and the third floor can have four slots.

This representation avoids allocating unnecessary slots on floors that have a smaller capacity.

## Parking Fee Calculation
The parking duration is calculated by subtracting the entry time from the exit time.

For this program, every started hour is charged in full. I used the following formula to calculate the billable hours:

`billableHours = (duration + 59) / 60`

For a parking duration of 130 minutes, the result is three billable hours. If the hourly rate is Rs 30, the total parking fee is Rs 90.

## Type Conversion and Integer Division
Integer division can produce an incorrect result if the remainder is ignored. For example, `130 / 60` gives `2` instead of three billable hours.

To avoid this problem, I used `(duration + 59) / 60`. This performs ceiling division for non-negative durations using integer arithmetic.

## Algorithm
1. Store the vehicle registration number and vehicle type.
2. Store the floor number and slot number.
3. Record the entry and exit times in minutes.
4. Calculate the parking duration.
5. Calculate the number of billable hours.
6. Calculate the parking fee using the hourly rate.
7. Store the registration number in the selected parking slot.
8. Display the vehicle details, duration and parking fee.

## How to Run
1. Open an online Java compiler.
2. Copy the code into the editor.
3. Run the program.
4. Check the output and take a screenshot.
5. Save the screenshot as `output.png` in the `Task02` folder.

## Alternative Approach
A one-dimensional array can represent all parking slots if every floor has the same number of slots. The index can be calculated using the floor number and the number of slots per floor.

However, this approach is less suitable when different floors have different capacities because the index calculation becomes more complicated.

An `ArrayList<String[]>` can also store one array for each floor. This allows the collection of floors to grow dynamically, while each floor can have a different number of slots.

For a jagged array containing a total of S slots, accessing a slot using its floor and slot indices takes O(1) time. The array requires O(S) space for its slot references.

## Result
I implemented a Java program using suitable data types and a jagged array to represent parking slots. The program calculates the parking duration and fee using integer ceiling division and displays the results correctly.

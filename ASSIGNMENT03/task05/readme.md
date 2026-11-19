# Task 5 – Race Conditions in a Cinema Seat-Booking System

## Objective
To prevent duplicate seat bookings using Java synchronization.

## Concepts Used
- Race condition
- Critical section
- Synchronization
- Synchronized methods
- Thread safety

## Problem Description
A cinema application allows multiple users to book seats simultaneously. Duplicate bookings must be prevented.

## Implementation
The program creates 10 seats and 100 user threads. Each user attempts to book a seat. A synchronized method ensures that only one thread can perform the booking operation at a time.

## Expected Output
```text
Seats sold: 10
```

## Result
The program successfully prevents duplicate bookings. Only 10 seats are booked.

## Conclusion
Synchronization prevents race conditions and maintains data consistency in multithreaded applications.

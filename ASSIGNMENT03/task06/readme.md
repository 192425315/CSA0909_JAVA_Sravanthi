# Task 6 – Deadlock and Inter-Thread Communication

## Objective
To implement deadlock prevention and producer-consumer communication using Java multithreading.

## Concepts Used
- Deadlock
- Synchronization
- wait()
- notifyAll()
- Producer-consumer problem
- Lock ordering
- Inter-thread communication

## Problem Description
The program simulates hostel wallet transfers and a mess token counter. One producer prepares tokens, while two consumers collect them from a bounded buffer.

## Implementation

**Part A – Wallet Transfer**

Two wallet accounts transfer money between each other. Consistent lock ordering is used to prevent deadlock.

**Part B – Producer-Consumer**

A buffer of size 5 is created. The producer adds 20 tokens, and two consumers collect 10 tokens each.

The program uses:
- wait() when the buffer is full or empty.
- notifyAll() to wake waiting threads.
- synchronized methods to ensure thread safety.

## Expected Output
```text
Sum of tokens collected: 210 (expected 210)
Transfer completed: Rs.100
Transfer completed: Rs.50
Total wallet balance: 1500
```

The order of transfer messages may vary.

## Result
All 20 tokens are collected successfully. The final token sum is 210, and the total wallet balance remains 1500.

## Conclusion
Inter-thread communication coordinates multiple threads. Synchronization and consistent lock ordering help prevent race conditions and deadlocks.

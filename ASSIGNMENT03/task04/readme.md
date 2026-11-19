# Task 4 – Thread Creation and Thread Life Cycle

## Objective
To implement parallel answer-script scanning using Java multithreading.

## Concepts Used
- Thread class
- Runnable interface
- Thread life cycle
- start()
- sleep()
- join()
- isAlive()

## Problem Description
An examination cell uses four scanner stations to scan answer scripts simultaneously.

## Implementation
The program creates four scanner stations using two approaches:
1. Extending the Thread class.
2. Implementing the Runnable interface.

The start() method starts threads, sleep() pauses execution, join() waits for completion and isAlive() checks thread status.

## Expected Output
```text
All stations completed.
Station 1 alive: false
Total scripts scanned: 12
```

## Result
All four scanner stations execute concurrently and successfully scan three scripts each.

## Conclusion
Multithreading allows multiple tasks to execute concurrently. Thread and Runnable provide different approaches to creating threads in Java.

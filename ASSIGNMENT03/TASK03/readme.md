# Task 3 – Resource Safety, Debugging and Robust Code

## Objective
To develop a daily sales report generator using Java resource management.

## Concepts Used
- try, catch and finally
- Manual close()
- Try-with-resources
- File handling
- Debugging
- Suppressed exceptions

## Problem Description
A retail company generates daily sales reports by reading multiple store files and writing a summary file.

## Implementation
The program creates two sample store files and generates reports using two approaches.

1. Manual resource closing using finally.
2. Automatic resource closing using try-with-resources.

Both methods read sales information and write it into separate report files.

## Expected Output
```text
Manual report generated successfully.
Automatic report generated successfully.
Both reports generated successfully.
```

## Result
Both approaches successfully generate sales reports containing the store information.

## Conclusion
Try-with-resources automatically closes opened resources and reduces resource leakage. It is simpler and safer than manual resource management.


# Task 7 – Concurrent Fault-Tolerant Result Processor

## Objective
To design and implement a concurrent system for processing 10,000 student examination result files.

## Concepts Used
- ExecutorService
- Callable
- Future
- ConcurrentHashMap
- Fixed Thread Pool
- Exception Handling
- Performance Measurement

## Problem Description
The examination department needs to process thousands of student result files. Missing or corrupt files should not stop the complete processing operation.

## Implementation
The program creates sample student result files and processes them using ExecutorService.

Callable performs individual file processing, Future collects the results, and ConcurrentHashMap stores the shared results safely.

Missing and corrupt files are handled separately without stopping other tasks.

## Testing
The program is tested using:
- Valid student files
- Corrupt student files
- Missing student files

Different thread pool sizes are tested by changing POOL_SIZE to 1, 2, 4 and 8.

## Expected Output
```text
Result Processing Completed.
Total Files: 10000
Successful: 9990
Failed: 10
Total Valid Scores: 799200
Thread Pool Size: 4
```

Execution time depends on the system.

## Result
The system processes student files concurrently and generates a summary of successful and failed operations.

## Conclusion
ExecutorService provides efficient thread management. Callable and Future support concurrent execution and result collection. Exception handling ensures that one failed file does not stop the complete processing operation.

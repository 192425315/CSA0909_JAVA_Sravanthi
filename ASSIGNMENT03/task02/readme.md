# Task 2 – User-Defined Exceptions and Propagation

## Objective
To implement custom exceptions and exception propagation in an online examination portal.

## Concepts Used
- User-defined exceptions
- Checked exceptions
- throw and throws
- Exception propagation
- Exception chaining
- getCause()

## Problem Description
An online examination portal must handle storage failures while saving student answer files.

## Implementation
The program defines ExamException and SubmissionStorageException. The Storage class checks the file size and handles IOException by wrapping it inside a custom exception. The original cause is preserved using exception chaining.

## Test Cases
1. Valid file size – File saved successfully.
2. File exceeding 500 KB – Storage exception generated.

## Expected Output
```text
Saved answer01.pdf
Error: Could not store answer02.pdf
Cause: Disk quota exceeded
```

## Conclusion
Custom exceptions provide meaningful error messages. Exception chaining preserves the original cause and improves debugging.

# Task 1 – Handling Failure in a Bank ATM Simulation

## Objective
To develop a simple ATM simulation using Java exception handling.

## Concepts Used
- Exception hierarchy
- Checked and unchecked exceptions
- try, catch and finally
- Multi-catch and exception handling
- throw and throws
- User-defined exceptions

## Problem Description
The ATM system accepts an account number and withdrawal amount. It checks the account balance and processes the transaction. It handles invalid inputs, negative amounts, insufficient balance and file errors.

## Implementation
The program uses a custom exception named `InsufficientFundsException` to handle insufficient balance. NumberFormatException handles invalid numeric input, IllegalArgumentException handles negative amounts, and IOException handles file-related errors.

The finally block executes after every withdrawal attempt.

## Test Cases

| Test Case | Input | Expected Result |
|---|---|---|
| 1 | 1000 | Successful withdrawal |
| 2 | abc | Invalid numeric input |
| 3 | -500 | Invalid amount |
| 4 | 9000 | Insufficient balance |
| 5 | 200 | Successful withdrawal |

## Output
```text
Account: A101
Cash withdrawn: Rs.1000.0
Remaining balance: Rs.4000.0
ATM transaction completed.
```

Other test cases display their respective error messages.

## Conclusion
The ATM simulation successfully handles different transaction failures using Java exception handling. It improves program reliability and prevents unexpected termination.

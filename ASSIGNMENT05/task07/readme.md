
# TASK 7 – ENGINEER AN ADVERSARIAL INPUT

## Student Details
- **Student Name:** D. Sravanthi
- **Register Number:** 192425315
- **Department:** Artificial Intelligence and Machine Learning
- **Course:** CSA09 – Programming in Java
- **Assignment:** 5 – CO5

## Aim
To demonstrate SQL injection attacks, secure login validation and damaged bus-pass file handling using Java.

## Concepts Used
- SQL Injection
- Statement
- PreparedStatement Concept
- HashMap
- File Handling
- BufferedReader
- BufferedWriter
- Exception Handling
- Input Validation

## Program Description

The program performs the following operations:

1. Creates sample user login records.
2. Tests an unsafe login with a crafted SQL injection input.
3. Tests secure login using parameterized-input simulation.
4. Checks a valid login.
5. Creates a damaged bus-pass file.
6. Detects records with incorrect fields.
7. Detects non-numeric amounts.
8. Skips empty lines.
9. Counts valid and damaged records.

## Test Cases

| Test | Input | Expected Result |
|---|---|---|
| SQL Injection | Crafted login ID | Unsafe simulation accepts |
| Secure Login | Same crafted ID | Rejected |
| Valid Login | Correct credentials | Accepted |
| Damaged Record | Wrong field count | Skipped |
| Invalid Amount | ABC | Rejected |
| Empty Line | Blank record | Skipped |

## How to Execute

1. Open Programiz Java Online Compiler.
2. Copy the Main.java source code.
3. Paste it into the editor.
4. Click Run.
5. Verify the login and damaged-file test results.

## Security Explanation

Unsafe SQL concatenation allows attackers to modify query conditions through specially crafted input.

PreparedStatement uses parameter placeholders and treats input as data rather than SQL commands. This prevents SQL injection through parameter values.

## Result
Successfully demonstrated unsafe and safe login behaviour and identified damaged bus-pass records using Java.

## Conclusion
Input validation and parameterized database queries improve application security and protect stored information from malicious inputs.

**Execution note:** This Programiz version simulates the login behaviours. The final assignment's actual JDBC demonstration must also be performed using a local test database and PreparedStatement.

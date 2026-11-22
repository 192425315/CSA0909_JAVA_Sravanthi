# TASK 5 – DESIGN FOR CORRECTNESS AND SAFETY

## Student Details
- **Student Name:** D. Sravanthi
- **Register Number:** 192425315
- **Department:** Artificial Intelligence and Machine Learning
- **Course:** CSA09 – Programming in Java
- **Assignment:** 5 – CO5

## Aim
To demonstrate JDBC transaction management and SQL injection prevention using Java.

## Concepts Used
- JDBC
- Connection
- Statement
- PreparedStatement
- ResultSet
- Commit
- Rollback
- SQL Injection
- Exception Handling

## Program Description

The program performs two major operations.

### 1. Transaction Management
- Transfers ₹500 from one wallet to another.
- Uses `setAutoCommit(false)`.
- Uses `commit()` to save successful changes.
- Uses `rollback()` if an error occurs.

### 2. SQL Injection Prevention
- Demonstrates an unsafe SQL query using Statement.
- Demonstrates a secure query using PreparedStatement.
- Compares the results of both login methods.

## Database Setup
1. Open MySQL Workbench.
2. Execute `database.sql`.
3. Update the database username and password in `Main.java`.
4. Add MySQL Connector/J to the Java project.

## How to Compile
```bash
javac -cp ".;mysql-connector-j.jar" Main.java
```

## How to Run
```bash
java -cp ".;mysql-connector-j.jar" Main
```

On Linux or macOS, use `:` instead of `;` in the classpath.

## Expected Behaviour
- Initially, wallet 1 has ₹5000.
- Wallet 2 has ₹3000.
- After successful transfer, wallet 1 has ₹4500.
- Wallet 2 has ₹3500.
- Unsafe SQL may accept a crafted login input.
- PreparedStatement rejects the same invalid login.

## Result
Successfully demonstrated JDBC transaction management using commit and rollback and SQL injection prevention using PreparedStatement.

## Conclusion
Transactions maintain data correctness by ensuring that related database operations succeed or fail together. PreparedStatement protects database queries by treating user input as parameter values rather than SQL commands.

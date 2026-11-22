# Task 08 – B3: Statement vs PreparedStatement vs Batch

**Student Name:** D. Sravanthi  
**Register Number:** 192425315  
**Department:** Artificial Intelligence and Machine Learning  
**Subject:** CSA09 – Programming in Java

## Aim
To compare Statement, PreparedStatement and batch insertion approaches for storing bus-pass records and understand the difference between auto-commit and transaction-based insertion.

## Software Requirements
- Java
- Programiz Online Java Compiler
- MySQL and JDBC driver for the actual database experiment

## Description
The program generates bus-pass records and simulates three insertion methods:

1. **Statement:** Creates a separate SQL string for each record.
2. **PreparedStatement:** Uses a parameterized SQL statement repeatedly.
3. **Batch Processing:** Groups records into batches of 1,000 before insertion.

The program tests 1,000, 10,000 and 100,000 records.

## Transaction Test
The program deliberately generates a failure while inserting the sixth record.

- With auto-commit, the first five inserted records remain.
- With a transaction, rollback removes all inserted records.

## Expected Behaviour
- All three methods insert 10 records in the small-data test.
- Performance timings are displayed for three data sizes.
- Auto-commit retains five records after the forced failure.
- Transaction rollback leaves zero records.

## Complexity Analysis
- Individual insertion: O(n) insertion operations.
- PreparedStatement: O(n) insertion operations, with reduced repeated SQL parsing in a real database.
- Batch insertion: O(n) records grouped into batches of size b.
- Batch memory requirement: O(b), excluding the database and generated input list.

## Conclusion
Batch insertion is generally suitable for large-scale imports because it reduces the number of database communication round trips. PreparedStatement is suitable for safe insertion of individual records. Transactions help maintain data consistency by allowing all changes to be committed or rolled back together.

**Note:** This Programiz version simulates the insertion approaches. Actual Statement, PreparedStatement, ResultSetMetaData, batch execution and transaction timings must be tested using MySQL with a JDBC driver before submitting the final JDBC evidence.


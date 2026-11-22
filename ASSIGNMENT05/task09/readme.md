# Task 09 – B4: Final Engineering Decision

**Student Name:** D. Sravanthi  
**Register Number:** 192425315  
**Department:** Artificial Intelligence and Machine Learning  
**Subject:** CSA09 – Programming in Java

## Aim
To migrate bus-pass records from a text file into a database using batches and transactions, identify invalid records, inspect database metadata and select a suitable storage method for multiple campuses.

## Software Requirements
- Java
- Programiz Online Java Compiler
- MySQL and JDBC driver for actual database migration

## B4.1 – Practical Migration Strategy
The program reads bus-pass records from a CSV file and validates each record. Valid records are grouped into batches of two in this demonstration. Invalid records are stored separately in a failure report.

The migration uses a simulated transaction to demonstrate committing the valid records together.

## B4.2 – Database Metadata
Database metadata provides information about:
- Database product name
- JDBC driver version
- Available database tables
- Table columns and their data types

This information helps developers check database compatibility when moving an application to another server.

## B4.3 – Production Decision

A relational database is the best choice for the university transport office.

**Reasons:**
1. All 12 campuses can access shared records.
2. Multiple counters can issue passes concurrently.
3. Records can be searched efficiently using indexed columns.
4. Transactions protect data consistency.
5. Access control and backups improve data safety.

Text files are suitable for small applications but are difficult to manage with multiple simultaneous users. Serialization is useful for saving Java objects but is not ideal for shared multi-campus access.

## Alternative Concept and Complexity Analysis
If each campus used a separate serialized file, searching across all campuses would require checking each file. For n total records, a full search takes O(n) time.

A shared database with an index can provide faster searches and better concurrency management. Separate campus files also create synchronization and duplicate-record problems.

## Conclusion
A centralized relational database with JDBC, batch processing, transactions and indexes is the most suitable solution for the university's 12-campus transport system.

**Note:** This Programiz implementation simulates migration and metadata. Actual JDBC migration and DatabaseMetaData output must be demonstrated using MySQL and a configured JDBC driver.


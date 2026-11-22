
# TASK 6 – CAMPUS EVENT REGISTRATION APPLICATION

**Course:** Programming in Java (CSA09)  
**Assignment:** 4 – CO4  
**Student Name:** D. Sravanthi  
**Register Number:** 192425315  
**Department:** AI & ML

## 1. Objective
To develop a campus event registration system
that manages student registrations, prevents
duplicates, filters records and exports data.

## 2. Files Included
- Main.java – Registration management program.
- database.sql – MySQL database script.
- README.md – Project documentation.
- screenshots – Program execution evidence.

## 3. Technologies Used
- Java
- MySQL
- JDBC concepts
- Java Collections
- File Handling

## 4. Features
1. Student registration.
2. Input validation.
3. Duplicate registration prevention.
4. View all registrations.
5. Filter registrations by event.
6. Export registrations to CSV.
7. Error message handling.

## 5. Concepts Used
- ArrayList
- Classes and Objects
- Encapsulation
- Methods
- Conditional Statements
- Exception Handling
- File Handling
- SQL Constraints

## 6. Execution

Platform: Programiz Online Java Compiler

Steps:
1. Open Programiz Java Compiler.
2. Paste Main.java.
3. Click Run.
4. Select the required menu option.
5. Enter student details.
6. Check the output.

## 7. Sample Input

1
192425315
Sravanthi
AIML
sravanthi@gmail.com
Tech Fest

## 8. Expected Output

Registration Successful!

Duplicate registration not allowed!

## 9. Test Cases

| Test Case | Expected Result |
|---|---|
| Valid registration | Successful |
| Empty student ID | Error |
| Empty student name | Error |
| Invalid email | Error |
| Duplicate registration | Rejected |
| View registrations | Records displayed |
| Filter by event | Matching records displayed |
| Export CSV | CSV file generated |

## 10. Database
The database.sql file contains:
- Students table
- Events table
- Registrations table
- Foreign key constraints
- Unique registration constraint
- Filtering query
- Pagination query

## 11. Result
The campus event registration management
program was tested using Programiz.
Registration, validation, duplicate checking,
filtering and CSV export features were demonstrated.

## 12. Conclusion
This task helped me understand Java application
design, registration management, duplicate
prevention, database concepts and file handling.

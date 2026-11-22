# TASK 3 – DATA ACCESS OBJECT (DAO) DESIGN PATTERN

## Student Details
- **Student Name:** D. Sravanthi
- **Register Number:** 192425315
- **Department:** Artificial Intelligence and Machine Learning
- **Course:** CSA09 – Programming in Java
- **Assignment:** 5 – CO5

## Aim
To implement the Data Access Object (DAO) design pattern using Java to perform student record insertion, searching, updating and deletion.

## Concepts Used
- DAO Design Pattern
- Generic Interface
- Java Collections
- ArrayList
- LinkedHashMap
- Optional Class
- CRUD Operations

## Program Description
The program contains three main components:

1. **Student Class:** Stores student ID, name and email.
2. **Dao Interface:** Defines generic methods for insert, findById, findAll, update and delete.
3. **StudentDao Class:** Implements the DAO interface and manages student records.

## Operations Performed
1. Insert student records.
2. Search for a student using ID.
3. Display all student records.
4. Update student information.
5. Delete a student record.

## How to Execute

1. Open Programiz Java Online Compiler.
2. Copy the `Main.java` source code.
3. Paste it into the editor.
4. Click the Run button.
5. Observe the output.

## Expected Output
```text
===== STUDENT DATABASE SYSTEM =====

1. INSERT STUDENTS
Student inserted successfully!
Student inserted successfully!
Student inserted successfully!

2. FIND STUDENT
ID: 101, Name: Sravanthi, Email: sravanthi@gmail.com

3. DISPLAY ALL STUDENTS
ID: 101, Name: Sravanthi, Email: sravanthi@gmail.com
ID: 102, Name: Arun, Email: arun@gmail.com
ID: 103, Name: Divya, Email: divya@gmail.com

4. UPDATE STUDENT
Student updated successfully!
ID: 102, Name: Arun Kumar, Email: arunkumar@gmail.com

5. DELETE STUDENT
Student deleted successfully!

6. FINAL STUDENT LIST
ID: 101, Name: Sravanthi, Email: sravanthi@gmail.com
ID: 102, Name: Arun Kumar, Email: arunkumar@gmail.com

Program executed successfully!
```

## Result
Successfully implemented the DAO design pattern using a generic interface and StudentDao class. All CRUD operations were executed successfully.

## Conclusion
The DAO design pattern separates data-access logic from application logic. It improves code reusability, maintainability and organization.

**Note:** This demonstration uses an in-memory collection instead of JDBC. The assignment's JDBC architecture can be implemented by replacing the collection-based DAO with a database-backed DAO.

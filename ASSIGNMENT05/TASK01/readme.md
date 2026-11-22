# TASK 1 – BUS PASS MANAGEMENT SYSTEM

## Student Details
- **Student Name:** D. Sravanthi
- **Register Number:** 192425315
- **Department:** Artificial Intelligence and Machine Learning
- **Course:** CSA09 – Programming in Java
- **Assignment:** Assignment 5 – CO5

## Aim
To develop a Java application to store, search and display university bus-pass records using Java file handling concepts.

## Technologies Used
- Java
- File Handling
- FileWriter
- FileReader
- BufferedWriter
- BufferedReader
- Exception Handling

## Program Description
The Bus Pass Management System performs the following operations:

1. Add new bus-pass records.
2. Search for a bus pass using Pass ID.
3. Display bus passes according to route number.
4. Skip damaged records while reading the file.
5. Display a message when a pass is not found.

## File Structure
```text
Task01_A1_File_Format/
│
├── BusPass.java
└── README.md
```

The program creates a temporary text file automatically during execution.

## How to Execute

**Step 1:** Open Programiz Java Online Compiler.

**Step 2:** Copy the `BusPass.java` source code.

**Step 3:** Paste the code into the Java editor.

**Step 4:** Click the Run button.

## Expected Output
```text
===== BUS PASS MANAGEMENT SYSTEM =====
Bus pass added successfully!
Bus pass added successfully!
Bus pass added successfully!

===== SEARCH BUS PASS =====
Pass Found:
P1001,192425315,Sravanthi,12,Avadi,2027-03-31,4500.0

===== SEARCH INVALID PASS =====
Pass Not Found!

===== ROUTE-WISE BUS PASSES =====
Bus Passes for Route 12:
P1001,192425315,Sravanthi,12,Avadi,2027-03-31,4500.0
P1003,192425317,Divya,12,Ambattur,2027-05-31,4200.0

Program executed successfully!
```

## Result
Successfully implemented the Bus Pass Management System using Java file handling. The program performs insertion, searching and route-wise listing of bus-pass records.

## Conclusion
Java file handling helps store and retrieve information efficiently. BufferedReader and BufferedWriter make reading and writing operations easier.

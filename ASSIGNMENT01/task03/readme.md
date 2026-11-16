# Task 3 – Engineer the hashCode()–equals() Contract

## Student Details
- **Name:** Sravanthi
- **Register Number:** [Enter Your Register Number]
- **Course:** CSA09 – Programming in Java
- **Assignment:** Assignment 2
- **Programming Language:** Java
- **Compiler:** Programiz Online Java Compiler

## 1. Aim
To understand how `equals()` and `hashCode()` work together in a HashSet to identify unique student records.

## 2. Problem Statement
A college fest records student attendance at different gates. The same student may be scanned multiple times. A HashSet is used to store only unique students.

## 3. Concepts Used
- HashSet
- equals() method
- hashCode() method
- Object comparison
- Java Collections Framework

## 4. Test Cases

**Test 1 – Same Object**

The same Student object is added twice.

Expected size: 2, because only two unique objects are added.

**Test 2 – Duplicate Registration Number**

Different Student objects with the same registration number are added.

Expected size: 2 after implementing hashCode() correctly.

**Test 3 – Changing Registration Number**

The registration number is changed after inserting the object into the HashSet.

Expected result: `contains()` returns false because changing a field used in hashCode() can make the object difficult to locate.

## 5. Expected Output

```text
Test 1 - Same object: 2
Test 2 - Repeated scans: 2
Test 3 - Found after change: false
Unique students: 2
```

## 6. Explanation

HashSet uses hashCode() to identify the bucket and equals() to compare objects.

If two objects are equal, they must have the same hash code.

Therefore, both equals() and hashCode() should be overridden consistently.

The registration number is used for comparison and is declared final to prevent modification.

## 7. Time and Space Complexity

| Operation | Average Time |
|---|---|
| Insert | O(1) |
| Search | O(1) |
| Delete | O(1) |

- Space Complexity: O(n)
- TreeSet search and insertion: O(log n)

## 8. Alternative Method

A TreeSet with a Comparator based on registration number or a HashMap using registration number as the key can also prevent duplicate student records.

## 9. Result

The program demonstrates the importance of the hashCode()–equals() contract. After implementing hashCode() correctly, duplicate student records are avoided in the HashSet.

## 10. Screenshots

Add your actual execution screenshots here:

1. Program source code.
2. Test case execution.
3. Final output in Programiz.


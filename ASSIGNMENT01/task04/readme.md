# Task 4 – Choosing a Map for a Pharmacy Inventory

## Student Details
- **Name:** Sravanthi
- **Register Number:** [Enter Your Register Number]
- **Course:** CSA09 – Programming in Java
- **Assignment:** Assignment 2
- **Language:** Java
- **Compiler:** Programiz Online Java Compiler

## 1. Aim
To compare HashMap, TreeMap and LinkedHashMap for storing and retrieving hospital pharmacy inventory details.

## 2. Problem Statement
A hospital pharmacy stores medicines using unique medicine codes. It needs fast stock lookups and two reports:
- Medicines in code order.
- Medicines in insertion order.

## 3. Concepts Used
- HashMap
- TreeMap
- LinkedHashMap
- Map interface
- System.nanoTime()
- Collections Framework

## 4. Experimental Setup

The program tests three inventory sizes:

| Test | Number of Medicines |
|---|---:|
| 1 | 1,000 |
| 2 | 5,000 |
| 3 | 10,000 |

For each map, the program performs 100,000 lookups and calculates the average time over five runs. It also measures report-generation time.

## 5. Expected Behaviour

- **HashMap:** Provides fast average lookup but does not guarantee ordering.
- **TreeMap:** Maintains keys in sorted order.
- **LinkedHashMap:** Maintains insertion order.

## 6. Complexity Analysis

| Operation | HashMap | TreeMap | LinkedHashMap |
|---|---|---|---|
| put() | O(1) average | O(log n) | O(1) average |
| get() | O(1) average | O(log n) | O(1) average |
| Iteration | O(n) | O(n) | O(n) |
| Key order | No guarantee | Sorted | Insertion order |

Space complexity: O(n) for all three maps.

## 7. Alternative Concept – Sorted Array

A sorted array can use Arrays.binarySearch() to find a medicine code.

- Lookup: O(log n)
- Insertion: O(n), because elements may need shifting.
- Code-order report: O(n)
- Space: O(n)

## 8. Scalability and Design Decision

For frequent stock lookups, HashMap or LinkedHashMap provides average O(1) lookup.

TreeMap is suitable when sorted medicine-code reports are frequently required.

A target response time should be defined by the pharmacy application. The measured lookup results should be compared with that target before deployment.

Hashtable is generally not preferred in new Java applications because it is a legacy synchronized collection. Other modern options can provide more suitable concurrency control when required.

## 9. Experimental Results

Enter your actual output measurements here.

| Inventory Size | HashMap Lookup (ns) | TreeMap Lookup (ns) | LinkedHashMap Lookup (ns) |
|---|---|---|---|
| 1,000 | | | |
| 5,000 | | | |
| 10,000 | | | |

Also record the code-order and insertion-order report timings from your program output.

## 10. Result

The program compares three Map implementations using different inventory sizes, measures lookup and report-generation times, and demonstrates their ordering behaviour.

## 11. Screenshots
1. Java source code.
2. Output for 1,000 medicines.
3. Output for 5,000 medicines.
4. Output for 10,000 medicines.
5. Code-order and insertion-order reports.

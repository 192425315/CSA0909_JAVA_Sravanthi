# Task 6 – Priority, Order and Fairness in a Queue

## Student Details
- **Name:** Sravanthi
- **Register Number:** [Enter Your Register Number]
- **Course:** CSA09 – Programming in Java
- **Assignment:** Assignment 2
- **Language:** Java
- **Compiler:** Programiz Online Java Compiler

## 1. Aim
To develop a hospital emergency triage system using PriorityQueue and Comparator.

## 2. Problem Statement
Patients are assigned severity levels from 1 to 5. The most critical patient must be treated first. Patients with the same severity must be treated in arrival order.

## 3. Concepts Used
- PriorityQueue
- Comparator
- Queue
- System.nanoTime()
- FIFO ordering
- Collections Framework

## 4. Priority Rules
1. Severity 1 has the highest priority.
2. Severity 5 has the lowest priority.
3. Equal-severity patients are treated according to arrival order.

## 5. Expected Assignment Output

```text
Meena(S1) Kumar(S1) Lata(S2) Ravi(S3) John(S3) Asha(S5)
```

## 6. Eight-Patient Test

The program also tests eight patients to verify priority and arrival-order handling.

Expected treatment order:

```text
Divya(S1) Ravi(S1) Meena(S2) Vijay(S2) Arun(S3) Sita(S3) Priya(S4) Kiran(S5)
```

## 7. Performance Experiment

The program measures admission and removal time for three queue sizes:

| Test | Patients |
|---|---:|
| 1 | 1,000 |
| 2 | 5,000 |
| 3 | 10,000 |

Each test is repeated five times and averaged.

Enter your actual measured timings here:

| Patients | Average Time (ns) |
|---|---|
| 1,000 | |
| 5,000 | |
| 10,000 | |

## 8. Complexity Analysis

| Operation | PriorityQueue |
|---|---|
| Add patient | O(log n) |
| Remove patient | O(log n) |
| View next patient | O(1) |
| Space | O(n) |

## 9. Alternative Concepts

**Sorted LinkedList**
- Admission: O(n)
- Removal: O(1)
- Space: O(n)

**Five FIFO Queues**
- Admission: O(1)
- Removal: O(1) with five severity queues
- Space: O(n)

## 10. Fairness Analysis

A severity-5 patient may wait indefinitely when more critical patients continuously arrive.

Priority aging can solve this problem by increasing the priority of patients who have waited beyond a fixed time.

When a patient's effective priority changes, the priority queue must be updated or rebuilt to maintain correct ordering.

## 11. Deployment Decision

PriorityQueue is suitable for managing a large number of patients because admission and removal take O(log n) time.

It can support a hospital receiving 5,000 patients daily as a basic data structure, with additional reliability, concurrency and fairness features required for real deployment.

## 12. Result

The program successfully demonstrates emergency patient prioritization using severity and arrival order. It also compares performance for different queue sizes.

## 13. Screenshots
1. Java source code.
2. Assignment test output.
3. Eight-patient test output.
4. Performance measurements.
5. Fairness analysis output.

# Task 5 – Generic Sports Leaderboard

## Student Details
- **Name:** Sravanthi
- **Register Number:** [Enter Your Register Number]
- **Course:** CSA09 – Programming in Java
- **Assignment:** Assignment 2
- **Language:** Java
- **Compiler:** Programiz Online Java Compiler

## 1. Aim
To develop a reusable generic leaderboard using Java Generics and Comparator.

## 2. Problem Statement
A sports competition ranks teams using points, goal difference and team name.

The ranking rules are:
1. Higher points first.
2. Higher goal difference first.
3. Team name in alphabetical order.

## 3. Concepts Used
- Java Generics
- Comparator interface
- Comparator.comparingInt()
- thenComparing()
- reversed()
- ArrayList
- List<? extends T>
- List sorting

## 4. Implementation

A generic class named Leaderboard<T> stores items of any type.

The Comparator<? super T> allows a suitable comparator to be supplied for the item type.

The addAll() method uses List<? extends T> to accept a list containing T or its subclasses.

The ranking() method creates a new list and sorts it without modifying the original list.

## 5. Expected Output

```text
Sports Leaderboard:
[Eagles(9,6), Bisons(9,4), Panthers(9,4), Cobras(6,1)]
```

Bisons is ranked above Panthers because both have equal points and goal difference, but Bisons comes first alphabetically.

## 6. Comparator Correctness

The comparator checks all three ranking conditions in order.

It uses Integer comparison through comparingInt() instead of subtracting values. This avoids integer overflow problems.

A consistent comparator maintains a valid ordering and supports correct sorting.

## 7. Alternative Concept – Comparable

Team could implement Comparable<Team> and define its natural ordering using compareTo().

However, Comparator is more flexible because different ranking rules can be created without changing the Team class.

Sorting three times using stable sorting is another approach, but it requires three sorting operations.

A single comparator chain is easier to maintain and reuse.

## 8. Complexity Analysis

- Adding a team: O(1) average.
- Sorting n teams: O(n log n).
- Copying the list: O(n).
- Space complexity: O(n) for the copied ranking list.

## 9. Result

The generic leaderboard successfully ranks teams using points, goal difference and alphabetical order. The program also demonstrates the importance of using a safe comparator.

## 10. Screenshots
1. Java source code.
2. Sports leaderboard output.
3. Additional team ranking output.
4. Faulty and correct comparator test output.

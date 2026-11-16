# Task 1 – Building Text Under Load

## Objective
To compare String, StringBuilder and StringBuffer while generating railway e-tickets.

## Workloads
1. Short workload: 1 ticket with 6 fields.
2. Heavy workload: 10,000 tickets with 50 fields each.
3. Custom workload: 2,000 tickets with 20 fields each.

## Concepts Used
- String is immutable. Every concatenation creates a new String result.
- StringBuilder is mutable and is suitable for single-threaded string construction.
- StringBuffer is synchronized and supports thread-safe individual operations.

## Prediction
String concatenation using `+` is expected to take more time for large workloads because it repeatedly creates new strings.

StringBuilder is expected to perform better because it modifies the same buffer.

StringBuffer may be slightly slower than StringBuilder because its methods are synchronized.

## Execution

Requirements: Java JDK 17 or later.

Compile:

```bash
javac Main.java
```

Run:

```bash
java Main
```

## Experimental Results

The program performs five measured runs and displays the average execution time.

Enter your actual results after running the program:

| Workload | String | StringBuilder | StringBuffer |
|---|---|---|---|
| Short | ______ | ______ | ______ |
| Heavy | ______ | ______ | ______ |
| Custom | ______ | ______ | ______ |

Time unit: nanoseconds (ns).

The program also displays estimated intermediate objects and approximate heap memory before and after execution. Heap readings are approximate and can vary due to garbage collection.

## Complexity Analysis

Let n represent the number of fields in one ticket.

| Approach | Time Complexity | Reason |
|---|---|---|
| String (+) | O(n²) | Repeated copying of growing strings |
| StringBuilder | O(n) | Appends to a mutable buffer |
| StringBuffer | O(n) | Appends with synchronization overhead |

All three approaches require O(n) space for the final ticket. String concatenation creates additional intermediate strings, while builders use a mutable internal buffer.

## Multithreaded Design Decision

For each ticket, StringBuilder is recommended because each thread can build its own ticket independently.

For a shared booking log, StringBuffer is suitable because its individual append operations are synchronized. Appending a complete ticket in one operation helps prevent interleaving between threads.

## Alternative Concept

String.join() and StringJoiner can combine multiple fields with a delimiter. String.format() can format ticket details, but repeated formatting may add overhead.

Example:

```java
String ticket = String.join(" | ",
    "PNR=123456",
    "Passenger=Arun",
    "Coach=S1"
);
```

## Conclusion

StringBuilder is generally suitable for generating individual tickets because it avoids repeated immutable string concatenation. StringBuffer is useful when multiple threads share a log. The final decision should be supported by the actual benchmark results.

## Screenshots

Add your actual screenshots here after execution:

- Java source code
- Short workload output
- Heavy workload output
- Custom workload output
- Sample ticket and StringJoiner output

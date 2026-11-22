# TASK 6 – IMPLEMENT AND INSTRUMENT THE FILE STORE

## Student Details
- **Student Name:** D. Sravanthi
- **Register Number:** 192425315
- **Department:** Artificial Intelligence and Machine Learning
- **Course:** CSA09 – Programming in Java
- **Assignment:** 5 – CO5

## Aim
To implement a bus-pass file storage system and investigate the performance of buffered and unbuffered file reading.

## Concepts Used
- File Handling
- FileInputStream
- FileOutputStream
- BufferedInputStream
- BufferedReader
- BufferedWriter
- Exception Handling
- Performance Measurement
- System.nanoTime()

## Program Description

The program performs the following operations:

1. Adds bus-pass records to a file.
2. Searches for a bus pass using its ID.
3. Displays bus passes based on route number.
4. Skips damaged records.
5. Counts attendance file lines using unbuffered reading.
6. Counts attendance file lines using buffered reading.
7. Measures search performance as the number of records increases.

## Test Cases

| Test Case | Input | Expected Result |
|---|---|---|
| Normal search | P1003 | Pass found |
| Invalid search | P9999 | Returns null |
| Damaged record | P1004 | Skipped |
| Route search | Route 12 | Displays matching passes |

## How to Execute in Programiz

1. Open Programiz Java Online Compiler.
2. Paste the Main.java code.
3. Click Run.
4. Verify the bus-pass insertion, search and route-wise listing output.

## Performance Experiments

The program includes two benchmark methods.

### Experiment 1: Buffering Comparison

File sizes:
- 1 MB
- 10 MB
- 50 MB

Compare:
- Unbuffered reading time
- Buffered reading time
- Number of lines counted

### Experiment 2: Search Performance

Record counts:
- 1,000
- 10,000
- 50,000
- 100,000

Measure the time taken to find the last pass in each file.

## Complexity Analysis

| Operation | Time Complexity |
|---|---|
| Add pass | O(1) append |
| Find pass | O(n) |
| List by route | O(n) |
| Buffered reading | O(n) |

Buffered reading uses additional memory for the buffer, while unbuffered reading uses constant auxiliary memory.

## Result
Successfully implemented the bus-pass file store with insertion, searching and route-wise listing. Buffered and unbuffered file-reading and search-performance experiments are included.

## Conclusion
Buffered reading reduces repeated read operations and is generally more efficient for large files. Searching a file sequentially takes O(n) time as the number of records increases.

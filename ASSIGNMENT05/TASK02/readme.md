# TASK 2 – DON'T READ WHAT YOU DON'T NEED

## Student Details
- **Student Name:** D. Sravanthi
- **Register Number:** 192425315
- **Department:** Artificial Intelligence and Machine Learning
- **Course:** CSA09 – Programming in Java
- **Assignment:** 5 – CO5

## Aim
To compare unbuffered and buffered file reading techniques in Java by counting the number of lines in an attendance file.

## Concepts Used
- FileInputStream
- BufferedInputStream
- Byte Array
- File Handling
- Exception Handling
- System.nanoTime()

## Program Description
The program performs the following operations:

1. Creates a sample attendance file.
2. Reads the file using FileInputStream.
3. Counts the lines using unbuffered reading.
4. Reads the same file using BufferedInputStream.
5. Counts the lines using buffered reading.
6. Compares the number of read calls and execution time.

## How to Execute

1. Open Programiz Java Online Compiler.
2. Copy the `Main.java` source code.
3. Paste it into the editor.
4. Click the Run button.
5. Observe the comparison results.

## Expected Observation

- Both methods should count the same number of lines.
- Unbuffered reading makes approximately one read call per byte, plus the final EOF call.
- Buffered reading reads multiple bytes in one operation.
- Buffered reading generally reduces the number of read calls and improves performance.

## Complexity Analysis

| Method | Time Complexity | Space Complexity |
|---|---|---|
| Unbuffered Reading | O(n) | O(1) |
| Buffered Reading | O(n) | O(b) |

Here, n represents the file size and b represents the buffer size.

## Result
Successfully implemented and compared unbuffered and buffered file-reading techniques in Java.

## Conclusion
BufferedInputStream improves file-reading efficiency by reading data in blocks instead of reading one byte at a time. It is useful when processing large files such as attendance logs.

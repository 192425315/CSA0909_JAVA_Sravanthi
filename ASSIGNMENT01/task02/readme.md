# Task 2 – ArrayList, LinkedList and ArrayDeque

## Objective
To compare ArrayList and LinkedList for playlist operations and evaluate ArrayDeque for an Up Next queue.

## Test Sizes
- 1,000 songs
- 5,000 songs
- 10,000 songs

## Operations Tested
1. Adding songs at the end.
2. Inserting songs at the front.
3. Inserting songs in the middle.
4. Removing songs from the front.
5. Removing songs from the middle.
6. Accessing the k-th song.
7. Iterating through the playlist.

The program performs five runs and calculates the average execution time.

## Prediction
ArrayList is expected to perform better for accessing the k-th song because it supports direct indexing.

LinkedList is expected to perform better for frequent insertion and removal at the beginning when using its list structure appropriately. However, finding a middle position requires traversal.

ArrayDeque is expected to be suitable for an Up Next queue because it supports insertion and removal at both ends.

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

No manual input is required.

## Experimental Results

Enter your actual results from the program:

| Playlist Size | ArrayList | LinkedList | ArrayDeque |
|---|---|---|---|
| 1,000 | ______ | ______ | ______ |
| 5,000 | ______ | ______ | ______ |
| 10,000 | ______ | ______ | ______ |

The program prints separate timings for each list operation. Record those timings in your assignment report.

## Time Complexity

| Operation | ArrayList | LinkedList | ArrayDeque |
|---|---|---|---|
| Add at end | O(1) amortized | O(1) | O(1) amortized |
| Insert at front | O(n) | O(1) | O(1) |
| Insert in middle | O(n) | O(n) to locate position | Not supported by index |
| Remove at front | O(n) | O(1) | O(1) |
| Get k-th element | O(1) | O(n) | Not supported by index |
| Iterate | O(n) | O(n) | O(n) |

## Space Complexity

All three structures require O(n) space.

ArrayList stores elements in an internal array. LinkedList stores elements in separate linked nodes with additional reference fields. ArrayDeque uses a resizable circular array.

## Engineering Decision

ArrayList is a good choice for playlists that need frequent random access and iteration.

LinkedList can be useful when insertions and removals happen frequently at known positions, but finding those positions takes time.

For the Up Next queue, ArrayDeque is a suitable choice because songs can be added and removed efficiently from both ends.

## Plain Array Alternative

A normal array can store songs, but inserting or deleting elements requires shifting other elements. Therefore, these operations take O(n) time. Accessing an element by index takes O(1).

## Conclusion

ArrayList is suitable for normal playlists with frequent reading and random access. ArrayDeque is more suitable for an Up Next queue with frequent operations at both ends. The final choice should be supported by the measured results.

## Screenshots

Add your actual screenshots:

- Task02_Code.png
- Task02_ArrayList.png
- Task02_LinkedList.png
- Task02_ArrayDeque.png
- Task02_CompleteOutput.png

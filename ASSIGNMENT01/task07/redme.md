# Task 7 – Diagnose, Redesign and Validate

**Name:** Sravanthi  
**Register Number:** Enter your register number  
**Subject:** Programming in Java – CSA09  
**Task:** Q7 – Design Challenge

## 1. Objective
To identify a performance limitation in the pharmacy inventory report and redesign the solution.

## 2. Bottleneck Diagnosis
The original solution uses a HashMap for storing medicines. A new TreeMap is created whenever a code-order report is generated.

Repeatedly creating and sorting the map causes unnecessary work, especially when reports are requested frequently without inventory changes.

*Evidence:* Add the relevant Q4 measurements from your own execution.

## 3. Design Objective
Reduce repeated sorting and report-generation time while maintaining the correct medicine-code order.

## 4. Revised Design
The redesigned solution uses:
- HashMap for medicine storage and quick lookup.
- A cached sorted list for code-order reports.
- The cache is rebuilt when a medicine is added or updated.

### Pseudocode
1. Store medicines in HashMap.
2. Create a sorted report list.
3. When inventory changes, rebuild the sorted list.
4. When a report is requested, reuse the cached list.

## 5. Prediction
The redesigned solution should generate repeated reports faster because it avoids sorting the inventory for every report.

However, rebuilding the cache after an update requires additional processing and memory.

## 6. Experimental Validation
Tested with 1,000, 5,000 and 10,000 medicines. Each test generated 500 reports and was repeated five times.

Enter the actual average timings printed by your program:

| Medicines | Original average (ns) | Redesigned average (ns) |
|---:|---:|---:|
| 1,000 | Enter result | Enter result |
| 5,000 | Enter result | Enter result |
| 10,000 | Enter result | Enter result |

Both implementations should produce matching report checksums.

## 7. Complexity Analysis

| Operation | Original | Redesigned |
|---|---|---|
| Medicine lookup | O(1) average | O(1) average |
| Report preparation | O(n log n) | O(n log n) when rebuilt |
| Repeated report | O(n log n) | O(n) |
| Extra memory | O(n) during report creation | O(n) cached list |

## 8. Trade-offs
- The redesigned solution uses extra memory for the cached list.
- Inventory updates require rebuilding the cache.
- It is useful when reports are requested frequently and updates are less frequent.
- The original solution may be preferable when inventory changes very frequently.

## 9. Conclusion
The redesign reduces repeated sorting by reusing a previously sorted report. Actual timing results should be used to decide whether the redesign provides a practical improvement.

## 10. Screenshots
Attach:
1. Program execution output.
2. Results for 1,000 medicines.
3. Results for 5,000 medicines.
4. Results for 10,000 medicines.

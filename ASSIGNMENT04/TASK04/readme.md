
# TASK 4 – EVENT-HANDLING FORENSICS

**Course:** Programming in Java (CSA09)  
**Assignment:** 4 – CO4  
**Student Name:** D. Sravanthi  
**Register Number:** 192425315  
**Department:** AI & ML

## 1. Objective
To identify and correct mouse event handling
errors using Java MouseAdapter and repainting.

## 2. Files Included
- DrawPad.java – AWT drawing application.
- Main.java – Programiz console simulation.
- README.md – Project documentation.

## 3. Concepts Used
- MouseAdapter
- MouseEvent
- mouseClicked()
- @Override
- ArrayList
- Graphics
- paint()
- repaint()
- WindowAdapter

## 4. Features
1. Detects mouse clicks.
2. Stores clicked coordinates.
3. Updates click count.
4. Draws dots on the window.
5. Preserves dots during repainting.

## 5. Execution

### Programiz Testing
1. Open Programiz Java Compiler.
2. Paste Main.java.
3. Enter the number of clicks.
4. Enter X and Y coordinates.
5. Click Run and check the output.

### GUI Execution
DrawPad.java contains the actual AWT
implementation and requires a desktop
Java environment.

## 6. Sample Input

3
50
60
100
120
150
180

## 7. Expected Output

Clicks: 1
Clicks: 2
Clicks: 3

Dot at (50, 60)
Dot at (100, 120)
Dot at (150, 180)

Total Clicks: 3

## 8. Result
The mouse click handling logic was corrected
using mouseClicked() and @Override.
The clicked points are stored in an ArrayList
and redrawn using paint().

## 9. Conclusion
This task helped me understand mouse events,
event listeners, repainting and graphics
handling in Java.


import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> points = new ArrayList<>();

        int count = 0;

        System.out.println("DRAW PAD SIMULATION");
        System.out.print("Enter number of mouse clicks: ");

        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {

            System.out.print("Enter X coordinate: ");
            int x = sc.nextInt();

            System.out.print("Enter Y coordinate: ");
            int y = sc.nextInt();

            points.add("(" + x + ", " + y + ")");

            count++;

            System.out.println("Clicks: " + count);
        }

        System.out.println("\nDrawing Points:");

        for (String p : points) {
            System.out.println("Dot at " + p);
        }

        System.out.println("\nTotal Clicks: " + count);
        System.out.println("All points stored successfully!");

        sc.close();
    }
}

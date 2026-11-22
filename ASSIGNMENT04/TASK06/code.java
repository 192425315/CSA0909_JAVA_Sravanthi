
import java.util.*;
import java.io.*;

class Registration {
    String id, name, department, email, event;

    Registration(String id, String name, String department,
                 String email, String event) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.event = event;
    }

    void display() {
        System.out.println(id + " | " + name + " | "
                + department + " | " + email + " | " + event);
    }
}

class RegistrationService {

    ArrayList<Registration> list = new ArrayList<>();

    void register(String id, String name, String dept,
                  String email, String event) {

        if (id.isEmpty() || name.isEmpty() ||
            dept.isEmpty() || email.isEmpty() || event.isEmpty()) {
            System.out.println("Error: All fields are required!");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            System.out.println("Error: Invalid email!");
            return;
        }

        for (Registration r : list) {
            if (r.id.equalsIgnoreCase(id) &&
                r.event.equalsIgnoreCase(event)) {
                System.out.println("Duplicate registration not allowed!");
                return;
            }
        }

        list.add(new Registration(id, name, dept, email, event));
        System.out.println("Registration Successful!");
    }

    void viewAll() {
        if (list.isEmpty()) {
            System.out.println("No registrations found!");
            return;
        }

        System.out.println("\nALL REGISTRATIONS");
        System.out.println("ID | Name | Department | Email | Event");

        for (Registration r : list) {
            r.display();
        }
    }

    void filter(String event) {
        boolean found = false;

        System.out.println("\nFILTERED REGISTRATIONS");

        for (Registration r : list) {
            if (r.event.equalsIgnoreCase(event)) {
                r.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No registrations found!");
        }
    }

    void exportCSV() {
        try (PrintWriter writer =
             new PrintWriter(new FileWriter("registrations.csv"))) {

            writer.println("StudentID,Name,Department,Email,Event");

            for (Registration r : list) {
                writer.println(
                    r.id + "," + r.name + "," + r.department
                    + "," + r.email + "," + r.event
                );
            }

            System.out.println("CSV exported successfully!");

        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegistrationService service = new RegistrationService();

        while (true) {

            System.out.println("\n================================");
            System.out.println(" CAMPUS EVENT REGISTRATION");
            System.out.println("================================");
            System.out.println("1. Register Student");
            System.out.println("2. View All Registrations");
            System.out.println("3. Filter by Event");
            System.out.println("4. Export Registrations");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice!");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Student ID: ");
                    String id = sc.nextLine().trim();

                    System.out.print("Student Name: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Department: ");
                    String dept = sc.nextLine().trim();

                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();

                    System.out.print("Event Name: ");
                    String event = sc.nextLine().trim();

                    service.register(id, name, dept, email, event);
                    break;

                case 2:
                    service.viewAll();
                    break;

                case 3:
                    System.out.print("Enter Event Name: ");
                    String selectedEvent = sc.nextLine().trim();
                    service.filter(selectedEvent);
                    break;

                case 4:
                    service.exportCSV();
                    break;

                case 5:
                    System.out.println("Thank you for using the application!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}


import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, String[]> doctors = new HashMap<>();

        doctors.put("Cardiology",
                new String[]{"Dr. Ravi", "Dr. Kumar"});

        doctors.put("Neurology",
                new String[]{"Dr. Priya", "Dr. Anil"});

        doctors.put("Orthopedics",
                new String[]{"Dr. Suresh", "Dr. Meena"});

        System.out.println("================================");
        System.out.println(" HOSPITAL APPOINTMENT DESK");
        System.out.println("================================");

        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Error: Patient ID is required!");
            sc.close();
            return;
        }

        System.out.println("\nAvailable Departments:");
        System.out.println("1. Cardiology");
        System.out.println("2. Neurology");
        System.out.println("3. Orthopedics");

        System.out.print("Enter Department: ");
        String dept = sc.nextLine().trim();

        if (!doctors.containsKey(dept)) {
            System.out.println("Invalid Department!");
            sc.close();
            return;
        }

        System.out.println("\nAvailable Doctors:");

        String[] doctorList = doctors.get(dept);

        for (int i = 0; i < doctorList.length; i++) {
            System.out.println((i + 1) + ". " + doctorList[i]);
        }

        System.out.print("Enter Doctor Name: ");
        String doc = sc.nextLine().trim();

        boolean validDoctor = false;

        for (String d : doctorList) {
            if (d.equalsIgnoreCase(doc)) {
                validDoctor = true;
                doc = d;
                break;
            }
        }

        if (!validDoctor) {
            System.out.println("Invalid Doctor Selection!");
            sc.close();
            return;
        }

        System.out.println("\nAvailable Time Slots:");
        System.out.println("1. 10:00 AM");
        System.out.println("2. 11:00 AM");
        System.out.println("3. 02:00 PM");
        System.out.println("4. 04:00 PM");

        System.out.print("Enter Time Slot: ");
        String time = sc.nextLine().trim();

        if (!Arrays.asList("10:00 AM", "11:00 AM",
                "02:00 PM", "04:00 PM").contains(time)) {
            System.out.println("Invalid Time Slot!");
            sc.close();
            return;
        }

        System.out.println("\n================================");
        System.out.println(" APPOINTMENT BOOKED SUCCESSFULLY");
        System.out.println("================================");
        System.out.println("Patient ID : " + id);
        System.out.println("Department : " + dept);
        System.out.println("Doctor     : " + doc);
        System.out.println("Time Slot  : " + time);
        System.out.println("================================");

        sc.close();
    }
}

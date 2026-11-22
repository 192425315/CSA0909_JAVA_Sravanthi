
import java.io.*;
import java.sql.Connection;
import java.util.*;

// Resident class
class Resident implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    String regNo;
    String phoneNumber;

    // Sensitive and live fields are not serialized
    transient String password;
    transient Connection connection;

    Resident(String name, String regNo,
             String phoneNumber, String password) {

        this.name = name;
        this.regNo = regNo;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    void display() {
        System.out.println("Resident Name: " + name);
        System.out.println("Register Number: " + regNo);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Password: " + password);
    }
}

// Room class
class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    int roomNumber;
    ArrayList<Resident> residents;

    Room(int roomNumber) {
        this.roomNumber = roomNumber;
        residents = new ArrayList<>();
    }

    void addResident(Resident r) {
        residents.add(r);
    }

    void display() {
        System.out.println("\nRoom Number: " + roomNumber);

        for (Resident r : residents) {
            r.display();
        }
    }
}

// Hostel class
class Hostel implements Serializable {

    private static final long serialVersionUID = 1L;

    String hostelName;
    ArrayList<Room> rooms;

    Hostel(String hostelName) {
        this.hostelName = hostelName;
        rooms = new ArrayList<>();
    }

    void addRoom(Room room) {
        rooms.add(room);
    }

    void display() {
        System.out.println("\nHostel Name: " + hostelName);

        for (Room room : rooms) {
            room.display();
        }
    }
}

// Main class
public class Main {

    public static void main(String[] args) {

        try {

            // Create hostel
            Hostel hostel = new Hostel("SIMATS Hostel");

            // Create rooms
            Room room1 = new Room(101);
            Room room2 = new Room(102);

            // Create residents
            Resident r1 = new Resident(
                "Sravanthi", "192425315",
                "9876543210", "pass123");

            Resident r2 = new Resident(
                "Divya", "192425316",
                "9876501234", "divya123");

            // Add residents to rooms
            room1.addResident(r1);
            room2.addResident(r2);

            // Add rooms to hostel
            hostel.addRoom(room1);
            hostel.addRoom(room2);

            // Create temporary file
            File file = File.createTempFile(
                "hostel", ".ser");

            // Serialization
            System.out.println("===== SERIALIZATION =====");

            try (ObjectOutputStream out =
                     new ObjectOutputStream(
                         new FileOutputStream(file))) {

                out.writeObject(hostel);

                System.out.println(
                    "Hostel data saved successfully!");
            }

            // Deserialization
            System.out.println("\n===== DESERIALIZATION =====");

            try (ObjectInputStream in =
                     new ObjectInputStream(
                         new FileInputStream(file))) {

                Hostel restored =
                    (Hostel) in.readObject();

                System.out.println(
                    "Hostel data retrieved successfully!");

                restored.display();
            }

            System.out.println("\n===== TRANSIENT FIELD CHECK =====");
            System.out.println(
                "Password after deserialization: " +
                r1.password);

            file.delete();

            System.out.println(
                "\nProgram executed successfully!");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

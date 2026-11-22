
import java.util.*;

// Student class
class Student {
    int id;
    String name;
    String email;

    Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    void display() {
        System.out.println(
            "ID: " + id +
            ", Name: " + name +
            ", Email: " + email
        );
    }
}

// Generic DAO interface
interface Dao<T, K> {
    void insert(T value);
    Optional<T> findById(K id);
    List<T> findAll();
    boolean update(T value);
    boolean delete(K id);
}

// Student DAO implementation
class StudentDao implements Dao<Student, Integer> {

    private Map<Integer, Student> students =
            new LinkedHashMap<>();

    // Insert student
    public void insert(Student student) {
        students.put(student.id, student);
        System.out.println("Student inserted successfully!");
    }

    // Find student by ID
    public Optional<Student> findById(Integer id) {
        return Optional.ofNullable(students.get(id));
    }

    // Display all students
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    // Update student
    public boolean update(Student student) {
        if (students.containsKey(student.id)) {
            students.put(student.id, student);
            return true;
        }
        return false;
    }

    // Delete student
    public boolean delete(Integer id) {
        return students.remove(id) != null;
    }
}

// Main class
public class Main {

    public static void main(String[] args) {

        StudentDao dao = new StudentDao();

        System.out.println("===== STUDENT DATABASE SYSTEM =====");

        // Insert students
        System.out.println("\n1. INSERT STUDENTS");

        dao.insert(new Student(
            101, "Sravanthi", "sravanthi@gmail.com"));

        dao.insert(new Student(
            102, "Arun", "arun@gmail.com"));

        dao.insert(new Student(
            103, "Divya", "divya@gmail.com"));

        // Find student
        System.out.println("\n2. FIND STUDENT");

        Optional<Student> result = dao.findById(101);

        if (result.isPresent()) {
            result.get().display();
        } else {
            System.out.println("Student not found!");
        }

        // Display all students
        System.out.println("\n3. DISPLAY ALL STUDENTS");

        for (Student s : dao.findAll()) {
            s.display();
        }

        // Update student
        System.out.println("\n4. UPDATE STUDENT");

        boolean updated = dao.update(
            new Student(102, "Arun Kumar",
                        "arunkumar@gmail.com"));

        System.out.println(
            updated ? "Student updated successfully!"
                    : "Student not found!"
        );

        // Display after update
        dao.findById(102).ifPresent(Student::display);

        // Delete student
        System.out.println("\n5. DELETE STUDENT");

        boolean deleted = dao.delete(103);

        System.out.println(
            deleted ? "Student deleted successfully!"
                    : "Student not found!"
        );

        // Final student list
        System.out.println("\n6. FINAL STUDENT LIST");

        for (Student s : dao.findAll()) {
            s.display();
        }

        System.out.println("\nProgram executed successfully!");
    }
}

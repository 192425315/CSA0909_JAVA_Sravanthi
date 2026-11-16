
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

class Student {
    private final String regNo;
    private final String name;

    Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student s = (Student) o;
        return regNo.equals(s.regNo);
    }

    // Correct hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(regNo);
    }

    @Override
    public String toString() {
        return regNo + " " + name;
    }
}

class MutableStudent {
    String regNo;

    MutableStudent(String regNo) {
        this.regNo = regNo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MutableStudent)) return false;
        return regNo.equals(((MutableStudent) o).regNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo);
    }
}

public class Main {
    public static void main(String[] args) {

        // Test 1: Same object scanned twice
        Set<Student> test1 = new HashSet<>();
        Student s1 = new Student("101", "Arun");

        test1.add(s1);
        test1.add(s1);
        test1.add(new Student("102", "Divya"));

        System.out.println("Test 1 - Same object: " + test1.size());

        // Test 2: Duplicate registration number
        Set<Student> test2 = new HashSet<>();

        test2.add(new Student("201", "Kiran"));
        test2.add(new Student("202", "Meena"));
        test2.add(new Student("201", "Kiran"));

        System.out.println("Test 2 - Repeated scans: " + test2.size());

        // Test 3: Changing registration number
        Set<MutableStudent> test3 = new HashSet<>();
        MutableStudent m = new MutableStudent("301");

        test3.add(m);
        m.regNo = "302";

        System.out.println("Test 3 - Found after change: "
                + test3.contains(m));

        // Assignment's original example
        Set<Student> present = new HashSet<>();

        present.add(new Student("192311001", "Arun"));
        present.add(new Student("192311002", "Divya"));
        present.add(new Student("192311001", "Arun"));

        System.out.println("Unique students: " + present.size());
    }
}

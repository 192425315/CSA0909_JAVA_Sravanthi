
import java.util.*;

public class Main {

    static class Book {
        String title, category;

        Book(String title, String category) {
            this.title = title;
            this.category = category;
        }
    }

    static ArrayList<Book> books = new ArrayList<>();

    static ArrayList<Book> results = new ArrayList<>();

    static int page = 0;
    static final int PAGE_SIZE = 2;

    static void search(String keyword, String category) {

        results.clear();
        page = 0;

        for (Book b : books) {

            boolean titleMatch =
                b.title.toLowerCase().contains(
                    keyword.toLowerCase());

            boolean categoryMatch =
                category.equalsIgnoreCase("All")
                || b.category.equalsIgnoreCase(category);

            if (titleMatch && categoryMatch) {
                results.add(b);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No books found!");
        } else {
            displayResults();
        }
    }

    static void displayResults() {

        int start = page * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, results.size());

        System.out.println("\n--- LIBRARY SEARCH RESULTS ---");

        for (int i = start; i < end; i++) {
            Book b = results.get(i);

            System.out.println((i + 1) + ". "
                    + b.title + " - " + b.category);
        }

        System.out.println("Page " + (page + 1));
    }

    static void nextPage() {

        if ((page + 1) * PAGE_SIZE < results.size()) {
            page++;
            displayResults();
        } else {
            System.out.println("No next page!");
        }
    }

    static void previousPage() {

        if (page > 0) {
            page--;
            displayResults();
        } else {
            System.out.println("No previous page!");
        }
    }

    static void reserve(int number) {

        if (number >= 1 && number <= results.size()) {
            System.out.println("Book Reserved Successfully: "
                    + results.get(number - 1).title);
        } else {
            System.out.println("Invalid book number!");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        books.add(new Book("Java Programming", "Programming"));
        books.add(new Book("Python Basics", "Programming"));
        books.add(new Book("Data Structures", "Computer Science"));
        books.add(new Book("Artificial Intelligence", "Computer Science"));
        books.add(new Book("Database Management", "Database"));
        books.add(new Book("Computer Networks", "Computer Science"));

        System.out.println("================================");
        System.out.println("     CAMPUS LIBRARY SEARCH");
        System.out.println("================================");

        System.out.print("Enter book title keyword: ");
        String keyword = sc.nextLine();

        System.out.println("\nCategories:");
        System.out.println("All");
        System.out.println("Programming");
        System.out.println("Computer Science");
        System.out.println("Database");

        System.out.print("Select category: ");
        String category = sc.nextLine();

        search(keyword, category);

        while (!results.isEmpty()) {

            System.out.println("\n1. Next");
            System.out.println("2. Previous");
            System.out.println("3. Reserve");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice!");
                continue;
            }

            switch (choice) {

                case 1:
                    nextPage();
                    break;

                case 2:
                    previousPage();
                    break;

                case 3:
                    System.out.print("Enter book number to reserve: ");
                    try {
                        int number = Integer.parseInt(sc.nextLine());
                        reserve(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid book number!");
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using Library Search!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}

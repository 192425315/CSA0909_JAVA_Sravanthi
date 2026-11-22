
import java.util.Scanner;

public class Main {

    interface Converter {
        void convert(double amount, String currency);
    }

    // Version A: Frame-style ActionListener concept
    static class FrameListener {
        void convert(double amount, String currency) {
            calculate(amount, currency, "Version A");
        }
    }

    // Version B: Named Inner Class
    class NamedHandler implements Converter {
        public void convert(double amount, String currency) {
            calculate(amount, currency, "Version B");
        }
    }

    // Version C: Anonymous Inner Class
    static Converter anonymousHandler = new Converter() {
        public void convert(double amount, String currency) {
            calculate(amount, currency, "Version C");
        }
    };

    // Version D: Lambda Expression
    static Converter lambdaHandler = (amount, currency) -> {
        calculate(amount, currency, "Version D");
    };

    // Common conversion method
    static void calculate(double amount, String currency, String version) {

        double rate;

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (currency.equalsIgnoreCase("USD")) {
            rate = 0.012;
        } else if (currency.equalsIgnoreCase("EUR")) {
            rate = 0.011;
        } else {
            System.out.println("Invalid currency!");
            return;
        }

        System.out.println("\n" + version);
        System.out.printf("Converted Amount: %.2f %s%n",
                amount * rate, currency.toUpperCase());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       CURRENCY CONVERTER");
        System.out.println("================================");

        System.out.print("Enter amount in INR: ");
        double amount = sc.nextDouble();

        System.out.print("Enter target currency (USD/EUR): ");
        String currency = sc.next();

        System.out.println("\nSelect Implementation:");
        System.out.println("1. Frame Listener Concept");
        System.out.println("2. Named Inner Class");
        System.out.println("3. Anonymous Inner Class");
        System.out.println("4. Lambda Expression");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        Main obj = new Main();

        switch (choice) {

            case 1:
                new FrameListener().convert(amount, currency);
                break;

            case 2:
                obj.new NamedHandler().convert(amount, currency);
                break;

            case 3:
                anonymousHandler.convert(amount, currency);
                break;

            case 4:
                lambdaHandler.convert(amount, currency);
                break;

            default:
                System.out.println("Invalid choice!");
        }

        sc.close();
    }
}

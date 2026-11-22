
import java.awt.*;
import java.awt.event.*;

class FeeService {
    public String pay(String regNo, String amountText) {
        if (regNo.trim().isEmpty() || amountText.trim().isEmpty())
            return "Please fill all fields!";

        try {
            double amount = Double.parseDouble(amountText);

            if (amount <= 0)
                return "Amount must be greater than zero!";

            return "Payment Successful! Amount: Rs. " + amount;

        } catch (NumberFormatException e) {
            return "Enter a valid amount!";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        FeeService service = new FeeService();

        System.out.println(service.pay("192425315", "5000"));
        System.out.println(service.pay("", "5000"));
        System.out.println(service.pay("192425315", "abc"));
        System.out.println(service.pay("192425315", "-100"));
    }
}


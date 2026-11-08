
package atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private String description;
    private String dateTime;

    // Constructor
    public Transaction(String type, double amount, String description) {

        this.type = type;
        this.amount = amount;
        this.description = description;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.dateTime = LocalDateTime.now().format(formatter);
    }

    // Getters
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getDateTime() {
        return dateTime;
    }

    // Display transaction
    public void displayTransaction() {

        System.out.println("Type: " + type);
        System.out.println("Amount: Rs. " + amount);
        System.out.println("Description: " + description);
        System.out.println("Date & Time: " + dateTime);
        System.out.println("----------------------------");
    }
}

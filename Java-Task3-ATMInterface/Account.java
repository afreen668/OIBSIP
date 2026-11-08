
package atm;

public class Account {

    // Private account details
    private String userId;
    private String pin;
    private String accountHolder;
    private double balance;

    // Constructor
    public Account(String userId, String pin, String accountHolder, double balance) {

        this.userId = userId;
        this.pin = pin;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Get User ID
    public String getUserId() {
        return userId;
    }

    // Get PIN
    public String getPin() {
        return pin;
    }

    // Get Account Holder Name
    public String getAccountHolder() {
        return accountHolder;
    }

    // Get Balance
    public double getBalance() {
        return balance;
    }

    // Validate PIN
    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    // Deposit Money
    public void deposit(double amount) {

        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful!");
            System.out.println("New Balance: Rs. " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw Money
    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
        }
        else if (amount > balance) {
            System.out.println("Insufficient Funds!");
        }
        else {
            balance -= amount;
            System.out.println("Withdrawal successful!");
            System.out.println("Remaining Balance: Rs. " + balance);
        }
    }
}



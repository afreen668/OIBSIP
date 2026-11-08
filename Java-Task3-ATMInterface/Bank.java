
package atm;

import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;

    // Constructor
    public Bank() {
        accounts = new ArrayList<>();

        // Sample accounts
        accounts.add(new Account("1001", "1234", "Ali Ahmed", 50000));
        accounts.add(new Account("1002", "5678", "Sara Khan", 30000));
    }

    // Find account using User ID
    public Account findAccount(String userId) {
        for (Account account : accounts) {
            if (account.getUserId().equals(userId)) {
                return account;
            }
        }

        return null;
    }

    // Validate login credentials
    public Account login(String userId, String pin) {
        Account account = findAccount(userId);

        if (account != null && account.validatePin(pin)) {
            return account;
        }

        return null;
    }

    // Transfer money between accounts
    public boolean transfer(String senderId, String receiverId, double amount) {

        Account sender = findAccount(senderId);
        Account receiver = findAccount(receiverId);

        if (sender == null || receiver == null) {
            System.out.println("Account not found!");
            return false;
        }

        if (senderId.equals(receiverId)) {
            System.out.println("Cannot transfer to the same account!");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Invalid transfer amount!");
            return false;
        }

        if (sender.getBalance() < amount) {
            System.out.println("Insufficient Funds!");
            return false;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        System.out.println("Transfer successful!");
        return true;
    }
}

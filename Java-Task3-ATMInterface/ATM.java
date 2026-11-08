package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;
    private ArrayList<Transaction> transactions;

    // Constructor
    public ATM() {
        bank = new Bank();
        scanner = new Scanner(System.in);
        transactions = new ArrayList<>();
    }

    // Login system
    public void start() {

        Account currentAccount = null;
        int attempts = 0;

        System.out.println("===== WELCOME TO ATM =====");

        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            currentAccount = bank.login(userId, pin);

            if (currentAccount != null) {
                System.out.println("\nLogin Successful!");
                System.out.println("Welcome, " +
                        currentAccount.getAccountHolder());
                break;
            } else {
                attempts++;
                System.out.println("Invalid User ID or PIN!");

                System.out.println("Attempts remaining: "
                        + (3 - attempts));
            }
        }

        if (currentAccount == null) {
            System.out.println("Account access denied!");
            return;
        }

        showMenu(currentAccount);
    }

    // Main ATM menu
    private void showMenu(Account account) {

        int choice = 0;

        do {

            System.out.println("\n===== ATM MAIN MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw(account);
                    break;

                case 3:
                    deposit(account);
                    break;

                case 4:
                    transfer(account);
                    break;

                case 5:
                    System.out.println("Thank you for using our ATM!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (choice != 5);
    }

    // Deposit
    private void deposit(Account account) {

        System.out.print("Enter deposit amount: ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount > 0) {
                account.deposit(amount);

                transactions.add(new Transaction(
                        "Deposit", amount, "Money deposited"));

            } else {
                System.out.println("Invalid amount!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount!");
        }
    }

    // Withdraw
    private void withdraw(Account account) {

        System.out.print("Enter withdrawal amount: ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                System.out.println("Invalid amount!");
                return;
            }

            if (amount > account.getBalance()) {
                System.out.println("Insufficient Funds!");
                return;
            }

            account.withdraw(amount);

            transactions.add(new Transaction(
                    "Withdrawal", amount, "Money withdrawn"));

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount!");
        }
    }

    // Transfer
    private void transfer(Account account) {

        System.out.print("Enter recipient User ID: ");
        String receiverId = scanner.nextLine();

        System.out.print("Enter transfer amount: ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());

            boolean success = bank.transfer(
                    account.getUserId(), receiverId, amount);

            if (success) {
                transactions.add(new Transaction(
                        "Transfer", amount,
                        "Transferred to " + receiverId));
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount!");
        }
    }

    // Transaction history
    private void showTransactionHistory() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }
}
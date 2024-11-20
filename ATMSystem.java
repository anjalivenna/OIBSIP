import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATMSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();
        
        System.out.println("Welcome to the ATM System!");
        
        // Prompt for user id and pin
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        
        // Validate user
        User user = atm.authenticateUser(userId, pin);
        
        if (user != null) {
            System.out.println("Authentication successful.");
            atm.showMenu(user);
        } else {
            System.out.println("Invalid user ID or PIN.");
        }
    }

    // User class - represents an individual user in the system
    public static class User {
        private String userId;
        private String pin;
        private double balance;
        private List<String> transactionHistory;
        
        public User(String userId, String pin, double balance) {
            this.userId = userId;
            this.pin = pin;
            this.balance = balance;
            this.transactionHistory = new ArrayList<>();
        }

        public String getUserId() {
            return userId;
        }

        public String getPin() {
            return pin;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
            addTransaction("Deposited: $" + amount);
        }

        public boolean withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
                addTransaction("Withdrew: $" + amount);
                return true;
            }
            return false;
        }

        public void addTransaction(String transaction) {
            transactionHistory.add(transaction);
        }

        public List<String> getTransactionHistory() {
            return transactionHistory;
        }
    }

    // TransactionHistory class - handles transaction history for the user
    public static class TransactionHistory {
        public void showHistory(User user) {
            List<String> history = user.getTransactionHistory();
            if (history.isEmpty()) {
                System.out.println("No transactions yet.");
            } else {
                System.out.println("Transaction History:");
                for (String transaction : history) {
                    System.out.println(transaction);
                }
            }
        }
    }

    // BankingOperations class - performs banking operations like deposit, withdrawal
    public static class BankingOperations {
        public void deposit(User user, double amount) {
            user.deposit(amount);
            System.out.println("Deposited: $" + amount);
        }

        public void withdraw(User user, double amount) {
            if (user.withdraw(amount)) {
                System.out.println("Withdrew: $" + amount);
            } else {
                System.out.println("Insufficient balance.");
            }
        }
    }

    // ATM class - acts as an interface for interacting with the ATM system
    public static class ATM {
        private BankingOperations bankingOperations = new BankingOperations();
        private TransactionHistory transactionHistory = new TransactionHistory();

        // Hardcoded users (for simplicity, but this could be dynamic)
        private User user1 = new User("user1", "1234", 500.0);
        private User user2 = new User("user2", "5678", 1000.0);

        public User authenticateUser(String userId, String pin) {
            if (userId.equals(user1.getUserId()) && pin.equals(user1.getPin())) {
                return user1;
            } else if (userId.equals(user2.getUserId()) && pin.equals(user2.getPin())) {
                return user2;
            }
            return null;
        }

        public void showMenu(User user) {
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Select an option: ");
                
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        transactionHistory.showHistory(user);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        bankingOperations.withdraw(user, withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        bankingOperations.deposit(user, depositAmount);
                        break;
                    case 4:
                        // Transfer functionality can be added here
                        System.out.println("Transfer feature is under development.");
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM.");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}

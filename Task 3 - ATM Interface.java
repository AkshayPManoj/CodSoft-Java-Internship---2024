//Task 3 - ATM Interface

import java.util.Scanner;

public class ATMSystem 
{

    // Inner class to represent the Bank Account
    static class BankAccount 
    {
        private double balance;

        public BankAccount(double initialBalance) 
        {
            this.balance = initialBalance;
        }

        public double getBalance() 
        {
            return balance;
        }

        // Overloaded deposit method for different types of input
        public void deposit(double amount) 
        {
            if (amount > 0) 
            {
                balance += amount;
            } 
            else 
            {
                System.out.println("Deposit amount must be positive.");
            }
        }

        // Overloaded deposit method with percentage
        public void deposit(double amount, boolean isPercentage) 
        {
            if (isPercentage && amount > 0 && amount <= 100) 
            {
                double depositAmount = (balance * amount) / 100;
                balance += depositAmount;
            } 
            else 
            {
                deposit(amount);
            }
        }

        // Overloaded withdraw method for different types of input
        public boolean withdraw(double amount) 
        {
            if (amount > 0 && amount <= balance) 
            {
                balance -= amount;
                return true;
            } 
            else 
            {
                System.out.println("Insufficient funds or invalid amount.");
                return false;
            }
        }

        // Overloaded withdraw method with percentage
        public boolean withdraw(double amount, boolean isPercentage) 
        {
            if (isPercentage && amount > 0 && amount <= 100) 
            {
                double withdrawAmount = (balance * amount) / 100;
                return withdraw(withdrawAmount);
            } 
            else 
            {
                return withdraw(amount);
            }
        }
    }

    // Inner class to represent the ATM functionality
    static class ATM 
    {
        private BankAccount account;

        public ATM(BankAccount account) 
        {
            this.account = account;
        }

        public void showMenu() 
        {
            Scanner scanner = new Scanner(System.in);
            while (true) 
            {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();

                switch (choice) 
                {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit(scanner);
                        break;
                    case 3:
                        withdraw(scanner);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                }
            }
        }

        private void checkBalance() 
        {
            System.out.println("Your current balance is: Rs." + account.getBalance());
        }

        private void deposit(Scanner scanner) 
        {
            System.out.print("Enter deposit amount: Rs.");
            double amount = scanner.nextDouble();
            System.out.print("Deposit as percentage of balance? (yes/no): ");
            boolean isPercentage = scanner.next().equalsIgnoreCase("yes");
            account.deposit(amount, isPercentage);
            System.out.println("Deposit successful. Your new balance is: Rs." + account.getBalance());
        }

        private void withdraw(Scanner scanner) 
        {
            System.out.print("Enter withdrawal amount: Rs.");
            double amount = scanner.nextDouble();
            System.out.print("Withdraw as percentage of balance? (yes/no): ");
            boolean isPercentage = scanner.next().equalsIgnoreCase("yes");
            if (account.withdraw(amount, isPercentage)) {
                System.out.println("Withdrawal successful. Your new balance is: Rs." + account.getBalance());
            }
        }
    }

    public static void main(String[] args) 
    {
        // Initialize bank account with an initial balance
        BankAccount myAccount = new BankAccount(500.00); // Example initial balance

        // Initialize ATM with the bank account
        ATM atm = new ATM(myAccount);

        // Start ATM interface
        atm.showMenu();
    }
}

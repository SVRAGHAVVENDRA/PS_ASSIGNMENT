package com.savoira;

/**
 * Demo runner class for verifying the BankAccount implementation.
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("=== MERIDIAN RETAIL BANK — BANK ACCOUNT DEMO ===");

        // 1. Create two bank accounts
        // Account 1: using the primary constructor
        System.out.println("\n--- Creating Account 1 ---");
        BankAccount acc1 = new BankAccount("1001", "Alice Smith", 5000.0);
        System.out.println("Created Account 1: " + acc1);

        // Account 2: using the overloaded constructor (defaults to 0.0 balance)
        System.out.println("\n--- Creating Account 2 ---");
        BankAccount acc2 = new BankAccount("1002", "Bob Jones");
        System.out.println("Created Account 2: " + acc2);

        // 2. Perform deposits and withdrawals
        System.out.println("\n--- Performing Valid Transactions ---");
        
        System.out.println("Depositing Rs. 1500.0 into Account 1...");
        acc1.deposit(1500.0);
        System.out.println("Withdrawing Rs. 2000.0 from Account 1...");
        acc1.withdraw(2000.0);
        System.out.println("Account 1 State: " + acc1);

        System.out.println("\nDepositing Rs. 3500.0 into Account 2...");
        acc2.deposit(3500.0);
        System.out.println("Withdrawing Rs. 1200.0 from Account 2...");
        acc2.withdraw(1200.0);
        System.out.println("Account 2 State: " + acc2);

        // 3. Attempt one invalid operation per account
        System.out.println("\n--- Attempting Invalid Transactions (Error Handling) ---");

        // Invalid operation 1: Overdraft on Account 1
        try {
            System.out.println("Attempting to withdraw Rs. 10000.0 from Account 1 (exceeds balance of Rs. " + acc1.getBalance() + ")...");
            acc1.withdraw(10000.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        // Invalid operation 2: Negative deposit on Account 2
        try {
            System.out.println("Attempting to deposit Rs. -500.0 into Account 2...");
            acc2.deposit(-500.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        // 4. Print final status
        System.out.println("\n--- Final Account Summary ---");
        System.out.println("Account 1: " + acc1);
        System.out.println("Account 2: " + acc2);
        System.out.println("\n================================================");
    }
}

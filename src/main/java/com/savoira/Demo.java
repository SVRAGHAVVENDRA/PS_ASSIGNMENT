package com.savoira;

/**
 * Updated Demo runner class for verifying BankAccount limits, static tracking,
 * and clean LoanUtils calculations on the w4-static-clean branch.
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("=== MERIDIAN RETAIL BANK — ADVANCED DEMO (w4-static-clean) ===");

        // 1. Demonstrate static account tracking
        System.out.println("\nInitial total accounts created: " + BankAccount.getTotalAccountsCreated());

        System.out.println("Creating multiple accounts...");
        var acc1 = new BankAccount("2001", "Emma Watson", 100_000.0);
        var acc2 = new BankAccount("2002", "Daniel Radcliffe", 50_000.0);
        var acc3 = new BankAccount("2003", "Rupert Grint");

        System.out.println("Total accounts created now: " + BankAccount.getTotalAccountsCreated());

        // 2. Demonstrate BankConfig Limits
        System.out.println("\n--- Testing BankConfig Limits ---");
        System.out.println("Max Deposit Allowed: Rs. " + BankConfig.MAX_DEPOSIT);
        System.out.println("Max Withdrawal Allowed: Rs. " + BankConfig.MAX_WITHDRAWAL);
        System.out.println("Max Daily Transactions: " + BankConfig.MAX_DAILY_TXN);

        // A. Test Exceeding Max Deposit Limit
        try {
            System.out.println("\nAttempting to deposit Rs. 600,000 into Account 1...");
            acc1.deposit(600_000.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        // B. Test Exceeding Max Withdrawal Limit
        try {
            System.out.println("\nAttempting to withdraw Rs. 250,000 from Account 1...");
            acc1.withdraw(250_000.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        // C. Test Exceeding Daily Transaction Limit (MAX_DAILY_TXN = 10)
        System.out.println("\nPerforming 10 successful rapid small deposits on Account 3...");
        for (var i = 1; i <= 10; i++) {
            acc3.deposit(10.0);
            System.out.println("Txn #" + i + " complete. Status: " + acc3);
        }

        try {
            System.out.println("\nAttempting the 11th transaction (exceeding daily limit of 10) on Account 3...");
            acc3.deposit(10.0);
        } catch (IllegalStateException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        // 3. Demonstrate LoanUtils Monthly Compound Interest
        System.out.println("\n--- Testing Clean LoanUtils Compound Interest ---");
        var principal = 10_000.0;
        var annualRate = 6.0; // 6% annual rate
        var months = 12; // 1 year

        var finalAmount = LoanUtils.calculateMonthlyCompoundInterest(principal, annualRate, months);
        System.out.printf("Principal: Rs. %.2f%n", principal);
        System.out.printf("Annual Interest Rate: %.2f%%%n", annualRate);
        System.out.printf("Compounding Period: %d months%n", months);
        System.out.printf("Accumulated Future Value: Rs. %.2f%n", finalAmount);
        System.out.printf("Interest Earned: Rs. %.2f%n", (finalAmount - principal));

        System.out.println("\n============================================================");
    }
}

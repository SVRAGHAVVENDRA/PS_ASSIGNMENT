package com.savoira.assessment;

import java.util.Locale;

/**
 * Task C1: BankAccount Class
 * Encapsulated bank account representing Meridian Retail Bank customer balances.
 */
public class BankAccount {
    private final String accountNumber;
    private final String holderName;
    private double balance;
    private int transactionCount;

    /**
     * Primary constructor to initialize a BankAccount.
     *
     * @param accountNumber the unique account identifier (cannot be null or blank)
     * @param holderName    the account holder's name (cannot be null or blank)
     * @param initialBalance the starting balance (must be non-negative)
     * @throws IllegalArgumentException if validation rules are violated
     */
    public BankAccount(final String accountNumber, final String holderName, final double initialBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name cannot be null or blank.");
        }
        if (initialBalance < 0.0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactionCount = 0;
    }

    /**
     * Overloaded constructor. Initial balance defaults to 0.0.
     * Uses constructor chaining to call the primary constructor.
     *
     * @param accountNumber the unique account identifier
     * @param holderName    the account holder's name
     * @throws IllegalArgumentException if validation rules are violated
     */
    public BankAccount(final String accountNumber, final String holderName) {
        this(accountNumber, holderName, 0.0);
    }

    /**
     * Deposits a specified amount into the account.
     * Enforces that the amount must be greater than zero.
     *
     * @param amount the deposit amount (must be positive)
     * @throws IllegalArgumentException if amount is non-positive
     */
    public void deposit(final double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        this.balance += amount;
        this.transactionCount++;
    }

    /**
     * Withdraws a specified amount from the account.
     * Enforces that the amount must be positive and does not exceed the available balance.
     *
     * @param amount the withdrawal amount (must be positive and <= balance)
     * @throws IllegalArgumentException if validation checks fail
     */
    public void withdraw(final double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient balance. Cannot withdraw more than the current balance.");
        }
        this.balance -= amount;
        this.transactionCount++;
    }

    /**
     * Gets the current account balance.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the count of successful transactions executed on this account.
     *
     * @return the number of deposits and withdrawals
     */
    public int getTransactionCount() {
        return transactionCount;
    }

    /**
     * Gets the unique account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the name of the account holder.
     *
     * @return the account holder name
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Returns a formatted summary string of the account state.
     * Formats balance to 2 decimal places prefixed with ₹.
     *
     * @return the account summary string
     */
    public String getSummary() {
        String formattedBalance = String.format(Locale.ROOT, "%.2f", this.balance);
        return "Account Number: " + this.accountNumber + 
               " | Holder: " + this.holderName.toUpperCase(Locale.ROOT) + 
               " | Balance: ₹" + formattedBalance + 
               " | Txns: " + this.transactionCount;
    }

    public static void main(String[] args) {
        System.out.println("=== Task C1: BankAccount Demonstration ===");

        // Create two accounts
        System.out.println("Creating Alice's account with initial balance Rs. 2000...");
        BankAccount acc1 = new BankAccount("A101", "Alice Smith", 2000.0);

        System.out.println("Creating Bob's account with default balance (0.0)...");
        BankAccount acc2 = new BankAccount("B102", "Bob Jones");

        // Perform deposits and withdrawals
        System.out.println("\nAlice deposits Rs. 1500...");
        acc1.deposit(1500.0);
        System.out.println("Alice withdraws Rs. 800...");
        acc1.withdraw(800.0);

        System.out.println("Bob deposits Rs. 500...");
        acc2.deposit(500.0);

        // Attempt invalid operation (negative withdrawal)
        try {
            System.out.println("\nAttempting to withdraw a negative amount from Alice's account...");
            acc1.withdraw(-100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        // Print summary for each account
        System.out.println("\n--- Final Summaries ---");
        System.out.println(acc1.getSummary());
        System.out.println(acc2.getSummary());
    }
}

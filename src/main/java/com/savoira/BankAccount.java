package com.savoira;

/**
 * Represents a bank account at Meridian Retail Bank.
 * Enforces business rules and constraints on all state changes.
 */
public class BankAccount {
    // Static tracking
    private static int totalAccountsCreated = 0;

    private final String accountNumber;
    private String holderName;
    private double balance;
    private int transactionCount;

    /**
     * Primary constructor to initialize a BankAccount.
     *
     * @param accountNumber the unique account identifier (cannot be null or blank)
     * @param holderName    the account holder's name (cannot be null or blank)
     * @param initialBalance the starting balance (must be non-negative)
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

        // Static tracking increment
        synchronized (BankAccount.class) {
            totalAccountsCreated++;
        }
    }

    /**
     * Overloaded constructor. Initial balance defaults to 0.0.
     *
     * @param accountNumber the unique account identifier (cannot be null or blank)
     * @param holderName    the account holder's name (cannot be null or blank)
     */
    public BankAccount(final String accountNumber, final String holderName) {
        this(accountNumber, holderName, 0.0);
    }

    /**
     * Deposits a specified amount into the account, enforcing limits.
     *
     * @param amount the amount to deposit (must be greater than zero, and up to MAX_DEPOSIT)
     */
    public void deposit(final double amount) {
        // Enforce daily transaction limit
        if (this.transactionCount >= BankConfig.MAX_DAILY_TXN) {
            throw new IllegalStateException("Transaction rejected: Daily transaction limit of " + BankConfig.MAX_DAILY_TXN + " exceeded.");
        }
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        // Enforce max deposit limit
        if (amount > BankConfig.MAX_DEPOSIT) {
            throw new IllegalArgumentException("Deposit amount exceeds the maximum limit of Rs. " + BankConfig.MAX_DEPOSIT);
        }

        this.balance += amount;
        this.transactionCount++;
    }

    /**
     * Withdraws a specified amount from the account, enforcing limits.
     *
     * @param amount the amount to withdraw (must be greater than zero, up to MAX_WITHDRAWAL, and less than or equal to balance)
     */
    public void withdraw(final double amount) {
        // Enforce daily transaction limit
        if (this.transactionCount >= BankConfig.MAX_DAILY_TXN) {
            throw new IllegalStateException("Transaction rejected: Daily transaction limit of " + BankConfig.MAX_DAILY_TXN + " exceeded.");
        }
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        // Enforce max withdrawal limit
        if (amount > BankConfig.MAX_WITHDRAWAL) {
            throw new IllegalArgumentException("Withdrawal amount exceeds the maximum limit of Rs. " + BankConfig.MAX_WITHDRAWAL);
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal.");
        }

        this.balance -= amount;
        this.transactionCount++;
    }

    /**
     * Returns the unique account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns the name of the account holder.
     *
     * @return the holder's name
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Sets a new name for the account holder.
     *
     * @param holderName the new holder name (cannot be null or blank)
     */
    public void setHolderName(final String holderName) {
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name cannot be null or blank.");
        }
        this.holderName = holderName;
    }

    /**
     * Returns the current balance.
     *
     * @return the account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns the total number of successfully executed transactions (deposits or withdrawals).
     *
     * @return the transaction count
     */
    public int getTransactionCount() {
        return transactionCount;
    }

    /**
     * Static getter for total number of accounts created.
     *
     * @return the total number of accounts created
     */
    public static int getTotalAccountsCreated() {
        synchronized (BankAccount.class) {
            return totalAccountsCreated;
        }
    }

    @Override
    public String toString() {
        return "ACC" + accountNumber + " | " + holderName + " | Balance: Rs." + balance + " | Txn: " + transactionCount;
    }
}

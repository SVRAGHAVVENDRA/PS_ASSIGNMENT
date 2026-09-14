package com.savoira.assessment;

import java.util.Scanner;

/**
 * Simulates ATM withdrawal logic for Meridian Retail Bank.
 * Enforces business constraints on cash dispensation:
 * 1. Minimum withdrawal ₹500
 * 2. Maximum withdrawal ₹20,000
 * 3. Amount must be a multiple of ₹500
 * 4. Sufficient balance check
 * 5. Maximum 3 failed attempts before card lock
 */
public class ATMSimulator {
    private static final double MIN_WITHDRAWAL = 500.0;
    private static final double MAX_WITHDRAWAL = 20000.0;
    private static final double DENOMINATION = 500.0;
    private static final int MAX_ATTEMPTS = 3;

    private double balance;

    public ATMSimulator() {
        this.balance = 10000.0;
    }

    public ATMSimulator(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Attempts to withdraw the requested amount from the account.
     * Validates all ATM business rules sequentially.
     *
     * @param amount Requested withdrawal amount in INR
     * @throws BelowMinimumWithdrawalException if amount < ₹500
     * @throws AboveMaximumWithdrawalException if amount > ₹20,000
     * @throws InvalidDenominationException    if amount is not a multiple of ₹500
     * @throws InsufficientBalanceException    if amount exceeds available balance
     */
    public void withdraw(double amount) throws ATMException {
        if (amount < MIN_WITHDRAWAL) {
            throw new BelowMinimumWithdrawalException(
                    "Error: Minimum withdrawal amount is ₹" + (int) MIN_WITHDRAWAL + ". Requested: ₹" + amount);
        }

        if (amount > MAX_WITHDRAWAL) {
            throw new AboveMaximumWithdrawalException(
                    "Error: Maximum withdrawal amount is ₹" + (int) MAX_WITHDRAWAL + ". Requested: ₹" + amount);
        }

        if (amount % DENOMINATION != 0) {
            throw new InvalidDenominationException(
                    "Error: Amount must be in multiples of ₹" + (int) DENOMINATION + ". Requested: ₹" + amount);
        }

        if (amount > balance) {
            double shortfall = amount - balance;
            throw new InsufficientBalanceException(
                    "Error: Insufficient balance. Available: ₹" + balance + ", Requested: ₹" + amount, shortfall);
        }

        this.balance -= amount;
    }

    /**
     * Interactive CLI session loop accepting user withdrawal attempts up to MAX_ATTEMPTS.
     */
    public static void main(String[] args) {
        ATMSimulator atm = new ATMSimulator();
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        System.out.println("=== Welcome to Meridian Retail Bank ATM ===");
        System.out.println("Current Account Balance: ₹" + atm.getBalance());

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("\nEnter withdrawal amount (Attempt " + (attempts + 1) + " of " + MAX_ATTEMPTS + "): ₹");
            String input = scanner.nextLine().trim();

            double amount;
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid numeric input. Please enter a valid number.");
                attempts++;
                continue;
            }

            try {
                atm.withdraw(amount);
                System.out.println("Withdrawal successful! Dispensed: ₹" + amount);
                System.out.println("Updated Account Balance: ₹" + atm.getBalance());
                return;
            } catch (ATMException e) {
                System.out.println(e.getMessage());
                attempts++;
            }
        }

        if (attempts >= MAX_ATTEMPTS) {
            System.out.println("\nCard locked.");
        }
    }
}

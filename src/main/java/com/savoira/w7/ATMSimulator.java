package com.savoira.w7;

import java.util.Scanner;

/**
 * Simulates an ATM withdrawal session for Meridian Retail Bank.
 * Enforces five business rules on every withdrawal attempt:
 * 1. Minimum withdrawal Rs.500
 * 2. Maximum withdrawal Rs.20,000
 * 3. Amount must be a multiple of Rs.500
 * 4. Amount must not exceed the current balance
 * 5. A maximum of 3 failed attempts locks the card
 */
public class ATMSimulator {
    private static final double MIN_WITHDRAWAL = 500.0;
    private static final double MAX_WITHDRAWAL = 20000.0;
    private static final double DENOMINATION = 500.0;
    private static final int MAX_ATTEMPTS = 3;

    private double balance;

    public ATMSimulator(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    private static String formatMoney(double val) {
        if (val == (long) val) {
            return String.valueOf((long) val);
        }
        return String.format("%.2f", val);
    }

    /**
     * Attempts to withdraw the specified amount, enforcing all five ATM rules.
     *
     * @param amount the amount requested for withdrawal
     * @throws BelowMinimumWithdrawalException if amount < Rs.500
     * @throws AboveMaximumWithdrawalException if amount > Rs.20,000
     * @throws InvalidDenominationException    if amount is not a multiple of Rs.500
     * @throws InsufficientBalanceException     if amount > current balance
     */
    public void withdraw(double amount) {
        if (amount < MIN_WITHDRAWAL) {
            throw new BelowMinimumWithdrawalException(
                    "Minimum withdrawal amount is Rs." + formatMoney(MIN_WITHDRAWAL) + ". Requested: Rs." + formatMoney(amount));
        }

        if (amount > MAX_WITHDRAWAL) {
            throw new AboveMaximumWithdrawalException(
                    "Maximum withdrawal amount is Rs." + formatMoney(MAX_WITHDRAWAL) + ". Requested: Rs." + formatMoney(amount));
        }

        if (amount % DENOMINATION != 0) {
            throw new InvalidDenominationException(
                    "Withdrawal amount must be a multiple of Rs." + formatMoney(DENOMINATION) + ". Requested: Rs." + formatMoney(amount));
        }

        if (amount > balance) {
            double shortfall = amount - balance;
            throw new InsufficientBalanceException(
                    "Insufficient balance. Available: Rs." + formatMoney(balance) + ", Requested: Rs." + formatMoney(amount)
                            + ", Shortfall: Rs." + formatMoney(shortfall),
                    shortfall);
        }

        balance -= amount;
    }

    /**
     * Runs an interactive ATM session: prompts for a withdrawal amount, allows up to
     * MAX_ATTEMPTS failed attempts, then locks the card.
     */
    public void runSession() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        System.out.println("Welcome to Meridian Retail Bank ATM.");
        System.out.println("Current balance: Rs." + formatMoney(balance));

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter withdrawal amount (attempt " + (attempts + 1) + " of " + MAX_ATTEMPTS + "): ");
            double amount;
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Exception: Invalid numeric input. Please enter a valid amount.");
                attempts++;
                continue;
            }

            try {
                withdraw(amount);
                System.out.println("Withdrawal successful. New balance: Rs." + formatMoney(balance));
                return;
            } catch (ATMException e) {
                System.out.println("Exception: " + e.getMessage());
                attempts++;
            }
        }

        System.out.println("Card locked.");
    }

    public static void main(String[] args) {
        ATMSimulator atm = new ATMSimulator(3000.0);
        atm.runSession();
    }
}


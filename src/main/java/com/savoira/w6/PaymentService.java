package com.savoira.w6;

/**
 * Service for processing retail bank payments with custom exception checks.
 */
public class PaymentService {
    private static final double DAILY_LIMIT = 200000.0;
    private double balance;

    public PaymentService(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Processes a payment of the specified amount against current balance.
     *
     * @param amount the payment amount
     * @throws InvalidAmountException if amount <= 0
     * @throws DailyLimitExceededException if amount > DAILY_LIMIT (Rs.200,000)
     * @throws InsufficientFundsException if amount > balance
     */
    public void processPayment(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid payment amount: Rs." + amount + ". Payment amount must be strictly positive.");
        }

        if (amount > DAILY_LIMIT) {
            throw new DailyLimitExceededException("Daily transaction limit of Rs." + DAILY_LIMIT + " exceeded. Attempted amount: Rs." + amount, amount);
        }

        if (amount > balance) {
            double shortfall = amount - balance;
            throw new InsufficientFundsException("Insufficient funds. Available: Rs." + balance + ", Requested: Rs." + amount + ", Shortfall: Rs." + shortfall, shortfall);
        }

        balance -= amount;
        System.out.println("Payment of Rs." + amount + " processed. New balance: Rs." + balance);
    }

    public static void main(String[] args) {
        System.out.println("=== Meridian Bank: Payment Service Exception Handling Demo ===");
        PaymentService service = new PaymentService(50000.0);
        System.out.println("Initial Account Balance: Rs." + service.getBalance());
        System.out.println("---------------------------------------------------------------");

        // Call 1: processPayment(15000) - should succeed
        System.out.println("\n[Attempt 1] Processing Rs.15000 payment:");
        try {
            service.processPayment(15000);
        } catch (PaymentException e) {
            System.err.println("Failed: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }

        // Call 2: processPayment(-500) - should throw InvalidAmountException
        System.out.println("\n[Attempt 2] Processing Rs.-500 payment:");
        try {
            service.processPayment(-500);
        } catch (InvalidAmountException e) {
            System.out.println("Caught InvalidAmountException: " + e.getMessage());
        } catch (PaymentException e) {
            System.err.println("Caught unexpected PaymentException: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }

        // Call 3: processPayment(250000) - should throw DailyLimitExceededException
        System.out.println("\n[Attempt 3] Processing Rs.250000 payment:");
        try {
            service.processPayment(250000);
        } catch (DailyLimitExceededException e) {
            System.out.println("Caught DailyLimitExceededException: " + e.getMessage());
            System.out.println("-> Attempted amount retrieved from exception: Rs." + e.getAttemptedAmount());
        } catch (PaymentException e) {
            System.err.println("Caught unexpected PaymentException: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }

        // Call 4: processPayment(40000) - balance is Rs.35000, so Rs.40000 exceeds available balance (shortfall Rs.5000)
        System.out.println("\n[Attempt 4] Processing Rs.40000 payment:");
        try {
            service.processPayment(40000);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught InsufficientFundsException: " + e.getMessage());
            System.out.println("-> Shortfall retrieved from exception: Rs." + e.getShortfall());
        } catch (PaymentException e) {
            System.err.println("Caught unexpected PaymentException: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }

        // Call 5: processPayment(10000) - balance is still Rs.35000, so Rs.10000 succeeds
        System.out.println("\n[Attempt 5] Processing Rs.10000 payment:");
        try {
            service.processPayment(10000);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught InsufficientFundsException: " + e.getMessage());
            System.out.println("-> Shortfall retrieved from exception: Rs." + e.getShortfall());
        } catch (PaymentException e) {
            System.err.println("Caught unexpected PaymentException: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("Final Account Balance: Rs." + service.getBalance());
    }
}

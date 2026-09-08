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

    private static String formatMoney(double val) {
        if (val == (long) val) {
            return String.valueOf((long) val);
        }
        return String.format("%.2f", val);
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
            throw new InvalidAmountException("Invalid payment amount: Rs." + formatMoney(amount) + ". Payment amount must be positive.");
        }

        if (amount > DAILY_LIMIT) {
            throw new DailyLimitExceededException("Daily transaction limit of Rs.200000 exceeded. Attempted amount: Rs." + formatMoney(amount), amount);
        }

        if (amount > balance) {
            double shortfall = amount - balance;
            throw new InsufficientFundsException("Insufficient funds. Available balance: Rs." + formatMoney(balance) + ", Requested: Rs." + formatMoney(amount) + ", Shortfall: Rs." + formatMoney(shortfall), shortfall);
        }

        balance -= amount;
        System.out.println("Payment of Rs." + formatMoney(amount) + " processed. New balance: Rs." + formatMoney(balance));
    }

    public static void main(String[] args) {
        PaymentService service = new PaymentService(50000.0);

        // 1. processPayment(15000) - should succeed
        try {
            service.processPayment(15000);
        } catch (PaymentException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }

        // 2. processPayment(-500) - should throw InvalidAmountException
        try {
            service.processPayment(-500);
        } catch (PaymentException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }

        // 3. processPayment(250000) - should throw DailyLimitExceededException
        try {
            service.processPayment(250000);
        } catch (PaymentException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }

        // 4. processPayment(40000) - throws InsufficientFundsException (balance is 35000, shortfall is 5000)
        try {
            service.processPayment(40000);
        } catch (PaymentException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }

        // 5. processPayment(10000) - should succeed (balance remains 35000, new balance becomes 25000)
        try {
            service.processPayment(10000);
        } catch (PaymentException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("Attempt complete.");
        }
    }
}

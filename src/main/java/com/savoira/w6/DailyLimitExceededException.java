package com.savoira.w6;

/**
 * Exception thrown when a payment amount exceeds the daily transaction limit.
 */
public class DailyLimitExceededException extends PaymentException {
    private final double attemptedAmount;

    public DailyLimitExceededException(String message, double attemptedAmount) {
        super(message);
        this.attemptedAmount = attemptedAmount;
    }

    public double getAttemptedAmount() {
        return attemptedAmount;
    }
}

package com.savoira.w6;

/**
 * Exception thrown when the payment amount exceeds the available account balance.
 */
public class InsufficientFundsException extends PaymentException {
    private final double shortfall;

    public InsufficientFundsException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}

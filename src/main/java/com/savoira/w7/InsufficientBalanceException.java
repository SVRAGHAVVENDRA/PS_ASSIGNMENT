package com.savoira.w7;

/**
 * Thrown when the account balance is insufficient to cover the requested withdrawal.
 */
public class InsufficientBalanceException extends ATMException {
    private final double shortfall;

    public InsufficientBalanceException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}


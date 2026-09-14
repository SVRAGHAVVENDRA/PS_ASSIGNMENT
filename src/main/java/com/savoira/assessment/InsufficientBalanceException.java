package com.savoira.assessment;

/**
 * Thrown when an ATM withdrawal request exceeds the customer's available ledger balance.
 */
public class InsufficientBalanceException extends ATMException {
    private final double shortfall;

    public InsufficientBalanceException(String message) {
        super(message);
        this.shortfall = 0.0;
    }

    public InsufficientBalanceException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}

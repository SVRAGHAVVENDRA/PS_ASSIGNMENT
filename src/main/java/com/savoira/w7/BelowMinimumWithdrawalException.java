package com.savoira.w7;

/**
 * Thrown when the requested withdrawal amount is below the minimum allowed limit (Rs.500).
 */
public class BelowMinimumWithdrawalException extends ATMException {
    public BelowMinimumWithdrawalException(String message) {
        super(message);
    }
}


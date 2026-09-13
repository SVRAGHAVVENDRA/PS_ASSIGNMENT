package com.savoira.assessment;

/**
 * Thrown when an ATM withdrawal request is less than the minimum permitted amount (₹500).
 */
public class BelowMinimumWithdrawalException extends ATMException {
    public BelowMinimumWithdrawalException(String message) {
        super(message);
    }
}

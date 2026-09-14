package com.savoira.assessment;

/**
 * Thrown when an ATM withdrawal request exceeds the single transaction limit (₹20,000).
 */
public class AboveMaximumWithdrawalException extends ATMException {
    public AboveMaximumWithdrawalException(String message) {
        super(message);
    }
}

package com.savoira.w7;

/**
 * Thrown when the requested withdrawal amount exceeds the maximum allowed limit (Rs.20,000).
 */
public class AboveMaximumWithdrawalException extends ATMException {
    public AboveMaximumWithdrawalException(String message) {
        super(message);
    }
}


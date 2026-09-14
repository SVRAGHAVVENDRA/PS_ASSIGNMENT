package com.savoira.assessment;

/**
 * Base checked exception for all ATM operations within Meridian Retail Bank.
 */
public class ATMException extends Exception {
    public ATMException(String message) {
        super(message);
    }
}

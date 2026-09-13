package com.savoira.assessment;

/**
 * Thrown when an ATM withdrawal request is not a multiple of the dispense denomination (₹500).
 */
public class InvalidDenominationException extends ATMException {
    public InvalidDenominationException(String message) {
        super(message);
    }
}

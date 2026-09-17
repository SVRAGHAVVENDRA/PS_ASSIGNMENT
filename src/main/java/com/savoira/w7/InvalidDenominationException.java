package com.savoira.w7;

/**
 * Thrown when the requested withdrawal amount is not a multiple of Rs.500.
 */
public class InvalidDenominationException extends ATMException {
    public InvalidDenominationException(String message) {
        super(message);
    }
}


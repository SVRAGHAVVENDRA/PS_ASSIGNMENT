package com.savoira.w7;

/**
 * Base custom unchecked exception for ATM withdrawal operations.
 */
public class ATMException extends RuntimeException {
    public ATMException(String message) {
        super(message);
    }

    public ATMException(String message, Throwable cause) {
        super(message, cause);
    }
}


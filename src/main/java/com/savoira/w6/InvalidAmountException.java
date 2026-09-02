package com.savoira.w6;

/**
 * Exception thrown when a payment amount is non-positive or invalid.
 */
public class InvalidAmountException extends PaymentException {
    public InvalidAmountException(String message) {
        super(message);
    }
}

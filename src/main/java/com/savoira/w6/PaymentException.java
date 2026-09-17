package com.savoira.w6;

/**
 * Base custom unchecked exception for payment operations.
 */
public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}

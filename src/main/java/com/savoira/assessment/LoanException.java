package com.savoira.assessment;

/**
 * Section C Task C1: Base runtime exception for loan processing.
 */
public class LoanException extends RuntimeException {
    public LoanException(String message) {
        super(message);
    }

    public LoanException(String message, Throwable cause) {
        super(message, cause);
    }
}

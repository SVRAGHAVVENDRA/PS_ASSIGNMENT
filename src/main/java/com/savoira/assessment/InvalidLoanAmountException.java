package com.savoira.assessment;

/**
 * Task C1.1: Exception thrown when the requested loan amount is non-positive or exceeds maximum ceiling.
 */
public class InvalidLoanAmountException extends LoanException {
    public InvalidLoanAmountException(String message) {
        super(message);
    }
}

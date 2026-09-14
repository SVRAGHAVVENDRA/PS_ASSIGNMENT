package com.savoira.assessment;

/**
 * Task C1.1: Exception thrown when applicant credit score falls below the required threshold.
 */
public class CreditScoreBelowThresholdException extends LoanException {
    public CreditScoreBelowThresholdException(String message) {
        super(message);
    }
}

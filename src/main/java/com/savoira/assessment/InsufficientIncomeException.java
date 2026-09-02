package com.savoira.assessment;

/**
 * Task C1.2: Exception thrown when applicant income is below the required threshold.
 * Stores the shortfall (income required minus income provided).
 */
public class InsufficientIncomeException extends LoanException {
    private final double shortfall;

    public InsufficientIncomeException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}

package com.savoira.assessment;

/**
 * Task A1.3: EducationLoan with a 6-month moratorium period.
 * EMI uses PersonalLoan formula, and totalRepayable() adds 6 months
 * of simple interest on top.
 */
public class EducationLoan extends Loan {
    private static final int MORATORIUM_MONTHS = 6;

    public EducationLoan(String loanId, String applicantName, double principalAmount, double annualRate, int tenureMonths) {
        super(loanId, applicantName, principalAmount, annualRate, tenureMonths);
    }

    /**
     * EMI = PersonalLoan flat simple interest formula.
     */
    @Override
    public double calculateEMI() {
        double totalInterest = principalAmount * (annualRate / 100.0) * (tenureMonths / 12.0);
        return (principalAmount + totalInterest) / tenureMonths;
    }

    /**
     * Adds 6 months of simple interest accrued during the moratorium to the total repayable amount.
     */
    @Override
    public double totalRepayable() {
        double regularRepayable = calculateEMI() * tenureMonths;
        double moratoriumInterest = principalAmount * (annualRate / 100.0) * (MORATORIUM_MONTHS / 12.0);
        return regularRepayable + moratoriumInterest;
    }
}

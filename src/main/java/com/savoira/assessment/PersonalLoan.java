package com.savoira.assessment;

/**
 * Task A1.2: PersonalLoan using flat-rate simple interest EMI formula.
 */
public class PersonalLoan extends Loan {

    public PersonalLoan(String loanId, String applicantName, double principalAmount, double annualRate, int tenureMonths) {
        super(loanId, applicantName, principalAmount, annualRate, tenureMonths);
    }

    /**
     * EMI = (principal + (principal * annualRate/100 * tenureMonths/12)) / tenureMonths
     */
    @Override
    public double calculateEMI() {
        double totalInterest = principalAmount * (annualRate / 100.0) * (tenureMonths / 12.0);
        return (principalAmount + totalInterest) / tenureMonths;
    }
}

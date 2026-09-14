package com.savoira.assessment;

/**
 * Task A1.1 & Task B1.1: HomeLoan implementing reducing balance EMI,
 * as well as Auditable and Exportable interfaces.
 */
public class HomeLoan extends Loan implements Auditable, Exportable {

    public HomeLoan(String loanId, String applicantName, double principalAmount, double annualRate, int tenureMonths) {
        super(loanId, applicantName, principalAmount, annualRate, tenureMonths);
    }

    /**
     * EMI = P * r * (1+r)^n / ((1+r)^n - 1)
     * where r = annualRate/12/100 and n = tenureMonths
     */
    @Override
    public double calculateEMI() {
        double monthlyRate = annualRate / 12.0 / 100.0;
        if (monthlyRate == 0.0) {
            return principalAmount / tenureMonths;
        }
        double factor = Math.pow(1.0 + monthlyRate, tenureMonths);
        return (principalAmount * monthlyRate * factor) / (factor - 1.0);
    }

    /**
     * Task B1.2: Returns '[AUDIT] LoanId=X | Applicant=Y | Amount=Z | Status=ACTIVE'
     */
    @Override
    public String getAuditLog() {
        return getAuditPrefix() + String.format("LoanId=%s | Applicant=%s | Amount=%.2f | Status=ACTIVE",
                loanId, applicantName, principalAmount);
    }

    /**
     * Task B1.3: Returns 'loanId,applicantName,principalAmount,annualRate,tenureMonths,emi'
     */
    @Override
    public String toCSVRow() {
        return String.format("%s,%s,%.2f,%.2f,%d,%.2f",
                loanId, applicantName, principalAmount, annualRate, tenureMonths, calculateEMI());
    }
}

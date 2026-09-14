package com.savoira.assessment;

import java.util.Objects;

/**
 * Section A Task A1: Abstract base class representing a bank loan.
 * Implements case-insensitive equals() and hashCode() as required by Task B2.
 */
public abstract class Loan {
    protected final String loanId;
    protected final String applicantName;
    protected final double principalAmount;
    protected final double annualRate;
    protected final int tenureMonths;

    public Loan(String loanId, String applicantName, double principalAmount, double annualRate, int tenureMonths) {
        this.loanId = loanId;
        this.applicantName = applicantName;
        this.principalAmount = principalAmount;
        this.annualRate = annualRate;
        this.tenureMonths = tenureMonths;
    }

    /**
     * Abstract EMI computation customized by loan type.
     */
    public abstract double calculateEMI();

    /**
     * Concrete calculation shared across all loan types.
     */
    public double totalRepayable() {
        return calculateEMI() * tenureMonths;
    }

    /**
     * Prints loan summary rounded to 2 decimal places.
     */
    public void printSummary() {
        System.out.println("----------------------------------------");
        System.out.println("Loan ID         : " + loanId);
        System.out.println("Applicant Name  : " + applicantName);
        System.out.printf("Principal Amount: Rs.%.2f\n", principalAmount);
        System.out.printf("Annual Rate     : %.2f%%\n", annualRate);
        System.out.println("Tenure (Months) : " + tenureMonths);
        System.out.printf("Monthly EMI     : Rs.%.2f\n", calculateEMI());
        System.out.printf("Total Repayable : Rs.%.2f\n", totalRepayable());
    }

    /**
     * Task B2: Two Loan objects are equal if and only if their loanId values match (case-insensitive).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Loan other)) return false;
        return loanId != null && loanId.equalsIgnoreCase(other.loanId);
    }

    @Override
    public int hashCode() {
        return loanId == null ? 0 : loanId.toUpperCase().hashCode();
    }

    public String getLoanId() {
        return loanId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public double getAnnualRate() {
        return annualRate;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }
}

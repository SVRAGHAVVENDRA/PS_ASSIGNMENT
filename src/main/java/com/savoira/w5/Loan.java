package com.savoira.w5;

/**
 * Abstract class representing a generic Loan.
 * Part of Week 5 Assignment 1.
 */
public abstract class Loan {
    protected String loanId;
    protected String applicantName;
    protected double principal;
    protected double annualRate;

    /**
     * Constructor initializing all four protected fields.
     */
    public Loan(String loanId, String applicantName, double principal, double annualRate) {
        this.loanId = loanId;
        this.applicantName = applicantName;
        this.principal = principal;
        this.annualRate = annualRate;
    }

    /**
     * Calculates the monthly EMI for the loan.
     * @return the calculated EMI amount
     */
    public abstract double calculateEMI();

    /**
     * Identifies the type of loan (e.g., Home Loan, Personal Loan).
     * @return a String representation of the loan type
     */
    public abstract String loanType();

    /**
     * Prints a summary of the loan details, rounding monetary outputs to 2 decimal places.
     */
    public void printSummary() {
        System.out.println("----------------------------------------");
        System.out.println("Loan Type      : " + loanType());
        System.out.println("Loan ID        : " + loanId);
        System.out.println("Applicant Name : " + applicantName);
        System.out.printf("Principal      : Rs.%.2f\n", principal);
        System.out.printf("Annual Rate    : %.2f%%\n", annualRate);
    }

    // Getters for encapsulation and code-smell refactoring later
    public String getLoanId() {
        return loanId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getAnnualRate() {
        return annualRate;
    }
}

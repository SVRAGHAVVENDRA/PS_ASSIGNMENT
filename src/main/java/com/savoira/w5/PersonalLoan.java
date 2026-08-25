package com.savoira.w5;

/**
 * Class representing a Personal Loan.
 * Part of Week 5 Assignment 1.
 */
public class PersonalLoan extends Loan implements Exportable {
    private int tenureMonths;

    /**
     * Constructor for PersonalLoan.
     */
    public PersonalLoan(String loanId, String applicantName, double principal, double annualRate, int tenureMonths) {
        super(loanId, applicantName, principal, annualRate);
        this.tenureMonths = tenureMonths;
    }

    /**
     * Implements calculateEMI using flat-rate simple interest.
     */
    @Override
    public double calculateEMI() {
        double totalInterest = principal * (annualRate / 100.0) * (tenureMonths / 12.0);
        return (principal + totalInterest) / tenureMonths;
    }

    /**
     * Returns the type of loan.
     */
    @Override
    public String loanType() {
        return "Personal Loan";
    }

    /**
     * Prints the summary including the Personal Loan specific fields.
     */
    @Override
    public void printSummary() {
        super.printSummary();
        System.out.println("Tenure         : " + tenureMonths + " months");
        System.out.printf("Monthly EMI    : Rs.%.2f\n", calculateEMI());
        System.out.println("----------------------------------------");
    }

    /**
     * Implementation of Exportable interface.
     * Returns a CSV representation of all PersonalLoan fields: loanId,applicantName,principal,annualRate,tenureMonths,emi
     */
    @Override
    public String toCSVRow() {
        return String.format("%s,%s,%.2f,%.2f,%d,%.2f",
                loanId, applicantName, principal, annualRate, tenureMonths, calculateEMI());
    }

    // Getter for tenure
    public int getTenureMonths() {
        return tenureMonths;
    }
}

package com.savoira.w5;

/**
 * Class representing a Home Loan.
 * Part of Week 5 Assignment 1.
 */
public class HomeLoan extends Loan implements Auditable {
    private int tenureMonths;

    /**
     * Constructor for HomeLoan.
     */
    public HomeLoan(String loanId, String applicantName, double principal, double annualRate, int tenureMonths) {
        super(loanId, applicantName, principal, annualRate);
        this.tenureMonths = tenureMonths;
    }

    /**
     * Implements calculateEMI using standard compound interest formula.
     */
    @Override
    public double calculateEMI() {
        double r = annualRate / 12.0 / 100.0; // monthly rate as decimal
        int n = tenureMonths;
        if (r == 0) {
            return principal / n;
        }
        return (principal * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }

    /**
     * Returns the type of loan.
     */
    @Override
    public String loanType() {
        return "Home Loan";
    }

    /**
     * Prints the summary including the Home Loan specific fields.
     */
    @Override
    public void printSummary() {
        super.printSummary();
        System.out.println("Tenure         : " + tenureMonths + " months");
        System.out.printf("Monthly EMI    : Rs.%.2f\n", calculateEMI());
        System.out.println("----------------------------------------");
    }

    /**
     * Prints the audit summary of the home loan.
     */
    @Override
    public void auditSummary() {
        System.out.printf("%s%s | %s | Rs.%.2f | Rate:%.2f%%\n",
                auditPrefix(), loanId, applicantName, principal, annualRate);
    }

    // Getter for tenure
    public int getTenureMonths() {
        return tenureMonths;
    }
}

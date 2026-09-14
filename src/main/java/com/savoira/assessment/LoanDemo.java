package com.savoira.assessment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Demonstrates Section A (Loan hierarchy & polymorphism),
 * Section B1 (Auditable & Exportable interfaces), and
 * Section B2 (equals and hashCode contract with HashSet deduplication).
 */
public class LoanDemo {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Section A: OOP Pillars - Inheritance & Polymorphism");
        System.out.println("==================================================");

        // Task A1.4: Create one of each loan type and store them in List<Loan>
        List<Loan> loanPortfolio = new ArrayList<>();
        loanPortfolio.add(new HomeLoan("HL-101", "Aarav Sharma", 4500000.00, 8.50, 240)); // 20 years
        loanPortfolio.add(new PersonalLoan("PL-202", "Pooja Patel", 350000.00, 11.50, 36)); // 3 years
        loanPortfolio.add(new EducationLoan("EL-303", "Rohan Verma", 800000.00, 9.00, 60)); // 5 years with 6-month moratorium

        // Iterate and call printSummary() on each - demonstrating runtime polymorphism
        System.out.println("Demonstrating Runtime Polymorphism across Loan Portfolio:");
        for (Loan loan : loanPortfolio) {
            loan.printSummary();
        }

        System.out.println("\n==================================================");
        System.out.println("Section B: Interfaces & Object Contract");
        System.out.println("==================================================");

        // Task B1.4: Interface references
        System.out.println("--- Task B1: Interface References Demonstration ---");
        Auditable auditable = new HomeLoan("HL-501", "Kavita Rao", 5200000.00, 8.25, 180);
        Exportable exportable = new HomeLoan("HL-502", "Siddharth Nair", 3800000.00, 8.40, 120);

        System.out.println("Auditable.getAuditLog():");
        System.out.println(auditable.getAuditLog());

        System.out.println("\nExportable.toCSVRow():");
        System.out.println(exportable.toCSVRow());

        // Task B2: equals() and hashCode() contract verification
        System.out.println("\n--- Task B2: equals() and hashCode() HashSet Verification ---");
        HomeLoan loan1 = new HomeLoan("HL-999", "Vikram Malhotra", 3000000.00, 8.75, 120);
        HomeLoan loan2 = new HomeLoan("hl-999", "Vikram M.", 3000000.00, 8.75, 120); // matching ID in lower case

        System.out.println("loan1 ID: " + loan1.getLoanId() + ", applicant: " + loan1.getApplicantName());
        System.out.println("loan2 ID: " + loan2.getLoanId() + ", applicant: " + loan2.getApplicantName());
        System.out.println("loan1.equals(loan2) [case-insensitive ID check]: " + loan1.equals(loan2));

        Set<Loan> loanSet = new HashSet<>();
        loanSet.add(loan1);
        loanSet.add(loan2);

        /*
         * EXPLANATION OF RESULT:
         * Because Loan overrides equals() and hashCode() to compare loanId case-insensitively,
         * both loan1 ("HL-999") and loan2 ("hl-999") evaluate to identical hash buckets and equal state.
         * The HashSet detects the collision and avoids adding the duplicate, retaining exactly 1 unique element.
         */
        System.out.println("HashSet size after adding both instances: " + loanSet.size());
        if (loanSet.size() == 1) {
            System.out.println("Verification Passed: HashSet successfully deduplicated identical loanId instances.");
        } else {
            System.out.println("Verification Failed: Expected size 1, but found " + loanSet.size());
        }
    }
}

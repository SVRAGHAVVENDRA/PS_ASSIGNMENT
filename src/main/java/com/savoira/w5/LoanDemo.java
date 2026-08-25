package com.savoira.w5;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo runner for Week 5 Assignment 1.
 */
public class LoanDemo {
    public static void main(String[] args) {
        System.out.println("=== Assignment 1: Inheritance & Polymorphism ===");

        List<Loan> loans = new ArrayList<>();

        // Create one HomeLoan and two PersonalLoans with realistic values
        loans.add(new HomeLoan("HL1001", "Alice Smith", 5000000.0, 8.5, 240)); // 20 years
        loans.add(new PersonalLoan("PL2001", "Bob Johnson", 300000.0, 12.0, 36));  // 3 years
        loans.add(new PersonalLoan("PL2002", "Charlie Brown", 150000.0, 14.5, 24)); // 2 years

        // Iterate the list and call printSummary() on each - demonstrating runtime polymorphism
        for (Loan loan : loans) {
            System.out.println("Polymorphic Loan Type Check: This is a " + loan.loanType());
            loan.printSummary();
        }
    }
}

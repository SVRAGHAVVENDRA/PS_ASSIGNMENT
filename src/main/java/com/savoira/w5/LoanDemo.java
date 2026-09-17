package com.savoira.w5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Demo runner for Week 5 Assignment 1 and Assignment 2.
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

        System.out.println("\n=== Assignment 2: Interfaces & Object Contract ===");

        // 4. Auditable reference pointing to a HomeLoan - call auditSummary() through it
        System.out.println("--- 4a. Auditable Reference Demo ---");
        Auditable auditableLoan = new HomeLoan("HL1002", "Diana Prince", 4500000.0, 7.8, 180);
        auditableLoan.auditSummary();

        // 4. Exportable reference pointing to a PersonalLoan - call toCSVRow() through it
        System.out.println("\n--- 4b. Exportable Reference Demo ---");
        Exportable exportableLoan = new PersonalLoan("PL2003", "Evan Wright", 250000.0, 11.5, 12);
        System.out.println("CSV Export Row: " + exportableLoan.toCSVRow());

        // 5. Override equals() and hashCode() verify HashSet entry count
        System.out.println("\n--- 5. Equals & HashCode HashSet Verification ---");
        // Create two HomeLoan objects with the same loanId (case-insensitive: "HL1001" and "hl1001")
        HomeLoan loanA = new HomeLoan("HL1001", "Alice Smith", 5000000.0, 8.5, 240);
        HomeLoan loanB = new HomeLoan("hl1001", "Alice S.", 5000000.0, 8.5, 240); // different case, same ID

        System.out.println("loanA equals loanB (same ID case-insensitive)? " + loanA.equals(loanB));

        Set<Loan> loanSet = new HashSet<>();
        loanSet.add(loanA);
        loanSet.add(loanB);

        System.out.println("HashSet size after adding both: " + loanSet.size());
        if (loanSet.size() == 1) {
            System.out.println("SUCCESS: HashSet correctly contains only 1 entry because their loanIds match case-insensitively.");
        } else {
            System.out.println("FAILURE: HashSet contains " + loanSet.size() + " entries.");
        }
    }

    /*
     * =========================================================================
     * TASK 6: Code Smells Analysis and Refactoring
     * =========================================================================
     * Original Snippet:
     * void process(List<Loan> l) {
     *     for(int i=0;i<l.size();i++){
     *         if(l.get(i).principal>500000&&l.get(i).principal<2000000)
     *             System.out.println(l.get(i).loanId);
     *     }
     * }
     *
     * Identified Code Smells:
     * 1. Feature Envy / Violation of Encapsulation (Direct Field Access):
     *    The method directly accesses `principal` and `loanId` fields from the Loan object
     *    rather than calling getters or encapsulating the check within the Loan class itself.
     * 2. Magic Numbers:
     *    `500000` and `2000000` are hardcoded literals. They should be defined as named constants.
     * 3. Inefficient List Iteration:
     *    Using `l.get(i)` inside a standard loop is inefficient (O(N^2)) if the list is a LinkedList.
     *    It should use an enhanced for-loop (for-each) or Stream API.
     *
     * Refactored Method:
     */
    private static final double MIN_AUDIT_LIMIT = 500000.0;
    private static final double MAX_AUDIT_LIMIT = 2000000.0;

    public void process(List<Loan> loans) {
        if (loans == null) return;
        for (Loan loan : loans) {
            // Check range using constant bounds and encapsulated logic/getters
            if (loan.getPrincipal() > MIN_AUDIT_LIMIT && loan.getPrincipal() < MAX_AUDIT_LIMIT) {
                System.out.println(loan.getLoanId());
            }
        }
    }
}

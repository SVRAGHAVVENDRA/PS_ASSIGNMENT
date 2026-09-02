package com.savoira.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Task D1: Demonstrates comprehensive collection usage with List, Map, and Set.
 */
public class LoanPortfolio {

    public static void main(String[] args) {
        System.out.println("=== Section D Task D1: LoanPortfolio Collections Usage ===");

        // D1.1 Use a List<Loan> to store all loans. Add at least three loans.
        List<Loan> loans = new ArrayList<>();
        Loan loan1 = new HomeLoan("HL-101", "Priya Nair", 4000000.0, 8.5, 240);
        Loan loan2 = new PersonalLoan("PL-202", "Devendra Singh", 250000.0, 12.0, 36);
        Loan loan3 = new EducationLoan("EL-303", "Ananya Sen", 600000.0, 9.5, 48);
        Loan loan4 = new PersonalLoan("PL-204", "Priya Nair", 150000.0, 13.0, 24); // Same applicant name as loan1

        loans.add(loan1);
        loans.add(loan2);
        loans.add(loan3);
        loans.add(loan4);
        System.out.println("Total loans added to List: " + loans.size());

        // D1.2 Use a Map<String, Loan> (key = loanId) to allow fast lookup.
        Map<String, Loan> loanMap = new HashMap<>();
        for (Loan loan : loans) {
            loanMap.put(loan.getLoanId(), loan);
        }

        System.out.println("\n--- Task D1.2: Map Lookup Demonstration ---");
        // Successful lookup
        String searchIdSuccess = "PL-202";
        Loan foundLoan = loanMap.get(searchIdSuccess);
        if (foundLoan != null) {
            System.out.printf("SUCCESSFUL LOOKUP: Key '%s' found -> Applicant: %s, Principal: Rs.%.2f\n",
                    searchIdSuccess, foundLoan.getApplicantName(), foundLoan.getPrincipalAmount());
        } else {
            System.out.println("Lookup failed for key: " + searchIdSuccess);
        }

        // Failed lookup
        String searchIdFail = "HL-999";
        Loan missingLoan = loanMap.get(searchIdFail);
        if (missingLoan != null) {
            System.out.println("Found: " + missingLoan.getApplicantName());
        } else {
            System.out.printf("FAILED LOOKUP: Key '%s' does not exist in the portfolio map.\n", searchIdFail);
        }

        // D1.3 Use a Set<String> to track unique applicant names from the portfolio. Print the count.
        System.out.println("\n--- Task D1.3: Set for Unique Applicant Names ---");
        Set<String> uniqueApplicants = new HashSet<>();
        for (Loan loan : loans) {
            uniqueApplicants.add(loan.getApplicantName());
        }
        System.out.println("Unique Applicants Count: " + uniqueApplicants.size());
        System.out.println("Unique Applicants List : " + uniqueApplicants);

        // D1.4 Iterate the list using both a for-each loop and an Iterator.
        System.out.println("\n--- Task D1.4: Iteration via Enhanced For-Each Loop ---");
        for (Loan loan : loans) {
            System.out.printf("[For-Each] ID: %-7s | Applicant: %-15s | Monthly EMI: Rs.%.2f\n",
                    loan.getLoanId(), loan.getApplicantName(), loan.calculateEMI());
        }

        System.out.println("\n--- Task D1.4: Iteration via java.util.Iterator ---");
        Iterator<Loan> iterator = loans.iterator();
        while (iterator.hasNext()) {
            Loan loan = iterator.next();
            System.out.printf("[Iterator] ID: %-7s | Applicant: %-15s | Total Repayable: Rs.%.2f\n",
                    loan.getLoanId(), loan.getApplicantName(), loan.totalRepayable());
        }
    }
}

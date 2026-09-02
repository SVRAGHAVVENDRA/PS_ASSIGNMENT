package com.savoira.assessment;

/**
 * Task A2: Method overloading demonstration for loan eligibility checks.
 */
public class LoanCalculator {

    /**
     * Overload 1: Eligible if monthlyIncome > 25000.
     */
    public boolean assessEligibility(double monthlyIncome) {
        boolean eligible = monthlyIncome > 25000.0;
        if (eligible) {
            System.out.printf("[Overload 1] Income: Rs.%.2f -> ELIGIBLE (Monthly income exceeds Rs.25,000 threshold).\n", monthlyIncome);
        } else {
            System.out.printf("[Overload 1] Income: Rs.%.2f -> NOT ELIGIBLE (Monthly income must be greater than Rs.25,000).\n", monthlyIncome);
        }
        return eligible;
    }

    /**
     * Overload 2: Eligible if (monthlyIncome - existingEMI) > 20000.
     */
    public boolean assessEligibility(double monthlyIncome, double existingEMI) {
        double disposableIncome = monthlyIncome - existingEMI;
        boolean eligible = disposableIncome > 20000.0;
        if (eligible) {
            System.out.printf("[Overload 2] Income: Rs.%.2f, Existing EMI: Rs.%.2f (Net: Rs.%.2f) -> ELIGIBLE (Net disposable income exceeds Rs.20,000).\n",
                    monthlyIncome, existingEMI, disposableIncome);
        } else {
            System.out.printf("[Overload 2] Income: Rs.%.2f, Existing EMI: Rs.%.2f (Net: Rs.%.2f) -> NOT ELIGIBLE (Net disposable income must be greater than Rs.20,000).\n",
                    monthlyIncome, existingEMI, disposableIncome);
        }
        return eligible;
    }

    /**
     * Overload 3: Eligible if previous conditions AND creditScore > 650.
     */
    public boolean assessEligibility(double monthlyIncome, double existingEMI, int creditScore) {
        double disposableIncome = monthlyIncome - existingEMI;
        boolean incomeOk = disposableIncome > 20000.0;
        boolean creditOk = creditScore > 650;
        boolean eligible = incomeOk && creditOk;

        if (eligible) {
            System.out.printf("[Overload 3] Income: Rs.%.2f, EMI: Rs.%.2f, Credit Score: %d -> ELIGIBLE (Net income > Rs.20k and Credit Score > 650).\n",
                    monthlyIncome, existingEMI, creditScore);
        } else {
            StringBuilder reason = new StringBuilder();
            if (!incomeOk) reason.append("Net income <= Rs.20,000; ");
            if (!creditOk) reason.append("Credit score ").append(creditScore).append(" <= 650; ");
            System.out.printf("[Overload 3] Income: Rs.%.2f, EMI: Rs.%.2f, Credit Score: %d -> NOT ELIGIBLE (%s).\n",
                    monthlyIncome, existingEMI, creditScore, reason.toString().trim());
        }
        return eligible;
    }

    public static void main(String[] args) {
        System.out.println("=== Task A2: LoanCalculator Method Overloading Demonstration ===");
        LoanCalculator calculator = new LoanCalculator();

        System.out.println("\n--- Testing Overload 1 (Income only) ---");
        calculator.assessEligibility(32000.0);
        calculator.assessEligibility(22000.0);

        System.out.println("\n--- Testing Overload 2 (Income + Existing EMI) ---");
        calculator.assessEligibility(45000.0, 15000.0); // 30000 net > 20000 -> eligible
        calculator.assessEligibility(35000.0, 18000.0); // 17000 net <= 20000 -> not eligible

        System.out.println("\n--- Testing Overload 3 (Income + Existing EMI + Credit Score) ---");
        calculator.assessEligibility(50000.0, 10000.0, 750); // net 40k, score 750 -> eligible
        calculator.assessEligibility(50000.0, 10000.0, 620); // net 40k, score 620 -> not eligible
        calculator.assessEligibility(28000.0, 12000.0, 720); // net 16k <= 20k -> not eligible
    }
}

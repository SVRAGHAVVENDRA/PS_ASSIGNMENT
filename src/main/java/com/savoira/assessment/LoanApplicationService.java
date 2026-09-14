package com.savoira.assessment;

/**
 * Task C1.3 & C1.4: Loan application service enforcing business validation rules
 * and throwing specialized domain exceptions.
 */
public class LoanApplicationService {
    public static final double MAX_LOAN_CEILING = 5_000_000.0;
    public static final int MIN_CREDIT_SCORE = 650;
    public static final double MIN_INCOME_RATIO = 0.10;

    /**
     * Evaluates a loan application and returns 'APPROVED' if all criteria are met.
     *
     * @param income      monthly applicant income
     * @param loanAmount  requested loan principal
     * @param creditScore applicant credit score
     * @return 'APPROVED' when all checks pass
     * @throws InvalidLoanAmountException           if loanAmount <= 0 or > 5,000,000
     * @throws InsufficientIncomeException          if income < loanAmount * 0.10
     * @throws CreditScoreBelowThresholdException  if creditScore < 650
     */
    public String applyForLoan(double income, double loanAmount, int creditScore) {
        if (loanAmount <= 0 || loanAmount > MAX_LOAN_CEILING) {
            throw new InvalidLoanAmountException(String.format(
                    "Invalid loan amount requested: Rs.%.2f. Amount must be positive and not exceed Rs.%.2f.",
                    loanAmount, MAX_LOAN_CEILING));
        }

        double requiredIncome = loanAmount * MIN_INCOME_RATIO;
        if (income < requiredIncome) {
            double shortfall = requiredIncome - income;
            throw new InsufficientIncomeException(String.format(
                    "Insufficient income. Required: Rs.%.2f (10%% of loan), Provided: Rs.%.2f, Shortfall: Rs.%.2f.",
                    requiredIncome, income, shortfall), shortfall);
        }

        if (creditScore < MIN_CREDIT_SCORE) {
            throw new CreditScoreBelowThresholdException(String.format(
                    "Credit score %d is below the minimum required threshold of %d.",
                    creditScore, MIN_CREDIT_SCORE));
        }

        return "APPROVED";
    }

    public static void main(String[] args) {
        System.out.println("=== Task C1: LoanApplicationService Test Scenarios ===");
        LoanApplicationService service = new LoanApplicationService();

        // Scenario 1: All pass -> Expected: APPROVED
        System.out.println("\n[Scenario 1: All Valid Inputs]");
        try {
            String result = service.applyForLoan(60000.0, 500000.0, 720);
            System.out.println("Result: " + result);
        } catch (LoanException e) {
            System.err.println("Unexpected failure: " + e.getMessage());
        } finally {
            System.out.println("Evaluation 1 complete.");
        }

        // Scenario 2: Income too low -> Expected: InsufficientIncomeException
        System.out.println("\n[Scenario 2: Income Below Threshold]");
        try {
            // Requested: 1,000,000 -> Required income: 100,000. Provided: 60,000 -> Shortfall: 40,000
            String result = service.applyForLoan(60000.0, 1000000.0, 750);
            System.out.println("Result: " + result);
        } catch (InsufficientIncomeException e) {
            System.out.println("Caught InsufficientIncomeException: " + e.getMessage());
            System.out.printf("-> Verified Shortfall: Rs.%.2f\n", e.getShortfall());
        } catch (LoanException e) {
            System.err.println("Caught unexpected LoanException: " + e.getMessage());
        } finally {
            System.out.println("Evaluation 2 complete.");
        }

        // Scenario 3: Loan amount invalid -> Expected: InvalidLoanAmountException
        System.out.println("\n[Scenario 3: Loan Amount Exceeds Maximum Ceiling]");
        try {
            // Requested: 6,000,000 > 5,000,000
            String result = service.applyForLoan(100000.0, 6000000.0, 780);
            System.out.println("Result: " + result);
        } catch (InvalidLoanAmountException e) {
            System.out.println("Caught InvalidLoanAmountException: " + e.getMessage());
        } catch (LoanException e) {
            System.err.println("Caught unexpected LoanException: " + e.getMessage());
        } finally {
            System.out.println("Evaluation 3 complete.");
        }

        // Scenario 4: Credit score too low -> Expected: CreditScoreBelowThresholdException
        System.out.println("\n[Scenario 4: Credit Score Below Threshold]");
        try {
            // Credit score: 580 < 650
            String result = service.applyForLoan(50000.0, 300000.0, 580);
            System.out.println("Result: " + result);
        } catch (CreditScoreBelowThresholdException e) {
            System.out.println("Caught CreditScoreBelowThresholdException: " + e.getMessage());
        } catch (LoanException e) {
            System.err.println("Caught unexpected LoanException: " + e.getMessage());
        } finally {
            System.out.println("Evaluation 4 complete.");
        }
    }
}

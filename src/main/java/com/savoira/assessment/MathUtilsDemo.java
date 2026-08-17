package com.savoira.assessment;

/**
 * Task D1.4: MathUtils Demo Class
 * Runs and prints results of rounding, simple interest, and compound interest calculations.
 */
public class MathUtilsDemo {
    public static void main(String[] args) {
        System.out.println("=== Task D1: MathUtils Demonstration ===");

        double principal = 15000.0;
        double rate = 5.5; // 5.5% annual rate
        double time = 3.5; // 3.5 years
        int compoundingFrequency = 4; // Quarterly compounding

        // 1. Calculate Simple Interest
        double simpleInterest = MathUtils.calculateSimpleInterest(principal, rate, time);
        double roundedSimpleInterest = MathUtils.roundToTwoDecimalPlaces(simpleInterest);
        System.out.println("Simple Interest Calculations:");
        System.out.println("  Principal: Rs. " + principal);
        System.out.println("  Annual Rate: " + rate + "%");
        System.out.println("  Time: " + time + " years");
        System.out.println("  Raw Simple Interest: Rs. " + simpleInterest);
        System.out.println("  Rounded Simple Interest: Rs. " + roundedSimpleInterest);

        // 2. Calculate Compound Interest
        double compoundInterest = MathUtils.calculateCompoundInterest(principal, rate, compoundingFrequency, time);
        double roundedCompoundInterest = MathUtils.roundToTwoDecimalPlaces(compoundInterest);
        System.out.println("\nCompound Interest Calculations (Quarterly Compounding):");
        System.out.println("  Principal: Rs. " + principal);
        System.out.println("  Annual Rate: " + rate + "%");
        System.out.println("  Compounding Frequency: " + compoundingFrequency + " times/year");
        System.out.println("  Time: " + time + " years");
        System.out.println("  Raw Compound Interest Earned: Rs. " + compoundInterest);
        System.out.println("  Rounded Compound Interest Earned: Rs. " + roundedCompoundInterest);
        System.out.println("  Total Accumulated Amount: Rs. " + MathUtils.roundToTwoDecimalPlaces(principal + compoundInterest));

        // 3. Test Rounding alone
        double valueToRound = 123.456789;
        System.out.println("\nRounding Utility test:");
        System.out.println("  Value to round: " + valueToRound);
        System.out.println("  Rounded: " + MathUtils.roundToTwoDecimalPlaces(valueToRound));
    }
}

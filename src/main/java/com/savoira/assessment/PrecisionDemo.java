package com.savoira.assessment;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Task C2: Demonstrates double floating-point inaccuracy vs BigDecimal exact precision,
 * followed by compound interest computation for ₹500,000 at 8.5% for 3 years compounded monthly.
 */
public class PrecisionDemo {
    public static void main(String[] args) {
        System.out.println("=== Task C2: Floating-Point Precision vs BigDecimal ===");

        // 1. Show the problem
        System.out.println("\n[1. Floating-Point Inaccuracy with double]");
        double d1 = 0.1 + 0.2;
        System.out.println("0.1 + 0.2 using double: " + d1); // Prints 0.30000000000000004 (NOT 0.3)

        // 2. Show the fix
        System.out.println("\n[2. Exact Precision using BigDecimal]");
        BigDecimal bd1 = new BigDecimal("0.1");
        BigDecimal bd2 = new BigDecimal("0.2");
        System.out.println("0.1 + 0.2 using BigDecimal: " + bd1.add(bd2)); // exactly 0.3

        // 3. Compound interest calculation
        // Loan = ₹500,000, Annual Rate = 8.5%, Time = 3 years, Compounded monthly (12 times/year)
        // Formula: A = P * (1 + r/n)^(n*t)
        System.out.println("\n[3. Monthly Compounding Calculation using BigDecimal]");
        BigDecimal principal = new BigDecimal("500000");
        BigDecimal annualRate = new BigDecimal("0.085");
        int compoundingPerYear = 12;
        int years = 3;
        int totalPeriods = compoundingPerYear * years; // 36 periods

        // Monthly rate = 0.085 / 12
        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(compoundingPerYear), 10, RoundingMode.HALF_UP);
        BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyRate);

        // (1 + r/n)^(n*t)
        BigDecimal compoundFactor = onePlusRate.pow(totalPeriods);

        // Maturity Amount A = P * compoundFactor
        BigDecimal maturityAmount = principal.multiply(compoundFactor).setScale(2, RoundingMode.HALF_UP);
        BigDecimal compoundInterest = maturityAmount.subtract(principal).setScale(2, RoundingMode.HALF_UP);

        System.out.println("Principal Amount   : ₹" + principal);
        System.out.println("Annual Rate        : 8.5%");
        System.out.println("Tenure             : 3 years (" + totalPeriods + " monthly cycles)");
        System.out.println("Maturity Amount (A): ₹" + maturityAmount);
        System.out.println("Compound Interest  : ₹" + compoundInterest);
    }
}

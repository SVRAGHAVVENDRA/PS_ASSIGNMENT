package com.savoira.w6;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Demonstrates floating-point precision issues with double arithmetic
 * and exact financial computations using BigDecimal.
 */
public class PrecisionDemo {
    public static void main(String[] args) {
        System.out.println("=== Task 1: Floating-Point Precision Demonstration ===");

        // 1. Show the problem with double arithmetic
        double d1 = 0.1;
        double d2 = 0.2;
        double doubleSum = d1 + d2;
        System.out.println("Double arithmetic (0.1 + 0.2): " + doubleSum);
        System.out.println("Does 0.1 + 0.2 == 0.3? " + (doubleSum == 0.3));

        // Another classic financial subtraction error with double
        double cash = 2.00;
        double price = 1.10;
        System.out.println("Double subtraction (2.00 - 1.10): " + (cash - price));

        // 2. Show the fix using BigDecimal with String constructor
        System.out.println("\n--- BigDecimal Solution ---");
        BigDecimal bd1 = new BigDecimal("0.1");
        BigDecimal bd2 = new BigDecimal("0.2");
        BigDecimal bdSum = bd1.add(bd2);
        System.out.println("BigDecimal arithmetic (0.1 + 0.2): " + bdSum);
        System.out.println("Does bdSum equals 0.3? " + bdSum.equals(new BigDecimal("0.3")));

        BigDecimal cashBd = new BigDecimal("2.00");
        BigDecimal priceBd = new BigDecimal("1.10");
        BigDecimal changeBd = cashBd.subtract(priceBd);
        System.out.println("BigDecimal subtraction (2.00 - 1.10): " + changeBd);

        // 3. Financial compound interest calculation with BigDecimal
        System.out.println("\n--- Financial Compound Interest with BigDecimal ---");
        // Principal = Rs. 500,000, Annual Rate = 8.5%, Tenure = 3 years, Compounded Monthly
        BigDecimal principal = new BigDecimal("500000.00");
        BigDecimal annualRate = new BigDecimal("0.085");
        int compoundingPeriodsPerYear = 12;
        int years = 3;
        int totalPeriods = compoundingPeriodsPerYear * years;

        // Periodic rate = annualRate / 12
        BigDecimal monthlyRate = annualRate.divide(
                BigDecimal.valueOf(compoundingPeriodsPerYear), 10, RoundingMode.HALF_UP
        );

        // Multiplier = (1 + r/n)^totalPeriods
        BigDecimal base = BigDecimal.ONE.add(monthlyRate);
        BigDecimal compoundFactor = base.pow(totalPeriods);

        // Maturity Amount = Principal * (1 + r/n)^(n*t)
        BigDecimal maturityAmount = principal.multiply(compoundFactor).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalInterest = maturityAmount.subtract(principal).setScale(2, RoundingMode.HALF_UP);

        System.out.println("Principal Amount   : Rs." + principal);
        System.out.println("Annual Rate        : 8.50%");
        System.out.println("Tenure             : 3 years (" + totalPeriods + " monthly compounding cycles)");
        System.out.println("Total Repayable    : Rs." + maturityAmount);
        System.out.println("Compound Interest  : Rs." + totalInterest);
    }
}

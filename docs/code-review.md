# Code Review: Task D2

This document details the code review for the obfuscated compound interest method.

### Original Implementation
```java
public double x(double a, double b, int c) {
    double r = 1;
    for(int i=0;i<c;i++) {r = r*(1+b);}
    return a*r;
}
```

---

## Identified Clean-Code Problems

### 1. Cryptic Identifier Names (Lack of Domain Context)
- **Problem**: The method name `x`, the parameters `a`, `b`, `c`, and the local variable `r` are single-character identifiers that convey zero business logic.
- **Impact**: It is impossible for a developer reading this code to know that the method computes compound interest without parsing the algebraic formula.
- **Remedy**: Rename them to meaningful identifiers:
  - `x` &rarr; `calculateFutureValue` (or `calculateCompoundInterest`)
  - `a` &rarr; `principal`
  - `b` &rarr; `interestRate` (specifically, rate factor per compounding period)
  - `c` &rarr; `compoundingPeriods`
  - `r` &rarr; `interestMultiplier`

### 2. Missing Input Validation (Business Rules Bypass)
- **Problem**: The method contains no defensive programming or checks on arguments.
- **Impact**: Passing a negative principal (`a`), a negative interest rate (`b`), or a negative compounding period (`c`) will compile and execute, returning nonsensical mathematical values that violate basic banking rules.
- **Remedy**: Add precondition validation checks using `IllegalArgumentException` to ensure all inputs are non-negative.

### 3. Missing Code Formatting & Spacing
- **Problem**: The code violates standard Java style guidelines regarding whitespaces. Operators are packed tightly together (e.g., `for(int i=0;i<c;i++)` and `r*(1+b)`).
- **Impact**: Packed code reduces visual scan speed and makes spotting typos (like using `+` instead of `*` or `<` instead of `<=`) more difficult.
- **Remedy**: Apply standard spacing around control statements and binary operators.

### 4. Lack of Documentation & Javadoc
- **Problem**: The method lacks Javadoc or explanation comments.
- **Impact**: Consumers of this method cannot know if the rate `b` expects a decimal factor (e.g. `0.05`) or a percentage (e.g. `5.0`), or what units of time `c` is based on (years, months, etc.).
- **Remedy**: Write a comprehensive Javadoc block detailing the parameter units, returned values, and exception conditions.

### 5. Floating-point Precision Risk (Use of `double` for Money)
- **Problem**: The method uses `double` for currency/monetary accumulation.
- **Impact**: Floating-point types (`double` and `float`) represent numbers in binary formats which cannot accurately represent base-10 fractions (like 0.1). This introduces compounding rounding errors in financial transactions over time.
- **Remedy**: In a production banking environment, currency computations should always use `BigDecimal` rather than `double`. (Note: Since the original method signature specifies `double`, the refactored solution below is provided using clean double logic, but also details how it would look using `BigDecimal` for proper financial compliance).

---

## Clean Code Refactored Implementation

Here is the clean-code rewrite of the method using descriptive identifiers, parameter validation, formatting, and documentation.

### Standard Double Implementation (Clean-Code Compliant)
```java
/**
 * Calculates the future value of an investment using compound interest.
 * 
 * @param principal          the initial principal amount (must be non-negative)
 * @param periodRateFactor   the decimal rate factor per compounding period (e.g., 0.05 for 5%; must be non-negative)
 * @param compoundingPeriods the number of compounding periods (must be non-negative)
 * @return the total accumulated future value (principal + compounded interest)
 * @throws IllegalArgumentException if principal, interest rate, or compounding periods are negative
 */
public double calculateFutureValue(
        final double principal,
        final double periodRateFactor,
        final int compoundingPeriods) {
    
    // 1. Input Validation
    if (principal < 0.0) {
        throw new IllegalArgumentException("Principal cannot be negative.");
    }
    if (periodRateFactor < 0.0) {
        throw new IllegalArgumentException("Interest rate factor cannot be negative.");
    }
    if (compoundingPeriods < 0) {
        throw new IllegalArgumentException("Compounding periods cannot be negative.");
    }

    // 2. Loop calculation with proper formatting
    double interestMultiplier = 1.0;
    for (int period = 0; period < compoundingPeriods; period++) {
        interestMultiplier = interestMultiplier * (1.0 + periodRateFactor);
    }

    // 3. Return final value
    return principal * interestMultiplier;
}
```

### Production Banking Alternative (using `BigDecimal`)
For actual banking applications, the calculation should be refactored as follows:

```java
import java.math.BigDecimal;
import java.math.RoundingMode;

public BigDecimal calculateFutureValueBigDecimal(
        final BigDecimal principal,
        final BigDecimal periodRateFactor,
        final int compoundingPeriods) {
        
    if (principal == null || principal.compareTo(BigDecimal.ZERO) < 0) {
        throw new IllegalArgumentException("Principal must be non-null and non-negative.");
    }
    if (periodRateFactor == null || periodRateFactor.compareTo(BigDecimal.ZERO) < 0) {
        throw new IllegalArgumentException("Period rate factor must be non-null and non-negative.");
    }
    if (compoundingPeriods < 0) {
        throw new IllegalArgumentException("Compounding periods cannot be negative.");
    }

    BigDecimal multiplier = BigDecimal.ONE;
    BigDecimal rateAddition = BigDecimal.ONE.add(periodRateFactor);

    for (int period = 0; period < compoundingPeriods; period++) {
        multiplier = multiplier.multiply(rateAddition);
    }

    return principal.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
}
```

# Assessment 3 Task D2: Code Review — Exception Handling

## Original Flawed Method

```java
public void processPayment(double amount) {
    try {
        if (amount <= 0) throw new Exception("bad");
        // deduct from account
    } catch (Exception e) {
        // silent
    }
}
```

---

## Identified Problems

### 1. Throwing Raw Generic `java.lang.Exception`
* **Problem**: The method instantiates and throws `new Exception(...)`, which is a top-level checked exception.
* **Why it's bad**: Throwing raw `Exception` forces calling methods to either catch or declare `throws Exception`, polluting caller method signatures. It fails to communicate specific semantic failure modes (such as an illegal argument or domain rule violation).
* **Fix**: Throw a specific, unchecked domain exception such as `InvalidAmountException` or `IllegalArgumentException`.

---

### 2. Meaningless / Obfuscated Error Message (`"bad"`)
* **Problem**: The exception message is simply `"bad"`.
* **Why it's bad**: In production environments, log aggregators and developers need clear diagnostic details (what the value was and why it was invalid) to debug issues without attaching a live debugger.
* **Fix**: Provide an informative message including the rejected value: `"Invalid payment amount: " + amount + ". Payment amount must be strictly positive."`

---

### 3. Catching Generic `Exception`
* **Problem**: The catch block catches `Exception e`.
* **Why it's bad**: Catching top-level `Exception` catches not only the intended validation exception, but also unchecked runtime exceptions (such as `NullPointerException`, `ArithmeticException`, or system errors) that might occur during the `// deduct from account` logic.
* **Fix**: Catch only specific, recoverable exceptions, or allow business validation exceptions to propagate up to the API/service boundary where appropriate error responses are constructed.

---

### 4. Swallowing Exceptions Silently (`// silent`)
* **Problem**: The `catch (Exception e)` block has no body and silently discards the error.
* **Why it's bad**: Silent swallowing is a critical banking anti-pattern:
  - The caller assumes the payment succeeded because no exception was raised and execution continues normally.
  - The account balance was not deducted, creating severe discrepancies between expected and actual account states.
  - Zero logging or telemetry is generated, making auditing and incident forensics impossible.
* **Fix**: Log the error at an appropriate severity level with context, rethrow a domain-specific exception, or return a meaningful result object indicating failure.

---

### 5. Immediate Throw-Catch Inside the Same Method Body
* **Problem**: The method throws an exception on line 3 and immediately catches it on line 5 within the exact same method.
* **Why it's bad**: Using exceptions for local in-method control flow adds massive stack-trace instantiation overhead and completely obscures the method's logic. An exception should signal a condition that the *current* method cannot handle.
* **Fix**: Throw the exception directly to notify the caller, without catching it immediately.

---

### 6. Missing Business Validations & Financial Precision
* **Problem**:
  - The method uses primitive `double amount`, which is prone to IEEE 754 floating-point rounding errors.
  - There is no check to ensure the account has sufficient funds before deducting.
  - The method lacks account balance mutation logic and transaction logging.
* **Fix**: Use `BigDecimal` for monetary amounts, validate against current balance, and provide atomic balance update.

---

## Corrected Implementation

```java
package com.savoira.assessment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentProcessor {
    private BigDecimal balance;

    public PaymentProcessor(BigDecimal initialBalance) {
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Processes a payment deduction against the account balance.
     *
     * @param amount the payment amount to deduct (must be strictly positive)
     * @throws InvalidLoanAmountException if amount is non-positive or null
     * @throws InsufficientIncomeException if amount exceeds available balance
     */
    public void processPayment(BigDecimal amount) {
        // 1. Validate amount is non-null and strictly positive
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidLoanAmountException(
                    "Invalid payment amount: " + amount + ". Payment amount must be strictly positive."
            );
        }

        BigDecimal scaledAmount = amount.setScale(2, RoundingMode.HALF_UP);

        // 2. Validate sufficient funds
        if (scaledAmount.compareTo(balance) > 0) {
            double shortfall = scaledAmount.subtract(balance).doubleValue();
            throw new InsufficientIncomeException(
                    String.format("Insufficient funds. Available: Rs.%.2f, Requested: Rs.%.2f, Shortfall: Rs.%.2f",
                            balance, scaledAmount, shortfall),
                    shortfall
            );
        }

        // 3. Deduct from account safely
        balance = balance.subtract(scaledAmount);
        System.out.printf("Payment of Rs.%.2f processed successfully. New balance: Rs.%.2f\n",
                scaledAmount, balance);
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
```

### Explanation of Improvements
1. **Meaningful Exceptions**: Throws specific domain exceptions (`InvalidLoanAmountException`, `InsufficientIncomeException`) with clear diagnostics instead of raw `new Exception("bad")`.
2. **No Silent Swallowing**: Exceptions propagate to the caller to signal failure explicitly.
3. **Financial Precision**: Replaced `double` with `BigDecimal` and `RoundingMode.HALF_UP` to eliminate binary rounding errors.
4. **Precondition Validation**: Checks both positive amount and sufficient funds before modifying state.

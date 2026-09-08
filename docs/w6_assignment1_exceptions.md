# Week 6 Assignment 1: Custom Exceptions & Exception Handling

## Business Context
Meridian Retail Bank's Payment Service implements a custom exception hierarchy to ensure fail-fast validations on incoming transactions.

---

## Exception Hierarchy Design

All custom exceptions reside in package `com.savoira.w6`:

```text
java.lang.RuntimeException
└── com.savoira.w6.PaymentException
    ├── com.savoira.w6.InvalidAmountException
    ├── com.savoira.w6.InsufficientFundsException (stores double shortfall + getter)
    └── com.savoira.w6.DailyLimitExceededException (stores double attemptedAmount + getter)
```

### Key Highlights
1. **Unchecked Hierarchy**: Subclassing `RuntimeException` allows payment validation errors to integrate cleanly without cluttering intermediate business method signatures with checked exception throws clauses.
2. **Rich Context**:
   - `InsufficientFundsException` carries the exact deficit (`shortfall`) to inform callers how much additional balance is required.
   - `DailyLimitExceededException` records the `attemptedAmount` that breached the regulatory daily ceiling (Rs.200,000).

---

## Test Execution Results

Starting balance: **Rs. 50,000**

1. **`processPayment(15000)`**:
   - Status: Success
   - Output: `Payment of Rs.15000.0 processed. New balance: Rs.35000.0`
   - Finally: `Attempt complete.`

2. **`processPayment(-500)`**:
   - Status: Throws `InvalidAmountException`
   - Output: `Caught InvalidAmountException: Invalid payment amount: Rs.-500.0. Payment amount must be strictly positive.`
   - Finally: `Attempt complete.`

3. **`processPayment(250000)`**:
   - Status: Throws `DailyLimitExceededException`
   - Output: `Caught DailyLimitExceededException: Daily transaction limit of Rs.200000.0 exceeded. Attempted amount: Rs.250000.0`
   - Attempted Amount Getter: `Rs.250000.0`
   - Finally: `Attempt complete.`

4. **`processPayment(40000)`**:
   - Status: Throws `InsufficientFundsException` (Available: Rs.35000, Requested: Rs.40000)
   - Output: `Caught InsufficientFundsException: Insufficient funds. Available: Rs.35000.0, Requested: Rs.40000.0, Shortfall: Rs.5000.0`
   - Shortfall Getter: `Rs.5000.0`
   - Finally: `Attempt complete.`

5. **`processPayment(10000)`**:
   - Status: Success (Deducted from unchanged Rs.35000 balance)
   - Output: `Payment of Rs.10000.0 processed. New balance: Rs.25000.0`
   - Finally: `Attempt complete.`

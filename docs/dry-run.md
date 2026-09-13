# Assessment 4 · Task A2.4: Execution Dry-Run Trace for `withdraw(7500)`

**Context**: Meridian Retail Bank — ATM Cash Dispensation  
**Target Execution**: `atm.withdraw(7500)` invoked on `new ATMSimulator()`  

---

## 1. Initial State

| Variable | Scope | Initial Value | Description |
|---|---|---|---|
| `balance` | Instance (`ATMSimulator`) | `10000.0` | Initial opening ledger balance |
| `attempts` | Local (`main`) | `0` | Consecutive failed attempts counter |
| `amount` | Argument (`withdraw`) | `7500.0` | User-entered requested withdrawal amount |
| `MIN_WITHDRAWAL` | Constant | `500.0` | Minimum per-transaction limit |
| `MAX_WITHDRAWAL` | Constant | `20000.0` | Maximum per-transaction limit |
| `DENOMINATION` | Constant | `500.0` | Physical note denomination multiple |
| `MAX_ATTEMPTS` | Constant | `3` | Maximum permitted failed attempts |

---

## 2. Step-by-Step Execution Trace Table

| Step # | Code Line / Condition | Evaluated Expression | Evaluation Result | Variable State (`balance`, `attempts`, `amount`) | Action Taken |
|---|---|---|---|---|---|
| **1** | `amount < MIN_WITHDRAWAL` | `7500.0 < 500.0` | `false` | `balance = 10000.0`, `attempts = 0`, `amount = 7500.0` | Condition passes; moves to next rule check |
| **2** | `amount > MAX_WITHDRAWAL` | `7500.0 > 20000.0` | `false` | `balance = 10000.0`, `attempts = 0`, `amount = 7500.0` | Condition passes; moves to next rule check |
| **3** | `amount % DENOMINATION != 0` | `7500.0 % 500.0 != 0` | `false` (`0.0 == 0.0`) | `balance = 10000.0`, `attempts = 0`, `amount = 7500.0` | Condition passes; multiple of 500 verified |
| **4** | `amount > balance` | `7500.0 > 10000.0` | `false` | `balance = 10000.0`, `attempts = 0`, `amount = 7500.0` | Condition passes; account has sufficient funds |
| **5** | `this.balance -= amount;` | `10000.0 - 7500.0` | `2500.0` | `balance = 2500.0`, `attempts = 0`, `amount = 7500.0` | **Balance successfully deducted** |
| **6** | `return` from `withdraw` | Method completes normally | — | `balance = 2500.0`, `attempts = 0`, `amount = 7500.0` | Control returns to `main()` loop |
| **7** | Print success & balance | Output printed to console | — | `balance = 2500.0`, `attempts = 0`, `amount = 7500.0` | Success message displayed; loop breaks |

---

## 3. Terminal Output Summary

```text
=== Welcome to Meridian Retail Bank ATM ===
Current Account Balance: ₹10000.0

Enter withdrawal amount (Attempt 1 of 3): ₹7500
Withdrawal successful! Dispensed: ₹7500.0
Updated Account Balance: ₹2500.0
```

### Conclusion
- All five business constraints were satisfied on Attempt 1.
- No exceptions were thrown.
- Final account balance is **₹2500.0**.

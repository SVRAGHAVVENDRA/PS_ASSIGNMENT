# Week 7 · Assignment 1 — Pseudocode, Flowchart & Dry Run

**Meridian Retail Bank — ATM Withdrawal Logic**

---

## Task 1 — Pseudocode

```
BEGIN ATM_WITHDRAWAL

    SET attempts = 0
    SET MAX_ATTEMPTS = 3
    SET MIN_WITHDRAWAL = 500
    SET MAX_WITHDRAWAL = 20000
    SET MULTIPLE = 500

    WHILE attempts < MAX_ATTEMPTS DO

        PROMPT user to enter withdrawal amount
        READ amount

        IF amount < MIN_WITHDRAWAL THEN
            DISPLAY "Error: Minimum withdrawal amount is Rs.500."
            INCREMENT attempts
            CONTINUE loop

        ELSE IF amount > MAX_WITHDRAWAL THEN
            DISPLAY "Error: Maximum withdrawal amount is Rs.20,000."
            INCREMENT attempts
            CONTINUE loop

        ELSE IF (amount MOD MULTIPLE) != 0 THEN
            DISPLAY "Error: Amount must be a multiple of Rs.500."
            INCREMENT attempts
            CONTINUE loop

        ELSE IF amount > current_balance THEN
            DISPLAY "Error: Insufficient balance."
            INCREMENT attempts
            CONTINUE loop

        ELSE
            // All rules passed
            current_balance = current_balance - amount
            DISPLAY "Withdrawal successful. New balance: " + current_balance
            BREAK loop  // exit after a successful withdrawal

        END IF

    END WHILE

    IF attempts == MAX_ATTEMPTS THEN
        DISPLAY "Card locked. Too many invalid attempts."
        LOCK card
    END IF

END ATM_WITHDRAWAL
```

**Rules covered:**
1. Minimum withdrawal Rs.500
2. Maximum withdrawal Rs.20,000
3. Amount must be a multiple of Rs.500
4. Sufficient balance check
5. Maximum 3 attempts before card lock (retry loop + lock message)

---

## Task 2 — Flowchart

The flowchart is exported/saved at [`docs/atm-flowchart.png`](./atm-flowchart.png).

It contains a **decision diamond** for each of the following checks (in order):
1. `attempts < 3 ?`
2. `amount < 500 ?`
3. `amount > 20000 ?`
4. `amount % 500 == 0 ?`
5. `amount > balance ?`

Terminal nodes: **Success (update balance)** and **Card Locked**.

> Note: Replace the placeholder image at `docs/atm-flowchart.png` with your own
> hand-drawn or draw.io/Lucidchart export before final submission.

---

## Task 3 — Dry Run

**Code:**
```java
int balance = 3000;
int count = 0;
int[] txns = {500, -200, 1000, -300, 800};
for (int i = 0; i < txns.length; i++) {
    balance += txns[i];
    if (txns[i] > 0) count++;
}
System.out.println("Balance: " + balance);
System.out.println("Deposits: " + count);
```

**Input:** `txns = {500, -200, 1000, -300, 800}`, starting `balance = 3000`

| i | txns[i] | balance (before) | balance += txns[i] | txns[i] > 0 ? | count |
|---|---------|-------------------|---------------------|----------------|-------|
| — | —       | —                  | 3000 (initial)      | —              | 0 (initial) |
| 0 | 500     | 3000               | 3500                | true           | 1     |
| 1 | -200    | 3500               | 3300                | false          | 1     |
| 2 | 1000    | 3300               | 4300                | true           | 2     |
| 3 | -300    | 4300               | 4000                | false          | 2     |
| 4 | 800     | 4000               | 4800                | true           | 3     |

Loop ends after `i = 4` since `txns.length = 5`.

---

## Task 4 — Final Output & Explanation

**Final Output:**
```
Balance: 4800
Deposits: 3
```

**Explanation:**
- `balance` starts at 3000 and each element of `txns` is added to it in sequence:
  3000 → 3500 → 3300 → 4300 → 4000 → 4800.
  Final balance = 3000 + 500 − 200 + 1000 − 300 + 800 = **4800**.
- `count` is only incremented when `txns[i] > 0` (a deposit). The positive values
  in the array are `500`, `1000`, and `800` — three positive entries — so
  `count` ends at **3**. The negative values (`-200`, `-300`) represent
  withdrawals and do not increment the deposit counter.


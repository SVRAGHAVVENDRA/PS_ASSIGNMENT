package com.savoira.w6;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Task 2: In-memory LoanLedger utilizing List, Map, and Set collections together
 * with BigDecimal for financial calculations.
 */
public class LoanLedger {

    // 1. List: maintains chronological order of all ledger transactions
    private final List<String> transactionHistory;

    // 2. Map: provides O(1) key-value lookup for account balances
    private final Map<String, BigDecimal> accountBalances;

    // 3. Set: guarantees uniqueness of active account identifiers
    private final Set<String> activeAccounts;

    public LoanLedger() {
        this.transactionHistory = new ArrayList<>();
        this.accountBalances = new HashMap<>();
        this.activeAccounts = new HashSet<>();
    }

    /**
     * Registers a new active account with an initial opening balance.
     */
    public void openAccount(String accountId, BigDecimal initialDeposit) {
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty.");
        }
        if (initialDeposit == null || initialDeposit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }

        BigDecimal scaledDeposit = initialDeposit.setScale(2, RoundingMode.HALF_UP);
        accountBalances.put(accountId, scaledDeposit);
        activeAccounts.add(accountId);

        String entry = String.format("[ACCOUNT_OPENED] Account %s opened with initial balance Rs.%.2f", accountId, scaledDeposit);
        transactionHistory.add(entry);
    }

    /**
     * Disburses a loan amount by crediting the designated account.
     */
    public void disburseLoan(String accountId, BigDecimal loanAmount) {
        validateActiveAccount(accountId);
        if (loanAmount == null || loanAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Loan amount must be strictly positive.");
        }

        BigDecimal currentBalance = accountBalances.get(accountId);
        BigDecimal newBalance = currentBalance.add(loanAmount).setScale(2, RoundingMode.HALF_UP);
        accountBalances.put(accountId, newBalance);

        String entry = String.format("[LOAN_DISBURSED] Rs.%.2f credited to %s. New Balance: Rs.%.2f", loanAmount, accountId, newBalance);
        transactionHistory.add(entry);
    }

    /**
     * Records a repayment against an account.
     */
    public void recordRepayment(String accountId, BigDecimal repaymentAmount) {
        validateActiveAccount(accountId);
        if (repaymentAmount == null || repaymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Repayment amount must be strictly positive.");
        }

        BigDecimal currentBalance = accountBalances.get(accountId);
        if (currentBalance.compareTo(repaymentAmount) < 0) {
            throw new IllegalStateException("Insufficient funds in account " + accountId + " for repayment.");
        }

        BigDecimal newBalance = currentBalance.subtract(repaymentAmount).setScale(2, RoundingMode.HALF_UP);
        accountBalances.put(accountId, newBalance);

        String entry = String.format("[REPAYMENT] Rs.%.2f deducted from %s. New Balance: Rs.%.2f", repaymentAmount, accountId, newBalance);
        transactionHistory.add(entry);
    }

    /**
     * Prints the balance per account from the Map.
     */
    public void printBalancePerAccount() {
        System.out.println("\n--- Account Balances ---");
        for (Map.Entry<String, BigDecimal> entry : accountBalances.entrySet()) {
            System.out.printf("Account: %-10s | Balance: Rs.%.2f\n", entry.getKey(), entry.getValue());
        }
    }

    /**
     * Prints the count and list of unique active accounts from the Set.
     */
    public void printActiveAccountSummary() {
        System.out.println("\n--- Active Accounts Summary ---");
        System.out.println("Total Active Accounts: " + activeAccounts.size());
        System.out.println("Active Account IDs   : " + activeAccounts);
    }

    /**
     * Prints the chronological transaction history from the List.
     */
    public void printTransactionHistory() {
        System.out.println("\n--- Transaction Audit Log ---");
        for (int i = 0; i < transactionHistory.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), transactionHistory.get(i));
        }
    }

    private void validateActiveAccount(String accountId) {
        if (accountId == null || !activeAccounts.contains(accountId)) {
            throw new IllegalArgumentException("Account " + accountId + " is not an active account in the ledger.");
        }
    }

    public List<String> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public Map<String, BigDecimal> getAccountBalances() {
        return Collections.unmodifiableMap(accountBalances);
    }

    public Set<String> getActiveAccounts() {
        return Collections.unmodifiableSet(activeAccounts);
    }

    public static void main(String[] args) {
        System.out.println("=== Meridian Bank: In-Memory Loan Ledger ===");
        LoanLedger ledger = new LoanLedger();

        // 1. Open accounts
        ledger.openAccount("ACC-101", new BigDecimal("10000.00"));
        ledger.openAccount("ACC-102", new BigDecimal("25000.00"));
        ledger.openAccount("ACC-103", new BigDecimal("5000.00"));

        // 2. Disburse loans
        ledger.disburseLoan("ACC-101", new BigDecimal("150000.00"));
        ledger.disburseLoan("ACC-103", new BigDecimal("80000.00"));

        // 3. Record repayments
        ledger.recordRepayment("ACC-101", new BigDecimal("12500.00"));
        ledger.recordRepayment("ACC-102", new BigDecimal("5000.00"));

        // 4. Output summaries as requested:
        // "Print the balance per account and the count of active accounts"
        ledger.printBalancePerAccount();
        ledger.printActiveAccountSummary();
        ledger.printTransactionHistory();
    }
}

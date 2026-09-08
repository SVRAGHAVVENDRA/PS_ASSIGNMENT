package com.savoira.w7;

/**
 * Big O complexity analysis for common account-management scenarios,
 * comparing naive approaches against optimized data-structure-based approaches.
 * All answers assume a dataset of 1 million accounts.
 */
public class ComplexityNotes {

    // ------------------------------------------------------------------
    // Scenario 1: Finding one account by ID
    // ------------------------------------------------------------------
    // Approach A: Linear scan through an array/list of accounts.
    //   Big O: O(n) — in the worst case, every one of the 1,000,000 accounts
    //   must be checked before finding (or failing to find) the target ID.
    //
    // Approach B: HashMap lookup keyed by account ID.
    //   Big O: O(1) average case — a hash lookup computes the bucket directly
    //   from the key's hash code, so lookup time does not grow with n.
    //
    // Choice for 1,000,000 accounts: HashMap lookup (O(1)).
    // Reason: A linear scan over 1 million records for every single lookup is
    // far too slow for any interactive or repeated-access scenario (e.g., an
    // ATM checking a customer's account on every transaction). A HashMap
    // keyed by account ID gives near-instant lookups regardless of dataset size,
    // at the cost of some extra memory for the hash table — a trade-off that
    // is clearly worth it at this scale.

    // ------------------------------------------------------------------
    // Scenario 2: Finding all overdue accounts
    // ------------------------------------------------------------------
    // Approach A: Nested loop comparing every pair of accounts.
    //   Big O: O(n^2) — for 1,000,000 accounts this is ~10^12 comparisons,
    //   which is computationally infeasible (would take days/weeks to run).
    //
    // Approach B: Single pass building/checking against a Set of overdue IDs.
    //   Big O: O(n) — one pass through all accounts, with O(1) average-case
    //   membership checks (or insertions) against a HashSet, giving overall
    //   linear time.
    //
    // Choice for 1,000,000 accounts: Single pass with a Set (O(n)).
    // Reason: The nested-loop approach (O(n^2)) does not scale at all beyond
    // small datasets — it is only ever appropriate for comparing pairs when
    // there is no way to avoid it. Since "overdue" status can be determined
    // independently per account (or by checking membership in a precomputed
    // Set of overdue IDs), a single linear pass is both correct and vastly
    // more efficient, turning an intractable O(n^2) problem into a fast O(n)
    // one.

    public static void main(String[] args) {
        System.out.println("See comments above for Big O analysis of account lookup");
        System.out.println("and overdue-account scenarios for 1,000,000 accounts.");
    }
}


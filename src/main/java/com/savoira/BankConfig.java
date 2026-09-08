package com.savoira;

/**
 * Static configuration constants for Meridian Retail Bank operations.
 */
public final class BankConfig {
    public static final double MAX_DEPOSIT = 500_000.0;
    public static final double MAX_WITHDRAWAL = 200_000.0;
    public static final int MAX_DAILY_TXN = 10;

    // Prevent instantiation of configuration class
    private BankConfig() {
        throw new UnsupportedOperationException("This is a utility/config class and cannot be instantiated.");
    }
}

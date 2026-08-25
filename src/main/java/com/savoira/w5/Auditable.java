package com.savoira.w5;

/**
 * Interface representing an auditable entity.
 * Part of Week 5 Assignment 2.
 */
public interface Auditable {
    
    /**
     * Default method returning the prefix for audit statements.
     */
    default String auditPrefix() {
        return "[AUDIT] ";
    }

    /**
     * Prints or processes the audit summary of the loan.
     */
    void auditSummary();
}

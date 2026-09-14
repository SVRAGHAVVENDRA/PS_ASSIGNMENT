package com.savoira.assessment;

/**
 * Section B Task B1: Interface defining audit logging capability.
 */
public interface Auditable {
    String getAuditLog();

    default String getAuditPrefix() {
        return "[AUDIT] ";
    }
}

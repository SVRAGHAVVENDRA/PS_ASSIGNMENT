package com.savoira.w5;

/**
 * Interface representing an exportable entity.
 * Part of Week 5 Assignment 2.
 */
public interface Exportable {
    
    /**
     * Converts the entity's data into a comma-separated String row.
     * @return comma-separated representation of fields
     */
    String toCSVRow();
}

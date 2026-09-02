package com.savoira.assessment;

/**
 * Task C2: Immutable AccountHolder Class
 * Holds read-only customer registration details.
 * 
 * <p>
 * <b>Why Immutability is useful for an AccountHolder object in a banking context:</b>
 * <ol>
 *   <li><b>Data Integrity and Security:</b> Crucial customer identity records (e.g., customerId, name, email) should not
 *       be modifiable after creation. Immutability prevents accidental or malicious tampering of records.</li>
 *   <li><b>Thread Safety:</b> Immutable objects are inherently thread-safe. Multiple threads can access customer records
 *       simultaneously without complex synchronization mechanisms, preventing race conditions.</li>
 *   <li><b>Cache and Map Key Stability:</b> In banking applications, customer records are frequently stored in caches or
 *       used as keys in HashMaps. If the object's properties could change, the hashcode would change, resulting in data loss.
 *       Immutability guarantees that hashcodes remain constant.</li>
 *   <li><b>Predictable State:</b> Operations like logging, auditing, and sending receipts can be performed safely, knowing
 *       the customer record cannot change mid-transaction.</li>
 * </ol>
 * </p>
 */
public final class AccountHolder {
    private final String name;
    private final String customerId;
    private final String email;

    /**
     * Constructs an immutable AccountHolder.
     *
     * @param name       the customer's full name (cannot be null or blank)
     * @param customerId the customer's unique system identifier (cannot be null or blank)
     * @param email      the customer's registered email address (cannot be null or blank)
     */
    public AccountHolder(final String name, final String customerId, final String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be null or blank.");
        }
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be null or blank.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Customer email cannot be null or blank.");
        }
        this.name = name;
        this.customerId = customerId;
        this.email = email;
    }

    /**
     * Returns the customer's name.
     *
     * @return the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer's unique ID.
     *
     * @return the customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Returns the customer's email.
     *
     * @return the customer email
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "AccountHolder[name=" + name + ", customerId=" + customerId + ", email=" + email + "]";
    }

    public static void main(final String[] args) {
        System.out.println("=== Task C2: Immutable AccountHolder ===");

        var holder = new AccountHolder("Jane Doe", "CUST-88092", "jane.doe@meridianbank.com");
        System.out.println("Created Account Holder: " + holder);
        System.out.println("Holder Name: " + holder.getName());
        System.out.println("Holder ID: " + holder.getCustomerId());
        System.out.println("Holder Email: " + holder.getEmail());
    }
}

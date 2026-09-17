# Task C2: Requirements Analysis

## C2.1 Functional Requirements
1. The system shall accept customer input for monthly income, existing loan EMIs, and requested loan amount.
2. The system shall calculate loan eligibility based on the customer's debt-to-income ratio.
3. The system shall display whether the customer is eligible along with the maximum loan amount they qualify for.
4. The system shall provide a clear explanation if the customer is not eligible.
5. The system shall allow users to change inputs and recalculate eligibility instantly.

## C2.2 Non-Functional Requirements
1. **Performance**: The eligibility check response time must be under 500ms for normal user requests.
2. **Security**: Customer financial data must be encrypted during transmission using TLS and masked in application logs.
3. **Usability**: The screen layout must be responsive and easy to use on mobile devices.

## C2.3 Scope Boundaries (What the feature will NOT do)
1. The feature will NOT perform a hard credit bureau check or issue a final legally binding loan agreement.
2. The feature will NOT collect uploaded documents (like salary slips) or disburse loan funds into the bank account.

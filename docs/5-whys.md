# Task C3: 5 Whys Analysis

## Problem Statement
Customers are abandoning the loan eligibility form on Step 2 without completing it.

## 5 Whys Analysis
1. **Why are customers abandoning the form on Step 2?**
   Because Step 2 asks users to manually upload bank statements and list all monthly expense details.

2. **Why does Step 2 ask for manual expense entry and file uploads?**
   Because the system requires detailed expense verification to calculate exact disposable income.

3. **Why does it require detailed expense verification at this stage?**
   Because the eligibility checker reuses the full loan application form instead of a simple initial checker.

4. **Why was the full application form reused for the eligibility check?**
   Because the initial version was built quickly without connecting it to existing customer profile data.

5. **Why was it not connected to existing customer profile data?**
   Because the feature was designed the same way for guest users and logged-in bank customers without pre-filling existing account data.

## Actionable Fix
Pre-fill income and existing EMI data for logged-in bank app users automatically so Step 2 becomes a simple 1-step verification instead of requiring manual uploads.

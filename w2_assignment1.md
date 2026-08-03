# Week 2 Assignment 1: Problem Framing & 5 Whys

**Context**: Meridian Retail Bank  
**Topic**: Pending Fund Transfers Root Cause Analysis

---

## 1. 5 Whys Analysis

| Why # | Your Answer |
|---|---|
| **Why 1** | Why do 200+ customer fund transfers show 'PENDING' for more than 48 hours and fail to complete? <br> *Answer*: Because the background transfer system does not receive a status update from the receiving bank's network API. |
| **Why 2** | Why does the background transfer system fail to receive a status update from the receiving bank's API? <br> *Answer*: Because the network connection times out during peak transaction hours when connecting to partner banks. |
| **Why 3** | Why does an API timeout leave transactions stuck in 'PENDING' status for 48 hours? <br> *Answer*: Because timed-out transactions are left unhandled until a manual batch reconciliation job runs every 48 hours. |
| **Why 4** | Why are timed-out transactions left waiting for a manual 48-hour batch job instead of retrying automatically? <br> *Answer*: Because the payment service lacks an automated retry mechanism to re-check timed-out transfer statuses. |
| **Why 5** | Why was an automated retry mechanism not included in the payment service? <br> *Answer*: Because the original system design assumed all API calls would return an immediate response without accounting for network drops. |

---

## 2. Root Cause Statement
The root cause of pending fund transfers is that the payment integration lacks an automated retry mechanism for network timeouts, causing failed connections to sit unhandled until a manual 48-hour batch cleanup.

---

## 3. "How Might We..." Problem Statement
How might we handle payment network timeouts automatically so that Meridian Bank customers receive immediate, accurate status updates on their fund transfers?

---

## 4. Solution Approaches

1. **Automated Status Retry System (Technology)**  
   Implement an automated retry service with exponential backoff that periodically queries the receiving bank API whenever a transaction times out.  
   *Rationale*: This resolves timed-out transactions within minutes rather than leaving them pending for 48 hours.

2. **Proactive Transfer Alerts & In-App Status Tracker (Communication)**  
   Send push notifications to customers when a network delay occurs and add a live "Refresh Status" button on the transfer details screen.  
   *Rationale*: This keeps customers informed about temporary delays in real-time, reducing anxiety and significantly lowering helpdesk call volume.

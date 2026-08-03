# Week 2 Assignment 2: Requirements & Decision Matrix

**Context**: Meridian Retail Bank  
**Feature**: Scheduled Payments

---

## 1. Functional Requirements

1. **FR-1**: The system shall allow customers to set up a recurring scheduled payment by specifying the recipient, payment amount, start date, and monthly frequency.
2. **FR-2**: The system shall execute the scheduled payment automatically on the set date each month at 08:00 AM.
3. **FR-3**: The system shall send a confirmation notification to the customer immediately after a scheduled payment is processed or if it fails due to insufficient funds.
4. **FR-4**: The system shall allow customers to edit or cancel an active scheduled payment up to 24 hours before the scheduled execution time.

---

## 2. Non-Functional Requirements

1. **NFR-1 (Performance)**: The system shall process and execute each scheduled payment transaction within 2 seconds.
2. **NFR-2 (Security)**: The system shall require two-factor authentication (2FA) for setting up scheduled payments and encrypt all payment data using AES-256 at rest and TLS 1.3 in transit.
3. **NFR-3 (Availability)**: The scheduled payment service shall maintain an uptime of 99.9% per month.

---

## 3. Weighted Decision Matrix (Notification Channels)

### Criteria & Weights (Scale 1–5):
- **Delivery Speed (Weight: 5)**: How quickly the notification reaches the customer.
- **Customer Reach (Weight: 4)**: Percentage of customers who can receive the message.
- **Cost to Operate (Weight: 4)**: Cost per notification sent (higher score = lower cost).
- **Reliability (Weight: 5)**: Dependability of message delivery without loss.

### Decision Matrix Table:

| Criterion | Weight | SMS (Score) | SMS (Weighted) | Push (Score) | Push (Weighted) | Email (Score) | Email (Weighted) |
|---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| Delivery Speed | 5 | 5 | 25 | 5 | 25 | 3 | 15 |
| Customer Reach | 4 | 5 | 20 | 3 | 12 | 4 | 16 |
| Cost to Operate | 4 | 2 | 8 | 5 | 20 | 5 | 20 |
| Reliability | 5 | 4 | 20 | 4 | 20 | 3 | 15 |
| **Total Score** | | | **73** | | **77** | | **66** |

### Calculations:
- **SMS Total**: $(5 \times 5) + (4 \times 5) + (4 \times 2) + (5 \times 4) = 25 + 20 + 8 + 20 = 73$
- **Push Notification Total**: $(5 \times 5) + (4 \times 3) + (4 \times 5) + (5 \times 4) = 25 + 12 + 20 + 20 = 77$
- **Email Total**: $(5 \times 3) + (4 \times 4) + (4 \times 5) + (5 \times 3) = 15 + 16 + 20 + 15 = 66$

---

## 4. Winning Channel & Justification

**Winning Channel**: **Push Notification** (Score: 77/90)

**Justification**:  
Push notifications won the evaluation because they offer instant delivery speed at near-zero operating cost compared to SMS gateway charges. While SMS has slightly higher customer reach for users who disable app notifications, push notifications provide the best combination of speed, cost efficiency, and security for active mobile banking app users. To handle users who disable push notifications, the team can use email as a fallback option.

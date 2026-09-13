# Assessment 4 · Task B2: Big-O Asymptotic Complexity Analysis

This document evaluates the time complexity of three code algorithms in Big-O notation and analyzes how execution time scales when the input size $n$ doubles ($n \rightarrow 2n$).

---

## Block X: Nested Iteration Loops

### Code
```java
for (int i = 0; i < n; i++) 
    for (int j = 0; j < n; j++) 
        System.out.println(i + "," + j);
```

### 1. Big-O Complexity: $O(n^2)$ (Quadratic Time)
- **Explanation**: 
  - The outer loop runs $n$ times ($i = 0$ to $n-1$).
  - For every single iteration of the outer loop, the inner loop also executes $n$ times ($j = 0$ to $n-1$).
  - Total number of basic operations: $n \times n = n^2$.
  - Therefore, the asymptotic time complexity is **$O(n^2)$**.

### 2. Scaling Behavior when $n$ Doubles ($n \rightarrow 2n$):
- Substituting $2n$ into the complexity equation:
  $$(2n)^2 = 4n^2 = 4 \times O(n^2)$$
- **Result**: When $n$ doubles, the number of operations increases by **4 times (quadruples)**.

---

## Block Y: Logarithmic Reduction While Loop

### Code
```java
int mid = n / 2; 
while (mid > 0) mid = mid / 2;
```

### 1. Big-O Complexity: $O(\log_2 n)$ or $O(\log n)$ (Logarithmic Time)
- **Explanation**:
  - Initially, `mid` is set to $n / 2$.
  - In each step of the `while` loop, `mid` is halved (`mid = mid / 2`) through integer division until it reaches 0.
  - The sequence of values for `mid` is: $\frac{n}{2}, \frac{n}{4}, \frac{n}{8}, \dots, 1, 0$.
  - The number of steps required to reduce $n/2$ to $0$ by repeatedly dividing by 2 is $\lfloor \log_2(n/2) \rfloor + 1 = \lfloor \log_2 n \rfloor$.
  - Therefore, the asymptotic time complexity is **$O(\log n)$**.

### 2. Scaling Behavior when $n$ Doubles ($n \rightarrow 2n$):
- Substituting $2n$ into the logarithmic equation:
  $$\log_2(2n) = \log_2(2) + \log_2(n) = 1 + \log_2(n)$$
- **Result**: When $n$ doubles, the number of operations increases by **only 1 additional operation** (an additive increase of $+1$ step, not a multiplicative factor).

---

## Block Z: Single Linear Traversal Loop

### Code
```java
for (int i = 0; i < n; i++) 
    System.out.println(arr[i]);
```

### 1. Big-O Complexity: $O(n)$ (Linear Time)
- **Explanation**:
  - The loop iterates from $i = 0$ to $i = n - 1$, executing a constant-time operation ($O(1)$ print statement) exactly $n$ times.
  - The total number of operations is directly proportional to the size of the array $n$.
  - Therefore, the asymptotic time complexity is **$O(n)$**.

### 2. Scaling Behavior when $n$ Doubles ($n \rightarrow 2n$):
- Substituting $2n$ into the linear equation:
  $$2n = 2 \times n$$
- **Result**: When $n$ doubles, the number of operations increases by **2 times (doubles)**.

---

## Summary Comparison Table

| Code Block | Algorithm Description | Big-O Complexity | Scaling when $n$ Doubles ($n \rightarrow 2n$) |
|---|---|:---:|:---:|
| **Block X** | Nested two-variable iteration | **$O(n^2)$** | **$4\times$ operations** (quadruples) |
| **Block Y** | Halving / binary reduction | **$O(\log n)$** | **$+1$ additional operation** |
| **Block Z** | Single array scan | **$O(n)$** | **$2\times$ operations** (doubles) |

# Sliding Window

## 1. What is Sliding Window?

**Sliding Window** is a technique used mainly for problems involving:

* Contiguous subarrays
* Substrings
* Consecutive elements
* Finding longest/shortest/max/min windows
* Fixed-size windows

Instead of repeatedly calculating every possible subarray or substring, we maintain a **window** using two pointers:

```text
left  →  l
right →  r
```

The window represents:

```text
[l ... r]
```

As we move through the array/string, we **expand** or **shrink** this window.

The main idea is:

> **Don't recalculate the entire window. Update it as the window moves.**

---

# 2. Simple Example

Consider:

```text
nums = [2, 1, 5, 1, 3, 2]
```

Suppose we want the maximum sum of any subarray of size `3`.

A brute-force approach would calculate:

```text
[2, 1, 5] → 8
[1, 5, 1] → 7
[5, 1, 3] → 9
[1, 3, 2] → 6
```

Answer:

```text
9
```

But notice something.

When we move from:

```text
[2, 1, 5]
```

to:

```text
[1, 5, 1]
```

we don't need to calculate `1 + 5 + 1` from scratch.

We can:

```text
Remove 2
Add 1
```

So:

```text
Old sum = 8

8 - 2 + 1 = 7
```

This is the core idea of Sliding Window.

---

# 3. Visualizing the Window

For:

```text
nums = [2, 1, 5, 1, 3, 2]
```

Window size = `3`

Initially:

```text
[ 2  1  5 ]  1  3  2
  ↑     ↑
  l     r
```

Sum:

```text
2 + 1 + 5 = 8
```

Move the window:

```text
  2 [ 1  5  1 ] 3  2
      ↑     ↑
      l     r
```

Update:

```text
8 - 2 + 1 = 7
```

Move again:

```text
  2  1 [ 5  1  3 ] 2
          ↑     ↑
          l     r
```

Update:

```text
7 - 1 + 3 = 9
```

Move again:

```text
  2  1  5 [ 1  3  2 ]
              ↑     ↑
              l     r
```

Update:

```text
9 - 5 + 2 = 6
```

Maximum:

```text
9
```

---

# 4. Why Sliding Window Is Useful

A common brute-force solution might take:

```text
O(n²)
```

or:

```text
O(n × k)
```

Sliding Window can often reduce this to:

```text
O(n)
```

Why?

Because each element is usually:

```text
Added to the window once
Removed from the window once
```

So we don't repeatedly process the same elements.

---

# 5. When Should You Think About Sliding Window?

Look for these keywords in a problem:

```text
contiguous subarray
substring
consecutive elements
longest subarray
shortest subarray
maximum sum
minimum sum
window of size k
at most k
exactly k
no repeating characters
```

For example:

> Find the longest substring without repeating characters.

This is a strong Sliding Window problem.

Another example:

> Find the maximum sum of a subarray of size `k`.

Again, Sliding Window.

---

# 6. Two Main Types of Sliding Window

There are two major patterns:

```text
1. Fixed-size Window
2. Variable-size Window
```

Understanding this distinction is very important.

---

# 7. Pattern 1: Fixed-Size Sliding Window

In a **fixed-size window**, the window size is given.

For example:

> Find the maximum sum of a subarray of size `k`.

If:

```text
k = 3
```

then the window must always contain exactly `3` elements.

The window looks like:

```text
[l ........ r]
```

where:

```text
r - l + 1 = k
```

---

# 8. Fixed Window Example

Consider:

```text
nums = [2, 1, 5, 1, 3, 2]
k = 3
```

Start:

```text
[2, 1, 5]
```

Sum:

```text
8
```

Move right:

```text
[1, 5, 1]
```

Update:

```text
8 - 2 + 1 = 7
```

Move again:

```text
[5, 1, 3]
```

Update:

```text
7 - 1 + 3 = 9
```

Move again:

```text
[1, 3, 2]
```

Update:

```text
9 - 5 + 2 = 6
```

Answer:

```text
9
```

---

# 9. Fixed Window Template

The general structure is:

```java
int left = 0;
int sum = 0;
int answer = 0;

for (int right = 0; right < nums.length; right++) {

    // Add new element
    sum += nums[right];

    // Window size is greater than k
    if (right - left + 1 > k) {

        // Remove left element
        sum -= nums[left];

        left++;

    }

    // Window size is exactly k
    if (right - left + 1 == k) {

        answer = Math.max(answer, sum);

    }
}
```

The important formula is:

```text
Window Size = right - left + 1
```

---

# 10. Fixed Window Example: Maximum Average

### Problem

Given:

```text
nums = [1, 12, -5, -6, 50, 3]
k = 4
```

Find the maximum average of any subarray of size `4`.

Instead of calculating every subarray:

```text
[1, 12, -5, -6]
[12, -5, -6, 50]
[-5, -6, 50, 3]
```

we maintain the sum.

```text
Window 1:

1 + 12 - 5 - 6 = 2
```

Move:

```text
2 - 1 + 50 = 51
```

Move:

```text
51 - 12 + 3 = 42
```

Maximum sum:

```text
51
```

Maximum average:

```text
51 / 4 = 12.75
```

---

# 11. Pattern 2: Variable-Size Window

This is the more important Sliding Window pattern.

Here, the window size is **not fixed**.

Instead, we expand and shrink the window depending on some condition.

The general structure is:

```text
Expand → Check condition → Shrink if necessary
```

We use:

```text
left
right
```

---

# 12. Variable Window Example

Consider:

```text
nums = [2, 3, 1, 2, 4, 3]
target = 7
```

Problem:

> Find the minimum length subarray whose sum is at least `7`.

Start:

```text
[2]
sum = 2
```

Not enough.

Expand:

```text
[2, 3]
sum = 5
```

Still not enough.

Expand:

```text
[2, 3, 1]
sum = 6
```

Still not enough.

Expand:

```text
[2, 3, 1, 2]
sum = 8
```

Now:

```text
sum >= 7
```

So we try to shrink the window.

Remove `2`:

```text
[3, 1, 2]
sum = 6
```

Now the window is invalid again.

Continue expanding:

```text
[3, 1, 2, 4]
sum = 10
```

Shrink:

```text
[1, 2, 4]
sum = 7
```

Still valid.

Shrink again:

```text
[2, 4]
sum = 6
```

Invalid.

Continue:

```text
[2, 4, 3]
sum = 9
```

Shrink:

```text
[4, 3]
sum = 7
```

Length:

```text
2
```

Answer:

```text
2
```

The subarray is:

```text
[4, 3]
```

---

# 13. Variable Window Template

A very important template:

```java
int left = 0;
int answer = 0;

for (int right = 0; right < nums.length; right++) {

    // Add nums[right]
    
    while (/* window is invalid */) {

        // Remove nums[left]
        left++;

    }

    // Window is valid here

    // Update answer

}
```

The exact condition depends on the problem.

---

# 14. Longest vs Shortest Window

This is one of the most important things to remember.

### Longest Window

Usually:

```text
Expand
↓
Shrink until valid
↓
Record answer
```

You record the answer **after the window becomes valid**.

### Shortest Window

Usually:

```text
Expand
↓
Window becomes valid
↓
Record answer
↓
Shrink while still valid
```

You want to shrink as much as possible.

---

# 15. Example: Longest Subarray

Suppose:

```text
nums = [1, 1, 0, 1, 1, 1]
```

Problem:

> Find the longest subarray containing at most one `0`.

We maintain:

```text
zeroCount
```

Expand the window.

When:

```text
zeroCount > 1
```

the window becomes invalid.

So we shrink from the left until:

```text
zeroCount <= 1
```

Then record:

```text
answer = max(answer, windowSize)
```

The pattern is:

```text
Expand
   ↓
Invalid?
   ↓
Shrink
   ↓
Valid
   ↓
Record longest
```

---

# 16. Example: Longest Substring Without Repeating Characters

This is **LeetCode 3**.

Given:

```text
s = "abcabcbb"
```

Find the longest substring without repeating characters.

Start:

```text
a
```

Valid.

```text
ab
```

Valid.

```text
abc
```

Valid.

Next:

```text
abca
```

`a` is repeated.

So the window is invalid.

Shrink from the left:

```text
bca
```

Now it is valid again.

Continue.

The longest substring is:

```text
"abc"
```

Length:

```text
3
```

---

# 17. Frequency Map for Sliding Window

For string problems, we often need to know how many times each character appears.

We can use:

```java
HashMap<Character, Integer>
```

For example:

```java
Map<Character, Integer> freq = new HashMap<>();
```

When adding a character:

```java
freq.put(
    s.charAt(right),
    freq.getOrDefault(s.charAt(right), 0) + 1
);
```

When removing:

```java
char ch = s.charAt(left);

freq.put(ch, freq.get(ch) - 1);

if (freq.get(ch) == 0) {

    freq.remove(ch);

}

left++;
```

---

# 18. Example: Longest Substring Without Repeating Characters

A common solution:

```java
public int lengthOfLongestSubstring(String s) {

    HashSet<Character> set = new HashSet<>();

    int left = 0;
    int answer = 0;

    for (int right = 0; right < s.length(); right++) {

        char ch = s.charAt(right);

        while (set.contains(ch)) {

            set.remove(s.charAt(left));

            left++;

        }

        set.add(ch);

        answer = Math.max(
            answer,
            right - left + 1
        );
    }

    return answer;
}
```

For:

```text
s = "abcabcbb"
```

Answer:

```text
3
```

---

# 19. Fixed vs Variable Window

| Feature           | Fixed Window        | Variable Window                   |
| ----------------- | ------------------- | --------------------------------- |
| Window size       | Fixed               | Changes                           |
| Usually given `k` | Yes                 | Usually no                        |
| Main movement     | Add + remove        | Expand + shrink                   |
| Example           | Max sum of size `k` | Longest substring without repeats |
| Common tools      | Sum, frequency      | Sum, frequency, set, counters     |

### Fixed Window

```text
Window size = k
```

Example:

```text
[1 2 3]
  ↓
[2 3 4]
  ↓
[3 4 5]
```

### Variable Window

```text
Window grows and shrinks
```

Example:

```text
[1]
[1 2]
[1 2 3]
[2 3]
[2 3 4]
```

---

# 20. Sliding Window and Two Pointers

Sliding Window is closely related to the **Two Pointer** technique.

We normally have:

```text
left
right
```

Example:

```text
       left       right
         ↓          ↓
[ 2 ][ 3 ][ 1 ][ 2 ][ 4 ][ 3 ]
```

`right` usually moves forward to expand the window.

`left` moves forward to shrink it.

Important:

> `left` never moves backward.

This is one reason the algorithm can remain `O(n)`.

---

# 21. Why Is Sliding Window O(n)?

Consider:

```text
[1, 2, 3, 4, 5]
```

The `right` pointer moves:

```text
0 → 1 → 2 → 3 → 4
```

So `right` moves `n` times.

The `left` pointer also moves forward, but never backward:

```text
0 → 1 → 2 → 3 → ...
```

Each element is added at most once and removed at most once.

Therefore:

```text
Time = O(n)
```

Not:

```text
O(n²)
```

---

# 22. Important Condition: When Does Sliding Window Work?

Sliding Window works best when the window condition is **monotonic**.

In simple terms:

> When expanding or shrinking the window gives us predictable behavior.

For example, with **positive numbers**:

```text
[2, 3, 1]
sum = 6
```

Adding another positive number:

```text
[2, 3, 1, 2]
sum = 8
```

The sum increased.

Removing from the left:

```text
[3, 1, 2]
sum = 6
```

The sum decreased.

This predictable behavior allows us to expand and shrink efficiently.

---

# 23. Important Pitfall: Negative Numbers

Consider:

```text
nums = [2, -5, 10]
```

With negative numbers, adding an element can **decrease** the sum.

For example:

```text
2 + (-5) = -3
```

So the simple sum-based Sliding Window logic may no longer work.

For problems such as:

> Shortest Subarray with Sum at Least K

when negative numbers are allowed, a different technique is usually needed.

A common solution uses:

```text
Prefix Sum + Monotonic Deque
```

This is **LeetCode 862**.

---

# 24. Pitfall: Window Size

Always remember:

```text
Window Size = right - left + 1
```

Not:

```text
right - left
```

Example:

```text
[2, 3, 5]
 ↑     ↑
 l     r
```

If:

```text
left = 0
right = 2
```

then:

```text
size = 2 - 0 + 1
     = 3
```

---

# 25. Pitfall: Removing Zero-Count Characters

Suppose we use:

```java
HashMap<Character, Integer> freq
```

and remove a character from the window.

If its count becomes `0`, remove it from the map:

```java
if (freq.get(ch) == 0) {

    freq.remove(ch);

}
```

Otherwise:

```java
freq.size()
```

may give the wrong number of distinct characters.

---

# 26. Pitfall: Updating the Answer at the Wrong Time

### Longest Window

Record after making the window valid:

```java
while (invalid) {

    // shrink
}

answer = Math.max(answer, windowSize);
```

### Shortest Window

Record while the window is still valid:

```java
while (valid) {

    answer = Math.min(
        answer,
        windowSize
    );

    // shrink
}
```

This distinction is extremely important.

---

# 27. Common Sliding Window Patterns

Most Sliding Window problems can be grouped into a few patterns.

### Pattern 1: Fixed Size + Sum

Example:

```text
Maximum sum subarray of size k
```

Use:

```text
sum
left
right
```

---

### Pattern 2: Fixed Size + Frequency

Example:

```text
Permutation in String
```

Use:

```text
frequency array/map
left
right
```

---

### Pattern 3: Variable Size + Sum

Example:

```text
Minimum Size Subarray Sum
```

Use:

```text
sum
left
right
```

---

### Pattern 4: Variable Size + Frequency

Example:

```text
Longest Substring Without Repeating Characters
```

Use:

```text
HashSet / HashMap
left
right
```

---

### Pattern 5: Variable Size + Counter

Example:

```text
Max Consecutive Ones III
```

Use:

```text
badCount
left
right
```

---

### Pattern 6: Sliding Window + Monotonic Deque

Example:

```text
Sliding Window Maximum
```

Use:

```text
Deque
left
right
```

---

# 28. A Useful Problem-Solving Checklist

When you see a new problem, ask:

### Step 1

Is the problem about a:

```text
Subarray?
Substring?
Contiguous section?
```

If yes, think about Sliding Window.

### Step 2

Is the window size fixed?

```text
"size k"
```

If yes:

```text
Fixed Window
```

### Step 3

If not fixed, is there a condition?

Examples:

```text
sum >= target
at most k zeros
no duplicate characters
at most k distinct characters
```

If yes:

```text
Variable Window
```

### Step 4

What information must the window maintain?

Maybe:

```text
sum
count
frequency
HashMap
HashSet
Deque
```

### Step 5

When does the window become invalid?

Write that condition clearly.

### Step 6

When should the answer be updated?

```text
Longest → after restoring validity
Shortest → while valid
```

---

# 29. Common LeetCode Problems

|   LC | Problem                                        | Pattern                   |
| ---: | ---------------------------------------------- | ------------------------- |
|    3 | Longest Substring Without Repeating Characters | Variable + Set/Frequency  |
|   76 | Minimum Window Substring                       | Variable + Frequency      |
|  209 | Minimum Size Subarray Sum                      | Variable + Sum            |
|  239 | Sliding Window Maximum                         | Monotonic Deque           |
|  424 | Longest Repeating Character Replacement        | Variable + Max Frequency  |
|  567 | Permutation in String                          | Fixed + Frequency         |
|  862 | Shortest Subarray with Sum ≥ K                 | Prefix Sum + Deque        |
|  992 | Subarrays with K Different Integers            | `atMost(k) - atMost(k-1)` |
| 1004 | Max Consecutive Ones III                       | Variable + Bad Counter    |
| 1456 | Max Vowels in Substring of Length K            | Fixed + Counter           |

---

# 30. The Most Important Templates

## Fixed Window

```java
int left = 0;

for (int right = 0; right < n; right++) {

    // Add nums[right]

    if (right - left + 1 > k) {

        // Remove nums[left]

        left++;

    }

    if (right - left + 1 == k) {

        // Calculate answer

    }
}
```

Remember:

```text
Add right
↓
If window too big
↓
Remove left
↓
Window size = k
↓
Calculate answer
```

---

## Variable Window

```java
int left = 0;

for (int right = 0; right < n; right++) {

    // Add nums[right]

    while (/* window is invalid */) {

        // Remove nums[left]

        left++;

    }

    // Window is valid

    // Update answer

}
```

Remember:

```text
Expand
↓
Check
↓
Invalid?
↓
Shrink
↓
Valid
↓
Update answer
```

---

# 31. Fixed Window vs Variable Window — Easy Memory Trick

Think about a **train**.

### Fixed Window

The train always has exactly `k` coaches.

```text
[ A B C ]
  ↓
[ B C D ]
  ↓
[ C D E ]
```

The size never changes.

### Variable Window

The train can become longer or shorter depending on the condition.

```text
[ A ]
[ A B ]
[ A B C ]
[ B C ]
[ B C D ]
```

You expand when you need more elements and shrink when the window becomes invalid.

---

# 32. Final Mental Model

The entire Sliding Window technique can be remembered as:

```text
              Sliding Window
                    |
          ┌─────────┴─────────┐
          ↓                   ↓
       Fixed                Variable
          |                   |
       size = k          condition based
          |                   |
      Add + Remove       Expand + Shrink
          |                   |
          └─────────┬─────────┘
                    ↓
             Maintain Window
                    ↓
              Update Answer
```

The most important idea is:

> **Instead of repeatedly calculating every subarray/substring, maintain information about the current window and update it as the window moves.**

---

# 33. Quick Revision

```text
Sliding Window
      ↓
Used for contiguous data
      ↓
Two pointers: left + right
      ↓
Right expands the window
      ↓
Left shrinks the window
      ↓
Maintain useful information
      ↓
Avoid recomputing from scratch
      ↓
Often O(n)
```

### Fixed Window

```text
Window size = k
```

Example:

```text
Maximum sum of subarray of size k
```

### Variable Window

```text
Window size changes
```

Example:

```text
Longest substring without repeating characters
Minimum size subarray sum
```

### Remember These Three Rules

```text
1. Window size = right - left + 1

2. left only moves forward

3. Each element enters and leaves the window
   at most once → usually O(n)
```

### One-Line Definition

> **Sliding Window is a technique where we maintain a contiguous range using two pointers and efficiently expand or shrink that range instead of recalculating every possible subarray or substring.**

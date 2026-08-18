# Binary Search

Binary Search is one of the most important searching techniques in DSA.

It is not just a way to find an element in a sorted array. The real power of Binary Search is that it can be used whenever a problem has a **monotonic search space** — a situation where the answer changes from one side of a boundary to the other.

This note starts from the absolute basics and gradually moves toward interview-level Binary Search problems.

---

# 1. What is Binary Search?

Suppose we have a sorted array:

```text
nums = [2, 5, 8, 12, 16, 23, 38, 45, 51]
```

We want to find:

```text
target = 23
```

A normal Linear Search checks:

```text
2 → 5 → 8 → 12 → 16 → 23
```

Binary Search works differently.

We look at the middle:

```text
[2, 5, 8, 12, 16, 23, 38, 45, 51]
              ↑
            mid
```

`16 < 23`

Therefore, everything on the left of `16` can be ignored.

Now search only:

```text
[23, 38, 45, 51]
```

Again check the middle.

This repeatedly cuts the search space approximately in half.

---

# 2. The Main Requirement

Classic Binary Search requires the search space to be **sorted**.

For example:

```text
[1, 3, 5, 7, 9, 11]
```

works.

But:

```text
[7, 1, 9, 3, 5]
```

does not work with normal Binary Search.

Why?

Because when we compare `nums[mid]` with the target, we need to know which half can safely be discarded.

---

# 3. Why Binary Search is Fast

Suppose there are `16` elements.

After each comparison:

```text
16
 ↓
8
 ↓
4
 ↓
2
 ↓
1
```

For `1,000,000` elements:

```text
1,000,000
500,000
250,000
125,000
...
1
```

The number of operations is approximately:

```text
log₂(n)
```

Therefore:

```text
Time Complexity = O(log n)
Space Complexity = O(1)
```

for the normal iterative implementation.

---

# 4. The Three Important Variables

The most common Binary Search implementation uses:

```java
int left = 0;
int right = nums.length - 1;
```

and:

```java
int mid = left + (right - left) / 2;
```

Think of them as:

```text
left -------------------- right
              ↑
             mid
```

### left

Beginning of the current search space.

### right

End of the current search space.

### mid

Middle of the current search space.

---

# 5. Basic Binary Search Template

```java
public int binarySearch(int[] nums, int target) {

    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}
```

---

# 6. Understanding the Conditions

The most important part is:

```java
if (nums[mid] == target)
```

We found the answer.

---

If:

```java
nums[mid] < target
```

then the target must be on the right.

So:

```java
left = mid + 1;
```

Example:

```text
[2, 5, 8, 12, 16, 23, 38]

              mid = 12
target = 23
```

Since:

```text
12 < 23
```

ignore:

```text
[2, 5, 8, 12]
```

Search:

```text
[16, 23, 38]
```

---

If:

```java
nums[mid] > target
```

then the target must be on the left.

So:

```java
right = mid - 1;
```

---

# 7. Dry Run

Given:

```text
nums = [2, 5, 8, 12, 16, 23, 38]
target = 23
```

### Step 1

```text
left = 0
right = 6

mid = 3
nums[mid] = 12
```

```text
12 < 23
```

Therefore:

```text
left = 4
```

---

### Step 2

```text
left = 4
right = 6

mid = 5
nums[mid] = 23
```

Found.

```text
return 5
```

---

# 8. Why `left <= right`?

We use:

```java
while (left <= right)
```

because when:

```text
left == right
```

there is still one element left to check.

Example:

```text
left = 4
right = 4
```

That means:

```text
[one element]
```

We must check it.

If we used:

```java
while (left < right)
```

we could skip the final candidate.

---

# 9. Why `mid = left + (right - left) / 2`?

You may see:

```java
int mid = (left + right) / 2;
```

This usually works, but it can theoretically cause integer overflow when `left + right` becomes larger than the maximum integer value.

Safer:

```java
int mid = left + (right - left) / 2;
```

Use this version as your standard template.

---

# 10. Binary Search Mental Model

Do not memorize only the code.

Think:

```text
1. What is my search space?
2. What is my middle?
3. Can I eliminate the left half?
4. Can I eliminate the right half?
5. What condition tells me which side to keep?
6. When do I stop?
```

This mental model becomes much more important in advanced problems.

---

# 11. Common Binary Search Patterns

Binary Search problems can be divided into several important patterns.

## Pattern 1 — Exact Search

Question:

> Find target in a sorted array.

Example:

```text
[1, 3, 5, 7, 9]
target = 7
```

Return its index.

---

## Pattern 2 — First Occurrence

Question:

> Find the first position where target appears.

Example:

```text
[1, 2, 2, 2, 3, 4]
target = 2
```

Answer:

```text
index = 1
```

Even after finding `2`, do not immediately return.

Continue searching left.

```java
if (nums[mid] == target) {
    answer = mid;
    right = mid - 1;
}
```

---

# 12. Last Occurrence

Example:

```text
[1, 2, 2, 2, 3, 4]
target = 2
```

Answer:

```text
index = 3
```

When found:

```java
if (nums[mid] == target) {
    answer = mid;
    left = mid + 1;
}
```

Continue searching right.

---

# 13. Lower Bound

Lower Bound means:

> Find the first index where `nums[index] >= target`.

Example:

```text
nums = [1, 3, 3, 5, 7]
target = 4
```

Answer:

```text
index = 3
```

because:

```text
nums[3] = 5
```

and:

```text
5 >= 4
```

Template:

```java
int left = 0;
int right = nums.length;

while (left < right) {

    int mid = left + (right - left) / 2;

    if (nums[mid] >= target) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

Notice that this uses:

```java
right = nums.length;
```

instead of:

```java
right = nums.length - 1;
```

This is a different Binary Search boundary style.

---

# 14. Upper Bound

Upper Bound means:

> Find the first index where `nums[index] > target`.

Example:

```text
nums = [1, 3, 3, 5, 7]
target = 3
```

Answer:

```text
index = 3
```

because:

```text
nums[3] = 5
```

and:

```text
5 > 3
```

Template:

```java
int left = 0;
int right = nums.length;

while (left < right) {

    int mid = left + (right - left) / 2;

    if (nums[mid] > target) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

---

# 15. Search Insert Position

Question:

> Where should the target be inserted so the array remains sorted?

Example:

```text
nums = [1, 3, 5, 6]
target = 2
```

Answer:

```text
1
```

because:

```text
[1, 2, 3, 5, 6]
```

The target belongs at index `1`.

This is essentially a **Lower Bound** problem.

---

# 16. Binary Search Without Knowing the Target

This is where Binary Search becomes much more interesting.

Sometimes the problem does NOT give you:

```text
target = 50
```

Instead, it asks something like:

> What is the minimum capacity required to ship all packages within `D` days?

There is no direct target element.

Instead, we search for the **answer**.

This is called:

# Binary Search on Answer

---

# 17. Binary Search on Answer

Suppose we have:

```text
weights = [1, 2, 3, 4, 5]
days = 3
```

We need to find the minimum ship capacity.

Possible capacities:

```text
5
6
7
8
9
10
11
12
13
14
15
```

We can test each capacity.

But there is a better observation.

If capacity `8` works, then:

```text
9 works
10 works
11 works
...
15 works
```

If capacity `5` does not work, smaller capacities also cannot work.

Therefore:

```text
capacity

5   6   7   8   9   10   11   12
N   N   N   Y   Y    Y    Y    Y
```

This is a **monotonic property**.

And that is exactly what Binary Search needs.

---

# 18. The Most Important Question

Whenever you see a problem asking:

```text
minimum possible X
maximum possible X
smallest X
largest X
minimum capacity
minimum speed
minimum time
maximum distance
```

ask:

> Can I guess an answer and check whether it works?

If yes, ask:

> If this answer works, will all larger answers also work?

or:

> If this answer works, will all smaller answers also work?

If yes:

```text
Binary Search on Answer
```

---

# 19. Binary Search on Answer Template

General structure:

```java
int left = minimumPossibleAnswer;
int right = maximumPossibleAnswer;

while (left < right) {

    int mid = left + (right - left) / 2;

    if (isPossible(mid)) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

The important part is:

```java
isPossible(mid)
```

This is your **feasibility function**.

---

# 20. Example — Koko Eating Bananas

Problem idea:

```text
piles = [3, 6, 7, 11]
h = 8
```

Find the minimum eating speed.

Possible speeds:

```text
1  2  3  4  5 ... 11
```

We do not know the target speed.

We ask:

```text
Can Koko finish all bananas at speed X?
```

If speed `6` works, then:

```text
7 works
8 works
...
11 works
```

Therefore:

```text
false false false true true true true
```

Binary Search can find the first `true`.

---

# 21. Feasibility Function

For Koko:

```java
boolean canFinish(int[] piles, int h, int speed) {

    long hours = 0;

    for (int pile : piles) {
        hours += (pile + speed - 1) / speed;
    }

    return hours <= h;
}
```

Then:

```java
int left = 1;
int right = maxPile;

while (left < right) {

    int mid = left + (right - left) / 2;

    if (canFinish(piles, h, mid)) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

---

# 22. How to Find Search Boundaries

For Binary Search on Answer, identifying:

```text
left
right
```

is often the hardest part.

Ask:

## What is the minimum possible answer?

Example:

```text
Koko eating bananas
```

Minimum speed:

```text
1
```

So:

```java
left = 1;
```

---

## What is the maximum possible answer?

Koko could eat one pile completely in one hour.

Therefore:

```text
max(piles)
```

So:

```java
right = maxPile;
```

---

# 23. Another Example — Ship Packages Within D Days

Problem:

```text
weights = [1,2,3,4,5,6,7,8,9,10]
days = 5
```

Find minimum ship capacity.

Minimum possible capacity:

```text
max(weights)
```

because a ship must at least carry the heaviest package.

Maximum possible capacity:

```text
sum(weights)
```

because with that capacity everything can be shipped in one day.

Therefore:

```java
int left = max(weights);
int right = sum(weights);
```

Then Binary Search.

---

# 24. Feasibility Function for Shipping

```java
boolean canShip(int[] weights, int days, int capacity) {

    int dayCount = 1;
    int currentWeight = 0;

    for (int weight : weights) {

        if (currentWeight + weight > capacity) {
            dayCount++;
            currentWeight = 0;
        }

        currentWeight += weight;
    }

    return dayCount <= days;
}
```

Then:

```java
int left = max(weights);
int right = sum(weights);

while (left < right) {

    int mid = left + (right - left) / 2;

    if (canShip(weights, days, mid)) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

---

# 25. Why `dayCount <= days` Instead of `dayCount == days`?

This is a very important interview detail.

Suppose:

```text
days = 5
```

and capacity `20` allows us to ship everything in:

```text
4 days
```

Is capacity `20` valid?

Yes.

Because the requirement is:

```text
within 5 days
```

not:

```text
exactly 5 days
```

Therefore:

```java
return dayCount <= days;
```

is correct.

---

# 26. Binary Search on a Monotonic Function

This is the core concept.

Imagine:

```text
false false false false true true true true
```

Binary Search can find:

```text
first true
```

Or:

```text
true true true true false false false
```

Binary Search can find:

```text
last true
```

This is the deeper idea behind many advanced Binary Search questions.

---

# 27. Rotated Sorted Array

Consider:

```text
[4, 5, 6, 7, 0, 1, 2]
```

This was originally sorted:

```text
[0, 1, 2, 4, 5, 6, 7]
```

but it was rotated.

Question:

> Search for a target in O(log n).

The array is not completely sorted, but one half is always sorted.

At every step:

```java
if (nums[left] <= nums[mid])
```

the left half is sorted.

Otherwise:

```text
right half is sorted
```

This allows us to determine which half may contain the target.

---

# 28. Search in Rotated Sorted Array

```java
public int search(int[] nums, int target) {

    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[left] <= nums[mid]) {

            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        } else {

            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return -1;
}
```

---

# 29. Find Minimum in Rotated Sorted Array

Example:

```text
[4, 5, 6, 7, 0, 1, 2]
```

Answer:

```text
0
```

Notice:

```text
nums[mid] > nums[right]
```

means the minimum must be on the right.

Otherwise:

```text
minimum is at mid or left
```

Template:

```java
int left = 0;
int right = nums.length - 1;

while (left < right) {

    int mid = left + (right - left) / 2;

    if (nums[mid] > nums[right]) {
        left = mid + 1;
    } else {
        right = mid;
    }
}

return nums[left];
```

---

# 30. Important Difference: `right = mid` vs `right = mid - 1`

This is one of the biggest Binary Search concepts.

If:

```text
mid
```

could still be the answer, do:

```java
right = mid;
```

Do NOT remove it.

Example:

```java
if (nums[mid] >= target) {
    right = mid;
}
```

Because `mid` could be the first valid position.

But when we know `mid` cannot be the answer:

```java
right = mid - 1;
```

The same logic applies to:

```java
left = mid
```

versus:

```java
left = mid + 1
```

---

# 31. Two Main Binary Search Styles

## Style 1 — Search for Exact Value

```java
while (left <= right)
```

Typical:

```text
target search
```

When eliminating `mid`:

```java
left = mid + 1;
right = mid - 1;
```

---

## Style 2 — Search for Boundary

```java
while (left < right)
```

Typical:

```text
first valid answer
minimum answer
lower bound
```

When `mid` may still be the answer:

```java
right = mid;
```

or:

```java
left = mid;
```

depending on the problem.

---

# 32. Common Mistakes

## Mistake 1 — Forgetting the Array Must Be Sorted

Binary Search is not automatically valid for every array.

---

## Mistake 2 — Infinite Loop

Wrong:

```java
left = mid;
```

with:

```java
while (left <= right)
```

can cause an infinite loop.

Usually use:

```java
left = mid + 1;
```

or:

```java
right = mid - 1;
```

for the exact-search style.

---

## Mistake 3 — Returning Immediately for First/Last Occurrence

Finding the target does not always mean you are done.

For first occurrence:

```java
answer = mid;
right = mid - 1;
```

For last occurrence:

```java
answer = mid;
left = mid + 1;
```

---

## Mistake 4 — Choosing Incorrect Search Boundaries

For Binary Search on Answer, always ask:

```text
What is the smallest possible answer?
What is the largest possible answer?
```

---

## Mistake 5 — Using `==` Instead of `<=`

For "within", "at most", or "no more than" constraints:

```java
<=
```

is often required.

Example:

```java
return daysUsed <= days;
```

---

# 33. A Simple Decision Framework

When you see a problem, ask these questions in order.

### Question 1

Is the array sorted?

If yes:

```text
Classic Binary Search
```

may work.

---

### Question 2

Is the array rotated?

If yes:

```text
Modified Binary Search
```

may work.

---

### Question 3

Is the problem asking:

```text
first
last
minimum
maximum
lower bound
upper bound
insert position
```

Think:

```text
Boundary Binary Search
```

---

### Question 4

Is there no obvious target?

Look for:

```text
minimum possible X
maximum possible X
smallest X
largest X
```

Then ask:

```text
Can I check whether X is possible?
```

If yes:

```text
Binary Search on Answer
```

---

# 34. How to Recognize Binary Search on Answer

Look for these words:

```text
minimum capacity
minimum speed
minimum time
minimum number
maximum minimum
minimum maximum
largest possible
smallest possible
at most
within D days
K workers
K machines
```

These are strong signals.

But do not blindly apply Binary Search.

You need a **monotonic feasibility condition**.

---

# 35. The Feasibility Pattern

The most useful template to remember:

```java
boolean isPossible(int x) {
    // Check whether x satisfies the problem
}
```

Then:

```java
int left = minimumAnswer;
int right = maximumAnswer;

while (left < right) {

    int mid = left + (right - left) / 2;

    if (isPossible(mid)) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

This pattern appears again and again in interview questions.

---

# 36. Complexity

## Normal Binary Search

```text
Time:  O(log n)
Space: O(1)
```

## Binary Search on Answer

If:

```text
N = cost of feasibility check
```

and:

```text
R = size of answer range
```

then:

```text
Time = O(N log R)
Space = O(1)
```

Example:

```text
Ship packages
```

If checking a capacity takes:

```text
O(n)
```

and the capacity range is:

```text
sum(weights) - max(weights)
```

then total complexity is approximately:

```text
O(n log(sum(weights)))
```

---

# 37. Practice Questions — Beginner

Start with these.

### 1. Binary Search

> Given a sorted array and a target, return the target's index. If it does not exist, return `-1`.

Pattern:

```text
Exact Binary Search
```

---

### 2. Search Insert Position

> Return the index where the target should be inserted in a sorted array.

Pattern:

```text
Lower Bound
```

---

### 3. First and Last Position

> Find the first and last position of a target in a sorted array.

Pattern:

```text
First Occurrence
Last Occurrence
```

---

### 4. Sqrt(x)

> Given a non-negative integer `x`, return the integer square root.

Example:

```text
x = 8
answer = 2
```

Think:

```text
1² <= 8
2² <= 8
3² > 8
```

Pattern:

```text
Binary Search on Answer
```

---

# 38. Practice Questions — Intermediate

### 5. Find Minimum in Rotated Sorted Array

Example:

```text
[3,4,5,1,2]
```

Answer:

```text
1
```

Pattern:

```text
Modified Binary Search
```

---

### 6. Search in Rotated Sorted Array

Example:

```text
[4,5,6,7,0,1,2]
target = 0
```

Answer:

```text
4
```

Pattern:

```text
Modified Binary Search
```

---

### 7. Find Peak Element

Example:

```text
[1,2,3,1]
```

Answer:

```text
index 2
```

Pattern:

```text
Binary Search using slope
```

---

### 8. Single Element in a Sorted Array

Example:

```text
[1,1,2,3,3,4,4,8,8]
```

Answer:

```text
2
```

Pattern:

```text
Binary Search using index parity
```

---

# 39. Practice Questions — Binary Search on Answer

### 9. Koko Eating Bananas

> Find the minimum eating speed needed to finish all bananas within `h` hours.

Pattern:

```text
Minimum Answer
```

---

### 10. Capacity to Ship Packages Within D Days

> Find the minimum ship capacity needed to ship all packages within `days`.

Pattern:

```text
Minimum Answer
```

---

### 11. Split Array Largest Sum

> Split an array into `k` subarrays while minimizing the largest subarray sum.

Pattern:

```text
Binary Search on Answer
```

---

### 12. Allocate Books

> Allocate books among students such that the maximum pages assigned to a student is minimized.

Pattern:

```text
Binary Search on Answer
```

---

### 13. Aggressive Cows

> Place cows in stalls so that the minimum distance between any two cows is maximized.

Pattern:

```text
Maximum Answer
```

---

### 14. Painter's Partition

> Paint boards using `k` painters while minimizing the maximum time taken by any painter.

Pattern:

```text
Binary Search on Answer
```

---

# 40. Practice Questions — Advanced

### 15. Median of Two Sorted Arrays

Pattern:

```text
Partition + Binary Search
```

Difficulty:

```text
Hard
```

---

### 16. Kth Smallest Element in a Sorted Matrix

Pattern:

```text
Binary Search on Value
```

---

### 17. Find K-th Smallest Pair Distance

Pattern:

```text
Binary Search on Answer
+
Two Pointer
```

---

### 18. Minimum Number of Days to Make m Bouquets

Pattern:

```text
Binary Search on Answer
```

---

### 19. Magnetic Force Between Two Balls

Pattern:

```text
Binary Search on Answer
+
Greedy
```

---

### 20. Minimum Limit of Balls in a Bag

Pattern:

```text
Binary Search on Answer
```

---

# 41. The Most Important Learning Order

Do not jump directly into hard Binary Search questions.

Follow this order:

```text
1. Basic Binary Search
        ↓
2. Search Insert Position
        ↓
3. First Occurrence
        ↓
4. Last Occurrence
        ↓
5. Lower Bound / Upper Bound
        ↓
6. Square Root
        ↓
7. Peak Element
        ↓
8. Rotated Sorted Array
        ↓
9. Find Minimum in Rotated Array
        ↓
10. Binary Search on Answer
        ↓
11. Koko Eating Bananas
        ↓
12. Ship Packages
        ↓
13. Aggressive Cows
        ↓
14. Split Array Largest Sum
        ↓
15. Hard Binary Search Problems
```

---

# 42. Interview Checklist

Before writing Binary Search code, ask:

```text
[ ] What is my search space?
[ ] Is it sorted or monotonic?
[ ] What is left?
[ ] What is right?
[ ] What does mid represent?
[ ] Can mid be the answer?
[ ] If mid works, which direction should I search?
[ ] If mid fails, which direction should I search?
[ ] Am I finding an exact value or a boundary?
[ ] Is the answer the first true or last true?
[ ] What is the minimum possible answer?
[ ] What is the maximum possible answer?
[ ] Can I create an isPossible() function?
```

---

# 43. The One Concept to Remember

Binary Search is NOT simply:

```text
Find an element in a sorted array.
```

The bigger idea is:

```text
Search Space
      ↓
Divide in Half
      ↓
Determine Which Half Can Be Eliminated
      ↓
Repeat
```

And for advanced problems:

```text
Possible Answers
      ↓
Check a Candidate Answer
      ↓
False False False True True True
      ↓
Binary Search the Boundary
```

Once you understand this, problems like:

```text
Koko Eating Bananas
Ship Packages
Aggressive Cows
Allocate Books
Split Array Largest Sum
Minimum Days to Make Bouquets
```

stop looking like completely different problems.

They become different versions of the same idea:

```text
Binary Search the answer.
```

---

# 44. Final Template Collection

## Exact Search

```java
int left = 0;
int right = nums.length - 1;

while (left <= right) {

    int mid = left + (right - left) / 2;

    if (nums[mid] == target) {
        return mid;
    }

    if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}

return -1;
```

## First Valid / Minimum Answer

```java
int left = low;
int right = high;

while (left < right) {

    int mid = left + (right - left) / 2;

    if (isPossible(mid)) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

## Last Valid / Maximum Answer

A common form is:

```java
int left = low;
int right = high;

while (left < right) {

    int mid = left + (right - left + 1) / 2;

    if (isPossible(mid)) {
        left = mid;
    } else {
        right = mid - 1;
    }
}

return left;
```

The key difference is the **biased midpoint**:

```java
(left + right) / 2
```

for finding the first valid position in the appropriate template, versus:

```java
(left + right + 1) / 2
```

when searching for the last valid position.

---

# 45. Final Mental Model

When you see a Binary Search problem, don't immediately think about code.

Think:

```text
What am I searching?
        ↓
An element?
        ↓
A position?
        ↓
A boundary?
        ↓
An answer?
        ↓
Can I check whether the answer is possible?
        ↓
Is that check monotonic?
        ↓
YES
        ↓
BINARY SEARCH
```

That is the real Binary Search pattern.

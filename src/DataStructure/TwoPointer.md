# Two Pointer Technique

## 1. What is the Two Pointer Technique?

The **Two Pointer Technique** is an algorithmic technique where we use **two indices/pointers** to traverse a data structure instead of using nested loops.

Most commonly, it is used with:

* Arrays
* Strings
* Linked Lists

The two pointers are usually called:

```text id="7y9j29"
left
right
```

or:

```text id="c6q0e4"
slow
fast
```

The main idea is:

> **Use two pointers to reduce unnecessary work and avoid checking every possible combination.**

---

# 2. Why Do We Need Two Pointers?

Consider this problem:

> Given a sorted array, find two numbers whose sum equals a target.

```text id="7x0v3x"
nums = [1, 2, 3, 4, 6]
target = 6
```

A brute-force approach would use two loops:

```java id="5d0p9x"
for (int i = 0; i < nums.length; i++) {

    for (int j = i + 1; j < nums.length; j++) {

        if (nums[i] + nums[j] == target) {
            // found
        }

    }
}
```

Time complexity:

```text id="j4nq7p"
O(n²)
```

But the array is sorted.

We can take advantage of that.

Use:

```text id="4p1e3y"
left = 0
right = nums.length - 1
```

```text id="2b8y3w"
[1, 2, 3, 4, 6]
 ↑           ↑
left       right
```

Calculate:

```text id="j1xw0z"
1 + 6 = 7
```

Target is `6`.

The sum is too large, so move `right`:

```text id="nq4u8t"
[1, 2, 3, 4, 6]
 ↑        ↑
left    right
```

Now:

```text id="k9f4qz"
1 + 4 = 5
```

Too small.

Move `left`:

```text id="w8d4p1"
[1, 2, 3, 4, 6]
    ↑     ↑
   left  right
```

Now:

```text id="j8g3x0"
2 + 4 = 6
```

Found!

Time complexity:

```text id="0j7d9b"
O(n)
```

This is the power of Two Pointers.

---

# 3. The Core Idea

Two pointers usually look like:

```text id="n9u2sp"
left                  right
  ↓                      ↓
[ 1 ][ 2 ][ 3 ][ 4 ][ 6 ]
```

We move the pointers based on the problem's condition.

For example:

```text id="q7d6a5"
sum < target  → left++
sum > target  → right--
sum == target → answer found
```

This works particularly well when the array is sorted.

---

# 4. Two Main Types of Two Pointers

There are several variations, but two fundamental patterns are:

```text id="p2q6cj"
1. Opposite Direction
2. Same Direction
```

There is also a very important specialized pattern:

```text id="s0u8eq"
3. Fast and Slow Pointer
```

---

# 5. Pattern 1: Opposite Direction

One pointer starts from the beginning.

The other starts from the end.

```text id="7l9b1p"
left →

[ 1 ][ 2 ][ 3 ][ 4 ][ 6 ]
                         ← right
```

Then they move toward each other.

General structure:

```java id="0y9t1d"
int left = 0;
int right = nums.length - 1;

while (left < right) {

    // Use nums[left] and nums[right]

    if (condition) {

        left++;

    } else {

        right--;

    }

}
```

This pattern is common for:

* Two Sum II
* Container With Most Water
* Valid Palindrome
* 3Sum
* 4Sum
* Squaring a Sorted Array

---

# 6. Example: Two Sum II

### Problem

Given a **sorted** array:

```text id="l8r3o5"
nums = [1, 2, 3, 4, 6]
target = 6
```

Find two numbers that add up to `6`.

Start:

```text id="z2o7ac"
[1, 2, 3, 4, 6]
 ↑           ↑
 L           R
```

Calculate:

```text id="j8f5k3"
1 + 6 = 7
```

Too large.

So:

```text id="3t2s5r"
right--
```

Now:

```text id="7h6c2n"
[1, 2, 3, 4, 6]
 ↑        ↑
 L        R
```

Calculate:

```text id="l5h1t8"
1 + 4 = 5
```

Too small.

So:

```text id="q5d1z8"
left++
```

Now:

```text id="j0w7l5"
[1, 2, 3, 4, 6]
    ↑     ↑
    L     R
```

Calculate:

```text id="m8f4r3"
2 + 4 = 6
```

Found.

---

# 7. Why Can We Move the Pointer Safely?

This is the most important reasoning behind the technique.

The array is sorted:

```text id="1x7h4v"
1  2  3  4  6
```

Suppose:

```text id="1n5s6z"
left = 1
right = 6
```

and:

```text id="7k5c3j"
1 + 6 = 7
```

Target:

```text id="5r2d0n"
6
```

The sum is too large.

Could we move `left`?

No.

Moving `left` to a larger value would make the sum even larger.

Therefore the only useful move is:

```text id="0g4g5a"
right--
```

Similarly, if:

```text id="j8x5v2"
1 + 4 = 5
```

and target is `6`, the sum is too small.

Moving `right` left would make the sum even smaller.

So we move:

```text id="9p2q4x"
left++
```

This is why sorting is so important for many opposite-direction two-pointer problems.

---

# 8. The Golden Rule for Sorted Arrays

For the classic pair-sum problem:

```text id="y7v3l8"
Condition        Movement
--------------------------------
sum < target     left++
sum > target     right--
sum == target    found
```

Remember:

> **Too small → increase the smaller value.**

> **Too large → decrease the larger value.**

---

# 9. Java Example: Two Sum II

```java id="f5p7v2"
public int[] twoSum(int[] numbers, int target) {

    int left = 0;
    int right = numbers.length - 1;

    while (left < right) {

        int sum = numbers[left] + numbers[right];

        if (sum == target) {

            return new int[]{left + 1, right + 1};

        } else if (sum < target) {

            left++;

        } else {

            right--;

        }
    }

    return new int[]{-1, -1};
}
```

Complexity:

```text id="3s0k4y"
Time:  O(n)
Space: O(1)
```

---

# 10. Pattern 2: Same Direction

In this pattern, both pointers move from left to right.

For example:

```text id="y9w3n2"
slow
 ↓
[ 1 ][ 2 ][ 2 ][ 3 ][ 4 ]
 ↑
fast
```

The pointers have different responsibilities.

Usually:

```text id="r1f8h4"
slow → position where we should write/store
fast → scans the array
```

This is commonly used for:

* Removing duplicates
* Moving zeroes
* Partitioning
* Filtering elements
* In-place array modifications

---

# 11. Example: Remove Duplicates

Consider:

```text id="9w4m8c"
nums = [1, 1, 2, 2, 3]
```

The array is sorted.

We want:

```text id="5y7p3z"
[1, 2, 3]
```

We can use:

```text id="m8s2v6"
slow
fast
```

The `fast` pointer scans the array.

The `slow` pointer tracks the position where the next unique value should go.

---

# 12. Visualizing Slow and Fast

Initially:

```text id="6g5x0q"
[1, 1, 2, 2, 3]
 ↑
slow
 ↑
fast
```

Move `fast`:

```text id="1y7c5b"
[1, 1, 2, 2, 3]
 ↑  ↑
slow fast
```

Same value:

```text id="4m8v1n"
1 == 1
```

Ignore it.

Move `fast`:

```text id="n3q8s7"
[1, 1, 2, 2, 3]
 ↑     ↑
slow  fast
```

Now:

```text id="6f4w9p"
1 != 2
```

Move `slow` and copy `2`:

```text id="d1y8r4"
[1, 2, 2, 2, 3]
    ↑     ↑
   slow  fast
```

Continue.

Final useful portion:

```text id="s7m2c1"
[1, 2, 3]
```

---

# 13. Same Direction Template

A common pattern:

```java id="w8f2d4"
int slow = 0;

for (int fast = 0; fast < nums.length; fast++) {

    if (/* valid element */) {

        nums[slow] = nums[fast];

        slow++;

    }

}
```

Here:

```text id="e3j6y2"
fast → scans
slow → writes
```

This often allows us to modify the array **in-place** without using another array.

---

# 14. Example: Move Zeroes

Given:

```text id="2m7f1a"
nums = [0, 1, 0, 3, 12]
```

Move all zeroes to the end:

```text id="6y4p9n"
[1, 3, 12, 0, 0]
```

We can use two pointers.

```text id="h2d8k7"
slow = position for next non-zero
fast = scans the array
```

When `fast` finds a non-zero value, we move it to the `slow` position.

Example:

```text id="m5w9q3"
[0, 1, 0, 3, 12]
    ↑
   fast
```

`1` is non-zero.

Place it at `slow`.

Continue scanning.

Final result:

```text id="5x8v2p"
[1, 3, 12, 0, 0]
```

---

# 15. Two Pointer vs Sliding Window

This distinction is important.

**Two Pointer** is the broader concept.

**Sliding Window** is one specific application of two pointers.

Think:

```text id="v6h1q3"
             Two Pointers
                  |
       ┌──────────┼──────────┐
       ↓          ↓          ↓
 Opposite      Same       Fast/Slow
 Direction    Direction
       |
       ↓
 Pair problems
       |
       ↓
 Sliding Window can also use
 left + right pointers
```

For example:

### Two Sum II

```text id="9r5j1c"
left →          ← right
```

This is two pointers.

### Longest Substring

```text id="1k8m6v"
left →      right →
```

This is a **sliding window**, which uses two pointers.

Therefore:

> **Every sliding window commonly uses two pointers, but not every two-pointer problem is a sliding window problem.**

---

# 16. Pattern 3: Fast and Slow Pointer

Fast and Slow Pointer is another important variation.

Instead of:

```text id="h7f2q4"
left + right
```

we use:

```text id="w2m8k5"
slow + fast
```

Usually:

```text id="y9x4q1"
slow → moves one step
fast → moves two steps
```

This is especially useful for **linked lists**.

---

# 17. Why Fast and Slow?

Consider a linked list:

```text id="5v2n8m"
1 → 2 → 3 → 4 → 5
```

Start:

```text id="x4k7p1"
slow = 1
fast = 1
```

Move:

```text id="a3m9q5"
slow → 2
fast → 3
```

Again:

```text id="k5r2w8"
slow → 3
fast → 5
```

When `fast` reaches the end, `slow` is around the middle.

This gives us a way to find the middle in **one pass**.

---

# 18. Example: Middle of Linked List

```text id="p9w4x2"
1 → 2 → 3 → 4 → 5
```

Use:

```java id="n8v3k1"
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;

    fast = fast.next.next;

}
```

At the end:

```text id="g6q1z8"
slow = 3
```

So `3` is the middle node.

Complexity:

```text id="f4y7m2"
Time:  O(n)
Space: O(1)
```

---

# 19. Fast and Slow Pointer for Cycle Detection

This is known as **Floyd's Cycle Detection Algorithm**.

Consider:

```text id="b8k2r6"
1 → 2 → 3 → 4
        ↑     |
        |_____|
```

There is a cycle.

Use:

```text id="s4n9w1"
slow → 1 step
fast → 2 steps
```

If a cycle exists, eventually:

```text id="q2m7x5"
slow == fast
```

Why?

Because the fast pointer keeps moving around the cycle and eventually catches the slow pointer.

---

# 20. Linked List Cycle Example

```java id="u5p8c3"
public boolean hasCycle(ListNode head) {

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {

        slow = slow.next;

        fast = fast.next.next;

        if (slow == fast) {

            return true;

        }

    }

    return false;
}
```

Complexity:

```text id="d8q2f7"
Time:  O(n)
Space: O(1)
```

---

# 21. Example: Valid Palindrome

Two pointers are also perfect for checking whether a string is a palindrome.

Given:

```text id="j7v3k9"
"racecar"
```

Start:

```text id="2q8m5x"
r a c e c a r
↑           ↑
L           R
```

Compare:

```text id="c4p1z8"
r == r
```

Move inward:

```text id="s9k2w5"
r a c e c a r
  ↑       ↑
  L       R
```

Compare:

```text id="0x6v3m"
a == a
```

Continue until:

```text id="q8m4n2"
L >= R
```

The string is a palindrome.

---

# 22. Container With Most Water

This is a very important Two Pointer problem.

Given:

```text id="k3w7p9"
height = [1,8,6,2,5,4,8,3,7]
```

We choose two lines.

Initially:

```text id="d2f8m4"
[1, 8, 6, 2, 5, 4, 8, 3, 7]
 ↑                             ↑
 L                             R
```

The area is:

```text id="v7x2n9"
width × minimum(height[left], height[right])
```

So:

```text id="w3k8p1"
area = (right - left)
       × min(height[left], height[right])
```

The key question is:

> Which pointer should move?

Move the pointer with the **smaller height**.

Why?

Because the current area is limited by the smaller wall.

Moving the taller wall cannot increase the limiting height, while moving the shorter wall gives us a chance to find a taller wall.

---

# 23. Container With Most Water Template

```java id="h5q8v2"
int left = 0;
int right = height.length - 1;

int maxArea = 0;

while (left < right) {

    int width = right - left;

    int currentHeight =
        Math.min(height[left], height[right]);

    int area = width * currentHeight;

    maxArea = Math.max(maxArea, area);

    if (height[left] < height[right]) {

        left++;

    } else {

        right--;

    }

}
```

Complexity:

```text id="y4n8s2"
Time:  O(n)
Space: O(1)
```

---

# 24. Three Sum

Two Pointers are also heavily used in **3Sum**.

Problem:

```text id="0v7m3p"
nums = [-1, 0, 1, 2, -1, -4]
```

Find triplets whose sum is `0`.

First sort:

```text id="q5x1n8"
[-4, -1, -1, 0, 1, 2]
```

Fix one number:

```text id="r7m2k5"
i = -1
```

Then use two pointers for the remaining part:

```text id="d8w4q1"
[-1, -1, 0, 1, 2]
  ↑           ↑
 left        right
```

Calculate:

```text id="s6p9v3"
-1 + (-1) + 2 = 0
```

Found:

```text id="m4k8x2"
[-1, -1, 2]
```

Continue searching.

---

# 25. 3Sum Pattern

The general structure is:

```java id="n7v2q5"
Arrays.sort(nums);

for (int i = 0; i < nums.length - 2; i++) {

    int left = i + 1;
    int right = nums.length - 1;

    while (left < right) {

        int sum =
            nums[i] + nums[left] + nums[right];

        if (sum == 0) {

            // Found triplet

            left++;
            right--;

        } else if (sum < 0) {

            left++;

        } else {

            right--;

        }
    }
}
```

Complexity:

```text id="f2m8x4"
Sorting: O(n log n)
Two-pointer search: O(n²)

Overall: O(n²)
```

---

# 26. Dutch National Flag / Sort Colors

Problem:

```text id="r8q2m6"
nums = [2, 0, 2, 1, 1, 0]
```

Sort:

```text id="x4k7p9"
[0, 0, 1, 1, 2, 2]
```

This uses **three pointers**:

```text id="c9m3w5"
low
mid
high
```

Conceptually:

```text id="0q8v2n"
0s | 1s | unknown | 2s
 ↑     ↑       ↑      ↑
low   mid      ?     high
```

Rules:

```text id="f5k1r8"
nums[mid] == 0
→ swap low and mid
→ low++
→ mid++

nums[mid] == 1
→ mid++

nums[mid] == 2
→ swap mid and high
→ high--
```

This is another example of pointer-based array manipulation.

---

# 27. Important Difference: Two Pointer vs HashMap

Consider Two Sum.

For an unsorted array:

```text id="v4m8q2"
nums = [2, 7, 11, 15]
target = 9
```

A HashMap solution is often best:

```text id="7x2n9p"
Time: O(n)
Space: O(n)
```

But if the array is sorted:

```text id="r6k3w8"
[2, 7, 11, 15]
```

Two pointers can give:

```text id="y8m1q5"
Time: O(n)
Space: O(1)
```

So always ask:

> **Is the array sorted or can I sort it without violating the problem's requirements?**

If yes, Two Pointers may be useful.

---

# 28. Important Difference: Two Pointer vs Nested Loops

Brute force:

```java id="k7m2p4"
for (int i = 0; i < n; i++) {

    for (int j = i + 1; j < n; j++) {

        // Check pair

    }

}
```

Complexity:

```text id="f8q1w5"
O(n²)
```

Two Pointer:

```java id="v3m9x7"
int left = 0;
int right = n - 1;

while (left < right) {

    // Process pair

}
```

Complexity:

```text id="n6k2r8"
O(n)
```

The important part is not simply "two pointers are faster."

The real reason is:

> **The problem's structure lets us eliminate many impossible pairs without checking them individually.**

---

# 29. How to Recognize Two Pointer Problems

Ask yourself these questions.

### Question 1

Is the problem about an array or string?

```text
Yes → Continue thinking
```

### Question 2

Is the array sorted?

```text
Yes → Opposite-direction pointers may work
```

### Question 3

Are you looking for a pair/triplet?

```text
Yes → Think Two Pointers
```

### Question 4

Are you comparing values from both ends?

```text
Yes → Think left + right
```

### Question 5

Are you modifying/filtering an array in-place?

```text
Yes → Think slow + fast
```

### Question 6

Is it a linked list and you need:

```text
Middle?
Cycle?
Cycle start?
```

Think:

```text
Fast + Slow
```

---

# 30. Common Two Pointer Patterns

```text
                    Two Pointers
                         |
        ┌────────────────┼────────────────┐
        ↓                ↓                ↓
  Opposite Ends     Same Direction    Fast + Slow
        |                |                |
        ↓                ↓                ↓
 Two Sum II        Remove Duplicates   Linked List Cycle
 3Sum              Move Zeroes         Middle Node
 Container         Partition           Happy Number
 Palindrome
```

---

# 31. Common Movement Rules

There is **no single golden rule that works for every Two Pointer problem**.

The pointer movement depends on the problem.

For sorted pair-sum problems:

```text id="8k5m1q"
sum < target
→ left++

sum > target
→ right--

sum == target
→ answer
```

For palindrome:

```text id="p7x2n4"
left++
right--
```

For Container With Most Water:

```text id="m9q3v6"
smaller height pointer moves
```

For Remove Duplicates:

```text id="r4k8w1"
fast scans
slow stores
```

For Linked List Cycle:

```text id="x6n2p9"
slow += 1
fast += 2
```

So:

> **Don't memorize pointer movements blindly. Understand why a pointer can safely move.**

---

# 32. Common Mistakes

## Mistake 1: Wrong Initialization

For opposite pointers:

```java id="u8q3m1"
int left = 0;
int right = nums.length - 1;
```

For same-direction:

```java id="z4p7k2"
int slow = 0;

for (int fast = 0; fast < nums.length; fast++) {
    
}
```

For linked lists:

```java id="n6w2x8"
ListNode slow = head;
ListNode fast = head;
```

---

## Mistake 2: Wrong Boundary

Usually:

```java id="r5m9q3"
while (left < right)
```

for opposite-direction pointers.

Not:

```java id="h2k7v1"
while (left <= right)
```

unless the specific problem requires examining the same index.

---

## Mistake 3: Moving the Wrong Pointer

In sorted Two Sum:

```text id="q4x8n2"
sum < target → left++
sum > target → right--
```

Don't reverse these.

---

## Mistake 4: Forgetting Sorting

Many opposite-direction problems rely on sorted data.

For example:

```text id="s3m7p9"
Two Sum II
3Sum
4Sum
```

Sorting gives us the information needed to decide which pointer can move.

---

## Mistake 5: Ignoring Duplicates

In problems like `3Sum` and `4Sum`, duplicate values can produce duplicate answers.

After finding a valid pair/triplet, we often need to skip duplicates.

Example:

```java id="k8v2m4"
while (left < right &&
       nums[left] == nums[left - 1]) {

    left++;

}
```

The exact duplicate handling depends on the problem.

---

# 33. Complexity

Most Two Pointer algorithms have:

```text id="w4n8q2"
Time: O(n)
Space: O(1)
```

when both pointers move only forward or toward each other.

For example:

```text
Two Sum II
Valid Palindrome
Container With Most Water
Move Zeroes
Remove Duplicates
```

However, if we first sort the array:

```text id="f2m6x9"
Arrays.sort(nums)
```

sorting costs:

```text id="q7p3m1"
O(n log n)
```

So a problem like `3Sum` becomes:

```text id="d9k4v8"
O(n log n) sorting
+
O(n²) pointer search
=
O(n²)
```

---

# 34. Two Pointer Problems to Practice

## Beginner

### 1. Two Sum II

**Pattern:** Opposite pointers

```text
Sorted array
left + right
```

### 2. Valid Palindrome

**Pattern:** Opposite pointers

```text
left → ← right
```

### 3. Remove Duplicates from Sorted Array

**Pattern:** Same direction

```text
slow + fast
```

### 4. Move Zeroes

**Pattern:** Same direction

```text
slow + fast
```

---

## Intermediate

### 5. Container With Most Water

**Pattern:** Opposite pointers

```text
Move smaller height
```

### 6. 3Sum

**Pattern:** Sort + fixed pointer + two pointers

```text
i + left + right
```

### 7. Sort Colors

**Pattern:** Three pointers

```text
low + mid + high
```

---

## Advanced

### 8. Trapping Rain Water

**Pattern:** Two pointers

```text
leftMax + rightMax
```

### 9. 4Sum

**Pattern:** Sorting + nested pointers

```text
i + j + left + right
```

---

# 35. Fast and Slow Pointer Problems

Practice these after understanding basic Two Pointers.

| Problem                   | Pattern     |
| ------------------------- | ----------- |
| Linked List Cycle         | Fast + Slow |
| Linked List Cycle II      | Fast + Slow |
| Happy Number              | Fast + Slow |
| Find the Duplicate Number | Fast + Slow |
| Middle of the Linked List | Fast + Slow |
| Palindrome Linked List    | Fast + Slow |
| Reorder List              | Fast + Slow |
| Circular Array Loop       | Fast + Slow |

---

# 36. Important Problems and Their Patterns

| Problem                   | Main Pattern    | Key Idea                        |
| ------------------------- | --------------- | ------------------------------- |
| Two Sum II                | Opposite        | Sorted pair sum                 |
| Valid Palindrome          | Opposite        | Compare both ends               |
| Remove Duplicates         | Same direction  | Slow writes, fast scans         |
| Move Zeroes               | Same direction  | Move valid values forward       |
| Container With Most Water | Opposite        | Move smaller height             |
| 3Sum                      | Sort + Opposite | Fix one, search pair            |
| 4Sum                      | Sort + Opposite | Fix two, search pair            |
| Sort Colors               | Three pointers  | Partition 0, 1, 2               |
| Trapping Rain Water       | Opposite        | Track left/right max            |
| Middle Linked List        | Fast + Slow     | Fast moves twice                |
| Linked List Cycle         | Fast + Slow     | Fast catches slow               |
| Happy Number              | Fast + Slow     | Detect repeated state           |
| Find Duplicate Number     | Fast + Slow     | Treat array as linked structure |

---

# 37. LeetCode Practice List

## Two Pointer

1. [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)
2. [Third Maximum Number](https://leetcode.com/problems/third-maximum-number/)
3. [Two Sum](https://leetcode.com/problems/two-sum/)
4. [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)
5. [Valid Anagram](https://leetcode.com/problems/valid-anagram/)
6. [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)
7. [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)
8. [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)
9. [3Sum Closest](https://leetcode.com/problems/3sum-closest/)
10. [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/)
11. [Sort Colors](https://leetcode.com/problems/sort-colors/)
12. [4Sum](https://leetcode.com/problems/4sum/)
13. [Backspace String Compare](https://leetcode.com/problems/backspace-string-compare/)
14. [Shortest Unsorted Continuous Subarray](https://leetcode.com/problems/shortest-unsorted-continuous-subarray/)

> **Note:** Not every problem in the above list is strictly a Two Pointer problem. Some are better classified as HashMap, Prefix Sum, Greedy, or other patterns. It's useful to keep them in your broader DSA practice list, but don't force the Two Pointer pattern onto a problem where it doesn't naturally fit.

---

# 38. Fast and Slow Pointer Practice

1. [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)
2. [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)
3. [Happy Number](https://leetcode.com/problems/happy-number/)
4. [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/)
5. [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)
6. [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/)
7. [Reorder List](https://leetcode.com/problems/reorder-list/)
8. [Circular Array Loop](https://leetcode.com/problems/circular-array-loop/)

---

# 39. How to Think During an Interview

When you see a problem, don't immediately start coding.

Go through this thought process:

```text
                Problem
                   ↓
        Is it array/string/list?
                   ↓
                  Yes
                   ↓
       Can I use two positions
       to eliminate possibilities?
                   ↓
                  Yes
                   ↓
        Which pointer pattern?
                   |
        ┌──────────┼──────────┐
        ↓          ↓          ↓
     Opposite    Same      Fast/Slow
       Ends    Direction
        ↓          ↓          ↓
      Pair      In-place    Linked List
      Search    Filtering     Cycle
```

---

# 40. The Most Important Mental Models

## Model 1: Opposite Ends

Think:

```text
left →       ← right
```

Use when:

* Array is sorted
* Comparing both ends
* Pair sum
* Palindrome
* Container problems

---

## Model 2: Same Direction

Think:

```text
slow →
fast  →
```

Use when:

* Filtering an array
* Removing duplicates
* Moving elements
* Maintaining a valid region

---

## Model 3: Fast and Slow

Think:

```text
slow → 1 step
fast → 2 steps
```

Use when:

* Finding middle
* Detecting cycles
* Finding cycle start
* Detecting repeated states

---

# 41. Two Pointer vs Sliding Window

Remember this relationship:

```text
                    TWO POINTERS
                         |
          ┌──────────────┼──────────────┐
          ↓              ↓              ↓
     Opposite Ends   Same Direction   Fast/Slow
          |
          ↓
     Pair Problems
     Palindrome
     Container
          |
          ↓
   SLIDING WINDOW
   is another common
   two-pointer pattern
```

So when learning DSA:

```text
Two Pointer
    ↓
Learn pointer movement
    ↓
Learn opposite-direction problems
    ↓
Learn same-direction problems
    ↓
Learn Fast + Slow
    ↓
Learn Sliding Window
```

---

# 42. Final Mental Model

The Two Pointer technique is not simply:

> "Put two variables called `left` and `right`."

The real idea is:

> **Use two moving positions to eliminate unnecessary comparisons or maintain a useful relationship between parts of the data.**

The three patterns to remember are:

```text
1. Opposite Direction

left →       ← right


2. Same Direction

slow →
fast  →


3. Fast and Slow

slow → 1 step
fast → 2 steps
```

And always ask:

```text
Why can I safely move this pointer?
```

That question is more important than memorizing a template.

---

# 43. Quick Revision

```text
Two Pointer
     ↓
Use two indices/pointers
     ↓
Reduce unnecessary work
     ↓
Usually O(n)
     ↓
Common patterns
     |
     ├── Opposite Direction
     |       ↓
     |   Two Sum II
     |   Palindrome
     |   Container
     |
     ├── Same Direction
     |       ↓
     |   Remove Duplicates
     |   Move Zeroes
     |
     └── Fast + Slow
             ↓
         Linked List Cycle
         Middle Node
         Happy Number
```

### Key Rules

```text
Sorted pair sum:

sum < target  → left++
sum > target  → right--
sum == target → found
```

But remember:

> **These movements are not universal. Pointer movement must come from the logic of the specific problem.**

### One-Line Definition

> **Two Pointer is a technique that uses two moving indices or pointers to efficiently traverse, compare, search, or modify a data structure while avoiding unnecessary work.**

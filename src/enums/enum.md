# Enums in Java

## 1. What is an Enum?

An **enum** (short for **enumeration**) is a special data type used when a variable should have a **fixed set of possible values**.

For example, an order can have only certain statuses:

```text
PLACED
CONFIRMED
SHIPPED
DELIVERED
CANCELLED
```

Instead of using random strings or numbers, Java allows us to define these values as an enum.

```java
enum OrderStatus {

    PLACED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED

}
```

Now `OrderStatus` becomes its own data type.

---

# 2. Why Do We Need Enums?

Suppose we don't use an enum.

We might write:

```java
String status = "SHIPPED";
```

Then somewhere else:

```java
if (status.equals("SHIPPED")) {

    System.out.println("Order is on the way");

}
```

The problem is that strings can contain anything.

For example:

```java
String status = "Shiped";
```

There is a spelling mistake.

But Java doesn't consider this an error because `"Shiped"` is still a valid `String`.

The program compiles successfully.

The problem is discovered only when the code runs.

---

# 3. Problems with Strings

Consider:

```java
String status = "PENDING";

if (status.equals("PNDING")) {

    processOrder();

}
```

There is a typo:

```text
PENDING
   ↓
PNDING
```

Java doesn't complain.

Why?

Because both are valid strings.

This can create difficult-to-find bugs.

---

# 4. Problems with Integers

Another approach is to use numbers:

```text
1 → PLACED
2 → CONFIRMED
3 → SHIPPED
4 → DELIVERED
5 → CANCELLED
```

Then we might write:

```java
int status = 3;

if (status == 3) {

    // What does 3 mean?
}
```

The code works, but it is difficult to understand.

What does `3` represent?

```text
3 = SHIPPED
```

This is called a **magic value** or **magic number**.

---

# 5. Enums Solve These Problems

Instead of:

```java
String status = "SHIPPED";
```

we can use:

```java
OrderStatus status = OrderStatus.SHIPPED;
```

Now Java knows that `status` can only contain values from `OrderStatus`.

For example:

```java
OrderStatus status = OrderStatus.SHIPPED;
```

Valid.

But:

```java
OrderStatus status = "SHIPPED";
```

Invalid.

And:

```java
OrderStatus status = 3;
```

Invalid.

This is called **type safety**.

---

# 6. Simple Definition

The easiest way to remember an enum is:

> **An enum represents a fixed set of related constants.**

For example:

### Order Status

```text
PLACED
CONFIRMED
SHIPPED
DELIVERED
CANCELLED
```

### User Role

```text
ADMIN
CUSTOMER
DRIVER
MANAGER
```

### Vehicle Type

```text
CAR
BIKE
TRUCK
BUS
```

### Direction

```text
NORTH
SOUTH
EAST
WEST
```

Whenever the possible values are **limited and predefined**, an enum can be a good choice.

---

# 7. Creating an Enum

The basic syntax is:

```java
enum OrderStatus {

    PLACED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED

}
```

Each value is called an **enum constant**.

So:

```text
OrderStatus
    |
    ├── PLACED
    ├── CONFIRMED
    ├── SHIPPED
    ├── DELIVERED
    └── CANCELLED
```

---

# 8. Using an Enum

We can create a variable of type `OrderStatus`.

```java
OrderStatus status = OrderStatus.SHIPPED;
```

Notice:

```java
OrderStatus.SHIPPED
```

This tells Java exactly which enum value we are using.

We can then compare it:

```java
if (status == OrderStatus.SHIPPED) {

    System.out.println("Your package is on the way!");

}
```

For enum comparison, using `==` is completely appropriate.

---

# 9. Why `==` Works with Enums

Enum constants are unique instances managed by Java.

So this is perfectly valid:

```java
if (status == OrderStatus.SHIPPED) {
    
}
```

You don't need:

```java
status.equals(OrderStatus.SHIPPED)
```

Although `equals()` would also work, `==` is commonly preferred for enum comparison because it is simple and clear.

---

# 10. Enum as a Data Type

One important thing to understand is that an enum creates its **own type**.

For example:

```java
enum PaymentMethod {

    CREDIT_CARD,
    DEBIT_CARD,
    UPI

}
```

Now we can declare:

```java
PaymentMethod method;
```

The variable `method` should contain only values from `PaymentMethod`.

Valid:

```java
method = PaymentMethod.UPI;
```

Invalid:

```java
method = "UPI";
```

Invalid:

```java
method = 1;
```

This is one of the biggest advantages of enums.

---

# 11. Enum with a Class

Enums can be used as fields inside normal classes.

For example:

```java
class Order {

    private String orderId;

    private OrderStatus status;

}
```

Now an order's status is guaranteed to be one of the values defined by `OrderStatus`.

We can create an order:

```java
OrderStatus status = OrderStatus.PLACED;
```

Then later:

```java
status = OrderStatus.CONFIRMED;
```

Then:

```java
status = OrderStatus.SHIPPED;
```

---

# 12. Benefits of Enums

## 1. Type Safety

The compiler knows which values are allowed.

```java
OrderStatus status = OrderStatus.SHIPPED;
```

You cannot accidentally assign an unrelated value.

---

## 2. Better Readability

Compare:

```java
if (status == 3)
```

with:

```java
if (status == OrderStatus.SHIPPED)
```

The second version immediately tells us what the code means.

---

## 3. Avoid Magic Values

Instead of:

```java
int status = 3;
```

we use:

```java
OrderStatus status = OrderStatus.SHIPPED;
```

No need to remember what `3` means.

---

## 4. Fewer Bugs

With strings:

```java
"SHIPPED"
"Shiped"
"shipped"
"Shipped"
```

All of these are different strings.

With enums, there is only:

```java
OrderStatus.SHIPPED
```

The compiler and IDE help prevent mistakes.

---

## 5. Better IDE Support

Modern IDEs provide:

* Auto-completion
* Refactoring
* Finding usages
* Compiler warnings/errors
* Navigation to enum definitions

For example, after typing:

```java
OrderStatus.
```

the IDE can show:

```text
PLACED
CONFIRMED
SHIPPED
DELIVERED
CANCELLED
```

---

# 13. When Should You Use an Enum?

Use an enum when the possible values are:

* Known in advance
* Limited
* Related to each other
* Unlikely to change frequently

### Good Examples

```text
OrderStatus
PaymentMethod
UserRole
Direction
Day
Month
VehicleType
TrafficLight
```

For example:

```java
enum TrafficLight {

    RED,
    YELLOW,
    GREEN

}
```

There is no reason to allow:

```text
PURPLE
BLUE
ORANGE
```

So an enum is a good fit.

---

# 14. Enum with Properties

Enums are more powerful than just a list of constants.

An enum can also have:

* Fields
* Constructors
* Methods
* Behavior

For example, suppose we want to represent different coins.

Each coin has a value.

```text
PENNY   → 1
NICKEL  → 5
DIME    → 10
QUARTER → 25
```

Instead of maintaining a separate map:

```java
Map<String, Integer>
```

we can keep the value directly inside the enum.

---

# 15. Enum with a Constructor

```java
enum Coin {

    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private final int value;

    Coin(int value) {

        this.value = value;

    }

    public int getValue() {

        return value;

    }

}
```

Now each enum constant has its own value.

```text
PENNY
  ↓
1

NICKEL
  ↓
5

DIME
  ↓
10

QUARTER
  ↓
25
```

---

# 16. Understanding the Constructor

This:

```java
PENNY(1)
```

passes `1` to the enum constructor:

```java
Coin(int value) {

    this.value = value;

}
```

Similarly:

```java
DIME(10)
```

passes `10`.

So:

```text
PENNY(1)
NICKEL(5)
DIME(10)
QUARTER(25)
```

means:

```text
Enum Constant + Data
```

---

# 17. Getting the Enum Value

We can use the getter:

```java
int value = Coin.DIME.getValue();
```

The result is:

```text
10
```

We can also perform calculations:

```java
int total =
    Coin.DIME.getValue()
    + Coin.QUARTER.getValue();
```

Result:

```text
35
```

This keeps the data and the constant together.

---

# 18. Why Put Data Inside an Enum?

Without an enum, we might create something like:

```java
Map<String, Integer> coinValues;
```

Then we have to maintain two separate concepts:

```text
Coin Name
    +
Coin Value
```

With an enum:

```java
DIME(10)
```

the name and its value stay together.

This makes the code easier to understand and maintain.

---

# 19. Enum with Multiple Properties

An enum can have more than one property.

For example, payment methods may have:

```text
Payment Method
Display Name
Fee Percentage
```

We can model that directly.

```java
enum PaymentMethod {

    CREDIT_CARD("Credit Card", 2.5),
    DEBIT_CARD("Debit Card", 1.0),
    UPI("UPI", 0.0),
    NET_BANKING("Net Banking", 1.5);

    private final String displayName;

    private final double feePercent;

    PaymentMethod(String displayName, double feePercent) {

        this.displayName = displayName;
        this.feePercent = feePercent;

    }

    public String getDisplayName() {

        return displayName;

    }

    public double getFeePercent() {

        return feePercent;

    }

}
```

Now every payment method contains its own information.

---

# 20. Using the PaymentMethod Enum

```java
PaymentMethod method = PaymentMethod.UPI;
```

We can get its display name:

```java
System.out.println(method.getDisplayName());
```

Output:

```text
UPI
```

And its fee:

```java
System.out.println(method.getFeePercent());
```

Output:

```text
0.0
```

For credit card:

```java
PaymentMethod method = PaymentMethod.CREDIT_CARD;

System.out.println(method.getDisplayName());
System.out.println(method.getFeePercent());
```

Output:

```text
Credit Card
2.5
```

---

# 21. Practical Example: Order Processing System

Let's build a small order system.

We will use two enums:

```text
OrderStatus
PaymentMethod
```

---

## Step 1: OrderStatus

```java
enum OrderStatus {

    PLACED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED

}
```

This represents the order lifecycle.

```text
PLACED
   ↓
CONFIRMED
   ↓
SHIPPED
   ↓
DELIVERED
```

Cancellation:

```text
PLACED ─────→ CANCELLED
CONFIRMED ──→ CANCELLED
```

But after shipping, cancellation is not allowed.

---

# 22. Step 2: PaymentMethod

```java
enum PaymentMethod {

    CREDIT_CARD("Credit Card", 2.5),
    DEBIT_CARD("Debit Card", 1.0),
    UPI("UPI", 0.0),
    NET_BANKING("Net Banking", 1.5);

    private final String displayName;

    private final double feePercent;

    PaymentMethod(String displayName, double feePercent) {

        this.displayName = displayName;
        this.feePercent = feePercent;

    }

    public String getDisplayName() {

        return displayName;

    }

    public double getFeePercent() {

        return feePercent;

    }

}
```

---

# 23. Step 3: Order Class

```java
class Order {

    private final String orderId;

    private OrderStatus status;

    private final PaymentMethod paymentMethod;

    private final double amount;

    public Order(
        String orderId,
        PaymentMethod paymentMethod,
        double amount
    ) {

        this.orderId = orderId;
        this.status = OrderStatus.PLACED;
        this.paymentMethod = paymentMethod;
        this.amount = amount;

    }

}
```

When an order is created, its initial status is:

```java
OrderStatus.PLACED
```

---

# 24. Advancing the Order Status

We can create a method to move the order to the next state.

```java
public void advanceStatus() {

    switch (status) {

        case PLACED:
            status = OrderStatus.CONFIRMED;
            break;

        case CONFIRMED:
            status = OrderStatus.SHIPPED;
            break;

        case SHIPPED:
            status = OrderStatus.DELIVERED;
            break;

        default:
            System.out.println("Order cannot advance further.");

    }

}
```

Now the order follows a controlled sequence:

```text
PLACED
   ↓
CONFIRMED
   ↓
SHIPPED
   ↓
DELIVERED
```

You can't accidentally move:

```text
PLACED → DELIVERED
```

unless you explicitly add such logic.

---

# 25. Cancelling an Order

We can also control cancellation.

```java
public boolean cancel() {

    if (
        status == OrderStatus.PLACED ||
        status == OrderStatus.CONFIRMED
    ) {

        status = OrderStatus.CANCELLED;

        return true;

    }

    return false;

}
```

Now:

```text
PLACED    → CANCELLED ✓
CONFIRMED → CANCELLED ✓
SHIPPED   → CANCELLED ✗
DELIVERED → CANCELLED ✗
```

The enum makes the business rule very easy to read.

---

# 26. Displaying Order Information

We can create:

```java
public void displayInfo() {

    System.out.println("Order ID: " + orderId);

    System.out.println("Status: " + status);

    System.out.println(
        "Payment: " + paymentMethod.getDisplayName()
    );

    System.out.println("Amount: ₹" + amount);

}
```

Example output:

```text
Order ID: ORD-001
Status: SHIPPED
Payment: Credit Card
Amount: ₹999.0
```

---

# 27. Complete Example

```java
enum OrderStatus {

    PLACED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED

}
```

```java
enum PaymentMethod {

    CREDIT_CARD("Credit Card", 2.5),
    DEBIT_CARD("Debit Card", 1.0),
    UPI("UPI", 0.0),
    NET_BANKING("Net Banking", 1.5);

    private final String displayName;

    private final double feePercent;

    PaymentMethod(String displayName, double feePercent) {

        this.displayName = displayName;
        this.feePercent = feePercent;

    }

    public String getDisplayName() {

        return displayName;

    }

    public double getFeePercent() {

        return feePercent;

    }

}
```

```java
class Order {

    private final String orderId;

    private OrderStatus status;

    private final PaymentMethod paymentMethod;

    private final double amount;

    public Order(
        String orderId,
        PaymentMethod paymentMethod,
        double amount
    ) {

        this.orderId = orderId;
        this.status = OrderStatus.PLACED;
        this.paymentMethod = paymentMethod;
        this.amount = amount;

    }

    public void advanceStatus() {

        switch (status) {

            case PLACED:
                status = OrderStatus.CONFIRMED;
                break;

            case CONFIRMED:
                status = OrderStatus.SHIPPED;
                break;

            case SHIPPED:
                status = OrderStatus.DELIVERED;
                break;

            default:
                System.out.println(
                    "Order cannot advance further."
                );

        }

    }

    public boolean cancel() {

        if (
            status == OrderStatus.PLACED ||
            status == OrderStatus.CONFIRMED
        ) {

            status = OrderStatus.CANCELLED;

            return true;

        }

        return false;

    }

    public void displayInfo() {

        System.out.println("Order ID: " + orderId);
        System.out.println("Status: " + status);
        System.out.println(
            "Payment: " + paymentMethod.getDisplayName()
        );
        System.out.println("Amount: ₹" + amount);

    }

}
```

Usage:

```java
public class Main {

    public static void main(String[] args) {

        Order order = new Order(
            "ORD-001",
            PaymentMethod.CREDIT_CARD,
            999.99
        );

        order.displayInfo();

        order.advanceStatus();
        order.advanceStatus();

        order.displayInfo();

        System.out.println(
            "Cancel after shipping: " + order.cancel()
        );

    }

}
```

Output:

```text
Order ID: ORD-001
Status: PLACED
Payment: Credit Card
Amount: ₹999.99

Order ID: ORD-001
Status: SHIPPED
Payment: Credit Card
Amount: ₹999.99

Cancel after shipping: false
```

---

# 28. Why This Design Works

## Status Is Controlled

Instead of allowing arbitrary strings:

```java
"PLACED"
"SHIPPED"
"Shiped"
"Delivered"
```

we use:

```java
OrderStatus.PLACED
OrderStatus.SHIPPED
OrderStatus.DELIVERED
```

Only valid values can be used.

---

## Payment Information Is Together

Each payment method contains its own data:

```text
CREDIT_CARD
    ↓
"Credit Card"
2.5%

UPI
    ↓
"UPI"
0.0%
```

There is no need for a separate lookup table.

---

## Business Rules Are Easy to Read

This:

```java
if (status == OrderStatus.SHIPPED)
```

is much easier to understand than:

```java
if (status == 3)
```

The code almost reads like English.

---

## Easy to Extend

Suppose we want to add:

```text
RETURNED
```

We can add:

```java
enum OrderStatus {

    PLACED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    RETURNED

}
```

Then we update the relevant business logic.

---

# 29. Enum vs String vs Integer

| Approach | Example               | Problem                         |
| -------- | --------------------- | ------------------------------- |
| String   | `"SHIPPED"`           | Typos are possible              |
| Integer  | `3`                   | Magic numbers, poor readability |
| Enum     | `OrderStatus.SHIPPED` | Type-safe and readable          |

### String

```java
String status = "SHIPPED";
```

### Integer

```java
int status = 3;
```

### Enum

```java
OrderStatus status = OrderStatus.SHIPPED;
```

The enum version is usually the clearest when the values are fixed.

---

# 30. Important Enum Features

An enum can contain:

```text
Constants
   ↓
Fields
   ↓
Constructor
   ↓
Methods
   ↓
Behavior
```

For example:

```java
enum PaymentMethod {

    UPI("UPI", 0.0);

    private final String displayName;

    private final double feePercent;

    PaymentMethod(String displayName, double feePercent) {

        this.displayName = displayName;
        this.feePercent = feePercent;

    }

    public String getDisplayName() {

        return displayName;

    }

}
```

So an enum is not simply a list of strings.

It is actually a special Java type that can contain **data and behavior**.

---

# 31. Common Places to Use Enums

Enums are commonly used for:

### Order Status

```java
OrderStatus.SHIPPED
```

### User Roles

```java
UserRole.ADMIN
UserRole.CUSTOMER
```

### Payment Methods

```java
PaymentMethod.UPI
PaymentMethod.CREDIT_CARD
```

### Directions

```java
Direction.NORTH
Direction.SOUTH
```

### Days

```java
Day.MONDAY
Day.FRIDAY
```

### Vehicle Types

```java
VehicleType.CAR
VehicleType.BIKE
VehicleType.TRUCK
```

### Application States

```java
ApplicationStatus.PENDING
ApplicationStatus.APPROVED
ApplicationStatus.REJECTED
```

---

# 32. When NOT to Use an Enum

Enums are best when the set of values is **fixed or controlled**.

For example:

```text
MONDAY
TUESDAY
WEDNESDAY
...
```

is perfect for an enum.

But imagine users can create unlimited categories:

```text
"Electronics"
"Furniture"
"Books"
"Gaming"
...
```

and administrators can add new categories at runtime.

An enum would not be the right choice because enum values are defined in the source code and aren't meant to be dynamically created at runtime.

In such cases, a database or another dynamic data structure is usually more appropriate.

---

# 33. Key Terms to Remember

### Enum

A special Java type representing a fixed set of constants.

### Enum Constant

An individual value inside an enum.

Example:

```java
OrderStatus.SHIPPED
```

### Type Safety

The compiler ensures that only valid enum values are assigned to an enum variable.

### Magic Value

An unexplained number or string used directly in code.

Example:

```java
if (status == 3)
```

### Enum Constructor

Used to initialize data associated with each enum constant.

### Enum Method

A method defined inside the enum to access data or perform behavior.

---

# 34. Final Mental Model

Think of an enum as a **controlled list of valid choices**.

Instead of saying:

```text
"Anything can be stored here."
```

we say:

```text
"Only these values are allowed."
```

For example:

```text
              OrderStatus
                   |
       ┌───────────┼───────────┐
       ↓           ↓           ↓
    PLACED     SHIPPED     DELIVERED
```

And for payment:

```text
             PaymentMethod
                   |
       ┌───────────┼───────────┐
       ↓           ↓           ↓
 CREDIT_CARD      UPI      DEBIT_CARD
```

The core idea is:

> **Use an enum when a value must belong to a predefined, limited set of choices.**

### One-Line Definition

> **An enum is a type-safe way to represent a fixed set of related constants in Java.**

---

# Quick Revision

```text
Enum
 ↓
Fixed set of values
 ↓
Type-safe
 ↓
Readable
 ↓
Avoids magic values
 ↓
Can contain fields, constructors and methods
 ↓
Useful for states, categories and options
```

### Remember

```text
String  → Can contain almost anything
int     → Can contain almost any number
Enum    → Only predefined values
```

The simplest example:

```java
enum OrderStatus {

    PLACED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED

}
```

Use it like:

```java
OrderStatus status = OrderStatus.SHIPPED;
```

And compare it like:

```java
if (status == OrderStatus.SHIPPED) {

    System.out.println("Order is on the way!");

}
```

# Builder Design Pattern

## 1. What is the Builder Pattern?

The **Builder Design Pattern** is a **creational design pattern** used to construct complex objects step-by-step.

The main idea is:

> **Separate object construction from the final object representation.**

Instead of passing many parameters into a constructor at once, we create an object using a dedicated **Builder**.

The Builder lets the client configure the object step-by-step and finally call:

```java
.build()
```

to create the final object.

The Builder Pattern is particularly useful when:

- An object has many optional fields.
- Most callers only need a subset of fields.
- You want to avoid long or telescoping constructors.
- The object requires multiple construction steps.
- You want readable object creation.
- The final object should be immutable.
- Validation should happen before the object is created.

---

# 2. The Problem Builder Solves

Imagine an HTTP request object:

```text
HttpRequest
    |
    +-- URL
    +-- Method
    +-- Headers
    +-- Query Parameters
    +-- Body
    +-- Timeout
```

Some fields are required:

```text
URL
```

while others are optional:

```text
Method
Headers
Query Parameters
Body
Timeout
```

At first, this looks manageable.

But as the number of fields increases, object construction becomes difficult.

---

# 3. Naive Approach: Large Constructor

One approach is to put everything inside one constructor:

```java
HttpRequest request = new HttpRequest(
        url,
        method,
        headers,
        queryParams,
        body,
        timeout
);
```

This creates several problems.

### Problem 1: Hard to read

What does each argument represent?

```java
new HttpRequest(
    url,
    method,
    headers,
    queryParams,
    body,
    timeout
);
```

The meaning is not immediately obvious.

### Problem 2: Null values

If we only need URL, method, and body:

```java
new HttpRequest(
    url,
    "POST",
    null,
    null,
    body,
    30000
);
```

The code becomes filled with `null` values.

### Problem 3: Parameter order matters

If multiple parameters have the same type, it is easy to swap them accidentally and still compile.

### Problem 4: Adding fields becomes difficult

Adding fields such as retry count, cache settings, proxy, authentication, and compression makes the constructor increasingly difficult to maintain.

---

# 4. Telescoping Constructor Anti-Pattern

A common attempt is constructor overloading:

```java
class User {

    public User(String name) {
        this(name, null);
    }

    public User(String name, String email) {
        this(name, email, null);
    }

    public User(String name, String email, String phone) {
        this(name, email, phone, null);
    }

    public User(
            String name,
            String email,
            String phone,
            String address
    ) {
        // initialization
    }
}
```

This is called the **Telescoping Constructor Pattern**.

It becomes problematic as optional fields grow because clients must remember constructor order and often pass `null` for fields they do not need.

---

# 5. Builder Pattern Solution

Instead of:

```java
new User(
    "Mayur",
    "mayur@example.com",
    null,
    "Nagpur"
);
```

we can write:

```java
User user = new User.Builder("Mayur")
        .email("mayur@example.com")
        .address("Nagpur")
        .build();
```

Every value is associated with a meaningful method name.

---

# 6. Core Idea

The Builder Pattern introduces a separate object responsible for construction.

```text
Client
   |
   v
Builder
   |
   | configure
   v
Builder state
   |
   | build()
   v
Product
```

The Builder stores construction state, and `build()` creates the final Product.

---

# 7. Two Important Concepts

## Step-by-Step Construction

Instead of:

```java
new User(name, email, phone, address);
```

we configure the Builder:

```java
builder
    .email(email)
    .phone(phone)
    .address(address);
```

## Fluent Interface

Builder methods usually return the Builder itself:

```java
public Builder email(String email) {
    this.email = email;
    return this;
}
```

Returning `this` enables method chaining:

```java
builder
    .email("mayur@example.com")
    .phone("9999999999")
    .address("Nagpur");
```

---

# 8. Basic Builder Structure

```java
class User {

    private String name;
    private String email;
    private String phone;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public static class Builder {

        private String name;
        private String email;
        private String phone;

        public Builder(String name) {
            this.name = name;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

Usage:

```java
User user = new User.Builder("Mayur")
        .email("mayur@example.com")
        .phone("9999999999")
        .build();
```

---

# 9. Understanding the Code

## Product

```java
class User {
}
```

`User` is the final object we want to create.

This is called the **Product**.

## Builder

```java
public static class Builder {
}
```

The Builder stores the information required to construct the Product.

## Required Fields

Required fields can be passed to the Builder constructor:

```java
public Builder(String name) {
    this.name = name;
}
```

## Optional Fields

Optional fields can be configured through Builder methods:

```java
public Builder email(String email) {
    this.email = email;
    return this;
}
```

## build()

```java
public User build() {
    return new User(this);
}
```

This is where the actual Product is created.

---

# 10. Why Is the Product Constructor Private?

Notice:

```java
private User(Builder builder) {
}
```

The constructor is private so clients cannot directly construct the Product.

Instead:

```java
new User.Builder(...)
```

is used to configure the object, followed by:

```java
.build();
```

This gives the Builder control over construction.

---

# 11. Builder Workflow

The normal workflow is:

```text
1. Client creates Builder
          |
          v
2. Required values provided
          |
          v
3. Optional values configured
          |
          v
4. build() called
          |
          v
5. Product created
          |
          v
6. Product returned
```

Example:

```java
User user = new User.Builder("Mayur")
        .email("mayur@example.com")
        .phone("9999999999")
        .build();
```

Internally:

```text
new Builder("Mayur")
       |
       v
Builder
       |
       +-- name
       +-- email
       +-- phone
       |
       v
build()
       |
       v
new User(builder)
       |
       v
User object
```

---

# 12. Making the Product Immutable

Builder works especially well with immutable objects.

```java
class User {

    private final String name;
    private final String email;
    private final String phone;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
```

There are no setters, and the fields are `final`.

Therefore, once the Product is created, its state cannot normally be changed.

A useful mental model is:

```text
Builder = mutable construction state
Product = immutable final state
```

---

# 13. Real-World Example: HttpRequest

Consider an HTTP request with:

```text
URL              required
Method           optional
Headers          optional
Query Parameters optional
Body             optional
Timeout          optional
```

A Builder can represent this cleanly.

```java
import java.util.*;

class HttpRequest {

    private final String url;
    private final String method;
    private final Map<String, String> headers;
    private final Map<String, String> queryParams;
    private final String body;
    private final int timeout;

    private HttpRequest(Builder builder) {

        this.url = builder.url;
        this.method = builder.method;

        this.headers =
                Collections.unmodifiableMap(
                        new HashMap<>(builder.headers)
                );

        this.queryParams =
                Collections.unmodifiableMap(
                        new HashMap<>(builder.queryParams)
                );

        this.body = builder.body;
        this.timeout = builder.timeout;
    }

    public static class Builder {

        private final String url;
        private String method = "GET";
        private Map<String, String> headers = new HashMap<>();
        private Map<String, String> queryParams = new HashMap<>();
        private String body;
        private int timeout = 30000;

        public Builder(String url) {
            this.url = url;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            this.queryParams.put(key, value);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}
```

---

# 14. Using the HttpRequest Builder

## Simple GET

```java
HttpRequest get =
        new HttpRequest.Builder(
                "https://api.example.com/users"
        )
        .build();
```

Defaults can be used:

```text
method  = GET
timeout = 30000
```

## POST

```java
HttpRequest post =
        new HttpRequest.Builder(
                "https://api.example.com/users"
        )
        .method("POST")
        .addHeader("Content-Type", "application/json")
        .body("{"name":"Mayur"}")
        .timeout(5000)
        .build();
```

## PUT

```java
HttpRequest put =
        new HttpRequest.Builder(
                "https://api.example.com/config"
        )
        .method("PUT")
        .addHeader("Authorization", "Bearer token")
        .addHeader("Content-Type", "application/json")
        .addQueryParam("env", "production")
        .addQueryParam("version", "2")
        .body("{"enabled":true}")
        .timeout(10000)
        .build();
```

---

# 15. What Builder Achieves

Compared with:

```java
new HttpRequest(
    url,
    "POST",
    headers,
    queryParams,
    body,
    5000
);
```

Builder gives:

```java
new HttpRequest.Builder(url)
    .method("POST")
    .addHeader("Content-Type", "application/json")
    .addQueryParam("env", "production")
    .body(body)
    .timeout(5000)
    .build();
```

Benefits:

- No telescoping constructors.
- No positional guessing.
- Optional values are explicit.
- Code is self-documenting.
- Fields can usually be configured in any order.
- Adding optional fields is easier.

---

# 16. Class Diagram

The classic Builder Pattern has four participants:

```text
             +---------+
             | Client  |
             +----+----+
                  |
                  v
          +---------------+
          |    Director   |  optional
          +-------+-------+
                  |
                  v
          +---------------+
          |    Builder    |
          +-------+-------+
                  |
                  v
          +---------------+
          |ConcreteBuilder|
          +-------+-------+
                  |
                  v
          +---------------+
          |    Product    |
          +---------------+
```

In many Java applications, the structure is simplified to:

```text
Client
  |
  v
Product.Builder
  |
  | configuration
  v
build()
  |
  v
Product
```

A separate Director or ConcreteBuilder class is often unnecessary.

---

# 17. Participants

## Product

The final complex object.

Example:

```java
HttpRequest
```

## Builder

Exposes the API used to configure the Product:

```java
method()
addHeader()
body()
timeout()
build()
```

## ConcreteBuilder

Stores intermediate state and creates the Product.

In Java, this is often simply the nested `Builder` class.

## Director

Coordinates construction steps and can provide reusable construction recipes.

The Director is optional.

---

# 18. Director

Suppose every payment request requires:

```text
Authorization header
Content-Type
Timeout
```

Instead of repeating the configuration everywhere, a Director can provide a standard recipe.

```java
class HttpRequestDirector {

    public HttpRequest createPaymentRequest(
            String url,
            String token
    ) {

        return new HttpRequest.Builder(url)
                .method("POST")
                .addHeader(
                        "Authorization",
                        "Bearer " + token
                )
                .addHeader(
                        "Content-Type",
                        "application/json"
                )
                .timeout(10000)
                .build();
    }
}
```

Usage:

```java
HttpRequestDirector director =
        new HttpRequestDirector();

HttpRequest request =
        director.createPaymentRequest(
                "https://api.example.com/payment",
                "token123"
        );
```

The Director is useful when many parts of the application need the same construction sequence.

---

# 19. When Do We Need a Director?

Use a Director when:

- Construction has a specific sequence.
- Multiple places use the same construction recipe.
- You want named standard configurations.
- You want to hide complicated construction logic.

Do not add a Director just because the textbook diagram contains one.

For many fluent Java Builders:

```java
new Product.Builder(...)
    .field(...)
    .field(...)
    .build();
```

is enough.

---

# 20. Builder Validation

Validation can be performed inside:

```java
build()
```

Example:

```java
public User build() {

    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException(
                "Name is required"
        );
    }

    if (email == null || email.isBlank()) {
        throw new IllegalArgumentException(
                "Email is required"
        );
    }

    return new User(this);
}
```

This means the Builder can prevent invalid Products from being created.

---

# 21. Required vs Optional Fields

A useful convention is:

```text
Required fields
      ↓
Builder constructor

Optional fields
      ↓
Builder methods
```

Example:

```java
public Builder(String name) {
    this.name = name;
}
```

Then:

```java
.email(...)
.phone(...)
.address(...)
```

can remain optional.

---

# 22. Default Values

Builders can provide defaults:

```java
private String method = "GET";
private int timeout = 30000;
```

Then:

```java
new HttpRequest.Builder(url)
    .build();
```

automatically uses:

```text
GET
30 seconds
```

If the client wants a custom timeout:

```java
new HttpRequest.Builder(url)
    .timeout(5000)
    .build();
```

---

# 23. Defensive Copying

If the Product contains mutable collections, avoid directly sharing the Builder's collection:

```java
this.headers = builder.headers;
```

Prefer:

```java
this.headers =
        Collections.unmodifiableMap(
                new HashMap<>(builder.headers)
        );
```

This creates a copy and exposes it as unmodifiable.

Conceptually:

```text
Builder Map
     |
     | copy
     v
Product Map
     |
     v
unmodifiable
```

This is important when designing immutable Products.

---

# 24. Builder Reuse

A Builder can sometimes be reused:

```java
User.Builder builder =
        new User.Builder("Mayur");

User user1 =
        builder
            .email("one@example.com")
            .build();
```

Then:

```java
User user2 =
        builder
            .email("two@example.com")
            .build();
```

However, reuse can create state-related confusion.

For simple code, creating a new Builder for each Product is usually clearer:

```java
User user1 =
        new User.Builder("Mayur")
            .email("one@example.com")
            .build();

User user2 =
        new User.Builder("Mayur")
            .email("two@example.com")
            .build();
```

---

# 25. Builder vs Factory

Both are creational patterns, but they answer different questions.

## Factory

> **Which object should I create?**

Example:

```java
Shape shape =
        ShapeFactory.createShape("CIRCLE");
```

## Builder

> **How should I configure and construct this complex object?**

Example:

```java
User user =
        new User.Builder("Mayur")
            .email("mayur@example.com")
            .age(24)
            .build();
```

### Memory Trick

```text
Factory → WHICH object?

Builder → HOW to construct it?
```

---

# 26. Builder vs Constructor

## Constructor

Good when the object is simple:

```java
User user =
        new User("Mayur", "mayur@example.com");
```

## Builder

Better when there are many optional parameters:

```java
User user =
        new User.Builder("Mayur")
            .email("mayur@example.com")
            .phone("9999999999")
            .address("Nagpur")
            .age(24)
            .build();
```

---

# 27. Builder vs Setter

Setter-based construction:

```java
User user = new User();

user.setName("Mayur");
user.setEmail("mayur@example.com");
user.setPhone("9999999999");
```

The object exists before it is fully configured.

Builder construction:

```text
Builder
   |
   | configure
   v
build()
   |
   v
complete Product
```

This is especially useful when the final Product should be immutable.

---

# 28. Advantages of Builder Pattern

### 1. Readability

```java
new User.Builder("Mayur")
    .email("mayur@example.com")
    .age(24)
    .build();
```

### 2. Optional Fields

Only required configuration needs to be specified.

### 3. Avoids Telescoping Constructors

No need for many overloaded constructors.

### 4. Supports Immutability

The Product can use final fields and no setters.

### 5. Centralized Validation

Validation can happen in `build()`.

### 6. Easy to Extend

A new optional field can usually be added as one new Builder method.

### 7. Flexible Ordering

Builder methods can usually be called in any order.

---

# 29. Disadvantages

## 1. More Code

A Builder introduces additional code and methods.

## 2. More Complexity

For a tiny object, Builder may be overengineering.

## 3. Boilerplate

Large objects may require many Builder methods.

A good rule is:

> **Use Builder when construction complexity justifies the extra abstraction.**

---

# 30. When Should You Use Builder?

Builder is a strong candidate when:

```text
Many fields
     +
Many optional fields
     +
Long constructor
     +
Need readable construction
     +
Need validation
     +
Want immutable Product
     |
     v
Builder
```

---

# 31. When Should You NOT Use Builder?

For simple objects:

```java
Point point = new Point(10, 20);
```

is perfectly reasonable.

Using:

```java
Point point =
        new Point.Builder()
            .x(10)
            .y(20)
            .build();
```

would add unnecessary complexity.

### Rule of Thumb

> **Few parameters + simple object → Constructor**
>
> **Many optional parameters + complex object → Builder**

---

# 32. Common Builder Mistakes

## Mistake 1: Forgetting `return this`

Wrong:

```java
public Builder email(String email) {
    this.email = email;
}
```

Correct:

```java
public Builder email(String email) {
    this.email = email;
    return this;
}
```

Without `return this`, chaining does not work.

---

## Mistake 2: Making the Product Constructor Public

If the goal is to force Builder-based construction:

```java
private User(Builder builder) {
}
```

is appropriate.

---

## Mistake 3: Forgetting Validation

If fields are required, validate them before construction:

```java
public User build() {

    if (name == null) {
        throw new IllegalArgumentException(
                "Name is required"
        );
    }

    return new User(this);
}
```

---

## Mistake 4: Sharing Mutable State

Avoid:

```java
this.tags = builder.tags;
```

when the Product is supposed to be immutable.

Use a defensive copy.

---

## Mistake 5: Using Builder for Tiny Objects

Do not use Builder merely because it is a design pattern.

Choose the simplest design that solves the problem.

---

# 33. Complete Interview-Ready Example

```java
public class User {

    private final String name;
    private final String email;
    private final int age;
    private final String phone;

    private User(Builder builder) {

        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.phone = builder.phone;
    }

    public static class Builder {

        // Required
        private final String name;

        // Optional
        private String email;
        private int age;
        private String phone;

        public Builder(String name) {
            this.name = name;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {

            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException(
                        "Name is required"
                );
            }

            if (age < 0) {
                throw new IllegalArgumentException(
                        "Age cannot be negative"
                );
            }

            return new User(this);
        }
    }
}
```

Usage:

```java
User user =
        new User.Builder("Mayur")
                .email("mayur@example.com")
                .age(24)
                .phone("9999999999")
                .build();
```

This demonstrates:

```text
Private Product constructor
        +
Nested Builder
        +
Required fields
        +
Optional fields
        +
Fluent methods
        +
Validation
        +
build()
        +
Immutable Product
```

---

# 34. Interview Questions

## Q1. What is the Builder Design Pattern?

Builder is a creational design pattern that separates the construction of a complex object from its representation and allows the object to be constructed step-by-step.

## Q2. Why use Builder instead of a constructor?

Builder is useful when an object has many optional parameters because it improves readability, avoids telescoping constructors, reduces null arguments, and makes object construction easier to extend.

## Q3. What is a fluent interface?

A fluent interface allows method chaining by returning the same object from each method.

```java
builder
    .name("Mayur")
    .email("mayur@example.com")
    .build();
```

## Q4. Why does Builder return `this`?

Returning `this` allows multiple Builder methods to be chained.

```java
return this;
```

## Q5. What is the Product?

The Product is the final complex object being constructed.

Example:

```java
HttpRequest
```

## Q6. What is the role of the Director?

The Director coordinates construction steps and can provide reusable construction recipes.

## Q7. Is Director mandatory?

No. Modern fluent Builders often allow the client to configure the Builder directly.

## Q8. How does Builder help immutability?

The Builder stores mutable construction state, while the final Product copies that state into final fields and exposes no setters.

## Q9. What is a telescoping constructor?

A design where multiple overloaded constructors support different combinations of optional parameters.

## Q10. What is the main advantage of Builder over telescoping constructors?

Builder avoids positional parameters and makes optional configuration explicit and readable.

---

# 35. Comparison Table

| Feature | Telescoping Constructor | Builder |
|---|---|---|
| Readability | Low as parameters grow | High |
| Optional fields | Awkward | Easy |
| Null arguments | Common | Usually avoided |
| Parameter order | Important | Usually flexible |
| Immutability | Possible | Very natural |
| Validation | Possible | Easy in `build()` |
| Extensibility | Poorer | Better |
| Boilerplate | Many constructors | Builder methods |
| Best for | Simple/few parameters | Complex objects |

---

# 36. Builder Pattern Mental Model

Think about ordering food.

Without Builder:

```text
Give me:
size,
bread,
cheese,
sauce,
vegetables,
spice,
drink
```

You must specify everything in one large request.

With Builder:

```text
OrderBuilder
    |
    +-- size(LARGE)
    +-- bread(WHEAT)
    +-- cheese(MOZZARELLA)
    +-- sauce(TOMATO)
    +-- spice(HIGH)
    |
    +-- build()
          |
          v
        Order
```

The Builder collects configuration.

`build()` creates the final object.

---

# 37. Another Mental Model: House Construction

A complex object can be thought of like a house:

```text
HouseBuilder
    |
    +-- bedrooms(3)
    +-- bathrooms(2)
    +-- garage(true)
    +-- garden(true)
    |
    +-- build()
          |
          v
        House
```

The Builder is responsible for construction.

The final `House` is the Product.

---

# 38. Final Mental Model

```text
             CLIENT
                |
                v
          Create Builder
                |
                v
       Configure Builder
                |
        +-------+-------+
        |       |       |
        v       v       v
      field   field   field
        \       |       /
         \      |      /
          v     v     v
              build()
                |
                v
             PRODUCT
                |
                v
        Fully constructed
        immutable object
```

The most important idea is:

> **Builder = Configure step-by-step → `build()` → Get the final object.**

---

# 39. Final Cheat Sheet

```text
BUILDER PATTERN
       |
       v
Creational Design Pattern
       |
       v
Construct complex objects step-by-step
       |
       +-------------------------+
       |                         |
       v                         v
Many optional fields       Complex construction
       |                         |
       +------------+------------+
                    |
                    v
                 Builder
                    |
                    v
              Fluent methods
                    |
                    v
                 build()
                    |
                    v
                Product
```

### Remember these five things:

1. **Builder separates construction from the final Product.**
2. **It is useful for complex objects with many optional fields.**
3. **Builder methods usually return `this` for method chaining.**
4. **`build()` creates the final object and can perform validation.**
5. **The final Product is often immutable.**

### One-line memory trick

> **Builder = Configure step-by-step → `build()` → Get the final object.**

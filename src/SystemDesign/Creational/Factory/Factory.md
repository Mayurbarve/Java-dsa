# Factory Method Design Pattern

## 1. What is the Factory Method Pattern?

The **Factory Method Design Pattern** is a **creational design pattern** that provides an interface for creating objects in a superclass, while allowing subclasses to alter the type of objects that will be created.

### It is particularly useful when:

- The exact type of object to be created is not known until runtime.
- Object creation logic is complex, repetitive, or needs encapsulation.
- You want to follow the **Open/Closed Principle** — open for extension, closed for modification.
- You have multiple objects of a similar type and conditional logic is starting to grow.

### Core idea

> **Let subclasses decide which concrete Product to create, while the base Creator defines the common workflow.**

---

# 2. The Problem: Sending Notifications

Imagine a web application that sends notifications.

Initially, it only supports email:

```java
class EmailNotification {

    public void send() {
        System.out.println("Sending an Email notification...");
    }
}
```

The service directly creates the notification:

```java
class NotificationService {

    public void sendNotification(String message) {

        EmailNotification email =
                new EmailNotification();

        email.send(message);
    }
}
```

This works while there is only one notification type.

---

## Adding SMS

A new requirement arrives:

```text
Support SMS notifications.
```

The service now needs conditional logic:

```java
class NotificationService {

    public void sendNotification(
            String type,
            String message
    ) {

        if (type.equals("EMAIL")) {

            EmailNotification email =
                    new EmailNotification();

            email.send(message);

        } else if (type.equals("SMS")) {

            SMSNotification sms =
                    new SMSNotification();

            sms.send(message);
        }
    }
}
```

Still manageable.

---

## Adding More Notification Types

Now imagine adding:

```text
Push
Slack
WhatsApp
```

The service becomes a large collection of conditions.

Now the service is responsible for:

1. Knowing every concrete notification class.
2. Deciding which object to create.
3. Creating the object.
4. Sending the notification.

This creates tight coupling.

---

# 3. Problems with the Conditional Approach

### Problem 1: Core logic must be modified

Every new notification type requires modifying the same service.

### Problem 2: Tight coupling

`NotificationService` directly knows about:

```text
EmailNotification
SMSNotification
PushNotification
SlackNotification
WhatsAppNotification
```

### Problem 3: Testing becomes harder

Object creation logic and notification behavior are mixed together.

### Problem 4: Open/Closed Principle violation

The class is not easily extendable without modifying existing code.

The desired approach is:

```text
Add new notification type
        |
        v
Add new classes
        |
        v
Existing core workflow remains unchanged
```

This is where Factory Method helps.

---

# 4. Simple Factory: A First Attempt

Before the full Factory Method Pattern, a common refactoring is to extract object creation into a separate class.

This is called a:

> **Simple Factory**

It is useful in real-world code, but it is **not a formal Gang of Four (GoF) design pattern**.

Example:

```java
class SimpleNotificationFactory {

    public static Notification createNotification(
            String type
    ) {

        return switch (type) {

            case "EMAIL" ->
                    new EmailNotification();

            case "SMS" ->
                    new SMSNotification();

            case "PUSH" ->
                    new PushNotification();

            default ->
                    throw new IllegalArgumentException(
                            "Unknown type"
                    );
        };
    }
}
```

The service becomes cleaner:

```java
class NotificationService {

    public void sendNotification(
            String type,
            String message
    ) {

        Notification notification =
                SimpleNotificationFactory
                        .createNotification(type);

        notification.send(message);
    }
}
```

---

## Problem with Simple Factory

The factory still contains:

```java
switch (type)
```

Every time a new notification type is introduced, the factory must be modified.

So:

```text
Add WhatsApp
      |
      v
Modify SimpleNotificationFactory
      |
      v
Add another switch case
```

The creation decision is still centralized.

Factory Method solves this differently.

---

# 5. What is Factory Method?

The Factory Method Pattern takes object creation and **hands it off to subclasses**.

Instead of one central factory deciding what to create:

```text
One Factory
     |
     +-- EMAIL
     +-- SMS
     +-- PUSH
     +-- SLACK
```

we delegate creation to specialized Creator classes:

```text
EmailNotificationCreator
        |
        +--> EmailNotification

SMSNotificationCreator
        |
        +--> SMSNotification

PushNotificationCreator
        |
        +--> PushNotification
```

### In simple words

> **Each subclass defines its own way of creating the Product.**

The base Creator defines the common workflow, while subclasses decide the actual Product.

---

# 6. Factory Method vs Conditional Logic

### Old approach

```java
if (type.equals("EMAIL")) {
    return new EmailNotification();
}

if (type.equals("SMS")) {
    return new SMSNotification();
}
```

### Factory Method approach

```text
EmailNotificationCreator
        |
        +--> new EmailNotification()

SMSNotificationCreator
        |
        +--> new SMSNotification()
```

The creation responsibility is distributed to specialized creators.

---

# 7. Real-World Analogy

Think of a food delivery platform.

### Simple Factory

Imagine one centralized kitchen:

```text
Central Kitchen
      |
      +-- Pizza
      +-- Sushi
      +-- Burger
```

The kitchen decides what food to prepare.

### Factory Method

Instead, imagine:

```text
Pizza Restaurant
      |
      +--> prepares Pizza

Sushi Restaurant
      |
      +--> prepares Sushi

Burger Restaurant
      |
      +--> prepares Burger
```

Each restaurant knows how to prepare its own product.

The platform works with the appropriate restaurant without needing to know the preparation details.

---

# 8. Factory Method Structure

```text
              Client
                 |
                 v
          +---------------+
          |    Creator    |
          +-------+-------+
                  |
                  | factoryMethod()
                  v
             << Product >>
                  ^
                  |
          +-------+-------+
          |               |
          v               v
   ConcreteCreatorA  ConcreteCreatorB
          |               |
          v               v
 ConcreteProductA   ConcreteProductB
```

---

# 9. Components of Factory Method

## 1. Product

The Product is an interface or abstract class defining the common contract.

Example:

```java
interface Notification {

    void send(String message);
}
```

All notification types implement this interface.

The rest of the application can work with:

```java
Notification
```

without knowing the concrete type.

---

## 2. Concrete Product

Concrete Products are the actual implementations of the Product interface.

Examples:

```text
EmailNotification
SMSNotification
PushNotification
SlackNotification
```

Each provides its own implementation of:

```java
send()
```

---

## 3. Creator

The Creator is an abstract class or interface that declares the Factory Method.

Example:

```text
NotificationCreator
        |
        +-- createNotification()
        |
        +-- send()
```

The Creator usually does two things:

1. Declares the Factory Method.
2. Contains shared logic that uses the Product.

The important idea is:

> **Creator defines the workflow, but does not decide the concrete Product.**

---

## 4. Concrete Creator

A Concrete Creator extends the Creator and overrides the Factory Method.

Examples:

```text
EmailNotificationCreator
        |
        +--> EmailNotification

SMSNotificationCreator
        |
        +--> SMSNotification

PushNotificationCreator
        |
        +--> PushNotification
```

Each Concrete Creator is responsible for its corresponding Product.

---

# 10. Factory Method Workflow

```text
Client
   |
   | selects Creator
   v
ConcreteCreator
   |
   | calls common operation
   v
Creator
   |
   | calls factoryMethod()
   v
ConcreteCreator
   |
   | creates
   v
ConcreteProduct
   |
   | returned as Product
   v
Creator uses Product
```

---

# 11. Step-by-Step Understanding

### Step 1: Client selects a Creator

The client decides which Concrete Creator to use:

```java
NotificationCreator creator =
        new EmailNotificationCreator();
```

### Step 2: Client calls the common operation

```java
creator.send("Welcome!");
```

The `send()` method belongs to the Creator.

### Step 3: Creator calls the Factory Method

Inside the Creator's workflow:

```text
send()
   |
   v
createNotification()
```

Because the method is overridden, the Concrete Creator implementation is executed.

### Step 4: Concrete Creator creates the Product

For an email creator:

```text
EmailNotificationCreator
          |
          v
new EmailNotification()
```

The object is returned as:

```text
Notification
```

### Step 5: Creator uses the Product

The Creator calls:

```java
notification.send(message);
```

The correct concrete implementation executes.

---

# 12. The Most Important Relationship

Remember:

```text
Creator
   |
   | defines workflow
   v
Factory Method
   |
   | overridden by
   v
Concrete Creator
   |
   | creates
   v
Concrete Product
```

### In one sentence

> **The Creator controls HOW the work happens, while the Concrete Creator decides WHAT object is created.**

---

# 13. Factory Method and Polymorphism

Factory Method heavily uses polymorphism.

Suppose:

```java
NotificationCreator creator;
```

At runtime:

```java
creator =
        new EmailNotificationCreator();
```

Then:

```java
creator.send("Hello");
```

The Creator's workflow calls:

```java
createNotification();
```

Java's dynamic dispatch selects:

```java
EmailNotificationCreator.createNotification()
```

which creates:

```java
EmailNotification
```

Flow:

```text
NotificationCreator reference
        |
        v
EmailNotificationCreator object
        |
        v
Overridden createNotification()
        |
        v
EmailNotification
```

---

# 14. Why Factory Method Supports Open/Closed Principle

Suppose we already have:

```text
EmailNotification
EmailNotificationCreator

SMSNotification
SMSNotificationCreator
```

Now we want:

```text
WhatsAppNotification
```

We can add:

```text
WhatsAppNotification
WhatsAppNotificationCreator
```

without changing the existing notification Creator workflow.

This means new behavior is introduced through extension rather than modifying the existing core logic.

---

# 15. Factory Method vs Simple Factory

| Feature | Simple Factory | Factory Method |
|---|---|---|
| Formal GoF pattern | No | Yes |
| Creation logic | Centralized | Delegated |
| Uses switch/if | Often | Usually not |
| Subclass-based creation | No | Yes |
| Open/Closed support | Limited | Stronger |
| Adding product | Modify factory | Add Concrete Creator/Product |
| Complexity | Lower | Higher |
| Best for | Small/simple systems | Extensible systems |

### Memory Trick

```text
Simple Factory
    ↓
ONE factory decides

Factory Method
    ↓
SUBCLASSES decide
```

---

# 16. Factory Method vs Builder

These patterns solve different problems.

## Factory Method

Answers:

> **Which type of object should be created?**

Example:

```text
EmailNotificationCreator
        ↓
EmailNotification
```

## Builder

Answers:

> **How should a complex object be constructed/configured?**

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

# 17. Factory Method vs Abstract Factory

These patterns are related but different.

### Factory Method

Usually focuses on creating:

```text
ONE Product
```

Example:

```text
Notification
```

### Abstract Factory

Creates a family of related products.

Example:

```text
Windows UI Factory
    |
    +-- WindowsButton
    +-- WindowsCheckbox

Mac UI Factory
    |
    +-- MacButton
    +-- MacCheckbox
```

### Memory Trick

```text
Factory Method
    → One product creation method

Abstract Factory
    → Family of related products
```

---

# 18. Advantages

## 1. Loose Coupling

Client/core code can depend on:

```java
Notification
```

instead of concrete classes.

## 2. Open/Closed Principle

New product types can be introduced through new classes.

## 3. Encapsulated Creation

The logic for creating a particular product lives in its Concrete Creator.

## 4. Shared Workflow

The Creator can define common behavior once:

```text
send()
   |
   +--> createNotification()
   |
   +--> notification.send()
```

## 5. Easier Testing

Concrete product creation and behavior can be tested independently.

---

# 19. Disadvantages

## 1. More Classes

A new Product may require:

```text
ConcreteProduct
+
ConcreteCreator
```

## 2. More Abstraction

The design is more complicated than:

```java
new EmailNotification();
```

for simple applications.

## 3. Client Still Selects a Creator

The client often needs to decide:

```java
new EmailNotificationCreator()
```

or:

```java
new SMSNotificationCreator()
```

A configuration mechanism may be needed when this choice is dynamic.

---

# 20. When Should You Use Factory Method?

Use Factory Method when:

- The exact Product type varies.
- Creation should be delegated to subclasses.
- You expect new Product types to be added.
- You want to reduce coupling to concrete classes.
- The Creator has meaningful shared workflow.
- Object creation logic should be encapsulated.
- You want extension without modifying the common Creator workflow.

---

# 21. When Should You Avoid It?

If there is:

```text
One simple object
+
Simple construction
+
No expected variation
```

then:

```java
new EmailNotification();
```

may be better.

Factory Method introduces additional abstraction, so the benefit should justify the complexity.

---

# 22. Practical Example: Document Export System

Factory Method can also be used for document exporting.

Suppose a reporting system supports:

```text
PDF
HTML
CSV
```

Each format has its own formatting logic.

Architecture:

```text
                 DocumentCreator
                       |
                createDocument()
                       ^
                       |
          +------------+------------+
          |            |            |
          v            v            v
   PdfExportCreator HtmlExportCreator CsvExportCreator
          |            |            |
          v            v            v
     PdfDocument   HtmlDocument   CsvDocument
```

The common Creator workflow can handle:

```text
create document
      |
      v
write header
      |
      v
write rows
      |
      v
write footer
```

while each Concrete Creator decides which Document implementation is created.

---

# 23. Shared Workflow Concept

This is one of the most important reasons to use Factory Method.

Imagine:

```text
Creator
   |
   +-- export()
          |
          +-- document = createDocument()
          |
          +-- document.getHeader()
          |
          +-- format rows
          |
          +-- document.getFooter()
```

The workflow stays the same.

Only this part changes:

```text
createDocument()
```

For PDF:

```text
new PdfDocument()
```

For HTML:

```text
new HtmlDocument()
```

For CSV:

```text
new CsvDocument()
```

This is the heart of Factory Method.

---

# 24. Common Mistakes

## Mistake 1: Putting all creation logic back into the Creator

Bad:

```java
if (type.equals("EMAIL")) {
    return new EmailNotification();
}

if (type.equals("SMS")) {
    return new SMSNotification();
}
```

This recreates the central conditional problem.

---

## Mistake 2: Confusing Simple Factory with Factory Method

This:

```java
NotificationFactory.create("EMAIL");
```

is not automatically Factory Method.

If one factory class uses a switch to create everything, that is generally a **Simple Factory**.

---

## Mistake 3: Forgetting the Product abstraction

Products should normally share a common contract:

```java
interface Notification {
    void send(String message);
}
```

This allows the Creator to work with them polymorphically.

---

## Mistake 4: Creating unnecessary abstraction

If there is no meaningful variation or extension requirement, Factory Method may be overengineering.

---

# 25. Interview Questions

## Q1. What is Factory Method?

Factory Method is a creational design pattern that defines an interface for creating an object while allowing subclasses to decide which concrete object to create.

## Q2. What problem does Factory Method solve?

It reduces coupling between core logic and concrete Product classes and allows object creation to be extended through subclasses.

## Q3. What is the difference between Simple Factory and Factory Method?

Simple Factory centralizes creation in one class, often using `if-else` or `switch`. Factory Method delegates creation to subclasses through an overridable Factory Method.

## Q4. Is Simple Factory a GoF pattern?

No. It is a common refactoring technique, but it is not one of the original 23 Gang of Four patterns.

## Q5. What is the Product?

The common interface or abstract class implemented by all Concrete Products.

Example:

```java
Notification
```

## Q6. What is a Concrete Product?

A specific implementation of the Product.

Examples:

```text
EmailNotification
SMSNotification
PushNotification
```

## Q7. What is the Creator?

The base class or interface that declares the Factory Method and may contain shared workflow.

## Q8. What is a Concrete Creator?

A subclass that overrides the Factory Method and creates a specific Concrete Product.

## Q9. Is a Director part of Factory Method?

No. **Director is associated with the classic Builder structure**, not a required participant of Factory Method.

## Q10. How does Factory Method support Open/Closed Principle?

New Product types can generally be introduced by adding new Concrete Product and Concrete Creator classes without modifying the existing Creator workflow.

## Q11. What concept is heavily used by Factory Method?

**Polymorphism** is central to the pattern because the Creator works with the Product abstraction while subclasses provide the concrete creation behavior.

---

# 26. Quick Comparison

| Pattern | Main Question |
|---|---|
| Factory Method | Which Concrete Product should a subclass create? |
| Simple Factory | Which Product should this factory create? |
| Abstract Factory | Which family of related Products should be created? |
| Builder | How should a complex Product be constructed? |
| Singleton | How can one shared instance be controlled? |

---

# 27. Mental Model

Think of a notification company:

```text
                NotificationCreator
                       |
                       | send()
                       |
                       v
              createNotification()
                       ^
                       |
              subclass decides
                       |
          +------------+------------+
          |            |            |
          v            v            v
       Email          SMS          Push
       Creator       Creator       Creator
          |            |            |
          v            v            v
       Email          SMS          Push
    Notification   Notification   Notification
```

The base Creator says:

> "I know how the notification workflow works."

The Concrete Creator says:

> "I know which notification object I need."

---

# 28. One-Line Memory Trick

> **Factory Method = Parent defines the creation method, child decides what to create.**

Or:

```text
Creator → defines HOW
Subclass → decides WHAT
```

---

# 29. Final Cheat Sheet

```text
                 FACTORY METHOD
                       |
                       v
              Creational Pattern
                       |
                       v
             Product abstraction
                       |
                       v
                Creator class
                       |
                       +-- factoryMethod()
                       |
                       v
              Concrete Creator
                       |
                       v
               Concrete Product
```

### Remember these five things:

1. **Factory Method is a creational design pattern.**
2. **The Product defines the common contract.**
3. **The Creator defines the Factory Method and often the common workflow.**
4. **Concrete Creators decide which Concrete Product to create.**
5. **New Product types can be added through new classes rather than modifying the existing creation workflow.**

### Most important distinction

```text
Simple Factory
    → One factory decides using conditions.

Factory Method
    → Subclasses decide through overriding.
```

### Final memory sentence

> **Factory Method moves object creation from the base workflow to specialized subclasses, allowing the system to extend with new Product/Creator pairs while keeping the common workflow unchanged.**

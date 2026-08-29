# Abstract Factory Design Pattern

## 1. What is the Abstract Factory Pattern?

The **Abstract Factory Design Pattern** is a **creational design pattern** that provides an interface for creating **families of related objects** without specifying their concrete classes.

> **Abstract Factory creates a family of related objects that are designed to work together.**

For example, a cross-platform UI may have:

```text
Windows → WindowsButton + WindowsCheckbox + WindowsTextField
Mac     → MacButton + MacCheckbox + MacTextField
Linux   → LinuxButton + LinuxCheckbox + LinuxTextField
```

The application works with `Button`, `Checkbox`, and `TextField` abstractions instead of concrete platform classes.

---

# 2. The Problem Abstract Factory Solves

Suppose we are building a UI application with:

```text
Button
Checkbox
```

for:

```text
Windows
Mac
```

Without Abstract Factory, the client may contain logic such as:

```java
if (os.equals("WINDOWS")) {
    Button button = new WindowsButton();
    Checkbox checkbox = new WindowsCheckbox();
} else if (os.equals("MAC")) {
    Button button = new MacButton();
    Checkbox checkbox = new MacCheckbox();
}
```

The client is now coupled to every concrete implementation.

As more products and platforms are added, the conditional logic grows.

---

# 3. The Bigger Problem: Related Products

The important problem is not simply that there are many objects.

The objects belong to **product families**:

```text
Windows Family
    |
    +-- WindowsButton
    +-- WindowsCheckbox
    +-- WindowsTextField

Mac Family
    |
    +-- MacButton
    +-- MacCheckbox
    +-- MacTextField
```

We want to avoid accidentally mixing families:

```text
WindowsButton
+
MacCheckbox
+
LinuxTextField
```

Instead, we want one consistent family:

```text
Windows Factory
    |
    +-- WindowsButton
    +-- WindowsCheckbox
    +-- WindowsTextField
```

or:

```text
Mac Factory
    |
    +-- MacButton
    +-- MacCheckbox
    +-- MacTextField
```

This is the central problem solved by Abstract Factory.

---

# 4. Core Idea

Think of an Abstract Factory as an interface for creating a **family of related products**.

Instead of:

```java
new WindowsButton();
new WindowsCheckbox();
```

the client uses:

```java
factory.createButton();
factory.createCheckbox();
```

The selected factory determines which family is created.

```text
                  AbstractFactory
                  /      |       \\
                 /       |        \\
                v        v         v
          createButton() createCheckbox() createTextField()
                |        |         |
                +--------+---------+
                         |
                         v
                 Concrete Factory
                         |
             +-----------+-----------+
             |           |           |
             v           v           v
          Button     Checkbox     TextField
```

---

# 5. Product Family

This is the **most important concept** in Abstract Factory.

A product family is a group of related products designed to work together.

For example:

```text
Light Theme
    |
    +-- LightButton
    +-- LightCheckbox
    +-- LightTextField

Dark Theme
    |
    +-- DarkButton
    +-- DarkCheckbox
    +-- DarkTextField
```

The factory creates products belonging to the same family.

---

# 6. Abstract Factory Structure

```text
                         Client
                           |
                           v
                  +----------------+
                  | AbstractFactory|
                  +----------------+
                    /      |      \\
                   /       |       \\
                  v        v        v
             createA() createB() createC()
                  |        |        |
                  +--------+--------+
                           |
             +-------------+-------------+
             |                           |
             v                           v
     ConcreteFactoryA             ConcreteFactoryB
             |                           |
       +-----+-----+               +-----+-----+
       |     |     |               |     |     |
       v     v     v               v     v     v
      A1    A2    A3              B1    B2    B3
```

`A1`, `A2`, `A3` belong to one family.

`B1`, `B2`, `B3` belong to another family.

---

# 7. Participants

## 1. Abstract Factory

Defines methods for creating each product type.

```java
interface UIFactory {

    Button createButton();

    Checkbox createCheckbox();

    TextField createTextField();
}
```

## 2. Concrete Factory

Implements the Abstract Factory and creates a complete product family.

Examples:

```text
WindowsUIFactory
MacUIFactory
LinuxUIFactory
```

## 3. Abstract Product

Defines the common contract for a product type.

```java
interface Button {
    void render();
}
```

```java
interface Checkbox {
    void render();
}
```

## 4. Concrete Product

The actual implementations:

```text
WindowsButton
MacButton
LinuxButton

WindowsCheckbox
MacCheckbox
LinuxCheckbox
```

## 5. Client

Uses the Abstract Factory and Abstract Products without depending directly on concrete classes.

---

# 8. Complete Example: Cross-Platform UI

We need:

```text
Button
Checkbox
```

for:

```text
Windows
Mac
```

## Step 1: Abstract Products

```java
interface Button {
    void render();
}

interface Checkbox {
    void render();
}
```

---

# 9. Concrete Products

## Windows Button

```java
class WindowsButton implements Button {

    @Override
    public void render() {
        System.out.println("Rendering Windows Button");
    }
}
```

## Windows Checkbox

```java
class WindowsCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Rendering Windows Checkbox");
    }
}
```

## Mac Button

```java
class MacButton implements Button {

    @Override
    public void render() {
        System.out.println("Rendering Mac Button");
    }
}
```

## Mac Checkbox

```java
class MacCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Rendering Mac Checkbox");
    }
}
```

Product families:

```text
Windows Family
    |
    +-- WindowsButton
    +-- WindowsCheckbox

Mac Family
    |
    +-- MacButton
    +-- MacCheckbox
```

---

# 10. Abstract Factory

```java
interface UIFactory {

    Button createButton();

    Checkbox createCheckbox();
}
```

The factory defines **what can be created**, not the concrete implementation.

---

# 11. Concrete Windows Factory

```java
class WindowsUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
```

This creates the Windows family:

```text
WindowsUIFactory
       |
       +-- WindowsButton
       +-- WindowsCheckbox
```

---

# 12. Concrete Mac Factory

```java
class MacUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```

This creates the Mac family:

```text
MacUIFactory
       |
       +-- MacButton
       +-- MacCheckbox
```

---

# 13. Client

The client depends only on abstractions:

```java
class Application {

    private final Button button;
    private final Checkbox checkbox;

    public Application(UIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void render() {
        button.render();
        checkbox.render();
    }
}
```

The `Application` does not know about:

```text
WindowsButton
WindowsCheckbox
MacButton
MacCheckbox
```

---

# 14. Using the Factory

## Windows

```java
UIFactory factory = new WindowsUIFactory();

Application app = new Application(factory);

app.render();
```

Output:

```text
Rendering Windows Button
Rendering Windows Checkbox
```

## Mac

```java
UIFactory factory = new MacUIFactory();

Application app = new Application(factory);

app.render();
```

Output:

```text
Rendering Mac Button
Rendering Mac Checkbox
```

The `Application` code does not change.

Only the factory changes.

---

# 15. Complete Flow

For Windows:

```text
Client
  |
  v
WindowsUIFactory
  |
  +---- createButton()
  |          |
  |          v
  |    WindowsButton
  |
  +---- createCheckbox()
             |
             v
       WindowsCheckbox
```

For Mac:

```text
Client
  |
  v
MacUIFactory
  |
  +---- createButton()
  |          |
  |          v
  |       MacButton
  |
  +---- createCheckbox()
             |
             v
        MacCheckbox
```

---

# 16. Why Is It Called "Abstract Factory"?

The client interacts with an abstraction:

```java
UIFactory factory;
```

rather than a concrete factory.

The actual factory is selected at runtime:

```java
factory = new WindowsUIFactory();
```

or:

```java
factory = new MacUIFactory();
```

Then the client uses:

```java
factory.createButton();
factory.createCheckbox();
```

---

# 17. Switching the Entire Product Family

One of the biggest advantages is that the application can switch families as a unit.

```text
WindowsUIFactory
      |
      +-- WindowsButton
      +-- WindowsCheckbox

           ↓ switch factory

MacUIFactory
      |
      +-- MacButton
      +-- MacCheckbox
```

The Client does not need to change its product-creation code.

---

# 18. Preventing Incompatible Products

Suppose different platforms have different visual styles or implementation assumptions.

We want:

```text
Windows Factory
    ↓
Windows Button
Windows Checkbox
Windows TextField
```

rather than mixing:

```text
Windows Button
Mac Checkbox
Linux TextField
```

A Concrete Factory naturally keeps related products together.

---

# 19. Open/Closed Principle

Abstract Factory supports extension by introducing new Concrete Factories and Concrete Products.

Suppose we have:

```text
WindowsUIFactory
MacUIFactory
```

Now add:

```text
LinuxUIFactory
```

with:

```text
LinuxButton
LinuxCheckbox
LinuxTextField
```

The existing Client can continue working with:

```java
UIFactory
```

without knowing the Linux implementations.

---

# 20. Abstract Factory vs Factory Method

This is one of the most important interview comparisons.

## Factory Method

Factory Method generally focuses on creating a Product through a method that subclasses override.

```text
NotificationCreator
        |
        +-- createNotification()
```

Concrete creators:

```text
EmailNotificationCreator
        ↓
EmailNotification

SMSNotificationCreator
        ↓
SMSNotification
```

Think:

```text
Factory Method
    ↓
Usually one product hierarchy
```

## Abstract Factory

Abstract Factory creates multiple related Product types.

```text
UIFactory
    |
    +-- createButton()
    +-- createCheckbox()
    +-- createTextField()
```

Concrete factory:

```text
WindowsUIFactory
    |
    +-- WindowsButton
    +-- WindowsCheckbox
    +-- WindowsTextField
```

Think:

```text
Abstract Factory
    ↓
Family of related products
```

---

# 21. Abstract Factory vs Simple Factory

## Simple Factory

Usually one factory decides what concrete object to create:

```text
Factory
   |
   +-- createButton()
        |
        +-- WindowsButton
        +-- MacButton
```

## Abstract Factory

Different Concrete Factories create consistent product families:

```text
WindowsUIFactory
   |
   +-- WindowsButton
   +-- WindowsCheckbox
   +-- WindowsTextField

MacUIFactory
   |
   +-- MacButton
   +-- MacCheckbox
   +-- MacTextField
```

Memory trick:

```text
Simple Factory
    → One factory decides

Abstract Factory
    → Factory represents a product family
```

---

# 22. Abstract Factory vs Builder

## Builder

Focuses on constructing **one complex object step-by-step**.

```java
User user = new User.Builder("Mayur")
        .email("mayur@example.com")
        .age(24)
        .build();
```

## Abstract Factory

Focuses on creating **multiple related objects**.

```java
UIFactory factory = new WindowsUIFactory();

Button button = factory.createButton();
Checkbox checkbox = factory.createCheckbox();
```

Memory trick:

```text
Builder
→ One complex object
→ Step-by-step construction

Abstract Factory
→ Multiple related objects
→ Consistent product family
```

---

# 23. Abstract Factory and Dependency Injection

Abstract Factory works naturally with Dependency Injection.

Instead of the Application deciding:

```java
new WindowsUIFactory();
```

an external configuration layer can provide:

```java
Application app = new Application(factory);
```

The Application only depends on:

```java
UIFactory
```

This makes it easier to test and change implementations.

For example, a test can provide:

```text
MockUIFactory
```

instead of a real platform factory.

---

# 24. Real-World Example: Database Family

Imagine a system supporting:

```text
MySQL
PostgreSQL
```

Each provider needs:

```text
Connection
Command
Transaction
```

The abstraction can be:

```text
DatabaseFactory
    |
    +-- createConnection()
    +-- createCommand()
    +-- createTransaction()
```

Concrete families:

```text
MySQLFactory
    |
    +-- MySQLConnection
    +-- MySQLCommand
    +-- MySQLTransaction

PostgreSQLFactory
    |
    +-- PostgreSQLConnection
    +-- PostgreSQLCommand
    +-- PostgreSQLTransaction
```

The application works with:

```text
Connection
Command
Transaction
```

without directly depending on provider-specific implementations.

---

# 25. Real-World Example: Theme System

Suppose an application supports:

```text
Light Theme
Dark Theme
```

Each theme contains:

```text
Button
Menu
Dialog
TextField
```

The families become:

```text
LightThemeFactory
    |
    +-- LightButton
    +-- LightMenu
    +-- LightDialog
    +-- LightTextField

DarkThemeFactory
    |
    +-- DarkButton
    +-- DarkMenu
    +-- DarkDialog
    +-- DarkTextField
```

Switching the factory switches the entire UI family.

---

# 26. Advantages

## 1. Creates Consistent Product Families

A Concrete Factory ensures related products come from the same family.

## 2. Reduces Coupling

The Client depends on abstractions rather than concrete classes.

## 3. Easy to Switch Product Families

Changing the factory can change the entire family.

## 4. Supports Open/Closed Principle

New families can be introduced with new Concrete Factories and Products.

## 5. Centralizes Family Creation

The rules for creating a family live inside its Concrete Factory.

## 6. Improves Consistency

The factory makes it easier to prevent incompatible products from being combined.

---

# 27. Disadvantages

## 1. More Classes

A new family may require:

```text
Concrete Factory
+
Concrete Product A
+
Concrete Product B
+
Concrete Product C
```

## 2. Adding a New Product Type Can Be Expensive

Suppose the factory currently creates:

```text
Button
Checkbox
```

and we add:

```text
TextField
```

The Abstract Factory interface must change:

```java
TextField createTextField();
```

Then every Concrete Factory must implement it.

This is an important tradeoff.

## 3. More Abstraction

For a small application, Abstract Factory can be unnecessary complexity.

---

# 28. When Should You Use Abstract Factory?

Use Abstract Factory when:

- You need multiple related products.
- Products belong to identifiable families.
- Products should work together.
- You want to switch entire product families.
- You want to hide concrete classes from the Client.
- The application supports multiple platforms, themes, vendors, environments, or configurations.

Common examples:

```text
Cross-platform UI
Database providers
Cloud providers
Operating-system-specific components
Theme systems
Document families
Payment provider families
Messaging provider families
```

---

# 29. When Should You Avoid It?

Avoid Abstract Factory when:

```text
Only one product exists
+
No product family
+
Simple object creation
```

In such cases, a constructor or simpler Factory may be enough.

The key question is:

> **Do I have a family of related products that should be created consistently?**

If yes, Abstract Factory becomes a strong candidate.

---

# 30. Common Mistakes

## Mistake 1: Thinking "Many Factory Methods" Automatically Means Abstract Factory

The products must represent **related product types belonging to a family**.

## Mistake 2: Mixing Product Families

Avoid:

```text
WindowsButton
+
MacCheckbox
+
LinuxTextField
```

when those products are intended to form a single consistent family.

## Mistake 3: Confusing Factory Method and Abstract Factory

Factory Method:

```text
Creator
   ↓
one factory method
   ↓
Concrete Product
```

Abstract Factory:

```text
Factory
   |
   +-- Product A
   +-- Product B
   +-- Product C
```

## Mistake 4: Creating Abstract Factory Too Early

If there is no meaningful product family or variation, the pattern may be overengineering.

---

# 31. Interview Questions

## Q1. What is Abstract Factory?

Abstract Factory is a creational design pattern that provides an interface for creating families of related objects without specifying their concrete classes.

## Q2. What problem does Abstract Factory solve?

It hides concrete product creation and helps ensure that related products from the same family are created consistently.

## Q3. What is a product family?

A group of related products designed to work together.

Example:

```text
Windows Button
Windows Checkbox
Windows TextField
```

## Q4. What are the main components?

```text
Abstract Factory
Concrete Factory
Abstract Product
Concrete Product
Client
```

## Q5. What is the difference between Factory Method and Abstract Factory?

Factory Method generally delegates creation of a product to subclasses through an overridable method.

Abstract Factory provides multiple creation methods for a family of related products.

## Q6. Why does Abstract Factory reduce coupling?

Because the Client works with abstractions such as:

```java
UIFactory
Button
Checkbox
```

rather than concrete implementations.

## Q7. What happens when we add a new product family?

Usually we add a new Concrete Factory and its Concrete Products. Existing Client code can continue using the same abstractions.

## Q8. What happens when we add a new product type?

The Abstract Factory interface may need a new creation method, and every Concrete Factory must implement it.

## Q9. Can Abstract Factory use Factory Method?

Yes. The individual creation methods of an Abstract Factory can be implemented using Factory Method-style polymorphism. The patterns are closely related.

## Q10. What principles does Abstract Factory encourage?

It encourages programming to interfaces, low coupling, dependency inversion, and extension through new Concrete Factories and Products.

---

# 32. Quick Comparison

| Pattern | Main Question |
|---|---|
| Simple Factory | Which Product should this factory create? |
| Factory Method | Which Concrete Product should the subclass create? |
| Abstract Factory | Which family of related Products should be created? |
| Builder | How should a complex Product be constructed? |
| Singleton | How can one shared instance be controlled? |

---

# 33. Easiest Way to Remember the Three Factory Concepts

```text
Simple Factory
        ↓
ONE factory decides
"Which object?"

Factory Method
        ↓
SUBCLASS decides
"Which Concrete Product?"

Abstract Factory
        ↓
FAMILY factory decides
"Which related Product family?"
```

---

# 34. Abstract Factory vs Factory Method Diagram

## Factory Method

```text
             Creator
                |
         createProduct()
                |
                v
        Concrete Creator
                |
                v
        Concrete Product
```

Think:

```text
ONE product hierarchy
```

## Abstract Factory

```text
             Abstract Factory
              /      |      \\
             /       |       \\
            v        v        v
        ProductA  ProductB  ProductC
            |        |        |
            +--------+--------+
                     |
              Concrete Factory
```

Think:

```text
FAMILY of products
```

---

# 35. Mental Model: Furniture Store

Imagine a furniture system with two styles:

```text
Modern
Classic
```

Each style has:

```text
Chair
Sofa
Table
```

So:

```text
Modern Factory
    |
    +-- Modern Chair
    +-- Modern Sofa
    +-- Modern Table

Classic Factory
    |
    +-- Classic Chair
    +-- Classic Sofa
    +-- Classic Table
```

The Client asks for:

```text
Chair
Sofa
Table
```

It does not need to know whether they are Modern or Classic.

The selected factory determines the entire family.

That is Abstract Factory.

---

# 36. Final Mental Model

```text
                 ABSTRACT FACTORY
                         |
                         v
                Product Family
                         |
          +--------------+--------------+
          |              |              |
          v              v              v
       Product A      Product B      Product C
          |              |              |
          +--------------+--------------+
                         |
                         v
                 Concrete Factory
                         |
                selects one family
                         |
                         v
                       Client
```

The Client says:

> "I need products."

The Abstract Factory says:

> "Which family do you want?"

The Concrete Factory says:

> "I will create the products from that family."

---

# 37. Final Cheat Sheet

```text
ABSTRACT FACTORY
       |
       v
Creational Design Pattern
       |
       v
Creates families of related objects
       |
       +------------------------------+
       |                              |
       v                              v
Abstract Factory              Concrete Factory
       |                              |
       +-- createA()                  +-- creates A
       +-- createB()                  +-- creates B
       +-- createC()                  +-- creates C
                                      |
                                      v
                              Same Product Family
```

### Remember these five things

1. **Abstract Factory creates families of related products.**
2. **The Client works with abstractions, not concrete classes.**
3. **Each Concrete Factory creates one consistent product family.**
4. **It makes switching an entire product family easy.**
5. **Adding a new product type can require changing every factory.**

### One-line memory trick

> **Abstract Factory = Create a family of related objects without exposing their concrete classes.**

### Most important distinction

```text
Factory Method
→ Usually one product hierarchy

Abstract Factory
→ Multiple related product hierarchies
```

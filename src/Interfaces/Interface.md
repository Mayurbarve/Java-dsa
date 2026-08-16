# Interfaces in Java

## 1. What is an Interface?

An **interface** in Java is a **contract**.

It tells a class:

> "If you implement me, you must provide these behaviors."

An interface mainly defines **what a class should do**, while the implementing class decides **how to do it**.

### Simple Example

```java
interface PaymentGateway {

    void pay(double amount);

}
```

The interface says:

> Every payment gateway must have a `pay()` method.

But it doesn't say **how** the payment should be processed.

Different classes can implement it differently:

```java
class StripePayment implements PaymentGateway {

    public void pay(double amount) {
        System.out.println("Payment processed using Stripe");
    }
}
```

```java
class RazorpayPayment implements PaymentGateway {

    public void pay(double amount) {
        System.out.println("Payment processed using Razorpay");
    }
}
```

Both classes follow the same contract:

```text
PaymentGateway
      |
      | implements
      |
 ┌────┴──────────┐
 |               |
Stripe         Razorpay
```

But their implementation can be completely different.

---

# 2. The Main Idea

The easiest way to remember an interface is:

```text
Interface  →  WHAT
Class      →  HOW
```

For example:

```java
interface NotificationService {

    void send(String message);

}
```

The interface says:

> A notification service must be able to send a message.

It doesn't care whether the message is sent using:

* Email
* Slack
* WhatsApp
* SMS
* Webhook

Each implementation decides how to send it.

---

# 3. Real-World Analogy

Think about a **remote control**.

A remote might have buttons such as:

```text
play()
pause()
volumeUp()
powerOff()
```

The remote doesn't need to know how the device works internally.

A TV can implement those commands.

A soundbar can implement those commands.

A projector can implement those commands.

```text
              RemoteControl
              <<interface>>
                    |
          ┌─────────┼─────────┐
          ↓         ↓         ↓
         TV      SoundBar   Projector
```

The command is the same:

```java
play();
```

But the internal behavior can be different.

### Important Point

The interface defines the **common contract**.

The implementing classes provide the **actual behavior**.

---

# 4. Basic Syntax

An interface is created using the `interface` keyword.

```java
interface PaymentGateway {

    void initiatePayment(double amount);

}
```

A class implements an interface using the `implements` keyword.

```java
class StripePayment implements PaymentGateway {

    public void initiatePayment(double amount) {

        System.out.println("Processing payment using Stripe");

    }
}
```

Another class can implement the same interface:

```java
class RazorpayPayment implements PaymentGateway {

    public void initiatePayment(double amount) {

        System.out.println("Processing payment using Razorpay");

    }
}
```

Now we have:

```text
              PaymentGateway
              <<interface>>
                    |
          ┌─────────┴─────────┐
          ↓                   ↓
   StripePayment       RazorpayPayment
```

---

# 5. Why Do We Need Interfaces?

Interfaces become especially useful when we have **multiple implementations of the same behavior**.

For example, suppose we are building an e-commerce application.

We want to support:

```text
Stripe
Razorpay
PayPal
```

Without an interface, our `CheckoutService` might become tightly connected to a specific payment provider.

That creates a problem.

If we later change from Stripe to Razorpay, we may have to modify our business logic.

Instead, we create an interface:

```java
interface PaymentGateway {

    void initiatePayment(double amount);

}
```

Now `CheckoutService` depends on the interface rather than a specific payment provider.

---

# 6. Programming to an Interface

This is one of the most important concepts.

Instead of writing:

```java
StripePayment payment = new StripePayment();
```

we can write:

```java
PaymentGateway payment = new StripePayment();
```

Notice the difference.

The variable type is:

```java
PaymentGateway
```

not:

```java
StripePayment
```

This means our code depends on the **contract**, not the implementation.

For example:

```java
PaymentGateway payment;

payment = new StripePayment();

payment.initiatePayment(100);
```

Later:

```java
payment = new RazorpayPayment();

payment.initiatePayment(100);
```

The same variable can work with different implementations.

This is **polymorphism**.

---

# 7. Polymorphism with Interfaces

Consider:

```java
PaymentGateway payment;

payment = new StripePayment();
payment.initiatePayment(100);
```

Java calls the `StripePayment` implementation.

If we change it:

```java
payment = new RazorpayPayment();
payment.initiatePayment(100);
```

Java now calls the `RazorpayPayment` implementation.

So:

```text
PaymentGateway
      |
      |---- StripePayment
      |
      |---- RazorpayPayment
      |
      |---- PayPalPayment
```

The code using `PaymentGateway` doesn't need to know which implementation it received.

---

# 8. Dependency Injection

Interfaces become even more powerful when combined with **Dependency Injection (DI)**.

Consider this service:

```java
class CheckoutService {

    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(double amount) {

        paymentGateway.initiatePayment(amount);

    }
}
```

Notice something important.

`CheckoutService` does **not** create:

```java
new StripePayment();
```

or:

```java
new RazorpayPayment();
```

Instead, it receives a `PaymentGateway`.

This is called **Dependency Injection**.

The dependency is provided from outside.

---

# 9. Complete Example

### Step 1: Create the Interface

```java
interface PaymentGateway {

    void initiatePayment(double amount);

}
```

### Step 2: Create Implementations

```java
class StripePayment implements PaymentGateway {

    public void initiatePayment(double amount) {

        System.out.println("Processing payment using Stripe: $" + amount);

    }
}
```

```java
class RazorpayPayment implements PaymentGateway {

    public void initiatePayment(double amount) {

        System.out.println("Processing payment using Razorpay: ₹" + amount);

    }
}
```

### Step 3: Create the Service

```java
class CheckoutService {

    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {

        this.paymentGateway = paymentGateway;

    }

    public void checkout(double amount) {

        paymentGateway.initiatePayment(amount);

    }

}
```

### Step 4: Use It

```java
public class Main {

    public static void main(String[] args) {

        PaymentGateway stripe = new StripePayment();

        CheckoutService service = new CheckoutService(stripe);

        service.checkout(100);

    }

}
```

Output:

```text
Processing payment using Stripe: $100
```

Now we can switch to Razorpay:

```java
PaymentGateway razorpay = new RazorpayPayment();

CheckoutService service = new CheckoutService(razorpay);

service.checkout(100);
```

Output:

```text
Processing payment using Razorpay: ₹100
```

The important part is:

```java
CheckoutService
```

didn't need to change.

Only the implementation that was injected changed.

---

# 10. Why Is This Better?

Imagine `CheckoutService` directly depended on Stripe:

```java
class CheckoutService {

    private StripePayment payment = new StripePayment();

}
```

Now the service is tightly coupled to Stripe.

If we want Razorpay, we need to modify the service.

Instead:

```java
class CheckoutService {

    private PaymentGateway paymentGateway;

}
```

Now the service doesn't care about the actual provider.

It only knows:

> "I have something that follows the `PaymentGateway` contract."

This is called **loose coupling**.

---

# 11. Loose Coupling

### Tight Coupling

```text
CheckoutService
       |
       ↓
StripePayment
```

`CheckoutService` directly depends on Stripe.

Changing Stripe may require changes in `CheckoutService`.

### Loose Coupling

```text
             PaymentGateway
              <<interface>>
                    ↑
          ┌─────────┼─────────┐
          |         |         |
        Stripe   Razorpay   PayPal
```

And:

```text
CheckoutService
       |
       ↓
PaymentGateway
```

`CheckoutService` depends only on the interface.

This makes the system easier to change.

---

# 12. Main Benefits of Interfaces

## 1. Polymorphism

Different classes can be treated as the same interface type.

```java
PaymentGateway gateway;

gateway = new StripePayment();
gateway = new RazorpayPayment();
```

---

## 2. Loose Coupling

Classes depend on abstractions rather than concrete implementations.

```java
PaymentGateway
```

instead of:

```java
StripePayment
```

---

## 3. Extensibility

Suppose tomorrow we want PayPal.

We simply create:

```java
class PayPalPayment implements PaymentGateway {

    public void initiatePayment(double amount) {

        System.out.println("Processing payment using PayPal");

    }
}
```

We don't need to modify `CheckoutService`.

That's a major advantage.

---

## 4. Testability

Interfaces make testing easier.

For example, instead of using a real payment provider during testing, we can create a fake implementation:

```java
class MockPaymentGateway implements PaymentGateway {

    public void initiatePayment(double amount) {

        System.out.println("Mock payment successful");

    }
}
```

Then:

```java
PaymentGateway gateway = new MockPaymentGateway();

CheckoutService service = new CheckoutService(gateway);

service.checkout(100);
```

We can test `CheckoutService` without making a real payment.

---

## 5. Maintainability

Because classes are less dependent on each other, changes in one implementation are less likely to break unrelated code.

---

# 13. Practical Example: Notification System

Let's say we are building a monitoring system.

When a server goes down, we want to send an alert.

Different customers may want different notification channels:

```text
Email
Slack
SMS
Webhook
```

Instead of making `AlertService` know about every notification system, create an interface.

```java
interface NotificationService {

    void send(String recipient, String message);

}
```

Now create implementations.

### Email

```java
class EmailNotifier implements NotificationService {

    public void send(String recipient, String message) {

        System.out.println(
            "[Email] To: " + recipient + " | " + message
        );

    }
}
```

### Slack

```java
class SlackNotifier implements NotificationService {

    public void send(String recipient, String message) {

        System.out.println(
            "[Slack] Channel: " + recipient + " | " + message
        );

    }
}
```

### Webhook

```java
class WebhookNotifier implements NotificationService {

    public void send(String recipient, String message) {

        System.out.println(
            "[Webhook] URL: " + recipient + " | " + message
        );

    }
}
```

---

# 14. AlertService

Now our `AlertService` only depends on the interface.

```java
class AlertService {

    private NotificationService notifier;

    public AlertService(NotificationService notifier) {

        this.notifier = notifier;

    }

    public void triggerAlert(String recipient, String issue) {

        notifier.send(recipient, issue);

    }

}
```

Notice that `AlertService` does not know about:

```text
EmailNotifier
SlackNotifier
WebhookNotifier
```

It only knows:

```text
NotificationService
```

---

# 15. Using Different Implementations

### Email

```java
NotificationService email = new EmailNotifier();

AlertService alertService = new AlertService(email);

alertService.triggerAlert(
    "admin@example.com",
    "Server is down!"
);
```

### Slack

```java
NotificationService slack = new SlackNotifier();

AlertService alertService = new AlertService(slack);

alertService.triggerAlert(
    "#devops",
    "Server is down!"
);
```

The `AlertService` code remains the same.

Only the implementation changes.

---

# 16. Adding a New Implementation

Suppose tomorrow we want SMS notifications.

We create:

```java
class SMSNotifier implements NotificationService {

    public void send(String recipient, String message) {

        System.out.println(
            "[SMS] To: " + recipient + " | " + message
        );

    }

}
```

That's it.

We don't have to modify:

```java
AlertService
```

because it already works with:

```java
NotificationService
```

This is one of the biggest advantages of programming to interfaces.

---

# 17. The Big Picture

The architecture looks like this:

```text
                  <<interface>>
              NotificationService
                       ↑
          ┌────────────┼────────────┐
          |            |            |
          ↓            ↓            ↓
       Email         Slack       Webhook
      Notifier       Notifier      Notifier


                 AlertService
                      |
                      ↓
            NotificationService
```

`AlertService` doesn't care whether it receives:

```text
EmailNotifier
SlackNotifier
WebhookNotifier
SMSNotifier
```

As long as the object implements:

```java
NotificationService
```

everything works.

---

# 18. Interface vs Implementation

| Interface                         | Implementation             |
| --------------------------------- | -------------------------- |
| Defines the contract              | Provides the actual logic  |
| Defines **what**                  | Defines **how**            |
| Used for abstraction              | Contains concrete behavior |
| Promotes loose coupling           | Performs the actual work   |
| Can have multiple implementations | Implements the interface   |

Example:

```java
interface PaymentGateway {

    void pay(double amount);

}
```

The interface says:

```text
"Payment must be possible."
```

Implementation:

```java
class StripePayment implements PaymentGateway {

    public void pay(double amount) {

        // Stripe-specific logic

    }

}
```

The class says:

```text
"Here is HOW I perform the payment."
```

---

# 19. Important Terms to Remember

### Interface

A **contract** that defines expected behavior.

### Implementation

A class that provides the actual behavior of an interface.

### Polymorphism

The ability to use different implementations through the same interface type.

### Loose Coupling

Keeping classes independent from specific implementations.

### Dependency Injection

Providing an object's dependency from outside instead of creating it inside the class.

### Programming to an Interface

Writing code that depends on an interface rather than a concrete implementation.

---

# 20. The Most Important Example to Remember

If you remember only one example, remember this:

```java
interface PaymentGateway {

    void pay(double amount);

}
```

Multiple implementations:

```java
class StripePayment implements PaymentGateway {

    public void pay(double amount) {
        // Stripe logic
    }

}
```

```java
class RazorpayPayment implements PaymentGateway {

    public void pay(double amount) {
        // Razorpay logic
    }

}
```

Service:

```java
class CheckoutService {

    private PaymentGateway gateway;

    public CheckoutService(PaymentGateway gateway) {

        this.gateway = gateway;

    }

    public void checkout(double amount) {

        gateway.pay(amount);

    }

}
```

Usage:

```java
PaymentGateway gateway = new StripePayment();

CheckoutService checkout = new CheckoutService(gateway);

checkout.checkout(500);
```

Later:

```java
gateway = new RazorpayPayment();

checkout = new CheckoutService(gateway);

checkout.checkout(500);
```

The important relationship is:

```text
             Interface
                 |
        PaymentGateway
                 |
       ┌─────────┴─────────┐
       ↓                   ↓
    Stripe              Razorpay
       \                   /
        \                 /
         ↓               ↓
        CheckoutService
```

---

# 21. Final Mental Model

Think of an interface as a **standard plug/socket**.

The socket defines the standard:

```text
"Any compatible device can connect here."
```

Different devices can implement that standard differently.

Similarly:

```text
Interface
   ↓
Defines the contract
   ↓
Multiple implementations
   ↓
Service depends on interface
   ↓
Implementation can be changed easily
```

So the core idea is:

> **Program against an interface, not against a concrete implementation.**

This gives us:

```text
Interfaces
    ↓
Abstraction
    ↓
Loose Coupling
    ↓
Polymorphism
    ↓
Easy Testing
    ↓
Easy Extension
    ↓
Maintainable Code
```

## One-Line Definition

> **An interface is a contract that defines what a class must do, while allowing each implementing class to decide how it does it.**

# Singleton Design Pattern

## 1. What is Singleton?

The **Singleton Design Pattern** is a creational design pattern that ensures:

1. A class has **only one instance** throughout the application.
2. That single instance has a **global access point**.

In simple words:

> **Singleton = One class → One shared object → One access point**

Instead of allowing code like this:

```java
DatabaseConnection db1 = new DatabaseConnection();
DatabaseConnection db2 = new DatabaseConnection();
DatabaseConnection db3 = new DatabaseConnection();
```

we control object creation so that:

```java
DatabaseConnection db1 = DatabaseConnection.getInstance();
DatabaseConnection db2 = DatabaseConnection.getInstance();

System.out.println(db1 == db2); // true
```

Both variables point to the **same object**.

---

# 2. Why Do We Need Singleton?

Sometimes creating multiple objects of a class does not make sense.

For example:

- Application configuration
- Logger
- Database connection manager
- Cache manager
- Thread pool manager
- Application-wide settings
- Resource manager

Imagine a logging system:

```java
Logger logger1 = new Logger();
Logger logger2 = new Logger();
Logger logger3 = new Logger();
```

If every part of the application creates its own logger, we may end up with unnecessary objects and inconsistent state.

A Singleton allows:

```java
Logger logger1 = Logger.getInstance();
Logger logger2 = Logger.getInstance();
Logger logger3 = Logger.getInstance();
```

All three references point to the same object.

---

# 3. Main Characteristics of Singleton

A typical Singleton has three important parts:

### 1. Private Constructor

```java
private Singleton() {}
```

This prevents other classes from directly creating objects:

```java
new Singleton(); // ERROR
```

### 2. Static Instance

```java
private static Singleton instance;
```

The object is stored in a static variable because the instance belongs to the class rather than to an individual object.

### 3. Public Access Method

```java
public static Singleton getInstance() {
    return instance;
}
```

This provides a global access point to the single object.

---

# 4. Basic Singleton Structure

```java
class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

Usage:

```java
Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();

System.out.println(s1 == s2);
```

Output:

```text
true
```

---

# 5. How Singleton Works

Consider:

```java
Singleton s1 = Singleton.getInstance();
```

Initially:

```text
instance = null
```

The method checks:

```java
if (instance == null)
```

Since it is null:

```java
instance = new Singleton();
```

Now:

```text
  instance
     |
     v
+-----------+
| Singleton |
+-----------+
```

The method returns that object.

When we call:

```java
Singleton s2 = Singleton.getInstance();
```

the instance is no longer null.

Therefore:

```java
if (instance == null)
```

is false.

No new object is created.

The existing object is returned.

So:

```text
s1 ─────────┐
            |
            v
       +-----------+
       | Singleton |
       +-----------+
            ^
            |
s2 ─────────┘
```

Therefore:

```java
s1 == s2
```

returns:

```text
true
```

---

# 6. Singleton Lifecycle

The basic lazy Singleton follows this lifecycle:

```text
Application starts
       |
       v
instance = null
       |
       v
getInstance()
       |
       v
instance == null ?
      / \
    yes  no
     |    |
     v    |
create    |
object    |
     |    |
     v    |
store     |
instance  |
     \    /
      \  /
       vv
 return same instance
```

---

# 7. Singleton Variations

There are several ways to implement Singleton in Java.

Common approaches are:

1. Lazy Initialization
2. Thread-Safe Singleton
3. Double-Checked Locking
4. Eager Initialization
5. Bill Pugh / Initialization-on-Demand Holder
6. Enum Singleton
7. Static Block Initialization

Each approach has different trade-offs.

---

# 8. Lazy Initialization

## 8.1 What is Lazy Initialization?

Lazy initialization means:

> Create the Singleton object only when it is actually requested.

The object is **not created when the class is loaded**.

It is created during the first call to `getInstance()`.

---

## 8.2 Implementation

```java
class LazySingleton {

    // Holds the single shared instance
    private static LazySingleton instance;

    // Prevent external object creation
    private LazySingleton() {
    }

    // Global access point
    public static LazySingleton getInstance() {

        // Create the object only when needed
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }
}
```

---

## 8.3 How It Works

First call:

```java
LazySingleton s1 = LazySingleton.getInstance();
```

Initially:

```text
instance = null
```

Therefore:

```java
instance = new LazySingleton();
```

The object is created.

Second call:

```java
LazySingleton s2 = LazySingleton.getInstance();
```

Now:

```text
instance != null
```

Therefore the existing object is returned.

---

## 8.4 Advantages

- Object is created only when required.
- Simple implementation.
- No unnecessary object creation.

## 8.5 Disadvantages

The basic implementation is **not thread-safe**.

This is important in multi-threaded applications.

---

# 9. Why Lazy Singleton Is Not Thread-Safe

Suppose two threads call:

```java
getInstance();
```

at exactly the same time.

Initial state:

```text
instance = null
```

Imagine:

```text
Thread A                    Thread B

instance == null            instance == null
      |                           |
      v                           v
new Singleton()             new Singleton()
      |                           |
      v                           v
instance = Object A         instance = Object B
```

Now two objects may exist:

```text
Object A
Object B
```

That violates the Singleton requirement.

Therefore:

> Basic lazy initialization is not safe when multiple threads can access it concurrently.

---

# 10. Thread-Safe Singleton Using synchronized

We can make the lazy Singleton thread-safe by synchronizing `getInstance()`.

```java
class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }

    public static synchronized ThreadSafeSingleton getInstance() {

        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }

        return instance;
    }
}
```

---

# 11. How synchronized Works

The keyword:

```java
synchronized
```

ensures that only one thread can execute the synchronized method at a time for the relevant class/object lock.

Suppose:

```text
Thread A → getInstance()
Thread B → getInstance()
Thread C → getInstance()
```

Only one thread enters at a time.

```text
Thread A
   |
   v
 acquire lock
   |
   v
 create instance
   |
   v
 release lock
   |
   v
Thread B
   |
   v
 acquire lock
   |
   v
 instance already exists
   |
   v
 release lock
```

This prevents multiple instances from being created.

---

# 12. Performance Problem with synchronized

The implementation is correct:

```java
public static synchronized ThreadSafeSingleton getInstance()
```

but every call to `getInstance()` requires synchronization.

For example:

```java
ThreadSafeSingleton.getInstance();
ThreadSafeSingleton.getInstance();
ThreadSafeSingleton.getInstance();
ThreadSafeSingleton.getInstance();
```

After the first call, the object already exists.

There is no need to lock every time.

This creates unnecessary synchronization overhead.

That leads to the next approach.

---

# 13. Double-Checked Locking

Double-Checked Locking tries to achieve:

- Lazy initialization
- Thread safety
- Better performance

The idea is:

> Check once before locking and check again inside the lock.

---

## 13.1 Implementation

```java
class DoubleCheckedSingleton {

    // volatile is required for safe publication
    private static volatile DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {
    }

    public static DoubleCheckedSingleton getInstance() {

        // First check - fast path
        if (instance == null) {

            // Lock only when creation may be required
            synchronized (DoubleCheckedSingleton.class) {

                // Second check - prevents multiple creation
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }

        return instance;
    }
}
```

---

# 14. Why Two Checks?

Consider:

```text
Thread A                  Thread B

instance == null          instance == null
     |                         |
     v                         v
  waits for lock           waits for lock
     |                         |
     v                         |
creates instance              |
     |                         |
     v                         |
releases lock                 |
                               v
                         gets lock
                               |
                               v
                    instance == null ?
                               |
                               v
                             false
                               |
                               v
                       returns existing
```

The second check is essential.

Without it, both threads could enter the synchronized block one after another and potentially create objects.

---

# 15. Why volatile Is Required

This declaration is important:

```java
private static volatile DoubleCheckedSingleton instance;
```

The `volatile` keyword helps ensure that the object is safely published between threads and prevents problematic instruction reordering around:

```java
instance = new DoubleCheckedSingleton();
```

Conceptually, object creation involves steps such as:

```text
1. Allocate memory
2. Initialize object
3. Assign reference
```

Without proper memory visibility guarantees, another thread could potentially observe a reference before the object is fully initialized.

Using:

```java
volatile
```

provides the required visibility and ordering guarantees for this pattern.

Therefore:

> Do not remove `volatile` from a correctly implemented double-checked locking Singleton.

---

# 16. Performance of Double-Checked Locking

First call:

```text
instance == null
      |
      v
 acquire lock
      |
      v
 create object
```

Later calls:

```text
instance == null ?
      |
      v
    false
      |
      v
return instance
```

No synchronization is required after initialization.

Therefore it is usually much more efficient than synchronizing the entire method.

---

# 17. Eager Initialization

Eager initialization creates the Singleton when the class is initialized.

```java
class EagerSingleton {

    private static final EagerSingleton instance =
            new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
```

---

# 18. How Eager Initialization Works

The object is created immediately during class initialization:

```text
Class initialization
       |
       v
new EagerSingleton()
       |
       v
instance created
       |
       v
getInstance()
       |
       v
return existing instance
```

There is no:

```java
if (instance == null)
```

because the object already exists.

---

# 19. Why Eager Initialization Is Thread-Safe

Java class initialization is performed by the JVM in a thread-safe manner.

Therefore:

```java
private static final EagerSingleton instance =
        new EagerSingleton();
```

does not require explicit synchronization.

---

# 20. Advantages of Eager Initialization

- Very simple.
- Thread-safe.
- No synchronization overhead during access.
- Easy to understand.
- `final` prevents reassignment of the reference.

## Disadvantages

The instance is created even if the application never uses it.

For example:

```text
Application starts
      |
      v
Singleton created
      |
      v
Application never uses Singleton
```

The resource was still allocated.

This matters if construction is expensive.

---

# 21. Bill Pugh Singleton

The Bill Pugh approach is also known as:

> **Initialization-on-Demand Holder Idiom**

It provides:

- Lazy initialization
- Thread safety
- No explicit synchronization
- Good performance

---

## 21.1 Implementation

```java
public class BillPughSingleton {

    private BillPughSingleton() {
    }

    private static class Holder {

        private static final BillPughSingleton INSTANCE =
                new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

---

# 22. How Bill Pugh Works

The important part is:

```java
private static class Holder
```

The `Holder` class is not initialized merely because `BillPughSingleton` is loaded.

The Singleton is created when:

```java
getInstance()
```

references:

```java
Holder.INSTANCE
```

Conceptually:

```text
BillPughSingleton loaded
        |
        v
Holder not initialized
        |
        v
getInstance()
        |
        v
Holder.INSTANCE requested
        |
        v
Holder initialized
        |
        v
Singleton created
        |
        v
return INSTANCE
```

---

# 23. Why Bill Pugh Is Thread-Safe

Java guarantees thread-safe class initialization.

Therefore:

```java
private static final BillPughSingleton INSTANCE =
        new BillPughSingleton();
```

is initialized safely when `Holder` is initialized.

No explicit:

```java
synchronized
```

is required.

---

# 24. Why Bill Pugh Is Popular

It gives us a very useful combination:

| Requirement | Bill Pugh |
|---|---|
| Lazy initialization | Yes |
| Thread-safe | Yes |
| Explicit synchronization | No |
| Synchronization overhead after creation | No |
| Simple | Yes |
| Good performance | Yes |

For many normal Java Singleton use cases, this is an excellent implementation.

---

# 25. Enum Singleton

Java provides a particularly strong Singleton implementation using `enum`.

```java
enum EnumSingleton {

    INSTANCE;

    public void doSomething() {
        System.out.println("Singleton logic");
    }
}
```

Usage:

```java
EnumSingleton singleton = EnumSingleton.INSTANCE;

singleton.doSomething();
```

There is no:

```java
new EnumSingleton();
```

The enum constant itself represents the Singleton instance.

---

# 26. Why Enum Singleton Is Special

Enum Singleton gets important guarantees from the Java platform.

### Thread-safe initialization

Enum constants are initialized safely as part of enum class initialization.

### Serialization safety

Java's enum serialization mechanism preserves the enum constant identity.

Deserialization does not create a second enum instance.

### Reflection resistance

Java prevents normal reflective construction of enum instances.

### Single instance

The enum constant:

```java
INSTANCE
```

represents one instance of that enum constant.

---

# 27. Enum Singleton Example

```java
enum DatabaseManager {

    INSTANCE;

    public void connect() {
        System.out.println("Connected to database");
    }

    public void disconnect() {
        System.out.println("Disconnected from database");
    }
}
```

Usage:

```java
DatabaseManager.INSTANCE.connect();

DatabaseManager db1 = DatabaseManager.INSTANCE;
DatabaseManager db2 = DatabaseManager.INSTANCE;

System.out.println(db1 == db2);
```

Output:

```text
Connected to database
true
```

---

# 28. Limitation of Enum Singleton

Enums cannot extend another class.

For example, this is not possible:

```java
enum MySingleton extends SomeClass {
}
```

because every enum already extends:

```java
java.lang.Enum
```

However, an enum can implement interfaces:

```java
interface Logger {
    void log(String message);
}

enum LoggerSingleton implements Logger {

    INSTANCE;

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
```

---

# 29. Static Block Initialization

Static block initialization is another eager approach.

```java
class StaticBlockSingleton {

    private static StaticBlockSingleton instance;

    private StaticBlockSingleton() {
    }

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Exception occurred while creating Singleton"
            );
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
```

---

# 30. How Static Block Initialization Works

The static block:

```java
static {
    ...
}
```

runs during class initialization.

Therefore:

```text
Class initialization
       |
       v
static block executes
       |
       v
Singleton created
       |
       v
getInstance()
       |
       v
existing instance returned
```

Like eager initialization, this is not lazy.

---

# 31. Why Use a Static Block?

One advantage is that the initialization logic can contain more complicated code and explicit exception handling.

For example:

```java
static {
    try {
        instance = new StaticBlockSingleton();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
```

This can be useful when initialization may involve operations that need special handling.

---

# 32. Singleton Comparison

| Approach | Lazy | Thread-Safe | Explicit Lock | Performance | Complexity |
|---|---:|---:|---:|---:|---:|
| Basic Lazy | Yes | No | No | Good | Low |
| synchronized method | Yes | Yes | Yes | Lower | Low |
| Double-Checked Locking | Yes | Yes | Yes | Good | Medium |
| Eager | No | Yes | No | Excellent | Very Low |
| Bill Pugh | Yes | Yes | No | Excellent | Low |
| Enum | No* | Yes | No | Excellent | Very Low |
| Static Block | No | Yes | No | Excellent | Low |

`*` Enum constants are initialized during enum class initialization rather than on the first `getInstance()` call.

---

# 33. Which Singleton Should You Use?

A practical decision guide:

```text
Need Singleton?
      |
      v
Can you use enum?
   /        \
 yes         no
  |           |
  v           v
Enum       Need lazy initialization?
               |
          +----+----+
          |         |
         yes        no
          |         |
          v         v
      Bill Pugh    Eager
```

### Recommended choices

**Enum Singleton**

Use when you simply need a Singleton and enum limitations are acceptable.

```java
enum Singleton {
    INSTANCE;
}
```

**Bill Pugh**

Use when you want lazy initialization with clean Java class-initialization semantics.

```java
class Singleton {

    private Singleton() {
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

**Eager Initialization**

Use when the object is cheap to create and you know it will be needed.

**Double-Checked Locking**

Useful when you specifically need lazy initialization and want to avoid synchronization on every access, but it is more complex than Bill Pugh.

---

# 34. Singleton and Dependency Injection

Singleton is not always the best way to achieve shared services.

In modern applications, especially Spring applications, dependency injection is often preferred.

For example, Spring can manage a bean with singleton scope:

```java
@Service
public class UserService {
}
```

The Spring container manages the lifecycle and scope of the object.

Instead of manually doing:

```java
UserService.getInstance();
```

we can inject the dependency:

```java
@Autowired
private UserService userService;
```

The important distinction is:

> A Singleton design pattern is a class-level object-creation technique, while dependency injection is an architectural technique for managing dependencies.

---

# 35. Singleton vs Static Class

These concepts are related but not identical.

## Singleton

A Singleton creates an actual object.

```java
Singleton singleton = Singleton.getInstance();
```

It can:

- Implement interfaces
- Be passed as an object
- Have instance state
- Be used where an object is required

## Static Utility Class

A utility class usually exposes static methods:

```java
MathUtils.calculate();
```

There is generally no need to create an object.

---

# 36. Singleton vs Global Variable

A Singleton behaves somewhat like a controlled global object, but it provides encapsulation.

Instead of exposing:

```java
public static Database database;
```

we can control access:

```java
private static Database instance;

public static Database getInstance() {
    ...
}
```

This allows the class to control:

- How the object is created
- When it is created
- Whether it can be replaced
- How clients access it

---

# 37. Common Singleton Mistakes

## Mistake 1: Public Constructor

Wrong:

```java
class Singleton {

    public Singleton() {
    }
}
```

Anyone can do:

```java
new Singleton();
```

Therefore multiple instances are possible.

---

## Mistake 2: Non-static Instance

Wrong:

```java
private Singleton instance;
```

The access method itself would need an object.

A Singleton instance holder is normally static:

```java
private static Singleton instance;
```

---

## Mistake 3: Missing Null Check

Wrong:

```java
public static Singleton getInstance() {
    return new Singleton();
}
```

Every call creates a new object.

```java
getInstance(); // Object A
getInstance(); // Object B
getInstance(); // Object C
```

This is not a Singleton.

---

## Mistake 4: Incorrect Double-Checked Locking

Avoid:

```java
private static Singleton instance;

public static Singleton getInstance() {

    if (instance == null) {

        synchronized (Singleton.class) {

            if (instance == null) {
                instance = new Singleton();
            }
        }
    }

    return instance;
}
```

For correct double-checked locking, the field should be:

```java
private static volatile Singleton instance;
```

---

# 38. Singleton and Serialization

Serialization can create subtle problems for traditional Singleton implementations.

For example, an object can be serialized:

```java
ObjectOutputStream
```

and later deserialized:

```java
ObjectInputStream
```

For ordinary classes, deserialization can create a new object.

Therefore a traditional Singleton may require:

```java
private Object readResolve() {
    return instance;
}
```

Example:

```java
class SerializableSingleton implements java.io.Serializable {

    private static final SerializableSingleton INSTANCE =
            new SerializableSingleton();

    private SerializableSingleton() {
    }

    public static SerializableSingleton getInstance() {
        return INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
```

The purpose of `readResolve()` is to ensure that deserialization returns the existing Singleton instance rather than exposing another instance.

Enum Singleton avoids this traditional serialization problem because Java's enum serialization mechanism preserves enum identity.

---

# 39. Singleton and Reflection

Reflection can bypass many normal access restrictions.

For an ordinary Singleton:

```java
Constructor<Singleton> constructor =
        Singleton.class.getDeclaredConstructor();

constructor.setAccessible(true);
Singleton another = constructor.newInstance();
```

This can potentially create another instance.

Enum Singleton receives stronger protection from Java's reflection rules.

This is one reason enum Singleton is often recommended when its limitations are acceptable.

---

# 40. Singleton and Cloning

If a Singleton implements `Cloneable`, cloning can potentially produce another object.

For example:

```java
Singleton copy = (Singleton) singleton.clone();
```

To protect a traditional Singleton, cloning can be prevented:

```java
@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
}
```

However, the exact protection strategy depends on the class design and whether cloning is exposed at all.

---

# 41. Real-World Example: Configuration Manager

Suppose an application has configuration:

```java
class AppConfig {

    private static AppConfig instance;

    private String environment;
    private String databaseUrl;

    private AppConfig() {
        environment = "production";
        databaseUrl = "jdbc:mysql://localhost/app";
    }

    public static AppConfig getInstance() {

        if (instance == null) {
            instance = new AppConfig();
        }

        return instance;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }
}
```

Usage:

```java
AppConfig config1 = AppConfig.getInstance();
AppConfig config2 = AppConfig.getInstance();

System.out.println(config1.getEnvironment());

System.out.println(config1 == config2);
```

Output:

```text
production
true
```

Both references use the same configuration object.

---

# 42. Real-World Example: Logger

```java
enum AppLogger {

    INSTANCE;

    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
```

Usage:

```java
AppLogger.INSTANCE.info("Application started");
AppLogger.INSTANCE.error("Database connection failed");
```

This is a clean use case for an enum Singleton.

---

# 43. Singleton in System Design

Singleton often appears in discussions of:

- Logging systems
- Configuration managers
- Cache managers
- Connection managers
- Metrics collectors
- Resource managers
- Thread pools

However, in large distributed systems, remember:

> A Singleton is usually a single instance **inside one process/JVM**, not automatically one instance across an entire distributed system.

For example, if you have:

```text
Server 1 → Singleton A
Server 2 → Singleton B
Server 3 → Singleton C
```

Each JVM/process can have its own Singleton.

Therefore:

```text
Singleton ≠ globally unique across all servers
```

If a distributed application requires globally shared state, technologies such as distributed caches, databases, coordination services, or other distributed mechanisms may be required.

---

# 44. Important Interview Question

## Q1. What is Singleton?

**Answer:**

Singleton is a creational design pattern that restricts a class to one instance and provides a global access point to that instance.

---

## Q2. Why is the constructor private?

**Answer:**

The constructor is private so external classes cannot directly create objects using `new`.

---

## Q3. Why is the instance static?

**Answer:**

Because the Singleton instance belongs to the class itself and must be accessible without creating an object of that class.

---

## Q4. Why is basic lazy initialization not thread-safe?

**Answer:**

Two or more threads can simultaneously observe `instance == null` and each create a new object.

---

## Q5. How does synchronized make Singleton thread-safe?

**Answer:**

It ensures that only one thread at a time can execute the critical section responsible for creating the instance.

---

## Q6. What is double-checked locking?

**Answer:**

It checks whether the instance exists before acquiring the lock and checks again inside the synchronized block before creating the object.

---

## Q7. Why is volatile required in double-checked locking?

**Answer:**

`volatile` provides visibility and ordering guarantees needed for safe publication of the Singleton instance between threads.

---

## Q8. What is Bill Pugh Singleton?

**Answer:**

It uses a static nested Holder class. The Singleton instance is initialized when the Holder class is initialized, providing lazy initialization and thread safety without explicit synchronization.

---

## Q9. Why is Enum Singleton considered safe?

**Answer:**

Java provides enum-specific guarantees for initialization, serialization, and reflective construction, making enum a robust Singleton implementation.

---

## Q10. What is the disadvantage of eager initialization?

**Answer:**

The instance is created even if it is never used, which can waste resources when initialization is expensive.

---

# 45. Interview Comparison

### Basic Lazy

```java
private static Singleton instance;

public static Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }

    return instance;
}
```

**Question:** Is it thread-safe?

**Answer:** No.

---

### Synchronized Method

```java
public static synchronized Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }

    return instance;
}
```

**Question:** Is it thread-safe?

**Answer:** Yes.

**Problem:** Synchronization occurs on every call.

---

### Double-Checked Locking

```java
private static volatile Singleton instance;

public static Singleton getInstance() {

    if (instance == null) {

        synchronized (Singleton.class) {

            if (instance == null) {
                instance = new Singleton();
            }
        }
    }

    return instance;
}
```

**Question:** Why two checks?

**Answer:** The first avoids unnecessary locking after initialization. The second prevents multiple threads that reached the first check simultaneously from creating multiple objects.

---

### Bill Pugh

```java
private static class Holder {
    private static final Singleton INSTANCE =
            new Singleton();
}

public static Singleton getInstance() {
    return Holder.INSTANCE;
}
```

**Question:** Why is this lazy?

**Answer:** The Holder class is initialized only when it is first actively used.

---

### Enum

```java
enum Singleton {
    INSTANCE;
}
```

**Question:** Why is enum recommended?

**Answer:** It provides strong JVM-supported guarantees for Singleton identity, thread-safe initialization, serialization, and reflection resistance.

---

# 46. Best Practices

When implementing Singleton:

1. Keep the constructor private.
2. Provide a controlled access point.
3. Think about thread safety.
4. Use `volatile` with double-checked locking.
5. Prefer simple implementations over unnecessary complexity.
6. Consider Bill Pugh for lazy initialization.
7. Consider Enum Singleton when appropriate.
8. Be careful with serialization.
9. Avoid mutable global state when possible.
10. Consider dependency injection in large applications.

---

# 47. Key Concepts to Remember

```text
Singleton
   |
   +-- Private Constructor
   |
   +-- One Shared Instance
   |
   +-- Global Access Point
   |
   +-- Thread Safety Consideration
   |
   +-- Object Lifecycle Control
```

The most important implementations are:

```text
Lazy
  ↓
Simple but NOT thread-safe

Synchronized
  ↓
Thread-safe but synchronization on every access

Double-Checked Locking
  ↓
Thread-safe + lazy + optimized
  ↓
Requires volatile

Eager
  ↓
Simple + thread-safe
  ↓
Not lazy

Bill Pugh
  ↓
Lazy + thread-safe + no explicit synchronization

Enum
  ↓
Simple + robust + JVM-supported guarantees
```

---

# 48. Final Cheat Sheet

| Concept | Remember |
|---|---|
| Singleton purpose | Only one instance |
| Constructor | `private` |
| Instance | Usually `static` |
| Access | `getInstance()` or enum constant |
| Basic lazy | Not thread-safe |
| `synchronized` | Provides mutual exclusion |
| Double-checked locking | Two null checks |
| `volatile` | Required for correct DCL |
| Eager | Created during class initialization |
| Bill Pugh | Static Holder class |
| Enum | Strong Java-specific Singleton option |
| Serialization | Can break traditional Singleton |
| Reflection | Can challenge traditional Singleton |
| Cloning | Can create another object if not controlled |
| Distributed system | Singleton is normally per JVM/process |

---

# 49. One-Line Memory Trick

> **Singleton = Private Constructor + One Shared Instance + Controlled Access**

And for Java implementations:

> **Lazy → synchronized → double-check + volatile → eager → Bill Pugh → enum**

The most important thing is not memorizing every implementation. Understand **why the instance is created when it is, how thread safety is achieved, and what trade-off each implementation makes.**

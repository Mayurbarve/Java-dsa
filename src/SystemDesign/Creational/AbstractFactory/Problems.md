# Abstract Factory Pattern — Practice Problems

## Problem 1: UI Theme Factory

Design a UI system using the **Abstract Factory Pattern**.

The application supports two themes:

- Light Theme
- Dark Theme

Each theme must provide:

- Button
- Checkbox

Requirements:

- Create an abstraction for `Button`.
- Create an abstraction for `Checkbox`.
- Create concrete buttons and checkboxes for both themes.
- Create an abstract factory for creating the UI components.
- Create a concrete factory for each theme.
- Client code should work only with the abstract factory and abstract products.
- The client should be able to switch between Light and Dark themes without changing its component-creation logic.

---

## Problem 2: Operating System UI Factory

Design a cross-platform UI system using the **Abstract Factory Pattern**.

The application supports:

- Windows
- macOS
- Linux

Each operating system provides:

- Button
- Checkbox
- TextField

Requirements:

- Define common interfaces for all three UI components.
- Create OS-specific implementations.
- Create an abstract factory for creating the complete UI component family.
- Create one concrete factory for each operating system.
- Client code should not directly instantiate OS-specific components.
- Adding support for another operating system should require minimal changes to existing code.

---

## Problem 3: Vehicle Family Factory

Design a vehicle manufacturing system using the **Abstract Factory Pattern**.

The system supports two vehicle categories:

- Economy
- Luxury

Each category contains:

- Car
- Bike

Requirements:

- Define an abstraction for `Car`.
- Define an abstraction for `Bike`.
- Create Economy and Luxury implementations.
- Create an abstract vehicle factory.
- Create concrete factories for Economy and Luxury vehicles.
- A selected factory should create vehicles belonging to the same category.
- Client code should be independent of concrete vehicle classes.

---

## Problem 4: Database Factory

Design a database access system using the **Abstract Factory Pattern**.

The application supports:

- MySQL
- PostgreSQL

Each database family contains:

- Connection
- Query
- Transaction

Requirements:

- Define abstractions for all three products.
- Create MySQL implementations.
- Create PostgreSQL implementations.
- Create an abstract database factory.
- Create concrete factories for MySQL and PostgreSQL.
- Client code should be able to switch databases without directly creating database-specific classes.
- All objects created by one factory must belong to the same database family.

---

## Problem 5: Game Character Factory

Design a game character equipment system using the **Abstract Factory Pattern**.

The game supports:

- Warrior
- Mage
- Archer

Each character family contains:

- Weapon
- Armor

Requirements:

- Define abstractions for `Weapon` and `Armor`.
- Create concrete products for each character type.
- Create an abstract character factory.
- Create a concrete factory for each character type.
- A Warrior factory should create Warrior-compatible equipment.
- A Mage factory should create Mage-compatible equipment.
- An Archer factory should create Archer-compatible equipment.
- Client code should work with the abstractions instead of concrete classes.

---

## Problem 6: Payment Provider Factory

Design a payment system using the **Abstract Factory Pattern**.

The application supports:

- Stripe
- Razorpay
- PayPal

Each payment provider must provide:

- Payment Processor
- Refund Processor
- Payment Validator

Requirements:

- Define abstractions for all three products.
- Create provider-specific implementations.
- Create an abstract payment factory.
- Create one concrete factory for each payment provider.
- Client code should be independent of the selected payment provider.
- A factory must always create products belonging to the same payment provider.

---

## Problem 7: Cloud Provider Factory

Design a cloud infrastructure system using the **Abstract Factory Pattern**.

The application supports:

- AWS
- Azure
- Google Cloud

Each cloud provider offers:

- Compute Service
- Storage Service
- Database Service

Requirements:

- Define abstractions for all services.
- Create provider-specific implementations.
- Create an abstract cloud factory.
- Create concrete factories for AWS, Azure, and Google Cloud.
- Client code should be able to switch cloud providers without changing its service-usage logic.
- Ensure that services created by the same factory belong to the same cloud provider.

---

## Problem 8: Notification Platform Factory

Design a notification system using the **Abstract Factory Pattern**.

The application supports:

- Email Platform
- SMS Platform
- Push Notification Platform

Each platform must provide:

- Notification Sender
- Message Formatter
- Delivery Tracker

Requirements:

- Define abstractions for all three products.
- Create concrete implementations for each platform.
- Create an abstract notification factory.
- Create concrete factories for each notification platform.
- Client code should not directly instantiate concrete implementations.
- The selected factory should create a consistent family of notification components.

---

## Problem 9: E-Commerce Platform Factory

Design an e-commerce platform abstraction using the **Abstract Factory Pattern**.

The application supports:

- Amazon-style platform
- Shopify-style platform
- Custom Store platform

Each platform provides:

- Product Catalog
- Payment Service
- Order Service
- Shipping Service

Requirements:

- Define abstractions for all services.
- Create concrete implementations for each platform.
- Create an abstract e-commerce factory.
- Create concrete factories for each platform.
- Client code should work only with the abstract factory and product interfaces.
- Switching the entire platform should not require rewriting the client logic.
- All services created by a factory must belong to the same platform family.

---

## Problem 10: Operating System Resource Factory

Design an operating-system resource management system using the **Abstract Factory Pattern**.

The application supports:

- Windows
- Linux
- macOS

Each operating system provides:

- File System
- Process Manager
- Network Manager
- Security Manager

Requirements:

- Define abstractions for each resource.
- Create OS-specific implementations.
- Create an abstract resource factory.
- Create concrete factories for each operating system.
- Client code should not depend on OS-specific classes.
- Adding a new operating system should not require modifying existing concrete products.

---

# Challenge Problems

## Problem 11: Smart Home Factory

Design a smart home system using the **Abstract Factory Pattern**.

The system supports different smart-home ecosystems:

- Google Home
- Amazon Alexa
- Apple HomeKit

Each ecosystem provides:

- Smart Light
- Smart Lock
- Smart Thermostat
- Security Camera

Requirements:

- Create abstractions for all smart devices.
- Create concrete products for every ecosystem.
- Create an abstract smart-home factory.
- Create concrete factories for each ecosystem.
- All devices created by one factory must belong to the same ecosystem.
- Client code should be independent of the selected ecosystem.

---

## Problem 12: Restaurant Franchise Factory

Design a restaurant ordering system using the **Abstract Factory Pattern**.

The restaurant supports different regional menus:

- Indian
- Italian
- Mexican

Each menu family contains:

- Main Course
- Dessert
- Beverage

Requirements:

- Define abstractions for all food categories.
- Create concrete products for each region.
- Create an abstract menu factory.
- Create concrete factories for each regional menu.
- A selected factory must create food items belonging to the same regional menu.
- Client code should not directly instantiate individual food items.

---

## Problem 13: Computer Hardware Factory

Design a computer hardware manufacturing system using the **Abstract Factory Pattern**.

The system supports:

- Gaming PC
- Office PC
- Workstation

Each computer family contains:

- Processor
- Graphics Card
- Motherboard
- Power Supply

Requirements:

- Define abstractions for all hardware components.
- Create concrete components for each computer family.
- Create an abstract hardware factory.
- Create concrete factories for Gaming PC, Office PC, and Workstation.
- Components created by a factory should be compatible with that computer family.
- Client code should not directly instantiate concrete hardware classes.

---

## Problem 14: Travel Booking Factory

Design a travel booking system using the **Abstract Factory Pattern**.

The system supports:

- Budget Travel
- Business Travel
- Luxury Travel

Each travel package contains:

- Transport
- Hotel
- Meal
- Activity

Requirements:

- Define abstractions for each travel component.
- Create concrete implementations for each travel category.
- Create an abstract travel factory.
- Create concrete factories for Budget, Business, and Luxury travel.
- A factory must create a consistent travel package.
- Client code should not know the concrete classes being created.

---

## Problem 15: Complete Multi-Family Design Challenge

Design a complete application using the **Abstract Factory Pattern**.

Choose any real-world domain such as:

- Banking
- Food Delivery
- Ride Sharing
- E-Commerce
- Social Media
- Cloud Computing
- Gaming

Your design must contain:

- At least 3 product interfaces.
- At least 3 product families.
- At least 3 concrete factories.
- Multiple concrete products for every factory.
- One abstract factory.
- A client that works only with abstractions.

Additional requirements:

- The client must be able to switch the entire product family at runtime.
- The client must never directly use `new` with a concrete product class.
- Adding a new product family should not require changing existing client logic.
- Adding a new product type should be considered separately and its impact on the design should be documented.

Coffee Shop Ordering System: Requirements and Core Domain
The problem is to design an online coffee ordering system where customers can order drinks like espresso, latte, cappuccino, and tea, add customizations such as milk, sugar, whipped cream, or caramel, and have the total cost calculated dynamically. The system also needs to track order status through stages like placed, preparing, ready, and delivered, while notifying multiple stakeholders when the status changes.
The mention of delivered is important because it confirms this is an online ordering flow, not just an in-store café counter.
Think of the system as two layers: first the drink itself, then the order and its lifecycle.
The natural domain split is:
Beverage model for the drink and its price/description
Add-ons for customizations
Order for grouping drink details and tracking status
Observers for notifications
Key insight: Model the drink hierarchy first; order management and notifications come after the core beverage behavior is in place.
Key takeaway: The requirements are really about a drink system plus order lifecycle management, not just a menu of items.
Beverage Modeling and the Decorator Pattern
The first foundation is the beverage itself. Since the requirements include fixed drink types, a clean approach is to define a Beverage abstraction with concrete classes such as Cappuccino, Espresso, and Latte. Each beverage should provide at least a description and a cost.
The reason this comes first is that customizations only make sense once you already have a base drink.
The beverage is the base object; add-ons should wrap it, not replace it.
Core structure
Beverage: interface / abstraction
Concrete beverages:
Cappuccino
Espresso
Latte
Each concrete class defines:
getDescription()
getCost()
Why Decorator fits add-ons
Customizations like milk, sugar, or caramel are not separate products; they are additions layered onto an existing beverage. That makes the Decorator pattern a strong fit.
A decorator:
implements the same Beverage abstraction
wraps another beverage instance
adds to the wrapped beverage’s description and cost
For example:
base beverage: espresso
decorator: add milk
final result: still a beverage, but with updated behavior and price
Mental model
The decorator takes the wrapped beverage in its constructor, reads the wrapped beverage’s description and cost, and appends its own contribution.
Examples of customizations mentioned
milk
sugar
caramel
whipped cream
Key insight: Decorators let you build a drink step by step without exploding into many hard-coded subclasses.
Watch out: Do not use an enum alone if the object needs real behavior, pricing logic, and stacking add-ons. That quickly becomes too limited.
Key takeaway: Use a beverage interface plus decorators when you need dynamic combinations of base drinks and add-ons.
Order as the Higher-Level Entity
Once the beverage hierarchy exists, the next natural object is the Order. An order is a higher-level wrapper around the beverage model and is responsible for tracking the purchase and its status over time.
The order was described as holding:
an ID
beverage information
status
observers for notifications
There was also an important clarification that an order could reasonably contain multiple beverages, depending on the design, even if a simple implementation only uses one.
Start with the drink, then build the order around it.
This ordering matters because the order depends on the beverage abstraction, not the other way around.
Key insight: Don’t jump straight into order management before you’ve modeled what is being ordered.
Key takeaway: The order is the orchestration object; it sits on top of the beverage and customization model.
Observer Pattern for Order Notifications
Once the order exists, the next concern is how to notify multiple parties when its state changes. That is a classic Observer pattern use case.
When an order status changes, the order acts as the subject, and multiple observers listen for updates. The observers mentioned were:
Kitchen display
Billing system
Customer notifier
Each observer reacts differently to the same event. The kitchen cares about preparation, billing may care about the order details and price, and the customer cares about status updates.
How it works
The order status changes
The order updates its state
The order iterates through registered observers
Each observer gets notified
Observer interface design
A cleaner design is to pass the entire order object to observers instead of many separate fields. That keeps the observer interface simpler and more flexible.
If the observer needs only one field, you can still choose a smaller payload. But if the observer may need several pieces of information, passing the full order is cleaner.
Duplicate notification pitfall
A subtle issue is repeated status updates. If the same status is set twice, observers may get notified twice unnecessarily.
The fix is simple:
compare the new status to the current status
notify observers only if the status actually changed
Watch out: Without a status-change check, retries or repeated updates can cause duplicate notifications.
Key insight: Observer is ideal for one change triggering many independent reactions, but guard against duplicate events.
Key takeaway: Use Observer for order notifications, and notify only when the state truly changes.
State Management: Enum vs State Pattern
A major design question was whether order status should just be an enum or whether it should use the State pattern.
When enum is enough
An enum is good when you only need a finite set of labels:
placed
preparing
ready
delivered
If the system only needs to store and display the current status, enum is often sufficient.
When State pattern makes sense
The State pattern is useful when the current state affects behavior and, especially, when you need to enforce allowed transitions.
For example:
an order in delivered should not go back to placed
an order in preparing should not jump directly to delivered
some transitions may be valid only from specific states
State pattern is not about having more labels; it is about letting the current state control what happens next.
Two valid implementation styles
You can either:
hardcode transition validation in updateStatus with if/switch checks, or
create a separate class for each state and put the rules inside those classes
The second approach is the formal State pattern.
Why this wasn’t always necessary
The discussion also made it clear that the State pattern is not automatically required. If the problem only needs a simple status field and basic validation, an enum may be enough.
Key insight: Use State pattern only if transition rules or state-specific behavior are important enough to justify the added structure.
Key takeaway: Enum stores the status; State pattern manages state-dependent behavior and transition rules.
State Transitions and Order Lifecycle Rules
The order lifecycle was discussed as a set of states such as placed, preparing, ready, and delivered, with the main concern being how to move between them safely.
The important part is not the exact naming, but the fact that transitions should be controlled rather than assumed to happen automatically in one linear chain.
For example:
placed may move to preparing
preparing may move to ready
ready may move to delivered
Invalid transitions should be blocked, such as:
placed directly to delivered
delivered back to preparing
The current state should decide what the next allowed state can be.
This is what makes the State pattern valuable in order lifecycle management: it localizes the rules for each state and prevents illegal updates.
Watch out: Do not model order progression as if it were a chain of automatic sequential steps if the transitions are actually manual and time-separated.
Key takeaway: State-based validation keeps order transitions explicit, safe, and easy to extend.
Interview Strategy: Focus on the Hard Parts First
A recurring lesson was about how to approach an interview or design exercise under time pressure. The main message was that writing lots of code is not the goal. The goal is to demonstrate that you can identify and solve the hard parts of the problem.
A tight implementation window makes this especially important:
45–50 minutes is very tight for a full implementation
1 hour 20 minutes is more realistic for a thorough design exercise
Strong candidates show the difficult design logic first, not a mountain of boilerplate.
Practical advice
Solve the complex parts first
Leave easy boilerplate for later
If needed, note placeholders like:
“Will add getters/setters later”
“Will fill in boilerplate later”
Examples of low-value code that should not dominate your time:
getter/setter generation
access modifier details
mechanical builder setup
Key insight: Interviewers are looking for design judgment and reasoning, not just code volume.
Key takeaway: Focus on the hardest design decisions early; boilerplate can wait.
Scope and Pattern Selection: Don’t Force Extra Patterns
There was also a direct discussion about whether the solution had to include many patterns such as Singleton, Builder, Factory, Observer, and Decorator.
The guidance was to avoid forcing patterns just to show breadth. Instead, focus on the parts of the problem that are actually complex.
Good pattern selection means:
use Decorator for add-ons
use Observer for notifications
use State only if transition control is needed
use a factory if object creation needs to be centralized
avoid patterns that add complexity without solving a real problem
Design patterns are tools, not requirements.
Watch out: Over-engineering is a common mistake. A “more patterns” solution is not automatically a better one.
Key takeaway: Choose patterns because they fit the problem, not because you want to use them.
Factory Pattern for Beverage Creation
A Factory was also discussed as a way to centralize beverage creation. The idea is to hide the object construction logic from the main flow and choose the correct beverage based on input.
For example:
if the requested drink is known, instantiate the matching beverage class
otherwise, fall back to a default/base object
This is useful when the caller should not know the exact construction details.
Use a factory when object selection depends on input and you want to keep creation logic in one place.
Key takeaway: Factory helps keep drink creation separate from order handling and customization.
Practical Design Principles and Scope Clarification
A strong thread throughout the discussion was to keep the design grounded in the actual requirements and to clarify scope early.
A useful heuristic was to ask whether an existing class can be reused before creating a new one.
The general approach recommended was:
identify the right core entities first
build on top of them
add patterns only when they improve the design
ask clarifying questions if the expected level of complexity is unclear
Examples of good clarifying questions:
Do we need validations for each order status change?
Should some transitions be blocked?
Is a simple status update enough?
Interviewers often reveal the intended scope if you ask the right questions.
Key insight: Good design is often about restraint and reuse, not adding more abstractions.
Key takeaway: Reuse what exists, clarify scope early, and keep the solution as simple as the problem allows.
Quick Reference
Beverage abstraction first; concrete drinks like Espresso, Latte, Cappuccino
Decorator for milk, sugar, caramel, whipped cream; wrap base drink and add cost/description
Order sits above beverages; can hold one or more beverages, plus status and observers
Observer for kitchen, billing, customer notifications
Notify only when status actually changes to avoid duplicates
Enum is enough for simple status storage
State pattern is useful when transition rules and state-specific behavior matter
Don’t force State if a simple updateStatus check is enough
Factory can centralize beverage creation from input
In interviews, solve the hard parts first and defer boilerplate
Clarify scope early: “store status” vs “enforce transitions” is a key distinction
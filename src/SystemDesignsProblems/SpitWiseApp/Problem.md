Splitwise-like Expense Tracker: Problem Framing and Core Requirements
A Splitwise-style app lets people share expenses in a group setting such as trips, flats, or dinners. The purpose is not just to record payments, but to track who ultimately owes whom after all shared expenses are considered.
The core flow is: create users, add them to groups, record expenses inside a group, and compute balances. A group has a name and a list of members. Any group member can pay for something and log that expense for the group.
Key insight: The person who pays is not necessarily the person who bears the cost. Payment and responsibility are separate.
The design should let the system compute the net balance for each person rather than just raw per-expense shares. That is what makes settlement possible.
Example walkthrough
If Alice pays 900 for a 3-person trip and the bill is split equally, each person owes 300. Alice has paid 600 more than her share, so her balance is +600, while Bob and Carol each have -300.
If instead the actual consumption differs, the system should reflect that. For example, if the total expense is 500 and the shares are 100, 200, and 200, then the app should track those exact responsibilities rather than forcing an equal split.
Key insight: The app should calculate net amounts, not just store the original expense amount.
Key takeaway: Model the app around users, groups, expenses, and net balances, not just around raw payments.
Expense Splitting Strategies and Extensibility
The first split strategies discussed were equal split and exact split.
In an equal split, the total amount is divided evenly among all participants. For example, 5 people and a bill of 1000 means each person owes 200.
In an exact split, the owed amounts are specified directly for each participant. For example, if the total expense is 500, one person may owe 100, another 200, and another 200, with those values entered explicitly.
The important design goal is that the system should be open to new strategies later, such as percentage-based splits, weighted splits, or custom formulas, without rewriting the existing logic everywhere.
Key insight: The code should be open to adding a new split strategy without changing the existing logic everywhere.
A central factory should be the only place that creates split objects. That keeps split creation controlled and makes it easier to add new split types later.
Exact split vs equal split
Equal split calculates shares automatically.
Exact split uses manually specified amounts.
Watch out: In an exact split, the values must be provided directly and should not be treated like percentages or inferred amounts.
Key takeaway: Design split handling through interchangeable strategies, with a factory as the single creation point.
Notification Handling with Pluggable Listeners
Whenever an expense is added, all registered listeners should be notified automatically. The group should not depend directly on any concrete notification class.
Possible notification channels mentioned were in-app notification and email notification. The idea is that when an expense is recorded, interested parties get a short update automatically.
Key insight: Use a design where the group does not know the concrete notification mechanism. That makes it easy to plug in different notification types later.
This is a classic extensibility point: the expense system should publish the event, and notification handlers should subscribe to it.
Watch out: The group should not be tightly coupled to a specific notifier implementation.
Key takeaway: Notifications should be observer-like and decoupled from the expense logic.
Central Expense Manager and Validation Rules
There should be exactly one central manager handling expenses so that all records and balance calculations remain consistent in one place. Expense handling should not be split across multiple objects independently managing different parts of the logic.
Key insight: Keep expense handling centralized so the balance logic and stored records stay consistent.
Several validations are mandatory:
Expense amount cannot be negative.
In an exact split, the split amounts must sum to the total expense.
In a percentage split, percentages must add up to 100%.
The payer must be a member of the group.
A group must have at least one member before expenses can be recorded.
Required fields should not be null or blank, including:
user name
group name
description
users
groups
expenses
observer strategies / notification handlers
Key insight: Many design problems fail in practice because of missing validation. These checks are part of the core solution, not an afterthought.
Worked validation examples
If the total expense is 100 and the split amounts are 30 and 50, that is invalid because the total adds up to 80, not 100.
If percentages are 10% and 20%, that is invalid because they do not cover the full 100%.
Watch out: Don’t assume the payer can be outside the group or that empty groups can accept expenses.
Key takeaway: Centralized expense management only works if the system enforces strict validation on amounts, splits, and membership.
How to Think About the Problem in an Interview
The design is decentralized in the sense that any member can record an expense. There is no separate manager or admin whose job is to decide everyone’s share. If someone pays for petrol, lunch, or any other shared cost, that person records the expense and specifies the split.
A good interview demo does not need full production features. A simple main method that creates a group, adds members, records an expense, and prints the resulting balances is enough to prove the logic. You do not need real email, messaging, or UI.
Key insight: In system-design / OOP interview coding, a bare-bones runnable demo is enough to prove the model and logic.
Key takeaway: Treat expense creation as a decentralized action by any group member, and demonstrate the design with a simple runnable flow.
Quick Reference
Core objects: user, group, expense, split strategy, balance
Group: has a name and members
Expense flow: payer records amount + description + split details
Balance logic: compute net amounts owed/receivable
Split strategies:
equal split → divide evenly
exact split → use explicit amounts
Extensibility: add new split types without changing existing logic everywhere
Factory rule: one central factory creates split objects
Notifications: notify registered listeners when an expense is added
Centralization: keep expense handling in one manager/source of truth
Validations:
amount ≥ 0
exact split totals match expense
percentage split totals = 100%
payer must belong to group
group must have at least one member
required fields must be present
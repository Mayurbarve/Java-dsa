/*
PROBLEM STATEMENT
-----------------
Design a simplified Splitwise-style expense tracker. The app lets a group of
friends or flatmates record shared expenses and always know WHO OWES WHOM HOW
MUCH — without any mental math.

Your design should make it EASY to:
   - add a new way to split an expense later (e.g. by weight/shares), OR
   - add a new notification channel later (e.g. push notification),
without having to modify any existing class.


REQUIREMENTS
------------
1. Users can be created and added to Groups.
   A Group has a name and a list of members (Users).

2. Any group member can record an expense:
       "I paid Rs. X for [description]. Split it as follows."

3. TWO ways to split an expense must be supported:

       Equal  — divide total equally among ALL group members.
                e.g. Rs. 900 among 3 → each owes Rs. 300.

       Exact  — the payer specifies each member's exact share in rupees.
                Shares must sum to the total amount.
                e.g. Alice Rs. 250, Bob Rs. 180, Carol Rs. 120.

   Adding a THIRD split type later must NOT require editing any existing
   split class — only adding a new one.

4. A central factory must be the only place that creates split objects.
   Calling code should NOT do `new EqualSplitStrategy()` directly.

5. Every time an expense is added to a group, all registered listeners are
   notified automatically. The Group must NOT reference any concrete
   notification class.

   Required listeners:
       App Notification  — shows an in-app alert listing each member's share.
       Email             — sends a one-line email summary of the new expense.

6. There must be EXACTLY ONE instance of the central manager (the class that
   creates users, groups, and expenses) in the entire application.

7. A balance calculator shows, for any group, the net balance per member:
       Positive balance  →  the group owes this member money (they overpaid).
       Negative balance  →  this member owes money (they underpaid).

   Example: Alice pays Rs. 900 hotel for a 3-person trip (equal split).
       Alice: paid 900, owes 300 → net +600  (gets back Rs. 600)
       Bob:   paid 0,   owes 300 → net -300  (owes Rs. 300)
       Carol: paid 0,   owes 300 → net -300  (owes Rs. 300)

8. INPUT VALIDATION — the system must reject invalid input cleanly by throwing
   IllegalArgumentException with a clear, actionable message.  Required checks:
       (a) Amount must be > 0.
       (b) Exact-split shares must sum to the total amount (±0.01 tolerance).
       (c) Percentage-split percentages must sum to 100 (±0.01 tolerance).
       (d) The payer must be a member of the group.
       (e) A group must have at least one member before recording an expense.
       (f) All required string fields (user name, group name, description) must
           be non-null and non-blank.
       (g) No null users, groups, expenses, observers, or strategies anywhere.
 */





package SystemDesignsProblems.SpitWiseApp;

public class SpitWiseMain {
    public static void main(String[] args) {

    }
}
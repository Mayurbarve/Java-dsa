package SystemDesign.Creational.Factory;
/*
1. Define the Product Interface
2. Define Concrete Products
3. Define an Abstract Creator
4. Define Concrete Creators
5. Client Code
6. Adding a New Type

| Your Code                  | Factory Method Role |
| -------------------------- | ------------------- |
| `Notification`             | Product             |
| `EmailNotification`        | Concrete Product    |
| `NotificationCreator`      | Creator             |
| `EmailNotificationCreator` | Concrete Creator    |
| `createNotification()`     | Factory Method      |
| `FactoryMain`              | Client              |

 */
public class FactoryMain{
    public static void main(String[] args) {

        //reference variable's type is:
        NotificationCreator creator; //You are only declaring a reference variable. not creating Object

                        //actual object
        creator = new EmailNotificationCreator(); //actual object is created: and store its reference into creator
        creator.send("Welcome to our platform!");


    }
}

/*
                    FactoryMain
                         |
                         |
            NotificationCreator creator;
                         |
                         ↓
           new EmailNotificationCreator()
                         |
                         ↓
               creator.send(message)
                         |
                         ↓
             NotificationCreator.send()
                         |
                         ↓
                createNotification()
                         |
                    polymorphism
                         |
                         ↓
     EmailNotificationCreator.createNotification()
                         |
                         ↓
              new EmailNotification()
                         |
                         ↓
               Notification object
                         |
                         ↓
                notification.send()
                         |
                         ↓
              EmailNotification.send()
                         |
                         ↓
            "Sending Email message: ..."
 */
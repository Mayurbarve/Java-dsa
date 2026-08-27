package SystemDesign.Creational.Factory;

abstract class NotificationCreator {
    //"Every concrete Creator must tell me how to create a Notification."
    public abstract Notification createNotification();

    public void send(String message){
        Notification notification = createNotification(); //notification = EmailNotification
        notification.send(message);
    }

    /*
        creator
           │
           ↓
EmailNotificationCreator
           │
           │ createNotification()
           ↓
    EmailNotification
     */
}

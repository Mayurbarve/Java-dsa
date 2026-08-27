package SystemDesign.Creational.Factory;

public class SmsNotificationCreator extends NotificationCreator {

    @Override
    public Notification createNotification() {
        return new smsNotification();
    }
}

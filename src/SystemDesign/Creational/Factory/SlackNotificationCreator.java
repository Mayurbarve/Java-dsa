package SystemDesign.Creational.Factory;

public class SlackNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification() {
        return new slackNotification();
    }
}

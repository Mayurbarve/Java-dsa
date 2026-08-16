package Interfaces;

public class SlackNotifier implements NotificationService{
    public void sendNotification(String recipient, String message ){
        System.out.println("Slack Channel: " + recipient + " | " + message);
    }
}
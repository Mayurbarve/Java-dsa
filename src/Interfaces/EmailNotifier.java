package Interfaces;

public class EmailNotifier implements NotificationService{

    public void sendNotification(String recipient, String message) {
        System.out.println("Sending Notification to " + recipient + " | " + message );
    }
}
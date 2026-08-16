package Interfaces;

public class WebhookNotifier implements NotificationService{
    public void sendNotification(String recipient, String message){
        System.out.println("WebHook URL " + recipient + " | " + message);
    }
}
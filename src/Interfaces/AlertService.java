package Interfaces;

public class AlertService {
    private final NotificationService notificationService;

    public AlertService(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public void triggerNotification(String recipient, String message){
        String alert = "ALERT: " + message;
        notificationService.sendNotification(recipient, alert);
    }
}
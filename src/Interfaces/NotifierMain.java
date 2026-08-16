package Interfaces;

public class NotifierMain {
    public static void main(String[] args){


        AlertService emailSer = new AlertService(new EmailNotifier());
        emailSer.triggerNotification("mayurbarve04@gmail.com", "CPU Usage High");

        AlertService slackSer = new AlertService(new SlackNotifier());
        slackSer.triggerNotification("#Incident", "Database Connection Pool exhausted");

        AlertService webhookSer = new AlertService(new WebhookNotifier());
        webhookSer.triggerNotification("https://hooks.example.com/alerts", "Disk usage at 90%");



    }
}
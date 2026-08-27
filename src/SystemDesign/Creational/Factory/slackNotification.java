package SystemDesign.Creational.Factory;

public class slackNotification implements Notification {

    @Override
    public void send(String message){
        System.out.println("Sending slack message: " + message);
    }
}

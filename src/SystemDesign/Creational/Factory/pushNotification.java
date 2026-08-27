package SystemDesign.Creational.Factory;

public class pushNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Sending push message: " + message);
    }
}

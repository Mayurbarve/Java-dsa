package SystemDesign.Creational.Factory;

public class smsNotification implements Notification {

    @Override
    public void send(String message){
        System.out.println("Sending SMS message: " + message);
    }
}

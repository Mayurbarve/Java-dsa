package enums;

public class enumEx {
    void main() {
        Order order = new Order("ORD-001", PaymentMethod.CREDIT_CARD, 99.99);

        order.displayInfo(); //PLACED

        order.updateStatus(); // PLACED -> CONFIRMED

        order.displayInfo();

        order.updateStatus();
        order.displayInfo();


        //order.updateStatus(); // CONFIRMED -> SHIPPED
        //order.displayInfo();

        System.out.println("Cancel after shipping: " + order.cancel()); // false

        order.displayInfo(); //PLACED
    }
}
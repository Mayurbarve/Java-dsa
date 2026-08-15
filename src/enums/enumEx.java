package enums;

public class enumEx {
    void main() {
        Order order = new Order("ORD-001", PaymentMethod.CREDIT_CARD, 99.99);
        order.displayInfo();

        order.advanceStatus(); // PLACED -> CONFIRMED
        order.advanceStatus();
        order.advanceStatus(); // CONFIRMED -> SHIPPED
        order.displayInfo();

        System.out.println("Cancel after shipping: " + order.cancel()); // false
    }
}
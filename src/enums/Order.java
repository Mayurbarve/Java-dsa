package enums;

public class Order {
    private final String oderId;
    private OrderStatus status;
    private final PaymentMethod paymentMethod;
    private final double amount;

    Order(String orderId, PaymentMethod paymentMethod, double amount){
        this.oderId = orderId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = OrderStatus.PLACED;
    }

    public boolean advanceStatus(){
        switch(status){
            case PLACED:
                status = OrderStatus.CONFIRMED;
                return true;
            case CONFIRMED:
                status = OrderStatus.SHIPPED;
                return true;
            case SHIPPED:
                status = OrderStatus.DELIVERED;
                return true;
            default:
                return false;
        }
    }


    public boolean cancel(){
        if(status == OrderStatus.PLACED || status == OrderStatus.CONFIRMED){
            status = OrderStatus.CANCELED;
            return true;
        }
        return false;
    }

    public double getTotalAmount(){
        return amount + (amount * paymentMethod.getFeePercent() / 100);
    }

    public void displayInfo(){
        System.out.println(
                "Order ID: " + oderId + " | " +
                "Order Status: " + status + " | " +
                "Payment Method: " + paymentMethod.getDisplayName() + " | " +
                "Order Amount: " + amount + " | " +
                "Total Amount: " + getTotalAmount()
        );
    }


}

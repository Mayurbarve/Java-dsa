package Polymorphism.DiscountCalculator;

public class FlatDiscount extends Discount {

    private double amount;

    public FlatDiscount(double amount){
        super("$" + amount + " off");
        this.amount = amount;
    }

    @Override
    public double applyDiscount(double price) {
        return Math.max(price - amount, 0);
    }
}

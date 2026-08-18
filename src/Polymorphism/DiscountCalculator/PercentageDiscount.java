package Polymorphism.DiscountCalculator;

public class PercentageDiscount extends Discount {

    private double percentage;

    public PercentageDiscount(double percentage){
        super(percentage + "% off");
        this.percentage = percentage;
    }

    @Override
    double applyDiscount(double price) {
        return price * (1 - percentage / 100);
    }
}

package Polymorphism.DiscountCalculator;

abstract public class Discount {
    protected String label;

    public Discount(String label) {
        this.label = label;
    }

    abstract double applyDiscount(double price);

    public void describe(double originalPrice){
        double discountedPrice = applyDiscount(originalPrice);
        System.out.println(label + ": $" + String.format("%.2f", originalPrice)
                + " -> $" + String.format("%.2f", discountedPrice));
    }
}

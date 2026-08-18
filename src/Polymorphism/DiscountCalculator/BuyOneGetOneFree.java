package Polymorphism.DiscountCalculator;

public class BuyOneGetOneFree extends Discount {

    public BuyOneGetOneFree(){
        super("BuyOneGetOneFree");
    }

    public double applyDiscount(double discount){
        return discount / 2;
    }
}

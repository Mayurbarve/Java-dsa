package Polymorphism.DiscountCalculator;

public class OrderProcessor{

    public void processOrder(String itemName, double price, Discount discount){
        System.out.println("Item: " + itemName);
        discount.describe(price);
    }
}

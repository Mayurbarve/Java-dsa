package Polymorphism.DiscountCalculator;

/*
Design Discount Calculator Class
Problem: Build a pricing system where an OrderProcessor applies discounts polymorphically. Different discount types share a common base class with shared formatting logic, but each one calculates the discounted price differently. The processor works with any discount through the abstract Discount type.

Requirements:

Abstract Discount class with a label field (protected), an abstract apply(price) method that returns the discounted price, and a concrete describe(originalPrice) method that prints "label: $originalPrice -> $discountedPrice".
PercentageDiscount: takes a percentage (e.g., 20 means 20% off). Label is "20.0% off". Returns price * (1 - percentage/100).
FlatDiscount: takes a fixed amount off. Label is "$15.0 off". Returns price - amount (minimum 0).
BuyOneGetOneFree: halves the price. Label is "Buy 1 Get 1 Free". Returns price / 2.
OrderProcessor class with a processOrder(itemName, price, discount) method that prints the item name and calls describe() on the discount.
 */

public class OrderMain {
    public static void main(String[] args) {

        OrderProcessor processor = new OrderProcessor();

        processor.processOrder("Laptop", 999.99, new PercentageDiscount(20));
        processor.processOrder("Headphones", 49.99, new FlatDiscount(15));
        processor.processOrder("Keyboard", 79.98, new BuyOneGetOneFree());
    }
}
package SystemDesign.Behavioural.Strategy;
//Strategy Interface
//Concrete Strategy
//Context Class
//Clint Code
public class StrategyMain {
    public static void main(String[] args) {
        Order ord = new Order(56.7, "ZoneA", 512);

        ShippingStrategy flatRate = new FlatRateShipping(10.0);
        ShippingStrategy weightBased = new WeightBasedShipping(2.5);
        ShippingStrategy distanceBased = new DistanceBasedShipping(5.0);
        ShippingStrategy thirdParty = new ThirdPartyApiShipping(7.5, 0.02);

        // Create context with an initial strategy
        ShippingCostService shippingService = new ShippingCostService(flatRate);

        System.out.println("--- Order 1: Using Flat Rate (initial) ---");
        shippingService.CalculateShipping(ord);

        System.out.println("\n--- Order 1: Changing to Weight-Based ---");
        shippingService.setStrategy(weightBased);
        shippingService.CalculateShipping(ord);

        System.out.println("\n--- Order 1: Changing to Distance-Based ---");
        shippingService.setStrategy(distanceBased);
        shippingService.CalculateShipping(ord);

        System.out.println("\n--- Order 1: Changing to Third-Party API ---");
        shippingService.setStrategy(thirdParty);
        shippingService.CalculateShipping(ord);
    }
}
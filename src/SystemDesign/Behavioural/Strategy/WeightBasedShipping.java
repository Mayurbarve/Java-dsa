package SystemDesign.Behavioural.Strategy;

//Concrete Strategies
public class WeightBasedShipping implements ShippingStrategy {

    private final double PerKGCost;

    public WeightBasedShipping(double PerKGCost) {
        this.PerKGCost = PerKGCost;
    }

    @Override
    public double CalculateShipping(Order order) {
        System.out.println("Calculating the Weight Based Strategy ($ " + PerKGCost + ")");
        return order.getTotalWeight() * PerKGCost;
    }
}

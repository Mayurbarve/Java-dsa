package SystemDesign.Behavioural.Strategy;

//Concrete Strategies
public class FlatRateShipping implements ShippingStrategy {

    private double FlatRateCost;

    public FlatRateShipping(double rate) {
        this.FlatRateCost = rate;
    }

    @Override
    public double CalculateShipping(Order order) {
        System.out.println("Calculating with flat rate Strategy $(" + FlatRateCost +")");
        return FlatRateCost;
    }
}

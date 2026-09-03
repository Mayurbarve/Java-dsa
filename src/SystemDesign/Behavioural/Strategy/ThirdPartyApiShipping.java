package SystemDesign.Behavioural.Strategy;


//Concrete Strategies
public class ThirdPartyApiShipping implements ShippingStrategy {
    private final double baseRate;
    private final double ratePercentage;

    public ThirdPartyApiShipping(double baseRate, double ratePercentage) {
        this.baseRate = baseRate;
        this.ratePercentage = ratePercentage;
    }

    @Override
    public double CalculateShipping(Order order) {
        System.out.println("Calculating Third Party API Strategy");
        return baseRate + (order.getOrderValue()) * ratePercentage;
    }

}

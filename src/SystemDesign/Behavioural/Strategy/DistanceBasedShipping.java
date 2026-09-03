package SystemDesign.Behavioural.Strategy;

//Concrete Strategies
public class DistanceBasedShipping implements ShippingStrategy {

    private double ratePerKm;

    public DistanceBasedShipping(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    @Override
    public double CalculateShipping(Order order) {
        System.out.println("Calculating the Distance Based Strategy ($ " + ratePerKm + ")");
        return switch(order.getDestinationZone()){
            case "ZoneA" -> ratePerKm * 5.0;
            case "ZoneB" -> ratePerKm * 7.0;
            default -> ratePerKm * 10.0;
        };
    }

}

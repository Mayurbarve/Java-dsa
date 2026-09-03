package SystemDesign.Behavioural.Strategy;

//Context Class
public class ShippingCostService {
    private ShippingStrategy strategy;

    //Constructor set the initial strategy
    public ShippingCostService(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    //Method to Change Strategy Runtime


    public void setStrategy(ShippingStrategy strategy) {
        System.out.println("Shipping Strategy has been change to " + strategy.getClass().getSimpleName());
        this.strategy = strategy;
    }

    public double CalculateShipping(Order order) {
        if(strategy == null){
            throw new IllegalArgumentException("Strategy has not been set");
        }
        double shippingCost = strategy.CalculateShipping(order);
        System.out.println("Shipping Cost: " + shippingCost + " using " + strategy.getClass().getSimpleName());
        return shippingCost;
    }
}

package SystemDesign.Behavioural.Observer;

public class LiveActiveDisplay implements FitnessObserverObserver {

    @Override
    public void update(FitnessData data) {
        System.out.println("Live Active Display");
        System.out.println("Live Steps: " + data.getSteps() +
                           "Live Calories: " + data.getCalories() +
                           "Active Minute" + data.getActiveMinutes());
    }
}

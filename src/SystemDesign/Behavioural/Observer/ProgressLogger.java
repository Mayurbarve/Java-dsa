package SystemDesign.Behavioural.Observer;

public class ProgressLogger implements FitnessObserverObserver{
    @Override
    public void update(FitnessData data){
        System.out.println("Logger Live Data");
        System.out.println("Live Steps: " + data.getSteps() +
                "Live Calories: " + data.getCalories() +
                "Active Minute" + data.getActiveMinutes());
    }
}

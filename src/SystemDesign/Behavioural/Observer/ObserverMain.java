package SystemDesign.Behavioural.Observer;

//Observer Interface
//Subject Interface
//Concrete Subject
//Concrete Observer
//client
public class ObserverMain {
    public static void main(String[] args) {
        FitnessData fitnessData = new FitnessData();

        LiveActiveDisplay display = new LiveActiveDisplay();
        ProgressLogger logger = new ProgressLogger();
        GoalNotifier notifier = new GoalNotifier();

        // Register observers
        fitnessData.registerObserver(display);
        fitnessData.registerObserver(logger);
        fitnessData.registerObserver(notifier);

        // Simulate updates
        fitnessData.newFitnessDataPush(500, 5, 20);
        fitnessData.newFitnessDataPush(9800, 85, 350);
        fitnessData.newFitnessDataPush(10100, 90, 380);

        // Remove logger and reset notifier
        fitnessData.removeObserver(logger);
        notifier.resetGoal();
        fitnessData.dailyReset();
    }
}

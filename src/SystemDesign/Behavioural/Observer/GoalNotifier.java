package SystemDesign.Behavioural.Observer;

public class GoalNotifier implements FitnessObserverObserver{
    private final int stepGoal = 10000;
    private  boolean goalReach = false;


    @Override
    public void update(FitnessData data){
        if(data.getSteps() >= stepGoal){
            System.out.println("Notifier → 🎉 Goal Reached! You've hit " + stepGoal + " steps!");
            goalReach = true;
        }
    }

    public boolean isGoalReach() {
        return goalReach;
    }

    public void resetGoal(){
        goalReach = false;
    }
}

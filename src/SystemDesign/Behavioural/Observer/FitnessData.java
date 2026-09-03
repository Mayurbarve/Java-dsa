package SystemDesign.Behavioural.Observer;

import java.util.ArrayList;
import java.util.List;

public class FitnessData implements FitnessDataSubject{
    private int steps;
    private int activeMinutes;
    private int calories;

    private final List<FitnessObserverObserver> observers =  new ArrayList<>();

    @Override
    public void registerObserver(FitnessObserverObserver o) {
        observers.add(o);
    }
    @Override
    public void removeObserver(FitnessObserverObserver o) {
        observers.remove(o);
    }
    @Override
    public void notifyObservers() {
        for(FitnessObserverObserver o : observers) {
            o.update(this);
        }
    }

    public void newFitnessDataPush(int steps, int activeMinutes, int calories) {
        this.steps = steps;
        this.activeMinutes = activeMinutes;
        this.calories = calories;

        notifyObservers();
    }

    public void dailyReset() {
        steps = 0;
        activeMinutes = 0;
        calories = 0;
    }

    public int getSteps() {
        return steps;
    }
    public int getActiveMinutes() {
        return activeMinutes;
    }
    public int getCalories() {
        return calories;
    }
}

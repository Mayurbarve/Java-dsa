package SystemDesign.Behavioural.Observer;

public interface FitnessDataSubject {
    void registerObserver(FitnessObserverObserver observer);
    void removeObserver(FitnessObserverObserver observer);
    void notifyObservers();
}

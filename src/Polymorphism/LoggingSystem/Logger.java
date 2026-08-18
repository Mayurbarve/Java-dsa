package Polymorphism.LoggingSystem;

public interface Logger {
    void log(String level, String message);
    String getDestination();
}

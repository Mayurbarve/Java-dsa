package Polymorphism.LoggingSystem;

public class FileLogger implements Logger {
    private String filePath;

    public FileLogger(String filePath) {
        // TODO: initialize this.filePath
    }

    public void log(String level, String message) {
        // TODO: print "Writing to filePath: [level] message"
    }

    public String getDestination() {
        // TODO: return "File: filePath"
        return "";
    }
}

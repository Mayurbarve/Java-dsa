package Polymorphism.LoggingSystem;

public class DatabaseLogger implements Logger{
    private String tableName;

    public DatabaseLogger(String tableName) {
        // TODO: initialize this.tableName
    }

    public void log(String level, String message) {
        // TODO: print "INSERT INTO tableName: [level] message"
    }

    public String getDestination() {
        // TODO: return "Database: tableName"
        return "";
    }
}

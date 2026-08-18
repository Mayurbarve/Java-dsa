package Polymorphism.LoggingSystem;

/*
Design Logging System Class
Problem: Build a logging system where the application uses a Logger interface polymorphically. Different logger implementations send messages to different destinations, and the application doesn't know or care which one it's using.

Requirements:

Logger interface with log(level, message) and getDestination() methods.
ConsoleLogger: prints formatted log messages to the console.
FileLogger: simulates writing to a file (print with file path prefix).
DatabaseLogger: simulates inserting into a database (print with table name prefix).
Application class that takes a Logger in its constructor and uses it throughout.


Expected output
--- Using Console ---
[INFO] Application starting...
[INFO] Processing data...
[INFO] Application shutting down.

--- Using File: /var/log/app.log ---
Writing to /var/log/app.log: [INFO] Application starting...
Writing to /var/log/app.log: [INFO] Processing data...
Writing to /var/log/app.log: [INFO] Application shutting down.

--- Using Database: app_logs ---
INSERT INTO app_logs: [INFO] Application starting...
INSERT INTO app_logs: [INFO] Processing data...
INSERT INTO app_logs: [INFO] Application shutting down.
 */

import java.util.List;

public class LoggerMain {
    public static void main(String[] args) {
        List<Logger> loggers = List.of(
                new ConsoleLogger(),
                new FileLogger("/var/log/app.log"),
                new DatabaseLogger("app_logs")
        );

        for (Logger logger : loggers) {
            System.out.println("--- Using " + logger.getDestination() + " ---");
            Application app = new Application(logger);
            app.run();
            System.out.println();
        }
    }
}
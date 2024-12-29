package org.example;

public class Task1 {
    public static void main(String[] args){
        Logger logger = Logger.getInstance();
        logger.log("Application started");
        logger.logInfo("Loading modules");
        logger.logWarning("Low disk space");
        logger.logError("Unhandled exception");
    }
}
class Logger {
    private static Logger instance;
    private Logger() {}
    public static Logger getInstance(){
        if (instance == null){
            instance = new Logger();
        }
        return instance;
    }
    public void log(String message){
        System.out.println("LOG: " + message);
    }
    public void logInfo(String message){
        System.out.println("INFO: " + message);
    }
    public void logWarning(String message){
        System.out.println("WARNING: " + message);
    }
    public void logError(String message){
        System.out.println("ERROR: " + message);
    }
}

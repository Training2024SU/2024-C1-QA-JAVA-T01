package co.com.sofka.utils;

import java.util.logging.Logger;

public class UserLogger {
    private static final Logger logger = Logger.getLogger(UserLogger.class.getName());
    public static void info(String message){
        logger.info(message);
    }
    public static void error(String message){
        logger.severe(message);
    }

}

package com.sofkau.dialogo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
    private static Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());
    public static void info(String MSN){
        LOGGER.info(MSN);
    }
}

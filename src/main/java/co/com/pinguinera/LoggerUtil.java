package co.com.pinguinera;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;

/**
 * LoggerUtil proporciona un Logger global para la aplicación.
 * Configura el Logger para mostrar solo los mensajes de info log sin información adicional.
 */
public class LoggerUtil {   private static final Logger appLogger = Logger.getLogger("co.com.pinguinera");

    static {
        for (var handler : appLogger.getHandlers()) {
            appLogger.removeHandler(handler);
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        Formatter simpleFormatter = new Formatter() {
            @Override
            public String format(LogRecord entry) {
                return entry.getMessage() + System.lineSeparator();
            }
        };

        consoleHandler.setFormatter(simpleFormatter);
        appLogger.addHandler(consoleHandler);
        appLogger.setLevel(Level.INFO);
        appLogger.setUseParentHandlers(false);
    }

    public static Logger getLogger() {
        return appLogger;
    }

    private LoggerUtil() {
        // Constructor privado vacío para prevenir instanciaciones no deseadas.
    }
}

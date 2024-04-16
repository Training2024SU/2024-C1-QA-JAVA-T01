package co.com.pinguinera;

import java.util.logging.Logger;

public class LoggerUtil {
    // Logger global
    private static final Logger LOGGER = Logger.getLogger("co.com.pinguinera");

    // Método para obtener el logger global
    public static Logger getLogger() {
        return LOGGER;
    }
}

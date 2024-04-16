package co.com.sofkau.model;

import co.com.sofkau.integration.database.mysql.MySqlOperation;

import java.util.HashMap;
import java.util.Map;

public class EdadSugerida {

    private String edadSugeridad;

    private static MySqlOperation mySqlOperation;

    public static Map<String, String> edadSugeridas = new HashMap<>();

    public String getEdadSugeridad() {
        return edadSugeridad;
    }

    public void setEdadSugeridad(String edadSugeridad) {
        this.edadSugeridad = edadSugeridad;
    }

    public static MySqlOperation getMySqlOperation() {
        return mySqlOperation;
    }

    public static void setMySqlOperation(MySqlOperation mySqlOperation) {
        EdadSugerida.mySqlOperation = mySqlOperation;
    }
}

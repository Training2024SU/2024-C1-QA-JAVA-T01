package co.com.sofkau.model;

import co.com.sofkau.integration.database.mysql.MySqlOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaGenero {

    private String areaGenero;

    private static MySqlOperation mySqlOperation;

    public static Map<String, String> areaGeneros = new HashMap<>();

    public String getAreaGenero() {
        return areaGenero;
    }

    public void setAreaGenero(String areaGenero) {
        this.areaGenero = areaGenero;
    }

    public Map<String, String> getAreaGeneros() {
        return areaGeneros;
    }

    public static MySqlOperation getMySqlOperation() {
        return mySqlOperation;
    }

    public static void setMySqlOperation(MySqlOperation mySqlOperation) {
        AreaGenero.mySqlOperation = mySqlOperation;
    }
}

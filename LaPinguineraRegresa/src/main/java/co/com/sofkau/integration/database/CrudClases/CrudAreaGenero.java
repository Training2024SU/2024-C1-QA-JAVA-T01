package co.com.sofkau.integration.database.CrudClases;

import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.model.AreaGenero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CrudAreaGenero {

    public static void consultarTodasAreasGenero(MySqlOperation mySqlOperation) throws SQLException {
        mySqlOperation.setSqlStatement("SELECT * FROM areaGenero");
        mySqlOperation.executeSqlStatement();

        ResultSet resultSet = mySqlOperation.getResulset();


        while (resultSet.next()) {

            String areaGenero = resultSet.getString("areaGenero");
            String Publicacion_titulo = resultSet.getString("Publicacion_titulo");

            AreaGenero.areaGeneros.put(areaGenero,Publicacion_titulo);

        }

        for (Map.Entry<String, String> entry : AreaGenero.areaGeneros.entrySet()) {
            String areaGenero = entry.getKey();
            String Publicacion_titulo = entry.getValue();
            System.out.println("areaGenero: " + areaGenero + ", Publicacion_titulo: " + Publicacion_titulo);
        }

    }

    public static void insertIntoAreaGenero(MySqlOperation mySqlOperation, String areaGenero, String titulo_publicacion){

        mySqlOperation.setSqlStatement("INSERT INTO areaGenero (areaGenero, Publicacion_titulo) VALUES (?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(areaGenero,titulo_publicacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void eliminarAreaGenero(MySqlOperation mySqlOperation, String titulo) {
        mySqlOperation.setSqlStatement("DELETE FROM areaGenero WHERE Publicacion_titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ActualizarAreaGenero(MySqlOperation mySqlOperation, String areaGenero, String titulo) {

        mySqlOperation.setSqlStatement("UPDATE areaGenero SET areaGenero = ? WHERE Publicacion_titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(areaGenero,titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

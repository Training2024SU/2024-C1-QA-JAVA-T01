package co.com.sofkau.integration.database.CrudClases;

import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.model.AreaGenero;
import co.com.sofkau.model.EdadSugerida;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CrudEdadSugerida {

    public static void consultarTodasEdadSugeridad(MySqlOperation mySqlOperation) throws SQLException {
        mySqlOperation.setSqlStatement("SELECT * FROM edadSugerida");
        mySqlOperation.executeSqlStatement();

        ResultSet resultSet = mySqlOperation.getResulset();


        while (resultSet.next()) {
            String edad = resultSet.getString("edad");
            String Publicacion_titulo = resultSet.getString("Publicacion_titulo");

            EdadSugerida.edadSugeridas.put(edad, Publicacion_titulo);
        }

    }

    public static void insertIntoEdadSugerida (MySqlOperation mySqlOperation, String edad, String titulo_publicacion)
    {

        mySqlOperation.setSqlStatement("INSERT INTO edadSugerida (edad, Publicacion_titulo) VALUES (?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(edad, titulo_publicacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void eliminarEdadSugerida (MySqlOperation mySqlOperation, String titulo){
        mySqlOperation.setSqlStatement("DELETE FROM edadSugerida WHERE Publicacion_titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ActualizarEdadSugerida (MySqlOperation mySqlOperation, String edad, String titulo){

        mySqlOperation.setSqlStatement("UPDATE edadSugerida SET edad = ? WHERE Publicacion_titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(edad, titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

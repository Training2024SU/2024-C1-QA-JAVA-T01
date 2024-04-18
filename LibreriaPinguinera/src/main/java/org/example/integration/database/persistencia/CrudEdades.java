package org.example.integration.database.persistencia;

import org.example.integration.database.mysql.MySqlOperation;
import org.example.model.EdadSugerida;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.integration.database.mysql.MySqlConstants.SELECT;
import static org.example.logica.control.ConstantesLogica.TIPO_EDAD_SUGERIDA;

public class CrudEdades {
    MySqlOperation mySqlOperation;

    public CrudEdades() {
        mySqlOperation = new MySqlOperation();
    }

    public void seleccionarDatos() throws SQLException {
        mySqlOperation.setSqlStatement(String.format(SELECT, TIPO_EDAD_SUGERIDA));
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResulset();
        llenarEdades(resultSet);
    }

    public void crearEntidad(EdadSugerida entidad) {

    }

    private void llenarEdades(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String tituloPublicacion = resultSet.getString("titulo_publicacion");
            String edad = resultSet.getString("area_generocol");

            EdadSugerida edadSugerida = new EdadSugerida(tituloPublicacion, edad);
            EdadSugerida.edades.add(edadSugerida);
        }
    }
    public static List<EdadSugerida> obtenerEdadSugerida(String titulo) {

        List<EdadSugerida> edades = new ArrayList<>();
        for (EdadSugerida edad : EdadSugerida.edades) {
            if (edad.getTituloPublicacion().equals(titulo)) {
                edades.add(edad);
            }
        }
        return edades;
    }
}

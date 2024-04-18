package org.example.integration.database.persistencia;

import org.example.integration.database.mysql.MySqlOperation;
import org.example.model.AreaGenero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.integration.database.mysql.MySqlConstants.SELECT;
import static org.example.logica.control.ConstantesLogica.TIPO_AREA_GENERO;

public class CrudAreaGenero {
    MySqlOperation mySqlOperation;

    public CrudAreaGenero() {
        mySqlOperation = new MySqlOperation();
    }

    public void seleccionarDatos() throws SQLException {
        mySqlOperation.setSqlStatement(String.format(SELECT, TIPO_AREA_GENERO));
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResulset();
        llenarAreaGenero(resultSet);
    }



    private void llenarAreaGenero(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String tituloPublicacion = resultSet.getString("titulo_publicacion");
            String areaGenero = resultSet.getString("area_generocol");

            AreaGenero genero = new AreaGenero(tituloPublicacion, areaGenero);
            AreaGenero.generos.add(genero);
        }
    }

    public static List<AreaGenero> obtenerGeneroPublicacion(String titulo) {

        List<AreaGenero> generos = new ArrayList<>();
        for (AreaGenero genero : AreaGenero.generos) {
            if (genero.getTitulo().equals(titulo)) {
                generos.add(genero);
            }
        }
        return generos;
    }
}

package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.AreaGenero;
import com.sofkau.model.EdadSugerida;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class EdadSugeridaRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearEdadSugerida(EdadSugerida edadSugerida) {
        String query = String.format("INSERT INTO edad_sugerida (titulo_publicacion, edad) VALUES ('%s', '%s')",
                edadSugerida.getTitulo(), edadSugerida.getEdadSugeridad());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static ArrayList<EdadSugerida> consultarEdadesSugeridas() {
        String query = "SELECT * FROM edad_sugerida";
        IngresoQuery.ejecutarConsultaQuery(query);
        ArrayList<EdadSugerida> edadesSugeridas = new ArrayList<>();
        ResultSet resultSet = mySqlOperation.getResulset();

        try {
            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo_publicacion");
                String edad = resultSet.getString("edad");

                EdadSugerida edadSugerida = new EdadSugerida();
                edadSugerida.setTitulo(titulo);
                edadSugerida.setEdadSugeridad(edad);

                edadesSugeridas.add(edadSugerida);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return edadesSugeridas;
    }

    public static void actualizarEdadSugerida(String tituloPublicacion, EdadSugerida edadSugerida) {
        String query = String.format("UPDATE edad_sugerida SET titulo_publicacion = '%s', edad = '%s' WHERE titulo_publicacion = '%s'", edadSugerida.getTitulo(), edadSugerida.getEdadSugeridad(), tituloPublicacion);
        IngresoQuery.ejecutarIngresoQuery(query);
    }
}

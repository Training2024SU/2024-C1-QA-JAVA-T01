package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.AreaGenero;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AreaGeneroRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearAreaGenero(AreaGenero areaGenero) {
        String query = String.format("INSERT INTO area_genero (titulo_publicacion, area_generocol) VALUES ('%s', '%s')",
                areaGenero.getTitulo(), areaGenero.getAreaGenero());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static ArrayList<AreaGenero> consultarAreasGenero() {
        String query = "SELECT * FROM area_genero";
        IngresoQuery.ejecutarConsultaQuery(query);
        ArrayList<AreaGenero> areaGeneros = new ArrayList<>();
        ResultSet resultSet = mySqlOperation.getResulset();

        try {
            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo_publicacion");
                String areaGenero = resultSet.getString("area_generocol");

                AreaGenero area = new AreaGenero();
                area.setTitulo(titulo);
                area.setAreaGenero(areaGenero);

                areaGeneros.add(area);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return areaGeneros;
    }
}
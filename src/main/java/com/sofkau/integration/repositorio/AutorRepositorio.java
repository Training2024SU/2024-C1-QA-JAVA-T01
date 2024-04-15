package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Autor;
import com.sofkau.model.Empleado;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AutorRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearAutor(Autor autor) {
        String query = String.format("INSERT INTO Autor (id, nombre) VALUES ('%s', '%s')", autor.getId(), autor.getNombre());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static HashMap<String, Autor> consultarAutores() {
        String query = "Select * from Autor";
        IngresoQuery.ejecutarConsultaQuery(query);
        HashMap<String, Autor> autores = new HashMap<>();
        ResultSet resultSet = mySqlOperation.getResulset();

        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");

                Autor autor = new Autor(id, nombre);

                autores.put(autor.getId(),autor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autores;
    }


}

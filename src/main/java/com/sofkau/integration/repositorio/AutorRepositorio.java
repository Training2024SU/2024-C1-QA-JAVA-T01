package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Autor;
import com.sofkau.model.Empleado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AutorRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearAutor(Autor autor) throws SQLException {
        String query = String.format("INSERT INTO Empleado (id, nombre) VALUES ('%s', '%s')", autor.getId(), autor.getNombre());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static HashMap<String, Autor> consultarAutores() throws SQLException {
        String query = "Select * from Autor";
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatement();

        HashMap<String, Autor> autores = new HashMap<>();
        ResultSet resultSet = mySqlOperation.getResulset();

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String nombre = resultSet.getString("nombre");

            Autor autor = new Autor(id, nombre);

            autores.put(autor.getId(),autor);
        }

        return autores;
    }


}

package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.conexionBD.DataBase;
import co.com.pinguinera.modelado.herencia_publicacion.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    private static final String CONSULTA_LIBROS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'LIBRO'";

    public List<Libro> obtenerTodosLosLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();

        Connection conexion = DataBase.conectar();
        try (PreparedStatement statement = conexion.prepareStatement(CONSULTA_LIBROS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Crear una instancia de Libro
                Libro libro = new Libro(
                        resultSet.getString("Titulo"),
                        resultSet.getString("autor"),
                        resultSet.getInt("num_paginas"),
                        resultSet.getInt("cant_ejemplares"),
                        resultSet.getInt("cant_prestados"),
                        resultSet.getInt("cant_disponible"),
                        null, // áreas
                        null  // edades
                );

                // Añadir el libro a la lista
                libros.add(libro);
            }
        }

        return libros;
    }
}

package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.modelado.herencia_publicacion.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCRUDLibros {

    private Connection conexion;

    // Constructor que recibe una conexión
    public ServicioCRUDLibros(Connection conexion) {
        this.conexion = conexion;
    }

    // Consultas SQL
    private static final String CONSULTA_LIBROS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'LIBRO'";
    private static final String INSERTAR_LIBRO = "INSERT INTO Publicacion (titulo, autor, num_paginas, cant_ejemplares, cant_prestados, tipo_publicacion) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_LIBRO = "UPDATE Publicacion SET titulo = ?, autor = ?, num_paginas = ?, cant_ejemplares = ?, cant_prestados = ? WHERE titulo = ? AND tipo_publicacion = 'LIBRO'";
    private static final String ELIMINAR_LIBRO = "DELETE FROM Publicacion WHERE titulo = ? AND tipo_publicacion = 'LIBRO'";


    // Método para agregar un nuevo libro a la base de datos
    public void agregar(Libro libro) throws SQLException {

        try (PreparedStatement statement = conexion.prepareStatement(INSERTAR_LIBRO)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setInt(3, libro.getNumPaginas());
            statement.setInt(4, libro.getCantEjemplares());
            statement.setInt(5, libro.getCantPrestados());
            statement.setString(6, libro.getTipoPublicacion().name());

            // Ejecuta la consulta
            statement.executeUpdate();
        }
    }

    // Método para obtener todos los libros desde la base de datos
    public List<Libro> obtenerTodos() throws SQLException {
        List<Libro> libros = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(CONSULTA_LIBROS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Crear una instancia de Libro
                Libro libro = new Libro(
                        resultSet.getString("titulo"),
                        resultSet.getString("autor"),
                        resultSet.getInt("num_paginas"),
                        resultSet.getInt("cant_ejemplares"),
                        resultSet.getInt("cant_prestados"),
                        resultSet.getInt("cant_disponible"),
                        null, // áreas (debes consultar otra tabla si hay datos adicionales)
                        null  // edades (lo mismo que áreas)
                );

                // Añadir el libro a la lista
                libros.add(libro);
            }
        }

        return libros;
    }

    // Método para actualizar un libro existente en la base de datos
    public void actualizar(Libro libro) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(ACTUALIZAR_LIBRO)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setInt(3, libro.getNumPaginas());
            statement.setInt(4, libro.getCantEjemplares());
            statement.setInt(5, libro.getCantPrestados());
            statement.setString(6, libro.getTitulo());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un libro de la base de datos
    public void eliminar(String tituloLibro) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(ELIMINAR_LIBRO)) {
            statement.setString(1, tituloLibro); // Usa el título en lugar de un ID numérico
            statement.executeUpdate();
        }
    }

}

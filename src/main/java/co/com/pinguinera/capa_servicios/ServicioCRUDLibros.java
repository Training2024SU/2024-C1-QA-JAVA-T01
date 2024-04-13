package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.capa_datos.LibroDAO; // Importa LibroDAO
import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import co.com.pinguinera.modelado.publicaciones.Libro;
import java.sql.SQLException;
import java.util.List;

public class ServicioCRUDLibros {

    private GestorBD gestorBD;
    private LibroDAO libroDAO; // Declara la variable LibroDAO

    // Constructor que recibe una instancia de GestorBD
    public ServicioCRUDLibros(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
        // Inicializa LibroDAO
        this.libroDAO = new LibroDAO();
    }

    // Método para agregar un nuevo libro a la base de datos
    public void agregar(Libro libro) throws SQLException {
        try (var statement = gestorBD.prepararConsulta("INSERT INTO Publicacion (titulo, autor, num_paginas, cant_ejemplares, cant_prestados, tipo_publicacion) VALUES (?, ?, ?, ?, ?, ?)")) {
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
        // Reutiliza el método de LibroDAO
        return libroDAO.obtenerTodosLosLibros();
    }

    // Método para actualizar un libro existente en la base de datos
    public void actualizar(Libro libro) throws SQLException {
        try (var statement = gestorBD.prepararConsulta("UPDATE Publicacion SET titulo = ?, autor = ?, num_paginas = ?, cant_ejemplares = ?, cant_prestados = ? WHERE titulo = ? AND tipo_publicacion = 'LIBRO'")) {
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
        try (var statement = gestorBD.prepararConsulta("DELETE FROM Publicacion WHERE titulo = ? AND tipo_publicacion = 'LIBRO'")) {
            statement.setString(1, tituloLibro);
            statement.executeUpdate();
        }
    }
}

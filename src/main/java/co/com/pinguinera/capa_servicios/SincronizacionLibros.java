package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.capa_datos.LibroDAO;
import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizacionLibros {

    private final GestorBD gestorBD;
    private final LibroDAO libroDAO;

    private static final String INSERTAR_LIBRO = "INSERT INTO Publicacion (titulo, autor, num_paginas, cant_ejemplares, cant_prestados, tipo_publicacion) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_LIBRO = "UPDATE Publicacion SET autor = ?, num_paginas = ?, cant_ejemplares = ?, cant_prestados = ? WHERE titulo = ? AND tipo_publicacion = 'LIBRO'";
    private static final String ELIMINAR_LIBRO = "DELETE FROM Publicacion WHERE titulo = ? AND tipo_publicacion = 'LIBRO'";

    public SincronizacionLibros(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
        this.libroDAO = new LibroDAO();
    }

    // Inserta un nuevo libro en la base de datos
    private void insertarLibro(Libro libro) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(INSERTAR_LIBRO)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setInt(3, libro.getNumPaginas());
            statement.setInt(4, libro.getCantEjemplares());
            statement.setInt(5, libro.getCantPrestados());
            statement.setString(6, libro.getTipoPublicacion().name());
            statement.executeUpdate();
        }
    }

    // Actualiza un libro existente en la base de datos
    private void actualizarLibro(Libro libro) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(ACTUALIZAR_LIBRO)) {
            statement.setString(1, libro.getAutor());
            statement.setInt(2, libro.getNumPaginas());
            statement.setInt(3, libro.getCantEjemplares());
            statement.setInt(4, libro.getCantPrestados());
            statement.setString(5, libro.getTitulo());
            statement.executeUpdate();
        }
    }

    // Elimina un libro de la base de datos
    private void eliminarLibro(String titulo) throws SQLException {
        try {
            try (PreparedStatement statement = gestorBD.prepararConsulta(ELIMINAR_LIBRO)) {
                statement.setString(1, titulo);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("No se puede eliminar el libro '" + titulo + "' porque está relacionado con otros registros.");
        }
    }

    // Sincroniza la colección local de libros con la base de datos
    public void sincronizarConBaseDatos(List<Libro> librosLocales) throws SQLException {
        List<Libro> librosBD = libroDAO.obtenerTodosLosLibros();
        Map<String, Libro> mapaLibrosLocales = new HashMap<>();

        // Crear un mapa de libros locales por título
        for (Libro libroLocal : librosLocales) {
            mapaLibrosLocales.put(libroLocal.getTitulo(), libroLocal);
        }

        // Sincronizar libros existentes en la base de datos con la colección local
        for (Libro libroBD : librosBD) {
            String titulo = libroBD.getTitulo();
            Libro libroLocal = mapaLibrosLocales.get(titulo);

            if (libroLocal != null) {
                // Actualizar libro existente
                actualizarLibro(libroLocal);
                mapaLibrosLocales.remove(titulo);
            } else {
                // Eliminar libro no presente en la colección local
                eliminarLibro(titulo);
            }
        }

        // Insertar libros restantes de la colección local a la base de datos
        for (Libro libro : mapaLibrosLocales.values()) {
            insertarLibro(libro);
        }
    }

}

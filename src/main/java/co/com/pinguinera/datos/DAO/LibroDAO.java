package co.com.pinguinera.datos.DAO;

import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.model.publicaciones.Libro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibroDAO extends AbstractDAO<Libro> {

    private static final String CONSULTA_LIBROS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'LIBRO'";
    private static final String INSERTAR_LIBRO = "INSERT INTO Publicacion (idPublicacion, Titulo, tipo_publicacion, Autor, Num_paginas, Cant_ejemplares, Cant_prestados) VALUES (?, ?, 'LIBRO', ?, ?, ?, ?)";
    private static final String ACTUALIZAR_LIBRO = "UPDATE Publicacion SET Titulo = ?, Autor = ?, Num_paginas = ?, Cant_ejemplares = ?, Cant_prestados = ? WHERE idPublicacion = ?";
    private static final String ELIMINAR_LIBRO = "DELETE FROM Publicacion WHERE idPublicacion = ?";


    public LibroDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        return CONSULTA_LIBROS;
    }

    @Override
    protected Libro convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        Libro libro = new Libro();
        libro.setIdPublicacion(resultSet.getInt("idPublicacion"));
        libro.setTitulo(resultSet.getString("Titulo"));
        libro.setAutor(resultSet.getString("Autor"));
        libro.setNumPaginas(resultSet.getInt("Num_paginas"));
        libro.setCantEjemplares(resultSet.getInt("Cant_ejemplares"));
        libro.setCantPrestados(resultSet.getInt("Cant_prestados"));

        libro.setCantDisponible(resultSet.getInt("Cant_disponible"));

        return libro;
    }


    @Override
    public void insertar(Libro libro) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_LIBRO)) {
            statement.setInt(1, libro.getIdPublicacion());
            statement.setString(2, libro.getTitulo());
            statement.setString(3, libro.getAutor());
            statement.setInt(4, libro.getNumPaginas());
            statement.setInt(5, libro.getCantEjemplares());
            statement.setInt(6, libro.getCantPrestados());
            statement.executeUpdate();
        }
    }

    @Override
    public void actualizar(Libro libro) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_LIBRO)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setInt(3, libro.getNumPaginas());
            statement.setInt(4, libro.getCantEjemplares());
            statement.setInt(5, libro.getCantPrestados());
            statement.setInt(6, libro.getIdPublicacion());
            statement.executeUpdate();
        }
    }


    @Override
    public void eliminar(Libro libro) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_LIBRO)) {
            statement.setInt(1, libro.getIdPublicacion());
            statement.executeUpdate();
        }
    }


}

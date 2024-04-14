package co.com.pinguinera.capa_servicios.CRUBBD;

import co.com.pinguinera.capa_datos.LibroDAO;
import co.com.pinguinera.capa_servicios.CRUBBD.AbstractBaseDatosCRUD;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.sql.SQLException;
import java.util.List;

public class ConsultasLibros extends AbstractBaseDatosCRUD<Libro> {
    private final LibroDAO libroDAO;

    public ConsultasLibros(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    @Override
    public void insertar(Libro libro) throws SQLException {
        // Implementa la lógica para insertar un libro usando libroDAO
        libroDAO.insertar(libro);
    }

    @Override
    public List<Libro> obtenerTodos() throws SQLException {
        // Implementa la lógica para obtener todos los libros de la base de datos
        return libroDAO.obtenerTodos();
    }

    @Override
    public void actualizar(Libro libro) throws SQLException {
        // Implementa la lógica para actualizar un libro usando libroDAO
        libroDAO.actualizar(libro);
    }

    @Override
    public void eliminar(int id) throws SQLException {
        // Implementa la lógica para eliminar un libro por ID usando libroDAO
        libroDAO.eliminar(id);
    }
}

package co.com.pinguinera.capa_servicios.sincronizacionBD;

import co.com.pinguinera.capa_datos.LibroDAO;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class SincronizadorLibros {
    private final LibroDAO libroDAO;
    private final List<Libro> librosLocales;

    public SincronizadorLibros(LibroDAO libroDAO, List<Libro> librosLocales) {
        this.libroDAO = libroDAO;
        this.librosLocales = librosLocales;
    }

    public void sincronizar() throws SQLException {
        // Obtiene todos los libros de la base de datos
        List<Libro> librosBD = libroDAO.obtenerTodos();

        // Sincroniza la lista local con la base de datos
        sincronizarLibrosLocalesConBaseDeDatos(librosBD);
        // Elimina libros de la base de datos que no están en la lista local
        eliminarLibrosNoLocalesDeBaseDeDatos(librosBD);
    }

    private void sincronizarLibrosLocalesConBaseDeDatos(List<Libro> librosBD) throws SQLException {
        // Recorre la lista local de libros
        for (Libro libroLocal : librosLocales) {
            // Busca si el libro local existe en la base de datos
            Libro libroBD = buscarLibroEnBaseDeDatos(libroLocal, librosBD);

            if (libroBD != null) {
                // Si existe pero tiene diferencias, actualízalo
                if (!Objects.equals(libroLocal, libroBD)) {
                    libroDAO.actualizar(libroLocal);
                }
            } else {
                // Si no existe en la base de datos, insértalo
                libroDAO.insertar(libroLocal);
            }
        }
    }

    private Libro buscarLibroEnBaseDeDatos(Libro libroLocal, List<Libro> librosBD) {
        for (Libro libroBD : librosBD) {
            // Compara los IDs de los libros usando el método `getIdPublicacion()`
            if (libroLocal.getIdPublicacion() == libroBD.getIdPublicacion()) {
                return libroBD;
            }
        }
        return null;
    }

    private void eliminarLibrosNoLocalesDeBaseDeDatos(List<Libro> librosBD) throws SQLException {
        // Recorre la lista de libros en la base de datos
        for (Libro libroBD : librosBD) {
            // Busca si el libro en la base de datos existe en la lista local
            boolean encontradoEnLocal = librosLocales.stream()
                    .anyMatch(libroLocal -> libroLocal.getIdPublicacion() == libroBD.getIdPublicacion());

            // Si no se encontró en la lista local, elimínalo de la base de datos
            if (!encontradoEnLocal) {
                libroDAO.eliminar(libroBD.getIdPublicacion());
            }
        }
    }
}

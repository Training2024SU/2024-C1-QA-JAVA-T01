package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.LibroDAO;
import co.com.pinguinera.datos.crud_local.CRUDLibrosLocales;
import co.com.pinguinera.datos.model.publicaciones.Libro;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorLibros {

    private final LibroDAO libroDAO;
    private final CRUDLibrosLocales crudLibrosLocales;

    public SincronizadorLibros(LibroDAO libroDAO, CRUDLibrosLocales crudLibrosLocales) {
        this.libroDAO = libroDAO;
        this.crudLibrosLocales = crudLibrosLocales;
    }

    public void sincronizarLibros() throws SQLException {
        // Obtén la lista de libros de la base de datos
        List<Libro> librosBD = libroDAO.obtenerTodos();

        // Obtén la lista de libros locales
        List<Libro> librosLocales = crudLibrosLocales.obtenerTodos();

        // Crea un mapa de libros locales para búsquedas rápidas
        Map<Integer, Libro> mapaLibrosLocales = new HashMap<>();
        for (Libro libroLocal : librosLocales) {
            mapaLibrosLocales.put(libroLocal.getIdPublicacion(), libroLocal);
        }

        // Actualiza libros en la base de datos basados en la lista local
        for (Libro libroBD : librosBD) {
            Libro libroLocal = mapaLibrosLocales.get(libroBD.getIdPublicacion());
            if (libroLocal != null) {
                // Si el libro local está en la base de datos, actualízalo si hay diferencias
                if (!libroLocal.equals(libroBD)) {
                    libroDAO.actualizar(libroLocal);
                }
                // Elimina el libro del mapa para que al final sepamos cuáles no estaban en la base de datos
                mapaLibrosLocales.remove(libroLocal.getIdPublicacion());
            }
        }

        // Inserta libros locales que no están en la base de datos
        for (Libro libroLocal : mapaLibrosLocales.values()) {
            libroDAO.insertar(libroLocal);
        }
    }
}

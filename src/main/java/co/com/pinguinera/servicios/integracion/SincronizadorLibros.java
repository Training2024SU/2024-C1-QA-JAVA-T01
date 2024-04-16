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
        List<Libro> librosBD = libroDAO.obtenerTodos();
        List<Libro> librosLocales = crudLibrosLocales.obtenerTodos();

        Map<Integer, Libro> mapaLibrosLocales = new HashMap<>();
        for (Libro libroLocal : librosLocales) {
            mapaLibrosLocales.put(libroLocal.getIdPublicacion(), libroLocal);
        }

        for (Libro libroBD : librosBD) {
            Libro libroLocal = mapaLibrosLocales.get(libroBD.getIdPublicacion());
            if (libroLocal != null) {
                if (!libroLocal.equals(libroBD)) {
                    libroDAO.actualizar(libroLocal);
                }
                mapaLibrosLocales.remove(libroLocal.getIdPublicacion());
            }
        }

        // Inserta libros locales que no están en la base de datos
        for (Libro libroLocal : mapaLibrosLocales.values()) {
            libroDAO.insertar(libroLocal);
        }
    }
}

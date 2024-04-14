package co.com.pinguinera.capa_servicios.sincronizacionBD;

import co.com.pinguinera.capa_servicios.CRUBBD.ConsultasLibros;
import co.com.pinguinera.capa_servicios.CRUD.ServicioLocalCRUDLibros;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorLibros {

    private final ConsultasLibros consultasLibros;
    private final ServicioLocalCRUDLibros servicioLocalCRUDLibros;

    public SincronizadorLibros(ConsultasLibros consultasLibros, ServicioLocalCRUDLibros servicioLocalCRUDLibros) {
        this.consultasLibros = consultasLibros;
        this.servicioLocalCRUDLibros = servicioLocalCRUDLibros;
    }

    public void sincronizarLibros() throws SQLException {
        // Obtén la lista de libros de la base de datos
        List<Libro> librosBD = consultasLibros.obtenerTodos();

        // Obtén la lista de libros local
        List<Libro> librosLocales = servicioLocalCRUDLibros.obtenerTodos();

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
                    consultasLibros.actualizar(libroLocal);
                }
                // Elimina el libro del mapa para que al final sepamos cuáles no estaban en la base de datos
                mapaLibrosLocales.remove(libroLocal.getIdPublicacion());
            }
        }

        // Inserta libros locales que no están en la base de datos
        for (Libro libroLocal : mapaLibrosLocales.values()) {
            consultasLibros.insertar(libroLocal);
        }
    }
}

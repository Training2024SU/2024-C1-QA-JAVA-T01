package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.capa_datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.capa_datos.LibroDAO;
import co.com.pinguinera.capa_servicios.sincronizacionBD.SincronizadorLibros;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Crea una instancia de `BaseDatosImpl` como `GestorBD`
            GestorBD gestorBD = new BaseDatosImpl(conexion);

            // Crea una instancia de `LibroDAO` con `GestorBD`
            LibroDAO libroDAO = new LibroDAO(gestorBD);

            // Crea una lista local de libros con datos realistas
            List<Libro> librosLocales = new ArrayList<>();

            // Añade libros de prueba a la lista local siguiendo la estructura especificada
            librosLocales.add(new Libro(1, "BlancaNieves", "Walt Disney", 200, 10, 3, 7, null, null));
            librosLocales.add(new Libro(2, "Libro 2", "Autor 2", 150, 8, 2, 6, null, null));
            librosLocales.add(new Libro(3, "El Principito", "Antoine de Saint-Exupéry", 96, 20, 5, 15, null, null));
            librosLocales.add(new Libro(4, "Cien años de soledad", "Gabriel García Márquez", 417, 12, 4, 8, null, null));
            librosLocales.add(new Libro(5, "Don Quijote de la Mancha", "Miguel de Cervantes", 863, 5, 2, 3, null, null));

            // Crea una instancia de `SincronizadorLibros`
            SincronizadorLibros sincronizadorLibros = new SincronizadorLibros(libroDAO, librosLocales);

            // Ejecuta la sincronización
            sincronizadorLibros.sincronizar();

            System.out.println("Sincronización completada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

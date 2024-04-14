package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.capa_datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.capa_servicios.ServicioCRUDLibros;
import co.com.pinguinera.modelado.publicaciones.Libro;
import co.com.pinguinera.modelado.AreaGenero;
import co.com.pinguinera.modelado.EdadSugerida;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Crea una instancia de BaseDatosImpl
            BaseDatosImpl baseDatosImpl = new BaseDatosImpl(conexion);

            // Crea una instancia de ServicioCRUDLibros
            ServicioCRUDLibros servicioLibros = new ServicioCRUDLibros();

            // Crea una instancia de SincronizacionLibros
            SincronizacionLibros sincronizacionLibros = new SincronizacionLibros(baseDatosImpl);

            List<AreaGenero> areas = List.of();
            List<EdadSugerida> edades = List.of();

            // Añadir nuevos libros a la colección local
            servicioLibros.agregar(new Libro("The Hobbit", "J.R.R. Tolkien", 310, 25, 12, 13, areas, edades));
            servicioLibros.agregar(new Libro("Moby Dick", "Herman Melville", 635, 10, 4, 6, areas, edades));
            servicioLibros.agregar(new Libro("The Lord of the Rings", "J.R.R. Tolkien", 1216, 15, 7, 8, areas, edades));
            servicioLibros.agregar(new Libro("Brave New World", "Aldous Huxley", 268, 20, 10, 10, areas, edades));
            servicioLibros.agregar(new Libro("The Old Man and the Sea", "Ernest Hemingway", 127, 12, 5, 7, areas, edades));
            servicioLibros.agregar(new Libro("Wuthering Heights", "Emily Bronte", 400, 18, 8, 10, areas, edades));
            servicioLibros.agregar(new Libro("Great Expectations", "Charles Dickens", 544, 16, 6, 10, areas, edades));

            // Muestra todos los libros de la colección local
            List<Libro> libros = servicioLibros.obtenerTodos();
            System.out.println("Lista de libros en la colección local:");
            for (Libro libro : libros) {
                System.out.println(libro);
            }

            // Sincroniza los cambios en la colección local con la base de datos
            sincronizacionLibros.sincronizarConBaseDatos(libros);
            System.out.println("Colección local sincronizada con la base de datos.");

        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

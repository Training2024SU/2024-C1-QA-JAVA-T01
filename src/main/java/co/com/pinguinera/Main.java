package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.capa_datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.capa_servicios.ServicioCRUDLibros;
import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import co.com.pinguinera.modelado.AreaGenero;
import co.com.pinguinera.modelado.EdadSugerida;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicia la conexión a la base de datos
            Connection conexion = ConexionBD.conectar();

            // Inicializa la implementación de GestorBD pasando la conexión
            GestorBD gestorBD = new BaseDatosImpl(conexion);

            // Crea una instancia de ServicioCRUDLibros y pasa el gestorBD
            ServicioCRUDLibros servicioCRUDLibros = new ServicioCRUDLibros(gestorBD);

            // Crea una instancia de Libro para agregar
            String titulo = "Cien años de soledad";
            String autor = "Gabriel García Márquez";
            int numPaginas = 417;
            int cantEjemplares = 10;
            int cantPrestados = 0;
            int cantDisponible = cantEjemplares - cantPrestados;

            // Listas para áreas y edades sugeridas
            List<AreaGenero> areas = new ArrayList<>();
            List<EdadSugerida> edades = new ArrayList<>();

            // Crea un libro
            Libro libro = new Libro(titulo, autor, numPaginas, cantEjemplares, cantPrestados, cantDisponible, areas, edades);

            // Agrega el libro a la base de datos usando el servicio
            System.out.println("Agregando libro: " + libro);
            servicioCRUDLibros.agregar(libro);

            // Obtén todos los libros de la base de datos
            System.out.println("\nLista de libros en la base de datos:");
            List<Libro> libros = servicioCRUDLibros.obtenerTodos();
            for (Libro l : libros) {
                System.out.println(l);
            }

            // Actualiza el primer libro de la lista
            if (!libros.isEmpty()) {
                Libro libroParaActualizar = libros.get(0);
                libroParaActualizar.setTitulo("Don Quijote de la Mancha");
                libroParaActualizar.setAutor("Miguel de Cervantes Saavedra");
                servicioCRUDLibros.actualizar(libroParaActualizar);
                System.out.println("\nLibro actualizado: " + libroParaActualizar);
            }

            // Elimina el primer libro de la lista
            if (!libros.isEmpty()) {
                String tituloLibroAEliminar = libros.get(0).getTitulo();
                servicioCRUDLibros.eliminar(tituloLibroAEliminar);
                System.out.println("\nLibro eliminado con título: " + tituloLibroAEliminar);
            }

            // Vuelve a obtener todos los libros para ver los cambios
            System.out.println("\nLista actualizada de libros en la base de datos:");
            libros = servicioCRUDLibros.obtenerTodos();
            for (Libro l : libros) {
                System.out.println(l);
            }

            // Cierra la conexión a la base de datos
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

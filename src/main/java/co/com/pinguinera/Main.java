package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.DataBase;
import co.com.pinguinera.capa_servicios.ServicioCRUDLibros;
import co.com.pinguinera.modelado.AreaGenero;
import co.com.pinguinera.modelado.EdadSugerida;
import co.com.pinguinera.modelado.tipoPublicaciones.Libro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicia la conexión a la base de datos
        try (Connection conexion = DataBase.conectar()) {

            // Crea una instancia de ServicioCRUDLibros y pasa la conexión
            ServicioCRUDLibros servicioCRUDLibros = new ServicioCRUDLibros(conexion);

            // Crea una instancia de Libro para agregar
            String titulo = "El Quijote";
            String autor = "Miguel de Cervantes";
            int numPaginas = 1000;
            int cantEjemplares = 5;
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
                int idLibroAEliminar = libros.get(0).getId(); // Asegúrate de tener un método getId() en Libro
                servicioCRUDLibros.eliminar(idLibroAEliminar);
                System.out.println("\nLibro eliminado con ID: " + idLibroAEliminar);
            }

            // Vuelve a obtener todos los libros para ver los cambios
            System.out.println("\nLista actualizada de libros en la base de datos:");
            libros = servicioCRUDLibros.obtenerTodos();
            for (Libro l : libros) {
                System.out.println(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

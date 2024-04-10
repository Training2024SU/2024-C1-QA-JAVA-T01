package co.com.pinguinera;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import co.com.pinguinera.modelos.Libro;
import co.com.pinguinera.modelos.DAO.LibroDAO;
import co.com.pinguinera.modelos.repositorios.LibroRepositorio;

public class Main {
    public static void main(String[] args) {
        try {
            // Intenta establecer la conexión a la base de datos
            Connection conexion = DataBase.conectar();

            // Si la conexión se establece con éxito, muestra un mensaje
            System.out.println("Conexión establecida correctamente.");

            // Crea un objeto LibroDAO y pasa la conexión a la base de datos
            LibroRepositorio libroDAO = new LibroDAO(conexion);

            // Agregar un nuevo libro
            Libro nuevoLibro = new Libro();
            nuevoLibro.setTitulo("El Principito");
            nuevoLibro.setAutor("Antoine de Saint-Exupéry");
            nuevoLibro.setAreaConocimiento("Literatura infantil");
            nuevoLibro.setNumPaginas(96);
            nuevoLibro.setCantEjemplares(10);
            libroDAO.agregarLibro(nuevoLibro);

            // Obtener todos los libros y mostrarlos en la consola
            List<Libro> libros = libroDAO.obtenerTodosLosLibros();
            System.out.println("Libros en la biblioteca:");
            for (Libro libro : libros) {
                System.out.println(libro.getTitulo() + " - " + libro.getAutor());
            }

            // Buscar un libro por título
            String titulo = "El Principito";
            Libro libroEncontrado = libroDAO.buscarLibroPorTitulo(titulo);
            if (libroEncontrado != null) {
                System.out.println("Libro encontrado: " + libroEncontrado.getTitulo() + " - " + libroEncontrado.getAutor());
            } else {
                System.out.println("No se encontró ningún libro con el título: " + titulo);
            }

            // Cierra la conexión
            conexion.close();
        } catch (SQLException e) {
            // Si ocurre algún error al intentar establecer la conexión, muestra el mensaje de error
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            // También puedes imprimir la traza completa del error llamando a e.printStackTrace()
            // e.printStackTrace();
        }
    }
}

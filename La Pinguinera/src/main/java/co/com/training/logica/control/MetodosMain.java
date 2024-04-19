package co.com.training.control;

import co.com.training.logica.CrudLibro;
import co.com.training.logica.CrudNovela;
import co.com.training.modelo.Libro;

import java.sql.SQLException;
import java.util.Scanner;

public class MetodosMain {

    private final CrudLibro crudLibro;
    private final CrudNovela crudNovela;

    public MetodosMain(CrudLibro crudLibro, CrudNovela crudNovela) {
        this.crudLibro = crudLibro;
        this.crudNovela = crudNovela;
    }

    public void insertarLibros(int cantidad) {
        Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese los datos del libro:");
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            System.out.print("Área de conocimiento: ");
            String areaConocimiento = scanner.nextLine();
            System.out.print("Número de páginas: ");
            int numeroPaginas = Integer.parseInt(scanner.nextLine());
            System.out.print("Cantidad de ejemplares: ");
            int cantidadEjemplares = Integer.parseInt(scanner.nextLine());
            System.out.print("Cantidad prestados: ");
            int cantidadPrestados = Integer.parseInt(scanner.nextLine());

            // Crear un objeto Libro con los datos ingresados por el usuario
            Libro libro = new Libro(titulo, autor, areaConocimiento, numeroPaginas, cantidadEjemplares, cantidadPrestados);

            // Insertar el libro en la base de datos usando el método insertarLibro de CrudLibro
            crudLibro.insertarLibro(libro);
    }

    public void actualizarLibro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el título del libro a actualizar:");
        String titulo = scanner.nextLine();

        try {
            // Consultar el libro con el título proporcionado
            Libro libro = crudLibro.consultarLibroPorTitulo(titulo);

            if (libro != null) {
                // Mostrar los detalles del libro y solicitar los nuevos datos al usuario
                System.out.println("Detalles del libro:");
                System.out.println(libro);
                System.out.println("Ingrese los nuevos datos del libro:");
                System.out.print("Autor: ");
                String autor = scanner.nextLine();
                System.out.print("Área de conocimiento: ");
                String areaConocimiento = scanner.nextLine();
                System.out.print("Número de páginas: ");
                int numeroPaginas = Integer.parseInt(scanner.nextLine());
                System.out.print("Cantidad de ejemplares: ");
                int cantidadEjemplares = Integer.parseInt(scanner.nextLine());
                System.out.print("Cantidad prestados: ");
                int cantidadPrestados = Integer.parseInt(scanner.nextLine());

                // Actualizar el libro en la base de datos
                libro.setAutor(autor);
                libro.setAreaConocimiento(areaConocimiento);
                libro.setNumeroPaginas(numeroPaginas);
                libro.setCantidadEjemplares(cantidadEjemplares);
                libro.setCantidadPrestados(cantidadPrestados);

                crudLibro.actualizarLibro(libro);
                System.out.println("Libro actualizado correctamente.");
            } else {
                System.out.println("El libro no existe en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el libro: " + e.getMessage());
        }
    }

    public void consultarLibroPorTitulo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el título del libro a consultar:");
        String titulo = scanner.nextLine();

        try {
            // Consultar el libro por título
            Libro libro = crudLibro.consultarLibroPorTitulo(titulo);
            if (libro != null) {
                System.out.println("Detalles del libro:");
                System.out.println(libro);
            } else {
                System.out.println("El libro no existe en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el libro: " + e.getMessage());
        }
    }

    public void eliminarLibroPorTitulo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el título del libro a eliminar:");
        String titulo = scanner.nextLine();

        try {
            // Eliminar el libro por título
            boolean eliminado = crudLibro.eliminarLibroPorTitulo(titulo);
            if (eliminado) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("El libro no existe en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        }
    }
}





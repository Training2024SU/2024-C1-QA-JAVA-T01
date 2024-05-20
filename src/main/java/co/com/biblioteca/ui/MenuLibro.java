package co.com.biblioteca.ui;

import co.com.biblioteca.DAO.LibroDAO;
import co.com.biblioteca.modelo.Libro;
import java.util.List;
import java.util.Scanner;


public class MenuLibro {
    private static final LibroDAO libroDAO = new LibroDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenuLibro();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    listarLibrosDisponibles();
                    break;
                case 2:
                    agregarNuevoLibro();
                    break;
                case 3:
                    actualizarInformacionLibro();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 5:
                    buscarLibros();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private static void mostrarMenuLibro() {
        System.out.println("\nMenú de Libros");
        System.out.println("1. Listar libros disponibles");
        System.out.println("2. Agregar nuevo libro");
        System.out.println("3. Actualizar información de un libro");
        System.out.println("4. Eliminar libro");
        System.out.println("5. Buscar libros por título o autor");
        System.out.println("6. Volver al menú principal");
        System.out.print("Ingrese su opción: ");
    }

    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void listarLibrosDisponibles() {
        System.out.println("\nListado de libros disponibles:");
        libroDAO.obtenerTodosLosLibros().forEach(System.out::println);
    }

    private static void agregarNuevoLibro() {
        System.out.println("\nAgregar nuevo libro");

        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        titulo = scanner.nextLine();

        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el área del conocimiento del libro: ");
        String areaConocimiento = scanner.nextLine();

        System.out.print("Ingrese el número de páginas del libro: ");
        int numeroPaginas = obtenerEntero();

        System.out.print("Ingrese la cantidad de ejemplares del libro: ");
        int cantidadEjemplares = obtenerEntero();

        // Inicialmente, la cantidad prestados y disponible serán iguales a la cantidad de ejemplares
        int cantidadPrestados = cantidadEjemplares;
        int cantidadDisponible = cantidadEjemplares;

        Libro nuevoLibro = new Libro(0, titulo, autor, areaConocimiento, numeroPaginas, cantidadEjemplares, cantidadPrestados, cantidadDisponible);
        libroDAO.agregarLibro(nuevoLibro);
        System.out.println("¡Libro agregado exitosamente!");
    }

    private static void actualizarInformacionLibro() {
        System.out.println("\nActualizar información de un libro");

        System.out.print("Ingrese el ID del libro a actualizar: ");
        int idLibro = obtenerEntero();

        // Verificar si el libro existe
        Libro libroExistente = libroDAO.obtenerLibroPorId(idLibro);
        if (libroExistente == null) {
            System.out.println("No se encontró ningún libro con ese ID.");
            return;
        }

        System.out.print("Ingrese el nuevo título del libro (deje en blanco si no desea cambiar): ");
        String nuevoTitulo = scanner.nextLine(); // Consumir el salto de línea pendiente
        nuevoTitulo = scanner.nextLine();

        System.out.print("Ingrese el nuevo autor del libro (deje en blanco si no desea cambiar): ");
        String nuevoAutor = scanner.nextLine();

        System.out.print("Ingrese el nuevo área del conocimiento del libro (deje en blanco si no desea cambiar): ");
        String nuevaAreaConocimiento = scanner.nextLine();

        System.out.print("Ingrese el nuevo número de páginas del libro (deje 0 si no desea cambiar): ");
        int nuevoNumeroPaginas = obtenerEntero();

        System.out.print("Ingrese la nueva cantidad de ejemplares del libro (deje 0 si no desea cambiar): ");
        int nuevaCantidadEjemplares = obtenerEntero();

        // Actualizar el libro solo con los campos no vacíos
        if (!nuevoTitulo.isEmpty()) {
            libroExistente.setTitulo(nuevoTitulo);
        }
        if (!nuevoAutor.isEmpty()) {
            libroExistente.setAutor(nuevoAutor);
        }
        if (!nuevaAreaConocimiento.isEmpty()) {
            libroExistente.setAreaConocimiento(nuevaAreaConocimiento);
        }
        if (nuevoNumeroPaginas > 0) {
            libroExistente.setNumeroPaginas(nuevoNumeroPaginas);
        }
        if (nuevaCantidadEjemplares > 0) {
            libroExistente.setCantidadEjemplares(nuevaCantidadEjemplares);
        }

        libroDAO.actualizarLibro(libroExistente);
        System.out.println("¡Información del libro actualizada exitosamente!");
    }

    private static void eliminarLibro() {
        System.out.println("\nEliminar libro");

        System.out.print("Ingrese el ID del libro a eliminar: ");
        int idLibro = obtenerEntero();

        // Verificar si el libro existe
        Libro libroExistente = libroDAO.obtenerLibroPorId(idLibro);
        if (libroExistente == null) {
            System.out.println("No se encontró ningún libro con ese ID.");
            return;
        }

        libroDAO.eliminarLibroPorId(idLibro);
        System.out.println("¡Libro eliminado exitosamente!");
    }

    private static void buscarLibros() {
        System.out.println("\nBuscar libros por título o autor");

        System.out.print("Ingrese el título o autor a buscar: ");
        String criterioBusqueda = scanner.nextLine(); // Consumir el salto de línea pendiente
        criterioBusqueda = scanner.nextLine();

        LibroDAO libroDAO = new LibroDAO(); // Crear una instancia de LibroDAO
        List<Libro> librosEncontrados = libroDAO.buscarLibros(criterioBusqueda); // Llamar al método de instancia
        if (librosEncontrados.isEmpty()) {
            System.out.println("No se encontraron libros que coincidan con el criterio de búsqueda.");
        } else {
            System.out.println("Libros encontrados:");
            for (Libro libro : librosEncontrados) {
                System.out.println(libro);
            }
        }
    }


    private static int obtenerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }
}

package co.com.biblioteca.ui;

import co.com.biblioteca.DAO.GrabacionDAO;
import co.com.biblioteca.modelo.Grabacion;
import java.util.List;
import java.util.Scanner;

public class MenuGrabacion {

        private static final GrabacionDAO grabacionDAO = new GrabacionDAO();
        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            boolean salir = false;

            while (!salir) {
                mostrarMenuGrabacion();
                int opcion = obtenerOpcion();

                switch (opcion) {
                    case 1:
                        listarGrabacionesDisponibles();
                        break;
                    case 2:
                        agregarNuevaGrabacion();
                        break;
                    case 3:
                        actualizarInformacionGrabacion();
                        break;
                    case 4:
                        eliminarGrabacion();
                        break;
                    case 5:
                        buscarGrabacion();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            }
        }

        private static void mostrarMenuGrabacion() {
            System.out.println("\nMenú de Grabacion");
            System.out.println("1. Listar Grabaciones disponibles");
            System.out.println("2. Agregar nueva Grabacion");
            System.out.println("3. Actualizar información de una Grabacion");
            System.out.println("4. Eliminar Grabacion");
            System.out.println("5. Buscar Grabacion por título o autor");
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

        private static void listarGrabacionesDisponibles() {
            System.out.println("\nListado de grabaciones disponibles:");
            grabacionDAO.obtenerTodasLasGrabaciones().forEach(System.out::println);
        }

        private static void agregarNuevaGrabacion() {
            System.out.println("\nAgregar nuevo Grabacion");

            System.out.print("Ingrese el título de la Grabacion: ");
            String titulo = scanner.nextLine(); // Consumir el salto de línea pendiente
          //  titulo = scanner.nextLine();

            System.out.print("Ingrese el autor de la Grabacion: ");
            String autor = scanner.nextLine();

            System.out.print("Ingrese el genero de la Grabacion: ");
            String genero = scanner.nextLine();

            System.out.print("Ingrese la duracion de la Grabacion: ");
            int duracion = obtenerEntero();

            System.out.print("Ingrese la cantidad de ejemplares de la grabacion:");
            int cantidadEjemplares = obtenerEntero();

            // Inicialmente, la cantidad prestados y disponible serán iguales a la cantidad de ejemplares
            int cantidadPrestados = cantidadEjemplares;
            int cantidadDisponible = cantidadEjemplares;


            Grabacion nuevaGrabacion = new Grabacion(titulo, autor, genero, duracion, cantidadEjemplares, cantidadPrestados, cantidadDisponible);
            grabacionDAO.agregarGrabacion(nuevaGrabacion);
            System.out.println("¡Grabacion agregada exitosamente!");
        }

        private static void actualizarInformacionGrabacion() {
            System.out.println("\nActualizar información de una Grabacion");

            System.out.print("Ingrese el ID de la Grabacion a actualizar: ");
            int idGrabacion = obtenerEntero();

            // Verificar si el grabacion existe
            Grabacion grabacionExistente = grabacionDAO.obtenerGrabacionPorId(idGrabacion);
            if (grabacionExistente == null) {
                System.out.println("No se encontró ningúna grabacion con ese ID.");
                return;
            }

            System.out.print("Ingrese el nuevo título de la grabacion (deje en blanco si no desea cambiar): ");
            String nuevoTitulo = scanner.nextLine(); // Consumir el salto de línea pendiente

            System.out.print("Ingrese el nuevo autor de la grabacion (deje en blanco si no desea cambiar): ");
            String nuevoAutor = scanner.nextLine();

            System.out.print("Ingrese el nuevo genero de la grabacion (deje en blanco si no desea cambiar): ");
            String nuevaAreaConocimiento = scanner.nextLine();

            System.out.print("Ingrese la nueva duracion de la grabacion (deje 0 si no desea cambiar): ");
            int nuevoNumeroPaginas = obtenerEntero();

            System.out.print("Ingrese la nueva cantidad de ejemplares de la grabacion (deje 0 si no desea cambiar): ");
            int nuevaCantidadEjemplares = obtenerEntero();

            // Actualizar el grabacion solo con los campos no vacíos
            if (!nuevoTitulo.isEmpty()) {
                grabacionExistente.setTitulo(nuevoTitulo);
            }
            if (!nuevoAutor.isEmpty()) {
                grabacionExistente.setAutor(nuevoAutor);
            }
            if (!nuevaAreaConocimiento.isEmpty()) {
                grabacionExistente.setGenero(nuevaAreaConocimiento);
            }
            if (nuevoNumeroPaginas > 0) {
                grabacionExistente.setDuracion(nuevoNumeroPaginas);
            }
            if (nuevaCantidadEjemplares > 0) {
                grabacionExistente.setCantidadEjemplares(nuevaCantidadEjemplares);
            }

            grabacionDAO.actualizarGrabacion(grabacionExistente);
            System.out.println("¡Información de la grabacion actualizada exitosamente!");
        }

        private static void eliminarGrabacion() {
            System.out.println("\nEliminar grabacion");

            System.out.print("Ingrese el ID de la grabacion a eliminar: ");
            int idGrabacion = obtenerEntero();

            // Verificar si el grabacion existe
            Grabacion grabacionExistente = grabacionDAO.obtenerGrabacionPorId(idGrabacion);
            if (grabacionExistente == null) {
                System.out.println("No se encontró ningúna grabacion con ese ID.");
                return;
            }

            grabacionDAO.eliminarGrabacionPorId(idGrabacion);
            System.out.println("¡Grabacion eliminada exitosamente!");
        }

    static void buscarGrabacion() {
        System.out.println("\nBuscar grabacion por título o autor");

        System.out.print("Ingrese el título o autor a buscar: ");
        String criterioBusqueda = scanner.nextLine();

        GrabacionDAO grabacionDAO = new GrabacionDAO(); // Crear una instancia de GrabacionDAO
        List<Grabacion> grabacionEncontrados = grabacionDAO.buscarGrabacion(criterioBusqueda); // Llamar al método de instancia
        if (grabacionEncontrados.isEmpty()) {
            System.out.println("No se encontraron grabaciones que coincidan con el criterio de búsqueda.");
        } else {
            System.out.println("Libros encontrados:");
            for (Grabacion grabacion : grabacionEncontrados) {
                System.out.println(grabacion);
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


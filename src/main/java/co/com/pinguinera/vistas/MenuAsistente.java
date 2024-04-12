package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.MenuAsistenteControlador;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;
import co.com.pinguinera.modelos.Libro;
import co.com.pinguinera.modelos.Novela;
import co.com.pinguinera.modelos.Prestamo;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAsistente {

    private static final int AGREGAR_LIBRO = 1;
    private static final int ACTUALIZAR_LIBRO = 2;
    private static final int ELIMINAR_LIBRO = 3;
    private static final int AGREGAR_NOVELA = 4;
    private static final int ACTUALIZAR_NOVELA = 5;
    private static final int ELIMINAR_NOVELA = 6;
    private static final int LISTAR_PRESTAMOS_POR_ESTADO = 7;
    private static final int LISTAR_PRESTAMOS_POR_FECHA = 8;
    private static final int SALIR = 9;

    private final Scanner scanner;
    private final MenuAsistenteControlador menuAsistenteControlador;

    // Constructor
    public MenuAsistente(Scanner scanner, LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio,
                         PrestamoRepositorio prestamoRepositorio) {
        this.scanner = scanner;
        this.menuAsistenteControlador = new MenuAsistenteControlador(libroRepositorio, novelaRepositorio, prestamoRepositorio);
    }

    // Método para mostrar el menú del asistente
    public void mostrarMenuAsistente() {
        while (true) {
            System.out.println("\nMenú Asistente:");
            System.out.println("1. Agregar libro");
            System.out.println("2. Actualizar libro");
            System.out.println("3. Eliminar libro");
            System.out.println("4. Agregar novela");
            System.out.println("5. Actualizar novela");
            System.out.println("6. Eliminar novela");
            System.out.println("7. Listar préstamos por estado");
            System.out.println("8. Listar préstamos por fecha");
            System.out.println("9. Salir");
            System.out.println("Elige una opción:");

            int opcion;

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case AGREGAR_LIBRO:
                        System.out.println("Ingrese el ID del libro:");
                        int libroId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada

                        System.out.println("Ingrese el título del libro:");
                        String tituloLibro = scanner.nextLine();

                        System.out.println("Ingrese el autor del libro:");
                        String autorLibro = scanner.nextLine();

                        // Crear un objeto Libro con los datos recopilados
                        Libro libro = new Libro();
                        libro.setLibroID(libroId);
                        libro.setTitulo(tituloLibro);
                        libro.setAutor(autorLibro);

                        // Llamar a agregarLibro
                        menuAsistenteControlador.agregarLibro(libro);
                        break;

                    case ACTUALIZAR_LIBRO:
                        System.out.println("Ingrese el ID del libro a actualizar:");
                        libroId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada

                        System.out.println("Ingrese el nuevo título del libro:");
                        tituloLibro = scanner.nextLine();

                        System.out.println("Ingrese el nuevo autor del libro:");
                        autorLibro = scanner.nextLine();

                        // Crear un objeto Libro con los datos recopilados
                        libro = new Libro();
                        libro.setLibroID(libroId);
                        libro.setTitulo(tituloLibro);
                        libro.setAutor(autorLibro);

                        // Llamar a actualizarLibro
                        menuAsistenteControlador.actualizarLibro(libro);
                        break;

                    case ELIMINAR_LIBRO:
                        System.out.println("Ingrese el ID del libro a eliminar:");
                        libroId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada

                        // Llamar a eliminarLibro
                        menuAsistenteControlador.eliminarLibro(libroId);
                        break;

                    case AGREGAR_NOVELA:
                        System.out.println("Ingrese el ID de la novela:");
                        int novelaId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada

                        System.out.println("Ingrese el título de la novela:");
                        String tituloNovela = scanner.nextLine();

                        System.out.println("Ingrese el autor de la novela:");
                        String autorNovela = scanner.nextLine();

                        // Crear un objeto Novela con los datos recopilados
                        Novela novela = new Novela();
                        novela.setNovelaID(novelaId);
                        novela.setTitulo(tituloNovela);
                        novela.setAutor(autorNovela);

                        // Llamar a agregarNovela
                        menuAsistenteControlador.agregarNovela(novela);
                        break;

                    case ACTUALIZAR_NOVELA:
                        System.out.println("Ingrese el ID de la novela a actualizar:");
                        novelaId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada

                        System.out.println("Ingrese el nuevo título de la novela:");
                        tituloNovela = scanner.nextLine();

                        System.out.println("Ingrese el nuevo autor de la novela:");
                        autorNovela = scanner.nextLine();

                        // Crear un objeto Novela con los datos recopilados
                        novela = new Novela();
                        novela.setNovelaID(novelaId);
                        novela.setTitulo(tituloNovela);
                        novela.setAutor(autorNovela);

                        // Llamar a actualizarNovela
                        menuAsistenteControlador.actualizarNovela(novela);
                        break;

                    case ELIMINAR_NOVELA:
                        System.out.println("Ingrese el ID de la novela a eliminar:");
                        novelaId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada

                        // Llamar a eliminarNovela
                        menuAsistenteControlador.eliminarNovela(novelaId);
                        break;

                    case LISTAR_PRESTAMOS_POR_ESTADO:
                        System.out.println("Ingrese el estado de los préstamos (por ejemplo, 'SOLICITADO' o 'DEVUELTO'):");
                        String estadoPrestamo = scanner.nextLine();

                        // Llamar a listarPrestamosPorEstado
                        menuAsistenteControlador.listarPrestamosPorEstado(estadoPrestamo);
                        break;

                    case LISTAR_PRESTAMOS_POR_FECHA:
                        System.out.println("Ingrese la fecha de inicio (formato yyyy-MM-dd):");
                        String fechaInicioStr = scanner.nextLine();
                        Date fechaInicio = Date.valueOf(fechaInicioStr);

                        System.out.println("Ingrese la fecha final (formato yyyy-MM-dd):");
                        String fechaFinStr = scanner.nextLine();
                        Date fechaFin = Date.valueOf(fechaFinStr);

                        // Llamar a listarPrestamosPorFecha
                        menuAsistenteControlador.listarPrestamosPorFecha(fechaInicio, fechaFin);
                        break;

                    case SALIR:
                        System.out.println("Saliendo del menú Asistente...");
                        return;

                    default:
                        System.out.println("Opción no válida. Por favor, inténtelo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }
}

package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.MenuAsistenteControlador;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;
import co.com.pinguinera.modelos.Libro;
import co.com.pinguinera.modelos.Novela;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAsistente {

    private final Scanner scanner;
    private final MenuAsistenteControlador menuAsistenteControlador;

    public MenuAsistente(Scanner scanner, LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio, PrestamoRepositorio prestamoRepositorio) {
        this.scanner = scanner;
        this.menuAsistenteControlador = new MenuAsistenteControlador(libroRepositorio, novelaRepositorio, prestamoRepositorio);
    }

    public void mostrarMenuAsistente() {
        while (true) {
            mostrarOpcionesMenu();
            try {
                int opcion = obtenerOpcionUsuario();
                manejarOpcionSeleccionada(opcion);
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
    }

    private void mostrarOpcionesMenu() {
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
        System.out.print("Elige una opción: ");
    }

    private int obtenerOpcionUsuario() {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        return opcion;
    }

    private void manejarOpcionSeleccionada(int opcion) {
        switch (opcion) {
            case 1:
                agregarLibro();
                break;
            case 2:
                actualizarLibro();
                break;
            case 3:
                eliminarLibro();
                break;
            case 4:
                agregarNovela();
                break;
            case 5:
                actualizarNovela();
                break;
            case 6:
                eliminarNovela();
                break;
            case 7:
                listarPrestamosPorEstado();
                break;
            case 8:
                listarPrestamosPorFecha();
                break;
            case 9:
                System.out.println("Saliendo del menú Asistente...");
                System.exit(0);
            default:
                System.out.println("Opción no válida, por favor intenta de nuevo.");
        }
    }

    private void agregarLibro() {
        System.out.print("Ingrese el ID del libro: ");
        int libroId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        System.out.print("Ingrese el título del libro: ");
        String tituloLibro = scanner.nextLine();

        System.out.print("Ingrese el autor del libro: ");
        String autorLibro = scanner.nextLine();

        Libro libro = new Libro();
        libro.setLibroID(libroId);
        libro.setTitulo(tituloLibro);
        libro.setAutor(autorLibro);

        menuAsistenteControlador.agregarLibro(libro);
        System.out.println("Libro agregado exitosamente.");
    }

    private void actualizarLibro() {
        System.out.print("Ingrese el ID del libro a actualizar: ");
        int libroId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el nuevo título del libro: ");
        String nuevoTitulo = scanner.nextLine();

        System.out.print("Ingrese el nuevo autor del libro: ");
        String nuevoAutor = scanner.nextLine();

        Libro libro = new Libro();
        libro.setLibroID(libroId);
        libro.setTitulo(nuevoTitulo);
        libro.setAutor(nuevoAutor);

        menuAsistenteControlador.actualizarLibro(libro);
        System.out.println("Libro actualizado exitosamente.");
    }

    private void eliminarLibro() {
        System.out.print("Ingrese el ID del libro a eliminar: ");
        int libroId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        menuAsistenteControlador.eliminarLibro(libroId);
        System.out.println("Libro eliminado exitosamente.");
    }

    private void agregarNovela() {
        System.out.print("Ingrese el ID de la novela: ");
        int novelaId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el título de la novela: ");
        String tituloNovela = scanner.nextLine();

        System.out.print("Ingrese el autor de la novela: ");
        String autorNovela = scanner.nextLine();

        Novela novela = new Novela();
        novela.setNovelaID(novelaId);
        novela.setTitulo(tituloNovela);
        novela.setAutor(autorNovela);

        menuAsistenteControlador.agregarNovela(novela);
        System.out.println("Novela agregada exitosamente.");
    }

    private void actualizarNovela() {
        System.out.print("Ingrese el ID de la novela a actualizar: ");
        int novelaId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el nuevo título de la novela: ");
        String nuevoTituloNovela = scanner.nextLine();

        System.out.print("Ingrese el nuevo autor de la novela: ");
        String nuevoAutorNovela = scanner.nextLine();

        Novela novela = new Novela();
        novela.setNovelaID(novelaId);
        novela.setTitulo(nuevoTituloNovela);
        novela.setAutor(nuevoAutorNovela);

        menuAsistenteControlador.actualizarNovela(novela);
        System.out.println("Novela actualizada exitosamente.");
    }

    private void eliminarNovela() {
        System.out.print("Ingrese el ID de la novela a eliminar: ");
        int novelaId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        menuAsistenteControlador.eliminarNovela(novelaId);
        System.out.println("Novela eliminada exitosamente.");
    }

    private void listarPrestamosPorEstado() {
        System.out.print("Ingrese el estado de los préstamos (por ejemplo, 'SOLICITADO' o 'DEVUELTO'): ");
        String estadoPrestamo = scanner.nextLine();

        menuAsistenteControlador.listarPrestamosPorEstado(estadoPrestamo);
    }

    private void listarPrestamosPorFecha() {
        System.out.print("Ingrese la fecha de inicio (formato yyyy-MM-dd): ");
        String fechaInicioStr = scanner.nextLine();
        Date fechaInicio = Date.valueOf(fechaInicioStr);

        System.out.print("Ingrese la fecha final (formato yyyy-MM-dd): ");
        String fechaFinStr = scanner.nextLine();
        Date fechaFin = Date.valueOf(fechaFinStr);

        menuAsistenteControlador.listarPrestamosPorFecha(fechaInicio, fechaFin);
    }
}

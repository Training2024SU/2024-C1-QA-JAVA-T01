package co.com.pinguinera.vistas.vista_usuario;

import co.com.pinguinera.controladores.crud.ControladorCRUDUsuario;
import co.com.pinguinera.controladores.crud.ControladorCRUDPrestamo;
import co.com.pinguinera.controladores.crud.ControladorCRUDLibro;
import co.com.pinguinera.controladores.crud.ControladorCRUDNovela;

import java.util.Scanner;

public class MenuPrincipalUsuario {

    private final ControladorCRUDUsuario controladorCRUDUsuario;
    private final ControladorCRUDPrestamo controladorPrestamo;
    private final ControladorCRUDLibro controladorCRUDLibro;
    private final ControladorCRUDNovela controladorCRUDNovela;
    private final Scanner scanner;

    public MenuPrincipalUsuario(ControladorCRUDUsuario controladorCRUDUsuario,
                                ControladorCRUDPrestamo controladorPrestamo,
                                ControladorCRUDLibro controladorCRUDLibro,
                                ControladorCRUDNovela controladorCRUDNovela) {
        this.controladorCRUDUsuario = controladorCRUDUsuario;
        this.controladorPrestamo = controladorPrestamo;
        this.controladorCRUDLibro = controladorCRUDLibro;
        this.controladorCRUDNovela = controladorCRUDNovela;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenú principal de usuario");
            System.out.println("1. Actualizar información de usuario");
            System.out.println("2. Realizar préstamo");
            System.out.println("3. Ver todos los libros");
            System.out.println("4. Ver todas las novelas");
            System.out.println("5. Salir");

            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    controladorCRUDUsuario.actualizarUsuario();
                    break;
                case 2:
                    controladorPrestamo.registrarPrestamo();
                    break;
                case 3:
                    controladorCRUDLibro.obtenerTodosLibros();
                    break;
                case 4:
                    controladorCRUDNovela.obtenerTodasNovelas();
                    break;
                case 5:
                    System.out.println("Saliendo del menú...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private int obtenerOpcion() {
        while (true) {
            System.out.print("Opción: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}

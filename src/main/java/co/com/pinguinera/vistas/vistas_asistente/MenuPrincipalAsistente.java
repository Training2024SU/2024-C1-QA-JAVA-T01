package co.com.pinguinera.vistas.vistas_asistente;

import co.com.pinguinera.controladores.crud.ControladorCRUDLibro;
import co.com.pinguinera.controladores.crud.ControladorCRUDNovela;
import co.com.pinguinera.controladores.crud.ControladorCRUDPrestamo;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.Scanner;

public class MenuPrincipalAsistente {
    private final ControladorCRUDLibro controladorCRUDLibro;
    private final ControladorCRUDNovela controladorCRUDNovela;
    private final ControladorCRUDPrestamo controladorCRUDPrestamo;
    private final Scanner scanner;

    public MenuPrincipalAsistente(ControladorCRUDLibro controladorCRUDLibro,
                                  ControladorCRUDNovela controladorCRUDNovela,
                                  ControladorCRUDPrestamo controladorCRUDPrestamo) {
        this.controladorCRUDLibro = controladorCRUDLibro;
        this.controladorCRUDNovela = controladorCRUDNovela;
        this.controladorCRUDPrestamo = controladorCRUDPrestamo;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenú principal de asistente");
            System.out.println("1. Ver todos los libros");
            System.out.println("2. Ver todas las novelas");
            System.out.println("3. Ver todos los préstamos");
            System.out.println("4. Agregar libro");
            System.out.println("5. Agregar novela");
            System.out.println("6. Eliminar libro");
            System.out.println("7. Eliminar novela");
            System.out.println("8. Actualizar libro");
            System.out.println("9. Actualizar novela");
            System.out.println("10. Salir");

            int opcion = VistaUtil.obtenerOpcion();

            switch (opcion) {
                case 1:
                    controladorCRUDLibro.obtenerTodosLibros();
                    break;
                case 2:
                    controladorCRUDNovela.obtenerTodasNovelas();
                    break;
                case 3:
                    controladorCRUDPrestamo.obtenerTodosPrestamos();
                    break;
                case 4:
                    controladorCRUDLibro.registrarLibro();
                    break;
                case 5:
                    controladorCRUDNovela.registrarNovela();
                    break;
                case 6:
                    controladorCRUDLibro.eliminarLibro();
                    break;
                case 7:
                    controladorCRUDNovela.eliminarNovela();
                    break;
                case 8:
                    controladorCRUDLibro.actualizarLibro();
                    break;
                case 9:
                    controladorCRUDNovela.actualizarNovela();
                    break;
                case 10:
                    System.out.println("Saliendo del menú...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }


}

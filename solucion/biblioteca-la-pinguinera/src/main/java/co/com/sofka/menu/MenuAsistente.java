package co.com.sofka.menu;

import co.com.sofka.util.ExportarLibros;
import co.com.sofka.servicio.ServicioLibro;
import co.com.sofka.servicio.ServicioNovela;
import co.com.sofka.servicio.ServicioPrestamo;
import static co.com.sofka.menu.ConstantesMenu.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAsistente {
    ServicioLibro servicioLibro;
    ServicioNovela servicioNovela;
    ServicioPrestamo servicioPrestamo;

    MetodosMenuAsistenteLibro metodosMenuAsistenteLibro;
    MetodosMenuAsistenteNovela metodosMenuAsistenteNovela;
    MetodosMenuAsistentePrestamo metodosMenuAsistentePrestamo;

    Scanner scanner;

    boolean seguirEjecucion = true;

    public MenuAsistente(ServicioLibro servicioLibro, ServicioNovela servicioNovela, ServicioPrestamo servicioPrestamo, Scanner scanner, ExportarLibros exportarLibros) {
        this.servicioLibro = servicioLibro;
        this.servicioNovela = servicioNovela;
        this.servicioPrestamo = servicioPrestamo;
        this.scanner = scanner;

        this.metodosMenuAsistenteLibro = new MetodosMenuAsistenteLibro(scanner, servicioLibro, exportarLibros);
        this.metodosMenuAsistenteNovela = new MetodosMenuAsistenteNovela(scanner, servicioNovela);
        this.metodosMenuAsistentePrestamo = new MetodosMenuAsistentePrestamo(scanner, servicioPrestamo);
    }

    public void imprimirMenuAsistente(){
        System.out.println(ConstantesMenu.MENSAJE_MENU_CUALQUIER_ROL);
        System.out.println(ConstantesMenu.OPCIONES_ASISTENTE);

        try {
            int eleccion = scanner.nextInt();
            scanner.nextLine();

            switch (eleccion) {
                case 1:
                    metodosMenuAsistenteLibro.guardarLibro();
                    break;

                case 2:
                    metodosMenuAsistenteNovela.guardarNovela();
                    break;
                case 3:
                    servicioLibro.listarAutores();
                    break;
                case 4:
                    servicioNovela.listarAutores();
                    break;
                case 5:
                    metodosMenuAsistenteLibro.buscarLibroPorAutor();
                    break;
                case 6:
                    metodosMenuAsistenteNovela.buscarNovelaPorAutor();
                    break;
                case 7:
                    metodosMenuAsistentePrestamo.listarPrestamosSolicitados();
                    break;
                case 8:
                    metodosMenuAsistentePrestamo.realizarPrestamosSolicitados();
                    break;
                case 9:
                    metodosMenuAsistentePrestamo.listarPrestamosRealizados();
                    break;
                case 10:
                    metodosMenuAsistentePrestamo.finalizarPrestamoRealizado();
                    break;
                case 11:
                    metodosMenuAsistenteLibro.modificarLibro();
                    break;
                case 12:
                    metodosMenuAsistenteNovela.modificarNovela();
                    break;
                case 13:
                    metodosMenuAsistenteLibro.borrarLibro();
                    break;
                case 14:
                    metodosMenuAsistenteNovela.borrarNovela();
                    break;
                case 15:
                    metodosMenuAsistenteLibro.exportarAExcel();
                    break;
                case 16:
                    metodosMenuAsistenteLibro.leerExcel();
                    break;
                case 0:
                    seguirEjecucion = false;
                    System.out.println(ConstantesMenu.MENSAJE_DESPEDIDA);
                    break;
                default:
                    System.out.println(ConstantesMenu.OPCION_NO_VALIDA);
            }
        } catch (InputMismatchException error){
            System.out.println(OPCION_NO_VALIDA);
            scanner.nextLine();
            return;
        }

    }

    public boolean quieroSeguirEjecucion(){
        return seguirEjecucion;
    }


}

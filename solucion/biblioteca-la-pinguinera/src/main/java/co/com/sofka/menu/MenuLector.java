package co.com.sofka.menu;

import co.com.sofka.modelo.Usuario;
import co.com.sofka.servicio.ServicioLibro;
import co.com.sofka.servicio.ServicioNovela;
import co.com.sofka.servicio.ServicioPrestamo;

import java.util.InputMismatchException;
import java.util.Scanner;

import static co.com.sofka.menu.ConstantesMenu.*;
import static co.com.sofka.menu.ConstantesMenu.SEGUNDO_MENSAJE_PRESTAR_NOVELA;

public class MenuLector {

    ServicioLibro servicioLibro;
    ServicioNovela servicioNovela;
    ServicioPrestamo servicioPrestamo;

    Scanner scanner;

    boolean seguirEjecucion = true;

    public MenuLector(ServicioLibro servicioLibro, ServicioNovela servicioNovela, ServicioPrestamo servicioPrestamo, Scanner scanner ) {
        this.servicioLibro = servicioLibro;
        this.servicioNovela = servicioNovela;
        this.servicioPrestamo = servicioPrestamo;
        this.scanner = scanner;
    }

    public void imprimirMenuLectores(Usuario usuario){
        System.out.println(ConstantesMenu.MENSAJE_MENU_CUALQUIER_ROL);
        System.out.println(ConstantesMenu.OPCIONES_LECTOR);

        try {
            int eleccion = scanner.nextInt();
            scanner.nextLine();

            switch (eleccion){
                case 1:
                    servicioLibro.listarAutores();
                    break;
                case 2:
                    servicioNovela.listarAutores();
                    break;
                case 3:
                    servicioLibro.listarLibrosDisponibles();
                    break;
                case 4:
                    servicioNovela.listarNovelasDisponibles();
                    break;
                case 5:
                    prestarLibros(usuario);
                    break;
                case 6:
                    prestarNovelas(usuario);
                    break;
                case 0:
                    seguirEjecucion = false;
                    System.out.println(ConstantesMenu.MENSAJE_DESPEDIDA);
                    break;
                default:
                    System.out.println(ConstantesMenu.OPCION_NO_VALIDA);
            }
        } catch (InputMismatchException e){
            System.out.println("Opcion ingresada no es valida");
            scanner.nextLine();
            return;
        }

    }

    public void prestarLibros(Usuario usuarioIngresado){
        System.out.println(PRIMER_MENSAJE_PRESTAR_LIBRO);
        String titulo = scanner.nextLine();

        servicioPrestamo.solicitarPrestamoLibro(usuarioIngresado, titulo);
        System.out.println(SEGUNDO_MENSAJE_PRESTAR_LIBRO );
    }

    public void prestarNovelas(Usuario usuarioIngresado){
        System.out.println(PRIMER_MENSAJE_PRESTAR_NOVELA);
        String titulo = scanner.nextLine();

        servicioPrestamo.solicitarPrestamoNovela(usuarioIngresado, titulo);
        System.out.println(SEGUNDO_MENSAJE_PRESTAR_NOVELA);
    }

    public boolean quieroSeguirEjecucion(){
        return seguirEjecucion;
    }


}

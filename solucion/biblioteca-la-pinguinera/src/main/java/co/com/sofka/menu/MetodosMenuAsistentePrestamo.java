package co.com.sofka.menu;

import co.com.sofka.servicio.ServicioPrestamo;
import co.com.sofka.util.EstadoPrestamo;

import java.util.Scanner;

import static co.com.sofka.menu.ConstantesMenu.*;
import static co.com.sofka.menu.ConstantesMenu.SEGUNDO_MENSAJE_PRESTAMO_FINALIZADO;

public class MetodosMenuAsistentePrestamo {
    public Scanner scanner;
    public ServicioPrestamo servicioPrestamo;

    public MetodosMenuAsistentePrestamo(Scanner scanner, ServicioPrestamo servicioPrestamo) {
        this.scanner = scanner;
        this.servicioPrestamo = servicioPrestamo;
    }

    public void listarPrestamosSolicitados(){
        System.out.println("Listando prestamos en estado SOLICITADO \nIngrese el correo del usuario");
        String correoUsuarioPrestamos = scanner.nextLine();

        servicioPrestamo.listarPorCorreoYEstado(correoUsuarioPrestamos, EstadoPrestamo.SOLICITADO);
    }

    public void realizarPrestamosSolicitados(){
        System.out.println(PRIMER_MENSAJE_PRESTAMO_SOLICITADO);
        Long prestamoId = scanner.nextLong();
        scanner.nextLine();

        servicioPrestamo.realizarPrestamo(prestamoId);
    }

    public void listarPrestamosRealizados(){
        System.out.println(PRIMER_MENSAJE_PRESTAMO_REALIZADO);
        String correoUsuarioPrestamos = scanner.nextLine();

        servicioPrestamo.listarPorCorreoYEstado(correoUsuarioPrestamos, EstadoPrestamo.REALIZADO);
    }

    public void finalizarPrestamoRealizado(){
        System.out.println(PRIMER_MENSAJE_PRESTAMO_FINALIZADO);
        Long prestamoId = scanner.nextLong();
        scanner.nextLine();

        servicioPrestamo.realizarDevolucion(prestamoId);
        System.out.println(SEGUNDO_MENSAJE_PRESTAMO_FINALIZADO);
    }
}

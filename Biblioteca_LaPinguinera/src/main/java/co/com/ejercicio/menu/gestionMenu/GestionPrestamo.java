package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Prestamo;
import co.com.ejercicio.modeloAccesoBD.PrestamoAccesoBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.com.ejercicio.menu.dialogos.MenuGestionPrestamo.menuPrestamos;

public class GestionPrestamo {

    public static void mostrarMenuGestionPrestamo(){
        menuPrestamos();
    }
    public static void insertarPrestamo() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando préstamo...");
        System.out.println("Ingrese id ");
        int idPrestamo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Fecha prestamo");
        Date fechaPrestamo = Date.valueOf(scanner.nextLine());
        System.out.println("Fecha devolucion");
        Date fechaDevolucion = Date.valueOf(scanner.nextLine());
        System.out.println("Estado");
        String estado = scanner.nextLine();
        System.out.println("Correo usuario");
        String correoUsuario = scanner.nextLine();
        System.out.println("Titulo publicacion");
        String tituloPublicacion = scanner.nextLine();

        Connection conexion = Conexion.obtenerConexion();
        PrestamoAccesoBD prestamoAccesoBD = new PrestamoAccesoBD(conexion);
        Prestamo prestamo = new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, estado, correoUsuario, tituloPublicacion);
        prestamoAccesoBD.insertarPrestamo(prestamo);
    }

    public static void obtenerPrestamo() throws SQLException {
        System.out.println("Obteniendo préstamo...");
        Connection conexion = Conexion.obtenerConexion();
        PrestamoAccesoBD prestamoAccesoBD = new PrestamoAccesoBD(conexion);
        List<Prestamo> prestamos = prestamoAccesoBD.obtenerTodosLosPrestamos();
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo.toString());
        }
    }

    public static void eliminarPrestamo() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eliminando préstamo...");
        System.out.println("Ingresa el id a eliminar");
        int idPrestamo = scanner.nextInt();

        Connection conexion = Conexion.obtenerConexion();
        PrestamoAccesoBD prestamoAccesoBD = new PrestamoAccesoBD(conexion);
        prestamoAccesoBD.eliminarPrestamo(idPrestamo);
    }

    public static void actualizarPrestamo() {
        System.out.println("Actualizando préstamo...");
    }

}

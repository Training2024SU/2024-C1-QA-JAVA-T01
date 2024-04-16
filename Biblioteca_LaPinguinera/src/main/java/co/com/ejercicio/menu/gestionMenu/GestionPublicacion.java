package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Publicacion;
import co.com.ejercicio.modeloAccesoBD.PublicacionAccesoBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.com.ejercicio.menu.dialogos.MenuGestionPublicacion.menuPublicacion;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;
import static co.com.ejercicio.modeloAccesoBD.PublicacionAccesoBD.obtenerPublicacionesPorAutor;

public class GestionPublicacion {

    public static void mostrarMenuGestionPublicacion() {
        menuPublicacion();
    }

    public static void gestionInsertarPublicacion() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando publicacion...");
        System.out.println("Ingresa Titulo");
        String titulo = scanner.nextLine();
        System.out.println("Ingresa tipo de publicacion");
        String tipo = scanner.nextLine();
        System.out.println("Ingresa numero de paginas");
        int numPagina = scanner.nextInt();
        System.out.println("Ingresa cantidad de ejemplares");
        int canEjemplares = scanner.nextInt();
        System.out.println("Ingresa cantidad prestado");
        int prestado = scanner.nextInt();
        int cantidadDisponible = canEjemplares - prestado;
        System.out.println("Ingresa nombre de autor");
        String nombreAutor = scanner.nextLine();

        Connection conexion = Conexion.obtenerConexion();
        PublicacionAccesoBD publicacionAccesoBD = new PublicacionAccesoBD(conexion);
        publicacionAccesoBD.insertarPublicacion(new Publicacion(titulo, tipo, numPagina,
                canEjemplares, prestado, cantidadDisponible, nombreAutor));
    }

    public static void gestionObtenerAutoresPublicacion() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingresa el nombre del autor");
            String nombreAutor = scanner.nextLine();
            Connection conexion = Conexion.obtenerConexion();
            PublicacionAccesoBD publicacionAccesoBD = new PublicacionAccesoBD(conexion);
            List<Publicacion> publicaciones = obtenerPublicacionesPorAutor(nombreAutor);
            for (Publicacion publicacion : publicaciones) {
                System.out.println(publicacion);
            }
        }

    public static void gestionObtenerPublicacion() throws SQLException {

        System.out.println("Obteniendo Publicacion...");
        try {
            Connection conexion = Conexion.obtenerConexion();
            PublicacionAccesoBD publicacionAccesoBD = new PublicacionAccesoBD(conexion);
            List<Publicacion> publicaciones = publicacionAccesoBD.obtenerTodasLasPublicaciones();
            for (Publicacion publicacion : publicaciones) {
                System.out.println(publicacion);
            }
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public static void gestionEliminarPublicacion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el titulo de la publicacion que quiere eliminar");
        String opcion = scanner.nextLine();
        System.out.println("Eliminando publicacion...");
        try {
            Connection conexion = Conexion.obtenerConexion();
            PublicacionAccesoBD publicacionAccesoBD = new PublicacionAccesoBD(conexion);
            publicacionAccesoBD.eliminarPublicacion(opcion);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public static void gestionActualizarPublicacion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa los datos a actualizar");
        System.out.println("Ingresa el titulo");
        String titulo = scanner.nextLine();
        System.out.println("ingresa tipo de publicacion");
        String tipoPublicacion = scanner.nextLine();
        System.out.println("ingresa Numero de paginas");
        int numPaginas = scanner.nextInt();
        System.out.println("ingresa cantidad Ejemplares");
        int cantidadEjemplares = scanner.nextInt();
        System.out.println("ingresa cantidad prestado");
        int canPrestado = scanner.nextInt();
        int canDisponible = cantidadEjemplares - canPrestado;
        System.out.println("ingresa Id Autor");
        String nombreAutor = scanner.nextLine();
        System.out.println("Actualizando libro...");

        Connection conexion = Conexion.obtenerConexion();
        PublicacionAccesoBD publicacionAccesoBD = new PublicacionAccesoBD(conexion);
        publicacionAccesoBD.actualizarPublicacion(new Publicacion(titulo,
                tipoPublicacion, numPaginas, cantidadEjemplares,
                canPrestado, canDisponible, nombreAutor));
    }
}

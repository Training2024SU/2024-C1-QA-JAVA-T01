package org.moreno.cristian.ui.crudNovelas;

import org.moreno.cristian.repositorios.RepositorioAutor;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.servicios.*;

import java.sql.SQLException;
import java.util.Scanner;

import static org.moreno.cristian.ui.crudLibros.ActualizarLibro.actualizarLibro;
import static org.moreno.cristian.ui.crudLibros.CrearLibro.crearLibro;
import static org.moreno.cristian.ui.crudLibros.EliminarLibro.eliminarLibro;
import static org.moreno.cristian.ui.crudLibros.VerLibro.verLibro;

public class CrudNovelas {

    private static Scanner scan = new Scanner(System.in);
    private static final RepositorioAutor servicioAutor;
    private static final RepositorioLibro servicioLibro;

    static {
        try {
            servicioAutor = new ServicioAutor(ConexionBD.obtenerConexion());
            servicioLibro = new ServicioLibro(new ServicioPublicacion(ConexionBD.obtenerConexion(), new ServicioAutor(ConexionBD.obtenerConexion())), ConexionBD.obtenerConexion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crudNovelas() {
        while (true) {
            System.out.println("\nQué desea hacer?\n" +
                    "   1. Crear libro \n" +
                    "   2. Eliminar libro \n" +
                    "   3. Actualizar libro \n" +
                    "   4. Ver libros ");

            String respuestaAdmin = scan.nextLine();

            switch(respuestaAdmin) {
                case "1":
                    crearLibro(scan, servicioLibro);
                    break;
                case "2":
                    eliminarLibro(scan, servicioLibro);
                    break;
                case "3":
                    actualizarLibro(scan, servicioLibro, servicioAutor);
                    break;
                case "4":
                    verLibro(scan, servicioLibro);
                    break;
                default:
                    System.out.println("Inténtalo de nuevo");
            }
        }
    }
}

package org.moreno.cristian.ui.crudNovelas;

import org.moreno.cristian.repositorios.RepositorioAutor;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;
import org.moreno.cristian.servicios.*;

import java.sql.SQLException;
import java.util.Scanner;

import static org.moreno.cristian.ui.crudLibros.ActualizarLibro.actualizarLibro;
import static org.moreno.cristian.ui.crudLibros.CrearLibro.crearLibro;
import static org.moreno.cristian.ui.crudLibros.EliminarLibro.eliminarLibro;
import static org.moreno.cristian.ui.crudLibros.VerLibro.verLibro;
import static org.moreno.cristian.ui.crudNovelas.ActualizarNovela.actualizarNovela;
import static org.moreno.cristian.ui.crudNovelas.CrearNovela.crearNovela;
import static org.moreno.cristian.ui.crudNovelas.EliminarNovela.eliminarNovela;
import static org.moreno.cristian.ui.crudNovelas.VerNovela.verNovela;

public class CrudNovelas {

    private static Scanner scan = new Scanner(System.in);
    private static final RepositorioAutor servicioAutor;
    private static final RepositorioNovela servicioNovela;

    static {
        try {
            servicioAutor = new ServicioAutor(ConexionBD.obtenerConexion());
            servicioNovela = new ServicioNovela(new ServicioPublicacion(ConexionBD.obtenerConexion(), new ServicioAutor(ConexionBD.obtenerConexion())), ConexionBD.obtenerConexion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crudNovelas() {
        while (true) {
            System.out.println("\nQué desea hacer?\n" +
                    "   1. Crear novela \n" +
                    "   2. Eliminar novela \n" +
                    "   3. Actualizar novela \n" +
                    "   4. Ver novelas ");

            String respuestaAdmin = scan.nextLine();

            switch(respuestaAdmin) {
                case "1":
                    crearNovela(scan, servicioNovela);
                    break;
                case "2":
                    eliminarNovela(scan, servicioNovela);
                    break;
                case "3":
                    actualizarNovela(scan, servicioNovela, servicioAutor);
                    break;
                case "4":
                    verNovela(scan, servicioNovela);
                    break;
                default:
                    System.out.println("Inténtalo de nuevo");
            }
        }
    }

}

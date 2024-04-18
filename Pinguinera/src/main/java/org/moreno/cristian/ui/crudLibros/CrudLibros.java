package org.moreno.cristian.ui.crudLibros;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.repositorios.RepositorioAutor;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.servicios.*;

import java.sql.SQLException;
import java.util.Scanner;

import static org.moreno.cristian.ui.crudLibros.ActualizarLibro.actualizarLibro;
import static org.moreno.cristian.ui.crudLibros.CrearLibro.crearLibro;
import static org.moreno.cristian.ui.crudLibros.EliminarLibro.eliminarLibro;
import static org.moreno.cristian.ui.crudLibros.VerLibro.verLibro;

public class CrudLibros {

    private static Scanner scan = ScannerUtil.obtenerScanner();
    private static final RepositorioAutor servicioAutor;
    private static final RepositorioLibro servicioLibro;
    static Logger log = LogManager.getLogger(String.valueOf(CrudLibros.class));

    static {
        try {
            servicioAutor = new ServicioAutor(ConexionBD.obtenerConexion());
            servicioLibro = new ServicioLibro(new ServicioPublicacion(ConexionBD.obtenerConexion(), new ServicioAutor(ConexionBD.obtenerConexion())), ConexionBD.obtenerConexion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crudLibros() {
        while (true) {
            log.info("\nQué desea hacer?\n" +
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
                    log.error("Respuesta no válida. Inténtalo de nuevo");
            }
        }
    }

}

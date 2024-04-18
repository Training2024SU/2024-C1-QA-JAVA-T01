package org.moreno.cristian.ui.crudLibros;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.repositorios.RepositorioLibro;

import java.util.Scanner;

public class EliminarLibro {

    static Logger log = LogManager.getLogger(String.valueOf(EliminarLibro.class));
    public static void eliminarLibro (Scanner scan, RepositorioLibro servicioLibro) {

        log.info("Ingresa el id del libro: ");
        String libroId = scan.nextLine();

        if (servicioLibro.eliminarLibro(libroId)) {
            log.info("Libro eliminado");
        } else {
            log.warn("Libro no encontrado");
        }
    }
}

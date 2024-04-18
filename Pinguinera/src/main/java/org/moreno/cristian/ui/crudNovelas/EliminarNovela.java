package org.moreno.cristian.ui.crudNovelas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;
import org.moreno.cristian.ui.crudLibros.EliminarLibro;

import java.util.Scanner;

public class EliminarNovela {

    static Logger log = LogManager.getLogger(String.valueOf(EliminarNovela.class));

    public static void eliminarNovela (Scanner scan, RepositorioNovela servicioNovela) {

        log.info("Ingresa el id de la novela: ");
        String novelaId = scan.nextLine();

        if (servicioNovela.eliminarNovela(novelaId)) {
            log.info("Novela eliminada");
        } else {
            log.warn("Novela no encontrada");
        }
    }
}

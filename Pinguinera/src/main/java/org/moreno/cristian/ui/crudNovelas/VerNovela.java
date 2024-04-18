package org.moreno.cristian.ui.crudNovelas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Novela;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.modelos.enums.Genero;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VerNovela {

    static Logger log = LogManager.getLogger(String.valueOf(VerNovela.class));

    public static void verNovela(Scanner scan, RepositorioNovela servicioNovela) {

        Optional<List<Novela>> novelasOptional = servicioNovela.todasNovelas();

        if (novelasOptional.isPresent()) {

            List<Novela> novelas = novelasOptional.get();

            for (Novela novela : novelas) {
                log.info("Id: " + novela.getId());
                log.info("Título: " + novela.getTitulo());
                log.info("Autor: " + novela.getAutor().getNombre());
                log.info("Área conocimiento: " + novela.getGenero());
                log.info("Copias disponibles: " + novela.getEjemplaresDisponibles());
                log.info("-----");
            }
        } else {
            log.info("No hay novelas");
        }
    }
}

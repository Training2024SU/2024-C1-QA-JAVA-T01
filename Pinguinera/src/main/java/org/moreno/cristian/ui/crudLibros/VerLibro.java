package org.moreno.cristian.ui.crudLibros;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.repositorios.RepositorioLibro;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VerLibro {

    static Logger log = LogManager.getLogger(String.valueOf(VerLibro.class));

    public static void verLibro(Scanner scan, RepositorioLibro servicioLibro) {

        Optional<List<Libro>> librosOptional = servicioLibro.todosLibros();

        if (librosOptional.isPresent()) {

            List<Libro> libros = librosOptional.get();

            for (Libro libro : libros) {
                log.info("Id: " + libro.getId());
                log.info("Título: " + libro.getTitulo());
                log.info("Autor: " + libro.getAutor().getNombre());
                log.info("Área conocimiento: " + libro.getAreaConocimiento());
                log.info("Copias disponibles: " + libro.getEjemplaresDisponibles());
                log.info("-----");
            }
        } else {
            log.warn("No hay libros");
        }

    }

}

package org.moreno.cristian.ui.crudNovelas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Novela;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.modelos.enums.Genero;
import org.moreno.cristian.repositorios.RepositorioAutor;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;
import org.moreno.cristian.servicios.ScannerUtil;

import java.util.Optional;
import java.util.Scanner;

public class ActualizarNovela {

    static Logger log = LogManager.getLogger(String.valueOf(ActualizarNovela.class));

    public static void actualizarNovela(Scanner scan, RepositorioNovela servicioNovela, RepositorioAutor servicioAutor) {

        log.info("Ingresa el título de la novela a actualizar: ");
        String titulo = scan.nextLine();

        Optional<Novela> novelaOptional = servicioNovela.novelaPorNombre(titulo);

        if (!novelaOptional.isPresent()) {
            log.warn("No existe una novela con este nombre ");
            return;
        }
        Novela novelaVieja = novelaOptional.get();

        log.info("Ingresa el nuevo título de la novela a actualizar: ");
        String nuevoTitulo = scan.nextLine();

        log.info("Ingresa el nuevo número de ejemplares: ");
        int nuevoNumeroEjemplares = ScannerUtil.pedirEntero();

        log.info("Ingresa el nuevo nombre del autor: ");
        String nombreNuevoAutor = scan.nextLine();

        Autor nuevoAutor;
        Optional<Autor> autor = servicioAutor.autorPorNombre(nombreNuevoAutor);

        if(autor.isPresent()) {
            nuevoAutor = autor.get();
        } else {
            nuevoAutor = new Autor(nombreNuevoAutor);
        }

        log.info("Ingresa la nueva edad de lectura: ");
        int nuevaEdadLectura = ScannerUtil.pedirEntero();

        log.info("Ingresa el nuevo género: ");
        String nuevoGenero = scan.nextLine();

        Novela novelaActualizada = new Novela(novelaVieja.getId(), nuevoTitulo, nuevoNumeroEjemplares, 0, 0, nuevoAutor, nuevaEdadLectura, Genero.valueOf(nuevoGenero));

        if (servicioNovela.actualizarNovela(novelaActualizada)) {
            log.info("Libro actualizado");
        } else {
            log.error("Ocurrió un error");
        }
    }
}

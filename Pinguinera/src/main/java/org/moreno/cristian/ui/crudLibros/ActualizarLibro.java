package org.moreno.cristian.ui.crudLibros;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.repositorios.RepositorioAutor;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.servicios.ScannerUtil;

import java.util.Optional;
import java.util.Scanner;

public class ActualizarLibro {

    static Logger log = LogManager.getLogger(String.valueOf(ActualizarLibro.class));
    public static void actualizarLibro(Scanner scan, RepositorioLibro servicioLibro, RepositorioAutor servicioAutor) {

        log.info("Ingresa el título del libro actualizar: ");
        String titulo = scan.nextLine();

        Optional<Libro> libroOptional = servicioLibro.libroPorNombre(titulo);

        if (!libroOptional.isPresent()) {
            log.warn("No existe un libro con este nombre ");
            return;
        }
        Libro libroViejo = libroOptional.get();


        log.info("Ingresa el nuevo título del libro actualizar: ");
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


        log.info("Ingresa el número de páginas: ");
        int nuevoNumeroPaginas = ScannerUtil.pedirEntero();


        log.info("Ingresa el nuevo área de conocimiento: ");
        String nuevoArea = scan.nextLine();

        Libro libroActualizado = new Libro(libroViejo.getId(), nuevoTitulo, nuevoNumeroEjemplares, 0, 0, nuevoAutor, nuevoNumeroPaginas, AreaConocimiento.valueOf(nuevoArea));

        if (servicioLibro.actualizarLibro(libroActualizado)) {
            log.info("Libro actualizado");
        } else {
            log.error("Ocurrió un error");
        }
    }
}

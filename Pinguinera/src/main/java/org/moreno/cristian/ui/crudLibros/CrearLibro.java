package org.moreno.cristian.ui.crudLibros;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.servicios.ScannerUtil;

import java.sql.Connection;
import java.util.Scanner;

public class CrearLibro {

    static Logger log = LogManager.getLogger(String.valueOf(CrearLibro.class));

    public static void crearLibro(Scanner scan, RepositorioLibro servicioLibro) {

        log.info("Ingresa el título del libro: ");
        String titulo = scan.nextLine();

        if (servicioLibro.libroPorNombre(titulo).isPresent()) {
            log.error("Ya existe un libro con este título ");
            return;
        }

        log.info("Ingresa el número de ejemplares: ");
        int numeroEjemplares = ScannerUtil.pedirEntero();


        log.info("Ingresa el nombre del autor: ");
        String autor = scan.nextLine();


        log.info("Ingresa el número de páginas: ");
        int numeroPaginas = ScannerUtil.pedirEntero();


        log.info("Ingresa el área de conocimiento: ");
        String area = scan.nextLine();

        Libro nuevoLibro = new Libro(titulo, numeroEjemplares, 0, 0, new Autor(autor), numeroPaginas, AreaConocimiento.valueOf(area));

        if (servicioLibro.guardarLibro(nuevoLibro)) {
            log.info("Libro guardado");
        } else {
            log.error("Ocurrió un error");
        }
    }

}

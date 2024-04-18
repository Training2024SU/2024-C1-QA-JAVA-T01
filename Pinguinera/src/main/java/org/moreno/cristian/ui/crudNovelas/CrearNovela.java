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
import org.moreno.cristian.servicios.ScannerUtil;
import org.moreno.cristian.ui.crudLibros.EliminarLibro;

import java.util.Scanner;

public class CrearNovela {

    static Logger log = LogManager.getLogger(String.valueOf(CrearNovela.class));

    public static void crearNovela(Scanner scan, RepositorioNovela servicioNovela) {

        log.info("Ingresa el título de la novela: ");
        String titulo = scan.nextLine();

        if (servicioNovela.novelaPorNombre(titulo).isPresent()) {
            log.warn("Ya existe una novela con este título ");
            return;
        }

        log.info("Ingresa el número de ejemplares: ");
        int numeroEjemplares = ScannerUtil.pedirEntero();


        log.info("Ingresa el nombre del autor: ");
        String autor = scan.nextLine();


        log.info("Ingresa la edad recomendada de lectura: ");
        int edadLectura = ScannerUtil.pedirEntero();


        log.info("Ingresa el género: ");
        String genero = scan.nextLine();

        Novela nuevoLibro = new Novela(titulo, numeroEjemplares, 0, 0, new Autor(autor), edadLectura, Genero.valueOf(genero));

        if (servicioNovela.guardarNovela(nuevoLibro)) {
            log.info("Novela guardada");
        } else {
            log.info("Ocurrió un error");
        }
    }
}

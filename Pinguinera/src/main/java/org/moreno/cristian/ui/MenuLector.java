package org.moreno.cristian.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.modelos.*;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;
import org.moreno.cristian.repositorios.RepositorioPrestamo;
import org.moreno.cristian.servicios.*;
import org.moreno.cristian.ui.crudNovelas.CrudNovelas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuLector {

    private static Scanner scan = ScannerUtil.obtenerScanner();
    private static RepositorioLibro servicioLibro;
    private static RepositorioNovela servicioNovela;
    private static RepositorioPrestamo servicioPrestamo;
    static Logger log = LogManager.getLogger(String.valueOf(MenuLector.class));

    static {
        try {
            servicioLibro = new ServicioLibro(new ServicioPublicacion(ConexionBD.obtenerConexion(), new ServicioAutor(ConexionBD.obtenerConexion())), ConexionBD.obtenerConexion());
            servicioNovela = new ServicioNovela(new ServicioPublicacion(ConexionBD.obtenerConexion(), new ServicioAutor(ConexionBD.obtenerConexion())), ConexionBD.obtenerConexion());
            servicioPrestamo = new ServicioPrestamo(new ServicioPublicacion(ConexionBD.obtenerConexion(), new ServicioAutor(ConexionBD.obtenerConexion())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void home (Usuario lector) {

        log.info("Bienvenido de nuevo " + lector.getNombre() + "\n");

        while (true) {

            log.info("Qué desea hacer\n" +
                    "   1. Ver libros\n" +
                    "   2. Ver novelas\n" +
                    "   3. Hacer préstamo\n" +
                    "   4. Prestar novelas\n" +
                    "   5. Devolver libros\n" +
                    "   6. Devoler novelas\n");

            String respuestaLector = scan.nextLine();

            switch(respuestaLector) {
                case "1":
                    listarLibros();
                    break;
                case "2":
                    listarNovelas();
                    break;
                case "3":
                    hacerPrestamo(lector);
                    break;
                case "4":
                    prestarNovelas();
                    break;
                case "5":
                    devolverLibros();
                    break;
                case "6":
                    devolverNovelas();
                    break;
                default:
                    log.warn("Ingresa una opción correcta");
            }
        }
    }

    public static void listarLibros () {


        while (true) {

            log.info(
                    "\n   1. Ver todos\n" +
                    "   2. Filtrar por autor\n" +
                    "   3. Salir" );

            String respuestaLector = scan.nextLine();
            log.info(respuestaLector);

            if (respuestaLector.equals("1")) {
                Optional<List<Libro>> librosOptional = servicioLibro.todosDisponibles();

                if (librosOptional.isPresent()) {

                    List<Libro> libros = librosOptional.get();

                    for (Libro libro : libros) {
                        log.info("Título: " + libro.getTitulo());
                        log.info("Autor: " + libro.getAutor().getNombre());
                        log.info("Área conocimiento: " + libro.getAreaConocimiento());
                        log.info("Copias disponibles: " + libro.getEjemplaresDisponibles());
                        log.info("-----");
                    }
                } else {
                    log.warn("No hay libros disponibles.");
                }
            } else if (respuestaLector.equals("2")) {

                log.info("Ingrese el nombre del autor: ");
                String nombreAutor = scan.nextLine();
                Optional<List<Libro>> librosOptional = servicioLibro.disponiblesPorAutor(nombreAutor);

                if (librosOptional.isPresent()) {
                    List<Libro> libros = librosOptional.get();

                    for (Libro libro : libros) {
                        log.info("\nTítulo: " + libro.getTitulo());
                        log.info("Autor: " + libro.getAutor().getNombre());
                        log.info("Área conocimiento: " + libro.getAreaConocimiento());
                        log.info("-----");
                    }
                } else {
                    log.warn("No hay libros disponibles con ese autor");
                }
            } else {
                log.error("Respuesta no válida, inténtalo de nuevo");
            }
        }
    }

    public static void listarNovelas () {
        while (true) {

            log.info(
                    "\n   1. Ver todas\n" +
                    "   2. Filtrar por autor" );

            String respuestaLector = scan.nextLine();
            log.info(respuestaLector);

            if (respuestaLector.equals("1")) {
                Optional<List<Novela>> novelasOptional = servicioNovela.todasDisponibles();

                if (novelasOptional.isPresent()) {

                    List<Novela> novelas = novelasOptional.get();

                    for (Novela novela : novelas) {
                        log.info("Título: " + novela.getTitulo());
                        log.info("Autor: " + novela.getAutor().getNombre());
                        log.info("Género: " + novela.getGenero());
                        log.info("Copias disponibles: " + novela.getEjemplaresDisponibles());
                        log.info("-----");
                    }
                } else {
                    log.warn("No hay novelas disponibles.");
                }
            } else if (respuestaLector.equals("2")) {

                log.info("Ingrese el nombre del autor: ");
                String nombreAutor = scan.nextLine();
                Optional<List<Novela>> novelasOptional = servicioNovela.disponiblesPorAutor(nombreAutor);

                if (novelasOptional.isPresent()) {
                    List<Novela> novelas = novelasOptional.get();

                    for (Novela novela : novelas) {
                        log.info("\nTítulo: " + novela.getTitulo());
                        log.info("Autor: " + novela.getAutor().getNombre());
                        log.info("Género: " + novela.getGenero());
                        log.info("-----");
                    }
                } else {
                    log.warn("No hay novelas disponibles con ese autor");
                }
            } else {
                log.error("Respuesta no válida, inténtalo de nuevo");
            }
        }
    }

    public static void hacerPrestamo(Usuario usuario) {

        List<Publicacion> publicacionesEnPrestamo = new ArrayList<>();

        log.info("Escribe el número de publicaciones que deseas prestar: ");
        int cantidadPublicaciones = ScannerUtil.pedirEntero();


        for (int i = 1; i <= cantidadPublicaciones; i++) {
            log.info("Ingresa el nombre de la pubicación " + i + ": ");
            String nombrePublicacion = scan.nextLine();

            Optional<? extends Publicacion> publicacionOptional = servicioLibro.disponiblePorNombreLibro(nombrePublicacion);

            if (publicacionOptional.isPresent()) {

                int copiasDisponibles = publicacionOptional.get().getEjemplaresDisponibles();

                log.info("Existen " + copiasDisponibles + " ejemplar(es)\n" +
                        "Ingresa la cantidad de ejemplares que deseas prestar: ");
                int cantidadParaPrestar = ScannerUtil.pedirEntero();

                while(cantidadParaPrestar > copiasDisponibles) {
                    log.warn("Has elegido una cantidad mayor a la disponible, escribe una cantidad válida: ");
                    cantidadParaPrestar = ScannerUtil.pedirEntero();
                }
                publicacionOptional.get().setEjemplaresDisponibles(copiasDisponibles - cantidadParaPrestar);
                publicacionesEnPrestamo.add(publicacionOptional.get());
            } else {
                log.info("No se pudo agregar el libro: No existe o no disponible");
            }
        }

        if(publicacionesEnPrestamo.size() == 0) {
            log.error("No se pudo hacer el préstamo: no se agregó ninguna publicación");
            return;
        }

        LocalDate fechaDeHoy = LocalDate.now();
        Prestamo nuevoPrestamo = new Prestamo(fechaDeHoy, fechaDeHoy.plusDays(15), usuario, publicacionesEnPrestamo);

        if(servicioPrestamo.guardarPrestamo(nuevoPrestamo)) {
            log.info("Préstamo realizado");
        }
        log.error("Ocurrió un error durante el préstamo");

    }

    public static void prestarNovelas () {


        while (true) {

            log.info("Escribe el nombre del libro que deseas prestar: ");
            String respuestaLector = scan.nextLine();
        }
    }

    public static void devolverLibros () {


        while (true) {

            log.info("Escribe el nombre del libro que deseas devolver: ");

            String respuestaLector = scan.nextLine();
        }
    }

    public static void devolverNovelas () {


        while (true) {

            log.info("Escribe el nombre del libro que deseas devolver: ");

            String respuestaLector = scan.nextLine();
        }
    }


}

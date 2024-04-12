package org.moreno.cristian.ui;

import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.servicios.ServicioLibro;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MenuLector {

    private static Scanner scan = new Scanner(System.in);
    private static ServicioLibro servicioLibro = new ServicioLibro();


    public static void home (Usuario lector) {

        System.out.println("Bienvenido de nuevo " + lector.getNombre() + "\n");

        while (true) {

            System.out.println("Qué desea hacer\n" +
                    "   1. Ver libros\n" +
                    "   2. Prestar libros\n" +
                    "   3. Devolver libros\n");

            String respuestaLector = scan.nextLine();

            switch(respuestaLector) {
                case "1":
                    listarLibros();
                    break;
                case "2":
                    prestarLibros();
                    break;
                default:
                    System.out.println("Ingresa una opción correcta");
            }
        }
    }

    public static void listarLibros () {


        while (true) {

            System.out.println(
                    "\n   1. Ver todos\n" +
                    "   2. Filtrar por autor" );

            String respuestaLector = scan.nextLine();
            System.out.println(respuestaLector);

            if (respuestaLector.equals("1")) {
                Optional<ArrayList<Libro>> librosOptional = servicioLibro.todosDisponibles();

                if (librosOptional.isPresent()) {

                    ArrayList<Libro> libros = librosOptional.get();

                    for (Libro libro : libros) {
                        System.out.println("Título: " + libro.getTitulo());
                        System.out.println("Autor: " + libro.getAutor().getNombre());
                        System.out.println("Área conocimiento: " + libro.getAreaConocimiento());
                        System.out.println("Copias disponibles: " + libro.getEjemplaresDisponibles());
                        System.out.println("-----");
                    }
                } else {
                    System.out.println("No hay libros disponibles.");
                }
            } else if (respuestaLector.equals("2")) {

                System.out.print("Ingrese el nombre del autor: ");
                String nombreAutor = scan.nextLine();
                Optional<ArrayList<Libro>> librosOptional = servicioLibro.disponiblesPorAutor(nombreAutor);

                if (librosOptional.isPresent()) {
                    ArrayList<Libro> libros = librosOptional.get();

                    for (Libro libro : libros) {
                        System.out.println("\nTítulo: " + libro.getTitulo());
                        System.out.println("Autor: " + libro.getAutor().getNombre());
                        System.out.println("Área conocimiento: " + libro.getAreaConocimiento());
                        System.out.println("-----");
                    }
                } else {
                    System.out.println("No hay libros disponibles con ese autor");
                }
            } else {
                System.out.println("Respuesta no válida, inténtalo de nuevo");
            }
        }
    }

    public static void prestarLibros () {


        while (true) {

            System.out.print("Escribe el nombre del libro que deseas prestar: ");
            String respuestaLector = scan.nextLine();
        }
    }

    public static void devolverLibros () {


        while (true) {

            System.out.print("Escribe el nombre del libro que deseas devolver: ");

            String respuestaLector = scan.nextLine();
        }
    }


}

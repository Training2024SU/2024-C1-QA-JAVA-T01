package org.moreno.cristian.ui.crudLibros;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.repositorios.RepositorioLibro;

import java.sql.Connection;
import java.util.Scanner;

public class CrearLibro {

    public static void crearLibro(Scanner scan, RepositorioLibro servicioLibro) {

        System.out.print("Ingresa el título del libro: ");
        String titulo = scan.nextLine();

        if (servicioLibro.libroPorNombre(titulo).isPresent()) {
            System.out.println("Ya existe un libro con este título ");
            return;
        }

        int numeroEjemplares =0;
        System.out.print("Ingresa el número de ejemplares: ");

        while (true) {
            try {
                numeroEjemplares = scan.nextInt();
                scan.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.");
                // Clear the scanner buffer
                scan.next(); // Read and discard the invalid input
            }

        }

        System.out.print("Ingresa el nombre del autor: ");
        String autor = scan.nextLine();


        int numeroPaginas =0;
        System.out.print("Ingresa el número de páginas: ");

        while (true) {
            try {
                numeroPaginas = scan.nextInt();
                scan.nextLine();
                System.out.print("You entered: " + numeroPaginas);
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.");
                // Clear the scanner buffer
                scan.next(); // Read and discard the invalid input
            }
            break;
        }

        System.out.print("Ingresa el área de conocimiento: ");
        String area = scan.nextLine();

        Libro nuevoLibro = new Libro(titulo, numeroEjemplares, 0, 0, new Autor(autor), numeroPaginas, AreaConocimiento.valueOf(area));

        if (servicioLibro.guardarLibro(nuevoLibro)) {
            System.out.println("Libro guardado");
        } else {
            System.out.println("Ocurrió un error");
        }
    }

}

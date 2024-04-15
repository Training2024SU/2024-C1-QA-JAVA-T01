package org.moreno.cristian.ui.crudLibros;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.repositorios.RepositorioAutor;
import org.moreno.cristian.repositorios.RepositorioLibro;

import java.util.Optional;
import java.util.Scanner;

public class ActualizarLibro {

    public static void actualizarLibro(Scanner scan, RepositorioLibro servicioLibro, RepositorioAutor servicioAutor) {

        System.out.print("Ingresa el título del libro actualizar: ");
        String titulo = scan.nextLine();

        Libro libroViejo = servicioLibro.libroPorNombre(titulo).get();

        if (!servicioLibro.libroPorNombre(titulo).isPresent()) {
            System.out.println("No existe un libro con este nombre ");
            return;
        }

        System.out.print("Ingresa el nuevo título del libro actualizar: ");
        String nuevoTitulo = scan.nextLine();

        int nuevoNumeroEjemplares =0;
        System.out.print("Ingresa el nuevo número de ejemplares: ");

        while (true) {
            try {
                nuevoNumeroEjemplares = scan.nextInt();
                scan.nextLine();
                System.out.print("You entered: " + nuevoNumeroEjemplares + "\n");
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.");
                // Clear the scanner buffer
                scan.next(); // Read and discard the invalid input
            }
            break;
        }

        System.out.print("Ingresa el nuevo nombre del autor: ");
        String nombreNuevoAutor = scan.nextLine();

        Autor nuevoAutor;
        Optional<Autor> autor = servicioAutor.autorPorNombre(nombreNuevoAutor);

        if(autor.isPresent()) {
            nuevoAutor = autor.get();
        } else {
            nuevoAutor = new Autor(nombreNuevoAutor);
        }


        int nuevoNumeroPaginas =0;
        System.out.print("Ingresa el número de páginas: ");

        while (true) {
            try {
                nuevoNumeroPaginas = scan.nextInt();
                scan.nextLine();
                System.out.print("You entered: " + nuevoNumeroPaginas);
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.");
                // Clear the scanner buffer
                scan.next(); // Read and discard the invalid input
            }
            break;
        }

        System.out.print("Ingresa el nuevo área de conocimiento: ");
        String nuevoArea = scan.nextLine();

        Libro libroActualizado = new Libro(libroViejo.getId(), nuevoTitulo, nuevoNumeroEjemplares, 0, 0, nuevoAutor, nuevoNumeroPaginas, AreaConocimiento.valueOf(nuevoArea));

        if (servicioLibro.actualizarLibro(libroActualizado)) {
            System.out.println("Libro actualizado");
        } else {
            System.out.println("Ocurrió un error");
        }
    }
}

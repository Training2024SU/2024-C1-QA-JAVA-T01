package org.moreno.cristian.ui.crudNovelas;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Novela;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.modelos.enums.Genero;
import org.moreno.cristian.repositorios.RepositorioAutor;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;

import java.util.Optional;
import java.util.Scanner;

public class ActualizarNovela {

    public static void actualizarNovela(Scanner scan, RepositorioNovela servicioNovela, RepositorioAutor servicioAutor) {

        System.out.print("Ingresa el título de la novela a actualizar: ");
        String titulo = scan.nextLine();

        Optional<Novela> novelaOptional = servicioNovela.novelaPorNombre(titulo);

        if (!novelaOptional.isPresent()) {
            System.out.println("No existe una novela con este nombre ");
            return;
        }
        Novela novelaVieja = novelaOptional.get();

        System.out.print("Ingresa el nuevo título de la novela a actualizar: ");
        String nuevoTitulo = scan.nextLine();

        int nuevoNumeroEjemplares =0;
        System.out.print("Ingresa el nuevo número de ejemplares: ");

        while (true) {
            try {
                nuevoNumeroEjemplares = scan.nextInt();
                scan.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer: ");
                // Clear the scanner buffer
                scan.next(); // Read and discard the invalid input
            }
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


        int nuevaEdadLectura =0;
        System.out.print("Ingresa la nueva edad de lectura: ");

        while (true) {
            try {
                nuevaEdadLectura = scan.nextInt();
                scan.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.");
                // Clear the scanner buffer
                scan.next(); // Read and discard the invalid input
            }
        }

        System.out.print("Ingresa el nuevo género: ");
        String nuevoGenero = scan.nextLine();

        Novela novelaActualizada = new Novela(novelaVieja.getId(), nuevoTitulo, nuevoNumeroEjemplares, 0, 0, nuevoAutor, nuevaEdadLectura, Genero.valueOf(nuevoGenero));

        if (servicioNovela.actualizarNovela(novelaActualizada)) {
            System.out.println("Libro actualizado");
        } else {
            System.out.println("Ocurrió un error");
        }
    }
}

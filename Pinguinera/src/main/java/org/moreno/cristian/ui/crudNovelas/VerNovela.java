package org.moreno.cristian.ui.crudNovelas;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Novela;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.modelos.enums.Genero;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;

import java.util.Scanner;

public class VerNovela {

    public static void verNovela(Scanner scan, RepositorioNovela servicioNovela) {

        System.out.print("Ingresa el título de la novela: ");
        String titulo = scan.nextLine();

        if (servicioNovela.novelaPorNombre(titulo).isEmpty()) {
            System.out.println("Novela no encontrada ");
            return;
        }

        int numeroEjemplares =0;
        System.out.print("Ingresa el número de ejemplares: ");

        while (true) {
            try {
                numeroEjemplares = scan.nextInt();
                scan.nextLine();

            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.");
                // Clear the scanner buffer
                scan.next(); // Read and discard the invalid input
            }
            if (numeroEjemplares <= 0) {
                System.out.println("El número de ejemplares deber ser mayor que cero");
            } else {
                break;
            }

        }

        System.out.print("Ingresa el nombre del autor: ");
        String autor = scan.nextLine();


        int edadLectura =0;
        System.out.print("Ingresa el número de páginas: ");

        while (true) {
            try {
                edadLectura = scan.nextInt();
                scan.nextLine();
                System.out.print("You entered: " + edadLectura);
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.");
                // Clear the scanner buffer
                scan.next(); // Read and discard the invalid input
            }
            break;
        }

        System.out.print("Ingresa el área de conocimiento: ");
        String area = scan.nextLine();

        Novela nuevaNovela = new Novela(titulo, numeroEjemplares, 0, 0, new Autor(autor), edadLectura, Genero.valueOf(area));

        if (servicioNovela.guardarNovela(nuevaNovela)) {
            System.out.println("Novela guardado");
        } else {
            System.out.println("Ocurrió un error");
        }
    }
}

package org.moreno.cristian.ui.crudNovelas;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Novela;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.modelos.enums.Genero;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;

import java.util.Scanner;

public class CrearNovela {

    public static void crearNovela(Scanner scan, RepositorioNovela servicioNovela) {

        System.out.print("Ingresa el título de la novela: ");
        String titulo = scan.nextLine();

        if (servicioNovela.novelaPorNombre(titulo).isPresent()) {
            System.out.println("Ya existe una novela con este título ");
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


        int edadLectura =0;
        System.out.print("Ingresa la edad recomendada de lectura: ");

        while (true) {
            try {
                edadLectura = scan.nextInt();
                scan.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.");
                // Clear the scanner buffer
                scan.next(); // Read and discard the invalid input
            }

        }

        System.out.print("Ingresa el género: ");
        String genero = scan.nextLine();

        Novela nuevoLibro = new Novela(titulo, numeroEjemplares, 0, 0, new Autor(autor), edadLectura, Genero.valueOf(genero));

        if (servicioNovela.guardarNovela(nuevoLibro)) {
            System.out.println("Novela guardada");
        } else {
            System.out.println("Ocurrió un error");
        }
    }
}

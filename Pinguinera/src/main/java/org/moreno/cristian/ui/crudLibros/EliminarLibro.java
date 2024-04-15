package org.moreno.cristian.ui.crudLibros;

import org.moreno.cristian.repositorios.RepositorioLibro;

import java.util.Scanner;

public class EliminarLibro {

    public static void eliminarLibro (Scanner scan, RepositorioLibro servicioLibro) {

        System.out.print("Ingresa el nombre del libro: ");
        String nombreLibro = scan.nextLine();

        if (servicioLibro.eliminarLibro(nombreLibro)) {
            System.out.println("Libro eliminado");
        } else {
            System.out.println("Libro no encontrado");
        }
    }
}

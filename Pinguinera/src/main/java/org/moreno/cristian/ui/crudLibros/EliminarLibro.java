package org.moreno.cristian.ui.crudLibros;

import org.moreno.cristian.repositorios.RepositorioLibro;

import java.util.Scanner;

public class EliminarLibro {

    public static void eliminarLibro (Scanner scan, RepositorioLibro servicioLibro) {

        System.out.print("Ingresa el id del libro: ");
        String libroId = scan.nextLine();

        if (servicioLibro.eliminarLibro(libroId)) {
            System.out.println("Libro eliminado");
        } else {
            System.out.println("Libro no encontrado");
        }
    }
}

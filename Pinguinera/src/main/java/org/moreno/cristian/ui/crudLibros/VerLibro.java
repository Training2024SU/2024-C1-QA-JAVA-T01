package org.moreno.cristian.ui.crudLibros;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.repositorios.RepositorioLibro;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VerLibro {

    public static void verLibro(Scanner scan, RepositorioLibro servicioLibro) {

        Optional<List<Libro>> librosOptional = servicioLibro.todosLibros();

        if (librosOptional.isPresent()) {

            List<Libro> libros = librosOptional.get();

            for (Libro libro : libros) {
                System.out.println("Id: " + libro.getId());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Área conocimiento: " + libro.getAreaConocimiento());
                System.out.println("Copias disponibles: " + libro.getEjemplaresDisponibles());
                System.out.println("-----");
            }
        } else {
            System.out.println("No hay libros");
        }

    }

}

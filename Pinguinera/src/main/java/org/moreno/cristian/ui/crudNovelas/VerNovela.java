package org.moreno.cristian.ui.crudNovelas;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Novela;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.modelos.enums.Genero;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VerNovela {

    public static void verNovela(Scanner scan, RepositorioNovela servicioNovela) {

        Optional<List<Novela>> novelasOptional = servicioNovela.todasNovelas();

        if (novelasOptional.isPresent()) {

            List<Novela> novelas = novelasOptional.get();

            for (Novela novela : novelas) {
                System.out.println("Id: " + novela.getId());
                System.out.println("Título: " + novela.getTitulo());
                System.out.println("Autor: " + novela.getAutor().getNombre());
                System.out.println("Área conocimiento: " + novela.getGenero());
                System.out.println("Copias disponibles: " + novela.getEjemplaresDisponibles());
                System.out.println("-----");
            }
        } else {
            System.out.println("No hay novelas");
        }
    }
}

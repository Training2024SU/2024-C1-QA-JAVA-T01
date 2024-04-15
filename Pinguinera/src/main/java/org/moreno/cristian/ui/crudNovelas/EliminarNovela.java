package org.moreno.cristian.ui.crudNovelas;

import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioNovela;

import java.util.Scanner;

public class EliminarNovela {

    public static void eliminarNovela (Scanner scan, RepositorioNovela servicioNovela) {

        System.out.print("Ingresa el id de la novela: ");
        String novelaId = scan.nextLine();

        if (servicioNovela.eliminarNovela(novelaId)) {
            System.out.println("Novela eliminada");
        } else {
            System.out.println("Novela no encontrada");
        }
    }
}

package co.com.sofka.menu;

import co.com.sofka.servicio.ServicioNovela;

import java.util.Scanner;

import static co.com.sofka.menu.ConstantesMenu.PRIMER_MENSAJE_BUSCAR_NOVELA_POR_AUTOR;
import static co.com.sofka.menu.ConstantesMenu.PRIMER_MENSAJE_MODIFICAR_NOVELA;

public class MetodosMenuAsistenteNovela {
    public Scanner scanner;
    public ServicioNovela servicioNovela;

    public MetodosMenuAsistenteNovela(Scanner scanner, ServicioNovela servicioNovela) {
        this.scanner = scanner;
        this.servicioNovela = servicioNovela;
    }

    public void guardarNovela(){
        System.out.println(ConstantesMenu.PRIMER_MENSAJE_GUARDAR_NOVELA);
        String titulo = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_GUARDAR_NOVELA);
        String autor = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_GUARDAR_NOVELA);
        int cantidadEjemplares = scanner.nextInt();
        scanner.nextLine();

        System.out.println(ConstantesMenu.CUARTO_MENSAJE_GUARDAR_NOVELA);
        String genero = scanner.nextLine();

        System.out.println(ConstantesMenu.QUINTO_MENSAJE_GUARDAR_NOVELA);
        int edadDeLecturaSugerida = scanner.nextInt();
        scanner.nextLine();

        servicioNovela.guardarNovela(titulo, autor, cantidadEjemplares,genero, edadDeLecturaSugerida);
    }

    public void buscarNovelaPorAutor(){
        System.out.println(PRIMER_MENSAJE_BUSCAR_NOVELA_POR_AUTOR);
        String autor = scanner.nextLine();

        servicioNovela.listarNovelaPorAutor(autor);
    }

    public void modificarNovela(){
        System.out.println(PRIMER_MENSAJE_MODIFICAR_NOVELA);
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println(ConstantesMenu.PRIMER_MENSAJE_GUARDAR_NOVELA);
        String titulo = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_GUARDAR_NOVELA);
        String autor = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_GUARDAR_NOVELA);
        int cantidadEjemplares = scanner.nextInt();
        scanner.nextLine();

        System.out.println(ConstantesMenu.CUARTO_MENSAJE_GUARDAR_NOVELA);
        String genero = scanner.nextLine();

        System.out.println(ConstantesMenu.QUINTO_MENSAJE_GUARDAR_NOVELA);
        int edadDeLecturaSugerida = scanner.nextInt();
        scanner.nextLine();

        servicioNovela.modificarNovela(id, titulo, autor, cantidadEjemplares, genero, edadDeLecturaSugerida);
    }

    public void borrarNovela(){
        System.out.println("Ingresar el id de la novela a borrar");
        Long novelaId = scanner.nextLong();
        scanner.nextLine();

        servicioNovela.borrarNovelaPorId(novelaId);
    }
}

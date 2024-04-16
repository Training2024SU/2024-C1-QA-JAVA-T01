package co.com.sofka.menu;

import co.com.sofka.ExportarLibros;
import co.com.sofka.servicio.ServicioLibro;

import java.util.Scanner;

import static co.com.sofka.menu.ConstantesMenu.PRIMER_MENSAJE_BUSCAR_LIBRO_POR_AUTOR;
import static co.com.sofka.menu.ConstantesMenu.PRIMER_MENSAJE_MODIFICAR_LIBRO;

public class MetodosMenuAsistenteLibro {

    public Scanner scanner;

    public ServicioLibro servicioLibro;

    public ExportarLibros exportarLibros;

    public MetodosMenuAsistenteLibro(Scanner scanner, ServicioLibro servicioLibro, ExportarLibros exportarLibros) {
        this.scanner = scanner;
        this.servicioLibro = servicioLibro;
        this.exportarLibros = exportarLibros;
    }

    public void guardarLibro(){
        System.out.println(ConstantesMenu.PRIMER_MENSAJE_GUARDAR_LIBRO);
        String titulo = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_GUARDAR_LIBRO);
        String autor = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_GUARDAR_LIBRO);
        int cantidadEjemplares = scanner.nextInt();
        scanner.nextLine();

        System.out.println(ConstantesMenu.CUARTO_MENSAJE_GUARDAR_LIBRO);
        String areaDelConocimiento = scanner.nextLine();

        System.out.println(ConstantesMenu.QUINTO_MENSAJE_GUARDAR_LIBRO);
        int numeroDePaginas = scanner.nextInt();
        scanner.nextLine();

        servicioLibro.guardarLibro(titulo, autor, cantidadEjemplares, areaDelConocimiento, numeroDePaginas);
    }

    public void buscarLibroPorAutor(){
        System.out.println(PRIMER_MENSAJE_BUSCAR_LIBRO_POR_AUTOR);
        String autor = scanner.nextLine();

        servicioLibro.listarLibroPorAutor(autor);
    }

    public void modificarLibro(){
        System.out.println(PRIMER_MENSAJE_MODIFICAR_LIBRO);
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println(ConstantesMenu.PRIMER_MENSAJE_GUARDAR_LIBRO);
        String titulo = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_GUARDAR_LIBRO);
        String autor = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_GUARDAR_LIBRO);
        int cantidadEjemplares = scanner.nextInt();
        scanner.nextLine();

        System.out.println(ConstantesMenu.CUARTO_MENSAJE_GUARDAR_LIBRO);
        String areaDelConocimiento = scanner.nextLine();

        System.out.println(ConstantesMenu.QUINTO_MENSAJE_GUARDAR_LIBRO);
        int numeroDePaginas = scanner.nextInt();
        scanner.nextLine();

        servicioLibro.modificarLibro(id, titulo, autor, cantidadEjemplares, areaDelConocimiento, numeroDePaginas);
    }

    public void borrarLibro(){
        System.out.println("Ingresar el id del libro a borrar");
        Long libroId = scanner.nextLong();
        scanner.nextLine();

        servicioLibro.borrarLibroPorId(libroId);
    }

    public void exportarAExcel(){
        String filePath = "C:\\Users\\User\\OneDrive\\Documents\\sofka\\EntregasSubidasAGitHub\\2024-C1-QA-JAVA-T01\\solucion\\biblioteca-la-pinguinera\\src\\main\\resources";
        exportarLibros.exportToExcel(filePath);
    }

    public void leerExcel(){
        String filePath = "C:\\Users\\User\\OneDrive\\Documents\\sofka\\EntregasSubidasAGitHub\\2024-C1-QA-JAVA-T01\\solucion\\biblioteca-la-pinguinera\\src\\main\\resources\\libros-disponibles.csv";

        System.out.println("Leyendo archivos en " + filePath);

        exportarLibros.leerArchivo(filePath);

        System.out.println("Lectura archivo completada");
    }

}

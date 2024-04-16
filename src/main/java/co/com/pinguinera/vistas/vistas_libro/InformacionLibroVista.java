package co.com.pinguinera.vistas.vistas_libro;

import java.util.Scanner;

public class InformacionLibroVista {

    private final Scanner scanner;

    public InformacionLibroVista() {
        this.scanner = new Scanner(System.in);
    }

    public String pedirTituloLibro() {
        System.out.print("Ingrese el título del libro: ");
        return scanner.nextLine();
    }

    public String pedirAutorLibro() {
        System.out.print("Ingrese el autor del libro: ");
        return scanner.nextLine();
    }

    public int pedirNumPaginas() {
        System.out.print("Ingrese el número de páginas del libro: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirCantEjemplares() {
        System.out.print("Ingrese la cantidad de ejemplares del libro: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirCantPrestados() {
        System.out.print("Ingrese la cantidad de libros prestados: ");
        return Integer.parseInt(scanner.nextLine());
    }
}

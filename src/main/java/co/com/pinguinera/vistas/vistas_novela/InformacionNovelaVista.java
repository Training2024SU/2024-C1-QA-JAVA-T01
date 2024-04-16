package co.com.pinguinera.vistas.vista_novela;

import java.util.Scanner;

public class InformacionNovelaVista {

    private final Scanner scanner;

    public InformacionNovelaVista() {
        this.scanner = new Scanner(System.in);
    }

    public String pedirTituloNovela() {
        System.out.print("Ingrese el título de la novela: ");
        return scanner.nextLine();
    }

    public String pedirAutorNovela() {
        System.out.print("Ingrese el autor de la novela: ");
        return scanner.nextLine();
    }

    public int pedirNumPaginas() {
        System.out.print("Ingrese el número de páginas de la novela: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirCantEjemplares() {
        System.out.print("Ingrese la cantidad de ejemplares de la novela: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirCantPrestados() {
        System.out.print("Ingrese la cantidad de novelas prestadas: ");
        return Integer.parseInt(scanner.nextLine());
    }
}


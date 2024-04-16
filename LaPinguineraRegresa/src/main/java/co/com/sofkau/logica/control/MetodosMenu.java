package co.com.sofkau.logica.control;

import co.com.sofkau.dialogo.Menus.MenuPrincipal;
import co.com.sofkau.model.Publicacion;

import java.sql.SQLException;
import java.util.Scanner;

import static co.com.sofkau.dialogo.ConstantesMenu.MSN_PRINCIPAL_1;

public class MetodosMenu {

    /*public static int preguntarNumeroUsuario2() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        System.out.println(MSN_PRINCIPAL_1);

        String input = scanner.nextLine();

        if (input.matches("[0-9]+")) {
            opcion = Integer.parseInt(input);

        } else {
            System.out.println("Ingrese un número válido.");
        }

        return opcion;
    }*/


    public static String preguntarStringAlUsuario(String mensaje) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print(mensaje);
            return scanner.nextLine();
        }catch (Exception e) {
            System.out.println("error debido a " + e);
        }
        return "null";
    }

    public static int preguntarNumeroUsuario() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(MSN_PRINCIPAL_1);
        int option;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            option = 0;
            System.out.println("Ingrese un número válido");
            return -1; // Regresar -1 para indicar que se debe volver al menú principal
        }
        return option;
    }


}

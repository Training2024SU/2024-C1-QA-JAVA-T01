package Garcia.Juan.logica;

import Garcia.Juan.database.mysql.MySqlOperation;

import java.sql.SQLException;

import static Garcia.Juan.logica.MetodosUsuario.*;
import static Garcia.Juan.dialogo.Menu.*;


import java.text.ParseException;
import java.util.Scanner;

public class MetodosMain {

    private static MySqlOperation mySqlOperation;

    public MetodosMain(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static void iniciarPrograma(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        int option;
        boolean ciclo = true;
        while (ciclo){
            menuInicial();
            option=pedirOpcion();
            ciclo = seleccionarOpciones(option,ciclo);
        }
    }
    public static int pedirOpcion() {
        Scanner scanner = new Scanner(System.in);
        int option;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            option = 0;
            System.out.println("Error por la razon " + e.getMessage());
        }
        return option;
    }

    public static boolean seleccionarOpciones(int option, boolean ciclo) throws SQLException, ParseException {
        switch (option) {
            case 1:
                iniciarSesion(mySqlOperation);
                break;
            case 2:
                registrarUsuario(mySqlOperation,0);
                break;
            case 3:
                ciclo = false;
                break;
            default:
                errorOpcion();
        }
        return ciclo;
    }
}

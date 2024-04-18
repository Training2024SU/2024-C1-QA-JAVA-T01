package com.sofka.menu;

import java.sql.SQLException;

import static com.sofka.constants.MenuConstants.MENU_ADMINISTRADOR;
import static com.sofka.controllers.ControlEmpleado.*;
import static com.sofka.controllers.ControlMenu.*;
import static com.sofka.controllers.ControlPrestamo.*;
import static com.sofka.controllers.ControlPublicacion.*;
import static com.sofka.controllers.ControlStament.insertarLista;
import static com.sofka.controllers.ControlUsuario.*;

public class menuDeAdministrador {


    public static void menuAdministrador() throws NumberFormatException, SQLException {
        String opcion = pedirEntrada(MENU_ADMINISTRADOR);

        ejecutarMenuAdministrador(opcion);
    }

    private static void ejecutarMenuAdministrador(String opcion) throws SQLException {
        switch (opcion) {
            case "1":
                registrarNuevoUsuario();
                break;
            case "2":
                registrarNuevoEmpleado("ADMINISTRADOR");
                break;
            case "3":
                registrarNuevoEmpleado("ASISTENTE");
                break;
            case "4":
                insertarLista(registrarLibro());
                break;
            case "5":
                insertarLista(registrarNovela());
                break;
            case "6":
                do{
                    solicitarPrestamo();
                } while (!(preguntarSalir().equals("s")));
                break;
            case "7":
                selectAllFromUsuario();
                break;
            case "8":
                selectAllFromLibro();
                break;
            case "9":
                selectAllFromNovela();
                break;
            case "10":
                selectAllFromPrestamo();
                break;
            case "11":
                selectAllFromEmpleado();
                break;
            case "12":
                insertarUsuarioFaker(preguntarCantidadAlUsuario());
                break;
            case "13":
                insertarLibrosFaker(preguntarCantidadAlUsuario());
                break;
            case "14":
                insertarNovelaFaker(preguntarCantidadAlUsuario());
                break;
            case "15":
                insertarPrestamoFaker(preguntarCantidadAlUsuario());
                break;
            case "16":
                insertarEmpleadoFaker(preguntarCantidadAlUsuario());
                break;
            case "0":
                mostrarMensaje("¡Cerrando Sesion!");
                break;
            default:
                mostrarMensaje("Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("0"))) menuAdministrador();
    }

}

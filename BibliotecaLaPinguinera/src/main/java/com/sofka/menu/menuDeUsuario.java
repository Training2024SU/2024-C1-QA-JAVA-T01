package com.sofka.menu;

import java.sql.SQLException;

import static com.sofka.constants.MenuConstants.MENU_USUARIO;
import static com.sofka.controllers.ControlFuncionesEspeciales.buscarPublicacionPorAutor;
import static com.sofka.controllers.ControlMenu.*;
import static com.sofka.controllers.ControlPrestamo.selectAllFromPrestamo;
import static com.sofka.controllers.ControlPrestamo.solicitarPrestamo;
import static com.sofka.controllers.ControlPublicacion.*;

public class menuDeUsuario {

    public static void menuUsuario() throws SQLException {
        String opcion = pedirEntrada(MENU_USUARIO);
        ejecutarMenuUsuario(opcion);
    }

    private static void ejecutarMenuUsuario(String opcion) throws SQLException {
        switch (opcion) {
            case "1":
                buscarPublicacionPorAutor();
                break;
            case "2":
                do{
                    solicitarPrestamo();
                } while (!(preguntarSalir().equals("s")));
                break;
            case "3":
                selectAllFromPrestamo();
                break;
            case "4":
                selectAllFromLibro();
                break;
            case "5":
                selectAllFromNovela();
                break;
            case "6":
                selectAllFromPublicacion();
                break;
            case "0":
                mostrarMensaje("¡Cerrando Sesión!");
                break;
            default:
                mostrarMensaje("Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("0"))) menuUsuario();
    }
}

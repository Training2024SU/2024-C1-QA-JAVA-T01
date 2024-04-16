package com.sofka.menu;

import java.sql.SQLException;

import static com.sofka.BibliotecaLaPinguinera.*;
import static com.sofka.constants.MenuConstants.MENU_INICIO;
import static com.sofka.controllers.ControlMenu.mostrarMensaje;
import static com.sofka.controllers.ControlMenu.pedirEntrada;
import static com.sofka.controllers.ControlPrestamo.selectAllFromPrestamo;
import static com.sofka.controllers.ControlPublicacion.*;
import static com.sofka.controllers.ControlUsuario.registrarNuevoUsuario;
import static com.sofka.menu.menuDeInicioSesion.menuInicioSesionComo;

public class menuDeInicio {
    public static void menuInicio() throws NumberFormatException, SQLException, NullPointerException {
        String opcion = (pedirEntrada(MENU_INICIO));
        ejecutarMenuInicio(opcion);
    }

    private static void ejecutarMenuInicio(String opcion) throws SQLException {
        switch (opcion) {
            case "1":
                menuInicioSesionComo();
                break;
            case "2":
                selectAllFromLibro();
                break;
            case "3":
                selectAllFromNovela();
                break;
            case "4":
                selectAllFromPublicacion();
                break;
            case "5":
                selectAllFromPrestamo();
                break;
            case "6":
                registrarNuevoUsuario();
                break;
            case "0":
                mostrarMensaje("¡OjO, Va a salir de la Biblioteca!");
                break;
            default:
                mostrarMensaje(SELECCIONE_CORRECTAMENTE);

        }
        if (!(opcion.equals("0"))) menuInicio();
    }
}

package com.sofka.menu;

import java.sql.SQLException;

import static com.sofka.constants.MenuConstants.MENU_ASISTENTE;
import static com.sofka.controllers.ControlFuncionesEspeciales.buscarPrestamoPorCorreo;
import static com.sofka.controllers.ControlFuncionesEspeciales.buscarPublicacionPorAutor;
import static com.sofka.controllers.ControlMenu.*;
import static com.sofka.controllers.ControlPrestamo.*;
import static com.sofka.controllers.ControlPublicacion.*;
import static com.sofka.controllers.ControlStament.insertIntoBd;
import static com.sofka.controllers.ControlStament.insertarLista;
import static com.sofka.controllers.ControlUsuario.registrarNuevoUsuario;

public class menuDeAsistente {

    public static void menuAsistente() throws SQLException {
        String opcion = (pedirEntrada(MENU_ASISTENTE));
        ejecutarMenuAsistente(opcion);
    }

    private static void ejecutarMenuAsistente(String opcion) throws SQLException {
        switch (opcion) {
            case "1":
                buscarPublicacionPorAutor();
                break;
            case "2":
                insertIntoBd(registrarLibro().get(0));
                insertIntoBd(registrarLibro().get(1));
                break;
            case "3":
                insertIntoBd(registrarNovela().get(0));
                insertIntoBd(registrarNovela().get(1));
                insertIntoBd(registrarNovela().get(2));
                break;
            case "4":
                buscarPrestamoPorCorreo();
                break;
            case "5":
                do {
                    solicitarPrestamo();
                } while (!(preguntarSalir().equals("s")));
                break;
            case "6":
                insertarLista(realizarPrestamo());
                break;
            case "7":
                insertarLista(finalizarPrestamo());
                break;
            case "8":
                selectAllFromPrestamo();
                break;
            case "9":
                selectAllFromLibro();
                break;
            case "10":
                selectAllFromNovela();
                break;
            case "11":
                registrarNuevoUsuario();
                break;
            case "0":
                mostrarMensaje("¡Cerrando Sesión!");
                break;
            default:
                mostrarMensaje("Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("0"))) menuAsistente();
    }
}

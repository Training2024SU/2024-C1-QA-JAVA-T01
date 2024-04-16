package com.sofka.menu;

import java.sql.SQLException;

import static com.sofka.controllers.ControlMenu.*;

public class menuDeInicioSesion {


    public static void menuInicioSesionComo() throws NumberFormatException, SQLException {
        String opcion = (pedirEntrada(
                "Iniciar Sesión o Registrarse Como: \n\n" +
                        "1. Administrador\n" +
                        "2. Asistente\n" +
                        "3. Lector\n" +
                        "0. Atras"));

        switch (opcion) {
            case "1", "2", "3":
                iniciarSesion(opcion);
                break;
            case "0":
                break;
            default:
                mostrarMensaje("Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("0"))) menuInicioSesionComo();
    }

}

package com.sofka.controllers;

import static com.sofka.constants.InsertConstants.INSERT_EDADSUGERIDA;

public class ControlEdadSugerida {

    public static String crearEdadSugerida(String titulo, String edadSugerida) {
        return (String.format(INSERT_EDADSUGERIDA, titulo, edadSugerida));
    }
}

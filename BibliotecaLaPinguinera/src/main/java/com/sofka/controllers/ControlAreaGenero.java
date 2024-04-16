package com.sofka.controllers;

import static com.sofka.constants.InsertConstants.INSERT_AREAGENERO;

public class ControlAreaGenero {

    public static String crearAreaGenero(String titulo, String areaGenero) {
        return (String.format(INSERT_AREAGENERO, titulo, areaGenero));
    }

}

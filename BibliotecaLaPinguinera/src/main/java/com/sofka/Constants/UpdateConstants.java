package com.sofka.Constants;

public class UpdateConstants {
    public static final String UPDATE_PUBLICACION = "UPDATE `publicacion` SET `cantPrestados` = '%s', `cantDisponibles` = '%s' WHERE (`titulo` = '%s')";
    public static final String UPDATE_PRESTAMO = "UPDATE prestamo SET `estado` = '%s' WHERE (`idPrestamo` = '%s')";
}

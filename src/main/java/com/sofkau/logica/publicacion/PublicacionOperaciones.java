package com.sofkau.logica.publicacion;

import com.github.javafaker.Faker;

import java.util.Locale;

import static com.sofkau.integration.database.mysql.Constantes.INSERT_LIBRO;

public class PublicacionOperaciones {




    public static String crearLibro() {
        String id;
        String nombre;
        String numeroHojas;
        String editorial;
        String sentencia;
        Faker faker = new Faker(new Locale("es"));
        id = faker.bothify("####???##");
        nombre = (faker.name().name()).replace("'", "");
        numeroHojas = faker.bothify("###");
        editorial = (faker.book().publisher()).replace("'", "");
        sentencia = String.format(INSERT_LIBRO, id, nombre, numeroHojas, editorial);
        return sentencia;
    }
}

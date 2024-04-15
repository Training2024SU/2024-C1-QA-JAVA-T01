package com.sofkau.logica.publicacion;

import com.github.javafaker.Faker;
import com.sofkau.integration.repositorio.PublicacionRepositorio;
import com.sofkau.model.Publicacion;
import com.sofkau.util.enums.TipoPublicacion;

import java.util.HashMap;
import java.util.Locale;

import static com.sofkau.integration.database.mysql.Constantes.INSERT_LIBRO;

public class PublicacionOperaciones {


    private static HashMap<String, Publicacion> publiciones = new HashMap<>();
    public void registrarPublicacion(Publicacion publicacion, TipoPublicacion tipo) {

        publicacion.setTipo(tipo.toString());
        publicacion.setCantidadDisponible(publicacion.getCantidadEjemplares()- publicacion.getCantidadPrestado());

        // Crear la publicación en la base de datos
        PublicacionRepositorio.crearPublicacion(publicacion);

        // Agregar la publicación al HashMap de publicaciones
         publiciones.put(publicacion.getTitulo(), publicacion);

        System.out.println("Libro creado correctamente: "+publicacion);

    }



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

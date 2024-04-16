package com.sofkau.logica.publicacion;

import com.github.javafaker.Faker;
import com.sofkau.integration.repositorio.PublicacionRepositorio;
import com.sofkau.model.AreaGenero;
import com.sofkau.model.Autor;
import com.sofkau.model.EdadSugerida;
import com.sofkau.model.Publicacion;
import com.sofkau.util.enums.TipoPublicacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static com.sofkau.integration.database.mysql.Constantes.INSERT_LIBRO;

public class PublicacionOperaciones {


    private static HashMap<String, Publicacion> publicaciones = new HashMap<>();

    private AreaGeneroOperaciones areaGeneroOperaciones = new AreaGeneroOperaciones();

    private EdadSugeridaOperaciones edadSugeridaOperaciones = new EdadSugeridaOperaciones();


    public void registrarPublicacion(Publicacion publicacion, TipoPublicacion tipo) {

        publicacion.setTipo(tipo.toString());
        publicacion.setCantidadDisponible(publicacion.getCantidadEjemplares()- publicacion.getCantidadPrestado());

        // Crear la publicación en la base de datos
        PublicacionRepositorio.crearPublicacion(publicacion);

        // Agregar la publicación al HashMap de publicaciones
         publicaciones.put(publicacion.getTitulo(), publicacion);

        System.out.println("Libro creado correctamente: "+publicacion);

    }

    private Publicacion relacionarAreasGenero(Publicacion publicacion) {
        // Obtener las áreas de género para esta publicación
        ArrayList<AreaGenero> areasGenero = areaGeneroOperaciones.getAreaGeneroPorIdTitulo(publicacion.getTitulo());

        // Asignar las áreas de género a la publicación
        publicacion.setAreas(areasGenero);

        return publicacion;
    }

    private Publicacion relacionarEdadesSugeridas(Publicacion publicacion) {
        // Obtener las edades sugeridas para esta publicación
        ArrayList<EdadSugerida> edadesSugeridas = edadSugeridaOperaciones.getEdadesSugeridasPorIdTitulo(publicacion.getTitulo());

        // Asignar las edades sugeridas a la publicación
        publicacion.setEdades(edadesSugeridas);

        return publicacion;
    }

    private void getPublicaciones() {
        publicaciones = PublicacionRepositorio.consultarPublicaciones();
        for (Publicacion publicacion : publicaciones.values()) {
           publicaciones.put(publicacion.getTitulo(),relacionarAreasGenero(publicacion));
            publicaciones.put(publicacion.getTitulo(),relacionarEdadesSugeridas(publicacion));
        }
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

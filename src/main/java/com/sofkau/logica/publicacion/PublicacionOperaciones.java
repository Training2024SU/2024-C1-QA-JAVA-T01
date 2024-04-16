package com.sofkau.logica.publicacion;

import com.github.javafaker.Faker;
import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.dialogo.Menu;
import com.sofkau.integration.repositorio.AreaGeneroRepositorio;
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

    public PublicacionOperaciones() {
        getPublicaciones();
    }


    // Se hace una sobrecarga de metodos para registrar según sea el tipo de publicacion

    // Se crea un libro
    public void registrarPublicacion(Publicacion publicacion, AreaGenero areaGenero) {

        publicacion.setCantidadDisponible(publicacion.getCantidadEjemplares() - publicacion.getCantidadPrestado());

        // Crear el libro en la base de datos
        PublicacionRepositorio.crearPublicacion(publicacion);

        //Crear Area genero en la base de datos
        areaGeneroOperaciones.crearAreaGenero(areaGenero);

        // Agregar el libro al HashMap de publicaciones
         publicaciones.put(publicacion.getTitulo(),relacionarAreasGenero(publicacion));

        System.out.println("Libro creado correctamente: "+publicacion);


    }

    // Se crea una novela
    public void registrarPublicacion(Publicacion publicacion, AreaGenero areaGenero, EdadSugerida edadSugerida) {

        publicacion.setCantidadDisponible(publicacion.getCantidadEjemplares()- publicacion.getCantidadPrestado());

        // Crear el libro en la base de datos
        PublicacionRepositorio.crearPublicacion(publicacion);

        //Crear Area genero en la base de datos
        areaGeneroOperaciones.crearAreaGenero(areaGenero);

        // Crear EdadSugeridad en la base de datos
        edadSugeridaOperaciones.crearEdadSugerida(edadSugerida);

        // Agregar la novela al HashMap de publicaciones
        publicaciones.put(publicacion.getTitulo(),relacionarAreasGenero(publicacion));
        publicaciones.put(publicacion.getTitulo(),relacionarEdadesSugeridas(publicacion));

        MensajeOperacionBd.crearNovela();

    }

    //Se actualiza el libro

    public void actualizarPublicacion(Publicacion publicacion, AreaGenero areaGenero, String antiguoTitulo) {

        publicacion.setCantidadDisponible(publicacion.getCantidadEjemplares() - publicacion.getCantidadPrestado());

        // Se busca la publicacion anterior con el titulo sin actualizar
        Publicacion publicacionPorActualizar = publicaciones.get(antiguoTitulo);

        if(publicacionPorActualizar != null){
            // Actualizar el libro en la base de datos
            PublicacionRepositorio.actualizarPublicacion(antiguoTitulo,publicacion);

            //Actualizar area de conocimineto en la base de datos
            areaGeneroOperaciones.actualizarAreaGenero(antiguoTitulo,areaGenero);

            // Agregar el nuevo libro actualizado al HashMap de publicaciones
            publicaciones.remove(antiguoTitulo);
            publicaciones.put(publicacion.getTitulo(),publicacion);
            publicaciones.put(publicacion.getTitulo(),relacionarAreasGenero(publicacion));
            publicaciones.put(publicacion.getTitulo(),relacionarEdadesSugeridas(publicacion));

            MensajeOperacionBd.actualizarLibroExitoso();
        }else{
            MensajeOperacionBd.errorActualizarLibro();
        }

    }


    // Se actualiza la novela
    public void actualizarPublicacion(Publicacion publicacion, AreaGenero areaGenero, EdadSugerida edadSugerida, String antiguoTitulo) {

        publicacion.setCantidadDisponible(publicacion.getCantidadEjemplares() - publicacion.getCantidadPrestado());

        // Se busca la publicacion anterior con el titulo sin actualizar
        Publicacion publicacionPorActualizar = publicaciones.get(antiguoTitulo);

        if(publicacionPorActualizar != null){

            // Actualizar el novela en la base de datos
            PublicacionRepositorio.actualizarPublicacion(antiguoTitulo,publicacion);

            //Actualizar Area genero en la base de datos
            areaGeneroOperaciones.actualizarAreaGenero(antiguoTitulo,areaGenero);

            // Actualizar EdadSugeridad en la base de datos
            edadSugeridaOperaciones.actualizarEdadSugerida(antiguoTitulo,edadSugerida);



            // Agregar la nueva novela actualizada al HashMap de publicaciones
            publicaciones.remove(antiguoTitulo);
            publicaciones.put(publicacion.getTitulo(),publicacion);
            publicaciones.put(publicacion.getTitulo(),relacionarAreasGenero(publicacion));
            publicaciones.put(publicacion.getTitulo(),relacionarEdadesSugeridas(publicacion));

            MensajeOperacionBd.actualizarNovelaExitoso();
        }else{
            MensajeOperacionBd.errorActualizarLibro();
        }

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

    // Trae todas las publicaciones y las guarda en el hashmap
    private void getPublicaciones() {
        publicaciones = PublicacionRepositorio.consultarPublicaciones();
        for (Publicacion publicacion : publicaciones.values()) {
           publicaciones.put(publicacion.getTitulo(),relacionarAreasGenero(publicacion));
            publicaciones.put(publicacion.getTitulo(),relacionarEdadesSugeridas(publicacion));
        }
    }

    public void imprimirNovelas() {
        // Iterar sobre todas las publicaciones
        for (Publicacion publicacion : publicaciones.values()) {
            // Verificar si la publicación es del tipo novela
            if (publicacion.getTipo().equals(TipoPublicacion.Novela.toString()) && publicacion.getCantidadDisponible() != 0) {
                // Imprimir información relevante de la novela
                System.out.println(publicacion);
                System.out.println("Géneros:");
                for (AreaGenero areaGenero : publicacion.getAreas()) {
                    System.out.println("- " + areaGenero.getAreaGenero());
                }
                System.out.println("Edades sugeridas:");
                for (EdadSugerida edadSugerida : publicacion.getEdades()) {
                    System.out.println("- " + edadSugerida.getEdadSugeridad());
                }
                System.out.println();
            }
        }
    }

    public void imprimirLibros() {
        // Iterar sobre todas las publicaciones
        for (Publicacion publicacion : publicaciones.values()) {
            // Verificar si la publicación es del tipo libro y si hay cantidades disponibles
            if (publicacion.getTipo().equals(TipoPublicacion.Libro.toString()) && publicacion.getCantidadDisponible() > 0) {
                // Imprimir información relevante del libro
                System.out.println(publicacion);
                System.out.println("Número de páginas: "+publicacion.getNumeroPaginas());
                System.out.println("Areas Conocimiento: ");
                for (AreaGenero areaGenero : publicacion.getAreas()) {
                    System.out.println("- " + areaGenero.getAreaGenero());
                }
                System.out.println();
            }
        }
    }

}

package com.sofka.controllers;

import net.datafaker.Faker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import static com.sofka.constants.InsertConstants.INSERT_PUBLICACION;
import static com.sofka.constants.SelectConstants.*;
import static com.sofka.controllers.ControlAreaGenero.crearAreaGenero;
import static com.sofka.controllers.ControlEdadSugerida.crearEdadSugerida;
import static com.sofka.controllers.ControlMenu.mostrarMensaje;
import static com.sofka.controllers.ControlMenu.pedirEntrada;
import static com.sofka.controllers.ControlStament.ejecutarMostrarSQL;
import static com.sofka.controllers.ControlStament.insertIntoBd;

public class ControlPublicacion {

    public static ArrayList<String> registrarLibro() {
        String titulo = pedirEntrada( "Ingrese el titulo del libro: ");
        String autor = pedirEntrada( "Ingrese el autor del libro: ");
        String areaGenero = pedirEntrada( "Ingrese el area de conocimineto del libro: ");
        String numPaginas = pedirEntrada( "Ingrese el numero de pag. del libro: ");
        String cantEjemplares = pedirEntrada( "Ingrese la cantidad de ejemplares del libro: ");
        String cantPrestados = pedirEntrada( "Ingrese la cantidad prestada del libro: ");
        String cantDisponibles = Integer.toString(Integer.parseInt(cantEjemplares) - Integer.parseInt(cantPrestados));
        ArrayList<String> miLista = new ArrayList<>();
        miLista.add(String.format(INSERT_PUBLICACION, titulo, autor, "LIBRO", numPaginas, cantEjemplares, cantPrestados, cantDisponibles));
        miLista.add(crearAreaGenero(titulo, areaGenero));
        mostrarMensaje("¡Libro registrado exitosamente: " + titulo + "!");
        return miLista;
    }

    public static ArrayList<String> registrarNovela() {
        String titulo = pedirEntrada( "Ingrese el titulo de la Novela: ");
        String autor = pedirEntrada( "Ingrese el autor de la Novela: ");
        String areaGenero = pedirEntrada( "Ingrese el género de la Novela: ");
        String numPaginas = pedirEntrada( "Ingrese el numero de pag. de la Novela: ");
        String edadSugerida = pedirEntrada( "Ingrese la edad sugerida de la Novela: ");
        String cantEjemplares = pedirEntrada( "Ingrese la cantidad de ejemplares de la Novela: ");
        String cantPrestados = pedirEntrada( "Ingrese la cantidad prestada de la Novela: ");
        String cantDisponibles = Integer.toString(Integer.parseInt(cantEjemplares) - Integer.parseInt(cantPrestados));
        ArrayList<String> miLista = new ArrayList<>();
        miLista.add(String.format(INSERT_PUBLICACION, titulo, autor, "NOVELA", numPaginas, cantEjemplares, cantPrestados, cantDisponibles));
        miLista.add(crearEdadSugerida(titulo, edadSugerida));
        miLista.add(crearAreaGenero(titulo, areaGenero));
        mostrarMensaje("¡Novela registrada exitosamente: " + titulo + "!");
        return miLista;
    }

    public static void insertarLibrosFaker(int cantidadLibros) {
        for (int i = 0; i < cantidadLibros; i++) {
            ArrayList<String> publicaciones = crearPublicacion("LIBRO");
            insertIntoBd(publicaciones.get(0));
            insertIntoBd(publicaciones.get(1));
        }
    }

    public static void insertarNovelaFaker(int cantidadNovelas) {
        for (int i = 0; i < cantidadNovelas; i++) {
            ArrayList<String> publicaciones = crearPublicacion("NOVELA");
            insertIntoBd(publicaciones.get(0));
            insertIntoBd(publicaciones.get(1));
            insertIntoBd(publicaciones.get(2));
        }
    }

    private static ArrayList<String> crearPublicacion(String tipo) {
        String titulo;
        String autor;
        String numPaginas;
        String cantEjemplares;
        String cantPrestados;
        String cantDisponibles;
        String areaGenero;
        ArrayList<String> miLista = new ArrayList<>();
        Faker faker = new Faker(new Locale("es"));

        titulo = faker.book().title().replace("'", "");
        autor = faker.book().author().replace("'", "");
        areaGenero = faker.book().genre().replace("'", "");
        String edadSugerida = Integer.toString(faker.number().numberBetween(5, 25));
        numPaginas = faker.bothify("###");
        cantEjemplares = faker.bothify("###");
        cantPrestados = "0";
        cantDisponibles = cantEjemplares;
        miLista.add(String.format(INSERT_PUBLICACION, titulo, autor, tipo, numPaginas, cantEjemplares, cantPrestados, cantDisponibles));
        miLista.add(crearAreaGenero(titulo, areaGenero));
        if (Objects.equals(tipo, "NOVELA")) miLista.add(crearEdadSugerida(titulo, edadSugerida));
        return miLista;
    }

    public static void selectAllFromPublicacion() throws SQLException {
        System.out.println("Lista de Punlicaciones Registradas");
        ejecutarMostrarSQL(SELECT_ALL_FROM_PUBLICACION);
    }

    public static void selectAllFromLibro() throws SQLException {
        System.out.println("Lista de Libros Registrados");
        ejecutarMostrarSQL(SELECT_ALL_FROM_LIBRO);
    }

    public static void selectAllFromNovela() throws SQLException {
        System.out.println("Lista de Novelas Registradas");
        ejecutarMostrarSQL(SELECT_ALL_FROM_NOVELA);
    }
}

package org.example.logica.control;

import org.example.integration.database.persistencia.CrudPublicacion;
import org.example.model.AreaGenero;
import org.example.model.EdadSugerida;
import org.example.model.Publicacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.example.logica.control.ConstantesLogica.*;
import static org.example.logica.control.MetodosMain.pedirOpcion;
import static org.example.logica.control.UsuarioControl.librosSeleccionados;
import static org.example.logica.control.UsuarioControl.novelasSeleccionados;
import static org.example.model.Prestamo.prestamos;
import static org.example.model.Publicacion.*;

public class PublicacionControl {
    static CrudPublicacion crudPublicacion = new CrudPublicacion();
    private static final Logger logger=Logger.getLogger(PublicacionControl.class.getName());

    public PublicacionControl() {
    }

    public static void seleccionarLibros() throws SQLException {
        validarListas();
        librosDisponibles();
        int opcion;
        boolean bandera = true;
        while (bandera) {
            mostrarLibrosDisponibles();
            opcion = pedirOpcion();
            bandera = seleccionarOpcionLibros(opcion, bandera);
        }
    }

    public static void seleccionarNovelas() throws SQLException {
        validarListas();
        novelasDisponibles();
        int opcion;
        boolean bandera = true;
        while (bandera) {
            mostrarNovelas();
            opcion = pedirOpcion();
            bandera = seleccionarOpcionNovelas(opcion, bandera);
        }
    }

    public static void seleccionarLibroPorAutor() throws SQLException {
        validarListas();
        librosDisponibles();
        int opcionAutor;
        boolean bandera = true;
        while (bandera) {
            bandera = seleccionAutorLibro();
            opcionAutor = pedirOpcion();
            bandera = seleccionarOpcionLibrosPorAutor(opcionAutor, bandera);
        }
    }

    public static void seleccionarNovelaPorAutor() throws SQLException {
        validarListas();
        novelasDisponibles();
        String autor;
        int opcionAutor;
        int opcion;
        boolean bandera = true;
        while (bandera) {
            bandera = seleccionAutorNovela();
            opcionAutor = pedirOpcion();
            bandera = seleccionarOpcionNovelasPorAutor(opcionAutor, bandera);
        }
    }

    private static boolean seleccionAutorLibro() {
        String autor;
        int opcion;
        mostrarAutores();
        opcion = pedirOpcion();
        if (opcion == 0) {
            return false;
        } else {
            autor = librosDisponibles.get(opcion - 1).getAutor();
            mostrarLibrosPorAutor(librosPorAutor(autor));
        }
        return true;
    }

    private static boolean seleccionAutorNovela() {
        String autor;
        int opcion;
        mostrarAutoresNovelas();
        opcion = pedirOpcion();
        if (opcion == 0) {
            return false;
        } else {
            autor = Publicacion.novelasDisponibles.get(opcion - 1).getAutor();
            mostrarNovelasPorAutor(novelasPorAutor(autor));
        }
        return true;
    }

    private static void mostrarLibrosDisponibles() {
        System.out.println(SELECCION_LIBRO);
        for (int i = 0; i < Publicacion.librosDisponibles.size(); i++) {
            System.out.println(i + 1 + ". " + Publicacion.librosDisponibles.get(i).getTitulo());
        }
        System.out.println(OPCION_CERO);
    }

    private static void mostrarNovelas() {
        for (int i = 0; i < Publicacion.novelasDisponibles.size(); i++) {
            System.out.println(i + 1 + ". " + Publicacion.novelasDisponibles.get(i).getTitulo());
        }
        System.out.println(OPCION_CERO);
    }

    private static boolean seleccionarOpcionLibros(int opcion, boolean bandera) {
        if (opcion <= Publicacion.librosDisponibles.size() && opcion > 0) {
            librosSeleccionados.add(Publicacion.librosDisponibles.get(opcion - 1));
            System.out.println(SELECCION_LIBRO_DOS);
        } else if (opcion == 0) {
            bandera = false;
        } else {
            System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }

    private static boolean seleccionarOpcionNovelas(int opcion, boolean bandera) {
        System.out.println(SELECCION_NOVELA);
        if (opcion < Publicacion.novelasDisponibles.size() && opcion > 0) {
            novelasSeleccionados.add(Publicacion.novelasDisponibles.get(opcion - 1));
            System.out.println(SELECCION_NOVELA_DOS);
        } else if (opcion == 0) {
            bandera = false;
        } else {
            System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }

    public static void validarListas() throws SQLException {
        if (publicaciones.isEmpty()) {
            crudPublicacion.seleccionarDatos();
        }
        if (libros.isEmpty()) {
            llenarLibros();
        }
        if (novelas.isEmpty()) {
            llenarNovelas();
        }
    }

    private static void llenarLibros() throws SQLException {
        for (int i = 0; i < Publicacion.publicaciones.size(); i++) {
            if (Publicacion.publicaciones.get(i).getTipo().equalsIgnoreCase(LIBRO)) {
                libros.add(Publicacion.publicaciones.get(i));
            }
        }
    }

    private static void llenarNovelas() throws SQLException {
        for (int i = 0; i < Publicacion.publicaciones.size(); i++) {
            if (Publicacion.publicaciones.get(i).getTipo().equalsIgnoreCase(NOVELA)) {
                novelas.add(Publicacion.publicaciones.get(i));
            }
        }
    }

    private static void librosDisponibles() {
        if (librosDisponibles.isEmpty()) {
            for (int i = 0; i < libros.size(); i++) {
                if (libros.get(i).getCantidadDisponible() >= 1) {
                    Publicacion.librosDisponibles.add(libros.get(i));
                }
            }
        }
    }

    private static void novelasDisponibles() {
        if (novelasDisponibles.isEmpty()) {
            for (int i = 0; i < novelas.size(); i++) {
                if (novelas.get(i).getCantidadDisponible() >= 1) {
                    Publicacion.novelasDisponibles.add(novelas.get(i));
                }
            }
        }
    }

    private static boolean seleccionarOpcionLibrosPorAutor(int opcion, boolean bandera) {
        if (opcion <= librosDisponibles.size() && opcion > 0) {
            librosSeleccionados.add(librosDisponibles.get(opcion - 1));
            System.out.println(SELECCION_LIBRO_DOS);
        } else if (opcion == 0) {
            bandera = false;
        } else {
            System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }

    public static void mostrarLibrosPorAutor(List<Publicacion> librosPorAutor) {
        System.out.println(SELECCION_LIBRO);
        for (int i = 0; i < librosPorAutor.size(); i++) {
            System.out.println(i + 1 + ". " + librosPorAutor.get(i).getAutor() + "-" + librosPorAutor.get(i).getTitulo());
        }
        System.out.println(OPCION_CERO);
    }

    public static void mostrarAutores() {
        for (int i = 0; i < Publicacion.librosDisponibles.size(); i++) {
            System.out.println(i + 1 + ". " + Publicacion.librosDisponibles.get(i).getAutor());
        }
        System.out.println(SELECCION_AUTOR);
        System.out.println(OPCION_CERO);
    }

    public static List<Publicacion> librosPorAutor(String autor) {
        List<Publicacion> resultado = libros.stream().filter(libro -> libro.getAutor().equals(autor)).collect(Collectors.toList());
        return resultado;
    }

    private static boolean seleccionarOpcionNovelasPorAutor(int opcion, boolean bandera) {
        if (opcion < Publicacion.novelasDisponibles.size() && opcion > 0) {
            novelasSeleccionados.add(Publicacion.novelasDisponibles.get(opcion - 1));
            System.out.println(SELECCION_NOVELA_DOS);
        } else if (opcion == 0) {
            bandera = false;
        } else {
            System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }

    public static void mostrarNovelasPorAutor(List<Publicacion> novelasPorAutor) {
        System.out.println(SELECCION_NOVELA);
        for (int i = 0; i < novelasPorAutor.size(); i++) {
            System.out.println(i + 1 + ". " + novelasPorAutor.get(i).getAutor() + "-" + novelasPorAutor.get(i).getTitulo());
        }
        System.out.println(OPCION_CERO);
    }

    public static void mostrarAutoresNovelas() {
        for (int i = 0; i < novelasDisponibles.size(); i++) {
            System.out.println(i + 1 + ". " + novelasDisponibles.get(i).getAutor());
        }
        System.out.println(SELECCION_AUTOR);
    }

    public static List<Publicacion> novelasPorAutor(String autor) {
        List<Publicacion> resultado = novelas.stream().filter(novela -> novela.getAutor().equals(autor)).collect(Collectors.toList());
        return resultado;
    }

    public static void actualizarCantidades(List<Publicacion> publicaciones) {
        for (Publicacion p : publicaciones) {
            int cantidadPrestado = p.getCantidadPrestado() + 1;
            int cantidadDisponible = p.getCantidadDisponible() - 1;
            Publicacion publicacion = new Publicacion(p.getTitulo(), p.getTipo(), p.getAutor(), p.getNumeroPaginas(), p.getCantidadEjemplares(),
                    cantidadPrestado, cantidadDisponible);
            crudPublicacion.actualizarPublicacion(publicacion);
        }
    }

    public static void actualizarCantidadesDevueltasDb(String correo) {
        String tituloDevuelto="";
        for (int i = 0; i < prestamos.size(); i++) {
            if(prestamos.get(i).getCorreoUsuario().equalsIgnoreCase(correo) && prestamos.get(i).getEstadoPrestamo().equalsIgnoreCase(ESTADO_REALIZADO)){
                tituloDevuelto=prestamos.get(i).getTituloPublicacion();
                for (int j = 0; j < publicaciones.size(); j++) {
                    if(publicaciones.get(i).getTitulo().equalsIgnoreCase(tituloDevuelto)){
                        int cantidadPrestado = publicaciones.get(i).getCantidadPrestado() - 1;
                        int cantidadDisponible = publicaciones.get(i).getCantidadDisponible() + 1;
                        Publicacion publicacion= new Publicacion(publicaciones.get(i).getTitulo(), publicaciones.get(i).getTipo(), publicaciones.get(i).getAutor(),
                                publicaciones.get(i).getNumeroPaginas(), publicaciones.get(i).getCantidadEjemplares(), cantidadPrestado, cantidadDisponible);
                        crudPublicacion.actualizarPublicacion(publicacion);
                    }
                }
            }
        }
        /*
        for (Publicacion p : publicaciones) {
            int cantidadPrestado = p.getCantidadPrestado() - 1;
            int cantidadDisponible = p.getCantidadDisponible() + 1;
            Publicacion publicacion = new Publicacion(p.getTitulo(), p.getTipo(), p.getAutor(), p.getNumeroPaginas(), p.getCantidadEjemplares(),
                    cantidadPrestado, cantidadDisponible);
            p.setCantidadPrestado(cantidadPrestado);
            p.setCantidadDisponible(cantidadDisponible);
            crudPublicacion.actualizarPublicacion(publicacion);
        }
         */

    }

    public static void crearPublicacion() throws SQLException {
        if(publicaciones.isEmpty()){
            crudPublicacion.seleccionarDatos();
        }
        Publicacion publicacion = pedirDatosPublicacion();
        crudPublicacion.crearEntidad(publicacion);
        publicaciones.add(publicacion);
        if (publicacion.getTipo().equalsIgnoreCase(LIBRO)) {
            libros.add(publicacion);
        } else if (publicacion.getTipo().equalsIgnoreCase(NOVELA)) {
            novelas.add(publicacion);
        }
        logger.info(PUBLICACION_CREADA);
    }

    public static Publicacion pedirDatosPublicacion() {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println(TITULO_PUBLICACION);
        String titulo = sc.nextLine();
        String tipo = pedirOpcionTipo();
        System.out.println(AUTOR_PUBLICACION);
        String autor = sc.nextLine();
        System.out.println(NUM_PAGINAS_PUBLICACION);
        int numPaginas = pedirOpcion();
        System.out.println(CANT_EJEMPLARES_PUBLICACION);
        int cantEjemplares = pedirOpcion();
        int cantPrestados = 0;
        int cantDisponibles = cantEjemplares;
        System.out.println(GENERO_PUBLICACION);
        String genero = sc.nextLine();
        List<AreaGenero> generos = new ArrayList<>();
        AreaGenero areaGenero = new AreaGenero(titulo, genero);
        generos.add(areaGenero);
        System.out.println(EDAD_PUBLICACION);
        String edad = sc.nextLine();
        List<EdadSugerida> edades = new ArrayList<>();
        EdadSugerida edadSugerida = new EdadSugerida(titulo, edad);
        edades.add(edadSugerida);

        return new Publicacion(titulo, tipo, autor, numPaginas, cantEjemplares, cantPrestados, cantDisponibles, generos, edades);
    }

    private static String pedirOpcionTipo() {
        String tipo;
        int opcionPublicacion;
        while (true) {
            System.out.println(TIPO_PUBLICACION_OPCION);
            opcionPublicacion = pedirOpcion();
            if (opcionPublicacion == 1) {
                tipo = LIBRO;
                return tipo;
            } else if (opcionPublicacion == 2) {
                tipo = NOVELA;
                return tipo;
            } else {
                System.out.println(SELECCION_INVALIDA);
            }
        }
    }

    public static void actualizarPublicacion() throws SQLException {
        Publicacion publicacionSeleccionada = seleccionarPublicacionCrud(ACTUALIZAR);
        Publicacion publicacionActualizada = actualizarPublicacionDatos(publicacionSeleccionada);
        crudPublicacion.actualizarPublicacion(publicacionActualizada);
        publicaciones.remove(publicacionSeleccionada);
        publicaciones.add(publicacionActualizada);
        actualizarListas(publicacionSeleccionada, publicacionActualizada);

        logger.info(PUBLICACION_ACTUALIZADA);
    }

    private static void actualizarListas(Publicacion publicacionSeleccionada, Publicacion publicacionActualizada) {
        if (publicacionSeleccionada.getTipo().equalsIgnoreCase(LIBRO)) {
            libros.remove(publicacionSeleccionada);
        } else if (publicacionSeleccionada.getTipo().equalsIgnoreCase(NOVELA)) {
            novelas.remove(publicacionSeleccionada);
        }
        if (publicacionActualizada.getTipo().equalsIgnoreCase(LIBRO)) {
            libros.add(publicacionActualizada);
        } else if (publicacionActualizada.getTipo().equalsIgnoreCase(NOVELA)) {
            novelas.add(publicacionActualizada);
        }
    }

    private static Publicacion seleccionarPublicacionCrud(String tipoCrud) throws SQLException {
        int opcion;
        Publicacion publicacionSeleccionada;
        mostrarPublicaciones();
        System.out.println(String.format(PUBLICACION_CRUD, tipoCrud));
        opcion = pedirOpcion();
        publicacionSeleccionada = publicaciones.get(opcion - 1);
        return publicacionSeleccionada;
    }

    public static Publicacion actualizarPublicacionDatos(Publicacion publicacionSeleccionada) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String tipo = pedirOpcionTipo();
        System.out.println(AUTOR_PUBLICACION);
        String autor = sc.nextLine();
        System.out.println(NUM_PAGINAS_PUBLICACION);
        int numPaginas = pedirOpcion();
        System.out.println(CANT_EJEMPLARES_PUBLICACION);
        int cantEjemplares = pedirOpcion();
        int cantPrestados = 0;
        int cantDisponibles = cantEjemplares;
        System.out.println(GENERO_PUBLICACION);
        String genero = sc.nextLine();
        List<AreaGenero> generos = new ArrayList<>();
        AreaGenero areaGenero = new AreaGenero(publicacionSeleccionada.getTitulo(), genero);
        generos.add(areaGenero);
        System.out.println(EDAD_PUBLICACION);
        String edad = sc.nextLine();
        List<EdadSugerida> edades = new ArrayList<>();
        EdadSugerida edadSugerida = new EdadSugerida(publicacionSeleccionada.getTitulo(), edad);
        edades.add(edadSugerida);

        Publicacion publicacionActualizada = new Publicacion(publicacionSeleccionada.getTitulo(), tipo, autor, numPaginas, cantEjemplares, cantPrestados, cantDisponibles, generos, edades);
        return publicacionActualizada;
    }

    public static void eliminarPublicacion() throws SQLException {
        Publicacion publicacion = seleccionarPublicacionCrud(ELIMINAR);
        crudPublicacion.eliminarPublicacion(publicacion);
        publicaciones.remove(publicacion);
        if (publicacion.getTipo().equalsIgnoreCase(LIBRO)) {
            libros.remove(publicacion);
        } else if (publicacion.getTipo().equalsIgnoreCase(NOVELA)) {
            novelas.remove(publicacion);
        }
        System.out.println(ELIMINACION_EXITOSA);
    }

    public static void mostrarPublicaciones() throws SQLException {
        if (publicaciones.isEmpty()) {
            crudPublicacion.seleccionarDatos();
        }
        for (int i = 0; i < publicaciones.size(); i++) {
            System.out.println(i + 1 + ". " + publicaciones.get(i));
        }
    }

}

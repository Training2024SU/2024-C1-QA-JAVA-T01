package com.sofkau.dialogo;

import com.sofkau.logica.publicacion.CrudLibro;
import com.sofkau.model.Publicacion;
import com.sofkau.util.PedirPorConsola;

import java.sql.SQLException;
import java.util.List;

public class PublicacionDialog {

    public static void logicaObtenerPublicacionPorTitulo() throws SQLException {
        System.out.println("Eligio obtener publicacion por titulo");
        System.out.println("Escriba el Titulo de la publicacion que desea obtener: ");
        String tituloToGet = PedirPorConsola.pedirString();
        Publicacion publicacionObtenida = CrudLibro.findPublicacionByTitulo(tituloToGet);
        System.out.println(publicacionObtenida);
    }

    public static void logicaObtenerPublicacionesPorAutor() throws SQLException {
        System.out.println("Eligio obtener publicaciones por autor");
        System.out.println("Escriba el autor de las publicaciones que desea obtener: ");
        String autorToGet = PedirPorConsola.pedirString();
        List<Publicacion> publicacionesPorAutor = CrudLibro.getAllPublicacionesByAutor(autorToGet);
        publicacionesPorAutor.forEach(publicacion -> System.out.println(publicacion));
    }

    public static void logicaCrudPublicacion() throws SQLException {
        System.out.println("Publicacion");
        System.out.println("Eligio la seccion de publicaciones, selecione la operacion que desea realizar");
        System.out.println("1. Crear publicacion");
        System.out.println("2. Editar publicacion");
        System.out.println("3. Eliminar publicacion");
        System.out.println("4. Obtener publicacion por titulo");
        int operacionSeleccionada = PedirPorConsola.pedirOpcion();
        switch (operacionSeleccionada) {
            case 1:
                System.out.println("Eligio crea publicacion");
                System.out.println("Escriba el Titulo: ");
                String titulo = PedirPorConsola.pedirString();
                System.out.println("Escriba el Tipo: ");
                String tipo = PedirPorConsola.pedirString();
                System.out.println("Escriba el Autor: ");
                String autor = PedirPorConsola.pedirString();
                System.out.println("Escriba el Numero de paginas: ");
                int numPaginas = PedirPorConsola.pedirOpcion();
                System.out.println("Escriba el cantidad de ejemplares: ");
                int cantEjemplares = PedirPorConsola.pedirOpcion();
                Publicacion publicacion = new Publicacion();
                publicacion.setTitulo(titulo);
                publicacion.setAutor(autor);
                publicacion.setTipo(tipo);
                publicacion.setNumeroPaginas(numPaginas);
                publicacion.setCantidadEjemplares(cantEjemplares);
                publicacion.setCantidadPrestado(0);
                CrudLibro.createPublicacion(publicacion);
                break;
            case 2:
                System.out.println("Eligio editar publicacion");
                System.out.println("Escriba el Titulo de la publicacion que desea editar: ");
                String tituloEditar = PedirPorConsola.pedirString();
                System.out.println("Escriba la cantidad nueva de prestados: ");
                int cantPrestadosEditar = PedirPorConsola.pedirOpcion();
                System.out.println("Escriba la cantidad nueva de ejemplares: ");
                int cantEjemplaresEditar = PedirPorConsola.pedirOpcion();
                CrudLibro.updatePublicacionByTitulo(cantEjemplaresEditar, cantPrestadosEditar, tituloEditar);
                break;
            case 3:
                System.out.println("Eligio eliminar publicacion");
                System.out.println("Escriba el Titulo de la publicacion que desea eliminar: ");
                String tituloEliminar = PedirPorConsola.pedirString();
                CrudLibro.deletePublicacion(tituloEliminar);
                break;
            case 4:
                System.out.println("Eligio obtener publicacion por titulo");
                System.out.println("Escriba el Titulo de la publicacion que desea obtener: ");
                String tituloToGet = PedirPorConsola.pedirString();
                Publicacion publicacionObtenida = CrudLibro.findPublicacionByTitulo(tituloToGet);
                System.out.println(publicacionObtenida);
                break;
        }
    }
}

package co.com.sofkau.logica.publicacion;

import co.com.sofkau.model.Publicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicacionUtils {

    public static void printPublicacionsAutor(String autor) {
        imprimirInformacionPublicacion(filtrarPorAutor(Publicacion.getPublicaciones(), autor));
    }

    public static void imprimirPublicacionsDisponbles() {

        imprimirInformacionPublicacion(FiltrarPorDisponibilidad(Publicacion.getPublicaciones()));
    }


    public static ArrayList<Publicacion> FiltrarPorDisponibilidad(ArrayList<Publicacion> publicaciones) {

        List<Publicacion> publicacionesFiltradasPorDisponibilidad = publicaciones.stream()
                .filter(publicacion -> publicacion.getCantidadDisponible() > 0)
                .collect(Collectors.toList());

        return new ArrayList<>(publicacionesFiltradasPorDisponibilidad);
    }

    public static ArrayList<Publicacion> filtrarPorAutor(ArrayList<Publicacion> publicaciones, String autor) {

        List<Publicacion> publicacionesFiltradasPorAutor = publicaciones.stream()
                .filter(publicacion -> publicacion.getAutor().equals(autor))
                .collect(Collectors.toList());

        return new ArrayList<>(publicacionesFiltradasPorAutor);
    }

    public static ArrayList<Publicacion> filtrarPorTitulo(ArrayList<Publicacion> publicaciones, String Titulo) {

        List<Publicacion> publicacionesFiltradasPorTitulo = publicaciones.stream()
                .filter(publicacion -> publicacion.getTitulo().equals(Titulo))
                .collect(Collectors.toList());

        return new ArrayList<>(publicacionesFiltradasPorTitulo);
    }

    public static int filtrarPorDisponibilidadTitulo(ArrayList<Publicacion> publicaciones, String titulo) {

        List<Publicacion> publicacionesFiltradasPorTitulo = filtrarPorTitulo(publicaciones, titulo);

        if (!publicacionesFiltradasPorTitulo.isEmpty()) {

            Publicacion publicacion = publicacionesFiltradasPorTitulo.get(0);
            return publicacion.getCantidadPrestado();
        } else {

            return 0;
        }
    }

    public static void imprimirInformacionPublicacion(List<Publicacion> librosFiltrados) {
        for (Publicacion libroImprimir : librosFiltrados) {
            System.out.println("Titulo: " + libroImprimir.getTitulo());
            System.out.println("Autor: " + libroImprimir.getAutor());
            System.out.println("numero de paginas: " + libroImprimir.getNumeroPaginas());
            System.out.println("Cantidad Ejemplares: " + libroImprimir.getCantidadEjemplares());
            System.out.println("Cantidad Prestado: " + libroImprimir.getCantidadPrestado());
            System.out.println("Cantidad Disponible: " + libroImprimir.getCantidadDisponible());
            System.out.println("-------------------------");
        }
    }


}

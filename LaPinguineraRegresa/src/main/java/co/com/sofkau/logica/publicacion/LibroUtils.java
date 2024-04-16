package co.com.sofkau.logica.publicacion;

import co.com.sofkau.model.AreaGenero;
import co.com.sofkau.model.Empleado;
import co.com.sofkau.model.Publicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LibroUtils {

    public static void imprimirLibros(){
        ArrayList<Publicacion> publicaciones = Publicacion.getPublicaciones();
        AsignarArea(publicaciones);
        printLibros(publicaciones);
    }


    public static void printLibros(ArrayList<Publicacion> publicaciones) {

        List<Publicacion> librosFiltrados = publicaciones.stream()
                .filter(p -> p.getTipo().equals("Libro"))
                .collect(Collectors.toList());

        //AsignarArea(librosFiltrados);

        PublicacionUtils.imprimirInformacionPublicacion(librosFiltrados);

    }

    private static void AsignarArea(List<Publicacion> librosFiltrados) {

        for (Publicacion libroAsignar : librosFiltrados){
            for (Map.Entry<String, String> entry : AreaGenero.areaGeneros.entrySet()){

                if (Objects.equals(libroAsignar.getTitulo(), entry.getValue())){

                    libroAsignar.setAreas(entry.getKey());
                }
            }
        }
    }


}

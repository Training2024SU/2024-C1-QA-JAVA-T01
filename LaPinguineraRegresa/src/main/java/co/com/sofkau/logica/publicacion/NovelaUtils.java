package co.com.sofkau.logica.publicacion;

import co.com.sofkau.model.AreaGenero;
import co.com.sofkau.model.EdadSugerida;
import co.com.sofkau.model.Publicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static co.com.sofkau.util.enums.TipoPublicacion.TIPO_DOS;

public class NovelaUtils {

    public static void imprimirNovela(){
        ArrayList<Publicacion> publicaciones = Publicacion.getPublicaciones();
        AsignarEdadSugeridad(publicaciones);
        printNovelas(publicaciones);
    }
    public static void printNovelas(ArrayList<Publicacion> publicaciones) {

        List<Publicacion> librosFiltrados = publicaciones.stream()
                .filter(p -> p.getTipo().equals(TIPO_DOS.getvalue()))
                .collect(Collectors.toList());

        PublicacionUtils.imprimirInformacionPublicacion(librosFiltrados);

    }
    private static void AsignarEdadSugeridad(List<Publicacion> publicaciones) {

        for (Publicacion NovelaAsignar : publicaciones){
            for (Map.Entry<String, String> entry : EdadSugerida.edadSugeridas.entrySet()){

                if (Objects.equals(NovelaAsignar.getTitulo(), entry.getValue())){
                    NovelaAsignar.setEdades(entry.getKey());
                }
            }
        }
    }



}

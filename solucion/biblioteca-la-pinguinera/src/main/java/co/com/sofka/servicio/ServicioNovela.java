package co.com.sofka.servicio;

import co.com.sofka.modelo.Novela;
import co.com.sofka.repositorio.RepositorioNovela;
import java.util.List;
import java.util.stream.Collectors;

import static co.com.sofka.menu.ConstantesMenu.*;

public class ServicioNovela {
    private RepositorioNovela repositorioNovela;
    public ServicioNovela(RepositorioNovela repositorioNovela){
        this.repositorioNovela = repositorioNovela;
    }

    public void guardarNovela(String titulo, String autor, int cantidadEjemplares, String genero, int edadDeLecturaSugerida){
        Novela novela = new Novela(titulo, autor, cantidadEjemplares, genero, edadDeLecturaSugerida);
        repositorioNovela.guardar(novela);
        System.out.println(SEXTO_MENSAJE_GUARDAR_NOVELA);
    }
    public void modificarNovela(Long idNovela, String titulo, String autor, int cantidadEjemplares, String genero, int edadDeLecturaSugerida){
        Novela novelaModificar = repositorioNovela.obtenerNovela(idNovela);

        novelaModificar.setTitulo(titulo);
        novelaModificar.setAutor(autor);
        novelaModificar.setCantidadEjemplares(cantidadEjemplares);
        novelaModificar.setGenero(genero);
        novelaModificar.setEdadDeLecturaSugerida(edadDeLecturaSugerida);

        repositorioNovela.modificar(novelaModificar);
        System.out.println(SEPTIMO_MENSAJE_MODIFICAR_NOVELA);

    }

    public void listarNovelasDisponibles() {
        List<Novela> novelas = repositorioNovela.listarNovelas().stream()
                .filter(novela -> novela.isFueBorrado() == false) // traiga todo lo que tenga fueBorrado = false
                .filter(novela -> novela.sePuedePrestar())
                .collect(Collectors.toList());

        if (novelas.isEmpty()) {
            System.out.println("No se encuentran novelas disponibles.");
        } else {
            System.out.println("Lista de novelas:");
            for (Novela novela : novelas) {
                    novela.imprimirDetalles();
                System.out.println("-----------------------------------");
            }
        }
    }

    public void listarAutores(){
        repositorioNovela.listarNovelas().stream()
                .filter(novela -> !novela.isFueBorrado())
                .map(novela -> novela.getAutor())
                .filter(autor -> autor != null)
                .distinct()
                .forEach(autor -> System.out.println(autor));

    }

    public void listarNovelaPorAutor(String autor){
        repositorioNovela.listarNovelas().stream()
                .filter(novela -> !novela.isFueBorrado())
                .filter(novela -> autor.equals(novela.getAutor()))
                .forEach(novela -> System.out.println(novela));

    }

    public void borrarNovelaPorId(Long novelaId){
        Novela novela = repositorioNovela.obtenerNovela(novelaId);

        if(novela == null){
            System.out.println("Esa novela no existe");
            return;
        }

        novela.setFueBorrado(true);
        repositorioNovela.modificar(novela);

        System.out.println("Novela borrada exitosamente");
    }
}

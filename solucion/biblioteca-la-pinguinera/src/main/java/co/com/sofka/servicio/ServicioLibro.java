package co.com.sofka.servicio;

import co.com.sofka.modelo.Libro;
import co.com.sofka.repositorio.RepositorioLibro;

import java.util.List;
import java.util.stream.Collectors;
import static co.com.sofka.menu.ConstantesMenu.*;

public class ServicioLibro {

    private RepositorioLibro repositorioLibro;

    public ServicioLibro(RepositorioLibro repositorioLibro){
        this.repositorioLibro = repositorioLibro;
    }

    public void guardarLibro(String titulo, String autor, int cantidadEjemplares, String areaDelConocimiento, int numeroDePaginas){
        Libro libro = new Libro(titulo, autor, cantidadEjemplares, areaDelConocimiento, numeroDePaginas);

        System.out.println(libro.toString());
        repositorioLibro.guardar(libro);
        System.out.println(OCTAVO_MENSAJE_GUARDAR_LIBRO);
    }

    public void modificarLibro(Long idLibro, String titulo, String autor, int cantidadEjemplares, String areaDelConocimiento, int numeroDePaginas){
        Libro libroModificar = repositorioLibro.obtenerLibro(idLibro);

        libroModificar.setTitulo(titulo);
        libroModificar.setAutor(autor);
        libroModificar.setCantidadEjemplares(cantidadEjemplares);
        libroModificar.setAreaDelConocimiento(areaDelConocimiento);
        libroModificar.setNumeroDePaginas(numeroDePaginas);

        repositorioLibro.modificar(libroModificar);
        System.out.println(NOVENO_MENSAJE_MODIFICAR_LIBRO);

    }
    public void listarLibrosDisponibles() {
        List<Libro> libros = repositorioLibro.listarLibros()
                .stream()
                .filter(libro -> libro.isFueBorrado() == false)
                .filter(libro -> libro.sePuedePrestar())
                .collect(Collectors.toList());

        if (libros.isEmpty()) {
            System.out.println("No se encuentran libros disponibles.");
        } else {
            System.out.println("Lista de libros:");
            for (Libro libro : libros) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Cantidad de ejemplares: " + libro.getCantidadEjemplares());
                System.out.println("Cantidad de prestados: " + libro.getCantidadPrestados());
                System.out.println("Cantidad de disponible: " + libro.getCantidadDisponible());
                System.out.println("Area del conocimiento: " + libro.getAreaDelConocimiento());
                System.out.println("Numero de paginas: " + libro.getNumeroDePaginas());
                System.out.println("-----------------------------------");
            }
        }
    }

    public void listarAutores(){
        repositorioLibro.listarLibros().stream()
                .filter(libro -> !libro.isFueBorrado())
                .map(libro -> libro.getAutor())
                .filter(autor -> autor != null)
                .distinct()
                .forEach(autor -> System.out.println(autor));

    }

    public void listarLibroPorAutor(String autor){
        repositorioLibro.listarLibros().stream()
                .filter(libro -> !libro.isFueBorrado())
                .filter(libro -> autor.equals(libro.getAutor()))
                .forEach(libro -> System.out.println(libro));

    }

    public void borrarLibroPorId(Long libroId){
        Libro libro = repositorioLibro.obtenerLibro(libroId);

        if(libro == null){
            System.out.println("Id ingresado no existe");
            return;
        }

        libro.setFueBorrado(true);

        repositorioLibro.modificar(libro);

        System.out.println("Libro borrado exitosamente");
    }
}

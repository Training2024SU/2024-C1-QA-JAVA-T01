package co.com.pinguinera.interfaces;

import java.util.List;

public interface LibroRepositorio {
    void agregarLibro(Libro libro);
    void actualizarLibro(Libro libro);
    void eliminarLibro(int libroId);
    List<Libro> obtenerTodosLosLibros();
    Libro buscarLibroPorTitulo(String titulo);
    List<String> listarAutoresDeLibros();
}

package co.com.pinguinera.controladores;

import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;

import java.util.List;

public class MenuLectorControlador {

    private final LibroRepositorio libroRepositorio;
    private final NovelaRepositorio novelaRepositorio;
    private final PrestamoRepositorio prestamoRepositorio;
    private final int usuarioId;

    public MenuLectorControlador(LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio, PrestamoRepositorio prestamoRepositorio, int usuarioId) {
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
        this.prestamoRepositorio = prestamoRepositorio;
        this.usuarioId = usuarioId;
    }

    // Método para obtener el usuarioId
    public int getUsuarioId() {
        return usuarioId;
    }

    // Métodos para operaciones relacionadas con novelas

    public void verTodasLasNovelas() {
        List<Novela> novelas = novelaRepositorio.obtenerTodasLasNovelas();
        if (novelas.isEmpty()) {
            System.out.println("No hay novelas disponibles.");
        } else {
            System.out.println("Novelas disponibles:");
            for (Novela novela : novelas) {
                System.out.println(novela);
            }
        }
    }

    public void buscarNovelaPorTitulo(String titulo) {
        List<Novela> novelas = novelaRepositorio.buscarNovelaPorTitulo(titulo);
        if (novelas.isEmpty()) {
            System.out.println("No se encontraron novelas con el título: " + titulo);
        } else {
            System.out.println("Novelas encontradas:");
            for (Novela novela : novelas) {
                System.out.println(novela);
            }
        }
    }

    public void listarAutoresDeNovelas() {
        List<String> autores = novelaRepositorio.listarAutoresDeNovelas();
        System.out.println("Autores de novelas:");
        for (String autor : autores) {
            System.out.println(autor);
        }
    }

    // Métodos para operaciones relacionadas con libros

    public void verTodosLosLibros() {
        List<Libro> libros = libroRepositorio.obtenerTodosLosLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles.");
        } else {
            System.out.println("Libros disponibles:");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public void buscarLibroPorTitulo(String titulo) {
        Libro libro = libroRepositorio.buscarLibroPorTitulo(titulo);
        if (libro == null) {
            System.out.println("No se encontró ningún libro con el título: " + titulo);
        } else {
            System.out.println("Libro encontrado: " + libro);
        }
    }

    public void listarAutoresDeLibros() {
        List<String> autores = libroRepositorio.listarAutoresDeLibros();
        System.out.println("Autores de libros:");
        for (String autor : autores) {
            System.out.println(autor);
        }
    }

    // Métodos para operaciones relacionadas con préstamos

    public void realizarPrestamo(Prestamo prestamo) {
        // Verifica que prestamoRepositorio no sea null antes de intentar usarlo
        if (prestamoRepositorio == null) {
            System.err.println("Error: `prestamoRepositorio` es null. No se puede realizar el préstamo.");
            return;
        }

        prestamo.setUsuarioID(usuarioId);
        prestamoRepositorio.realizarPrestamo(prestamo);
        System.out.println("Préstamo realizado con éxito.");
    }


    public void confirmarPrestamo(int prestamoId) {
        prestamoRepositorio.confirmarPrestamo(prestamoId);
        System.out.println("Préstamo confirmado con éxito.");
    }

    public void devolverPrestamo(int prestamoId) {
        prestamoRepositorio.devolverPrestamo(prestamoId);
        System.out.println("Préstamo devuelto con éxito.");
    }

    public void verMisPrestamos() {
        List<Prestamo> prestamos = prestamoRepositorio.listarPrestamosPorUsuario(usuarioId);
        if (prestamos.isEmpty()) {
            System.out.println("No tienes préstamos actualmente.");
        } else {
            System.out.println("Tus préstamos:");
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo);
            }
        }
    }
}

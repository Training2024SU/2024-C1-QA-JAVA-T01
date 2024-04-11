package co.com.pinguinera.controladores;

import co.com.pinguinera.modelos.Libro;
import co.com.pinguinera.modelos.Novela;
import co.com.pinguinera.modelos.Prestamo;
import co.com.pinguinera.modelos.interfaces.LibroRepositorio;
import co.com.pinguinera.modelos.interfaces.NovelaRepositorio;
import co.com.pinguinera.modelos.interfaces.PrestamoRepositorio;

import java.util.List;

public class MenuLectorControlador {

    private final LibroRepositorio libroRepositorio;
    private final NovelaRepositorio novelaRepositorio;
    private final PrestamoRepositorio prestamoRepositorio;
    private final int usuarioId;

    public MenuLectorControlador(LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio,
                                 PrestamoRepositorio prestamoRepositorio, int usuarioId) {
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
        this.prestamoRepositorio = prestamoRepositorio;
        this.usuarioId = usuarioId;
    }

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

    public void realizarPrestamo(int prestamoId) {
        // Implementar la lógica para realizar el préstamo
        System.out.println("Realizando préstamo...");
        Prestamo prestamo = new Prestamo();
        // Configurar la información del préstamo
        prestamo.setUsuarioId(usuarioId);
        prestamo.setPrestamoId(prestamoId);
        // Realizar el préstamo
        prestamoRepositorio.realizarPrestamo(prestamo);
        System.out.println("Préstamo realizado con éxito.");
    }

    public void devolverPrestamo(int prestamoId) {
        // Implementar la lógica para devolver el préstamo
        System.out.println("Devolviendo préstamo...");
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

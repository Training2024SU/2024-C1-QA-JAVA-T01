package co.com.pinguinera.controladores;

import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;
import co.com.pinguinera.modelos.Libro;
import co.com.pinguinera.modelos.Novela;
import co.com.pinguinera.modelos.Prestamo;

import java.util.Date;
import java.util.List;

public class MenuAsistenteControlador {

    private final LibroRepositorio libroRepositorio;
    private final NovelaRepositorio novelaRepositorio;
    private final PrestamoRepositorio prestamoRepositorio;

    public MenuAsistenteControlador(LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio,
                                    PrestamoRepositorio prestamoRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
        this.prestamoRepositorio = prestamoRepositorio;
    }

    // Métodos para gestionar libros
    public void agregarLibro(Libro libro) {
        libroRepositorio.agregarLibro(libro);
        System.out.println("Libro agregado exitosamente.");
    }

    public void actualizarLibro(Libro libro) {
        libroRepositorio.actualizarLibro(libro);
        System.out.println("Libro actualizado exitosamente.");
    }

    public void eliminarLibro(int libroId) {
        libroRepositorio.eliminarLibro(libroId);
        System.out.println("Libro eliminado exitosamente.");
    }


    public void listarPrestamosPorEstado(String estado) {
        // Cambia 'PrestamoRepositorio' por 'prestamoRepositorio' para usar la instancia del objeto
        List<Prestamo> prestamos = prestamoRepositorio.listarPrestamosPorEstado(estado);

        if (prestamos.isEmpty()) {
            System.out.println("No se encontraron préstamos con el estado: " + estado);
        } else {
            System.out.println("Préstamos con el estado: " + estado + ":");
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo);
            }
        }
    }

    public void listarPrestamosPorFecha(Date fechaInicio, Date fechaFin) {
        // Cambia 'PrestamoRepositorio' por 'prestamoRepositorio' para usar la instancia del objeto
        List<Prestamo> prestamos = prestamoRepositorio.listarPrestamosPorFecha(fechaInicio, fechaFin);

        if (prestamos.isEmpty()) {
            System.out.println("No se encontraron préstamos entre las fechas: " + fechaInicio + " y " + fechaFin);
        } else {
            System.out.println("Préstamos entre las fechas: " + fechaInicio + " y " + fechaFin + ":");
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo);
            }
        }
    }

    // Métodos para gestionar novelas
    public void agregarNovela(Novela novela) {
        novelaRepositorio.agregarNovela(novela);
        System.out.println("Novela agregada exitosamente.");
    }

    public void actualizarNovela(Novela novela) {
        novelaRepositorio.actualizarNovela(novela);
        System.out.println("Novela actualizada exitosamente.");
    }

    public void eliminarNovela(int novelaId) {
        novelaRepositorio.eliminarNovela(novelaId);
        System.out.println("Novela eliminada exitosamente.");
    }
}

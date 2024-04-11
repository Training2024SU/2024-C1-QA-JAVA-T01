package co.com.pinguinera.controladores;

import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;

public class MenuAsistenteControlador {

    private final LibroRepositorio libroRepositorio;
    private final NovelaRepositorio novelaRepositorio;
    private final PrestamoRepositorio prestamoRepositorio;

    public MenuAsistenteControlador(LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio, PrestamoRepositorio prestamoRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
        this.prestamoRepositorio = prestamoRepositorio;
    }

    public void gestionarLibros() {
        // Lógica para gestionar libros
        System.out.println("Gestión de libros...");
        // Implementar lógica de negocio aquí
    }

    public void gestionarNovelas() {
        // Lógica para gestionar novelas
        System.out.println("Gestión de novelas...");
        // Implementar lógica de negocio aquí
    }

    public void gestionarPrestamos() {
        // Lógica para gestionar préstamos
        System.out.println("Gestión de préstamos...");
        // Implementar lógica de negocio aquí
    }
}

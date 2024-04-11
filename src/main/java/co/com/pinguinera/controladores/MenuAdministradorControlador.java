package co.com.pinguinera.controladores;

import co.com.pinguinera.modelos.interfaces.LibroRepositorio;
import co.com.pinguinera.modelos.interfaces.NovelaRepositorio;
import co.com.pinguinera.modelos.interfaces.UsuarioRepositorio;
import co.com.pinguinera.modelos.interfaces.UsuarioRolesRepositorio;

import java.util.List;

public class MenuAdministradorControlador {

    private UsuarioRepositorio usuarioRepositorio;
    private UsuarioRolesRepositorio usuarioRolesRepositorio;
    private LibroRepositorio libroRepositorio;
    private NovelaRepositorio novelaRepositorio;

    public MenuAdministradorControlador(UsuarioRepositorio usuarioRepositorio, UsuarioRolesRepositorio usuarioRolesRepositorio,
                                        LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioRolesRepositorio = usuarioRolesRepositorio;
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
    }

    public void gestionarAsistentes() {
        // Lógica para gestionar asistentes
        System.out.println("Gestión de asistentes...");
        // Implementar lógica de negocio aquí
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

    public void listarAutores() {
        // Lógica para listar autores de libros y novelas
        System.out.println("Listando autores...");
        // Implementar lógica de negocio aquí
    }
}

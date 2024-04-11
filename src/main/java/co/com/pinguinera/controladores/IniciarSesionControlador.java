package co.com.pinguinera.controladores;

import co.com.pinguinera.modelos.DAO.AutenticacionDAO;
import co.com.pinguinera.modelos.TipoRol;
import co.com.pinguinera.modelos.interfaces.LibroRepositorio;
import co.com.pinguinera.modelos.interfaces.NovelaRepositorio;
import co.com.pinguinera.modelos.interfaces.UsuarioRepositorio;
import co.com.pinguinera.modelos.interfaces.UsuarioRolesRepositorio;
import co.com.pinguinera.vistas.MenuAdministrador;
import co.com.pinguinera.vistas.MenuLector;
import co.com.pinguinera.vistas.Notificaciones;
import co.com.pinguinera.modelos.interfaces.PrestamoRepositorio;


import java.util.Scanner;

public class IniciarSesionControlador {

    private Scanner scanner;
    private AutenticacionDAO autenticacionDAO;
    private UsuarioRepositorio usuarioRepositorio;
    private UsuarioRolesRepositorio usuarioRolesRepositorio;
    private LibroRepositorio libroRepositorio;
    private NovelaRepositorio novelaRepositorio;
    private PrestamoRepositorio prestamoRepositorio;


    // Constructor del controlador
    public IniciarSesionControlador(Scanner scanner, AutenticacionDAO autenticacionDAO, UsuarioRepositorio usuarioRepositorio, UsuarioRolesRepositorio usuarioRolesRepositorio, LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio) {
        this.scanner = scanner;
        this.autenticacionDAO = autenticacionDAO;
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioRolesRepositorio = usuarioRolesRepositorio;
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;

    }

    public void iniciarSesion() {
        System.out.println("Iniciar sesión");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        // Autenticar el usuario
        boolean autenticado = autenticacionDAO.autenticarUsuario(correo, contraseña);

        if (autenticado) {
            // Autenticación exitosa
            Notificaciones.mostrarMensajeExito();

            // Obtén el rol del usuario autenticado
            TipoRol rol = autenticacionDAO.obtenerRolUsuario(correo);

            if (rol != null && rol == TipoRol.ADMINISTRADOR) {
                Notificaciones.mostrarRol(rol);

                // Crear instancia de MenuAdministrador y llamar a mostrarMenuAdministrador
                MenuAdministrador menuAdministrador = new MenuAdministrador(
                        scanner,
                        usuarioRepositorio,
                        usuarioRolesRepositorio,
                        libroRepositorio,
                        novelaRepositorio
                );
                menuAdministrador.mostrarMenuAdministrador();
            } else if (rol != null && rol == TipoRol.LECTOR) {
                Notificaciones.mostrarRol(rol);

                // Crear instancia de MenuLector y llamar a mostrarMenuLector
                MenuLector menuLector = new MenuLector(
                        scanner,
                        libroRepositorio,
                        novelaRepositorio,
                        prestamoRepositorio,
                        usuarioRepositorio.buscarUsuarioPorCorreo(correo).getUsuarioID()
                );
                menuLector.mostrarMenuLector();
            } else {
                System.out.println("El usuario no tiene rol válido. Acceso restringido.");
            }
        } else {
            // Autenticación fallida
            Notificaciones.procesoFallido();
        }
    }
    }


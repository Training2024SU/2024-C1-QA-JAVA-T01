package co.com.pinguinera.controladores;

import co.com.pinguinera.modelos.DAO.AutenticacionDAO;
import co.com.pinguinera.modelos.TipoRol;
import co.com.pinguinera.modelos.interfaces.LibroRepositorio;
import co.com.pinguinera.modelos.interfaces.NovelaRepositorio;
import co.com.pinguinera.modelos.interfaces.UsuarioRepositorio;
import co.com.pinguinera.modelos.interfaces.UsuarioRolesRepositorio;
import co.com.pinguinera.vistas.MenuAdministrador;
import co.com.pinguinera.vistas.MenuAsistente;
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

            if (rol != null) {
                Notificaciones.mostrarRol(rol);

                // Manejar el rol del usuario autenticado
                if (rol == TipoRol.ADMINISTRADOR) {
                    // Crear instancia de MenuAdministrador y llamar a mostrarMenuAdministrador
                    MenuAdministrador menuAdministrador = new MenuAdministrador(
                            scanner,
                            usuarioRepositorio,
                            usuarioRolesRepositorio,
                            libroRepositorio,
                            novelaRepositorio
                    );
                    menuAdministrador.mostrarMenuAdministrador();
                } else if (rol == TipoRol.LECTOR) {
                    // Crear instancia de MenuLector y llamar a mostrarMenuLector
                    MenuLector menuLector = new MenuLector(
                            scanner,
                            libroRepositorio,
                            novelaRepositorio,
                            prestamoRepositorio,
                            usuarioRepositorio.buscarUsuarioPorCorreo(correo).getUsuarioID()
                    );
                    menuLector.mostrarMenuLector();
                } else if (rol == TipoRol.ASISTENTE) {
                    // Crear instancia de MenuAsistente y llamar a mostrarMenuAsistente
                    MenuAsistente menuAsistente = new MenuAsistente(
                            scanner,
                            libroRepositorio,
                            novelaRepositorio,
                            prestamoRepositorio
                    );
                    menuAsistente.mostrarMenuAsistente();
                } else {
                    System.out.println("El usuario no tiene rol válido. Acceso restringido.");
                }
            } else {
                System.out.println("No se pudo determinar el rol del usuario.");
            }
        } else {
            // Autenticación fallida
            Notificaciones.procesoFallido();
        }
    }
    }


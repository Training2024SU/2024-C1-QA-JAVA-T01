package co.com.pinguinera.controladores;

import co.com.pinguinera.DAO.AutenticacionDAO;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;
import co.com.pinguinera.interfaces.RolesRepositorio;
import co.com.pinguinera.interfaces.UsuarioRepositorio;
import co.com.pinguinera.vistas.MenuAdministrador;
import co.com.pinguinera.vistas.MenuAsistente;
import co.com.pinguinera.vistas.MenuLector;
import co.com.pinguinera.vistas.Notificaciones;

import java.util.Scanner;

public class IniciarSesionControlador {

    private final Scanner scanner;
    private final AutenticacionDAO autenticacionDAO;
    private final UsuarioRepositorio usuarioRepositorio;
    private final RolesRepositorio rolesRepositorio;
    private final LibroRepositorio libroRepositorio;
    private final NovelaRepositorio novelaRepositorio;
    private final PrestamoRepositorio prestamoRepositorio;

    // Constructor del controlador
    public IniciarSesionControlador(
            Scanner scanner,
            AutenticacionDAO autenticacionDAO,
            UsuarioRepositorio usuarioRepositorio,
            RolesRepositorio rolesRepositorio,
            LibroRepositorio libroRepositorio,
            NovelaRepositorio novelaRepositorio,
            PrestamoRepositorio prestamoRepositorio
    ) {
        this.scanner = scanner;
        this.autenticacionDAO = autenticacionDAO;
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolesRepositorio = rolesRepositorio;
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
        this.prestamoRepositorio = prestamoRepositorio;
    }

    // Método para iniciar sesión
    public void iniciarSesion() {
        String correo = solicitarCorreo();
        String contraseña = solicitarContraseña();

        boolean autenticado = autenticarUsuario(correo, contraseña);

        if (autenticado) {
            manejarAutenticacionExitosa(correo);
        } else {
            Notificaciones.procesoFallido();
        }
    }

    // Solicitar el correo del usuario
    private String solicitarCorreo() {
        System.out.println("Iniciar sesión");
        System.out.print("Correo: ");
        return scanner.nextLine();
    }

    // Solicitar la contraseña del usuario
    private String solicitarContraseña() {
        System.out.print("Contraseña: ");
        return scanner.nextLine();
    }

    // Autenticar al usuario
    private boolean autenticarUsuario(String correo, String contraseña) {
        return autenticacionDAO.autenticarUsuario(correo, contraseña);
    }

    // Manejar una autenticación exitosa
    private void manejarAutenticacionExitosa(String correo) {
        Notificaciones.mostrarMensajeExito();

        TipoRol rol = autenticacionDAO.obtenerRolUsuario(correo);
        Notificaciones.mostrarRol(rol);

        if (rol != null) {
            manejarRolUsuario(correo, rol);
        } else {
            System.out.println("No se pudo determinar el rol del usuario.");
        }
    }

    // Manejar el rol del usuario
    private void manejarRolUsuario(String correo, TipoRol rol) {
        switch (rol) {
            case ADMINISTRADOR:
                mostrarMenuAdministrador();
                break;
            case LECTOR:
                mostrarMenuLector(correo);
                break;
            case ASISTENTE:
                mostrarMenuAsistente();
                break;
            default:
                System.out.println("El usuario no tiene rol válido. Acceso restringido.");
                break;
        }
    }

    // Mostrar el menú del administrador
    private void mostrarMenuAdministrador() {
        MenuAdministrador menuAdministrador = new MenuAdministrador(
                scanner,
                usuarioRepositorio,
                rolesRepositorio,
                libroRepositorio,
                novelaRepositorio
        );
        menuAdministrador.mostrarMenuAdministrador();
    }

    // Mostrar el menú del lector
    private void mostrarMenuLector(String correo) {
        int usuarioId = obtenerUsuarioIdPorCorreo(correo);
        MenuLector menuLector = new MenuLector(
                scanner,
                libroRepositorio,
                novelaRepositorio,
                prestamoRepositorio,
                usuarioId
        );
        menuLector.mostrarMenuLector();
    }

    // Mostrar el menú del asistente
    private void mostrarMenuAsistente() {
        MenuAsistente menuAsistente = new MenuAsistente(
                scanner,
                libroRepositorio,
                novelaRepositorio,
                prestamoRepositorio
        );
        menuAsistente.mostrarMenuAsistente();
    }

    // Obtener el ID del usuario por correo
    private int obtenerUsuarioIdPorCorreo(String correo) {
        return usuarioRepositorio.buscarUsuarioPorCorreo(correo).getUsuarioID();
    }
}

package co.com.pinguinera.controladores;

import co.com.pinguinera.modelos.DAO.AutenticacionDAO;
import java.util.Scanner;

// Importa la clase Notificaciones
import co.com.pinguinera.modelos.TipoRol;
import co.com.pinguinera.vistas.Notificaciones;

public class IniciarSesionControlador {
    private Scanner scanner;
    private AutenticacionDAO autenticacionDAO;

    public IniciarSesionControlador(Scanner scanner, AutenticacionDAO autenticacionDAO) {
        this.scanner = scanner;
        this.autenticacionDAO = autenticacionDAO;
    }

    public void iniciarSesion() {
        System.out.println("Iniciar sesión");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        // Llama al método autenticarUsuario con los datos recogidos
        boolean autenticado = autenticacionDAO.autenticarUsuario(correo, contraseña);

        // Verifica si la autenticación fue exitosa
        if (autenticado) {
            // Autenticación exitosa
            Notificaciones.mostrarMensajeExito();

            // Obtén el rol del usuario autenticado
            TipoRol rol = autenticacionDAO.obtenerRolUsuario(correo);

            // Muestra el rol del usuario
            if (rol != null) {
                Notificaciones.mostrarRol(rol);
            } else {
                System.out.println("No se pudo determinar el rol del usuario.");
            }

        } else {
            // Autenticación fallida
            Notificaciones.procesoFallido(); // Llama a procesoFallido de Notificaciones
            // Aquí puedes manejar el caso de inicio de sesión fallido
        }
    }

}

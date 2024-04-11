package co.com.pinguinera.vistas;

import co.com.pinguinera.modelos.TipoRol;

public class Notificaciones {

    // Método para mostrar mensaje de autenticación exitosa
    public static void mostrarMensajeExito() {
        System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");
    }

    // Método para mostrar mensaje de autenticación fallida
    public static void procesoFallido() {
        System.out.println("Inicio de sesión fallido. Por favor, verifica tus credenciales.");
    }

    // Otros métodos de notificaciones
    public static void mostrarMensajeUsuarioAgregado() {
        System.out.println("Usuario agregado correctamente.");
    }

    public static void mostrarRolAsignado(TipoRol rol) {
        System.out.println("Rol \"" + rol.name() + "\" asignado al nuevo usuario.");
    }

    public static void mostrarRol(TipoRol rol) {
        System.out.println("El rol del usuario es: " + rol.name());
    }
}

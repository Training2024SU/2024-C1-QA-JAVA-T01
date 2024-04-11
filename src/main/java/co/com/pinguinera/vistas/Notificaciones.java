package co.com.pinguinera.vistas;

import co.com.pinguinera.modelos.TipoRol;

public class Notificaciones {

    public static void mostrarMensajeExito() {
        System.out.println("Proceso completado exitosamente.");
    }

    public static void mostrarMensajeUsuarioAgregado() {
        System.out.println("Usuario agregado correctamente.");
    }

    public static void procesoFallido() {
        System.out.println("No se completó el proceso.");
    }

    public static void mostrarRolAsignado(TipoRol rol) {
        System.out.println("Rol \"" + rol.name() + "\" asignado al nuevo usuario.");
    }
}

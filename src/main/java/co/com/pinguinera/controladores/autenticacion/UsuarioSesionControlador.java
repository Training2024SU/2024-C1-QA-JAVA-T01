package co.com.pinguinera.controladores.autenticacion;

import co.com.pinguinera.servicios.GestorAccesoUsuarios;
import co.com.pinguinera.vistas.VistaUtil;

public class UsuarioSesionControlador {

    private GestorAccesoUsuarios gestorAccesoUsuarios;

    public UsuarioSesionControlador(GestorAccesoUsuarios gestorAccesoUsuarios) {
        this.gestorAccesoUsuarios = gestorAccesoUsuarios;
    }

    /**
     * Gestiona el proceso de inicio de sesión.
     */
    public void iniciarSesion() {
        // Obtén las credenciales del usuario desde la clase VistaUtil
        String correo = VistaUtil.pedirCorreoElectronico();
        String contrasena = VistaUtil.pedirContrasena();

        // Verifica si el usuario existe y las credenciales son correctas usando el servicio
        boolean esUsuarioValido = gestorAccesoUsuarios.verificarUsuario(correo, contrasena);

        // Notifica a VistaUtil del resultado
        if (esUsuarioValido) {
            VistaUtil.mostrarMensajeExito();
        } else {
            VistaUtil.mostrarMensajeError();
        }
    }
}

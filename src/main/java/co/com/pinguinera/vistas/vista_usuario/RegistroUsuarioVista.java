package co.com.pinguinera.vistas.vista_usuario;

import co.com.pinguinera.datos.model.Usuario;
import co.com.pinguinera.vistas.VistaUtil;

public class RegistroUsuarioVista {

    /**
     * Solicita los datos de un nuevo usuario desde la consola.
     * @return Un objeto `Usuario` con los datos ingresados.
     */
    public Usuario pedirDatosUsuario() {
        Usuario usuario = new Usuario();

        // Solicitar nombre del usuario usando VistaUtil
        usuario.setNombre(VistaUtil.pedirNombre());

        // Solicitar correo electrónico del usuario usando VistaUtil
        usuario.setCorreo(VistaUtil.pedirCorreoElectronico());

        // Solicitar contraseña del usuario usando VistaUtil
        usuario.setContrasena(VistaUtil.pedirContrasena());

        // Puedes solicitar otros datos necesarios aquí, como dirección, teléfono, etc.

        return usuario;
    }

    /**
     * Muestra un mensaje de éxito cuando el registro es exitoso.
     */
    public void mostrarMensajeExitoRegistro() {
        VistaUtil.mostrarMensajeExito();
    }

    /**
     * Muestra un mensaje de error si el registro falla.
     * @param mensaje Mensaje de error a mostrar.
     */
    public void mostrarMensajeErrorRegistro(String mensaje) {
        VistaUtil.mostrarMensajeError();
    }
}

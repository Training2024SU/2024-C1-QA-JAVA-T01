package co.com.pinguinera.vistas.vista_usuario;

import co.com.pinguinera.datos.model.Usuario;
import co.com.pinguinera.vistas.VistaUtil;

public class GestionarCuentaUsuario {

    // Método para solicitar los datos a actualizar de un usuario
    public Usuario pedirDatosActualizadosUsuario() {
        Usuario usuarioActualizado = new Usuario();

        System.out.println("Actualizar cuenta del usuario");
        usuarioActualizado.setNombre(VistaUtil.pedirNombre());
        usuarioActualizado.setCorreo(VistaUtil.pedirCorreoElectronico());
        usuarioActualizado.setContrasena(VistaUtil.pedirContrasena());

        return usuarioActualizado;
    }
}

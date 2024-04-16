package co.com.pinguinera.controladores.autenticacion;

import co.com.pinguinera.servicios.GestorAccesoUsuarios;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_usuario.MenuPrincipalUsuario;

public class UsuarioSesionControlador {
    private GestorAccesoUsuarios gestorAccesoUsuarios;
    private MenuPrincipalUsuario menuPrincipalUsuario;

    public UsuarioSesionControlador(GestorAccesoUsuarios gestorAccesoUsuarios) {
        this.gestorAccesoUsuarios = gestorAccesoUsuarios;
    }

    /**
     * Gestiona el proceso de inicio de sesión.
     */
    public void iniciarSesion() {
        String correo = VistaUtil.pedirCorreoElectronico();
        String contrasena = VistaUtil.pedirContrasena();

        boolean esUsuarioValido = gestorAccesoUsuarios.verificarUsuario(correo, contrasena);
        if (esUsuarioValido) {
            VistaUtil.mostrarMensajeExito();
            if (menuPrincipalUsuario != null) {
                menuPrincipalUsuario.mostrarMenu();
            }
        } else {
            VistaUtil.mostrarMensajeError();
        }
    }

    /**
     * Configura el menú principal del usuario para que se muestre después de un inicio de sesión exitoso.
     * @param menuPrincipalUsuario La instancia de MenuPrincipalUsuario a asignar.
     */
    public void setMenuPrincipalUsuario(MenuPrincipalUsuario menuPrincipalUsuario) {
        this.menuPrincipalUsuario = menuPrincipalUsuario;
    }
}

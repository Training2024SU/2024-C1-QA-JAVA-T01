package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.UsuarioDAO;
import co.com.pinguinera.datos.crud_local.CRUDUsuariosLocales;
import co.com.pinguinera.datos.model.Usuario;
import co.com.pinguinera.servicios.integracion.SincronizadorUsuario;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_usuario.InformacionUsuarioVista;

import java.sql.SQLException;
import java.util.List;

public class ControladorCRUDUsuario {

    private final InformacionUsuarioVista vista;
    private final CRUDUsuariosLocales crudUsuariosLocales;
    private final UsuarioDAO usuarioDAO;
    private final SincronizadorUsuario sincronizadorUsuario;

    public ControladorCRUDUsuario(InformacionUsuarioVista vista, CRUDUsuariosLocales crudUsuariosLocales,
                                  UsuarioDAO usuarioDAO, SincronizadorUsuario sincronizadorUsuario) {
        this.vista = vista;
        this.crudUsuariosLocales = crudUsuariosLocales;
        this.usuarioDAO = usuarioDAO;
        this.sincronizadorUsuario = sincronizadorUsuario;
    }

    public void registrarUsuario() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(vista.pedirNombreUsuario());
        nuevoUsuario.setCorreo(vista.pedirCorreoUsuario());
        nuevoUsuario.setContrasena(vista.pedirContrasenaUsuario());
        crudUsuariosLocales.agregar(nuevoUsuario);
        try {
            usuarioDAO.insertar(nuevoUsuario);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarUsuario() {
        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setNombre(vista.pedirNombreUsuario());
        usuarioActualizado.setCorreo(vista.pedirCorreoUsuario());
        usuarioActualizado.setContrasena(vista.pedirContrasenaUsuario());
        crudUsuariosLocales.actualizar(usuarioActualizado);
        try {
            usuarioDAO.actualizar(usuarioActualizado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarUsuario() {
        String correo = vista.pedirCorreoUsuario();
        Usuario usuarioAEliminar = new Usuario();
        usuarioAEliminar.setCorreo(correo);
        crudUsuariosLocales.eliminar(usuarioAEliminar);
        try {
            usuarioDAO.eliminar(usuarioAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void obtenerTodosUsuarios() {
        List<Usuario> usuariosLocales = crudUsuariosLocales.obtenerTodos();
        // Presentar los usuarios locales
        for (Usuario usuario : usuariosLocales) {
            System.out.println(usuario);
        }
    }

    private void sincronizarDatos() {
        try {
            sincronizadorUsuario.sincronizarUsuarios();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }
}

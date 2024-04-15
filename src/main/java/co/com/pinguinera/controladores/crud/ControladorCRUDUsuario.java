package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.UsuarioDAO;
import co.com.pinguinera.datos.crud_local.CRUDUsuariosLocales;
import co.com.pinguinera.datos.model.Usuario;
import co.com.pinguinera.servicios.integracion.SincronizadorUsuario;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_usuario.RegistroUsuarioVista;

import java.sql.SQLException;
import java.util.List;

public class ControladorCRUDUsuario {

    private final RegistroUsuarioVista vista;
    private final CRUDUsuariosLocales crudUsuariosLocales;
    private final UsuarioDAO usuarioDAO;
    private final SincronizadorUsuario sincronizadorUsuario;

    public ControladorCRUDUsuario(RegistroUsuarioVista vista, CRUDUsuariosLocales crudUsuariosLocales,
                                  UsuarioDAO usuarioDAO, SincronizadorUsuario sincronizadorUsuario) {
        this.vista = vista;
        this.crudUsuariosLocales = crudUsuariosLocales;
        this.usuarioDAO = usuarioDAO;
        this.sincronizadorUsuario = sincronizadorUsuario;
    }

    public void registrarUsuario() {
        Usuario nuevoUsuario = vista.pedirDatosUsuario();
        crudUsuariosLocales.agregar(nuevoUsuario);
        try {
            usuarioDAO.insertar(nuevoUsuario);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarUsuario() {
        Usuario usuarioActualizado = vista.pedirDatosUsuario();
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
        Usuario usuarioAEliminar = vista.pedirDatosUsuario();
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
        // Presenta los usuarios locales, esto se haría en la vista.
    }

    private void sincronizarDatos() {
        try {
            sincronizadorUsuario.sincronizarUsuarios();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }
}


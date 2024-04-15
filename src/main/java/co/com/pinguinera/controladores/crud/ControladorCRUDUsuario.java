package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.crud_base_datos.UsuarioPersistencia;
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
    private final UsuarioPersistencia usuarioPersistencia;
    private final SincronizadorUsuario sincronizadorUsuario;

    public ControladorCRUDUsuario(RegistroUsuarioVista vista, CRUDUsuariosLocales crudUsuariosLocales,
                                  UsuarioPersistencia usuarioPersistencia, SincronizadorUsuario sincronizadorUsuario) {
        this.vista = vista;
        this.crudUsuariosLocales = crudUsuariosLocales;
        this.usuarioPersistencia = usuarioPersistencia;
        this.sincronizadorUsuario = sincronizadorUsuario;
    }

    /**
     * Registra un nuevo usuario.
     */
    public void registrarUsuario() {
        Usuario nuevoUsuario = vista.pedirDatosUsuario();
        crudUsuariosLocales.agregar(nuevoUsuario);
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    /**
     * Actualiza un usuario existente.
     */
    public void actualizarUsuario() {
        Usuario usuarioActualizado = vista.pedirDatosUsuario();
        crudUsuariosLocales.actualizar(usuarioActualizado);
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    /**
     * Elimina un usuario existente.
     */
    public void eliminarUsuario() {
        Usuario usuarioAEliminar = vista.pedirDatosUsuario();
        crudUsuariosLocales.eliminar(usuarioAEliminar);
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    /**
     * Muestra todos los usuarios locales.
     */
    public void obtenerTodosUsuarios() {
        List<Usuario> usuariosLocales = crudUsuariosLocales.obtenerTodos();
        // Aquí muestra los usuarios locales, probablemente en otra vista
    }

    /**
     * Sincroniza los datos locales con la base de datos remota.
     */
    private void sincronizarDatos() {
        try {
            sincronizadorUsuario.sincronizarUsuarios();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }
}

package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.crud_base_datos.UsuarioPersistencia;
import co.com.pinguinera.datos.crud_local.CRUDUsuariosLocales;
import co.com.pinguinera.datos.model.Usuario;
import co.com.pinguinera.servicios.integracion.SincronizadorUsuario;
import co.com.pinguinera.vistas.vista_usuario.RegistroUsuarioVista;

import java.sql.SQLException;
import java.util.List;

public class RegistroUsuarioControlador {

    private final RegistroUsuarioVista vista;
    private final CRUDUsuariosLocales crudUsuariosLocales;
    private final UsuarioPersistencia usuarioPersistencia;
    private final SincronizadorUsuario sincronizadorUsuario;

    public RegistroUsuarioControlador(RegistroUsuarioVista vista, CRUDUsuariosLocales crudUsuariosLocales, UsuarioPersistencia usuarioPersistencia, SincronizadorUsuario sincronizadorUsuario) {
        this.vista = vista;
        this.crudUsuariosLocales = crudUsuariosLocales;
        this.usuarioPersistencia = usuarioPersistencia;
        this.sincronizadorUsuario = sincronizadorUsuario;
    }

    /**
     * Gestiona el proceso de registro de un nuevo usuario.
     */
    public void registrarUsuario() {
        // Paso 1: Obtén los datos del nuevo usuario desde la vista
        Usuario nuevoUsuario = vista.pedirDatosUsuario();

        // Paso 2: Agregar el nuevo usuario a la colección local
        crudUsuariosLocales.agregar(nuevoUsuario);

        try {
            // Paso 3: Sincroniza los datos locales con la base de datos
            // Antes de sincronizar, puedes obtener los usuarios de la base de datos
            List<Usuario> usuariosBD = usuarioPersistencia.obtenerTodos();

            // Sincroniza los usuarios locales y de la base de datos usando el SincronizadorUsuario
            sincronizadorUsuario.sincronizarUsuarios();

            // Si el registro es exitoso y la sincronización también, muestra un mensaje de éxito
            vista.mostrarMensajeExitoRegistro();
        } catch (SQLException e) {
            // Si hay un error en la sincronización, muestra un mensaje de error
            vista.mostrarMensajeErrorRegistro("No se pudo sincronizar el usuario: " + e.getMessage());
        }
    }
}

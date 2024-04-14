package co.com.pinguinera.capa_servicios.sincronizacionBD;

import co.com.pinguinera.capa_datos.UsuarioDAO;
import co.com.pinguinera.modelado.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class SincronizadorUsuarios {

    private final UsuarioDAO usuarioDAO;
    private final List<Usuario> usuariosLocales; // Lista local de objetos Usuario

    public SincronizadorUsuarios(UsuarioDAO usuarioDAO, List<Usuario> usuariosLocales) {
        this.usuarioDAO = usuarioDAO;
        this.usuariosLocales = usuariosLocales;
    }

    public void sincronizar() throws SQLException {
        // Obtener todos los registros de Usuario de la base de datos
        List<Usuario> usuariosDeBaseDeDatos = usuarioDAO.obtenerTodos();

        sincronizarUsuariosLocalesConBaseDeDatos(usuariosDeBaseDeDatos);
        eliminarUsuariosNoLocalesDeBaseDeDatos(usuariosDeBaseDeDatos);
    }

    private void sincronizarUsuariosLocalesConBaseDeDatos(List<Usuario> usuariosDeBaseDeDatos) throws SQLException {
        for (Usuario usuarioLocal : usuariosLocales) {
            Usuario usuarioCorrespondienteEnBD = buscarUsuarioEnBaseDeDatos(usuarioLocal, usuariosDeBaseDeDatos);

            if (usuarioCorrespondienteEnBD != null) {
                if (!Objects.equals(usuarioLocal, usuarioCorrespondienteEnBD)) {
                    // Actualizar el usuario en la base de datos
                    usuarioDAO.actualizar(usuarioLocal);
                }
            } else {
                // El usuario local no existe en la base de datos, agregarlo
                usuarioDAO.insertar(usuarioLocal);
            }
        }
    }

    private Usuario buscarUsuarioEnBaseDeDatos(Usuario usuarioLocal, List<Usuario> usuariosDeBaseDeDatos) {
        for (Usuario usuarioBD : usuariosDeBaseDeDatos) {
            if (usuarioLocal.getIdUsuario() == usuarioBD.getIdUsuario()) {
                return usuarioBD;
            }
        }
        return null;
    }

    private void eliminarUsuariosNoLocalesDeBaseDeDatos(List<Usuario> usuariosDeBaseDeDatos) throws SQLException {
        for (Usuario usuarioBD : usuariosDeBaseDeDatos) {
            boolean encontradoEnLocal = false;

            // Buscar si el usuario de la base de datos existe en la lista local
            for (Usuario usuarioLocal : usuariosLocales) {
                if (usuarioBD.getIdUsuario() == usuarioLocal.getIdUsuario()) {
                    encontradoEnLocal = true;
                    break;
                }
            }

            if (!encontradoEnLocal) {
                // El usuario existe en la base de datos pero no en la lista local, eliminarlo
                usuarioDAO.eliminar(usuarioBD.getIdUsuario());
            }
        }
    }
}

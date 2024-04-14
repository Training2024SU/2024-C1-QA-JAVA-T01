package co.com.pinguinera.capa_servicios;

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
        // Obtén todos los registros de Usuario de la base de datos
        List<Usuario> usuariosBD = usuarioDAO.obtenerTodos();

        // Sincronización: agregar, actualizar, eliminar
        for (Usuario usuarioLocal : usuariosLocales) {
            boolean encontradoEnBD = false;

            // Buscar si el usuario local existe en la base de datos
            for (Usuario usuarioBD : usuariosBD) {
                if (usuarioLocal.getIdUsuario() == usuarioBD.getIdUsuario()) {
                    encontradoEnBD = true;

                    // Verificar si hay diferencias entre los objetos
                    if (!Objects.equals(usuarioLocal, usuarioBD)) {
                        // Actualizar el usuario en la base de datos
                        usuarioDAO.actualizar(usuarioLocal);
                    }
                    break;
                }
            }

            if (!encontradoEnBD) {
                // El usuario local no existe en la base de datos, agregarlo
                usuarioDAO.insertar(usuarioLocal);
            }
        }

        // Eliminar los usuarios que existen en la base de datos pero no en la lista local
        for (Usuario usuarioBD : usuariosBD) {
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

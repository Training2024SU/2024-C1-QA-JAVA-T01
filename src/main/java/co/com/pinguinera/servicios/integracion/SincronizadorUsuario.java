package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.crud_base_datos.UsuarioPersistencia;
import co.com.pinguinera.datos.crud_local.CRUDUsuariosLocales;
import co.com.pinguinera.datos.model.Usuario;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorUsuario {

    private final UsuarioPersistencia usuarioPersistencia;
    private final CRUDUsuariosLocales crudUsuariosLocales;

    public SincronizadorUsuario(UsuarioPersistencia usuarioPersistencia, CRUDUsuariosLocales crudUsuariosLocales) {
        this.usuarioPersistencia = usuarioPersistencia;
        this.crudUsuariosLocales = crudUsuariosLocales;
    }

    public void sincronizarUsuarios() throws SQLException {
        // Obtén la lista de usuarios de la base de datos
        List<Usuario> usuariosBD = usuarioPersistencia.obtenerTodos();

        // Obtén la lista de usuarios local
        List<Usuario> usuariosLocales = crudUsuariosLocales.obtenerTodos();

        // Crea un mapa de usuarios locales para búsquedas rápidas
        Map<Integer, Usuario> mapaUsuariosLocales = new HashMap<>();
        for (Usuario usuarioLocal : usuariosLocales) {
            mapaUsuariosLocales.put(usuarioLocal.getIdUsuario(), usuarioLocal);
        }

        // Actualiza usuarios en la base de datos basados en la lista local
        for (Usuario usuarioBD : usuariosBD) {
            Usuario usuarioLocal = mapaUsuariosLocales.get(usuarioBD.getIdUsuario());
            if (usuarioLocal != null) {
                // Si el usuario local está en la base de datos, actualízalo si hay diferencias
                if (!usuarioLocal.equals(usuarioBD)) {
                    usuarioPersistencia.actualizar(usuarioLocal);
                }
                // Elimina el usuario del mapa para que al final sepamos cuáles no estaban en la base de datos
                mapaUsuariosLocales.remove(usuarioLocal.getIdUsuario());
            }
        }

        // Inserta usuarios locales que no están en la base de datos
        for (Usuario usuarioLocal : mapaUsuariosLocales.values()) {
            usuarioPersistencia.insertar(usuarioLocal);
        }
    }
}

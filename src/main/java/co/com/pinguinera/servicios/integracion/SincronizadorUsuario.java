package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.UsuarioDAO;
import co.com.pinguinera.datos.crud_local.CRUDUsuariosLocales;
import co.com.pinguinera.datos.model.Usuario;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorUsuario {

    private final UsuarioDAO usuarioDAO;
    private final CRUDUsuariosLocales crudUsuariosLocales;

    public SincronizadorUsuario(UsuarioDAO usuarioDAO, CRUDUsuariosLocales crudUsuariosLocales) {
        this.usuarioDAO = usuarioDAO;
        this.crudUsuariosLocales = crudUsuariosLocales;
    }

    public void sincronizarUsuarios() throws SQLException {
        List<Usuario> usuariosBD = usuarioDAO.obtenerTodos();
        List<Usuario> usuariosLocales = crudUsuariosLocales.obtenerTodos();

        Map<Integer, Usuario> mapaUsuariosLocales = new HashMap<>();
        for (Usuario usuarioLocal : usuariosLocales) {
            mapaUsuariosLocales.put(usuarioLocal.getIdUsuario(), usuarioLocal);
        }

        for (Usuario usuarioBD : usuariosBD) {
            Usuario usuarioLocal = mapaUsuariosLocales.get(usuarioBD.getIdUsuario());
            if (usuarioLocal != null) {
                if (!usuarioLocal.equals(usuarioBD)) {
                    usuarioDAO.actualizar(usuarioLocal);
                }
                mapaUsuariosLocales.remove(usuarioLocal.getIdUsuario());
            }
        }

        for (Usuario usuarioLocal : mapaUsuariosLocales.values()) {
            usuarioDAO.insertar(usuarioLocal);
        }
    }
}

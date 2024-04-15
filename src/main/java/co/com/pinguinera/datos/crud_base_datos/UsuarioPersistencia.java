package co.com.pinguinera.datos.crud_base_datos;

import co.com.pinguinera.datos.UsuarioDAO;
import co.com.pinguinera.datos.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioPersistencia extends AbstractBaseDatosCRUD<Usuario> {
    private final UsuarioDAO usuarioDAO;

    public UsuarioPersistencia(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void insertar(Usuario usuario) throws SQLException {
        usuarioDAO.insertar(usuario);
    }

    @Override
    public List<Usuario> obtenerTodos() throws SQLException {
        return usuarioDAO.obtenerTodos();
    }

    @Override
    public void actualizar(Usuario usuario) throws SQLException {
        usuarioDAO.actualizar(usuario);
    }

    @Override
    public void eliminar(int id) throws SQLException {
        usuarioDAO.eliminar(id);
    }
}

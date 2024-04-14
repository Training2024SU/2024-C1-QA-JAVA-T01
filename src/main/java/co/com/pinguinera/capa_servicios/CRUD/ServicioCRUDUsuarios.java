package co.com.pinguinera.capa_servicios.CRUD;

import co.com.pinguinera.capa_datos.UsuarioDAO;
import co.com.pinguinera.capa_servicios.interfaces.CRUD;
import co.com.pinguinera.modelado.Usuario;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ServicioCRUDUsuarios implements CRUD<Usuario> {
    private List<Usuario> usuarios;
    private UsuarioDAO usuarioDAO;

    // Constructor
    public ServicioCRUDUsuarios(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        this.usuarios = new ArrayList<>();
        try {
            cargarDatosIniciales();
        } catch (SQLException e) {
            // Manejo de SQLException
            System.err.println("Error al cargar datos iniciales: " + e.getMessage());
            e.printStackTrace();
            // Puedes decidir cómo manejar la excepción aquí, por ejemplo, lanzar una excepción personalizada
        }
    }

    // Carga los datos iniciales desde la base de datos
    private void cargarDatosIniciales() throws SQLException {
        List<Usuario> listaUsuarios = usuarioDAO.obtenerTodos();
        usuarios.addAll(listaUsuarios);
    }

    @Override
    public void agregar(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        int index = usuarios.indexOf(usuario);
        if (index != -1) {
            usuarios.set(index, usuario);
        }
    }

    @Override
    public void eliminar(Usuario usuario) {
        usuarios.remove(usuario);
    }

}

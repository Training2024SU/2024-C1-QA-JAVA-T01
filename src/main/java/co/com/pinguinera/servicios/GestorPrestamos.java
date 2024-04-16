package co.com.pinguinera.servicios;

import co.com.pinguinera.datos.DAO.PrestamoDAO;
import co.com.pinguinera.datos.DAO.UsuarioDAO;
import co.com.pinguinera.datos.model.Prestamo;
import co.com.pinguinera.datos.model.Usuario;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorPrestamos {
    private UsuarioDAO usuarioDAO;
    private PrestamoDAO prestamoDAO;
    private List<Usuario> listaUsuarios;
    private List<Prestamo> listaPrestamos;

    public GestorPrestamos(UsuarioDAO usuarioDAO, PrestamoDAO prestamoDAO) {
        this.usuarioDAO = usuarioDAO;
        this.prestamoDAO = prestamoDAO;
    }

    public void cargarDatos() {
        try {
            listaUsuarios = usuarioDAO.obtenerTodos();
            listaPrestamos = prestamoDAO.obtenerTodos();
        } catch (SQLException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
        }
    }

    public Map<String, List<Prestamo>> relacionarUsuariosYPrestamos() {
        Map<String, List<Prestamo>> prestamosPorCorreo = new HashMap<>();
        for (Usuario usuario : listaUsuarios) {
            List<Prestamo> prestamosUsuario = listaPrestamos.stream()
                    .filter(prestamo -> prestamo.getIdUsuario() == usuario.getIdUsuario())
                    .collect(Collectors.toList());
            prestamosPorCorreo.put(usuario.getCorreo(), prestamosUsuario);
        }
        return prestamosPorCorreo;
    }

    public List<Prestamo> listarPrestamosPorCorreo(String correo) {
        cargarDatos();
        Usuario usuario = listaUsuarios.stream()
                .filter(u -> u.getCorreo().equals(correo))
                .findFirst()
                .orElse(null);
        if (usuario == null) {
            return List.of();
        }
        return listaPrestamos.stream()
                .filter(prestamo -> prestamo.getIdUsuario() == usuario.getIdUsuario())
                .collect(Collectors.toList());
    }
}


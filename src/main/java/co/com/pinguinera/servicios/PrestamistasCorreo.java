package co.com.pinguinera.servicios;

import co.com.pinguinera.datos.DAO.UsuarioDAO;
import co.com.pinguinera.datos.DAO.PrestamoDAO;
import co.com.pinguinera.datos.model.Usuario;
import co.com.pinguinera.datos.model.Prestamo;
import co.com.pinguinera.LoggerUtil;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class PrestamistasCorreo {

    private static final Logger LOGGER = LoggerUtil.getLogger();

    private final UsuarioDAO usuarioDAO;
    private final PrestamoDAO prestamosDAO;

    private List<Usuario> listaUsuariosLocales;
    private List<Prestamo> listaPrestamosLocales;

    public PrestamistasCorreo(UsuarioDAO usuarioDAO, PrestamoDAO prestamosDAO) {
        this.usuarioDAO = usuarioDAO;
        this.prestamosDAO = prestamosDAO;
        cargarDatosLocales();
    }

    // Método para cargar los datos de usuarios y préstamos de la base de datos y almacenarlos localmente
    private void cargarDatosLocales() {
        try {
            listaUsuariosLocales = usuarioDAO.obtenerTodos();
            listaPrestamosLocales = prestamosDAO.obtenerTodos();
        } catch (Exception e) {
            LOGGER.warning("No se pudo cargar los datos locales: " + e.getMessage());
        }
    }

    public List<Prestamo> listarPrestamosPorCorreo(String correo) {
        // Buscar el usuario por correo
        Optional<Usuario> usuarioOpt = listaUsuariosLocales.stream()
                .filter(usuario -> usuario.getCorreo().equalsIgnoreCase(correo))
                .findFirst();

        if (usuarioOpt.isEmpty()) {
            LOGGER.warning("No se encontró un usuario con el correo proporcionado.");
            return List.of(); // Retornar una lista vacía si no se encuentra el usuario
        }

        // Obtener el ID del usuario
        int idUsuario = usuarioOpt.get().getIdUsuario();

        // Filtrar y obtener la lista de préstamos del usuario y retornarla directamente
        return listaPrestamosLocales.stream()
                .filter(prestamo -> prestamo.getIdUsuario() == idUsuario)
                .toList();
    }

}

package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.capa_datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.capa_datos.UsuarioDAO;
import co.com.pinguinera.capa_servicios.ServicioCRUDUsuarios;
import co.com.pinguinera.capa_servicios.SincronizadorUsuarios;
import co.com.pinguinera.modelado.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Crea una instancia de BaseDatosImpl
            BaseDatosImpl baseDatosImpl = new BaseDatosImpl(conexion);

            // Crea una instancia de UsuarioDAO utilizando BaseDatosImpl
            UsuarioDAO usuarioDAO = new UsuarioDAO(baseDatosImpl);

            // Crea una instancia de ServicioCRUDUsuarios
            ServicioCRUDUsuarios servicioUsuarios = new ServicioCRUDUsuarios(usuarioDAO);

            // Añade nuevos usuarios a la colección local
            // Añade nuevos usuarios a la colección local
            Usuario usuario1 = new Usuario(1, "juan.perez@example.com", "Juan", "1234");
            Usuario usuario2 = new Usuario(2, "maria.lopez@example.com", "Maria", "5678");
            Usuario usuario3 = new Usuario(3, "carlos.gomez@example.com", "Carlos", "abcd");
            Usuario usuario4 = new Usuario(4, "ana.martinez@example.com", "Ana", "efgh");
            Usuario usuario5 = new Usuario(5, "luis.rodriguez@example.com", "Luis", "ijkl");
            Usuario usuario6 = new Usuario(6, "laura.ramos@example.com", "Laura", "mnop");

// Agrega los usuarios creados al servicio CRUD de usuarios
            servicioUsuarios.agregar(usuario1);
            servicioUsuarios.agregar(usuario2);
            servicioUsuarios.agregar(usuario3);
            servicioUsuarios.agregar(usuario4);
            servicioUsuarios.agregar(usuario5);
            servicioUsuarios.agregar(usuario6);



            // Muestra todos los usuarios de la colección local
            List<Usuario> usuarios = servicioUsuarios.obtenerTodos();
            System.out.println("Lista de usuarios en la colección local:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }

            // Crea una instancia de SincronizadorUsuarios
            // con UsuarioDAO y la lista de usuarios de ServicioCRUDUsuarios
            SincronizadorUsuarios sincronizadorUsuarios = new SincronizadorUsuarios(usuarioDAO, usuarios);

            // Sincroniza los cambios en la colección local con la base de datos
            sincronizadorUsuarios.sincronizar();
            System.out.println("Colección local de usuarios sincronizada con la base de datos.");

        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

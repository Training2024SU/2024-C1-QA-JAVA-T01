package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.capa_datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.capa_datos.UsuarioDAO;
import co.com.pinguinera.capa_servicios.GestorAccesoUsuarios;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Bloque `try-with-resources` para manejar la conexión de base de datos
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Crear una instancia de `BaseDatosImpl` implementando `GestorBD` usando la conexión
            GestorBD gestorBD = new BaseDatosImpl(conexion);

            // Crear una instancia de `UsuarioDAO` utilizando `gestorBD`
            UsuarioDAO usuarioDAO = new UsuarioDAO(gestorBD);

            // Crear una instancia de `GestorAccesoUsuarios` utilizando `usuarioDAO`
            GestorAccesoUsuarios gestorAccesoUsuarios = new GestorAccesoUsuarios(usuarioDAO);

            // Prueba de verificar si un usuario específico existe
            // Ejemplo de credenciales
            String correo = "juan.perez@example.com";
            String contrasena = "1233453424";

            // Verificar si el usuario con las credenciales dadas existe
            boolean accesoConcedido = gestorAccesoUsuarios.verificarUsuario(correo, contrasena);

            // Mostrar el resultado de la verificación
            if (accesoConcedido) {
                System.out.println("Acceso concedido al usuario con correo: " + correo);
            } else {
                System.out.println("Acceso denegado para el usuario con correo: " + correo);
            }
        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

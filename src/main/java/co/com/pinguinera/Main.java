package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.capa_datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.capa_datos.EmpleadoDAO;
import co.com.pinguinera.capa_servicios.GestorAccesoEmpleados;


import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Bloque `try-with-resources` para manejar la conexión de base de datos
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Crear una instancia de `BaseDatosImpl` implementando `GestorBD` usando la conexión
            GestorBD gestorBD = new BaseDatosImpl(conexion);

            // Crear una instancia de `EmpleadoDAO` utilizando `gestorBD`
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(gestorBD);

            // Crear una instancia de `GestorAccesoEmpleados` utilizando `empleadoDAO`
            GestorAccesoEmpleados gestorAccesoEmpleados = new GestorAccesoEmpleados(empleadoDAO);

            // Prueba de verificar si un empleado específico existe y obtener su rol
            // Ejemplo de credenciales
            String correo = "administrador@pingu.com.co";
            String contrasena = "contraseñasegura123456";

            // Verificar si el empleado con las credenciales dadas existe y obtener su rol
            String rolEmpleado = gestorAccesoEmpleados.verificarEmpleado(correo, contrasena);

            // Mostrar el resultado de la verificación
            if (rolEmpleado != null) {
                System.out.println("Acceso concedido al empleado con correo: " + correo + ". Rol: " + rolEmpleado);
            } else {
                System.out.println("Acceso denegado para el empleado con correo: " + correo);
            }
        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

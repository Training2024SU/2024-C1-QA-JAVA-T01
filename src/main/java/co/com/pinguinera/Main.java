package co.com.pinguinera;

import co.com.pinguinera.controladores.autenticacion.UsuarioSesionControlador;
import co.com.pinguinera.controladores.autenticacion.EmpleadoSesionControlador;
import co.com.pinguinera.controladores.crud.RegistroUsuarioControlador;
import co.com.pinguinera.datos.UsuarioDAO;
import co.com.pinguinera.datos.EmpleadoDAO;
import co.com.pinguinera.datos.conexionBD.ConexionBD;
import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.servicios.GestorAccesoUsuarios;
import co.com.pinguinera.servicios.GestorAccesoEmpleados;
import co.com.pinguinera.datos.crud_base_datos.UsuarioPersistencia;
import co.com.pinguinera.datos.crud_base_datos.EmpleadoPersistencia;
import co.com.pinguinera.datos.crud_local.CRUDUsuariosLocales;
import co.com.pinguinera.datos.crud_local.CRUDEmpleadosLocales;
import co.com.pinguinera.servicios.integracion.SincronizadorUsuario;
import co.com.pinguinera.servicios.integracion.SincronizadorEmpleado;
import co.com.pinguinera.vistas.vista_usuario.RegistroUsuarioVista;
import co.com.pinguinera.vistas.MenuPrincipal;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Bloque `try-with-resources` para manejar la conexión de base de datos
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Crear instancias de `BaseDatosImpl` y `UsuarioDAO`
            GestorBD gestorBD = new BaseDatosImpl(conexion);
            UsuarioDAO usuarioDAO = new UsuarioDAO(gestorBD);
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(gestorBD);

            // Crear instancias de CRUD locales y persistencias
            CRUDUsuariosLocales crudUsuariosLocales = new CRUDUsuariosLocales();
            UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(usuarioDAO);
            CRUDEmpleadosLocales crudEmpleadosLocales = new CRUDEmpleadosLocales();
            EmpleadoPersistencia empleadoPersistencia = new EmpleadoPersistencia(empleadoDAO);

            // Crear instancias de servicios de acceso y sincronizadores
            GestorAccesoUsuarios gestorAccesoUsuarios = new GestorAccesoUsuarios(usuarioDAO);
            GestorAccesoEmpleados gestorAccesoEmpleados = new GestorAccesoEmpleados(empleadoDAO);
            SincronizadorUsuario sincronizadorUsuario = new SincronizadorUsuario(usuarioPersistencia, crudUsuariosLocales);
            SincronizadorEmpleado sincronizadorEmpleado = new SincronizadorEmpleado(empleadoPersistencia, crudEmpleadosLocales);

            // Crear vistas
            RegistroUsuarioVista registroUsuarioVista = new RegistroUsuarioVista();

            // Crear controladores
            UsuarioSesionControlador usuarioSesionControlador = new UsuarioSesionControlador(gestorAccesoUsuarios);
            EmpleadoSesionControlador empleadoSesionControlador = new EmpleadoSesionControlador(gestorAccesoEmpleados);
            RegistroUsuarioControlador registroUsuarioControlador = new RegistroUsuarioControlador(registroUsuarioVista, crudUsuariosLocales, usuarioPersistencia, sincronizadorUsuario);

            // Crear instancia de `MenuPrincipal` y mostrar el menú
            MenuPrincipal menuPrincipal = new MenuPrincipal(empleadoSesionControlador, usuarioSesionControlador, registroUsuarioControlador);
            menuPrincipal.mostrarMenu();

        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

package co.com.pinguinera;

import co.com.pinguinera.controladores.autenticacion.UsuarioSesionControlador;
import co.com.pinguinera.controladores.autenticacion.EmpleadoSesionControlador;
import co.com.pinguinera.controladores.crud.ControladorCRUDUsuario;
import co.com.pinguinera.controladores.crud.ControladorCRUDPrestamo;
import co.com.pinguinera.datos.DAO.UsuarioDAO;
import co.com.pinguinera.datos.DAO.EmpleadoDAO;
import co.com.pinguinera.datos.DAO.PrestamoDAO;
import co.com.pinguinera.datos.conexionBD.ConexionBD;
import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.datos.crud_local.CRUDUsuariosLocales;
import co.com.pinguinera.datos.crud_local.CRUDEmpleadosLocales;
import co.com.pinguinera.datos.crud_local.CRUDPrestamosLocales;
import co.com.pinguinera.servicios.GestorAccesoUsuarios;
import co.com.pinguinera.servicios.GestorAccesoEmpleados;
import co.com.pinguinera.servicios.integracion.SincronizadorUsuario;
import co.com.pinguinera.servicios.integracion.SincronizadorEmpleado;
import co.com.pinguinera.servicios.integracion.SincronizadorPrestamos;
import co.com.pinguinera.vistas.MenuPrincipal;
import co.com.pinguinera.vistas.vista_usuario.MenuPrincipalUsuario;
import co.com.pinguinera.vistas.vista_usuario.InformacionUsuarioVista;
import co.com.pinguinera.vistas.vistas_prestamo.InformacionPrestamoVista;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            GestorBD gestorBD = new BaseDatosImpl(conexion);
            UsuarioDAO usuarioDAO = new UsuarioDAO(gestorBD);
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(gestorBD);
            PrestamoDAO prestamoDAO = new PrestamoDAO(gestorBD);

            CRUDUsuariosLocales crudUsuariosLocales = new CRUDUsuariosLocales();
            CRUDEmpleadosLocales crudEmpleadosLocales = new CRUDEmpleadosLocales();
            CRUDPrestamosLocales crudPrestamosLocales = new CRUDPrestamosLocales();

            SincronizadorUsuario sincronizadorUsuario = new SincronizadorUsuario(usuarioDAO, crudUsuariosLocales);
            SincronizadorEmpleado sincronizadorEmpleado = new SincronizadorEmpleado(empleadoDAO, crudEmpleadosLocales);
            SincronizadorPrestamos sincronizadorPrestamos = new SincronizadorPrestamos(prestamoDAO, crudPrestamosLocales);

            InformacionUsuarioVista informacionUsuarioVista = new InformacionUsuarioVista();
            InformacionPrestamoVista informacionPrestamoVista = new InformacionPrestamoVista();

            GestorAccesoUsuarios gestorAccesoUsuarios = new GestorAccesoUsuarios(usuarioDAO);
            GestorAccesoEmpleados gestorAccesoEmpleados = new GestorAccesoEmpleados(empleadoDAO);

            UsuarioSesionControlador usuarioSesionControlador = new UsuarioSesionControlador(gestorAccesoUsuarios);
            EmpleadoSesionControlador empleadoSesionControlador = new EmpleadoSesionControlador(gestorAccesoEmpleados);

            ControladorCRUDUsuario controladorCRUDUsuario = new ControladorCRUDUsuario(
                    informacionUsuarioVista,
                    crudUsuariosLocales,
                    usuarioDAO,
                    sincronizadorUsuario
            );

            ControladorCRUDPrestamo controladorCRUDPrestamo = new ControladorCRUDPrestamo(
                    informacionPrestamoVista,
                    crudPrestamosLocales,
                    prestamoDAO,
                    sincronizadorPrestamos
            );

            // Crear instancia de MenuPrincipalUsuario
            MenuPrincipalUsuario menuPrincipalUsuario = new MenuPrincipalUsuario(
                    controladorCRUDUsuario,
                    controladorCRUDPrestamo
            );

            // Asignar MenuPrincipalUsuario a UsuarioSesionControlador
            usuarioSesionControlador.setMenuPrincipalUsuario(menuPrincipalUsuario);

            MenuPrincipal menuPrincipal = new MenuPrincipal(
                    empleadoSesionControlador,
                    usuarioSesionControlador,
                    controladorCRUDUsuario
            );
            menuPrincipal.mostrarMenu();

        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

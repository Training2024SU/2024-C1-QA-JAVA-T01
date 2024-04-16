package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.autenticacion.UsuarioSesionControlador;
import co.com.pinguinera.controladores.autenticacion.EmpleadoSesionControlador;
import co.com.pinguinera.controladores.crud.*;
import co.com.pinguinera.datos.DAO.*;
import co.com.pinguinera.datos.crud_local.*;
import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.servicios.GestorAccesoUsuarios;
import co.com.pinguinera.servicios.GestorAccesoEmpleados;
import co.com.pinguinera.servicios.integracion.*;
import co.com.pinguinera.vistas.*;
import co.com.pinguinera.vistas.vista_empleado.InformacionEmpleadoVista;
import co.com.pinguinera.vistas.vista_usuario.MenuPrincipalUsuario;
import co.com.pinguinera.vistas.vista_usuario.InformacionUsuarioVista;
import co.com.pinguinera.vistas.vistas_administrativo.MenuPrincipalAdministrativo;
import co.com.pinguinera.vistas.vistas_asistente.MenuPrincipalAsistente;
import co.com.pinguinera.vistas.vistas_libro.InformacionLibroVista;
import co.com.pinguinera.vistas.vistas_novela.InformacionNovelaVista;
import co.com.pinguinera.vistas.vistas_prestamo.InformacionPrestamoVista;

import java.sql.Connection;
import java.sql.SQLException;

public class MenuPrincipalFactory {

    public static MenuPrincipal crearMenuPrincipal(Connection conexion) throws SQLException {
        // Crear instancias de GestorBD
        GestorBD gestorBD = new BaseDatosImpl(conexion);

        // Crear instancias de los DAOs
        UsuarioDAO usuarioDAO = new UsuarioDAO(gestorBD);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO(gestorBD);
        PrestamoDAO prestamoDAO = new PrestamoDAO(gestorBD);
        LibroDAO libroDAO = new LibroDAO(gestorBD);
        NovelaDAO novelaDAO = new NovelaDAO(gestorBD);

        // Crear instancias de los CRUD locales
        CRUDUsuariosLocales crudUsuariosLocales = new CRUDUsuariosLocales();
        CRUDEmpleadosLocales crudEmpleadosLocales = new CRUDEmpleadosLocales();
        CRUDPrestamosLocales crudPrestamosLocales = new CRUDPrestamosLocales();
        CRUDLibrosLocales crudLibrosLocales = new CRUDLibrosLocales();
        CRUDNovelasLocales crudNovelasLocales = new CRUDNovelasLocales();

        // Crear instancias de los sincronizadores
        SincronizadorUsuario sincronizadorUsuario = new SincronizadorUsuario(usuarioDAO, crudUsuariosLocales);
        SincronizadorEmpleado sincronizadorEmpleado = new SincronizadorEmpleado(empleadoDAO, crudEmpleadosLocales);
        SincronizadorPrestamos sincronizadorPrestamos = new SincronizadorPrestamos(prestamoDAO, crudPrestamosLocales);
        SincronizadorLibros sincronizadorLibros = new SincronizadorLibros(libroDAO, crudLibrosLocales);
        SincronizadorNovelas sincronizadorNovelas = new SincronizadorNovelas(novelaDAO, crudNovelasLocales);

        // Crear instancias de las vistas
        InformacionUsuarioVista informacionUsuarioVista = new InformacionUsuarioVista();
        InformacionEmpleadoVista informacionEmpleadoVista = new InformacionEmpleadoVista();
        InformacionPrestamoVista informacionPrestamoVista = new InformacionPrestamoVista();
        InformacionLibroVista informacionLibroVista = new InformacionLibroVista();
        InformacionNovelaVista informacionNovelaVista = new InformacionNovelaVista();

        // Crear instancias de los gestores de acceso
        GestorAccesoUsuarios gestorAccesoUsuarios = new GestorAccesoUsuarios(usuarioDAO);
        GestorAccesoEmpleados gestorAccesoEmpleados = new GestorAccesoEmpleados(empleadoDAO);

        // Crear instancias de los controladores de sesión
        UsuarioSesionControlador usuarioSesionControlador = new UsuarioSesionControlador(gestorAccesoUsuarios);

        // Crear instancias de los controladores CRUD
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

        ControladorCRUDLibro controladorCRUDLibro = new ControladorCRUDLibro(
                informacionLibroVista,
                crudLibrosLocales,
                libroDAO,
                sincronizadorLibros
        );

        ControladorCRUDNovela controladorCRUDNovela = new ControladorCRUDNovela(
                informacionNovelaVista,
                crudNovelasLocales,
                novelaDAO,
                sincronizadorNovelas
        );

        ControladorCRUDEmpleado controladorCRUDEmpleado = new ControladorCRUDEmpleado(
                informacionEmpleadoVista,
                crudEmpleadosLocales,
                empleadoDAO,
                sincronizadorEmpleado
        );

        // Crear instancia de MenuPrincipalUsuario con los cuatro controladores: Usuario, Prestamo, Libro y Novela
        MenuPrincipalUsuario menuPrincipalUsuario = new MenuPrincipalUsuario(
                controladorCRUDUsuario,
                controladorCRUDPrestamo,
                controladorCRUDLibro,
                controladorCRUDNovela
        );

        // Asignar MenuPrincipalUsuario a UsuarioSesionControlador
        usuarioSesionControlador.setMenuPrincipalUsuario(menuPrincipalUsuario);

        // Crear instancia de MenuPrincipalAsistente
        MenuPrincipalAsistente menuPrincipalAsistente = new MenuPrincipalAsistente(
                controladorCRUDLibro,
                controladorCRUDNovela,
                controladorCRUDPrestamo
        );

        // Crear instancia de MenuPrincipalAdministrativo
        MenuPrincipalAdministrativo menuPrincipalAdministrativo = new MenuPrincipalAdministrativo(
                controladorCRUDUsuario,
                controladorCRUDPrestamo,
                controladorCRUDEmpleado,
                controladorCRUDNovela,
                controladorCRUDLibro
        );

        // Crear instancia de EmpleadoSesionControlador con GestorAccesoEmpleados, MenuPrincipalAsistente y MenuPrincipalAdministrativo
        EmpleadoSesionControlador empleadoSesionControlador = new EmpleadoSesionControlador(
                gestorAccesoEmpleados,
                menuPrincipalAsistente,
                menuPrincipalAdministrativo
        );

        // Crear instancia de MenuPrincipal
        MenuPrincipal menuPrincipal = new MenuPrincipal(
                empleadoSesionControlador,
                usuarioSesionControlador,
                controladorCRUDUsuario
        );

        return menuPrincipal;
    }
}

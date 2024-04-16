package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.EmpleadoDAO;
import co.com.pinguinera.datos.crud_local.CRUDEmpleadosLocales;
import co.com.pinguinera.datos.model.Empleado;
import co.com.pinguinera.servicios.integracion.SincronizadorEmpleado;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_empleado.InformacionEmpleadoVista;
import co.com.pinguinera.vistas.vista_usuario.InformacionUsuarioVista;

import java.sql.SQLException;
import java.util.List;

public class ControladorCRUDEmpleado {

    private final InformacionEmpleadoVista vista;
    private final CRUDEmpleadosLocales crudEmpleadosLocales;
    private final EmpleadoDAO empleadoDAO;
    private final SincronizadorEmpleado sincronizadorEmpleado;

    public ControladorCRUDEmpleado(InformacionEmpleadoVista vista, CRUDEmpleadosLocales crudEmpleadosLocales,
                                   EmpleadoDAO empleadoDAO, SincronizadorEmpleado sincronizadorEmpleado) {
        this.vista = vista;
        this.crudEmpleadosLocales = crudEmpleadosLocales;
        this.empleadoDAO = empleadoDAO;
        this.sincronizadorEmpleado = sincronizadorEmpleado;
    }

    public void registrarEmpleado() {
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre(vista.pedirNombre());
        nuevoEmpleado.setCorreo(vista.pedirCorreo());
        nuevoEmpleado.setContrasena(vista.pedirContrasena());
        nuevoEmpleado.setRol(vista.pedirRol());
        nuevoEmpleado.setEsAdministrativo(vista.pedirEsAdministrativo());
        crudEmpleadosLocales.agregar(nuevoEmpleado);
        try {
            empleadoDAO.insertar(nuevoEmpleado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeEmpleadoAdministrativoExistente();
            return;
        }
        sincronizarDatos();
    }


    public void obtenerTodosEmpleados() {
        try {
            List<Empleado> empleados = empleadoDAO.obtenerTodos();
            empleados.forEach(System.out::println);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeSolicitudFallida();
        }
    }


    public void actualizarEmpleado() {
        Empleado empleadoActualizado = new Empleado();
        empleadoActualizado.setNombre(vista.pedirNombre());
        empleadoActualizado.setCorreo(vista.pedirCorreo());
        empleadoActualizado.setContrasena(vista.pedirContrasena());
        empleadoActualizado.setRol(vista.pedirRol());
        empleadoActualizado.setEsAdministrativo(vista.pedirEsAdministrativo());
        crudEmpleadosLocales.actualizar(empleadoActualizado);
        try {
            empleadoDAO.actualizar(empleadoActualizado);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        sincronizarDatos();
    }

    public void eliminarEmpleado() {
        String correo = vista.pedirCorreo();
        Empleado empleadoAEliminar = new Empleado();
        empleadoAEliminar.setCorreo(correo);
        crudEmpleadosLocales.eliminar(empleadoAEliminar);
        try {
            empleadoDAO.eliminar(empleadoAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }


    private void sincronizarDatos() {
        try {
            sincronizadorEmpleado.sincronizarEmpleados();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

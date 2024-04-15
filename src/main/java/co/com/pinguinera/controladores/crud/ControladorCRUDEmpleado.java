package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.EmpleadoDAO;
import co.com.pinguinera.datos.crud_local.CRUDEmpleadosLocales;
import co.com.pinguinera.datos.model.Empleado;
import co.com.pinguinera.servicios.integracion.SincronizadorEmpleado;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_empleado.RegistroEmpleadoVista;

import java.sql.SQLException;

public class ControladorCRUDEmpleado {

    private final RegistroEmpleadoVista vista;
    private final CRUDEmpleadosLocales crudEmpleadosLocales;
    private final EmpleadoDAO empleadoDAO;
    private final SincronizadorEmpleado sincronizadorEmpleado;

    public ControladorCRUDEmpleado(RegistroEmpleadoVista vista, CRUDEmpleadosLocales crudEmpleadosLocales,
                                   EmpleadoDAO empleadoDAO, SincronizadorEmpleado sincronizadorEmpleado) {
        this.vista = vista;
        this.crudEmpleadosLocales = crudEmpleadosLocales;
        this.empleadoDAO = empleadoDAO;
        this.sincronizadorEmpleado = sincronizadorEmpleado;
    }

    public void registrarEmpleado() {
        Empleado nuevoEmpleado = vista.pedirDatosEmpleado();
        try {
            crudEmpleadosLocales.agregar(nuevoEmpleado);
            empleadoDAO.insertar(nuevoEmpleado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarEmpleado() {
        Empleado empleadoActualizado = vista.pedirDatosEmpleado();
        try {
            crudEmpleadosLocales.actualizar(empleadoActualizado);
            empleadoDAO.actualizar(empleadoActualizado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarEmpleado() {
        Empleado empleadoAEliminar = vista.pedirDatosEmpleado();
        try {
            crudEmpleadosLocales.eliminar(empleadoAEliminar);
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
            VistaUtil.mostrarMensajeError();
        }
    }
}

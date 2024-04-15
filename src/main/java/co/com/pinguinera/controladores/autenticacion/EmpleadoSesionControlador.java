package co.com.pinguinera.controladores.autenticacion;

import co.com.pinguinera.servicios.GestorAccesoEmpleados;
import co.com.pinguinera.vistas.VistaUtil;

public class EmpleadoSesionControlador {

    private GestorAccesoEmpleados gestorAccesoEmpleados;

    public EmpleadoSesionControlador(GestorAccesoEmpleados gestorAccesoEmpleados) {
        this.gestorAccesoEmpleados = gestorAccesoEmpleados;
    }

    /**
     * Gestiona el proceso de inicio de sesión para empleados.
     */
    public void iniciarSesion() {
        // Obtén las credenciales del empleado desde `VistaUtil`
        String correo = VistaUtil.pedirCorreoElectronico();
        String contrasena = VistaUtil.pedirContrasena();

        // Verifica si el empleado existe y las credenciales son correctas usando el servicio
        String rolEmpleado = gestorAccesoEmpleados.verificarEmpleado(correo, contrasena);

        // Notifica a la vista del resultado
        if (rolEmpleado != null) {
            VistaUtil.mostrarMensajeExito();
            System.out.println("Rol del empleado: " + rolEmpleado);
        } else {
            VistaUtil.mostrarMensajeError();
        }
    }
}

package co.com.pinguinera.controladores.autenticacion;

import co.com.pinguinera.datos.model.enums.RolEmpleado;
import co.com.pinguinera.servicios.GestorAccesoEmpleados;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vistas_asistente.MenuPrincipalAsistente;
import co.com.pinguinera.vistas.vistas_administrativo.MenuPrincipalAdministrativo;

public class EmpleadoSesionControlador {

    private GestorAccesoEmpleados gestorAccesoEmpleados;
    private MenuPrincipalAsistente menuPrincipalAsistente;
    private MenuPrincipalAdministrativo menuPrincipalAdministrativo;

    public EmpleadoSesionControlador(GestorAccesoEmpleados gestorAccesoEmpleados,
                                     MenuPrincipalAsistente menuPrincipalAsistente,
                                     MenuPrincipalAdministrativo menuPrincipalAdministrativo) {
        this.gestorAccesoEmpleados = gestorAccesoEmpleados;
        this.menuPrincipalAsistente = menuPrincipalAsistente;
        this.menuPrincipalAdministrativo = menuPrincipalAdministrativo;
    }

    /**
     * Gestiona el proceso de inicio de sesión para empleados.
     */
    public void iniciarSesion() {
        String correo = VistaUtil.pedirCorreoElectronico();
        String contrasena = VistaUtil.pedirContrasena();

        RolEmpleado rolEmpleado = RolEmpleado.valueOf(gestorAccesoEmpleados.verificarEmpleado(correo, contrasena));

        if (rolEmpleado != null) {
            VistaUtil.mostrarMensajeExito();

            if (rolEmpleado == RolEmpleado.ASISTENTE) {
                menuPrincipalAsistente.mostrarMenu();
            } else if (rolEmpleado == RolEmpleado.ADMINISTRATIVO) {
                menuPrincipalAdministrativo.mostrarMenu();
            } else {
                System.out.println("Rol del empleado desconocido. No se puede mostrar un menú.");
            }
        }
    }
}

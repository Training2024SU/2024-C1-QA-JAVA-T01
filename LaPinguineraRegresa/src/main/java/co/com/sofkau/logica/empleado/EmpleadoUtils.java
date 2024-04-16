package co.com.sofkau.logica.empleado;

import co.com.sofkau.model.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoUtils {


    public static void imprimirEmpleados() {
        ArrayList<Empleado> empleados = Empleado.getEmpleados();
        imprimirInformacionEmpleados(empleados);
    }

    public static List<Empleado> filtrarEmpleadosPorCorreo(String correo) {

        ArrayList<Empleado> empleados = Empleado.getEmpleados();

        List<Empleado> empleadosFiltrados = empleados.stream()
                .filter(empleado -> empleado.getCorreo().equals(correo))
                .collect(Collectors.toList());

        if (empleadosFiltrados.isEmpty()) {
            System.out.println("No se encontraron empleados con el correo electrónico: " + correo);
        } else {
            System.out.println("Empleados con el correo electrónico '" + correo + "':");
            imprimirInformacionEmpleados(empleadosFiltrados);
        }

        return empleadosFiltrados;
    }

    private static void imprimirInformacionEmpleados(List<Empleado> listEmpleadosImprimir) {
        for (Empleado empleado : listEmpleadosImprimir) {
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Correo: " + empleado.getCorreo());
            System.out.println("Clave: " + empleado.getClave());
            System.out.println("Rol: " + empleado.getRol());
            System.out.println("-------------------------");
        }
    }

    public static boolean iniciarSesion(String correo, String clave) {

        ArrayList<Empleado> empleados = Empleado.getEmpleados();

        for (Empleado empleado : empleados) {
            if (empleado.getCorreo().equals(correo) && empleado.getClave().equals(clave)) {
                System.out.println("Inicio de sesión exitoso para el empleado con correo: " + correo);
                return true;
            }
        }
        System.out.println("Correo o clave incorrectos. Inicio de sesión fallido.");
        return false;
    }


}

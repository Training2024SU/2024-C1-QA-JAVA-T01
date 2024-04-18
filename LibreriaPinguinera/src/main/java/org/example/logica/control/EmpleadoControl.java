package org.example.logica.control;

import org.example.integration.database.persistencia.CrudEmpleado;
import org.example.model.Empleado;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.dialog.Menu.*;
import static org.example.excelcsv.ExportarLibro.reporte;
import static org.example.logica.control.ConstantesLogica.*;
import static org.example.logica.control.MetodosMain.pedirOpcion;
import static org.example.logica.control.PrestamoControl.finalizarPrestamo;
import static org.example.logica.control.PrestamoControl.realizarPrestamo;
import static org.example.logica.control.PublicacionControl.*;
import static org.example.model.Empleado.empleados;

public class EmpleadoControl {
    static CrudEmpleado crudEmpleado = new CrudEmpleado();
    private static final Logger logger=Logger.getLogger(EmpleadoControl.class.getName());

    public EmpleadoControl() {
        crudEmpleado = new CrudEmpleado();
    }


    public static void mostrarMenuEmpleado(Empleado empleado) throws SQLException {
        if (empleado != null) {
            if (empleado.getRol().equalsIgnoreCase(TIPO_ASISTENTE)) {
                mostrarMenuAsistente();
            } else if (empleado.getRol().equalsIgnoreCase(TIPO_ADMINISTRADOR)) {
                mostrarMenuAdministrador();
            }
        }
    }


    public Empleado iniciarSesionEmpleado() throws SQLException {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        if (empleados.isEmpty()) {
            crudEmpleado.seleccionarDatos();
        }
        List<Empleado> empleados = Empleado.empleados;

        System.out.println(String.format(MSN_CORREO, TIPO_EMPLEADO));
        String correo = scanner.nextLine();
        System.out.println(String.format(MSN_CONTRASENA, TIPO_EMPLEADO));
        String contrasena = scanner.nextLine();

        if (validarCredencialesEmpleado(empleados, correo, contrasena)) {
            return obtenerEmpleado(correo);
        }
        return null;
    }

    private static Empleado obtenerEmpleado(String correo) {
        Empleado empleado;
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getCorreo().equalsIgnoreCase(correo)) {
                empleado = empleados.get(i);
                return empleado;
            }
        }
        return null;
    }

    private static boolean validarCredencialesEmpleado(List<Empleado> empleados, String correo, String contrasena) {
        for (Empleado empleado : empleados) {
            if (empleado.getCorreo().equalsIgnoreCase(correo) && empleado.getContrasenna().equals(contrasena)) {
                System.out.println(INICIO_EXITOSO);
                return true;
            }
        }
        System.out.println(INICIO_FALLIDO);
        return false;
    }

    //opciones asistente
    private static void mostrarMenuAsistente() throws SQLException {
        boolean bandera = true;
        int opcion;
        while (bandera) {
            menuAsistente();
            opcion = pedirOpcion();
            bandera = seleccionarOpcionesAsistente(opcion, bandera);
        }
    }

    public static boolean seleccionarOpcionesAsistente(int opcion, boolean bandera) throws SQLException {
        switch (opcion) {
            case 1:
                gestionarPublicaciones();
                break;
            case 2:
                gestionarPrestamos();
                break;
            case 0:
                bandera = false;
            default:
                System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }


    public static void gestionarPublicaciones() throws SQLException {
        boolean bandera = true;
        int opcion;
        while (bandera) {
            menuGestionarPublicaciones();
            opcion = pedirOpcion();
            bandera = seleccionarOpcionPublicaciones(opcion, bandera);
        }
    }

    public static boolean seleccionarOpcionPublicaciones(int opcion, boolean bandera) throws SQLException {
        switch (opcion) {
            case 1:
                crearPublicacion();
                break;
            case 2:
                actualizarPublicacion();
                break;
            case 3:
                mostrarPublicaciones();
                break;
            case 4:
                eliminarPublicacion();
                break;
            case 5:
                reporte();
            case 0:
                bandera = false;
            default:
                System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }
    private static void gestionarPrestamos() throws SQLException {
        boolean bandera = true;
        int opcion;
        while (bandera) {
            menuGestionarPrestamos();
            opcion = pedirOpcion();
            bandera = seleccionarOpcionPrestamos(opcion, bandera);
        }
    }

    private static boolean seleccionarOpcionPrestamos(int opcion, boolean bandera) throws SQLException {
        switch (opcion) {
            case 1:
                realizarPrestamo();
                break;
            case 2:
                finalizarPrestamo();
                break;
            case 0:
                bandera = false;
            default:
                System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }


    //Opciones empleado administrador
    private static void mostrarMenuAdministrador() throws SQLException {
        boolean bandera = true;
        int opcion;
        while (bandera) {
            menuAdministrador();
            opcion = pedirOpcion();
            bandera = seleccionarOpcionesAdministrador(opcion, bandera);
        }
    }
    private static boolean seleccionarOpcionesAdministrador(int opcion, boolean bandera) throws SQLException {
        switch (opcion) {
            case 1:
                gestionarEmpleados();
                break;
            case 0:
                bandera = false;
            default:
                System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }
    private static void gestionarEmpleados() throws SQLException {
        boolean bandera = true;
        int opcion;
        while (bandera) {
            menuGestionarEmpleados();
            opcion = pedirOpcion();
            bandera = seleccionarOpcionesEmpleados(opcion, bandera);
        }
    }

    private static boolean seleccionarOpcionesEmpleados(int opcion, boolean bandera) throws SQLException {
        switch (opcion) {
            case 1:
                crearEmpleado();
                break;
            case 2:
                mostrarEmpleados();
                break;
            case 3:
                eliminarEmpleado();
                break;
            case 0:
                bandera = false;
            default:
                System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }

    public static void crearEmpleado() {
        Empleado empleado=solicitarDatos();
        crudEmpleado.crearEntidad(empleado);
        empleados.add(empleado);
        logger.info(ASISTENTE_CREADO);
    }

    private static Empleado solicitarDatos() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println(String.format(MSN_NOMBRE, TIPO_EMPLEADO));
        String nombre = scanner.nextLine();
        System.out.println(String.format(MSN_CORREO, TIPO_EMPLEADO));
        String correo = scanner.nextLine();
        System.out.println(String.format(MSN_CONTRASENA, TIPO_EMPLEADO));
        String contrasena = scanner.nextLine();
        String rol = pedirOpcionRol();

        return new Empleado(nombre, correo, contrasena, rol);
    }

    private static String pedirOpcionRol() {
        String tipo;
        int opcionRol;
        while (true) {
            System.out.println(TIPO_ROL_OPCION);
            opcionRol = pedirOpcion();
            if (opcionRol == 1) {
                tipo = TIPO_ADMINISTRADOR;
                return tipo;
            } else if (opcionRol == 2) {
                tipo = TIPO_ASISTENTE;
                return tipo;
            } else {
                System.out.println(SELECCION_INVALIDA);
            }
        }
    }
    private static void eliminarEmpleado() throws SQLException {
        Empleado empleado=seleccionarEmpleadoCrud(ELIMINAR);
        crudEmpleado.eliminarEmpleado(empleado);
        empleados.remove(empleado);
        System.out.println(ELIMINACION_EXITOSA);
    }
    private static Empleado seleccionarEmpleadoCrud(String tipoCrud) throws SQLException {
        int opcion;
        Empleado EmpleadoSeleccionado;
        mostrarEmpleados();
        System.out.println(String.format(EMPLEADO_CRUD,tipoCrud));
        opcion = pedirOpcion();
        EmpleadoSeleccionado = empleados.get(opcion - 1);
        return EmpleadoSeleccionado;
    }
    public static void mostrarEmpleados() throws SQLException {
        if(empleados.isEmpty()){
            crudEmpleado.seleccionarDatos();
        }
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println(i+1+". "+empleados.get(i));
        }
    }
}

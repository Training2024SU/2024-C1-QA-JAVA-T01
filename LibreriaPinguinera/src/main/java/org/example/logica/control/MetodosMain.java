package org.example.logica.control;

import org.example.integration.database.persistencia.CrudUsuario;
import org.example.model.Empleado;
import org.example.model.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

import static org.example.dialog.Menu.menuPrincipal;
import static org.example.logica.control.ConstantesLogica.SELECCION_INVALIDA;
import static org.example.logica.control.EmpleadoControl.mostrarMenuEmpleado;
import static org.example.logica.control.UsuarioControl.mostrarMenuLector;

public class MetodosMain {
    static private Usuario usuario;
    static UsuarioControl usuarioControl = new UsuarioControl();
    static EmpleadoControl empleadoControl = new EmpleadoControl();

    public MetodosMain() {
    }

    public static void implementarLogica() throws SQLException {
        int option;
        boolean bandera = true;
        while (bandera) {
            menuPrincipal();
            option = pedirOpcion();
            bandera = seleccionarOpciones(option, bandera);

        }
    }

    public static boolean seleccionarOpciones(int option, boolean bandera) throws SQLException {
        Empleado empleado;
        switch (option) {
            case 1:
                empleado = empleadoControl.iniciarSesionEmpleado();
                mostrarMenuEmpleado(empleado);
                break;
            case 2:
                usuario = usuarioControl.iniciarSesionUsuario();
                if(usuario!=null){
                    mostrarMenuLector(usuario);
                }
                break;
            case 3:
                usuarioControl.crearUsuario();
                break;
            case 0:
                bandera = false;
                break;
            default:
                System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }

    public static int pedirOpcion() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        try {
            opcion = scanner.nextInt();
        } catch (Exception e) {
            opcion = 100;
            System.out.println("Error por la razon " + e.getMessage());
        }
        return opcion;
    }

}

package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Empleado;
import co.com.ejercicio.modelo.Usuario;
import co.com.ejercicio.modeloAccesoBD.EmpleadoAccesoBD;
import co.com.ejercicio.modeloAccesoBD.UsuarioAccesoBD;
import co.com.ejercicio.util.enums.Roles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.com.ejercicio.menu.dialogos.MenuGestionEmpleado.menuEmpleado;

public class GestionEmpleado {

    public static void mostrarMenuGestionEmpleado() {
        menuEmpleado();
    }

    public static void gestionInsertarEmpleado() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando empleado...");

        System.out.println("Ingrese el id");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el nombreEmpleado");
        String nombreEmpleado = scanner.nextLine();
        System.out.println("Ingrese el correo");
        String correo = scanner.nextLine();
        System.out.println("Ingrese contraseña");
        String contrasenia = scanner.nextLine();
        String rol = Roles.TIPO_DOS.getvalue();



        Connection conexion = Conexion.obtenerConexion();
        EmpleadoAccesoBD empleadoAccesoBD =new EmpleadoAccesoBD(conexion);
        Empleado empleado = new Empleado(id, nombreEmpleado,correo,contrasenia, rol);
        empleadoAccesoBD.insertarEmpleado(empleado);
    }

    public static void gestionObtenerEmpleado() throws SQLException{

        System.out.println("Obteniendo empleados...");

        Connection conexion = Conexion.obtenerConexion();
        EmpleadoAccesoBD empleadoAccesoBD = new EmpleadoAccesoBD(conexion);
        List<Empleado> empleados = empleadoAccesoBD.obtenerTodosLosEmpleados();
        for (Empleado empleado: empleados){
            System.out.println(empleado);
        }
    }

    public static void gestionEliminarEmpleado() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eliminando usuario...");
        System.out.println("Ingresa el correo del usuario a eliminar");
        String correoUsuario = scanner.nextLine();
        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD = new UsuarioAccesoBD(conexion);
        usuarioAccesoBD.eliminarUsuario(correoUsuario);
    }

    public static void gestionActualizarEmpleado() {
        System.out.println("Actualizando usuario...");
    }
}


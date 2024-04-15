package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.logica.empleado.EmpleadoOperaciones;
import com.sofkau.logica.usuario.UsuarioOperaciones;
import com.sofkau.model.Empleado;
import com.sofkau.model.Usuario;
import com.sofkau.util.enums.Roles;

import java.sql.SQLException;
import java.util.Scanner;


public class ControlIngreso {

    protected static boolean bandera = true;

    private static Scanner scannerGlobal = new Scanner(System.in);

    private static EmpleadoOperaciones empleadoOp;

    public ControlIngreso() throws SQLException {
    }


    // Se hace publico para ser el unico metodo que se pueda llamar desde el main
    public static void implementarLogica() {

        int option;
            while (bandera) {
                try {
                Menu.menuPrincipal();
                option = Integer.parseInt(scannerGlobal.nextLine());
                inicioSesion(option);
            } catch (Exception e) {
                    System.out.println("Error por la razon " + e.getMessage());
                }
        }
    }

    private static void inicioSesion(int option) throws SQLException {
        switch (option) {
            case 1:

                break;
            case 2:
                EmpleadoOperaciones.getEmpleados();
                Menu.correo();
                String correoEmp = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasenaEmp = scannerGlobal.nextLine();

                empleadoOp = new EmpleadoOperaciones();

                if(empleadoOp.inicioSesion(correoEmp,contrasenaEmp)){
                    menuEmpleado();
                }else{
                    System.out.println("Credenciales incorrectas por favor verifique");
                }

                break;
            case 3:
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();
                UsuarioOperaciones.getUsuarios();
                UsuarioOperaciones.registrarUsuario(new Usuario(nombre,correo,contrasena));
                break;
            case 4:
                bandera = false;
                break;
            default:
                System.out.println("ingrese una opcion válida");
        }
    }

    private static void menuEmpleado() throws SQLException {

        switch (empleadoOp.getEmpleadoActual().getRol()) {
            case "ADMINISTRADOR" -> {
                Menu.menuAdministrador();
                int op = Integer.parseInt(scannerGlobal.nextLine());
                menuAdministrador(op);
            }
            case "ASISTENTE" -> {
                Menu.ingresoAsistente();
                int op = Integer.parseInt(scannerGlobal.nextLine());

            }
            case "PROPIETARIO" -> {
                Menu.ingresoPropietario();
                int op = Integer.parseInt(scannerGlobal.nextLine());
                menuPropietario(op);
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                bandera = false;
            }

        }
    }

    private static void menuAdministrador(int op) throws SQLException {

        switch (op) {
            case 1 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();
                UsuarioOperaciones.getUsuarios();

                empleadoOp.registrarEmpleado(new Empleado(nombre,correo,contrasena), Roles.PROPIETARIO.toString());

            }
            case 2 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();
                UsuarioOperaciones.getUsuarios();

                empleadoOp.registrarEmpleado(new Empleado(nombre,correo,contrasena), Roles.ASISTENTE.toString());

            }
            case 3 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();
                UsuarioOperaciones.getUsuarios();
                UsuarioOperaciones.registrarUsuario(new Usuario(nombre, correo, contrasena));
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                bandera = false;
            }

        }
    }

    private static void menuPropietario(int op) throws SQLException {

        switch (op) {
            case 1 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();
                UsuarioOperaciones.getUsuarios();

                empleadoOp.registrarEmpleado(new Empleado(nombre,correo,contrasena), Roles.ASISTENTE.toString());

            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                bandera = false;
            }

        }
    }



    private static int pedirOpcion() {
        Scanner scanner = new Scanner(System.in);
        int option;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            option = 0;
            System.out.println("Error por la razon " + e.getMessage());
        }
        return option;
    }
}

package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.logica.Autor.AutorOperaciones;
import com.sofkau.logica.empleado.EmpleadoOperaciones;
import com.sofkau.logica.publicacion.PublicacionOperaciones;
import com.sofkau.logica.usuario.UsuarioOperaciones;
import com.sofkau.model.Empleado;
import com.sofkau.model.Publicacion;
import com.sofkau.model.Usuario;
import com.sofkau.util.enums.Roles;
import com.sofkau.util.enums.TipoPublicacion;

import java.sql.SQLException;
import java.util.Scanner;


public class ControlIngreso {

    protected static boolean bandera = true;

    private static Scanner scannerGlobal = new Scanner(System.in);

    private static EmpleadoOperaciones empleadoOp;

    private static AutorOperaciones autorOp = new AutorOperaciones();

    private static PublicacionOperaciones publicacionOp = new PublicacionOperaciones();

    private static int option = 0;
    private static int optionEmp = 0;

    public ControlIngreso() {
    }


    // Se hace publico para ser el unico metodo que se pueda llamar desde el main
    public static void implementarLogica() {

            autorOp.generateAutores(5);
            while (bandera) {
                try {
                    switch (option) {
                        case 0 ->{
                            Menu.menuPrincipal();
                            String op = scannerGlobal.nextLine();
                            option = Integer.parseInt(op);
                            inicioSesion(option);
                        }case 1 ->{

                        }case 2 ->{
                            scannerGlobal.nextLine();
                            menuEmpleado();
                        }
                    }

            } catch (Exception e) {
                    System.out.println("Error por la razon " + e.getMessage());
                }
        }
    }

    private static void inicioSesion(int option) {
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

    private static void menuEmpleado()  {

        switch (empleadoOp.getEmpleadoActual().getRol()) {
            case "ADMINISTRADOR" -> {
                Menu.menuAdministrador();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                menuAdministrador(op);
            }
            case "ASISTENTE" -> {
                Menu.ingresoAsistente();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                menuAsistente(op);
            }
            case "PROPIETARIO" -> {
                Menu.ingresoPropietario();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                menuPropietario(op);
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                bandera = false;
            }

        }
    }

    private static void menuAdministrador(int op) {

        switch (op) {
            case 1 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();
                UsuarioOperaciones.getUsuarios();

                empleadoOp.registrarEmpleado(new Empleado(nombre,correo,contrasena), Roles.ADMINISTRADOR.toString());

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

    private static void menuPropietario(int op) {

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

    private static void menuAsistente(int op) {
        switch (op) {
            case 1 -> {
                Menu.ingresoLibro();
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                autorOp.listarAutores();
                Menu.ingresoAutor();
                String nombreAutor = scannerGlobal.nextLine();
                Menu.ingresoCantEjemplar();
                int cantEjemplar = scannerGlobal.nextInt();
                Menu.ingresoCantPrestado();
                int cantPres = scannerGlobal.nextInt();
                Menu.ingresoNumPaginas();
                int numPag = scannerGlobal.nextInt();
                Menu.ingresoAreaConocimiento();
                String areaConocimiento = scannerGlobal.nextLine();


                publicacionOp.registrarPublicacion(new Publicacion(titulo,autorOp.buscarAutorNombre(nombreAutor),numPag,cantEjemplar,cantPres),
                        TipoPublicacion.Libro);
            }
            case 2 -> {
                Menu.ingresoNovela();
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                autorOp.listarAutores();
                Menu.ingresoAutor();
                String nombreAutor = scannerGlobal.nextLine();
                Menu.ingresoCantEjemplar();
                int cantEjemplar = scannerGlobal.nextInt();
                Menu.ingresoCantPrestado();
                int cantPres = scannerGlobal.nextInt();
                publicacionOp.registrarPublicacion(new Publicacion(titulo,autorOp.buscarAutorNombre(nombreAutor),cantEjemplar,cantPres),
                        TipoPublicacion.Novela);
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

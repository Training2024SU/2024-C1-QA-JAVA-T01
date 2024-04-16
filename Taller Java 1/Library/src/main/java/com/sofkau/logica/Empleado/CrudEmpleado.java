package com.sofkau.logica.Empleado;

import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Empleado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.dialogo.EmpleadoDialog.crearAsistente;
import static com.sofkau.dialogo.UsuarioDialog.crearUsuario;

public class CrudEmpleado {
    private static final String SELECT_CORREO_PASSWORD_FROM_EMPLEADO = "select * from mydb.empleado where Contrasena = '%s' and Correo = '%s'; ";
    // private static final String SELECT_CORREO_PASSWORD_FROM_EMPLEADO = "select * from mydb.empleado where Correo = '%s';";
    //private static final String SELECT_ALL_FROM_EMPLEADO = "select * from mydb.empleado ;";

    /*public static List<Empleado> getUsersFromTable(MySqlOperation mySqlOperation) throws SQLException {
       List<Empleado> empleados = new ArrayList<>();
      try {
           mySqlOperation.setSqlStatement(String.format(SELECT_ALL_FROM_EMPLEADO));
           mySqlOperation.executeSqlStatement();
           ResultSet resultSet = mySqlOperation.getResulset();

           while (resultSet.next()) {
               Empleado empleado = new Empleado();
               empleado.se
               usuario.setCorreo(resultSet.getString(1));
               usuario.setContrasena(resultSet.getString(2));
               usuario.setNombre(resultSet.getString(3));
               usuario.setRol(resultSet.getString(4));
               usuarios.add(usuario);
           }
       } catch (SQLException e) {
           System.out.println("Error al obtener usuarios: " + e.getMessage());
       }
       return usuarios;
   }*/
    public static void logearseEmpleado() throws SQLException {
        System.out.println("Bienvenido Empleado de la Pinguinera");
        System.out.println("Ingresa tus datos para iniciar");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese correo");
        String correo = scanner.nextLine();
        System.out.println("Ingrese contrasena");
        String contrasena = scanner.nextLine();

        mySqlOperation.setSqlStatement(String.format(SELECT_CORREO_PASSWORD_FROM_EMPLEADO,  contrasena, correo));

        //System.out.println(String.format(SELECT_CORREO_PASSWORD_FROM_EMPLEADO,  contrasena,correo));
        mySqlOperation.executeSqlStatement();
        //ResultSet resultSet = mySqlOperation.getResulset();
        //String rol = resultSet.getString(5);
        //System.out.println(rol);

        //System.out.println(mySqlOperation.getDataBaseName());
        //mySqlOperation.printColumnValue(5);




        try {
            String valor = mySqlOperation.printColumnValue(5);

            if ("admin".equals(valor)) {
                System.out.println(" eres admin");
                System.out.println("Que quieres hacer hoy?");
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("1. Gestionar Empleados");
                System.out.println("2. Gestionar Asistente ");
                System.out.println("3. Salir del programa");

                int opcion1 = scanner1.nextInt();

                switch (opcion1) {
                    case 1:
                        System.out.println("Ha seleccionado Crear  Empleados");
                        System.out.println(" Crear empleado nuevo");
                        crearAsistente();
                        break;

                    case 2:
                        System.out.println("Ha seleccionado Crear  usuario");
                        System.out.println(" Crear usuario nuevo");
                        crearUsuario();

                        break;
                    case 3:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }

            } else if (" asistente".equals(valor)) {
                System.out.println("eres asistente");
                int optionEmpleado=1;
            } else {
                System.out.println("datos incorrectos");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

package Garcia.Juan.logica;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Usuario;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Garcia.Juan.CRUD.CrudUsuario.*;
import static Garcia.Juan.logica.SesionIniciada.usuarioIniciado;

public class MetodosUsuario {

    private static MySqlOperation mySqlOperation;

    public MetodosUsuario(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static void iniciarSesion(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor ingrese un correo electrónio:");//hacer metodo
        credenciales.add(scanner.nextLine());
        //credenciales.add("administrador@pingu.com.co");
        System.out.println("Por favor ingrese la contraseña:");//hacer metodo
        credenciales.add(scanner.nextLine());
        //credenciales.add("contrasenasegura123456 ");
        if (checkUser(mySqlOperation,credenciales)){
            usuarioIniciado(mySqlOperation,credenciales);
        }
        else {
            System.out.println("El correo o contraseña son incorrectos");
        }
    }

    public static boolean checkUser(MySqlOperation mySqlOperation,List<String> credenciales) throws SQLException {
        String correo = credenciales.get(0);
        String contrasena = credenciales.get(1);
        List<Usuario> usuarios = getUsersFromTable(mySqlOperation);
        boolean encontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                encontrado = true;
                break; //
            }
        }
        return encontrado;
    }

    public static List<String> registrarUsuario(MySqlOperation mySqlOperation, int admin) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor ingrese un correo electrónio:");
        credenciales.add(scanner.nextLine());
        System.out.println("Por favor ingrese la contraseña:");
        credenciales.add(scanner.nextLine());
        System.out.println("Por favor ingrese su nombre:");
        credenciales.add(scanner.nextLine());

        if (admin==0){
            credenciales.add("LECTOR");
        }
        else{
            credenciales.add("ASISTENTE");
        }
        if (checkUser(mySqlOperation,credenciales)){
            System.out.println("El usuario ya esta registrado");
        }
        else {
            insertUser(mySqlOperation,credenciales);
            usuarioIniciado(mySqlOperation,credenciales);
        }
        return credenciales;
    }
}

package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Usuario;
import co.com.ejercicio.modeloAccesoBD.UsuarioAccesoBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.com.ejercicio.menu.dialogos.MenuGestionUsuario.interactuarConUsuario;

public class GestionUsuario {

    public static void mostrarMenuGestionUsuarios() {
        interactuarConUsuario();
    }

    public static void gestionInsertarUsuario() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando usuario...");

        System.out.println("Ingrese el correo");
        String correo = scanner.nextLine();
        System.out.println("Ingrese el nombre");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese contraseña");
        String contrasenia = scanner.nextLine();


        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD =new UsuarioAccesoBD(conexion);
        Usuario usuario = new Usuario(correo, nombre, contrasenia);
        usuarioAccesoBD.agregarUsuario(usuario);
    }

    public static void gestionObtenerUsuario() throws SQLException{
        System.out.println("Obteniendo usuario...");

        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD = new UsuarioAccesoBD(conexion);
        List<Usuario> usuarios = usuarioAccesoBD.obtenerTodosLosUsuarios();
        for (Usuario usuario: usuarios){
            System.out.println(usuario);
        }
    }

    public static void gestionEliminarUsuario() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eliminando usuario...");
        System.out.println("Ingresa el correo del usuario a eliminar");
        String correoUsuario = scanner.nextLine();
        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD = new UsuarioAccesoBD(conexion);
        usuarioAccesoBD.eliminarUsuario(correoUsuario);
    }

    public static void gestionActualizarUsuario() {
        System.out.println("Actualizando usuario...");
    }

}

package co.com.sofkau.logica.usuario;

import co.com.sofkau.integration.database.CrudClases.CrudUsuario;
import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.model.Empleado;
import co.com.sofkau.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioUtils {

    public static void imprimirUsuarios() {
        ArrayList<Usuario> usuarios = Usuario.getUsuarios();
        imprimirInformacionUsuarios(usuarios);
    }

    private static void imprimirInformacionUsuarios(List<Usuario> listEmpleadosImprimir) {
        for (Usuario usuarioImprimir : listEmpleadosImprimir) {
            System.out.println("Nombre: " + usuarioImprimir.getNombre());
            System.out.println("Correo: " + usuarioImprimir.getCorreo());
            System.out.println("Clave: " + usuarioImprimir.getClave());
            System.out.println("-------------------------");
        }
    }

    public static void crearCuentaUsuario(MySqlOperation mySqlOperation, String correo, String nombre, String clave){
        CrudUsuario.agregarUsuario(mySqlOperation,correo,nombre,clave);
    }

    public static boolean iniciarSesion(String correo, String clave) {

        ArrayList<Usuario> usuarios = Usuario.getUsuarios();

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getClave().equals(clave)) {
                System.out.println("Inicio de sesión exitoso para el Usuario con correo: " + correo);
                return true;
            }
        }
        System.out.println("Correo o clave incorrectos. Inicio de sesión fallido.");
        return false;
    }
}

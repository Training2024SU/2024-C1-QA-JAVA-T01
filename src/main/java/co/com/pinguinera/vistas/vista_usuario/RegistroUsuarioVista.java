package co.com.pinguinera.vistas.vista_usuario;

import co.com.pinguinera.datos.model.Usuario;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.Scanner;

public class RegistroUsuarioVista {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Solicita los datos de un nuevo usuario desde la consola.
     * @return Un objeto `Usuario` con los datos ingresados.
     */
    public Usuario pedirDatosUsuario() {
        Usuario usuario = new Usuario();

        // Solicitar nombre del usuario
        System.out.print("Por favor, introduzca su nombre: ");
        usuario.setNombre(scanner.nextLine());

        // Solicitar correo electrónico del usuario
        usuario.setCorreo(VistaUtil.pedirCorreoElectronico());

        // Solicitar contraseña del usuario
        usuario.setContrasena(VistaUtil.pedirContrasena());

        // Puedes solicitar otros datos necesarios aquí, como dirección, teléfono, etc.

        return usuario;
    }

    /**
     * Muestra un mensaje de éxito cuando el registro es exitoso.
     */
    public void mostrarMensajeExitoRegistro() {
        System.out.println("Registro exitoso. ¡Bienvenido!");
    }

    /**
     * Muestra un mensaje de error si el registro falla.
     * @param mensaje Mensaje de error a mostrar.
     */
    public void mostrarMensajeErrorRegistro(String mensaje) {
        System.out.println("Error en el registro: " + mensaje);
    }
}

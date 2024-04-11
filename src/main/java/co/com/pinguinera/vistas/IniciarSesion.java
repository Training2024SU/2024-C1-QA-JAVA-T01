package co.com.pinguinera.vistas;

import co.com.pinguinera.modelos.DAO.AutenticacionDAO;

import java.util.Scanner;

public class IniciarSesion {
    private Scanner scanner;
    private AutenticacionDAO autenticacionDAO;

    public IniciarSesion(Scanner scanner, AutenticacionDAO autenticacionDAO) {
        this.scanner = scanner;
        this.autenticacionDAO = autenticacionDAO;
    }

    public void iniciarSesion() {
        System.out.println("Iniciar sesión");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        // Llama al método autenticarUsuario con los datos recogidos
        boolean autenticado = autenticacionDAO.autenticarUsuario(correo, contraseña);

        // Verifica si la autenticación fue exitosa
        if (autenticado) {
            // Autenticación exitosa
            System.out.println("Inicio de sesión exitoso. Bienvenido!");
            // Aquí puedes realizar las acciones necesarias para un inicio de sesión exitoso
        } else {
            // Autenticación fallida
            System.out.println("Inicio de sesión fallido. Por favor, verifica tus credenciales.");
            // Aquí puedes manejar el caso de inicio de sesión fallido
        }
    }
}

package co.com.sofka.menu;

import co.com.sofka.servicio.ServicioUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

import static co.com.sofka.menu.ConstantesMenu.*;;

public class MenuAdministrador {
    ServicioUsuario servicioUsuario;
    Scanner scanner;

    boolean seguirEjecucion = true;

    public MenuAdministrador(ServicioUsuario servicioUsuario, Scanner scanner){
        this.servicioUsuario = servicioUsuario;
        this.scanner = scanner;
    }

    public void imprimirMenuAdministrador(){
        System.out.println(ConstantesMenu.MENSAJE_MENU_CUALQUIER_ROL);
        System.out.println(ConstantesMenu.OPCIONES_ADMINISTRADOR);

        try {
            int eleccion = scanner.nextInt();
            scanner.nextLine();

            switch (eleccion){
                case 1:
                    registroUsuarioAsistente(scanner);
                    break;
                case 2:
                    servicioUsuario.listarUsuariosAsistentes();
                    break;
                case 0:
                    System.out.println(ConstantesMenu.MENSAJE_DESPEDIDA);
                    seguirEjecucion = false;
                default:
                    System.out.println(OPCION_NO_VALIDA);
            }
        }
        catch (InputMismatchException e){
            System.out.println(OPCION_NO_VALIDA);
            scanner.nextLine();
            return;
        }

    }

    public boolean quieroSeguirEjecucion(){
        return seguirEjecucion;
    }
    private void registroUsuarioAsistente(Scanner scanner){
        System.out.println(ConstantesMenu.PRIMER_MENSAJE_REGISTRO);
        String nombre = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_REGISTRO);
        String correo = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_REGISTRO);
        String contrasena = scanner.nextLine();

        servicioUsuario.crearUsuarioAsistente(nombre, correo, contrasena);
    }

}

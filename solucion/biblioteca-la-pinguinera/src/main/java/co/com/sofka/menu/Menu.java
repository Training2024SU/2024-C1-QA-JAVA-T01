package co.com.sofka.menu;

import co.com.sofka.modelo.Usuario;
import co.com.sofka.repositorio.HibernateUtil;
import co.com.sofka.repositorio.RepositorioUsuario;
import co.com.sofka.servicio.ServicioUsuario;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(System.in);
    static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    static RepositorioUsuario repositorioUsuario = new RepositorioUsuario(sessionFactory);

    // Al iniciar la aplicacion no hay usuarioIngresado, despues del ingreso se asigna un usuario de la bd
    static Usuario usuarioIngresado = null;

    // All iniciar la aplicacion se desea ejecutar el menu, una ves el usuario desee, se asigna a false y se sale del menu
    static boolean seguirEjecucion = true;
    private static final transient Logger log = LoggerFactory.getLogger(Menu.class);
    public static void iniciar(){
        while(seguirEjecucion){
            if(usuarioIngresado != null){
                imprimirMenuUsuarioIngresado(scanner);
            } else {
                imprimirMenuUsuarioSinIngresar(scanner);
            }
        }

    }

    private static void imprimirMenuUsuarioIngresado(Scanner scanner){
        System.out.println("Usuario " + usuarioIngresado.getNombre() + " ya a ingresado");
    }

    private static void imprimirMenuUsuarioSinIngresar(Scanner scanner){
        imprimirMenuInicial();

        int eleccion = scanner.nextInt();
        scanner.nextLine();

        switch (eleccion){
            case 1:
                ingresoUsuario(scanner);
                break;
            case 2:
                System.out.println("Se hará registro pidiendo nombre, correo y contraseña");
                break;
            case 0:
                seguirEjecucion = false;
                System.out.println(ConstantesMenu.MENSAJE_DESPEDIDA);
                break;
            default:
                System.out.println(ConstantesMenu.OPCION_INVALIDA);
        }
    }

    private static void imprimirMenuInicial(){
        System.out.println(ConstantesMenu.MENSAJE_BIENVENIDA);
    }

    private static void ingresoUsuario(Scanner scanner){
        System.out.println("Ingreso a la aplicacion...\nPorfavor ingrese su correo");

        String correo = scanner.nextLine();

        System.out.println("Porfavor ingrese su contraseña");
        String contrasena = scanner.nextLine();

        usuarioIngresado = repositorioUsuario.obtenerPorCorreoYContrasena(correo, contrasena);

        if(usuarioIngresado != null){
            System.out.println("Ingreso exitoso, bienvenido: " + usuarioIngresado.getNombre());
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }
}

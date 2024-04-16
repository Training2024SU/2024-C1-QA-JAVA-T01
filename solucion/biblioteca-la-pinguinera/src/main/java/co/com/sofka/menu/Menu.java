package co.com.sofka.menu;

import co.com.sofka.servicio.ExportarLibro;
import co.com.sofka.util.RolUsuario;
import co.com.sofka.modelo.Usuario;
import co.com.sofka.repositorio.*;
import co.com.sofka.servicio.ServicioNovela;
import co.com.sofka.servicio.ServicioPrestamo;
import co.com.sofka.servicio.ServicioUsuario;
import co.com.sofka.servicio.ServicioLibro;
import org.hibernate.SessionFactory;
import static co.com.sofka.menu.ConstantesMenu.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    static RepositorioUsuario repositorioUsuario = new RepositorioUsuario(sessionFactory);
    static ServicioUsuario servicioUsuario = new ServicioUsuario(repositorioUsuario);
    static RepositorioLibro repositorioLibro = new RepositorioLibro(sessionFactory);
    static ServicioLibro servicioLibro = new ServicioLibro(repositorioLibro);
    static RepositorioNovela repositorioNovela = new RepositorioNovela(sessionFactory);
    static ServicioNovela servicioNovela = new ServicioNovela(repositorioNovela);
    static RepositorioPrestamo repositorioPrestamo = new RepositorioPrestamo(sessionFactory);
    static ServicioPrestamo servicioPrestamo = new ServicioPrestamo(repositorioPrestamo, repositorioLibro, repositorioNovela);

    static ExportarLibro exportarLibro = new ExportarLibro(sessionFactory);

    static MenuAdministrador menuAdministrador = new MenuAdministrador(servicioUsuario, scanner);
    static MenuLector menuLector = new MenuLector(servicioLibro, servicioNovela, servicioPrestamo, scanner, exportarLibro);
    static MenuAsistente menuAsistente = new MenuAsistente(servicioLibro, servicioNovela, servicioPrestamo, scanner, exportarLibro);


    static Usuario usuarioIngresado = null;

    // Al iniciar la aplicacion se desea ejecutar el menu, una vez el usuario desee, se asigna a false y se sale del menu
    static boolean seguirEjecucion = true;
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
        System.out.println("\nBienvenido " + usuarioIngresado.getNombre() + " ...");

        if(usuarioIngresado.getRol().equals(RolUsuario.ADMINISTRADOR)){
            menuAdministrador.imprimirMenuAdministrador();
            seguirEjecucion = menuAdministrador.quieroSeguirEjecucion();
        } else if(usuarioIngresado.getRol().equals(RolUsuario.ASISTENTE)) {
            menuAsistente.imprimirMenuAsistente();
            seguirEjecucion = menuAsistente.quieroSeguirEjecucion();
        } else {
            menuLector.imprimirMenuLectores(usuarioIngresado);
            seguirEjecucion = menuLector.quieroSeguirEjecucion();
        }

    }

    private static void imprimirMenuUsuarioSinIngresar(Scanner scanner){
        imprimirMenuInicial();

        try {
            int eleccion = scanner.nextInt();
            scanner.nextLine();

            switch (eleccion){
                case 1:
                    ingresoUsuario(scanner);
                    break;
                case 2:
                    registroUsuario(scanner);
                    break;
                case 0:
                    seguirEjecucion = false;
                    System.out.println(ConstantesMenu.MENSAJE_DESPEDIDA);
                    break;
                default:
                    System.out.println(ConstantesMenu.OPCION_NO_VALIDA);
            }
        } catch (InputMismatchException e){
            System.out.println(OPCION_NO_VALIDA);
            scanner.nextLine();
            return;
        }
    }

    private static void imprimirMenuInicial(){
        System.out.println(ConstantesMenu.MENSAJE_BIENVENIDA);
    }

    private static void ingresoUsuario(Scanner scanner){
        System.out.println(ConstantesMenu.PRIMER_MENSAJE_INGRESO);

        String correo = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_INGRESO);
        String contrasena = scanner.nextLine();

        usuarioIngresado = repositorioUsuario.obtenerPorCorreoYContrasena(correo, contrasena);

        if(usuarioIngresado != null){
            System.out.println(ConstantesMenu.MENSAJE_INGRESO_EXITOSO + usuarioIngresado.getNombre());
        } else {
            System.out.println(ConstantesMenu.MENSAJE_INGRESO_FALLIDO);
        }
    }

    private static void registroUsuario(Scanner scanner){
        System.out.println(ConstantesMenu.PRIMER_MENSAJE_REGISTRO);
        String nombre = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_REGISTRO);
        String correo = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_REGISTRO);
        String contrasena = scanner.nextLine();

        servicioUsuario.crearUsuarioLector(nombre, correo, contrasena);
    }

}

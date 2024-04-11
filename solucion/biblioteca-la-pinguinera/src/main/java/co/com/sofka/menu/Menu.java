package co.com.sofka.menu;

import co.com.sofka.modelo.RolUsuario;
import co.com.sofka.modelo.Usuario;
import co.com.sofka.repositorio.*;
import co.com.sofka.servicio.ServicioNovela;
import co.com.sofka.servicio.ServicioPrestamo;
import co.com.sofka.servicio.ServicioUsuario;
import co.com.sofka.servicio.ServicioLibro;
import org.hibernate.SessionFactory;

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


    // Al iniciar la aplicacion no hay usuario Ingresado, despues del ingreso se asigna un usuario de la bd
    static Usuario usuarioIngresado = null;

    // Al iniciar la aplicacion se desea ejecutar el menu, una ves el usuario desee, se asigna a false y se sale del menu
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
        System.out.println("Bienvenido " + usuarioIngresado.getNombre());

        if(usuarioIngresado.getRol().equals(RolUsuario.ADMINISTRADOR)){
            imprimirMenuAdministrador(scanner);
        } else if(usuarioIngresado.getRol().equals(RolUsuario.ASISTENTE)) {
            imprimirMenuAsistenten(scanner);
        } else {
            imprimirMenuLectores(scanner);
        }

    }

    private static void imprimirMenuAdministrador(Scanner scanner){
        System.out.println(ConstantesMenu.MENSAJE_MENU_CUALQUIER_ROL);
        System.out.println(ConstantesMenu.OPCIONES_ADMINISTRADOR);

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
                seguirEjecucion = false;
                System.out.println(ConstantesMenu.MENSAJE_DESPEDIDA);
                break;
            default:
                System.out.println(ConstantesMenu.OPCION_INVALIDA);
        }
    }

    private static void imprimirMenuLectores(Scanner scanner){
        System.out.println(ConstantesMenu.MENSAJE_MENU_CUALQUIER_ROL);
        System.out.println(ConstantesMenu.OPCIONES_LECTOR);

        int eleccion = scanner.nextInt();
        scanner.nextLine();

        switch (eleccion){
            case 1:
                servicioLibro.listarAutores();
                break;
            case 2:
                servicioNovela.listarAutores();
                break;
            case 3:
                servicioLibro.listarLibrosDisponibles();
                break;
            case 4:
                servicioNovela.listarNovelasDisponibles();
                break;
            case 5:
                prestarLibros(scanner);
                break;
            case 6:
                prestarNovelas(scanner);
                break;
            case 0:
                seguirEjecucion = false;
                System.out.println(ConstantesMenu.MENSAJE_DESPEDIDA);
                break;
            default:
                System.out.println(ConstantesMenu.OPCION_INVALIDA);
        }
    }

    private static void imprimirMenuAsistenten(Scanner scanner){
        System.out.println(ConstantesMenu.MENSAJE_MENU_CUALQUIER_ROL);
        System.out.println(ConstantesMenu.OPCIONES_ASISTENTE);

        int eleccion = scanner.nextInt();
        scanner.nextLine();

        switch (eleccion) {
            case 1:
                guardarLibro(scanner);
                break;

            case 2:
                guardarNovela(scanner);
                break;
            case 3:
                servicioLibro.listarAutores();
                break;
            case 4:
                servicioNovela.listarAutores();
                break;
            case 5:
                buscarLibroPorAutor(scanner);
                break;
            case 6:
                buscarNovelaPorAutor(scanner);
                break;
            case 7:
                revisarPrestamosSolicitados(scanner);
                break;
            case 8:
                finalizarPrestamoRealizado(scanner);
                break;
            case 9:
                modificarLibro(scanner);
                break;
            case 10:
                modificarNovela(scanner);
                break;
            case 0:
                seguirEjecucion = false;
                System.out.println(ConstantesMenu.MENSAJE_DESPEDIDA);
                break;
            default:
                System.out.println(ConstantesMenu.OPCION_INVALIDA);
        }
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
                registroUsuario(scanner);
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

    private static void registroUsuarioAsistente(Scanner scanner){
        System.out.println(ConstantesMenu.PRIMER_MENSAJE_REGISTRO);
        String nombre = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_REGISTRO);
        String correo = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_REGISTRO);
        String contrasena = scanner.nextLine();

        servicioUsuario.crearUsuarioAsistente(nombre, correo, contrasena);
    }

    private static void guardarLibro(Scanner scanner){
        System.out.println(ConstantesMenu.PRIMER_MENSAJE_GUARDAR_LIBRO);
        String titulo = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_GUARDAR_LIBRO);
        String autor = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_GUARDAR_LIBRO);
        int cantidadEjemplares = scanner.nextInt();
        scanner.nextLine();

        System.out.println(ConstantesMenu.SEXTO_MENSAJE_GUARDAR_LIBRO);
        String areaDelConocimiento = scanner.nextLine();

        System.out.println(ConstantesMenu.SEPTIMO_MENSAJE_GUARDAR_LIBRO);
        int numeroDePaginas = scanner.nextInt();
        scanner.nextLine();

        servicioLibro.guardarLibro(titulo, autor, cantidadEjemplares, areaDelConocimiento, numeroDePaginas);
    }

    public static void modificarLibro(Scanner scanner){
        System.out.println("Ingrese el id del libro");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println(ConstantesMenu.PRIMER_MENSAJE_GUARDAR_LIBRO);
        String titulo = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_GUARDAR_LIBRO);
        String autor = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_GUARDAR_LIBRO);
        int cantidadEjemplares = scanner.nextInt();
        scanner.nextLine();

        System.out.println(ConstantesMenu.SEXTO_MENSAJE_GUARDAR_LIBRO);
        String areaDelConocimiento = scanner.nextLine();

        System.out.println(ConstantesMenu.SEPTIMO_MENSAJE_GUARDAR_LIBRO);
        int numeroDePaginas = scanner.nextInt();
        scanner.nextLine();

        servicioLibro.modificarLibro(id, titulo, autor, cantidadEjemplares, areaDelConocimiento, numeroDePaginas);
    }

   private static void guardarNovela(Scanner scanner){
        System.out.println(ConstantesMenu.PRIMER_MENSAJE_GUARDAR_NOVELA);
        String titulo = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_GUARDAR_NOVELA);
        String autor = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_GUARDAR_NOVELA);
        int cantidadEjemplares = scanner.nextInt();
        scanner.nextLine();

        System.out.println(ConstantesMenu.SEXTO_MENSAJE_GUARDAR_NOVELA);
        String genero = scanner.nextLine();

        System.out.println(ConstantesMenu.SEPTIMO_MENSAJE_GUARDAR_NOVELA);
        int edadDeLecturaSugerida = scanner.nextInt();
        scanner.nextLine();

        servicioNovela.guardarNovela(titulo, autor, cantidadEjemplares,genero, edadDeLecturaSugerida);
    }

    public static void modificarNovela(Scanner scanner){
        System.out.println("Ingrese el id de la novela");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println(ConstantesMenu.PRIMER_MENSAJE_GUARDAR_NOVELA);
        String titulo = scanner.nextLine();

        System.out.println(ConstantesMenu.SEGUNDO_MENSAJE_GUARDAR_NOVELA);
        String autor = scanner.nextLine();

        System.out.println(ConstantesMenu.TERCER_MENSAJE_GUARDAR_NOVELA);
        int cantidadEjemplares = scanner.nextInt();
        scanner.nextLine();

        System.out.println(ConstantesMenu.SEXTO_MENSAJE_GUARDAR_NOVELA);
        String genero = scanner.nextLine();

        System.out.println(ConstantesMenu.SEPTIMO_MENSAJE_GUARDAR_NOVELA);
        int edadDeLecturaSugerida = scanner.nextInt();
        scanner.nextLine();

        servicioNovela.modificarNovela(id, titulo, autor, cantidadEjemplares, genero, edadDeLecturaSugerida);
    }

    public static void buscarLibroPorAutor(Scanner scanner){
        System.out.println("Ingrese el nombre del autor");
        String autor = scanner.nextLine();

        servicioLibro.listarLibroPorAutor(autor);
    }

    public static void buscarNovelaPorAutor(Scanner scanner){
        System.out.println("Ingrese el nombre del autor");
        String autor = scanner.nextLine();

        servicioNovela.listarNovelaPorAutor(autor);
    }

    public static void prestarLibros(Scanner scanner){
        System.out.println("Ingrese el titulo del libro");
        String titulo = scanner.nextLine();

        servicioPrestamo.solicitarPrestamoLibro(usuarioIngresado, titulo);
        System.out.println("Solicitud de prestamo acceptada, dirigase a un asesor para completar el prestamo");
    }

    public static void prestarNovelas(Scanner scanner){
        System.out.println("Ingrese el titulo de la novela");
        String titulo = scanner.nextLine();

        servicioPrestamo.solicitarPrestamoNovela(usuarioIngresado, titulo);
        System.out.println("Solicitud de prestamo acceptada, dirigase a un asesor para completar el prestamo");
    }

    public static void revisarPrestamosSolicitados(Scanner scanner){
        System.out.println("A continuacion, se listan los prestamos en estado de solicitado");
        servicioPrestamo.listarPrestamosSolicitados();

        System.out.println("Ingrese el id del prestamo para realizarlo");
        Long prestamoId = scanner.nextLong();
        scanner.nextLine();

        servicioPrestamo.realizarPrestamo(prestamoId);
        System.out.println("Prestamo realizado con exito");
    }

    public static void finalizarPrestamoRealizado(Scanner scanner){
        System.out.println("A continuacion, se listan los prestamos en estado de realizado");
        servicioPrestamo.listarPrestamosRealizados();

        System.out.println("Ingrese el id del prestamo para finalizarlo");
        Long prestamoId = scanner.nextLong();
        scanner.nextLine();

        servicioPrestamo.realizarDevolucion(prestamoId);
        System.out.println("Prestamo finalizado con exito");
    }
}

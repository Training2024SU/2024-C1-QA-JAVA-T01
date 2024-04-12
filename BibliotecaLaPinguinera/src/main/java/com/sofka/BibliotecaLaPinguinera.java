package com.sofka;

import com.sofka.model.Estado;
import com.sofka.model.Prestamo;
import com.sofka.model.Rol;
import com.sofka.model.Usuario;
import lombok.Data;
import net.datafaker.Faker;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sofka.integration.database.mysql.MySqlOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;


@SpringBootApplication
@Data
public class BibliotecaLaPinguinera {
    private static final String SERVER = "localhost";
    private static final String DATA_BASE_NAME = "bibliotecapinguinela";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String SELECT_ALL_FROM_LIBROS = String.format("select * from %s.libro", DATA_BASE_NAME);
    private static final String SELECT_ALL_FROM_NOVELAS = String.format("select * from %s.novela", DATA_BASE_NAME);
    private static final String SELECT_ALL_FROM_PRESTAMOS = String.format("select * from %s.prestamo", DATA_BASE_NAME);
    private static final String SELECT_ALL_FROM_USUARIOS = String.format("select * from %s.usuario", DATA_BASE_NAME);
    private static final String INSERT_LIBRO = "insert into libro values ('%s', '%s', '%s', '%s', '%s', '%s', '%s');";
    private static final String INSERT_NOVELA = "insert into novela values ('%s', '%s', '%s', '%s', '%s', '%s', '%s');";
    private static final String INSERT_PRESTAMO = "insert into prestamo values ('%s', '%s', '%s', '%s', '%s');";
    private static final String INSERT_USUARIO = "insert into usuario values ('%s', '%s', '%s', '%s');";
    private static final MySqlOperation mySqlOperation = new MySqlOperation();
    public static BufferedReader bufEntrada = new BufferedReader(new InputStreamReader(System.in));
    private static Usuario usuarioAdministrador;
    private List<Prestamo> prestamos = new ArrayList<>();

    public static void main(String[] args) throws SQLException, IOException, NumberFormatException {
        // Crear el usuario administrador
        usuarioAdministrador = new Usuario("John Doe", "administrador@pingu.com.co", "contrasenasegura123456", "ADMINISTRADOR");
        System.out.println("Bienvenido a la Biblioteca la Pingüinera\n");
        do {
            openConnection();
            menuInicio();
            closeConnection();
        } while (!(preguntarSalir().equals("s")));
    }

    private static void menuInicio() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Menú de Inicio de la Biblioteca la Pingüinela \n\n" +
                        "1. Iniciar Sesión o Registro\n" +
                        "2. Mostrar Libros\n" +
                        "3. Mostrar Novelas\n" +
                        "4. Mostrar Prestamos\n" +
                        "5. Salir de la Biblioteca"));
        switch (opcion) {
            case "1":
                menuInicioSesionComo();
                break;
            case "2":
                selectAllFromLibro();
                break;
            case "3":
                selectAllFromNovela();
                break;
            case "4":
                selectAllFromPrestamo();
                break;
            case "5":
                JOptionPane.showMessageDialog(null, "¡OjO, Va a salir de la Biblioteca!");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta, por favor seleccione correctamente");

        }
        if (!(opcion.equals("5"))) menuInicio();
    }

    private static void menuInicioSesionComo() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Iniciar Sesión Como:\n\n" +
                        "1. Administrador\n" +
                        "2. Asistente\n" +
                        "3. Lector\n" +
                        "4. Atras"));

        switch (opcion) {
            case "1", "2", "3":
                iniciarSesion(opcion);
                break;
            case "4":
                JOptionPane.showMessageDialog(null, "¡No se inicio Sesión!");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta, por favor seleccione correctamente");
                menuInicioSesionComo();
                break;
        }
    }

    private static void iniciarSesion(String rol) throws SQLException {
        String usuario = JOptionPane.showInputDialog(null, "Ingrese su nombre de usuario:");
        String contrasena = JOptionPane.showInputDialog(null, "Ingrese su contraseña:");
        if (Objects.equals(rol, "1")) validarAdministrador(usuario, contrasena);
        if (rol.equals("2") || rol.equals("3")) validarUsuario(usuario, contrasena, rol);
    }

    private static void validarUsuario(String usuario, String contrasena, String rol) throws SQLException {
        // Aquí se procede a validar las credenciales con una base de datos o lógica adicional
        Usuario nuevoUsuario = new Usuario();

        if (usuario.equals(usuarioAdministrador.getNombre()) && contrasena.equals(usuarioAdministrador.getContrasena())) {
            JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso para el usuario: " + usuario + "!");
            if (rol.equals("2")) menuAsistente();
            if (rol.equals("3")) menuAsistente();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            menuInicioSesionComo();
        }
    }

    private static void validarAdministrador(String usuario, String contrasena) throws SQLException {
        if (usuario.equals(usuarioAdministrador.getNombre()) && contrasena.equals(usuarioAdministrador.getContrasena())) {
            JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso para el usuario: " + usuario + "!");
            menuAdministrador();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            menuInicioSesionComo();
        }
    }

    private static void menuAsistente() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Menú del Asistente\n\n" +
                        "1. Buscar libro por autor\n" +
                        "2. Registrar Libro\n" +
                        "3. Registrar Novela\n" +
                        "4. Registrar Prestamo\n" +
                        "5. Mostrar Usuarios\n" +
                        "6. Mostrar Libros\n" +
                        "7. Mostrar Novelas\n" +
                        "8. Mostrar Prestamos\n" +
                        "10. Cerrar Sesión"));
        switch (opcion) {
            case "1":
                //buscarporautor
                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            case "5":
                selectAllFromUsuario();
                break;
            case "6":
                selectAllFromLibro();
                break;
            case "7":
                selectAllFromNovela();
                break;
            case "8":
                selectAllFromPrestamo();
                break;
            case "9":
                insertarUsuarioEnBd(preguntarAlUsuario());
                break;
            case "10":
                insertarLibrosEnBd(preguntarAlUsuario());
                break;
            case "11":
                insertarNovelaEnBd(preguntarAlUsuario());
                break;
            case "12":
                insertarPrestamoEnBd(preguntarAlUsuario());
                break;
            case "14":
                JOptionPane.showMessageDialog(null, "¡Cerrando Sesion!");
                menuInicioSesionComo();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("14"))) menuAsistente();
    }

    private static void menuAdministrador() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Menú del Administrador John Doe\n\n" +
                        "1.  Registrar Usuario\n" +
                        "2.  Registrar Libro\n" +
                        "3.  Registrar Novela\n" +
                        "4.  Registrar Prestamo\n" +
                        "5.  Mostrar Usuarios\n" +
                        "6.  Mostrar Libros\n" +
                        "7.  Mostrar Novelas\n" +
                        "8.  Mostrar Prestamos\n" +
                        "9.  Generar Usuarios con Faker\n" +
                        "10. Generar Libros con Faker\n" +
                        "11. Generar Novelas con Faker\n" +
                        "12. Generar Prestamos con Faker\n" +
                        "0.  Cerrar Sesión"));

        switch (opcion) {
            case "1":
                insertIntoBd(registrarUsuario());
                break;
            case "2":
                insertIntoBd(registrarLibro());
                break;
            case "3":
                insertIntoBd(registrarNovela());
                break;
            case "4":
                insertIntoBd(registrarPrestamo());
                break;
            case "5":
                selectAllFromUsuario();
                break;
            case "6":
                selectAllFromLibro();
                break;
            case "7":
                selectAllFromNovela();
                break;
            case "8":
                selectAllFromPrestamo();
                break;
            case "9":
                insertarUsuarioEnBd(preguntarAlUsuario());
                break;
            case "10":
                insertarLibrosEnBd(preguntarAlUsuario());
                break;
            case "11":
                insertarNovelaEnBd(preguntarAlUsuario());
                break;
            case "12":
                insertarPrestamoEnBd(preguntarAlUsuario());
                break;
            case "0":
                JOptionPane.showMessageDialog(null, "¡Cerrando Sesion!");
                menuInicioSesionComo();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("14"))) menuAdministrador();
    }

    public static String preguntarSalir() {
        return JOptionPane.showInputDialog(null, "¿Seguro que desea salir? (S/N):");
    }

    private static int preguntarAlUsuario() {
        return Integer.parseInt(JOptionPane.showInputDialog(null, "Cual es la cantidad que desea generar: "));
    }

    private static void insertarLibrosEnBd(int cantidadLibros) {
        for (int i = 0; i < cantidadLibros; i++) {
            insertIntoBd(crearLibro());
        }
    }

    private static void insertarNovelaEnBd(int cantidadNovelas) {
        for (int i = 0; i < cantidadNovelas; i++) {
            insertIntoBd(crearNovela());
        }
    }

    private static void insertarPrestamoEnBd(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            insertIntoBd(crearPrestamo());
        }
    }

    private static void insertarUsuarioEnBd(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            insertIntoBd(crearUsuario());
        }
    }


    private static String registrarLibro() {
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro: ");
        String autor = JOptionPane.showInputDialog(null, "Ingrese el autor del libro: ");
        String areaConocimiento = JOptionPane.showInputDialog(null, "Ingrese el area de conocimineto del libro: ");
        String numeroPaginas = JOptionPane.showInputDialog(null, "Ingrese el numero de pag. del libro: ");
        String cantidadEjemplares = JOptionPane.showInputDialog(null, "Ingrese la cantidad de ejemplares del libro: ");
        String cantidadPrestados = JOptionPane.showInputDialog(null, "Ingrese la cantidad prestada del libro: ");
        String cantidadDisponibles = Integer.toString(Integer.parseInt(cantidadEjemplares) - Integer.parseInt(cantidadPrestados));
        JOptionPane.showMessageDialog(null, "¡Libro registrado exitosamente: " + titulo + "!");
        return String.format(INSERT_LIBRO, titulo, autor, areaConocimiento, numeroPaginas, cantidadEjemplares, cantidadPrestados, cantidadDisponibles);
    }

    private static String registrarNovela() {
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo de la Novela: ");
        String autor = JOptionPane.showInputDialog(null, "Ingrese el autor de la Novela: ");
        String genero = JOptionPane.showInputDialog(null, "Ingrese el género de la Novela: ");
        String edadSugerida = JOptionPane.showInputDialog(null, "Ingrese la edad sugerida de la Novela: ");
        String cantidadEjemplares = JOptionPane.showInputDialog(null, "Ingrese la cantidad de ejemplares de la Novela: ");
        String cantidadPrestados = JOptionPane.showInputDialog(null, "Ingrese la cantidad prestada de la Novela: ");
        String cantidadDisponibles = Integer.toString(Integer.parseInt(cantidadEjemplares) - Integer.parseInt(cantidadPrestados));
        JOptionPane.showMessageDialog(null, "¡Novela registrada exitosamente: " + titulo + "!");
        return String.format(INSERT_NOVELA, titulo, autor, genero, edadSugerida, cantidadEjemplares, cantidadPrestados, cantidadDisponibles);
    }

    private static String registrarPrestamo() {
        String correo = JOptionPane.showInputDialog(null, "Ingrese el correo del Lector: ");
        String documento = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro/novela: ");
        String fechaPrestamo = JOptionPane.showInputDialog(null, "Ingrese la fecha del prestamo: ");
        String fechaDevolucion = JOptionPane.showInputDialog(null, "Ingrese la fecha de devolucion: ");
        String estado = JOptionPane.showInputDialog(null, "Ingrese el estado del prestamo: ");
        JOptionPane.showMessageDialog(null, "¡Prestamo registrado exitosamente a: " + correo + "!");
        return String.format(INSERT_PRESTAMO, correo, documento, fechaPrestamo, fechaDevolucion, estado);
    }

    private static String registrarUsuario() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese un nuevo nombre de usuario: ");
        String correo = JOptionPane.showInputDialog(null, "Ingrese una nueva correo: ");
        String contrasena = JOptionPane.showInputDialog(null, "Ingrese una nueva contraseña: ");
        String rol = JOptionPane.showInputDialog(null, "Ingrese el Rol del usuario: ");
        JOptionPane.showMessageDialog(null, "¡Usuario registrado exitosamente: " + nombre + "!");
        return String.format(INSERT_USUARIO, nombre, correo, contrasena, rol);
    }

    private static String crearLibro() {
        String titulo;
        String autor;
        String areaConocimiento;
        String numeroPaginas;
        String cantidadEjemplares;
        String cantidadPrestados;
        String cantidadDisponibles;

        Faker faker = new Faker(new Locale("es"));
        titulo = faker.book().title().replace("'", "");
        autor = faker.book().author().replace("'", "");
        areaConocimiento = faker.book().genre().replace("'", "");
        numeroPaginas = faker.bothify("###");
        cantidadEjemplares = faker.bothify("###");
        cantidadPrestados = "0";
        cantidadDisponibles = cantidadEjemplares;
        return String.format(INSERT_LIBRO, titulo, autor, areaConocimiento, numeroPaginas, cantidadEjemplares, cantidadPrestados, cantidadDisponibles);
    }

    private static String crearNovela() {
        String titulo;
        String autor;
        String genero;
        String edadSugerida;
        String cantidadEjemplares;
        String cantidadPrestados;
        String cantidadDisponibles;

        Faker faker = new Faker(new Locale("es"));
        titulo = faker.book().title().replace("'", "");
        autor = faker.book().author().replace("'", "");
        genero = faker.book().genre().replace("'", "");
        edadSugerida = faker.bothify("##");
        cantidadEjemplares = faker.bothify("###");
        cantidadPrestados = "0";
        cantidadDisponibles = cantidadEjemplares;
        return String.format(INSERT_NOVELA, titulo, autor, genero, edadSugerida, cantidadEjemplares, cantidadPrestados, cantidadDisponibles);
    }

    private static String crearPrestamo() {
        String correo;
        String documento;
        String fechaPrestamo;
        String fechaDevolucion;
        String estado;

        Faker faker = new Faker(new Locale("es"));
        correo = faker.internet().emailAddress();
        documento = faker.book().title().replace("'", "");
        fechaPrestamo = faker.date().past(1, TimeUnit.DAYS, "YYYY-MM-dd");
        fechaDevolucion = faker.date().future(15, TimeUnit.DAYS, "YYYY-MM-dd");
        estado = String.valueOf(Estado.values()[new Random().nextInt(Estado.values().length)]);
        return String.format(INSERT_PRESTAMO, correo, documento, fechaPrestamo, fechaDevolucion, estado);
    }

    private static String crearUsuario() {
        String nombre;
        String correo;
        String contrasena;
        String rol;

        Faker faker = new Faker(new Locale("es"));
        nombre = faker.name().name().replace("'", "");
        correo = faker.internet().emailAddress();
        contrasena = faker.passport().valid();
        rol = String.valueOf(Rol.values()[new Random().nextInt(Rol.values().length)]);
        return String.format(INSERT_USUARIO, nombre, correo, contrasena, rol);
    }

    private static void buscarPrestamoPorNombre() throws SQLException {
        System.out.println("Resultado busqueda del Prestamo");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PRESTAMOS);
        mySqlOperation.executeSqlStatement();
        ResultSet prestamos = mySqlOperation.getResulset();
        while ( prestamos.next() ) {
            String correo = prestamos.getString(1);
            if(correo.equals("ana.meraz@hotmail.com")){
                System.out.println(prestamos);}
        }
    }

    public static void selectAllFromUsuario() throws SQLException {
        System.out.println("Lista de Usuarios Registrados");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_USUARIOS);
        ejecutaSQL();
    }

    public static void selectAllFromLibro() throws SQLException {
        System.out.println("Lista de Libros Registrados");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_LIBROS);
        ejecutaSQL();
    }

    public static void selectAllFromNovela() throws SQLException {
        System.out.println("Lista de Novelas Registradas");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_NOVELAS);
        ejecutaSQL();
    }

    public static void selectAllFromPrestamo() throws SQLException {
        System.out.println("Lista de Prestamos Registrados");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PRESTAMOS);
        ejecutaSQL();
    }
public static void ejecutaSQL() throws SQLException {
    mySqlOperation.executeSqlStatement();
    mySqlOperation.printResulset();
    System.out.println();
}
    public static void insertIntoUsuarioAdministrador() {
        mySqlOperation.setSqlStatement(INSERT_USUARIO);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void insertIntoBd(String insert) {
        mySqlOperation.setSqlStatement(insert);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void openConnection() {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

    public static void closeConnection() {
        mySqlOperation.close();
    }
}

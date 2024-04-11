package com.sofka;

import com.sofka.model.Estado;
import com.sofka.model.Rol;
import com.sofka.model.Usuario;
import lombok.Data;
import net.datafaker.Faker;
import net.datafaker.providers.base.Options;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sofka.integration.database.mysql.MySqlOperation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;
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
    // Crear el usuario administrador

    public static void main(String[] args) throws SQLException, IOException,NumberFormatException {
    usuarioAdministrador = new Usuario("John Doe", "administrador@pingu.com.co", "contrasenasegura123456", "ADMINISTRADOR");
            System.out.println("Bienvenido a la Biblioteca la Pingüinera");
        do {

            openConnection();
            menuInicio();
            closeConnection();

        } while (!(preguntarSalir().equals("s")));
    }
    private static void menuInicio() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Menú de Inicio de la Biblioteca la Pingüinela\n\n" +
                        "1. Iniciar Sesión o Registro\n" +
                        "2. Mostrar Libros\n" +
                        "3. Mostrar Novelas\n" +
                        "4. Mostrar Prestamos\n" +
                        "5. Salir"));

        switch (opcion) {
            case "1":
                menuInicioSesionComo();
                break;
            case "2":
                selectAllFromLibro();
                menuInicio();
                break;
            case "3":
                selectAllFromNovela();
                menuInicio();
                break;
            case "4":
                selectAllFromPrestamo();
                menuInicio();
                break;
            case "5":
                JOptionPane.showMessageDialog(null, "¡Cerrando Biblioteca!");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta, por favor seleccione correctamente");
                menuInicio();
                break;
        }
    }

    private static void menuInicioSesionComo() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Iniciar Sesión Como:\n\n" +
                        "1. ADMINISTRADOR\n" +
                        "2. ASISTENTE\n" +
                        "3. LECTOR\n" +
                        "4. Atras"));

        switch (opcion) {
            case "1":
                menuInicioSesion();
                break;
            case "2":
                menuInicioSesion();
                break;
            case "3":
                menuInicioSesion();
                break;
            case "4":
                JOptionPane.showMessageDialog(null, "¡No se inicio Sesión!");
                menuInicio();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta, por favor seleccione correctamente");
                menuInicioSesion();
                break;
        }
    }

    private static void menuInicioSesion() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Menú de Inicio de Sesión\n\n" +
                        "1. Iniciar Sesión\n" +
                        "2. Registrarse\n" +
                        "3. Salir"));

        switch (opcion) {
            case "1":
                iniciarSesion(opcion);
                break;
            case "2":
                registrarUsuario();
                break;
            case "3":
                JOptionPane.showMessageDialog(null, "¡No se inicio Sesión!");
                menuInicioSesionComo();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta, por favor seleccione correctamente");
                menuInicioSesion();
                break;
        }
    }


    private static void iniciarSesion(String rol) throws SQLException {
        String usuario = JOptionPane.showInputDialog(null, "Ingrese su nombre de usuario:");
        String contrasena = JOptionPane.showInputDialog(null, "Ingrese su contraseña:");

        // Aquí se procede a validar las credenciales con una base de datos o lógica adicional
        if (usuario.equals(usuarioAdministrador.getNombre()) && contrasena.equals(usuarioAdministrador.getContrasena())) {
            JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso para el usuario: " + usuario + "!");
            switch (rol){
                case "1":
                    menuAdministrador();
                    break;
                case "2":
                    //menuAsistente();
                    break;
                case "3":
                    //menuLector();
                    break;
            }
            // Redirige al usuario a la página principal aquí
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            menuInicioSesion();
        }
    }

    private static void menuAdministrador() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Menú de Inicio del Administrador\n\n" +
                        "1. Registrar Usuarios\n" +
                        "2. Registrar Libros\n" +
                        "3. Registrar Novelas\n" +
                        "4. Registrar Prestamos\n" +
                        "5. Mostrar Usuarios\n" +
                        "6. Mostrar Libros\n" +
                        "7. Mostrar Novelas\n" +
                        "8. Mostrar Prestamos\n" +
                        "9. Generar Usuarios\n" +
                        "10. Generar Libros\n" +
                        "11. Generar Novelas\n" +
                        "12. Generar Prestamos\n" +
                        "14. Salir"));

        switch (opcion) {
            case "1":
                menuAdministrador();
                break;
            case "2":
                selectAllFromUsuario();
                menuAdministrador();
                break;
            case "3":
                selectAllFromLibro();
                menuAdministrador();
                break;
            case "4":
                selectAllFromNovela();
                menuAdministrador();
                break;
            case "5":
                selectAllFromUsuario();
                menuAdministrador();
                break;
            case "6":
                selectAllFromLibro();
                menuAdministrador();
                break;
            case "7":
                selectAllFromNovela();
                menuAdministrador();
                break;
            case "8":
                selectAllFromPrestamo();
                menuAdministrador();
                break;
            case "9":
                insertarUsuarioEnBd(preguntarAlUsuario());
                menuAdministrador();
                break;
            case "10":
                insertarLibrosEnBd(preguntarAlUsuario());
                menuAdministrador();
                break;
            case "11":
                insertarNovelaEnBd(preguntarAlUsuario());
                menuAdministrador();
                break;
            case "12":
                insertarPrestamoEnBd(preguntarAlUsuario());
                menuAdministrador();
                break;
            case "14":
                JOptionPane.showMessageDialog(null, "¡Cerrando Sesion!");
                menuInicioSesionComo();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta, por favor seleccione correctamente");
                menuAdministrador();
                break;
        }
    }

    private static void registrarUsuario() {
        String nuevoUsuario = JOptionPane.showInputDialog(null, "Ingrese un nuevo nombre de usuario:");
        String nuevaContrasena = JOptionPane.showInputDialog(null, "Ingrese una nueva contraseña:");

        // Aquí puedes guardar las credenciales en una base de datos o realizar otras acciones
        // Por ahora, simplemente mostraremos un mensaje de éxito.
        JOptionPane.showMessageDialog(null, "¡Usuario registrado exitosamente: " + nuevoUsuario + "!");
    }
    public static String preguntarSalir() throws IOException {
        return JOptionPane.showInputDialog(null, "¿Seguro que desea salir? (S/N):");
    }

    private static int preguntarAlUsuario() {
        int cantidad;
        cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Cual es la cantidad que desea generar: "));
        return cantidad;
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

    private static String crearLibro() {
        String titulo;
        String autor;
        String areaConocimiento;
        String numeroPaginas;
        String cantidadEjemplares;
        String cantidadPrestados;
        String cantidadDisponibles;
        String sentencia;

        Faker faker = new Faker(new Locale("es"));
        titulo = faker.book().title();
        autor = (faker.book().author()).replace("'", "");
        areaConocimiento = (faker.book().genre()).replace("'", "");
        numeroPaginas = faker.bothify("###");
        cantidadEjemplares = faker.bothify("###");
        cantidadPrestados = "0";
        cantidadDisponibles = cantidadEjemplares;
        sentencia = String.format(INSERT_LIBRO, titulo, autor, areaConocimiento, numeroPaginas, cantidadEjemplares, cantidadPrestados, cantidadDisponibles);
        return sentencia;
    }

    private static String crearNovela() {
        String titulo;
        String autor;
        String genero;
        String edadSugerida;
        String cantidadEjemplares;
        String cantidadPrestados;
        String cantidadDisponibles;
        String sentencia;

        Faker faker = new Faker(new Locale("es"));
        titulo = faker.book().title();
        autor = (faker.book().author()).replace("'", "");
        genero = (faker.book().genre()).replace("'", "");
        edadSugerida = faker.bothify("##");
        cantidadEjemplares = faker.bothify("###");
        cantidadPrestados = "0";
        cantidadDisponibles = cantidadEjemplares;
        sentencia = String.format(INSERT_NOVELA, titulo, autor, genero, edadSugerida, cantidadEjemplares, cantidadPrestados, cantidadDisponibles);
        return sentencia;
    }

    private static String crearPrestamo() {
        String correo;
        String documento;
        String fechaPrestamo;
        String fechaDevolucion;
        String estado;
        String sentencia;

        Faker faker = new Faker(new Locale("es"));

        correo = faker.internet().emailAddress();
        documento = faker.book().title();;
        fechaPrestamo = faker.date().birthday().toString();
        fechaDevolucion = faker.date().birthday().toString();
        estado = String.valueOf(Estado.values()[new Random().nextInt(Estado.values().length)]);
        sentencia = String.format(INSERT_PRESTAMO, correo, documento, fechaPrestamo, fechaDevolucion, estado);
        return sentencia;
    }

    private static String crearUsuario() {
        String nombre;
        String correo;
        String contrasena;
        String rol;
        String sentencia;

        Faker faker = new Faker(new Locale("es"));
        nombre = (faker.name().name()).replace("'", "");
        correo = faker.internet().emailAddress();
        contrasena = faker.passport().valid();
        rol = String.valueOf(Rol.values()[new Random().nextInt(Rol.values().length)]);
        sentencia = String.format(INSERT_USUARIO, nombre, correo, contrasena, rol);
        return sentencia;
    }

    public static void selectAllFromUsuario() throws SQLException {
        System.out.println("Lista de Usuarios Registrados");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_USUARIOS);
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
        System.out.println();
    }
    public static void selectAllFromLibro() throws SQLException {
        System.out.println("Lista de Libros Registrados");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_LIBROS);
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
        System.out.println();
    }

    public static void selectAllFromNovela() throws SQLException {
        System.out.println("Lista de Novelas Registradas");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_NOVELAS);
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
        System.out.println();
    }

    public static void selectAllFromPrestamo() throws SQLException {
        System.out.println("Lista de Prestamos Registrados");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PRESTAMOS);
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

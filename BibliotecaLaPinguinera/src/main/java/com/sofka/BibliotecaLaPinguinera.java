package com.sofka;

import com.sofka.Enums.Estado;
import net.datafaker.Faker;
import com.sofka.integration.database.mysql.MySqlOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static com.sofka.Constants.ConectorConstants.*;
import static com.sofka.Constants.InsertConstants.*;
import static com.sofka.Constants.SelectConstants.*;
import static com.sofka.Constants.UpdateConstants.*;
import static com.sofka.controller.ControlEmpleado.*;
import static com.sofka.controller.ControlUsuario.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;


@SpringBootApplication
public class BibliotecaLaPinguinera {
    public static final String SELECCIONE_CORRECTAMENTE = "Opción incorrecta, por favor seleccione correctamente";
    public static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void main(String[] args) throws SQLException, IOException, NumberFormatException {
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
                        "1. Iniciar Sesión\n" +
                        "2. Mostrar Libros\n" +
                        "3. Mostrar Novelas\n" +
                        "4. Mostrar Publicaciones\n" +
                        "5. Mostrar Prestamos\n" +
                        "6. Registrar Usuario\n" +
                        "0. Salir de la Biblioteca"));
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
                selectAllFromPublicacion();
                break;
            case "5":
                selectAllFromPrestamo();
                break;
            case "6":
                registrarNuevoUsuario();
                break;
            case "0":
                mostrarMensaje("¡OjO, Va a salir de la Biblioteca!");
                break;
            default:
                mostrarMensaje(SELECCIONE_CORRECTAMENTE);

        }
        if (!(opcion.equals("0"))) menuInicio();
    }

    private static void menuInicioSesionComo() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Iniciar Sesión o Registrarse Como: \n\n" +
                        "1. Administrador\n" +
                        "2. Asistente\n" +
                        "3. Lector\n" +
                        "0. Atras"));

        switch (opcion) {
            case "1", "2", "3":
                iniciarSesion(opcion);
                break;
            case "0":
                break;
            default:
                mostrarMensaje("Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("0"))) menuInicioSesionComo();
    }

    private static void iniciarSesion(String rol) throws SQLException {
        String usuario = JOptionPane.showInputDialog(null, "Ingrese su nombre: ");
        String contrasena = JOptionPane.showInputDialog(null, "Ingrese su contraseña: ");
        if (rol.equals("1")) validarEmpleado(usuario, contrasena, "ADMINISTRADOR");
        if (rol.equals("2")) validarEmpleado(usuario, contrasena, "ASISTENTE");
        if (Objects.equals(rol, "3")) validarUsuario(usuario, contrasena);
    }

    private static void validarUsuario(String usuario, String contrasena) throws SQLException {
        // Aquí se procede a validar las credenciales con una base de datos
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_USUARIO);
        mySqlOperation.executeSqlStatement();
        ResultSet usuarios = mySqlOperation.getResulset();
        boolean encontrado = false;
        while (usuarios.next()) {
            String nombre = usuarios.getString("nombre");
            String contrasenha = usuarios.getString("contrasenha");
            if (nombre.equals(usuario) && contrasenha.equals(contrasena)) {
                encontrado = true;
                mostrarMensaje("¡Inicio de sesión exitoso para el usuario: " + usuario + "!");
                System.out.println("Validar Usuario");
                menuUsuario();
            }
        }
        if (!encontrado) {
            mostrarMensaje("Usuario o contraseña incorrectos.");
        }

    }

    public static void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private static void validarEmpleado(String usuario, String contrasena, String rol) throws SQLException {
        // Aquí se procede a validar las credenciales con una base de datos
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_EMPLEADO);
        mySqlOperation.executeSqlStatement();
        ResultSet empleados = mySqlOperation.getResulset();
        boolean encontrado = false;
        encontrado = isEncontrado(usuario, contrasena, rol, empleados, encontrado);
        if (!encontrado) {
            mostrarMensaje("Usuario o contraseña incorrectos.");
        }
    }

    private static boolean isEncontrado(String usuario, String contrasena, String rol, ResultSet empleados, boolean encontrado) throws SQLException {
        while (empleados.next()) {
            String nombre = empleados.getString("nombre");
            String contrasenha = empleados.getString("contrasenha");
            String roles = empleados.getString("rol");
            if (nombre.equals(usuario) && contrasenha.equals(contrasena) && roles.equals(rol)) {
                encontrado = true;
                mostrarMensaje("¡Inicio de sesión exitoso para el empleado: " + usuario + "!");
                empleadoConRol(roles);
            }
        }
        return encontrado;
    }

    private static void empleadoConRol(String roles) throws SQLException {
        if (roles.equals("ADMINISTRADOR")) menuAdministrador();
        if (roles.equals("ASISTENTE")) menuAsistente();
    }
    private static void menuUsuario() throws SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Menú del Usuario\n\n" +
                        "1. Buscar libro por autor\n" +
                        "2. Solicitar Prestamo\n" +
                        "3. Mostrar mis Prestamos\n" +
                        "4. Mostrar Libros\n" +
                        "5. Mostrar Novelas\n" +
                        "6. Mostrar Publicaciones\n" +
                        "0.  Cerrar Sesión"));
        switch (opcion) {
            case "1":
                buscarPublicacionPorAutor();
                break;
            case "2":
                insertIntoBd(solicitarPrestamo());
                break;
            case "3":
                selectAllFromPrestamo();
                break;
            case "4":
                selectAllFromLibro();
                break;
            case "5":
                selectAllFromNovela();
                break;
            case "6":
                selectAllFromPublicacion();
                break;
            case "0":
                mostrarMensaje("¡Cerrando Sesión!");
                break;
            default:
                mostrarMensaje("Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("0"))) menuUsuario();
    }

    private static void menuAsistente() throws SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Menú del Asistente\n\n" +
                        "1. Buscar libro por autor\n" +
                        "2. Registrar Libro\n" +
                        "3. Registrar Novela\n" +
                        "4. Buscar prestamos de usuario\n" +
                        "5. Solicitar Prestamo\n" +
                        "6. Realizar Prestamo\n" +
                        "7. Finalizar Prestamo\n" +
                        "8. Mostrar Prestamos\n" +
                        "9. Mostrar Libros\n" +
                        "10. Mostrar Novelas\n" +
                        "11. Registrar Usuario\n" +
                        "0.  Cerrar Sesión"));
        switch (opcion) {
            case "1":
                buscarPublicacionPorAutor();
                break;
            case "2":
                insertIntoBd(registrarLibro().get(0));
                insertIntoBd(registrarLibro().get(1));
                break;
            case "3":
                insertIntoBd(registrarNovela().get(0));
                insertIntoBd(registrarNovela().get(1));
                insertIntoBd(registrarNovela().get(2));
                break;
            case "4":
                buscarPrestamoPorCorreo();
                break;
            case "5":
                insertIntoBd(solicitarPrestamo());
                break;
            case "6":
                insertarLista(realizarPrestamo());
                break;
            case "7":
                insertarLista(finalizarPrestamo());
                break;
            case "8":
                selectAllFromPrestamo();
                break;
            case "9":
                selectAllFromLibro();
                break;
            case "10":
                selectAllFromNovela();
                break;
            case "11":
                registrarNuevoUsuario();
                break;
            case "0":
                mostrarMensaje("¡Cerrando Sesión!");
                break;
            default:
                mostrarMensaje("Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("0"))) menuAsistente();
    }

    private static void menuAdministrador() throws NumberFormatException, SQLException {
        String opcion = (JOptionPane.showInputDialog(null,
                "Menú del Administrador\n\n" +
                        "1.  Registrar Usuario\n" +
                        "2.  Registrar Administrador\n" +
                        "3.  Registrar Asistente\n" +
                        "4.  Registrar Libro\n" +
                        "5.  Registrar Novela\n" +
                        "6.  Registrar Prestamo\n" +
                        "7.  Mostrar Usuarios\n" +
                        "8.  Mostrar Libros\n" +
                        "9.  Mostrar Novelas\n" +
                        "10.  Mostrar Prestamos\n" +
                        "11.  Mostrar Empleados\n" +
                        "12.  Generar Usuarios con Faker\n" +
                        "13. Generar Libros con Faker\n" +
                        "14. Generar Novelas con Faker\n" +
                        "15. Generar Prestamos con Faker\n" +
                        "16. Generar Empleados con Faker\n" +
                        "0.  Cerrar Sesión"));

        ejecutarMenuAdministrador(opcion);
    }

    private static void ejecutarMenuAdministrador(String opcion) throws SQLException {
        switch (opcion) {
            case "1":
                registrarNuevoUsuario();
                break;
            case "2":
                registrarNuevoEmpleado("ADMINISTRADOR");
                break;
            case "3":
                registrarNuevoEmpleado("ASISTENTE");
                break;
            case "4":
                insertIntoBd(registrarLibro().get(0));
                insertIntoBd(registrarLibro().get(1));
                break;
            case "5":
                insertIntoBd(registrarNovela().get(0));
                insertIntoBd(registrarNovela().get(1));
                insertIntoBd(registrarNovela().get(2));
                break;
            case "6":
                insertIntoBd(solicitarPrestamo());
                break;
            case "7":
                selectAllFromUsuario();
                break;
            case "8":
                selectAllFromLibro();
                break;
            case "9":
                selectAllFromNovela();
                break;
            case "10":
                selectAllFromPrestamo();
                break;
            case "11":
                selectAllFromEmpleado();
                break;
            case "12":
                insertarUsuarioFaker(preguntarCantidadAlUsuario());
                break;
            case "13":
                insertarLibrosFaker(preguntarCantidadAlUsuario());
                break;
            case "14":
                insertarNovelaFaker(preguntarCantidadAlUsuario());
                break;
            case "15":
                insertarPrestamoFaker(preguntarCantidadAlUsuario());
                break;
            case "16":
                insertarEmpleadoFaker(preguntarCantidadAlUsuario());
                break;
            case "0":
                mostrarMensaje("¡Cerrando Sesion!");
                break;
            default:
                mostrarMensaje("Opción incorrecta, por favor seleccione correctamente");
                break;
        }
        if (!(opcion.equals("0"))) menuAdministrador();
    }

    private static String menuSelecionEstado(String estado) {
        switch (estado) {
            case "1":
                estado = "SOLICITADO";
                break;
            case "2":
                estado = "REALIZADO";
                break;
            case "3":
                estado = "FINALIZADO";
                break;
            default:
                mostrarMensaje(SELECCIONE_CORRECTAMENTE);
        }
        return estado;
    }

    public static String preguntarSalir() {
        return JOptionPane.showInputDialog(null, "¿Seguro que desea salir? (S/N):");
    }

    private static int preguntarCantidadAlUsuario() {
        return Integer.parseInt(JOptionPane.showInputDialog(null, "Cual es la cantidad que desea generar: "));
    }

    public static void buscarPrestamoPorCorreo() throws SQLException {
        String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese el correo: ");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PRESTAMO);
        mySqlOperation.executeSqlStatement();
        ResultSet prestamos = mySqlOperation.getResulset();
        System.out.println("Resultado busqueda de los Prestamo del usuario ");
        while (prestamos.next()) {
            String correo = prestamos.getString("correoUsuario");
            String titulo = prestamos.getString("tituloPublicacion");
            if (correo.equals(correoUsuario)) {
                System.out.println("Correo: " + correo + ",\t" + "Titulo: " + titulo);
            }
        }
    }

    public static void buscarPublicacionPorAutor() throws SQLException {
        String autorIngresado = JOptionPane.showInputDialog(null, "Ingrese el autor: ");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PUBLICACION);
        mySqlOperation.executeSqlStatement();
        ResultSet prestamos = mySqlOperation.getResulset();
        System.out.println("Resultado busqueda de las Publicaciones del autor ");
        while (prestamos.next()) {
            String autor = prestamos.getString("autor");
            String titulo = prestamos.getString("titulo");
            if (autorIngresado.equals(autor)) {
                System.out.println("Titulo: " + titulo + ",\t" + "Autor: " + autor);
            }
        }
    }

    private static ArrayList<String> registrarLibro() {
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro: ");
        String autor = JOptionPane.showInputDialog(null, "Ingrese el autor del libro: ");
        String areaGenero = JOptionPane.showInputDialog(null, "Ingrese el area de conocimineto del libro: ");
        String numPaginas = JOptionPane.showInputDialog(null, "Ingrese el numero de pag. del libro: ");
        String cantEjemplares = JOptionPane.showInputDialog(null, "Ingrese la cantidad de ejemplares del libro: ");
        String cantPrestados = JOptionPane.showInputDialog(null, "Ingrese la cantidad prestada del libro: ");
        String cantDisponibles = Integer.toString(Integer.parseInt(cantEjemplares) - Integer.parseInt(cantPrestados));
        ArrayList<String> miLista = new ArrayList<>();
        miLista.add(String.format(INSERT_PUBLICACION, titulo, autor, "LIBRO", numPaginas, cantEjemplares, cantPrestados, cantDisponibles));
        miLista.add(crearAreaGenero(titulo, areaGenero));
        mostrarMensaje("¡Libro registrado exitosamente: " + titulo + "!");
        return miLista;
    }

    private static ArrayList<String> registrarNovela() {
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo de la Novela: ");
        String autor = JOptionPane.showInputDialog(null, "Ingrese el autor de la Novela: ");
        String areaGenero = JOptionPane.showInputDialog(null, "Ingrese el género de la Novela: ");
        String numPaginas = JOptionPane.showInputDialog(null, "Ingrese el numero de pag. de la Novela: ");
        String edadSugerida = JOptionPane.showInputDialog(null, "Ingrese la edad sugerida de la Novela: ");
        String cantEjemplares = JOptionPane.showInputDialog(null, "Ingrese la cantidad de ejemplares de la Novela: ");
        String cantPrestados = JOptionPane.showInputDialog(null, "Ingrese la cantidad prestada de la Novela: ");
        String cantDisponibles = Integer.toString(Integer.parseInt(cantEjemplares) - Integer.parseInt(cantPrestados));
        ArrayList<String> miLista = new ArrayList<>();
        miLista.add(String.format(INSERT_PUBLICACION, titulo, autor, "NOVELA", numPaginas, cantEjemplares, cantPrestados, cantDisponibles));
        miLista.add(crearEdadSugerida(titulo, edadSugerida));
        miLista.add(crearAreaGenero(titulo, areaGenero));
        mostrarMensaje("¡Novela registrada exitosamente: " + titulo + "!");
        return miLista;
    }

    public static ArrayList<String> realizarPrestamo() throws SQLException {
        String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese el correo: ");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PRESTAMO);
        mySqlOperation.executeSqlStatement();
        ResultSet prestamos = mySqlOperation.getResulset();
        ArrayList<String> miLista = new ArrayList<>();
        System.out.println("Resultado busqueda de los Prestamo del usuario ");
        while (prestamos.next()) {
            String idPrestamo = prestamos.getString("idPrestamo");
            String correo = prestamos.getString("correoUsuario");
            String titulo = prestamos.getString("tituloPublicacion");
            if (correo.equals(correoUsuario)) {
                miLista.add(String.format(UPDATE_PRESTAMO, "REALIZADO", idPrestamo));
                System.out.println("Correo: " + correo + ",\t" + "Titulo: " + titulo);
            }
        }
        return miLista;
    }

    public static ArrayList<String> finalizarPrestamo() throws SQLException {
        String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese el correo: ");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PRESTAMO);
        mySqlOperation.executeSqlStatement();
        ResultSet prestamos = mySqlOperation.getResulset();
        ArrayList<String> miLista = new ArrayList<>();
        System.out.println("Resultado busqueda de los Prestamo del usuario ");
        while (prestamos.next()) {
            String idPrestamo = prestamos.getString("idPrestamo");
            String correo = prestamos.getString("correoUsuario");
            String titulo = prestamos.getString("tituloPublicacion");
            if (correo.equals(correoUsuario)) {
                miLista.add(String.format(UPDATE_PRESTAMO, "FINALIZADO", idPrestamo));
                System.out.println("Correo: " + correo + ",\t" + "Titulo: " + titulo);
            }
        }
        return miLista;
    }

    private static String solicitarPrestamo() {
        Faker faker = new Faker();
        String idPrestamo = faker.passport().valid();
        String correo = JOptionPane.showInputDialog(null, "Ingrese el correo del Lector: ");
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro/novela: ");
        String estado = ("SOLICITADO");
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = LocalDate.now().plusDays(15);
        mostrarMensaje("¡Prestamo registrado exitosamente a: " + correo + "!");
        return String.format(INSERT_PRESTAMO, idPrestamo, fechaPrestamo, fechaDevolucion, estado, correo, titulo);
    }




    private static ArrayList<String> crearPublicacion(String tipo) {
        String titulo;
        String autor;
        String numPaginas;
        String cantEjemplares;
        String cantPrestados;
        String cantDisponibles;
        String areaGenero;
        ArrayList<String> miLista = new ArrayList<>();
        Faker faker = new Faker(new Locale("es"));

        titulo = faker.book().title().replace("'", "");
        autor = faker.book().author().replace("'", "");
        areaGenero = faker.book().genre().replace("'", "");
        String edadSugerida = Integer.toString(faker.number().numberBetween(5, 25));
        numPaginas = faker.bothify("###");
        cantEjemplares = faker.bothify("###");
        cantPrestados = "0";
        cantDisponibles = cantEjemplares;
        miLista.add(String.format(INSERT_PUBLICACION, titulo, autor, tipo, numPaginas, cantEjemplares, cantPrestados, cantDisponibles));
        miLista.add(crearAreaGenero(titulo, areaGenero));
        if (Objects.equals(tipo, "NOVELA")) miLista.add(crearEdadSugerida(titulo, edadSugerida));
        return miLista;
    }

    private static String crearAreaGenero(String titulo, String areaGenero) {
        return (String.format(INSERT_AREAGENERO, titulo, areaGenero));
    }

    private static String crearEdadSugerida(String titulo, String edadSugerida) {
        return (String.format(INSERT_EDADSUGERIDA, titulo, edadSugerida));
    }

    private static String crearPrestamo() throws SQLException {
        Faker faker = new Faker(new Locale("es"));
        String idPrestamo = faker.number().digits(10);

        String correo = JOptionPane.showInputDialog(null, "Ingrese el correo del Lector: ");
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro/novela: ");

        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = LocalDate.now().plusDays(15);
        String estado = String.valueOf(Estado.values()[new Random().nextInt(Estado.values().length)]);
        return String.format(INSERT_PRESTAMO, idPrestamo, fechaPrestamo, fechaDevolucion, estado, correo, titulo);
    }



    private static void insertarLibrosFaker(int cantidadLibros) {
        for (int i = 0; i < cantidadLibros; i++) {
            ArrayList<String> publicaciones = crearPublicacion("LIBRO");
            insertIntoBd(publicaciones.get(0));
            insertIntoBd(publicaciones.get(1));
        }
    }

    private static void insertarNovelaFaker(int cantidadNovelas) {
        for (int i = 0; i < cantidadNovelas; i++) {
            ArrayList<String> publicaciones = crearPublicacion("NOVELA");
            insertIntoBd(publicaciones.get(0));
            insertIntoBd(publicaciones.get(1));
            insertIntoBd(publicaciones.get(2));
        }
    }

    private static void insertarPrestamoFaker(int cantidad) throws SQLException {
        for (int i = 0; i < cantidad; i++) {
            insertIntoBd(crearPrestamo());
        }
    }



    public static void selectAllFromPublicacion() throws SQLException {
        System.out.println("Lista de Punlicaciones Registradas");
        insertIntoBd(SELECT_ALL_FROM_PUBLICACION);
        ejecutarMostrarSQL();
    }

    public static void selectAllFromLibro() throws SQLException {
        System.out.println("Lista de Libros Registrados");
        insertIntoBd(SELECT_ALL_FROM_LIBRO);
        ejecutarMostrarSQL();
    }

    public static void selectAllFromNovela() throws SQLException {
        System.out.println("Lista de Novelas Registradas");
        insertIntoBd(SELECT_ALL_FROM_NOVELA);
        ejecutarMostrarSQL();
    }

    public static void selectAllFromPrestamo() throws SQLException {
        System.out.println("Lista de Prestamos Registrados");
        insertIntoBd(SELECT_ALL_FROM_PRESTAMO);
        ejecutarMostrarSQL();
    }

    public static void ejecutarMostrarSQL() throws SQLException {
        mySqlOperation.printResulset();
        System.out.println();
    }

    private static void insertarLista(ArrayList<String> lista) throws SQLException {
        for (String elemento : lista) {
            insertIntoBd(elemento);
        }
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

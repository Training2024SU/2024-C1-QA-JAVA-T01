package com.sofka;

import lombok.Data;
import net.datafaker.Faker;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sofka.integration.database.mysql.MySqlOperation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
@Data
public class BibliotecaLaPinguinera {
    private static final String SERVER = "localhost";
    private static final String DATA_BASE_NAME = "libreriabuscalibre";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String SELECT_ALL_FROM_LIBROS = String.format("select * from %s.libro", DATA_BASE_NAME);
    private static final String INSERT_LIBRO = "insert into libreriabuscalibre.libro values ('%s', '%s', '%s', '%s');";
    private static final MySqlOperation mySqlOperation = new MySqlOperation();
    public static BufferedReader bufEntrada = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {
        do {
            System.out.println("Bienvenido a la libreria busca libre");
            System.out.println("Que desea realizar");
            System.out.println("1. Ingresar nuevo estudiante");
            System.out.println("2. Eliminar estudiante");
            openConnection();
            insertarLibrosEnBd(preguntarAlUsuario());
            selectAllFromLibro();
            closeConnection();
        } while (!(preguntarSalir().equals("s")));
    }

    public static String preguntarSalir() throws IOException {
        System.out.println();
        System.out.print("¿Desea salir del programa? (S/N): ");

        return bufEntrada.readLine();
    }

    private static int preguntarAlUsuario() {
        Scanner scanner = new Scanner(System.in);
        int cantidad;
        System.out.println("Cual es el numero de libros que quiere ingresar");
        cantidad = Integer.parseInt(scanner.nextLine());
        return cantidad;
    }

    private static void insertarLibrosEnBd(int cantidadLibros) {
        for (int i = 0; i < cantidadLibros; i++) {
            insertIntoLibros(crearLibro());
        }
    }

    private static String crearLibro() {
        String id;
        String nombre;
        String numeroHojas;
        String editorial;
        String sentencia;
        Faker faker = new Faker(new Locale("es"));
        id = faker.bothify("####???##");
        nombre = (faker.name().name()).replace("'", "");
        numeroHojas = faker.bothify("###");
        editorial = (faker.book().publisher()).replace("'", "");
        sentencia = String.format(INSERT_LIBRO, id, nombre, numeroHojas, editorial);
        return sentencia;
    }


    public static void openConnection() {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

    public static void selectAllFromLibro() throws SQLException {
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_LIBROS);
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
    }

    public static void insertIntoLibro() {
        mySqlOperation.setSqlStatement(INSERT_LIBRO);
        mySqlOperation.executeSqlStatementVoid();

    }

    public static void insertIntoLibros(String insert) {
        mySqlOperation.setSqlStatement(insert);
        mySqlOperation.executeSqlStatementVoid();

    }

    public static void closeConnection() {
        mySqlOperation.close();
    }
}

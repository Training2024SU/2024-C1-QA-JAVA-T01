package co.com.training.integration.database.mysql;

import java.util.Locale;

public class Constantes {
    public static final String SERVER = "localhost";
    public static final String MENSAJE_LIBRO = "Cual es el numero de libros que quiere ingresar";

    public static final String INSERT_LIBRO = "INSERT INTO la_pinguinera_biblioteca.libro VALUES ('%s', '%s', '%s', '%s');";
    public static final String SELECT_ALL_FROM_LIBRO = "SELECT * FROM libro";

}


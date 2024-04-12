package co.com.training.controlador;

import co.com.training.dao.AutorDAO;
import co.com.training.modelo.Autor;
import com.github.javafaker.Faker;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class AutorC {

    public static void ejecutarLogicaPrincipal(AutorDAO autorDAO) throws SQLException {
        int cantidadAutores = preguntarAlUsuario();
        insertarAutoresEnBd(cantidadAutores, autorDAO);
        selectAllFromAutor(autorDAO);
    }

    private static int preguntarAlUsuario() {
        Scanner scanner = new Scanner(System.in);
        int cantidad = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println("¿Cuál es el número de autores que quiere ingresar?");
                String input = scanner.nextLine().trim();
                cantidad = Integer.parseInt(input);
                entradaValida = true; // Si no hay excepción, la entrada es válida
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        return cantidad;
    }

    private static Autor crearAutor() {
        Faker faker = new Faker(new Locale("es"));
        String idAutor = faker.bothify("####???##");
        String nombreAutor = faker.name().firstName();
        String apellidoAutor = faker.name().lastName();
        return new Autor(idAutor, nombreAutor, apellidoAutor);
    }

    private static void insertarAutoresEnBd(int cantidadAutores, AutorDAO autorDAO) {
        for (int i = 0; i < cantidadAutores; i++) {
            Autor autor = crearAutor();
            autorDAO.insertarAutor(autor);
        }
        System.out.println("Registros agregados exitosamente");
    }

    public static void selectAllFromAutor(AutorDAO autorDAO) throws SQLException {
        autorDAO.selectAllFromAutor();
    }
}

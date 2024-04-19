package co.com.training.controlador;

import co.com.training.dao.LibroDAO;
import co.com.training.modelo.Autor;
import co.com.training.modelo.Libro;
import com.github.javafaker.Faker;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class LibroC {

    public static void ejecutarLogicaPrincipal(LibroDAO libroDAO) throws SQLException {
        int cantidadLibros = preguntarAlUsuario();
        insertarLibrosEnBd(cantidadLibros, libroDAO);
        // Aquí puedes agregar más lógica según sea necesario
    }

    private static int preguntarAlUsuario() {
        Scanner scanner = new Scanner(System.in);
        int cantidad = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println("¿Cuál es el número de libros que quiere ingresar?");
                String input = scanner.nextLine().trim();
                cantidad = Integer.parseInt(input);
                entradaValida = true; // Si no hay excepción, la entrada es válida
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        return cantidad;
    }

    private static Libro crearLibro() {
        Faker faker = new Faker(new Locale("es"));
        String id_material = faker.bothify("####???##");
        String titulo = faker.book().title();

        // Crear el autor con su ID único
        String id_autor = faker.random().toString();
        String nombre_autor = faker.book().author();
        String apellido_autor = faker.book().author();
        Autor autor = new Autor(id_autor, nombre_autor, apellido_autor);

        String area_conocimiento = faker.book().genre();
        int numero_paginas = faker.number().numberBetween(50, 1000);
        int cantidad_ejemplares = faker.number().numberBetween(1, 50);
        int cantidad_prestados = faker.number().numberBetween(0, cantidad_ejemplares);

        // Crear y devolver el objeto Libro con los datos generados
        return new Libro(id_material, titulo, autor, cantidad_ejemplares, cantidad_prestados, area_conocimiento, numero_paginas);
    }

    private static void insertarLibrosEnBd(int cantidadLibros, LibroDAO libroDAO) {
        for (int i = 0; i < cantidadLibros; i++) {
            Libro libro = crearLibro();
            libroDAO.agregarLibro(libro);
        }
        System.out.println("Registros agregados exitosamente");
    }
}

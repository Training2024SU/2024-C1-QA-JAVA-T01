package com.sofka.pruebasPOO;

import com.sofka.modelo.Libro;
import com.sofka.modelo.Novela;
import com.sofka.modelo.Prestamo;
import com.sofka.modelo.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


// Clase principal para la Biblioteca La Pingüinera, contiene los métodos principales para gestionar todo lo relacionado con la biblioteca.

public class PruebasPOO {

    private static ArrayList<Usuario> empleados;
    private static ArrayList<Libro> libros;
    private static ArrayList<Novela> novelas;
    private static ArrayList<Prestamo> prestamos;

    Usuario usuarioAdministrador = new Usuario("John Doe", "administrador@pingu.com.co", "contrasenasegura123456");

    public static void main(String[] args) {

// Crear el usuario administrador

        // Crear algunos asistentes

        empleados = new ArrayList<>();
        empleados.add(new Usuario("Asistente 1", "asistente1@pingu.com.co", "contrasena123"));
        empleados.add(new Usuario("Asistente 2", "asistente2@pingu.com.co", "contrasena456"));

        // Crear algunos libros y novelas

        libros = new ArrayList<>();
        libros.add(new Libro("Libro 1", "Autor 1", 100, 200, 10, 190));
        libros.add(new Libro("Libro 2", "Autor 2", 110, 300, 5, 295));
        novelas = new ArrayList<>();
        novelas.add(new Novela("Novela 1", "Autor 1", "NOVELA", 12, 8, 4, 4));
        novelas.add(new Novela("Novela 2", "Autor 2", "NOVELA", 16, 4, 1, 3));

        // Crear algunos préstamos

        prestamos = new ArrayList<>();
        Usuario usuario1 = new Usuario("Usuario 1", "usuario1@ejemplo.com", "contrasena123");
        ArrayList<Libro> librosUsuario1 = new ArrayList<>();
        librosUsuario1.add(libros.get(0));
        librosUsuario1.add(libros.get(1));
        ArrayList<Novela> novelasUsuario1 = new ArrayList<>();
        novelasUsuario1.add(novelas.get(0));
        prestamos.add(new Prestamo("novelasUsuario1", "2023-04-01", "2023-04-15", "SOLICITADO", usuario1.getCorreo(), librosUsuario1.get(0).getTitulo()));
    }

    // Métodos para gestionar usuarios, libros, novelas y préstamos

// Métodos para gestionar usuarios

    public static void agregarAsistente(Usuario asistente) {
        empleados.add(asistente);
    }

    public static boolean autenticarUsuario(String correo, String contrasena) {
        if ("correo".equals(correo) && "usuarioAdministrador".equals(contrasena)) {
            return true;
        }
        for (Usuario asistente : empleados) {
            if (asistente.getCorreo().equals(correo) && asistente.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public static Usuario obtenerUsuario(String correo) {
        if ("usuarioAdministrador".equals(correo)) {
            return new Usuario();
        }
        for (Usuario asistente : empleados) {
            if (asistente.getCorreo().equals(correo)) {
                return asistente;
            }
        }
        return null;
    }

// Métodos para gestionar libros

    public static void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public static void actualizarLibro(Libro libro) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equals(libro.getTitulo())) {
                libros.set(i, libro);
                return;
            }
        }
    }

    public static void eliminarLibro(Libro libro) {
        libros.remove(libro);
    }

    public static ArrayList<Libro> obtenerLibros() {
        return libros;
    }

    public static ArrayList<String> obtenerAutoresLibros() {
        Set<String> autores = new HashSet<>();
        for (Libro libro : libros) {
            autores.add(libro.getAutor());
        }
        return new ArrayList<>(autores);
    }

    public static ArrayList<Libro> obtenerLibrosPorAutor(String autor) {
        ArrayList<Libro> librosAutor = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAutor().equals(autor)) {
                librosAutor.add(libro);
            }
        }
        return librosAutor;
    }

// Métodos para gestionar novelas

    public static void agregarNovela(Novela novela) {
        novelas.add(novela);
    }

    public static void actualizarNovela(Novela novela) {
        for (int i = 0; i < novelas.size(); i++) {
            if (novelas.get(i).getTitulo().equals(novela.getTitulo())) {
                novelas.set(i, novela);
                return;
            }
        }
    }

    public static void eliminarNovela(Novela novela) {
        novelas.remove(novela);
    }

    public static ArrayList<Novela> obtenerNovelas() {
        return novelas;
    }

    public static ArrayList<String> obtenerAutoresNovelas() {
        Set<String> autores = new HashSet<>();
        for (Novela novela : novelas) {
            autores.add(novela.getAutor());
        }
        return new ArrayList<>(autores);
    }

    public static ArrayList<Novela> obtenerNovelasPorAutor(String autor) {
        ArrayList<Novela> novelasAutor = new ArrayList<>();
        for (Novela novela : novelas) {
            if (novela.getAutor().equals(autor)) {
                novelasAutor.add(novela);
            }
        }
        return novelasAutor;
    }

// Métodos para gestionar préstamos

    public static void realizarPrestamo(String idPrestamos, String fechaPrestamo, String fechaDevolucion, String estado, String correo, String titulo) {
        Prestamo prestamo = new Prestamo(idPrestamos, fechaPrestamo, fechaDevolucion, estado, correo, titulo);
        prestamos.add(prestamo);
        for (Libro libro : libros) {
            libro.prestar();
        }
        for (Novela novela : novelas) {
            novela.prestar();
        }
    }

    public static void devolverPrestamo(Prestamo prestamo) {
//        for (String libros : prestamos) {
//            libros.devolver();
//        }
//        for (Novela novela : prestamo.getNovelas()) {
//            novela.devolver();
//        }
//        prestamos.remove(prestamo);
    }

    public static ArrayList<Prestamo> obtenerPrestamosPorUsuario(Usuario usuario) {
        ArrayList<Prestamo> prestamosUsuario = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCorreoUsuario().equals(usuario)) {
                prestamosUsuario.add(prestamo);
            }
        }
        return prestamosUsuario;
    }
}

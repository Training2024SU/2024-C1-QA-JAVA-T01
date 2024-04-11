package com.sofka;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


// Clase principal para la Biblioteca La Pingüinera, contiene los métodos principales para gestionar todo lo relacionado con la biblioteca.

public class BibliotecaPinguinera {
    private static Usuario usuarioAdministrador;
    private static ArrayList<Usuario> asistentes;
    private static ArrayList<Libro> libros;
    private static ArrayList<Novela> novelas;
    private static ArrayList<Prestamo> prestamos;

    public static void main(String[] args) {

// Crear el usuario administrador
        usuarioAdministrador = new Usuario("John Doe", "administrador@pingu.com.co", "contraseñasegura123456", "ADMINISTRADOR");

        // Crear algunos asistentes

        asistentes = new ArrayList<>();
        asistentes.add(new Usuario("Asistente 1", "asistente1@pingu.com.co", "contrasena123", "ASISTENTE"));
        asistentes.add(new Usuario("Asistente 2", "asistente2@pingu.com.co", "contrasena456", "ASISTENTE"));

        // Crear algunos libros y novelas

        libros = new ArrayList<>();
        libros.add(new Libro("Libro 1", "Autor 1", "Área 1", 200, 10));
        libros.add(new Libro("Libro 2", "Autor 2", "Área 2", 300, 5));
        novelas = new ArrayList<>();
        novelas.add(new Novela("Novela 1", "Autor 1", "Género 1", 12, 8));
        novelas.add(new Novela("Novela 2", "Autor 2", "Género 2", 16, 4));

        // Crear algunos préstamos

        prestamos = new ArrayList<>();
        Usuario usuario1 = new Usuario("Usuario 1", "usuario1@ejemplo.com", "contrasena123", "LECTOR");
        ArrayList<Libro> librosUsuario1 = new ArrayList<>();
        librosUsuario1.add(libros.get(0));
        librosUsuario1.add(libros.get(1));
        ArrayList<Novela> novelasUsuario1 = new ArrayList<>();
        novelasUsuario1.add(novelas.get(0));
        prestamos.add(new Prestamo(usuario1, librosUsuario1, novelasUsuario1, "2023-04-01", "2023-04-15","SOLICITADO"));
    }

    // Métodos para gestionar usuarios, libros, novelas y préstamos

// Métodos para gestionar usuarios

    public static void agregarAsistente(Usuario asistente) {
        asistentes.add(asistente);
    }

    public static boolean autenticarUsuario(String correo, String contrasena) {
        if (usuarioAdministrador.getCorreo().equals(correo) && usuarioAdministrador.getContrasena().equals(contrasena)) {
            return true;
        }
        for (Usuario asistente : asistentes) {
            if (asistente.getCorreo().equals(correo) && asistente.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public static Usuario obtenerUsuario(String correo) {
        if (usuarioAdministrador.getCorreo().equals(correo)) {
            return usuarioAdministrador;
        }
        for (Usuario asistente : asistentes) {
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

    public static void realizarPrestamo(Usuario usuario, ArrayList<Libro> libros, ArrayList<Novela> novelas, String fechaPrestamo, String fechaDevolucion, String estado) {
        Prestamo prestamo = new Prestamo(usuario, libros, novelas, fechaPrestamo, fechaDevolucion,estado);
        prestamos.add(prestamo);
        for (Libro libro : libros) {
            libro.prestar();
        }
        for (Novela novela : novelas) {
            novela.prestar();
        }
    }

    public static void devolverPrestamo(Prestamo prestamo) {
        for (Libro libro : prestamo.getLibros()) {
            libro.devolver();
        }
        for (Novela novela : prestamo.getNovelas()) {
            novela.devolver();
        }
        prestamos.remove(prestamo);
    }

    public static ArrayList<Prestamo> obtenerPrestamosPorUsuario(Usuario usuario) {
        ArrayList<Prestamo> prestamosUsuario = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().equals(usuario)) {
                prestamosUsuario.add(prestamo);
            }
        }
        return prestamosUsuario;
    }
}

package co.com.biblioteca.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String areaConocimiento;
    private int numeroPaginas;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponible;

    // Constructor para inicializar todos los atributos
    public Libro(int id, String titulo, String autor, String areaConocimiento, int numeroPaginas,
                 int cantidadEjemplares, int cantidadPrestados, int cantidadDisponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.areaConocimiento = areaConocimiento;
        this.numeroPaginas = numeroPaginas;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestados = cantidadPrestados;
        this.cantidadDisponible = cantidadDisponible;
    }

    public Libro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(String areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(int cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public int getCantidadPrestados() {
        return cantidadPrestados;
    }

    public void setCantidadPrestados(int cantidadPrestados) {
        this.cantidadPrestados = cantidadPrestados;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public boolean estaDisponible() {
        return cantidadDisponible > 0;
    }


    // Método para realizar el préstamo de un libro
    public void prestamo() {
        if (estaDisponible()) {
            cantidadPrestados++;
            cantidadDisponible--;
            System.out.println("El libro '" + titulo + "' ha sido prestado.");
        } else {
            System.out.println("El libro '" + titulo + "' no está disponible para préstamo.");
        }
    }

    // Método para devolver un libro prestado
    public void devolver() {
        if (cantidadPrestados > 0) {
            cantidadPrestados--;
            cantidadDisponible++;
            System.out.println("El libro '" + titulo + "' ha sido devuelto.");
        } else {
            System.out.println("No hay ejemplares de '" + titulo + "' prestados actualmente.");
        }
    }

    // Método para imprimir información del libro

    public static Libro obtenerLibroDesdeResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String autor = rs.getString("autor");
        String areaConocimiento = rs.getString("area_conocimiento");
        int numeroPaginas = rs.getInt("numero_paginas");
        int cantidadEjemplares = rs.getInt("cantidad_ejemplares");
        int cantidadPrestados = rs.getInt("cantidad_prestados");
        int cantidadDisponible = rs.getInt("cantidad_disponible");

        return new Libro(id, titulo, autor, areaConocimiento, numeroPaginas,
                cantidadEjemplares, cantidadPrestados, cantidadDisponible);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", areaConocimiento='" + areaConocimiento + '\'' +
                ", numeroPaginas=" + numeroPaginas +
                ", cantidadEjemplares=" + cantidadEjemplares +
                ", cantidadPrestados=" + cantidadPrestados +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}

package com.sofka.modelo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Clase para representar un libro
@Getter
@NoArgsConstructor
@ToString
public class Libro extends Publicacion {
    private String titulo;
    private String autor;
    private String tipo = "LIBRO";
    private int numeroPaginas;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponibles;

    public Libro(String titulo, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantiDisponibles) {
        super(titulo, autor, numPaginas, cantEjemplares, cantPrestados, cantiDisponibles);
    }

    public void prestar() {
        cantidadPrestados++;
        cantidadDisponibles--;
    }

    public void devolver() {
        cantidadPrestados--;
        cantidadDisponibles++;
    }
}
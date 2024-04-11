package com.sofka;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Clase para representar un libro
@Data
@Getter
@NoArgsConstructor
@ToString
class Libro {
    private String titulo;
    private String autor;
    private String area;
    private int numeroPaginas;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponibles;

    public Libro(String titulo, String autor, String area, int numeroPaginas, int cantidadEjemplares) {
        this.titulo = titulo;
        this.autor = autor;
        this.area = area;
        this.numeroPaginas = numeroPaginas;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestados = 0;
        this.cantidadDisponibles = cantidadEjemplares;
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
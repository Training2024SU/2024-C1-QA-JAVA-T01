package com.sofka.modelo;

// Clase para representar una novela

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Novela extends Publicacion {
    private String titulo;
    private String autor;
    private String tipo = "NOVELA";
    private int numPaginas;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponibles;

    public Novela(String titulo, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantiDisponibles) {
        super(titulo, autor, numPaginas, cantEjemplares, cantPrestados, cantiDisponibles);
    }

    @Override
    public void prestar() {
        cantidadPrestados++;
        cantidadDisponibles--;
    }

    @Override
    public void devolver() {
        cantidadPrestados--;
        cantidadDisponibles++;
    }
}

package com.sofka.modelo;

// Clase para representar una Publicacion

import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Publicacion {
    private String titulo;
    private String autor;
    private String tipo;
    private int numPaginas;
    private int cantEjemplares;
    private int cantPrestados;
    private int cantiDisponibles;

    public Publicacion(String titulo, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantiDisponibles) {
        this.titulo = titulo;
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.cantEjemplares = cantEjemplares;
        this.cantPrestados = cantPrestados;
        this.cantiDisponibles = cantiDisponibles;
    }

    public void prestar() {
        cantPrestados++;
        cantiDisponibles--;
    }

    public void devolver() {
        cantPrestados--;
        cantiDisponibles++;
    }
}

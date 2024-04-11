package com.sofka;

// Clase para representar una novela

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Getter
@NoArgsConstructor
@ToString
class Novela {
    private String titulo;
    private String autor;
    private String genero;
    private int edadSugerida;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponibles;

    public Novela(String titulo, String autor, String genero, int edadSugerida, int cantidadEjemplares) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.edadSugerida = edadSugerida;
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

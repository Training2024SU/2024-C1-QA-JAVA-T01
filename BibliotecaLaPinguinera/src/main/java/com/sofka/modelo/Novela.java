package com.sofka.modelo;

// Clase para representar una novela

import lombok.*;

@Data
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

    public void prestar() {
        cantidadPrestados++;
        cantidadDisponibles--;
    }

    public void devolver() {
        cantidadPrestados--;
        cantidadDisponibles++;
    }
}

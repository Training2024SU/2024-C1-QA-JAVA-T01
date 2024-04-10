package com.sofka;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

// Clase para representar un préstamo

@Data
@Getter
@NoArgsConstructor
class Prestamo {
    private Usuario usuario;
    private ArrayList<Libro> libros;
    private ArrayList<Novela> novelas;
    private String fechaPrestamo;
    private String fechaDevolucion;

    public Prestamo(Usuario usuario, ArrayList<Libro> libros, ArrayList<Novela> novelas, String fechaPrestamo, String fechaDevolucion) {
        this.usuario = usuario;
        this.libros = libros;
        this.novelas = novelas;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

}

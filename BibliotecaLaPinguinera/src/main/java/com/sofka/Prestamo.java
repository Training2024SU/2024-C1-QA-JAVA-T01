package com.sofka;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

// Clase para representar un préstamo

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Prestamo {
    private Usuario usuario;
    private ArrayList<Libro> libros;
    private ArrayList<Novela> novelas;
    private String fechaPrestamo;
    private String fechaDevolucion;

}

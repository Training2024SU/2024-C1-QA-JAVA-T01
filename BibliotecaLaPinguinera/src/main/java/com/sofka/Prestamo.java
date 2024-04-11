package com.sofka;


import lombok.*;

import java.util.ArrayList;

// Clase para representar un préstamo

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Prestamo {
    private Usuario usuario;
    private ArrayList<Libro> libros;
    private ArrayList<Novela> novelas;
    private String fechaPrestamo;
    private String fechaDevolucion;

}

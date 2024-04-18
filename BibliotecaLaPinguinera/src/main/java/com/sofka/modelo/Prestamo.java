package com.sofka.modelo;

import lombok.*;

// Clase para representar un préstamo
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prestamo {
    private String idPrestamo;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String estado;
    private String correoUsuario;
    private String tituloPublicacion;
}

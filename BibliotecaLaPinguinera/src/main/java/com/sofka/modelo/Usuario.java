package com.sofka.modelo;

import lombok.*;

// Clase para representar un usuario
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    private String correo;
    private String nombre;
    private String contrasena;
}

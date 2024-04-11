package com.sofka;

import lombok.*;

// Clase para representar un usuario
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Usuario {
    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;

}

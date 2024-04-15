package com.sofka.modelo;

import lombok.*;

// Clase para representar un usuario
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empleado {
    private String idEmpleado;
    private String nombre;
    private String contrasena;
    private String correo;
    private String rol;
}

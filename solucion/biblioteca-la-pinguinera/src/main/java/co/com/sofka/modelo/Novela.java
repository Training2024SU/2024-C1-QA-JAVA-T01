package co.com.sofka.modelo;

import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Novela extends Material{
    private String genero;
    private int edadDeLecturaSugerida;

    @Builder
    public Novela(String titulo, String autor, int cantidadEjemplares, String genero, int edadDeLecturaSugerida){
        this.titulo = titulo;
        this.autor = autor;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestados = 0;
        this.genero = genero;
        this.edadDeLecturaSugerida = edadDeLecturaSugerida;
    }

    public boolean sePuedePrestar(){
        return (this.cantidadEjemplares - cantidadPrestados) > 0;
    }


}

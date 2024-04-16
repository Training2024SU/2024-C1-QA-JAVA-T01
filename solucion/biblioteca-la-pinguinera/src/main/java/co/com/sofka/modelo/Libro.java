package co.com.sofka.modelo;

import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Libro extends Material{
    private String areaDelConocimiento;
    private int numeroDePaginas;

    public Libro(String titulo, String autor, int cantidadEjemplares, String areaDelConocimiento, int numeroDePaginas){
        this.titulo = titulo;
        this.autor = autor;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestados = 0;
        this.areaDelConocimiento = areaDelConocimiento;
        this.numeroDePaginas = numeroDePaginas;

    }

    public boolean sePuedePrestar(){
        return (cantidadEjemplares - cantidadPrestados) > 0;
    }

    @Override
    public void imprimirDetalles(){
        super.imprimirDetalles();
        System.out.println("Area del conocimiento: " + getAreaDelConocimiento());
        System.out.println("Numero de paginas: " + getNumeroDePaginas());
    }
}

package co.com.sofka.modelo;

import lombok.*;
import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String titulo;
    protected String autor;
    protected int cantidadEjemplares;
    protected int cantidadPrestados;
    protected int cantidadDisponible;
    protected boolean fueBorrado = false;

 protected void imprimirDetalles(){
     System.out.println("Título: " + getTitulo());
     System.out.println("Autor: " + getAutor());
     System.out.println("Cantidad de ejemplares: " + getCantidadEjemplares());
     System.out.println("Cantidad de prestados: " + getCantidadPrestados());
     System.out.println("Cantidad de disponible: " + getCantidadDisponible());

 }


}

package co.com.sofka.modelo;

import lombok.*;
import org.hibernate.annotations.Where;

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

}

package co.com.sofka.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@ToString
public abstract class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponible;

}

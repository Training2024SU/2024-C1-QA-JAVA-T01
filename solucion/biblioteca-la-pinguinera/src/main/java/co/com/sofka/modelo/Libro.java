package co.com.sofka.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String areaDelConocimiento;
    private int numeroDePaginas;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponible;


}

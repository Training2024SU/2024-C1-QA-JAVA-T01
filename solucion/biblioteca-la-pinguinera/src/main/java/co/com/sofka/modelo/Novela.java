package co.com.sofka.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Novela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    private int edadDeLecturaSugerida;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponible;
}

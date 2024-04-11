package co.com.sofka.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
    private Usuario usuario;

    private Long material_id;

    LocalDateTime fechaDePrestamo;
    private LocalDateTime feachaDeEntrega;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estadoPrestamo;
}

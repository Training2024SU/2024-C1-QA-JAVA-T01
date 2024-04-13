package co.com.pinguinera.modelos;

import co.com.pinguinera.enums.EstadoPrestamo;
import java.time.LocalDate;

public class Prestamo {
    private int idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private EstadoPrestamo estado; // Usamos la clase EstadoPrestamo
    private String correoUsuario;
    private String tituloPublicacion;

    public Prestamo() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public Prestamo(int idPrestamo, LocalDate fechaPrestamo, LocalDate fechaDevolucion, EstadoPrestamo estado, String correoUsuario, String tituloPublicacion) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.correoUsuario = correoUsuario;
        this.tituloPublicacion = tituloPublicacion;
    }

    // Getters y Setters
    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setTituloPublicacion(String tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", estado=" + estado +
                ", correoUsuario='" + correoUsuario + '\'' +
                ", tituloPublicacion='" + tituloPublicacion + '\'' +
                '}';
    }
}

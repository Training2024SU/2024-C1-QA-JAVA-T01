package co.com.pinguinera.datos.model;

import co.com.pinguinera.datos.model.enums.EstadoPrestamo;

import java.time.LocalDate;

public class Prestamo {
    private int idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private EstadoPrestamo estado; // Usamos la clase EstadoPrestamo
    private int idUsuario; // Cambiado a idUsuario
    private int idPublicacion; // Cambiado a idPublicacion

    public Prestamo() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public Prestamo(int idPrestamo, LocalDate fechaPrestamo, LocalDate fechaDevolucion, EstadoPrestamo estado, int idUsuario, int idPublicacion) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.idUsuario = idUsuario; // Usamos idUsuario
        this.idPublicacion = idPublicacion; // Usamos idPublicacion
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    @Override
    public String toString() {
        final String RESET = "\033[0m";
        final String GREEN = "\033[32m";
        String columnas = String.format(
                "%-10s %-20s %-20s %-10s %-15s %-15s",
                "ID", "Fecha Préstamo", "Fecha Devolución", "Estado", "ID Usuario", "ID Publicación"
        );
        String datos = String.format(
                "%s%-10d%s %s%-20s%s %s%-20s%s %s%-10s%s %s%-15d%s %s%-15d%s",
                GREEN, idPrestamo, RESET,
                GREEN, fechaPrestamo, RESET,
                GREEN, fechaDevolucion, RESET,
                GREEN, estado, RESET,
                GREEN, idUsuario, RESET,
                GREEN, idPublicacion, RESET
        );
        return "\n" + columnas + "\n" + datos;
    }

}

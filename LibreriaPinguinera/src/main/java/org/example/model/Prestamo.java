package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prestamo {
    public static final List<Prestamo> prestamos=new ArrayList<>();
    private int id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String estadoPrestamo;
    private String correoUsuario;
    private String tituloPublicacion;

    public Prestamo() {
    }

    public Prestamo(int id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String estadoPrestamo, String correoUsuario, String tituloPublicacion) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoPrestamo = estadoPrestamo;
        this.correoUsuario = correoUsuario;
        this.tituloPublicacion = tituloPublicacion;
    }

    public Prestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion, String estadoPrestamo, String correoUsuario, String tituloPublicacion) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoPrestamo = estadoPrestamo;
        this.correoUsuario = correoUsuario;
        this.tituloPublicacion = tituloPublicacion;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", estadoPrestamo='" + estadoPrestamo + '\'' +
                ", correoUsuario='" + correoUsuario + '\'' +
                ", tituloPublicacion='" + tituloPublicacion + '\'' +
                '}';
    }
}

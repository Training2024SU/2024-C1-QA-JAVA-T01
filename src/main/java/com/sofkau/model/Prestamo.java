package com.sofkau.model;

import java.util.Date;

public class Prestamo {
    private String id;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    private String estadoPrestamo;
    private String correoUsuario;
    private String tituloPublicacion;

    public Prestamo(String id, Date fechaPrestamo, Date fechaDevolucion, String estadoPrestamo, String correoUsuario, String tituloPublicacion) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoPrestamo = estadoPrestamo;
        this.correoUsuario = correoUsuario;
        this.tituloPublicacion = tituloPublicacion;
    }

    public Prestamo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
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
                "id='" + id + '\'' +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", estadoPrestamo='" + estadoPrestamo + '\'' +
                ", correoUsuario='" + correoUsuario + '\'' +
                ", tituloPublicacion='" + tituloPublicacion + '\'' +
                '}';
    }
}

package co.com.training.models;

import co.com.training.util.enums.EstadoPrestamo;

import java.util.Date;

public class Prestamo {
    private int idPrestamo;
    private Usuario correoUsuario;
    private Publicacion publicacion;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private EstadoPrestamo estado;

    public Prestamo(int idPrestamo, Usuario correoUsuario, Publicacion publicacion, Date fechaPrestamo, Date fechaDevolucion, EstadoPrestamo estado) {
        this.idPrestamo = idPrestamo;
        this.correoUsuario = correoUsuario;
        this.publicacion = publicacion;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Usuario getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(Usuario correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
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

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

}
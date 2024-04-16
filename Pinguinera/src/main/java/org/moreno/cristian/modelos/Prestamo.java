package org.moreno.cristian.modelos;

import com.github.javafaker.Faker;
import org.moreno.cristian.modelos.enums.EstadoPrestamo;

import java.time.LocalDate;
import java.util.List;

public class Prestamo {

    private String id;
    private LocalDate fechaRealizado;
    private LocalDate fechaExpiracion;
    private Usuario usuario;
    private List<? extends Publicacion> publicaciones;
    private EstadoPrestamo estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaRealizado() {
        return fechaRealizado;
    }

    public void setFechaRealizado(LocalDate fechaRealizado) {
        this.fechaRealizado = fechaRealizado;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<? extends Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<? extends Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public Prestamo(String id, LocalDate fechaRealizado, LocalDate fechaExpiracion, Usuario usuario, List<? extends Publicacion> publicaciones) {
        this.id = id;
        this.fechaRealizado = fechaRealizado;
        this.fechaExpiracion = fechaExpiracion;
        this.usuario = usuario;
        this.publicaciones = publicaciones;
    }

    public Prestamo(LocalDate fechaRealizado, LocalDate fechaExpiracion, Usuario usuario, List<? extends Publicacion> publicaciones) {
        this.id = new Faker().bothify("#####");
        this.fechaRealizado = fechaRealizado;
        this.fechaExpiracion = fechaExpiracion;
        this.usuario = usuario;
        this.publicaciones = publicaciones;
        this.estado = EstadoPrestamo.REALIZADO;
    }
}

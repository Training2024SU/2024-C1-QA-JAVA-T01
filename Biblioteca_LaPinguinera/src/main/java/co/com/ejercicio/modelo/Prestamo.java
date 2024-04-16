package co.com.ejercicio.modelo;

import co.com.ejercicio.util.enums.EstadoPrestamo;

import java.sql.Date;

public class Prestamo {
    private int idPrestamo;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;
    private String usuarioCorreo;
    private String publicacionTitulo;

    public Prestamo(int idPrestamo, Date fechaPrestamo, Date fechaDevolucion, String estado, String usuarioCorreo, String publicacionTitulo) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.usuarioCorreo = usuarioCorreo;
        this.publicacionTitulo = publicacionTitulo;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getPublicacionTitulo() {
        return publicacionTitulo;
    }

    public void setPublicacionTitulo(String publicacionTitulo) {
        this.publicacionTitulo = publicacionTitulo;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", estado=" + estado +
                ", usuarioCorreo='" + usuarioCorreo + '\'' +
                ", publicacionTitulo='" + publicacionTitulo + '\'' +
                '}';
    }
}

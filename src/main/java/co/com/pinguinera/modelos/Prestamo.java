package co.com.pinguinera.modelos;

import java.util.Date;

public class Prestamo {
    private int prestamoID;
    private int usuarioID;
    private int libroID;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;

    // Getters y setters para prestamoID
    public int getPrestamoID() {
        return prestamoID;
    }

    public void setPrestamoID(int prestamoID) {
        this.prestamoID = prestamoID;
    }

    // Getters y setters para usuarioID
    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    // Getters y setters para libroID
    public int getLibroID() {
        return libroID;
    }

    public void setLibroID(int libroID) {
        this.libroID = libroID;
    }

    // Getters y setters para fechaPrestamo
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    // Getters y setters para fechaDevolucion
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y setters para estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

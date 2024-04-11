package co.com.pinguinera.modelos;

import java.util.Date;

public class Prestamo {
    private int prestamoID;
    private int usuarioID;
    private int itemID; // Puede ser un libro o una novela
    private String tipo; // Puede ser "LIBRO" o "NOVELA"
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado; // Puede ser "SOLICITADO", "REALIZADO" o "FINALIZADO"

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

    // Getters y setters para itemID (puede ser libro o novela)
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    // Getters y setters para tipo (LIBRO o NOVELA)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if ("LIBRO".equals(tipo) || "NOVELA".equals(tipo)) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("Tipo debe ser LIBRO o NOVELA");
        }
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

    // Getters y setters para estado (SOLICITADO, REALIZADO, FINALIZADO)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if ("SOLICITADO".equals(estado) || "REALIZADO".equals(estado) || "FINALIZADO".equals(estado)) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado debe ser SOLICITADO, REALIZADO o FINALIZADO");
        }
    }

    // Sobrescribe el método toString() para mostrar la información del préstamo de forma legible
    @Override
    public String toString() {
        return "Prestamo{" +
                "ID de préstamo: " + prestamoID +
                ", ID de usuario: " + usuarioID +
                ", ID de ítem: " + itemID +
                ", Tipo de ítem: '" + tipo + '\'' +
                ", Fecha de préstamo: " + fechaPrestamo +
                ", Fecha de devolución: " + fechaDevolucion +
                ", Estado: '" + estado + '\'' +
                '}';
    }
}

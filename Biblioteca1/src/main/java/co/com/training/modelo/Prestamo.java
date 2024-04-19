package co.com.training.modelo;

import java.util.Date;

public class Prestamo {
    private int id_prestamo;
    private Usuario usuario;
    private Material material;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;

    public Prestamo() {
    }

    public Prestamo(int id_prestamo, Usuario usuario, Material material, Date fechaPrestamo, Date fechaDevolucion, String estado) {
        this.id_prestamo = id_prestamo;
        this.usuario = usuario;
        this.material = material;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public Prestamo(Usuario usuario, Material material) {
        this.usuario = usuario;
        this.material = material;
        this.fechaPrestamo = new Date(); // Establecer la fecha actual como fecha de préstamo
        this.fechaDevolucion = null; // La fecha de devolución se establecerá cuando se devuelva el material
        this.estado = "Pendiente"; // Por defecto, el estado del préstamo es "Pendiente"
    }


    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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

    @Override
    public String toString() {
        return "Prestamo{" +
                "id_prestamo=" + id_prestamo +
                ", usuario=" + usuario +
                ", material=" + material +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", estado='" + estado + '\'' +
                '}';
    }
}




package co.com.training.modelo;

import co.com.training.util.enums.EstadoPrestamo;
import co.com.training.util.enums.TipoPublicacion;

import java.util.Date;

public class Prestamo {
    private int idPrestamo;
    private String correoUsuario;
    private TipoPublicacion tipoPublicacion;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private EstadoPrestamo estadoPrestamo;
    private String tituloPublicacion;
    private String tituloLibro;
    private String tituloNovela;

    public Prestamo(int idPrestamo, String correoUsuario, TipoPublicacion tipoPublicacion, Date fechaPrestamo, Date fechaDevolucion, EstadoPrestamo estadoPrestamo, String tituloPublicacion, String tituloLibro, String tituloNovela) {
        this.idPrestamo = idPrestamo;
        this.correoUsuario = correoUsuario;
        this.tipoPublicacion = tipoPublicacion;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoPrestamo = estadoPrestamo;
        this.tituloPublicacion = tituloPublicacion;
        this.tituloLibro = tituloLibro;
        this.tituloNovela = tituloNovela;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
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

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public String getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setTituloPublicacion(String tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getTituloNovela() {
        return tituloNovela;
    }

    public void setTituloNovela(String tituloNovela) {
        this.tituloNovela = tituloNovela;
    }
}
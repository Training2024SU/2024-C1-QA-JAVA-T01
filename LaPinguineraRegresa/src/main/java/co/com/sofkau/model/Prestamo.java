package co.com.sofkau.model;

import co.com.sofkau.integration.database.mysql.MySqlOperation;

import java.util.ArrayList;
import java.util.Date;

public class Prestamo {
    private int id;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String estado;
    private String Usuario_correo;
    private String Publicacion_titulo;

    private static MySqlOperation mySqlOperation;

    public static ArrayList<Prestamo> prestamos = new ArrayList<>();

    public Prestamo(int id, String fechaPrestamo, String fechaDevolucion, String estado, String usuario_correo, String publicacion_titulo) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.Usuario_correo = usuario_correo;
        this.Publicacion_titulo = publicacion_titulo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario_correo() {
        return Usuario_correo;
    }

    public void setUsuario_correo(String usuario_correo) {
        Usuario_correo = usuario_correo;
    }

    public String getPublicacion_titulo() {
        return Publicacion_titulo;
    }

    public void setPublicacion_titulo(String publicacion_titulo) {
        Publicacion_titulo = publicacion_titulo;
    }

    public static ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public static MySqlOperation getMySqlOperation() {
        return mySqlOperation;
    }

    public static void setMySqlOperation(MySqlOperation mySqlOperation) {
        Prestamo.mySqlOperation = mySqlOperation;
    }
}

package co.com.biblioteca.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Grabacion {
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private int duracion;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponible;

    // Constructor para inicializar todos los atributos

    public Grabacion(String titulo, String autor, String genero, int duracion, int cantidadEjemplares, int cantidadPrestados, int cantidadDisponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.duracion = duracion;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestados = cantidadPrestados;
        this.cantidadDisponible = cantidadDisponible;

    }

    public Grabacion(int id, String titulo, String autor, String genero, int duracion, int cantidadEjemplares, int cantidadPrestados, int cantidadDisponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.duracion = duracion;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestados = cantidadPrestados;
        this.cantidadDisponible = cantidadDisponible;
    }

    public Grabacion() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(int cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public int getCantidadPrestados() {
        return cantidadPrestados;
    }

    public void setCantidadPrestados(int cantidadPrestados) {
        this.cantidadPrestados = cantidadPrestados;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public boolean estaDisponible() {
            return cantidadDisponible > 0;
        }


        // Método para realizar el préstamo de una grabacion
        public void prestamo() {
            if (estaDisponible()) {
                cantidadPrestados++;
                cantidadDisponible--;
                System.out.println("La grabacion '" + titulo + "' ha sido prestado.");
            } else {
                System.out.println("La grabacion '" + titulo + "' no está disponible para préstamo.");
            }
        }

        // Método para devolver una Grabacion prestada
        public void devolver() {
            if (cantidadPrestados > 0) {
                cantidadPrestados--;
                cantidadDisponible++;
                System.out.println("La grabaciom '" + titulo + "' ha sido devuelto.");
            } else {
                System.out.println("No hay ejemplares de '" + titulo + "' prestados actualmente.");
            }
        }

        // Método para imprimir información de la Grabacion

        public static co.com.biblioteca.modelo.Grabacion obtenerGrabacionDesdeResultSet(ResultSet rs) throws SQLException {
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            String autor = rs.getString("autor");
            String genero = rs.getString("genero");
            int duracion = rs.getInt("duracion");
            int cantidadEjemplares = rs.getInt("cantidad_ejemplares");
            int cantidadPrestados = rs.getInt("cantidad_prestados");
            int cantidadDisponible = rs.getInt("cantidad_disponible");

            return new co.com.biblioteca.modelo.Grabacion(id, titulo, autor, genero, duracion,
                    cantidadEjemplares, cantidadPrestados, cantidadDisponible);
        }

        @Override
        public String toString() {
            return "Grabacion{" +
                    "id=" + id +
                    ", titulo='" + titulo + '\'' +
                    ", autor='" + autor + '\'' +
                    ", genero='" + genero + '\'' +
                    ", duracion=" + duracion +
                    ", cantidadEjemplares=" + cantidadEjemplares +
                    ", cantidadPrestados=" + cantidadPrestados +
                    ", cantidadDisponible=" + cantidadDisponible +
                    '}';
        }
    }


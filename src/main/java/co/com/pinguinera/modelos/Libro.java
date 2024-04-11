package co.com.pinguinera.modelos;

public class Libro {
    private int libroID;
    private String titulo;
    private String autor;
    private String areaConocimiento;
    private int numPaginas;
    private int cantEjemplares;

    // Getters y setters

    public int getLibroID() {
        return libroID;
    }

    public void setLibroID(int libroID) {
        this.libroID = libroID;
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

    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(String areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public int getCantEjemplares() {
        return cantEjemplares;
    }

    public void setCantEjemplares(int cantEjemplares) {
        this.cantEjemplares = cantEjemplares;
    }

    // Sobrescribe el método toString() para mostrar la información del libro de forma legible
    @Override
    public String toString() {
        return "Libro{" +
                "ID: " + libroID +
                ", Título: '" + titulo + '\'' +
                ", Autor: '" + autor + '\'' +
                ", Área de conocimiento: '" + areaConocimiento + '\'' +
                ", Número de páginas: " + numPaginas +
                ", Cantidad de ejemplares: " + cantEjemplares +
                '}';
    }
}

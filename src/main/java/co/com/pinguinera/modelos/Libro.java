package co.com.pinguinera.modelos;

public class Libro {
    private int libroID;
    private String titulo;
    private String autor;
    private String areaConocimiento;
    private int numPaginas;
    private int cantEjemplares;
    private int cantEjemplaresPrestados; // Nuevo atributo

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

    public int getCantPrestados() {
        return cantEjemplaresPrestados;
    }

    public void setCantPrestados(int cantEjemplaresPrestados) {
        this.cantEjemplaresPrestados = cantEjemplaresPrestados;
    }

    // Sobrescribe el método toString() para mostrar la información del libro de forma legible
    @Override
    public String toString() {
        // Define los anchos de las columnas (ajusta estos valores según sea necesario)
        int anchoID = 8;
        int anchoTitulo = 30;
        int anchoAutor = 30;
        int anchoAreaConocimiento = 20;
        int anchoNumPaginas = 10;
        int anchoCantEjemplares = 10;
        int anchoCantPrestados = 10;

        // Devuelve una cadena formateada como una tabla con columnas alineadas
        return String.format("%-" + anchoID + "d | %-"
                        + anchoTitulo + "s | %-"
                        + anchoAutor + "s | %-"
                        + anchoAreaConocimiento + "s | %-"
                        + anchoNumPaginas + "d | %-"
                        + anchoCantEjemplares + "d | %-"
                        + anchoCantPrestados + "d",
                libroID,
                titulo,
                autor,
                areaConocimiento,
                numPaginas,
                cantEjemplares,
                cantEjemplaresPrestados);
    }

}

package co.com.pinguinera.modelos;

public class Novela {
    private int novelaID;
    private String titulo;
    private String autor;
    private String genero;
    private int edadLecturaSugerida;
    private int cantEjemplares;
    private int cantPrestados;

    // Getters y setters

    public int getNovelaID() {
        return novelaID;
    }

    public void setNovelaID(int novelaID) {
        this.novelaID = novelaID;
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


    public int getEdadLecturaSugerida() {
        return edadLecturaSugerida;
    }

    public void setEdadLecturaSugerida(int edadLecturaSugerida) {
        this.edadLecturaSugerida = edadLecturaSugerida;
    }

    public int getCantEjemplares() {
        return cantEjemplares;
    }

    public void setCantEjemplares(int cantEjemplares) {
        this.cantEjemplares = cantEjemplares;
    }

    public int getCantPrestados() {
        return cantPrestados;
    }

    public void setCantPrestados(int cantPrestados) {
        this.cantPrestados = cantPrestados;
    }

    // Sobrescribe el método toString() para mostrar la información de la novela de forma legible
    @Override
    public String toString() {
        // Define los anchos de las columnas (ajusta estos valores según sea necesario)
        int anchoID = 8;
        int anchoTitulo = 30;
        int anchoAutor = 30;
        int anchoGenero = 20;
        int anchoEdadLectura = 15;
        int anchoCantEjemplares = 10;
        int anchoCantPrestados = 10;

        // Devuelve una cadena formateada como una tabla con columnas alineadas
        return String.format("%-" + anchoID + "d | %-"
                        + anchoTitulo + "s | %-"
                        + anchoAutor + "s | %-"
                        + anchoGenero + "s | %-"
                        + anchoEdadLectura + "d | %-"
                        + anchoCantEjemplares + "d | %-"
                        + anchoCantPrestados + "d",
                novelaID,
                titulo,
                autor,
                genero,
                edadLecturaSugerida,
                cantEjemplares,
                cantPrestados);
    }

}

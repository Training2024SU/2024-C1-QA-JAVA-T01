package co.com.pinguinera.modelos;

public class Novela {
    private int novelaID;
    private String titulo;
    private String autor;
    private String genero;
    private int edad;
    private int edadLecturaSugerida;
    private int cantEjemplares;
    private int cantPrestados;

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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
}

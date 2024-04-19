package co.com.training.models;

public class Novela extends Publicacion {
    private String genero;
    private int edadLecturaSugerida;

    public Novela(String tituloPublicacion, String autorPublicacion, int cantidadEjemplares, int cantidadPrestados, String genero, int edadLecturaSugerida) {
        super(tituloPublicacion, autorPublicacion, cantidadEjemplares, cantidadPrestados);
        this.genero = genero;
        this.edadLecturaSugerida = edadLecturaSugerida;
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

}

package co.com.training.models;

public class Libro extends Publicacion {
    private String areaConocimiento;
    private int numeroPaginas;

    public Libro(String tituloPublicacion, String autorPublicacion, int cantidadEjemplares, int cantidadPrestados, String areaConocimiento, int numeroPaginas) {
        super(tituloPublicacion, autorPublicacion, cantidadEjemplares, cantidadPrestados);
        this.areaConocimiento = areaConocimiento;
        this.numeroPaginas = numeroPaginas;
    }

    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(String areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

}

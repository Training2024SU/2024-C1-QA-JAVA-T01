package co.com.pinguinera.modelado;

public class AreaGenero {
    private String tituloPublicacion;
    private String areaGenero;

    // Constructor vacío
    public AreaGenero() {
    }

    // Constructor con todos los campos
    public AreaGenero(String tituloPublicacion, String areaGenero) {
        this.tituloPublicacion = tituloPublicacion;
        this.areaGenero = areaGenero;
    }

    // Getters y Setters
    public String getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setTituloPublicacion(String tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
    }

    public String getAreaGenero() {
        return areaGenero;
    }

    public void setAreaGenero(String areaGenero) {
        this.areaGenero = areaGenero;
    }

    @Override
    public String toString() {
        return "AreaGenero{" +
                "tituloPublicacion='" + tituloPublicacion + '\'' +
                ", areaGenero='" + areaGenero + '\'' +
                '}';
    }
}

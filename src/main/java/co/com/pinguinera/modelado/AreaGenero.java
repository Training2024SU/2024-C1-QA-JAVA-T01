package co.com.pinguinera.modelado;

public class AreaGenero {
    private int idPublicacion;  // Clave externa que se refiere a Publicacion
    private String areaGenero;  // Clave primaria compuesta con idPublicacion

    // Constructor vacío
    public AreaGenero() {}

    // Constructor con todos los campos
    public AreaGenero(int idPublicacion, String areaGenero) {
        this.idPublicacion = idPublicacion;
        this.areaGenero = areaGenero;
    }

    // Getters y Setters
    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
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
                "idPublicacion=" + idPublicacion +
                ", areaGenero='" + areaGenero + '\'' +
                '}';
    }
}

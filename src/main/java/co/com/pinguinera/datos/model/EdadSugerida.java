package co.com.pinguinera.datos.model;

public class EdadSugerida {
    private int idPublicacion;
    private String edad;

    // Constructor vacío
    public EdadSugerida() {}

    public EdadSugerida(int idPublicacion, String edad) {
        this.idPublicacion = idPublicacion;
        this.edad = edad;
    }

    // Getters y Setters
    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "EdadSugerida{" +
                "idPublicacion=" + idPublicacion +
                ", edad='" + edad + '\'' +
                '}';
    }
}

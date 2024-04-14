package co.com.pinguinera.modelado;

public class EdadSugerida {
    private int idPublicacion;  // Clave externa que se refiere a Publicacion
    private String edad;  // Clave primaria compuesta con idPublicacion

    // Constructor vacío
    public EdadSugerida() {}

    // Constructor con todos los campos
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

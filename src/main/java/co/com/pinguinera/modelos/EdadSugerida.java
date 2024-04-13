package co.com.pinguinera.modelos;

public class EdadSugerida {
    private String tituloPublicacion;
    private String edad;

    public EdadSugerida() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public EdadSugerida(String tituloPublicacion, String edad) {
        this.tituloPublicacion = tituloPublicacion;
        this.edad = edad;
    }

    // Getters y Setters
    public String getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setTituloPublicacion(String tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
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
                "tituloPublicacion='" + tituloPublicacion + '\'' +
                ", edad='" + edad + '\'' +
                '}';
    }
}

package co.com.training.modelo;

public class Novela extends Material {
    private String genero;
    private int edad_lectura_sugerida;

    public Novela(String id_material, String titulo, Autor autor, int cantidad_ejemplares, int cantidad_prestados, String genero, int edad_lectura_sugerida) {
        super(id_material, titulo, autor, cantidad_ejemplares, cantidad_prestados);
        this.genero = genero;
        this.edad_lectura_sugerida = edad_lectura_sugerida;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad_lectura_sugerida() {
        return edad_lectura_sugerida;
    }

    public void setEdad_lectura_sugerida(int edad_lectura_sugerida) {
        this.edad_lectura_sugerida = edad_lectura_sugerida;
    }

}

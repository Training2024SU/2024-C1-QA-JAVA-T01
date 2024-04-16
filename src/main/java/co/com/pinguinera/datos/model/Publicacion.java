package co.com.pinguinera.datos.model;
import co.com.pinguinera.datos.model.enums.TipoPublicacion;


public class Publicacion {
    private int idPublicacion; // Nuevo atributo `idPublicacion`
    private String titulo;
    private TipoPublicacion tipoPublicacion; // Usa el enum TipoPublicacion
    private String autor;
    private int numPaginas;
    private int cantEjemplares;
    private int cantPrestados;
    private int cantDisponible;

    public Publicacion() {
        // Constructor vacío
    }

    // Constructor con todos los campos, incluyendo `idPublicacion`
    public Publicacion(int idPublicacion, String titulo, TipoPublicacion tipoPublicacion, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantDisponible) {
        this.idPublicacion = idPublicacion;
        this.titulo = titulo;
        this.tipoPublicacion = tipoPublicacion;
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.cantEjemplares = cantEjemplares;
        this.cantPrestados = cantPrestados;
        this.cantDisponible = cantDisponible;

    }

    // Getters y Setters
    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
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

    public int getCantDisponible() {
        return cantDisponible;
    }

    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;
    }


    @Override
    public String toString() {
        final String RESET = "\033[0m"; // Restablecer color
        final String GREEN = "\033[32m"; // Verde

        String columnas = String.format(
                "%-10s %-40s %-25s %-10s %-12s %-12s %-12s",
                "ID", "Título", "Autor", "Páginas", "Ejemplares", "Prestados", "Disponible"
        );
        String datos = String.format(
                "%s%-10d%s %s%-40s%s %s%-25s%s %s%-10d%s %s%-12d%s %s%-12d%s %s%-12d%s",
                GREEN, idPublicacion, RESET,
                GREEN, titulo, RESET,
                GREEN, autor, RESET,
                GREEN, numPaginas, RESET,
                GREEN, cantEjemplares, RESET,
                GREEN, cantPrestados, RESET,
                GREEN, cantDisponible, RESET
        );

        return "\n" + columnas + "\n" + datos;
    }

}

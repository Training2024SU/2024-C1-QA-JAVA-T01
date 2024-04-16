package Garcia.Juan.model;

public class Producto {
    private String titulo;
    private String tipo;
    private String autor;
    private String numeroPaginas;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponibles;
    private String genero;
    private int edadMinima;

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Producto(String titulo, String tipo, String autor, String numeroPaginas, int cantidadPrestados, int cantidadEjemplares, int cantidadDisponibles, String genero, int edadMinima) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
        this.cantidadPrestados = cantidadPrestados;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadDisponibles = cantidadDisponibles;
        this.genero = genero;
        this.edadMinima = edadMinima;
    }

    public Producto() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(String numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(int cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public int getCantidadPrestados() {
        return cantidadPrestados;
    }

    public void setCantidadPrestados(int cantidadPrestados) {
        this.cantidadPrestados = cantidadPrestados;
    }

    public int getCantidadDisponibles() {
        return cantidadDisponibles;
    }

    public void setCantidadDisponibles(int cantidadDisponibles) {
        this.cantidadDisponibles = cantidadDisponibles;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                ", numeroPaginas='" + numeroPaginas + '\'' +
                ", cantidadEjemplares=" + cantidadEjemplares +
                ", cantidadPrestados=" + cantidadPrestados +
                ", cantidadDisponibles=" + cantidadDisponibles +
                '}';
    }

    public String toStringLibros() {
        return "Producto{ "+
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                ", numeroPaginas='" + numeroPaginas + '\'' +
                ", cantidadDisponibles=" + cantidadDisponibles +
                ", Area=" + genero+
                '}';
    }

    public String toStringNovelas() {
        return "Producto{ "+
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                ", numeroPaginas='" + numeroPaginas + '\'' +
                ", cantidadDisponibles=" + cantidadDisponibles +
                ", genero=" + genero+
                ", Edad minima =" + edadMinima+
                '}';
    }

    public String toStringAutor() {
        return "Producto{ "+
                "Autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", numeroPaginas='" + numeroPaginas + '\'' +
                ", cantidadDisponibles=" + cantidadDisponibles +
                '}';
    }
    public String toStringPrestamo() {
        return "Producto{ "+
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}

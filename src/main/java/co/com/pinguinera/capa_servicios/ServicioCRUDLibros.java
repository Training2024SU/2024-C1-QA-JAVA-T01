package co.com.pinguinera.capa_servicios;
import co.com.pinguinera.modelado.publicaciones.Libro;
import java.util.ArrayList;
import java.util.List;

public class ServicioCRUDLibros {

    private List<Libro> libros; // Colección local para almacenar los datos

    public ServicioCRUDLibros() {
        // Inicializa la colección local
        this.libros = new ArrayList<>();
    }

    // Método para agregar un nuevo libro a la colección local
    public void agregar(Libro libro) {
        libros.add(libro);
    }

    // Método para obtener todos los libros desde la colección local
    public List<Libro> obtenerTodos() {
        return libros;
    }

    // Método para actualizar un libro existente en la colección local
    public void actualizar(Libro libro) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equals(libro.getTitulo())) {
                libros.set(i, libro);
                break;
            }
        }
    }

    // Método para eliminar un libro de la colección local
    public void eliminar(String tituloLibro) {
        libros.removeIf(libro -> libro.getTitulo().equals(tituloLibro));
    }
}

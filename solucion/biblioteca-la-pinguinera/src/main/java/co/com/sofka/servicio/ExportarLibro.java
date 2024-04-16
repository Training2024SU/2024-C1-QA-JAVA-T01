package co.com.sofka.servicio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import co.com.sofka.modelo.Libro;
import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

@Getter
@Setter
@ToString
@Entity

public class ExportarLibro {

    private SessionFactory sessionFactory;

    public ExportarLibro(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void exportarArchivo(String filePath) {

        String nombreArchivo = "\\libros-disponibles.csv";
        String filePathConArchivo = filePath + nombreArchivo;

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Libro> criteria = builder.createQuery(Libro.class);
            Root<Libro> root = criteria.from(Libro.class);
            criteria.select(root);

            List<Libro> libros = session.createQuery(criteria).getResultList();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathConArchivo))) {
                // Encabezados
                writer.write("Titulo,Autor,Numero de Paginas,Area del conocimiento,Cantidad de Ejemplares,Cantidad de prestados,Cantidad Disponible");
                writer.newLine();

                // Datos de libros
                for (Libro libro : libros) {
                    writer.write(
                            libro.getTitulo() + "," +
                                    libro.getAutor() + "," +
                                    libro.getNumeroDePaginas() + "," +
                                    libro.getAreaDelConocimiento() + "," +
                                    libro.getCantidadEjemplares() + "," +
                                    libro.getCantidadPrestados() + "," +
                                    libro.getCantidadDisponible()
                    );
                    writer.newLine();
                }

                System.out.println("El archivo CSV se ha creado satisfactoriamente en: " + filePathConArchivo);
            } catch (IOException e) {
                System.err.println("Error al crear el archivo CSV: " + e.getMessage());
            }
        }
    }
}

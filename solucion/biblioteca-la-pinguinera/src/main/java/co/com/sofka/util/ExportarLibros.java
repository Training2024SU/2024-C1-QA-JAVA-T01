package co.com.sofka.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import co.com.sofka.modelo.Libro;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@ToString
@Entity

public class ExportarLibros {

    private SessionFactory sessionFactory;

    public ExportarLibros(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void exportToExcel(String filePath) {

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
                writer.write("Título,Autor,Numero de Paginas,Area del conocimiento,Cantidad de Ejemplares,Cantidad de prestados,Cantidad Disponible");
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

    public void leerArchivo(String filepath){


        try {
            // Crear un objeto Scanner para leer el archivo CSV
            Scanner scanner = new Scanner(new File(filepath));

            // Iterar sobre cada línea del archivo
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Dividir la línea en columnas utilizando coma como delimitador
                String[] columns = line.split(",");

                // Procesar las columnas como desees
                for (String column : columns) {
                    System.out.print(column + " ");
                }
                System.out.println(); // Nueva línea para la siguiente fila
            }

            // Cerrar el scanner cuando termines
            scanner.close();

        }
        catch(FileNotFoundException e) {
            // Manejar la excepción si el archivo no se encuentra
            e.printStackTrace();
        }
    }
}

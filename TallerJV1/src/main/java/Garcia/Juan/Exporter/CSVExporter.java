package Garcia.Juan.Exporter;

import Garcia.Juan.model.Usuario;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {
    public static void exportToCSV(List<Usuario> usuarios) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("C:\\\\Users\\\\SEBASTIAN\\\\Desktop\\\\Training\\\\Java\\\\2024-C1-QA-JAVA-T01\\\\TallerJV1\\\\src\\\\main\\\\resources\\\\usuarios.csv"))) {
            // Escribir encabezados
            String[] headers = {"Correo", "Nombre", "Contraseña", "Rol"};
            writer.writeNext(headers);

            // Escribir datos de usuarios
            for (Usuario usuario : usuarios) {
                String[] data = {usuario.getCorreo(), usuario.getNombre(), usuario.getContrasena(), usuario.getRol()};
                writer.writeNext(data);
            }

            System.out.println("CSV exportado exitosamente a: " + "C:\\\\Users\\\\SEBASTIAN\\\\Desktop\\\\Training\\\\Java\\\\2024-C1-QA-JAVA-T01\\\\TallerJV1\\\\src\\\\main\\\\resources\\\\usuarios.csv");
        } catch (IOException e) {
            System.err.println("Error al exportar a CSV: " + e.getMessage());
        }
    }
}
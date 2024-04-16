package co.com.sofka.servicio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.File;
import java.io.FileNotFoundException;
import javax.persistence.Entity;
import java.util.Scanner;


@Getter
@Setter
@ToString
@Entity
public class LectorArchivoLibro {
        public void LeerArchivo(String filepath){

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

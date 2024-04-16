// MetodosMain.java
package co.com.training.logica.control;

import co.com.training.operaciones.PublicacionesCRUD.InsertarPublicacion;
import co.com.training.models.Publicacion;

import java.sql.SQLException;

public class MetodosMain {

    public static void insertarItems() throws SQLException {
        // Crear objetos de tipo Publicacion
        Publicacion publicacion1 = new Publicacion("Título 1", "Autor 1", 10, 1);
        Publicacion publicacion2 = new Publicacion("Título 2", "Autor 2", 15, 2);
        Publicacion publicacion3 = new Publicacion("Título 3", "Autor 3", 20, 3);

        // Insertar los objetos en la base de datos
        InsertarPublicacion.insertarPublicacion(publicacion1);
        InsertarPublicacion.insertarPublicacion(publicacion2);
        InsertarPublicacion.insertarPublicacion(publicacion3);

        System.out.println("Se han insertado los items correctamente.");
    }
}


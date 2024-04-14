package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.capa_datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.capa_datos.LibroDAO;
import co.com.pinguinera.capa_servicios.CRUBBD.ConsultasLibros;
import co.com.pinguinera.capa_servicios.CRUD.ServicioLocalCRUDLibros;
import co.com.pinguinera.capa_servicios.sincronizacionBD.SincronizadorLibros;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Crea una instancia de `BaseDatosImpl` como `GestorBD`
            GestorBD gestorBD = new BaseDatosImpl(conexion);

            // Crea una instancia de `ConsultasLibros` utilizando `GestorBD`
            ConsultasLibros consultasLibros = new ConsultasLibros(new LibroDAO(gestorBD));

            // Crea una instancia de `ServicioLocalCRUDLibros`
            ServicioLocalCRUDLibros servicioLocalCRUDLibros = new ServicioLocalCRUDLibros();

            // Simula operaciones de CRUD en la lista local
            servicioLocalCRUDLibros.agregar(new Libro(1, "Norwegian Wood", "Haruki Murakami", 296, 15, 4, 11, null, null));
            servicioLocalCRUDLibros.agregar(new Libro(2, "The Tale of Genji", "Murasaki Shikibu", 1184, 20, 6, 14, null, null));
            servicioLocalCRUDLibros.agregar(new Libro(3, "Battle Royale", "Koushun Takami", 576, 12, 3, 9, null, null));
            servicioLocalCRUDLibros.agregar(new Libro(4, "Kafka on the Shore", "Haruki Murakami", 624, 18, 5, 13, null, null));
            servicioLocalCRUDLibros.agregar(new Libro(5, "Snow Country", "Yasunari Kawabata", 180, 10, 2, 8, null, null));
            // Crea una instancia de `SincronizadorLibros`
            SincronizadorLibros sincronizadorLibros = new SincronizadorLibros(consultasLibros, servicioLocalCRUDLibros);

            // Ejecuta la sincronización
            sincronizadorLibros.sincronizarLibros();

            System.out.println("Sincronización completada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

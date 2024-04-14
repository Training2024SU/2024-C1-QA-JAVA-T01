package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.capa_datos.ImplBD.BaseDatosImpl;
import co.com.pinguinera.capa_datos.NovelaDAO;
import co.com.pinguinera.capa_servicios.crud_base_datos.NovelaPersistencia;
import co.com.pinguinera.capa_servicios.crud_local.CRUDNovelasLocales;
import co.com.pinguinera.capa_servicios.integracion.SincronizadorNovelas;
import co.com.pinguinera.modelado.publicaciones.Novela;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Crea una instancia de `BaseDatosImpl` como `GestorBD`
            GestorBD gestorBD = new BaseDatosImpl(conexion);

            // Crea una instancia de `NovelaPersistencia` utilizando `GestorBD`
            NovelaPersistencia novelaPersistencia = new NovelaPersistencia(new NovelaDAO(gestorBD));

            // Crea una instancia de `CRUDNovelasLocales`
            CRUDNovelasLocales CRUDNovelasLocales = new CRUDNovelasLocales();

            // Simula operaciones de CRUD en la lista local con novelas de Venezuela
            CRUDNovelasLocales.agregar(new Novela(11, "Doña Bárbara", "Rómulo Gallegos", 400, 10, 3, 8, null, null));
            CRUDNovelasLocales.agregar(new Novela(12, "La trepadora", "Rómulo Gallegos", 330, 15, 4, 11, null, null));
            CRUDNovelasLocales.agregar(new Novela(13, "Canaima", "Rómulo Gallegos", 405, 18, 5, 12, null, null));
            CRUDNovelasLocales.agregar(new Novela(14, "La casa de las cuatro paredes", "Guillermo Meneses", 340, 12, 4, 9, null, null));
            CRUDNovelasLocales.agregar(new Novela(15, "La doncella y el último patriota", "Leoncio Martínez", 280, 14, 5, 10, null, null));

            // Crea una instancia de `SincronizadorNovelas`
            SincronizadorNovelas sincronizadorNovelas = new SincronizadorNovelas(novelaPersistencia, CRUDNovelasLocales);

            // Ejecuta la sincronización
            sincronizadorNovelas.sincronizarNovelas();

            System.out.println("Sincronización completada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

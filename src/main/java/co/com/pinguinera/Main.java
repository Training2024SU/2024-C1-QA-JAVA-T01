package co.com.pinguinera;

import co.com.pinguinera.modelos.DAO.NovelaDAO;
import co.com.pinguinera.modelos.Novela;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Intenta establecer la conexión a la base de datos
            Connection conexion = DataBase.conectar();

            // Si la conexión se establece con éxito, muestra un mensaje
            System.out.println("Conexión establecida correctamente.");

            // Instanciamos NovelaDAO con la conexión establecida
            NovelaDAO novelaDAO = new NovelaDAO(conexion);

            // Agregar una nueva novela
            Novela nuevaNovela = new Novela();
            nuevaNovela.setTitulo("El nombre del viento");
            nuevaNovela.setAutor("Patrick Rothfuss");
            nuevaNovela.setGenero("Fantasía");
            nuevaNovela.setEdadLecturaSugerida(16);
            nuevaNovela.setCantEjemplares(10);
            novelaDAO.agregarNovela(nuevaNovela);
            System.out.println("Nueva novela agregada correctamente.");

            // Obtener todas las novelas
            List<Novela> novelas = novelaDAO.obtenerTodasLasNovelas();
            System.out.println("Lista de todas las novelas:");
            for (Novela novela : novelas) {
                System.out.println(novela.getTitulo() + " - " + novela.getAutor());
            }

            // Buscar novelas por título
            String tituloBusqueda = "viento";
            List<Novela> novelasEncontradas = novelaDAO.buscarNovelaPorTitulo(tituloBusqueda);
            System.out.println("Novelas encontradas con '" + tituloBusqueda + "':");
            for (Novela novela : novelasEncontradas) {
                System.out.println(novela.getTitulo() + " - " + novela.getAutor());
            }

            // Listar autores de novelas
            List<String> autores = novelaDAO.listarAutoresDeNovelas();
            System.out.println("Autores de novelas:");
            for (String autor : autores) {
                System.out.println(autor);
            }

            // Cierra la conexión
            conexion.close();
        } catch (SQLException e) {
            // Si ocurre algún error al intentar establecer la conexión, muestra el mensaje de error
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            // También puedes imprimir la traza completa del error llamando a e.printStackTrace()
            // e.printStackTrace();
        }
    }
}

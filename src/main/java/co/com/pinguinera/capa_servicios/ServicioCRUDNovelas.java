package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.capa_datos.NovelaDAO; // Importa NovelaDAO
import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import co.com.pinguinera.modelado.publicaciones.Novela;
import java.sql.SQLException;
import java.util.List;

public class ServicioCRUDNovelas {

    private GestorBD gestorBD;
    private NovelaDAO novelaDAO; // Declara la variable NovelaDAO

    // Constructor que recibe una instancia de GestorBD
    public ServicioCRUDNovelas(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
        // Inicializa NovelaDAO
        this.novelaDAO = new NovelaDAO();
    }

    // Método para agregar una nueva novela a la base de datos
    public void agregar(Novela novela) throws SQLException {
        try (var statement = gestorBD.prepararConsulta("INSERT INTO Publicacion (titulo, autor, num_paginas, cant_ejemplares, cant_prestados, tipo_publicacion) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, novela.getTitulo());
            statement.setString(2, novela.getAutor());
            statement.setInt(3, novela.getNumPaginas());
            statement.setInt(4, novela.getCantEjemplares());
            statement.setInt(5, novela.getCantPrestados());
            statement.setString(6, novela.getTipoPublicacion().name());

            // Ejecuta la consulta
            statement.executeUpdate();
        }
    }

    // Método para obtener todas las novelas desde la base de datos
    public List<Novela> obtenerTodas() throws SQLException {
        // Reutiliza el método de NovelaDAO
        return novelaDAO.obtenerTodasLasNovelas();
    }

    // Método para actualizar una novela existente en la base de datos
    public void actualizar(Novela novela) throws SQLException {
        try (var statement = gestorBD.prepararConsulta("UPDATE Publicacion SET titulo = ?, autor = ?, num_paginas = ?, cant_ejemplares = ?, cant_prestados = ? WHERE titulo = ? AND tipo_publicacion = 'NOVELA'")) {
            statement.setString(1, novela.getTitulo());
            statement.setString(2, novela.getAutor());
            statement.setInt(3, novela.getNumPaginas());
            statement.setInt(4, novela.getCantEjemplares());
            statement.setInt(5, novela.getCantPrestados());
            statement.setString(6, novela.getTitulo());
            statement.executeUpdate();
        }
    }

    // Método para eliminar una novela de la base de datos
    public void eliminar(String tituloNovela) throws SQLException {
        try (var statement = gestorBD.prepararConsulta("DELETE FROM Publicacion WHERE titulo = ? AND tipo_publicacion = 'NOVELA'")) {
            statement.setString(1, tituloNovela);
            statement.executeUpdate();
        }
    }
}

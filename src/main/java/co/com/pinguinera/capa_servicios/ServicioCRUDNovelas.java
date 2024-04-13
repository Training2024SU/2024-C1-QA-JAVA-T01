package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import co.com.pinguinera.modelado.publicaciones.Novela;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCRUDNovelas {

    private GestorBD gestorBD;

    // Constructor que recibe una instancia de GestorBD
    public ServicioCRUDNovelas(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
    }

    // Consultas SQL específicas para novelas
    private static final String CONSULTA_NOVELAS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'NOVELA'";
    private static final String INSERTAR_NOVELA = "INSERT INTO Publicacion (titulo, autor, num_paginas, cant_ejemplares, cant_prestados, tipo_publicacion) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_NOVELA = "UPDATE Publicacion SET titulo = ?, autor = ?, num_paginas = ?, cant_ejemplares = ?, cant_prestados = ? WHERE titulo = ? AND tipo_publicacion = 'NOVELA'";
    private static final String ELIMINAR_NOVELA = "DELETE FROM Publicacion WHERE titulo = ? AND tipo_publicacion = 'NOVELA'";

    // Método para agregar una nueva novela a la base de datos
    public void agregar(Novela novela) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(INSERTAR_NOVELA)) {
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
        List<Novela> novelas = new ArrayList<>();

        try (PreparedStatement statement = gestorBD.prepararConsulta(CONSULTA_NOVELAS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Crear una instancia de Novela
                Novela novela = new Novela(
                        resultSet.getString("titulo"),
                        resultSet.getString("autor"),
                        resultSet.getInt("num_paginas"),
                        resultSet.getInt("cant_ejemplares"),
                        resultSet.getInt("cant_prestados"),
                        resultSet.getInt("cant_disponible"),
                        null, // Áreas, puedes ajustar según tu esquema
                        null  // Edades, puedes ajustar según tu esquema
                );

                // Añadir la novela a la lista
                novelas.add(novela);
            }
        }

        return novelas;
    }

    // Método para actualizar una novela existente en la base de datos
    public void actualizar(Novela novela) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(ACTUALIZAR_NOVELA)) {
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
        try (PreparedStatement statement = gestorBD.prepararConsulta(ELIMINAR_NOVELA)) {
            statement.setString(1, tituloNovela);
            statement.executeUpdate();
        }
    }
}

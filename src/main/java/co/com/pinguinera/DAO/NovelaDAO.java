package co.com.pinguinera.DAO;

import co.com.pinguinera.modelos.Novela;
import co.com.pinguinera.interfaces.NovelaRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovelaDAO implements NovelaRepositorio {

    private Connection conexion;

    // Constructor que recibe la conexión a la base de datos
    public NovelaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void agregarNovela(Novela novela) {
        String sql = "INSERT INTO Novelas (Titulo, Autor, Genero, EdadLecturaSugerida, CantEjemplares) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, novela.getTitulo());
            statement.setString(2, novela.getAutor());
            statement.setString(3, novela.getGenero());
            statement.setInt(4, novela.getEdadLecturaSugerida());
            statement.setInt(5, novela.getCantEjemplares());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarNovela(Novela novela) {
        String sql = "UPDATE Novelas SET Titulo = ?, Autor = ?, Genero = ?, EdadLecturaSugerida = ?, CantEjemplares = ? WHERE NovelaID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, novela.getTitulo());
            statement.setString(2, novela.getAutor());
            statement.setString(3, novela.getGenero());
            statement.setInt(4, novela.getEdadLecturaSugerida());
            statement.setInt(5, novela.getCantEjemplares());
            statement.setInt(6, novela.getNovelaID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarNovela(int novelaId) {
        String sql = "DELETE FROM Novelas WHERE NovelaID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, novelaId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Novela> obtenerTodasLasNovelas() {
        List<Novela> novelas = new ArrayList<>();
        // Modifica la consulta para incluir la condición que verifica la disponibilidad
        String sql = "SELECT * FROM Novelas WHERE CantEjemplares - CantPrestados > 0";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Novela novela = new Novela();
                novela.setNovelaID(resultSet.getInt("NovelaID"));
                novela.setTitulo(resultSet.getString("Titulo"));
                novela.setAutor(resultSet.getString("Autor"));
                novela.setGenero(resultSet.getString("Genero"));
                novela.setEdadLecturaSugerida(resultSet.getInt("EdadLecturaSugerida"));
                novela.setCantEjemplares(resultSet.getInt("CantEjemplares"));
                novela.setCantPrestados(resultSet.getInt("CantPrestados"));
                novelas.add(novela);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return novelas;
    }

    @Override
    public List<Novela> buscarNovelaPorTitulo(String titulo) {
        List<Novela> novelas = new ArrayList<>();
        // Modifica la consulta para incluir la condición de disponibilidad
        String sql = "SELECT * FROM Novelas WHERE Titulo LIKE ? AND CantEjemplares - CantPrestados > 0";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, "%" + titulo + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Novela novela = new Novela();
                    novela.setNovelaID(resultSet.getInt("NovelaID"));
                    novela.setTitulo(resultSet.getString("Titulo"));
                    novela.setAutor(resultSet.getString("Autor"));
                    novela.setGenero(resultSet.getString("Genero"));
                    novela.setEdadLecturaSugerida(resultSet.getInt("EdadLecturaSugerida"));
                    novela.setCantEjemplares(resultSet.getInt("CantEjemplares"));
                    novela.setCantPrestados(resultSet.getInt("CantPrestados"));
                    novelas.add(novela);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return novelas;
    }

    @Override
    public List<String> listarAutoresDeNovelas() {
        List<String> autores = new ArrayList<>();
        String sql = "SELECT DISTINCT Autor FROM Novelas";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                autores.add(resultSet.getString("Autor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }
}

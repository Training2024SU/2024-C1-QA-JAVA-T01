package org.moreno.cristian.servicios;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.repositorios.RepositorioAutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioAutor implements RepositorioAutor {

    private final Connection conn;

    public ServicioAutor(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Autor> autorPorNombre(String nombreAutor) {

        String sql = "SELECT * FROM autor WHERE nombre = ?";
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreAutor);
            ResultSet rs = pstmt.executeQuery();

            Autor autor = new Autor();

            while (rs.next()) {
                // Process each row as needed
                autor.setId(rs.getString("id"));
                autor.setNombre(rs.getString("nombre"));
            }

            if (autor.getId() == null) {
                return Optional.empty();
            } else {
                return Optional.of(autor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean guardarAutor(Autor autor) {
        String sql = "INSERT INTO autor (id, nombre) VALUES (?, ?)";
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, autor.getId());
            pstmt.setString(2, autor.getNombre());


            int filasAfectadas = pstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

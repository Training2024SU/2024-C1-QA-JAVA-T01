package org.moreno.cristian.servicios;

import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.modelos.enums.Rol;
import org.moreno.cristian.repositorios.RepositorioUsuario;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioUsuario implements RepositorioUsuario {

    Connection conn;

    public ServicioUsuario () {
        try {
            conn = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Usuario> validarCredenciales(String correo, String contrasenia) {

        String sqlConsulta = "SELECT * FROM usuario WHERE correo = ? AND contrasenia = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);

            pstmt.setString(1, new String(correo.getBytes(), "UTF-8"));
            pstmt.setString(2, new String(contrasenia.getBytes(), "UTF-8"));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Bienvenido " + rs.getString("nombre"));

                return Optional.of(new Usuario(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasenia"),
                        Rol.valueOf(rs.getString("rol"))
                ));
            }
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Usuario>> listarUsuarios() {

        String sqlConsulta = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasenia"),
                        Rol.valueOf(rs.getString("rol"))
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!usuarios.isEmpty()) {
            return Optional.of(usuarios);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> guardarUsuario(Usuario nuevoUser) {

        String sqlConsulta = "INSERT INTO usuario (id, nombre, correo, contrasenia, rol) " +
                "VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);

            pstmt.setString(1, nuevoUser.getId());
            pstmt.setString(2, nuevoUser.getNombre());
            pstmt.setString(3, nuevoUser.getCorreo());
            pstmt.setString(4, nuevoUser.getContrasenia());
            pstmt.setString(5, nuevoUser.getRol().toString());

            rowsAffected = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rowsAffected > 0) {
            return Optional.of(nuevoUser);
        }
        return Optional.empty();
    }
}

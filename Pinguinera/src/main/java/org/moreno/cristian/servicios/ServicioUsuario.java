package org.moreno.cristian.servicios;

import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.modelos.enums.Rol;
import org.moreno.cristian.repositorios.RepositorioUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ServicioUsuario implements RepositorioUsuario {
    @Override
    public Optional<Usuario> validarCredenciales(String correo, String contrasenia) {

        String sqlConsulta = "SELECT * FROM usuario WHERE correo = ? AND contrasenia = ?";
        try {

            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);

            pstmt.setString(1, correo);
            pstmt.setString(2, contrasenia);


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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<ArrayList<Usuario>> listarUsuarios() {
        return Optional.empty();
    }
}

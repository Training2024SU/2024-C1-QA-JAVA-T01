package co.com.pinguinera.capa_datos.ImplBD;

import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDatosImpl implements GestorBD {

    private Connection conexion;

    // Constructor para inyectar la conexión
    public BaseDatosImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public PreparedStatement prepararConsulta(String consulta) throws SQLException {
        // Prepara una consulta SQL y devuelve un objeto PreparedStatement
        return conexion.prepareStatement(consulta);
    }

    @Override
    public void cerrarConexion() throws SQLException {
        // Cierra la conexión a la base de datos
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

}

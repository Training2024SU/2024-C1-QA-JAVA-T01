package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.DAO;
import co.com.pinguinera.capa_datos.interfaces.GestorBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected GestorBD gestorBD;

    public AbstractDAO(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
    }

    @Override
    public List<T> obtenerTodos() throws SQLException {
        String consulta = obtenerConsultaTodos(); // Consulta específica para la tabla

        try (PreparedStatement statement = prepararConsulta(consulta)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> resultados = new ArrayList<>();

            // Llamada a un método abstracto para convertir cada fila a un objeto T
            while (resultSet.next()) {
                T objeto = convertirFilaAObjeto(resultSet);
                resultados.add(objeto);
            }

            return resultados;
        }
    }

    // Método abstracto para obtener la consulta específica de la tabla
    protected abstract String obtenerConsultaTodos();

    // Método abstracto para convertir una fila de ResultSet a un objeto T
    protected abstract T convertirFilaAObjeto(ResultSet resultSet) throws SQLException;

    // Método auxiliar para preparar la consulta
    protected PreparedStatement prepararConsulta(String consulta) throws SQLException {
        return gestorBD.prepararConsulta(consulta);
    }

    // Método auxiliar para cerrar la conexión
    protected void cerrarConexion() throws SQLException {
        gestorBD.cerrarConexion();
    }
}

package Garcia.Juan.CRUD;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudProducto {
    private static MySqlOperation mySqlOperation;

    public CrudProducto(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }
    private static final String GET_PRODUCTS = "SELECT p.*, COALESCE(em.edad,0) as edad\n" +
            "FROM bibliotecapingu.producto p\n" +
            "LEFT JOIN bibliotecapingu.edad_min em ON p.titulo = em.titulo\n";
    private static final String SUMAR_PRESTADOS = "UPDATE bibliotecaPingu.producto" +
            "    SET cant_prestados = cant_prestados + 1" +
            "    WHERE titulo = '%s'";
    private static final String RESTAR_PRESTADOS = "UPDATE bibliotecaPingu.producto" +
            "    SET cant_prestados = cant_prestados - 1" +
            "    WHERE titulo = '%s'";

    public static List<Producto> getProductos(MySqlOperation mySqlOperation){
        List<Producto> productos = new ArrayList<>();
        try {
            CrudProducto.mySqlOperation.setSqlStatement(String.format(GET_PRODUCTS));
            CrudProducto.mySqlOperation.executeSqlStatement();
            ResultSet resultSet = CrudProducto.mySqlOperation.getResulset();
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setTitulo(resultSet.getString(1));
                producto.setTipo(resultSet.getString(2));
                producto.setAutor(resultSet.getString(3));
                producto.setNumeroPaginas(resultSet.getString(4));
                producto.setCantidadEjemplares(resultSet.getInt(5));
                producto.setCantidadPrestados(resultSet.getInt(6));
                producto.setCantidadDisponibles(resultSet.getInt(7));
                producto.setGenero(resultSet.getString(8));
                producto.setEdadMinima(resultSet.getInt(9));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }
        return productos;
    }

    public static Producto getProducto(MySqlOperation mySqlOperation, String titulo, List<Producto> productos){
        Producto producto = new Producto();
        List<Producto> productosAqui = productos;
        for (Producto productoFor : productosAqui){
            if (productoFor.getTitulo().equals(titulo)){
                producto.setTitulo(productoFor.getTitulo());
                producto.setTipo(productoFor.getTipo());
                producto.setAutor(productoFor.getAutor());
                producto.setNumeroPaginas(productoFor.getNumeroPaginas());
                producto.setCantidadEjemplares(productoFor.getCantidadEjemplares());
                producto.setCantidadPrestados(productoFor.getCantidadPrestados());
                producto.setCantidadDisponibles(productoFor.getCantidadDisponibles());
                producto.setEdadMinima(productoFor.getEdadMinima());
                producto.setGenero(productoFor.getGenero());
            }
        }
        return producto;
    }

    public static void sumaPrestados(MySqlOperation mySqlOperation, List<Producto> productosSolicitados){
        for (Producto producto:productosSolicitados){
            String updateStatement = String.format(SUMAR_PRESTADOS,producto.getTitulo());
            mySqlOperation.setSqlStatement(updateStatement);
            mySqlOperation.executeSqlStatementVoid();
        }
    }
    public static void restaPrestados(MySqlOperation mySqlOperation, List<Producto> productosSolicitados){
        for (Producto producto:productosSolicitados){
            String updateStatement = String.format(RESTAR_PRESTADOS,producto.getTitulo());
            mySqlOperation.setSqlStatement(updateStatement);
            mySqlOperation.executeSqlStatementVoid();
        }
    }


}

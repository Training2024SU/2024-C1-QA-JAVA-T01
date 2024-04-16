package Garcia.Juan.CRUD;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Prestamo;
import Garcia.Juan.model.Producto;
import com.github.javafaker.Faker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Garcia.Juan.util.EstadoPrestamo.ESTADO_UNO;

public class CrudPrestamo {

    private static final String INSERT_PRESTAMO ="INSERT INTO bibliotecapingu.prestamo (id, estado ,fecha_salida, fecha_devolucion, correo_usuario) VALUES ('%s','%s','%s','%s','%s')";
    private static final String INSERT_CONTENIDO ="INSERT INTO bibliotecapingu.contenido (id_prestamo, titulo_libro) VALUES ('%s','%s')";
    private static final String GET_PRESTAMO = "SELECT * FROM bibliotecapingu.prestamo";

    private static MySqlOperation mySqlOperation;

    public CrudPrestamo(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;

    }

    public static void insertarPrestamo(MySqlOperation mySqlOperation, List<Producto> productosSolicitados,String correo) {
        String id =crearID();
        String statementPrestamo = crearPrestamo(mySqlOperation,id,correo,productosSolicitados);
        mySqlOperation.setSqlStatement(statementPrestamo);
        mySqlOperation.executeSqlStatementVoid();
        insertContenido(mySqlOperation,id,productosSolicitados);
    }

    public static String crearPrestamo(MySqlOperation mySqlOperation,String idOut,String correo, List<Producto> productosSolicitados){
        Faker faker = new Faker();
        String id = idOut;
        String estado = ESTADO_UNO.getvalue();
        LocalDateTime fecha = LocalDateTime.now();
        LocalDateTime fechaEntrega = fecha.plusDays(faker.number().numberBetween(1, 16));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = formatter.format(fecha);
        String fechaEntregaFormateada = formatter.format(fechaEntrega);
        String correo_usuario = correo;

        return String.format(INSERT_PRESTAMO,id,estado,fechaFormateada,fechaEntregaFormateada,correo_usuario);
    }

    public static void insertContenido(MySqlOperation mySqlOperation,String id, List<Producto> productosSolicitados){
        for (int i =0;i<productosSolicitados.size();i++){
            Producto producto = productosSolicitados.get(i);
            String titulo = producto.getTitulo();
            String statementContenido = String.format(INSERT_CONTENIDO,id,titulo);
            mySqlOperation.setSqlStatement(statementContenido);
            mySqlOperation.executeSqlStatementVoid();
        }
    }

    public static String crearID(){
        Faker faker = new Faker();
        String id = faker.bothify("??########");
        return id;
    }

    public static List<Prestamo> getPrestamos() throws SQLException, ParseException {
        List<Prestamo> prestamos = new ArrayList<>();
        mySqlOperation.setSqlStatement(String.format(GET_PRESTAMO));
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResulset();

        while (resultSet.next()) {
            Prestamo prestamo = new Prestamo();
            prestamo.setId(resultSet.getString(1));
            prestamo.setEstado(resultSet.getString(2));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            prestamo.setFechaSalida(sdf.parse(resultSet.getString(3)));
            prestamo.setFechaDevolucion(sdf.parse(resultSet.getString(4)));
            prestamo.setCorreoUsuario(resultSet.getString((5)));
            prestamos.add(prestamo);
        }
        return prestamos;
    }

}

package co.com.biblioteca.DAO;

import co.com.biblioteca.modelo.Empleado;
import co.com.biblioteca.modelo.enums.RolEmpleado;
import co.com.biblioteca.mysql.MySQLDB;
import co.com.biblioteca.ui.MenuAdministrador;
import co.com.biblioteca.ui.MenuAsistente;
import co.com.biblioteca.ui.MenuSuper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private static final String INSERT_EMPLEADO = "INSERT INTO empleado (nombre, correo, contrasena, rol) VALUES (?, ?, ?, ?)";
    private static final String INICIAR_SESION = "SELECT * FROM empleado WHERE correo = ? AND contrasena = ? ";
    private static final String OBTENER_EMPLEADO = "SELECT * FROM empleado WHERE id = ?";
    private static final String ELIMINAR_EMPLEADO = "DELETE FROM empleado WHERE id = ?";
    private static final String ACTUALIZAR_EMPLEADO = "UPDATE empleado SET nombre = ?, correo = ?, contrasena = ?, rol = ? WHERE id = ?";
    private static final String OBTENER_TODOS_EMPLEADOS = "SELECT * FROM empleado";

    private static Connection conexion;

    public EmpleadoDAO() {
        this.conexion = MySQLDB.obtenerConexion();
    }

    public void crearEmpleado(Empleado empleado) {
        try (PreparedStatement statement = conexion.prepareStatement(INSERT_EMPLEADO, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getCorreo());
            statement.setString(3, empleado.getContrasena());
            statement.setString(4, empleado.getRol().toString());
            statement.executeUpdate();
            //asignarIdGenerado(empleado, statement);
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public Empleado iniciarSesion(String correo, String contrasena) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(INICIAR_SESION)) {
            statement.setString(1, correo);
            statement.setString(2, contrasena);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    //empleado encontrado
                    String nombre = rs.getString("nombre");
                    String contra = rs.getString("contrasena");
                    String email = rs.getString("correo");
                    int id = rs.getInt("id");
                    RolEmpleado rol = RolEmpleado.valueOf(rs.getString("rol"));
                    switch (rol.toString()){
                        case "SUPER":
                            MenuSuper.mostrarMenu();
                            break;
                        case "ADMINISTRADOR":
                            MenuAdministrador.main(new String[0]);
                            break;
                        case "ASISTENTE":
                            MenuAsistente.main(new String[0]);
                            break;
                        default:
                            System.out.println("Rol no valido");
                    }
                    Empleado empleado = new Empleado(id, nombre, email, contra, rol.toString());
                    return empleado;
                } else {
                    System.out.println("Empleado con correo o contraseña incorrecta");
                    return null;
                }
            }
        }
    }

    public Empleado obtenerEmpleado(int id) {
        try (PreparedStatement statement = conexion.prepareStatement(OBTENER_EMPLEADO)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return crearEmpleadoDesdeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return null;
    }

    public void actualizarEmpleado(Empleado empleado) {
       
        try (PreparedStatement statement = conexion.prepareStatement(ACTUALIZAR_EMPLEADO)) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getCorreo());
            statement.setString(3, empleado.getContrasena());
            statement.setString(4, empleado.getRol().toString());
            statement.setInt(5, empleado.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public void eliminarEmpleado(int id) {
        try (PreparedStatement statement = conexion.prepareStatement(ELIMINAR_EMPLEADO)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public List<Empleado> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try (PreparedStatement statement = conexion.prepareStatement(OBTENER_TODOS_EMPLEADOS); ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                empleados.add(crearEmpleadoDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return empleados;
    }

    private Empleado crearEmpleadoDesdeResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String correo = rs.getString("correo");
        String contrasena = rs.getString("contrasena");
        RolEmpleado rol = RolEmpleado.valueOf(rs.getString("rol"));
        return new Empleado(id, nombre, correo, contrasena, rol.toString());
    }

    private void manejarExcepcionSQL(SQLException e) {
        e.printStackTrace();
    }

        public RolEmpleado obtenerRolEmpleado(int idEmpleado) {
            Empleado empleado = obtenerEmpleado(idEmpleado);
            if (empleado != null) {
                return RolEmpleado.valueOf(empleado.getRol());
            } else {
                return null;
            }
        }
    }



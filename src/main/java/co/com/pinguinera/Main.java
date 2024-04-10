package co.com.pinguinera;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import co.com.pinguinera.modelos.DAO.PrestamoDAO;
import co.com.pinguinera.modelos.EstadoPrestamo;
import co.com.pinguinera.modelos.Prestamo;

public class Main {
    public static void main(String[] args) {
        try {
            // Intenta establecer la conexión a la base de datos
            Connection conexion = DataBase.conectar();

            // Si la conexión se establece con éxito, muestra un mensaje
            System.out.println("Conexión establecida correctamente.");

            // Crear instancia de PrestamoDAO
            PrestamoDAO prestamoDAO = new PrestamoDAO(conexion);

            // Crear una instancia de Prestamo para usar en las pruebas
            Prestamo prestamo = new Prestamo();
            prestamo.setUsuarioID(3); // Reemplaza con el ID de usuario correspondiente
            prestamo.setLibroID(3); // Reemplaza con el ID de libro correspondiente
            prestamo.setFechaPrestamo(new Date()); // Reemplaza con la fecha de préstamo actual
            prestamo.setFechaDevolucion(new Date()); // Reemplaza con la fecha de devolución actual
            prestamo.setEstado(EstadoPrestamo.SOLICITADO.name()); // Reemplaza con el estado deseado

            // Prueba de agregarPrestamo
            prestamoDAO.realizarPrestamo(prestamo);
            System.out.println("Prestamo agregado correctamente.");

            // Prueba de listarPrestamosPorUsuario
            int usuarioID = 3; // Reemplaza con el ID de usuario correspondiente
            List<Prestamo> prestamosPorUsuario = prestamoDAO.listarPrestamosPorUsuario(usuarioID);
            System.out.println("Prestamos del usuario con ID " + usuarioID + ":");
            prestamosPorUsuario.forEach(System.out::println);

            // Prueba de listarPrestamosPorEstado
            EstadoPrestamo estado = EstadoPrestamo.SOLICITADO; // Reemplaza con el estado deseado
            List<Prestamo> prestamosPorEstado = prestamoDAO.listarPrestamosPorEstado(estado);
            System.out.println("Prestamos con estado " + estado + ":");
            prestamosPorEstado.forEach(System.out::println);

            // Prueba de listarPrestamosPorFecha
            Date fechaInicio = new Date(2024, 3, 1); // Reemplaza con la fecha de inicio deseada
            Date fechaFin = new Date(2024, 3, 10); // Reemplaza con la fecha de fin deseada
            List<Prestamo> prestamosPorFecha = prestamoDAO.listarPrestamosPorFecha(fechaInicio, fechaFin);
            System.out.println("Prestamos entre " + fechaInicio + " y " + fechaFin + ":");
            prestamosPorFecha.forEach(System.out::println);

            // Cierra la conexión
            conexion.close();
        } catch (SQLException e) {
            // Si ocurre algún error al intentar establecer la conexión, muestra el mensaje de error
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            // También puedes imprimir la traza completa del error llamando a e.printStackTrace()
            // e.printStackTrace();
        }
    }
}

package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;


import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.capa_servicios.ServicioCRUDPrestamos;
import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import co.com.pinguinera.modelado.Prestamo;
import co.com.pinguinera.modelado.enums.EstadoPrestamo;
import co.com.pinguinera.capa_datos.ImplBD.BaseDatosImpl;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            // La conexión a la base de datos se ha abierto exitosamente.
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Crea una instancia de BaseDatosImpl
            BaseDatosImpl baseDatosImpl = new BaseDatosImpl(conexion);

            // Crea una instancia de ServicioCRUDPrestamos usando BaseDatosImpl
            ServicioCRUDPrestamos servicioPrestamos = new ServicioCRUDPrestamos(baseDatosImpl);

            // Probar agregar() con datos realistas
            Prestamo nuevoPrestamo = new Prestamo();
            nuevoPrestamo.setFechaPrestamo(LocalDate.now());
            nuevoPrestamo.setFechaDevolucion(LocalDate.now().plusWeeks(2)); // Devuelve el préstamo en 2 semanas
            nuevoPrestamo.setEstado(EstadoPrestamo.SOLICITADO); // Estado inicial: SOLICITADO
            nuevoPrestamo.setCorreoUsuario("mariana.sandoval@example.com"); // Correo existente en tu base de datos
            nuevoPrestamo.setTituloPublicacion("100 años de soledad"); // Título existente en tu base de datos
            servicioPrestamos.agregar(nuevoPrestamo);
            System.out.println("Nuevo préstamo agregado con éxito.");

            // Probar obtenerTodos()
            List<Prestamo> prestamos = servicioPrestamos.obtenerTodos();
            System.out.println("Lista de todos los préstamos:");
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo);
            }



        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}

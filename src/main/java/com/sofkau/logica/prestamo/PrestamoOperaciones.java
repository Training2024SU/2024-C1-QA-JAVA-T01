package com.sofkau.logica.prestamo;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.EmpleadoRepositorio;
import com.sofkau.integration.repositorio.PrestamoRepositorio;
import com.sofkau.model.Empleado;
import com.sofkau.model.Prestamo;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;
import com.sofkau.util.enums.EstadoPrestamo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class PrestamoOperaciones {

    private static HashMap<String, Prestamo> prestamos = new HashMap<>();

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public PrestamoOperaciones() {
        getPrestamos();
    }

    public void RegistrarPrestamo (String titulo, String dateDevolucion, String correoUsuario){
        Prestamo prestamo = new Prestamo();
        prestamo.setId(GenerateUniqueId.generateID());
        prestamo.setCorreoUsuario(correoUsuario);
        prestamo.setTituloPublicacion(titulo);
        Date fechaPrestamo = new Date();
        Date fechaDevolucion = null;
        try {
            fechaDevolucion = formato.parse(dateDevolucion);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // calcula la diferencia en milisegundos y lo convierte a días
            long diferenciaEnDias = (fechaDevolucion.getTime() - fechaPrestamo.getTime()) / (1000 * 60 * 60 * 24);

            // Validar que la fecha de devolución no sea menor que la fecha actual y sea máximo 15 días después de la fecha de préstamo
            if (!(fechaDevolucion.after(fechaPrestamo) && diferenciaEnDias <= 15)) {
                System.out.println("Por favor ingrese una fecha de devolucion valida");
                return;
            }

        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setEstadoPrestamo(EstadoPrestamo.SOLICITADO.toString());

        PrestamoRepositorio.crearPrestamo(prestamo);

        MensajeOperacionBd.crearPrestamo();

        // Se actualiza el prestamo en la lista
        prestamos.put(prestamo.getId(), prestamo);

    }

    public void getPrestamos() {
        prestamos = PrestamoRepositorio.consultarPrestamos();
    }
}

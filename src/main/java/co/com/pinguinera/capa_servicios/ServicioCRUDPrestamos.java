package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.modelado.Prestamo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCRUDPrestamos {

    private List<Prestamo> prestamos; // Colección local de préstamos

    public ServicioCRUDPrestamos() {
        this.prestamos = new ArrayList<>(); // Inicializa la colección local
    }

    // Método para agregar un nuevo préstamo a la colección local
    public void agregar(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    // Método para obtener todos los préstamos desde la colección local
    public List<Prestamo> obtenerTodos() {
        return prestamos;
    }

    // Método para actualizar un préstamo existente en la colección local
    public void actualizar(Prestamo prestamo) {
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getIdPrestamo() == prestamo.getIdPrestamo()) {
                prestamos.set(i, prestamo);
                break;
            }
        }
    }

    // Método para eliminar un préstamo de la colección local
    public void eliminar(int idPrestamo) {
        prestamos.removeIf(prestamo -> prestamo.getIdPrestamo() == idPrestamo);
    }
}
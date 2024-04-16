package co.com.sofkau.logica.prestamo;

import co.com.sofkau.logica.publicacion.PublicacionUtils;
import co.com.sofkau.model.Empleado;
import co.com.sofkau.model.Prestamo;
import co.com.sofkau.model.Publicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrestamoUtils {
    public static void imprimirPrestamos() {
        ArrayList<Prestamo> prestamos = Prestamo.getPrestamos();
        imprimirInformacionPrestamos(prestamos);
    }

    public static void ImprimirPrestamoEmail(String correo){
        imprimirInformacionPrestamos(filtrarPrestamoEmail(Prestamo.getPrestamos(), correo));

    }

    public static ArrayList<Prestamo> filtrarPrestamoEmail(ArrayList<Prestamo> prestamos,String correo) {

        List<Prestamo> prestamosFiltrados = prestamos.stream()
                .filter(p -> p.getUsuario_correo().equals(correo))
                .collect(Collectors.toList());

        return new ArrayList<> (prestamosFiltrados);
    }

    public static Prestamo filtrarPrestamoId(ArrayList<Prestamo> prestamos,int Id) {

        List<Prestamo> prestamosFiltrados = prestamos.stream()
                .filter(p -> p.getId() == Id)
                .collect(Collectors.toList());

        return prestamosFiltrados.get(0);
    }



    private static void imprimirInformacionPrestamos(List<Prestamo> prestamos) {
        for (Prestamo prestamoImprimir: prestamos) {

            System.out.println("ID: " + prestamoImprimir.getId());
            System.out.println("fechaPrestamo: " + prestamoImprimir.getFechaPrestamo());
            System.out.println("fechaDevolucion: " + prestamoImprimir.getFechaDevolucion());
            System.out.println("estado: " + prestamoImprimir.getEstado());
            System.out.println("Usuario_correo: " + prestamoImprimir.getUsuario_correo());
            System.out.println("Publicacion_titulo: " + prestamoImprimir.getPublicacion_titulo());

            System.out.println("-------------------------");
        }
    }

    public static void alertaDevolucionTardia(int Id) {
        Prestamo prestamoFiltrado = filtrarPrestamoId(Prestamo.getPrestamos(), Id);

        if (prestamoFiltrado != null) {
            LocalDate fechaDevolucionTardia = LocalDate.parse(prestamoFiltrado.getFechaDevolucion());
            LocalDate fechaActual = LocalDate.now();

            if (fechaDevolucionTardia.isBefore(fechaActual)) {
                System.out.println("¡Alerta! La devolución es tardía.");
            }

        }
    }


}

package org.example.logica.control;

import org.example.integration.database.persistencia.CrudPrestamo;
import org.example.model.Prestamo;
import org.example.model.Publicacion;
import org.example.model.Usuario;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.dialog.Menu.msnConfirmarReserva;
import static org.example.logica.control.ConstantesLogica.*;
import static org.example.logica.control.MetodosMain.pedirOpcion;
import static org.example.logica.control.PublicacionControl.actualizarCantidades;
import static org.example.logica.control.PublicacionControl.actualizarCantidadesDevueltasDb;
import static org.example.logica.control.UsuarioControl.librosSeleccionados;
import static org.example.logica.control.UsuarioControl.novelasSeleccionados;
import static org.example.model.Prestamo.prestamos;
import static org.example.model.Publicacion.libros;
import static org.example.model.Publicacion.novelas;

public class PrestamoControl {
    static CrudPrestamo crudPrestamo = new CrudPrestamo();


    public static void confirmarOperacion(Usuario usuario) throws SQLException {
        boolean bandera = true;
        int opcion;
        while (bandera) {
            validarPublicacionesEscogidas();
            mostrarPublicacionesSeleccionados();
            msnConfirmarReserva();
            opcion = pedirOpcion();
            bandera = seleccionarOpcionValidacion(opcion, bandera, usuario);
        }
    }

    public static boolean validarPublicacionesEscogidas() {
        if (!librosSeleccionados.isEmpty() || !novelasSeleccionados.isEmpty()) {
            return true;
        } else {
            System.out.println(SIN_SELECCION_PUBLICACION);
        }
        return false;
    }

    public static void mostrarPublicacionesSeleccionados() {
        if (!librosSeleccionados.isEmpty()) {
            System.out.println(LIBROS_SELECCIONADOS);
            for (Publicacion libros : librosSeleccionados) {
                System.out.println(libros);
            }
        }
        if (!novelasSeleccionados.isEmpty()) {
            System.out.println(NOVELAS_SELECCIONADOS);
            for (Publicacion novelas : novelasSeleccionados) {
                System.out.println(novelas);
            }
        }
    }

    public static boolean seleccionarOpcionValidacion(int opcion, boolean bandera, Usuario usuario) throws SQLException {
        switch (opcion) {
            case 1:
                validarPrestamo(usuario);
                bandera = false;
                break;
            case 2:
                bandera = false;
                break;
            default:
                System.out.println(SELECCION_INVALIDA);
        }
        return bandera;
    }

    public static boolean validarPrestamo(Usuario usuario) throws SQLException {
        LocalDate fechaPrestamo;
        LocalDate fechaDevolucion;
        fechaPrestamo = LocalDate.now();
        fechaDevolucion = solicitarFechaDevolucion();
        crearPrestamo(fechaPrestamo, fechaDevolucion, usuario);

        return true;
    }

    private static void crearPrestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion, Usuario usuario) throws SQLException {
        if (!fechaDevolucion.isBefore(fechaPrestamo.plusDays(15))) {
            System.out.println(FECHA_DEVOLUCION_EXCEDIDA);
        } else {
            if (!librosSeleccionados.isEmpty()) {
                actualizarCantidades(librosSeleccionados);
                crearPrestamoDb(librosSeleccionados, fechaPrestamo, fechaDevolucion, usuario);
                System.out.println(PRESTAMO_SOLICITADO);
            }
            if (!novelasSeleccionados.isEmpty()) {
                actualizarCantidades(novelasSeleccionados);
                crearPrestamoDb(novelasSeleccionados, fechaPrestamo, fechaDevolucion, usuario);
                System.out.println(PRESTAMO_SOLICITADO);
            }
        }
    }

    private static void crearPrestamoDb(List<Publicacion> listaPublicacion, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Usuario usuario) throws SQLException {
        Prestamo prestamo;
        for (Publicacion p : listaPublicacion) {
            prestamo = new Prestamo(fechaPrestamo, fechaDevolucion, ESTADO_SOLICITADO, usuario.getCorreo(), p.getTitulo());
            prestamo.setId(crudPrestamo.crearEntidad(prestamo));
            if (prestamos.isEmpty()) {
                crudPrestamo.seleccionarDatos();
            }else {
                prestamos.add(prestamo);
            }

        }
    }

    private static LocalDate solicitarFechaDevolucion() {
        LocalDate fechaDevolucion;
        System.out.println(DIA_FECHA_DEVOLUCION);
        int dia = pedirOpcion();
        System.out.println(MES_FECHA_DEVOLUCION);
        int mes = pedirOpcion();
        System.out.println(ANIO_FECHA_DEVOLUCION);
        int anio = pedirOpcion();
        fechaDevolucion = LocalDate.of(anio, mes, dia);
        return fechaDevolucion;
    }

    public static void realizarPrestamo() throws SQLException {
        String correoUsuario = pedirCorreo();
        actualizarEstadoPrestamo(correoUsuario, ESTADO_SOLICITADO, ESTADO_SOLICITADO, ESTADO_REALIZADO);
        System.out.println(PRESTAMO_REALIZADO);
    }

    public static String pedirCorreo() {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println(String.format(MSN_CORREO, TIPO_USUARIO));
        String correoUsuario = sc.nextLine();
        return correoUsuario;
    }

    private static void actualizarEstadoPrestamo(String correoUsuario, String estadoLista, String estadoInicial, String estadoFinal) throws SQLException {
        mostrarPrestamos(correoUsuario, estadoLista);
        for (int i = 0; i < prestamos.size(); i++) {
            Prestamo prestamoRealizado = prestamos.get(i);
            if (prestamos.get(i).getCorreoUsuario().equalsIgnoreCase(correoUsuario) && prestamos.get(i).getEstadoPrestamo().equalsIgnoreCase(estadoInicial)) {
                prestamoRealizado.setEstadoPrestamo(estadoFinal);
                System.out.println(prestamoRealizado);
                prestamos.set(i, prestamoRealizado);

                crudPrestamo.actualizarPrestamo(prestamoRealizado);
            }
        }
    }

    private static void mostrarPrestamos(String correo, String estadoLista) throws SQLException {
        if (prestamos.isEmpty()) {
            crudPrestamo.seleccionarDatos();
        }
        if(estadoLista.equalsIgnoreCase(ESTADO_SOLICITADO)){
            System.out.println(PRESTAMO_SOLICITADO_USUARIO);
        }else if (estadoLista.equalsIgnoreCase(PRESTAMO_REALIZADO)){
            System.out.println(PRESTAMO_REALIZADO_USUARIO);
        }
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCorreoUsuario().equalsIgnoreCase(correo) && prestamos.get(i).getEstadoPrestamo().equalsIgnoreCase(estadoLista)) {
                System.out.println(i + 1 + ". " + prestamos.get(i));
            }
        }
    }

    public static void finalizarPrestamo() throws SQLException {
        String correoUsario = pedirCorreo();
        actualizarEstadoPrestamo(correoUsario, ESTADO_REALIZADO, ESTADO_REALIZADO, ESTADO_FINALIZADO);
        actualizarCantidadesDevueltasDb(correoUsario);
        String tituloPrestado = "";
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCorreoUsuario().equalsIgnoreCase(correoUsario) && prestamos.get(i).getEstadoPrestamo().equalsIgnoreCase(ESTADO_REALIZADO)) {
                tituloPrestado = prestamos.get(i).getTituloPublicacion();
                for (int j = 0; j < libros.size(); j++) {
                    if (libros.get(j).getTitulo().equalsIgnoreCase(tituloPrestado)) {
                        libros.get(j).setCantidadDisponible(libros.get(j).getCantidadDisponible() + 1);
                        libros.get(j).setCantidadPrestado(libros.get(j).getCantidadPrestado() - 1);
                    }
                }
                for (int k = 0; k < novelas.size(); k++) {
                    if (novelas.get(k).getTitulo().equalsIgnoreCase(tituloPrestado)) {
                        novelas.get(k).setCantidadDisponible(novelas.get(k).getCantidadDisponible() + 1);
                        novelas.get(k).setCantidadPrestado(novelas.get(k).getCantidadPrestado() - 1);
                    }
                }
            }
        }

        System.out.println("Prestamos finalizados éxitosamente");

    }

}

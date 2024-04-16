package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.LibroDAO;
import co.com.pinguinera.datos.crud_local.CRUDLibrosLocales;
import co.com.pinguinera.datos.model.publicaciones.Libro;
import co.com.pinguinera.servicios.integracion.SincronizadorLibros;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vistas_libro.InformacionLibroVista;

import java.sql.SQLException;
import java.util.List;

public class ControladorCRUDLibro {

    private final InformacionLibroVista vista;
    private final CRUDLibrosLocales crudLibrosLocales;
    private final LibroDAO libroDAO;
    private final SincronizadorLibros sincronizadorLibro;

    public ControladorCRUDLibro(InformacionLibroVista vista, CRUDLibrosLocales crudLibrosLocales,
                                LibroDAO libroDAO, SincronizadorLibros sincronizadorLibro) {
        this.vista = vista;
        this.crudLibrosLocales = crudLibrosLocales;
        this.libroDAO = libroDAO;
        this.sincronizadorLibro = sincronizadorLibro;
    }

    public void registrarLibro() {
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(vista.pedirTituloLibro());
        nuevoLibro.setAutor(vista.pedirAutorLibro());
        nuevoLibro.setNumPaginas(vista.pedirNumPaginas());
        nuevoLibro.setCantEjemplares(vista.pedirCantEjemplares());
        nuevoLibro.setCantPrestados(vista.pedirCantPrestados());
        crudLibrosLocales.agregar(nuevoLibro);
        try {
            libroDAO.insertar(nuevoLibro);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarLibro() {
        Libro libroActualizado = new Libro();
        libroActualizado.setTitulo(vista.pedirTituloLibro());
        libroActualizado.setAutor(vista.pedirAutorLibro());
        libroActualizado.setNumPaginas(vista.pedirNumPaginas());
        libroActualizado.setCantEjemplares(vista.pedirCantEjemplares());
        libroActualizado.setCantPrestados(vista.pedirCantPrestados());
        crudLibrosLocales.actualizar(libroActualizado);
        try {
            libroDAO.actualizar(libroActualizado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarLibro() {
        String titulo = vista.pedirTituloLibro();
        Libro libroAEliminar = new Libro();
        libroAEliminar.setTitulo(titulo);
        crudLibrosLocales.eliminar(libroAEliminar);
        try {
            libroDAO.eliminar(libroAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void obtenerTodosLibros() {
        try {
            List<Libro> librosBD = libroDAO.obtenerTodos();
            librosBD.forEach(System.out::println);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeSolicitudFallida();
        }
    }

    private void sincronizarDatos() {
        try {
            sincronizadorLibro.sincronizarLibros();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }
}

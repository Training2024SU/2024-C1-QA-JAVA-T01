package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.NovelaDAO;
import co.com.pinguinera.datos.crud_local.CRUDNovelasLocales;
import co.com.pinguinera.datos.model.publicaciones.Novela;
import co.com.pinguinera.servicios.integracion.SincronizadorNovelas;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vistas_novela.InformacionNovelaVista;;

import java.sql.SQLException;
import java.util.List;

public class ControladorCRUDNovela {

    private final InformacionNovelaVista vista;
    private final CRUDNovelasLocales crudNovelasLocales;
    private final NovelaDAO novelaDAO;
    private final SincronizadorNovelas sincronizadorNovela;

    public ControladorCRUDNovela(InformacionNovelaVista vista, CRUDNovelasLocales crudNovelasLocales,
                                 NovelaDAO novelaDAO, SincronizadorNovelas sincronizadorNovela) {
        this.vista = vista;
        this.crudNovelasLocales = crudNovelasLocales;
        this.novelaDAO = novelaDAO;
        this.sincronizadorNovela = sincronizadorNovela;
    }

    public void registrarNovela() {
        Novela nuevaNovela = new Novela();
        nuevaNovela.setTitulo(vista.pedirTituloNovela());
        nuevaNovela.setAutor(vista.pedirAutorNovela());
        nuevaNovela.setNumPaginas(vista.pedirNumPaginas());
        nuevaNovela.setCantEjemplares(vista.pedirCantEjemplares());
        nuevaNovela.setCantPrestados(vista.pedirCantPrestados());
        crudNovelasLocales.agregar(nuevaNovela);
        try {
            novelaDAO.insertar(nuevaNovela);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarNovela() {
        Novela novelaActualizada = new Novela();
        novelaActualizada.setTitulo(vista.pedirTituloNovela());
        novelaActualizada.setAutor(vista.pedirAutorNovela());
        novelaActualizada.setNumPaginas(vista.pedirNumPaginas());
        novelaActualizada.setCantEjemplares(vista.pedirCantEjemplares());
        novelaActualizada.setCantPrestados(vista.pedirCantPrestados());
        crudNovelasLocales.actualizar(novelaActualizada);
        try {
            novelaDAO.actualizar(novelaActualizada);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarNovela() {
        String titulo = vista.pedirTituloNovela();
        Novela novelaAEliminar = new Novela();
        novelaAEliminar.setTitulo(titulo);
        crudNovelasLocales.eliminar(novelaAEliminar);
        try {
            novelaDAO.eliminar(novelaAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void obtenerTodasNovelas() {
        try {
            List<Novela> novelasBD = novelaDAO.obtenerTodos();
            novelasBD.forEach(System.out::println);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeSolicitudFallida();
        }
    }

    private void sincronizarDatos() {
        try {
            sincronizadorNovela.sincronizarNovelas();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }
}

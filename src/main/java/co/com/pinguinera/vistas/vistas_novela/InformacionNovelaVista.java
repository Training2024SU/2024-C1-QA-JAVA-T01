package co.com.pinguinera.vistas.vistas_novela;

import co.com.pinguinera.vistas.VistaUtil;

public class InformacionNovelaVista {

    public String pedirTituloNovela() {
        return VistaUtil.pedirTitulo();
    }

    public String pedirAutorNovela() {
        return VistaUtil.pedirAutor();
    }

    public int pedirNumPaginas() {
        return VistaUtil.pedirNumPaginas();
    }

    public int pedirCantEjemplares() {
        return VistaUtil.pedirCantEjemplares();
    }

    public int pedirCantPrestados() {
        return VistaUtil.pedirCantPrestados();
    }
}

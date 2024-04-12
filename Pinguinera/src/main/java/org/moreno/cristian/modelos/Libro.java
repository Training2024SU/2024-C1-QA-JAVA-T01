package org.moreno.cristian.modelos;

import org.moreno.cristian.modelos.enums.AreaConocimiento;

public class Libro extends Publicacion {

    private String id;
    private int numeroPaginas;
    private AreaConocimiento areaConocimiento;

    public Libro(String id, String titulo, int totalEjemplares, int ejemplaresDisponibles, int ejemplaresPrestados, Autor autor, int numeroPaginas, AreaConocimiento areaConocimiento) {
        super(id, titulo, totalEjemplares, ejemplaresDisponibles, ejemplaresPrestados, autor);
        this.id = id;
        this.numeroPaginas = numeroPaginas;
        this.areaConocimiento = areaConocimiento;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public AreaConocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(AreaConocimiento areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }
}

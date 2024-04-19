package co.com.training.modelo;

public class Libro extends Material {
    private String area_conocimiento;
    private int numero_paginas;

    public Libro(String id_material, String titulo, Autor autor, int cantidad_ejemplares, int cantidad_prestados, String area_conocimiento, int numero_paginas) {
        super(id_material, titulo, autor, cantidad_ejemplares, cantidad_prestados);
        this.area_conocimiento = area_conocimiento;
        this.numero_paginas = numero_paginas;
    }

    public String getArea_conocimiento() {
        return area_conocimiento;
    }

    public void setArea_conocimiento(String area_conocimiento) {
        this.area_conocimiento = area_conocimiento;
    }

    public int getNumero_paginas() {
        return numero_paginas;
    }

    public void setNumero_paginas(int numero_paginas) {
        this.numero_paginas = numero_paginas;
    }

}

package co.com.pinguinera.modelos;

public enum TipoRol {
    ADMINISTRADOR(1),
    ASISTENTE(2),
    LECTOR(3);

    private final int rolID;

    TipoRol(int rolID) {
        this.rolID = rolID;
    }

    public int getRolID() {
        return rolID;
    }

    // Método estático para obtener un objeto TipoRol a partir del nombre del rol
    public static TipoRol obtenerPorNombre(String nombre) {
        for (TipoRol rol : values()) {
            if (rol.name().equalsIgnoreCase(nombre)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Rol no encontrado: " + nombre);
    }
}

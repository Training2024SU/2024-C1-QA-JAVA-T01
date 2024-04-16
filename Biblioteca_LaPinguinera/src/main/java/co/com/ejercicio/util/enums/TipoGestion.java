package co.com.ejercicio.util.enums;

public enum TipoGestion {
    GESTION_UNO("1. Gestionar Usuarios"),GESTION_DOS("2. Gestionar Publicaciones"),
    GESTION_TRES("3. Gestionar Empleados"),GESTION_CUATRO("4. Gestionar préstamos");
    private String value;

    TipoGestion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


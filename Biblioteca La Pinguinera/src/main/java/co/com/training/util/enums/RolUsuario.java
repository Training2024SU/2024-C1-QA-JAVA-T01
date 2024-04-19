package co.com.training.util.enums;

public enum RolUsuario {

    TIPO_UNO("ADMINISTRADOR"),
    TIPO_DOS("ASISTENTE"),
    TIPO_TRES("LECTOR");

    private String value;

    private RolUsuario(String rol){
        this.value = rol;
    }

    public String getvalue() {
        return value;
    }
}

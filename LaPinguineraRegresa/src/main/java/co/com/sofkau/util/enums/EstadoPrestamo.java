package co.com.sofkau.util.enums;

public enum EstadoPrestamo {

    ESTADO_UNO("SOLICITADO"),ESTADO_DOS("REALIZADO"),

    ESTADO_TRES("FINALIZADO");

    private String value;

    private EstadoPrestamo(String estado) {
        this.value = estado;
    }

    public String getvalue() {
        return value;
    }
}

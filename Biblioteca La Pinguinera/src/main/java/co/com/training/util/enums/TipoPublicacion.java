package co.com.training.util.enums;

public enum TipoPublicacion {
    TIPO_UNO("Libro"),
    TIPO_DOS("Novela");
    private String value;

    private TipoPublicacion (String tipo){
        this.value = tipo;
    }

    public String getvalue() {
        return value;
    }
}

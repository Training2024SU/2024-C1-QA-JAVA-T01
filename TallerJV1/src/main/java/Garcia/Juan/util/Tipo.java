package Garcia.Juan.util;

public enum Tipo {
    TIPO_UNO("Libro"),TIPO_DOS("Novela");
    private String value;

    private Tipo (String tipo){
        this.value = tipo;
    }

    public String getvalue() {
        return value;
    }
}

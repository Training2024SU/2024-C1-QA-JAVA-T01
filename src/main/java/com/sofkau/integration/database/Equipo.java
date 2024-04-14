package com.sofkau.integration.database;

public enum Equipo
{
    EQUIPO_UNO("FC Barcelona"), REAL_MADRID("Real Madrid"),
    SEVILLA("Sevilla FC"), VILLAREAL("Villareal");

    private String value;

    private Equipo (String nombreClub){
        this.value = nombreClub;
    }

    public String getvalue() {
        return value;
    }


}

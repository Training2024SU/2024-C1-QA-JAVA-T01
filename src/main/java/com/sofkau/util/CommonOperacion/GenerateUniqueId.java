package com.sofkau.util.CommonOperacion;

import java.util.UUID;

public class GenerateUniqueId {

    public static String generateID(){
        UUID uuid = UUID.randomUUID();
        // Tomar los primeros 8 caracteres del UUID
        return uuid.toString().substring(0, 10);
    }

}

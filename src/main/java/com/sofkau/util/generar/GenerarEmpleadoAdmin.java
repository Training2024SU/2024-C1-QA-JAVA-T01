package com.sofkau.util.generar;

import com.sofkau.logica.empleado.EmpleadoOperaciones;
import io.github.cdimascio.dotenv.Dotenv;

public class GenerarEmpleadoAdmin {


    static Dotenv dotenv = Dotenv.configure().load();

    private static final String server =  dotenv.get("database.server");
    private static final String databaseName = dotenv.get("database.databaseName");
    private static final String user = dotenv.get("database.user");
    private static final String password = dotenv.get("database.password");
    EmpleadoOperaciones empleadoOp = new EmpleadoOperaciones();
    public static void generarEmpleadoAdministrador(){
        String nombre =dotenv.get("nombre");
        String correo =dotenv.get("correo");
        String contrasena =dotenv.get("contrasena");
        String rol =dotenv.get("rol");

    }

}

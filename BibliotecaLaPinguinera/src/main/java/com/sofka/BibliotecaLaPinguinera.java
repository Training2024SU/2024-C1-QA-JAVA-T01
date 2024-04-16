package com.sofka;

import com.sofka.integration.database.mysql.MySqlOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

import static com.sofka.controllers.ControlMenu.preguntarSalir;
import static com.sofka.controllers.ControlStament.closeConnection;
import static com.sofka.controllers.ControlStament.openConnection;
import static com.sofka.menu.menuDeInicio.menuInicio;

@SpringBootApplication
public class BibliotecaLaPinguinera {
    public static final String SELECCIONE_CORRECTAMENTE = "Opción incorrecta, por favor seleccione correctamente";
    public static MySqlOperation mySqlOperation = new MySqlOperation();

    public static void main(String[] args) throws SQLException, NumberFormatException, NullPointerException {
        System.out.println("Bienvenido a la Biblioteca la Pingüinera\n");
        do {
            openConnection();
            menuInicio();
            closeConnection();
        } while (!(preguntarSalir().equals("s")));
    }
}

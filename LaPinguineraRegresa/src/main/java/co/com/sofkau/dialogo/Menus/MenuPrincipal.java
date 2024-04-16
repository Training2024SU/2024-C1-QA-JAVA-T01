package co.com.sofkau.dialogo.Menus;

import co.com.sofkau.ConeccionDB;
import co.com.sofkau.logica.control.MetodosMenu;
import co.com.sofkau.logica.usuario.UsuarioUtils;
import co.com.sofkau.model.Usuario;

import java.sql.SQLException;

import static co.com.sofkau.dialogo.ConstantesMenu.*;

public class MenuPrincipal {

    public static void menuPrincipal() throws SQLException {
        int opcion = -1;

        ConeccionDB.comenzarConeccion();


        while (opcion == -1) {
            System.out.println(MSN_UNO);
            System.out.println(MSN_DOS);
            System.out.println(MSN_TRES);
            System.out.println(MSN_CUATRO);
            System.out.println(MSN_CINCO);
            System.out.println(MSN_PRINCIPAL_2);
            opcion = MetodosMenu.preguntarNumeroUsuario();
            if (opcion == -1) {
                continue;
            }
        }
        switch (opcion) {
            case 1:
                System.out.println("Seleccionaste la opción 1");
                break;
            case 2:
                System.out.println("Seleccionaste " + MSN_TRES);
                UsuarioUtils.crearCuentaUsuario(Usuario.getMySqlOperation(), MetodosMenu.preguntarStringAlUsuario(MSN_INFORMACION_1), MetodosMenu.preguntarStringAlUsuario(MSN_INFORMACION_2),MetodosMenu.preguntarStringAlUsuario(MSN_INFORMACION_3));
                break;
            case 3:

                System.out.println("Seleccionaste la opción 3");
                break;
            case 4:

                System.out.println("Seleccionaste la opción 4");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

}

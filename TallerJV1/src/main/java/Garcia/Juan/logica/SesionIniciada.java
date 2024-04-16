package Garcia.Juan.logica;

import Garcia.Juan.Exporter.CSVExporter;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Usuario;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Garcia.Juan.CRUD.CrudUsuario.getUsersFromTable;
import static Garcia.Juan.dialogo.Menu.*;
import static Garcia.Juan.logica.MetodosMain.pedirOpcion;
import static Garcia.Juan.logica.MetodosPrestamo.*;
import static Garcia.Juan.logica.MetodosProducto.*;
import static Garcia.Juan.logica.MetodosUsuario.registrarUsuario;
import static Garcia.Juan.util.Roles.*;

public class SesionIniciada {

    private static MySqlOperation mySqlOperation;

    public SesionIniciada(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static void usuarioIniciado(MySqlOperation mySqlOperation, List<String> credenciales) throws SQLException, ParseException {
        String correo = credenciales.get(0);
        String contrasena = credenciales.get(1);
        List<String> usuarioIniciado = new ArrayList<>();
        List<Usuario> usuarios = getUsersFromTable(mySqlOperation);
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                usuarioIniciado.add(usuario.getCorreo());
                usuarioIniciado.add(usuario.getContrasena());
                usuarioIniciado.add(usuario.getNombre());
                usuarioIniciado.add(usuario.getRol());
                break;
            }
        }
        menuUsuarioIniciado(usuarioIniciado);
        accionesPorRol(usuarioIniciado.get(3),usuarioIniciado.get(0));
    }

    public static void accionesPorRol(String rol,String correo) throws SQLException, ParseException {
        boolean ciclo =true;
        while (ciclo){
            if (TIPO_TRES.getvalue().equals(rol)){
                menuLector();
                int opcion = pedirOpcion();
                switch (opcion){
                    case 1:
                        verPublicaciones(mySqlOperation);
                        break;
                    case 2:
                        solicitarPrestamo(mySqlOperation,correo);
                        break;
                    default:
                        ciclo=false;
                        break;
                }
            }
            else if (TIPO_DOS.getvalue().equals(rol)){ //asistente
                menuAsistente();
                int opcion = pedirOpcion();
                switch (opcion){
                    case 1:
                        gestionarMaterial(mySqlOperation);//gestionar material
                        break;
                    case 2:
                        gestionarPrestamo(mySqlOperation);//consultar prestamos
                        break;
                    default:
                        ciclo=false;
                        break;
                }
            }
            else {
                menuAdmin();
                int opcion = pedirOpcion();
                switch (opcion){
                    case 1:
                        registrarUsuario(mySqlOperation,1);
                        break;
                    case 2:
                        List<Usuario> usuarios = getUsersFromTable(mySqlOperation);//ver info users
                        System.out.println(usuarios);
                        CSVExporter.exportToCSV(usuarios);
                        break;
                    default:
                        ciclo=false;
                        break;
                }
            }
        }
    }
}

package Garcia.Juan.logica;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Prestamo;
import Garcia.Juan.model.Producto;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Garcia.Juan.CRUD.CrudPrestamo.getPrestamos;
import static Garcia.Juan.CRUD.CrudPrestamo.insertarPrestamo;
import static Garcia.Juan.CRUD.CrudProducto.*;
import static Garcia.Juan.dialogo.Menu.menuConfirmacion;
import static Garcia.Juan.logica.MetodosMain.pedirOpcion;
import static Garcia.Juan.util.EstadoPrestamo.*;

public class MetodosPrestamo {
    private static MySqlOperation mySqlOperation;

    public MetodosPrestamo(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static void solicitarPrestamo(MySqlOperation mySqlOperation,String correo){
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Cuántos productos desea solicitar? ");
        int cantidadProductos = Integer.parseInt(scanner.nextLine());
        List<Producto> productosSolicitados = new ArrayList<>();
        List<Producto> productos = getProductos(mySqlOperation);

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.print("Ingrese el título del producto #" + (i + 1) + ": ");
            String titulo = scanner.nextLine();
            productosSolicitados.add(getProducto(mySqlOperation,titulo,productos));
            System.out.println("Producto agregado: " + titulo);
            System.out.println("-----------------------------------");
        }
        System.out.println("Productos solicitados:");
        for (Producto producto : productosSolicitados) {
            System.out.println(producto.toStringLibros());
        }
        menuConfirmacion();
        int confirmacion = pedirOpcion();
        switch (confirmacion){
            case 1:
                //Metodo para agregar a la tabla de prestamo
                insertarPrestamo(mySqlOperation,productosSolicitados,correo);
                sumaPrestados(mySqlOperation,productosSolicitados);
                break;
            case 2:
                productosSolicitados.clear();
                break;
            default:
                break;
        }
    }

    public static void gestionarPrestamo(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserte el correo que desea consultar");
        String correo = scanner.nextLine();
        List<Prestamo> prestamos = getPrestamos();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCorreoUsuario().equals(correo)) {
                System.out.println("Información de solicitud:");
                System.out.println(prestamo);
            }
        }
        System.out.println("Digite la opción:");
        System.out.println("1. Cambiar estado");
        System.out.println("2. Salir");
        int seleccion = Integer.parseInt(scanner.nextLine());
        if (seleccion==1){
            System.out.println("Inserte el ID de prestamo:");
            String id = scanner.nextLine();
            System.out.println("------------------");
            System.out.println("Digite la opción:");
            System.out.println("1. "+String.valueOf(ESTADO_DOS));
            System.out.println("2. "+String.valueOf(ESTADO_TRES));
            int opcion = Integer.parseInt(scanner.nextLine());
            for (Prestamo prestamo:prestamos){
                if (prestamo.getId().equals(id)){
                    switch (opcion) {
                        case 1:
                            prestamo.setEstado(String.valueOf(ESTADO_DOS));//cambiar el estado del prestamo a realizado
                            break;
                        case 2:
                            prestamo.setEstado(String.valueOf(ESTADO_TRES));
                            break;
                    }
                }
            }
        }
    }
}

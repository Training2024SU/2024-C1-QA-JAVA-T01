package Garcia.Juan.logica;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static Garcia.Juan.CRUD.CrudProducto.getProductos;
import static Garcia.Juan.CRUD.CrudProductoAsistente.*;
import static Garcia.Juan.dialogo.Menu.menuProductos;


public class MetodosProducto {

    private static MySqlOperation mySqlOperation;

    public MetodosProducto(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static void verPublicaciones(MySqlOperation mySqlOperation) throws SQLException {
        int seleccion;
        Scanner scanner = new Scanner(System.in);
        menuProductos();
        seleccion = Integer.parseInt(scanner.nextLine());
        List<Producto> productos = getProductos(mySqlOperation);
        switch (seleccion){
            case 1:
                for (Producto producto : productos) {
                    if (producto.getCantidadDisponibles() > 0 && producto.getTipo().equals("Libro")) {
                        System.out.println(producto.toStringLibros());
                        System.out.println("-----------------------");
                    }
                }
                break;
            case 2:
                String autor;
                System.out.println("Inserte el nombre del autor que busca:");
                autor=scanner.nextLine();
                for (Producto producto : productos) {
                    if (producto.getCantidadDisponibles() > 0 && producto.getAutor().equals(autor) && producto.getTipo().equals("Libro")) {
                        System.out.println(producto.toStringAutor());
                    }
                }
                break;
            case 3:
                for (Producto producto : productos) {
                    if (producto.getCantidadDisponibles() > 0 && producto.getTipo().equals("Novela")) {
                        System.out.println(producto.toStringNovelas());
                        System.out.println("-----------------------");
                    }
                }
                break;
            case 4:
                String autorN;
                System.out.println("Inserte el nombre del autor que busca:");
                autorN=scanner.nextLine();
                for (Producto producto : productos) {
                    if (producto.getCantidadDisponibles() > 0 && producto.getAutor().equals(autorN) && producto.getTipo().equals("Novela")) {
                        System.out.println(producto.toStringAutor());
                    }
                }
                break;
        }
    }
    public static void gestionarMaterial(MySqlOperation mySqlOperation){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Por favor, digite el numero de opción:%n" +
                "1. Agregar libro%n" +
                "2. Eliminar libro%n" +
                "3. Actualizar libro%n" +
                "4. Obtener libro%n" +
                "5. Salir%n");
        int seleccion = Integer.parseInt(scanner.nextLine());
        switch (seleccion) {
            case 1:
                insertProduct(mySqlOperation);
                break;
            case 2:
                deleteProduct(mySqlOperation);
                break;
            case 3:
                updateProduct(mySqlOperation);
                break;
            case 4:
                obtainProduct(mySqlOperation);
                break;
        }

    }
}

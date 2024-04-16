package Garcia.Juan.CRUD;

import Garcia.Juan.database.mysql.MySqlOperation;

import java.sql.SQLException;
import java.util.Scanner;

public class CrudProductoAsistente {
    private static MySqlOperation mySqlOperation;

    public CrudProductoAsistente(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    private static final String INSERT_PRODUCT = "INSERT INTO bibliotecapingu.producto (titulo,tipo, autor,numero_pag,cant_ejemplares,cant_prestados, area_genero) VALUES ('%s','%s', '%s','%s','%s','%s','%s')";
    private static final String DELETE_PRODUCT = "DELETE FROM bibliotecapingu.producto where titulo='%s'";
    private static final String OBTAIN_PRODUCT = "DELETE FROM bibliotecapingu.producto where titulo='%s'";
    private static final String UPDATE_PRODUCT = "UPDATE libro SET titulo = '%s', autor = '%s' area_conocimiento = '%s', nu_paginas = '%s', cantidad_ejemplares = '%s', cantidad_prestados = '%s', cantidad_disponibles = '%s', area_genero = '%s'  WHERE titulo = '%s'";

    public static void insertProduct(MySqlOperation mySqlOperation){
        String statement = obtenerInfoProduct();
        mySqlOperation.setSqlStatement(statement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static String obtenerInfoProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserte el titulo del producto: ");
        String titulo = scanner.nextLine();
        System.out.println("Inserte el tipo de producto: ");
        String tipo = scanner.nextLine();
        System.out.print("Ingrese el autor del producto: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el numero de paginas del producto: ");
        String numeroPaginas = scanner.nextLine();
        System.out.print("Ingrese el numero de ejemplares del producto: ");
        int cantidadEjemplares = Integer.parseInt(scanner.nextLine());
        int cantidadPrestados = 0;
        System.out.print("Ingrese el area/genero del producto: ");
        String area = scanner.nextLine();
        return String.format(INSERT_PRODUCT, titulo, tipo, autor, numeroPaginas ,cantidadEjemplares, cantidadPrestados,area);
    }

    protected static String PreguntarAlUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Inserte el titulo del libro: ");
        String titulo = scanner.nextLine();
        return titulo;
    }
    public static void deleteProduct(MySqlOperation mySqlOperation){
        String deleteStatement = String.format(DELETE_PRODUCT,PreguntarAlUsuario());
        mySqlOperation.setSqlStatement(deleteStatement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void obtainProduct(MySqlOperation mySqlOperation){
        String obtainStatement = String.format(OBTAIN_PRODUCT,PreguntarAlUsuario());
        mySqlOperation.setSqlStatement(obtainStatement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void updateProduct(MySqlOperation mySqlOperation){
        String updateStatement = PreguntarAlUsuarioUpdate(PreguntarAlUsuario());
        mySqlOperation.setSqlStatement(updateStatement);
        mySqlOperation.executeSqlStatementVoid();
    }

    protected static String PreguntarAlUsuarioUpdate(String titulo) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Inserte el nuevo titulo del libro: ");
        String tituloNuevo = scanner.nextLine();
        System.out.printf("Inserte el nuevo autor del libro: ");
        String autor = scanner.nextLine();
        System.out.printf("Inserte el área de conocimiento nueva: ");
        String areaConocimiento = scanner.nextLine();
        System.out.printf("Inserte el nuevo numero de pagias: ");
        int nuPaginas = Integer.parseInt(scanner.nextLine());
        System.out.printf("Inserte el nuevo numero de ejemplares: ");
        int cantEjemplares = Integer.parseInt(scanner.nextLine());
        System.out.printf("Inserte el nuevo numero de ejemplares disponibles: ");
        int cantDisp = Integer.parseInt(scanner.nextLine());
        int cantPrest =cantEjemplares-cantDisp;
        System.out.print("Ingrese el area/genero del producto: ");
        String area = scanner.nextLine();
        return String.format(UPDATE_PRODUCT, tituloNuevo,areaConocimiento,nuPaginas,cantEjemplares,cantPrest,cantDisp,titulo,area);
    }
}

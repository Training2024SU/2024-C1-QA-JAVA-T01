package org.example.integration.database.mysql;

public class MySqlConstants {
    public static final String MY_SQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String CONNECTION_STRING = "jdbc:mysql://%s/%s?user=%s&password=%s";
    public static final String SERVER = "localhost:3306";
    public static final String SELECT = "SELECT * FROM libreria_pinguinera.%s";
    public static final String CREATE_USUARIO = "INSERT INTO libreria_pinguinera.usuario VALUES ('%s', '%s', '%s')";
    public static final String CREATE_PUBLICACION = "INSERT INTO libreria_pinguinera.publicacion VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
    public static final String CREATE_PRESTAMO = "INSERT INTO libreria_pinguinera.prestamo (fecha_prestamo, fecha_devolucion, estado, correo_usuario, titulo_publicacion) VALUES ( '%s', '%s', '%s', '%s', '%s')";
    public static final String CREATE_EMPLEADO = "INSERT INTO libreria_pinguinera.empleado  VALUES ('%s', '%s', '%s', '%s')";
    public static final String DELETE_PUBLICACION="DELETE FROM libreria_pinguinera.publicacion WHERE titulo = '%s'";
    public static final String DELETE_EMPLEADO="DELETE FROM libreria_pinguinera.empleado WHERE correo = '%s'";
    public static final String UPDATE_PULICACION = "UPDATE libreria_pinguinera.publicacion SET tipo_publicacion='%s', autor='%s', num_paginas='%s'," +
            "cant_ejemplares='%s', cant_prestados='%s', cant_disponible='%s' WHERE titulo='%s'";
    public static final String UPDATE_PRESTAMO = "UPDATE libreria_pinguinera.prestamo SET fecha_prestamo='%s', fecha_devolucion='%s', estado='%s'," +
            "titulo_publicacion='%s' WHERE idPrestamo = '%s'";
    public static final String PRESTAMO="prestamo";
}

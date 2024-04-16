package co.com.ejercicio.conexionBd.constantesCRUD;

public class QueryConstante {

    //Query a Tabla Publicacion en la BD
    public static final String SELECT_ALL_FROM_PUBLICACIONES = "SELECT * FROM publicacion";

    public static final String SELECT_ALL_AUTOR_FROM_PUBLICACIONES = "SELECT * FROM publicacion WHERE nombre_autor = ? " ;
    public static final String INSERT_PUBLICACIONES = "INSERT INTO publicacion (Titulo, Tipo_publicacion, numero_pagina, cantidad_ejemplares, cantidad_prestado, cantidad_disponible, autor_idAutor) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PUBLICACIONES = "UPDATE publicacion SET Tipo_publicacion = ?, Numero_pagina = ?, cantidad_ejemplares = ?, cantidad_prestado = ?, cantidad_disponible = ?, autor_idAutor = ? WHERE titulo = ?";
    public static final String DELETE_FROM_PUBLICACIONES = "DELETE FROM publicacion WHERE titulo = ?";


    //Query a Tabla Prestamo en la BD

    public static final String INSERT_PRESTAMO = "INSERT INTO prestamo (idPrestamo, fecha_prestamo, fecha_devolucion, estado, usuario_correo, Publicacion_titulo) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String DELETE_FROM_PRESTAMO = "DELETE FROM prestamo WHERE idPrestamo = ?";

    public static final String SELECT_ALL_FROM_PRESTAMO = "SELECT * FROM prestamo";
    public static final String UPDATE_PRESTAMO = "UPDATE prestamo SET fecha_prestamo = ?, fecha_devolucion = ?, estado = ?, usuario_correo = ?, publicacion_titulo = ? WHERE idPrestamo = ?";

    //Query a Tabla Usuario en la BD

    public static final String SELECT_ALL_FROM_USUARIO = "SELECT * FROM usuario";
    public static final String INSERT_USUARIO = "INSERT INTO usuario (correo, nombre, contrasenia) VALUES (?, ?, ?)";

    public static final String UPDATE_USUARIO = "UPDATE usuario SET nombre = ?, contrasenia = ? WHERE titulo = ?";
    public static final String DELETE_FROM_USUARIO = "DELETE FROM usuario WHERE correo = ?";

    public static final String SELECT_LOGUIN_USER = "SELECT * FROM usuario WHERE correo = ? AND contrasenia = ?";

    public static final String SELECT_REGISTRO_USUARIO = "SELECT * FROM usuario WHERE correo = ?";

    //Query a Tabla Empleado en la BD

    public static final String INSERT_EMPLEADO = "INSERT INTO empleado (idEmpleado, nombre, correo, contrasenia, rol) VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_FROM_EMPLEADO = "DELETE FROM empleado WHERE idEmpleado = ?";

    public static final String SELECT_LOGUIN_ASISTENTE = "SELECT * FROM empleado WHERE correo = ? AND contrasenia = ?";
    public static final String SELECT_ALL_FROM_EMPLEADO = "SELECT * FROM empleado";

}

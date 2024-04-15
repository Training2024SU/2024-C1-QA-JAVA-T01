package co.com.sofka.menu;

public class ConstantesMenu {

    // Mensajes basicos del menú.
    public static String MENSAJE_BIENVENIDA = "Bienvenido a Libreria La Pinguinera...\nSeleccione una opcion\n1. Ingresar\n2. Registrarse\n0. Salir";
    public static String MENSAJE_DESPEDIDA = "Gracias por usar la aplicacion";
    static String OPCION_NO_VALIDA = "Opcion no valida, por favor seleccione una opcion correcta";
    static String MENSAJE_INGRESO_EXITOSO = "Ingreso exitoso, bienvenido ";
    static String MENSAJE_INGRESO_FALLIDO = "Usuario o contraseña incorrectos";
    static String MENSAJE_MENU_CUALQUIER_ROL = "Seleccione una de las siguientes opciones, presione 0 para cerrar la aplicacion";

    //Opciones del menu segun el rol de usuario
    static String OPCIONES_ADMINISTRADOR = "1.Crear Asistentes\n2.Listar Asistentes\n";
    static String OPCIONES_LECTOR = "1.Listar Autores de Libros\n2.Listar Autores de Novelas \n3.Listar Libros disponibles \n4.Listar Novelas disponibles \n5.Realizar Prestamo Libro \n6.Realizar Prestamo Novela";
    static String OPCIONES_ASISTENTE = "1.Crear Libro \n2.Crear Novela\n3.Listar Autores de Libros\n4.Listar Autores de Novelas\n5.Listar Libros por Autor\n6.Listar Novelas por Autor\n7.Listar Prestamos solicitados \n8.Realizar prestamos solicitados \n9.Listar prestamos realizados \n10.Finalizar pestamos realizados \n11.Modificar un libro \n12.Modificar una novela \n13.Borrar Libro \n14.Borrar Novela";


    //Mensajes de ingreso
    static String PRIMER_MENSAJE_INGRESO = "Ingreso a la aplicacion...\nPor favor ingrese su correo";
    static String SEGUNDO_MENSAJE_INGRESO = "Por favor ingrese su contraseña";

    //Mensajes de registro
    static String PRIMER_MENSAJE_REGISTRO = "Registro de usuario ...\nPor favor ingrese el nombre";
    static String SEGUNDO_MENSAJE_REGISTRO = "Por favor ingrese el correo";
    static String TERCER_MENSAJE_REGISTRO = "Por favor ingrese la contraseña";

    //Mensajes para guardar libro
    static String PRIMER_MENSAJE_GUARDAR_LIBRO = "Guardar libro ...\nPor favor ingrese el titulo del libro a guardar";
    static String SEGUNDO_MENSAJE_GUARDAR_LIBRO = "Por favor ingrese el nombre del autor del libro";
    static String TERCER_MENSAJE_GUARDAR_LIBRO = "Por favor ingrese la cantidad de ejemplares del libro";
    static String CUARTO_MENSAJE_GUARDAR_LIBRO = "Por favor ingrese el area del conocimiento del libro";
    static String QUINTO_MENSAJE_GUARDAR_LIBRO = "Por favor ingrese el numero de paginas del libro";
    public static String SEXTO_MENSAJE_GUARDAR_LIBRO = "Se ha guardado el libro exitosamente";
    public static String SEPTIMO_MENSAJE_MODIFICAR_LIBRO = "Se ha modificado el libro exitosamente";

    // Mensajes para modificar libro
    static String PRIMER_MENSAJE_MODIFICAR_LIBRO = "Ingrese el id del libro";
    static String PRIMER_MENSAJE_BUSCAR_LIBRO_POR_AUTOR = "Ingrese el nombre del autor del libro";

    // Mensajes para prestar libro
    static String PRIMER_MENSAJE_PRESTAR_LIBRO = "Ingrese el titulo del libro";
    static String SEGUNDO_MENSAJE_PRESTAR_LIBRO = "Tu solicitud de prestamo ha sido aceptada. Por favor, dirigete a uno de nuestros asesores para completar el proceso.";

    //Mensajes para guardar novela
    static String PRIMER_MENSAJE_GUARDAR_NOVELA = "Guardar Novela ...\nPor favor ingrese el titulo de la novela a guardar";
    static String SEGUNDO_MENSAJE_GUARDAR_NOVELA = "Por favor ingrese el nombre del autor de la novela";
    static String TERCER_MENSAJE_GUARDAR_NOVELA = "Por favor ingrese la cantidad de ejemplares de la novela";
    static String CUARTO_MENSAJE_GUARDAR_NOVELA = "Por favor ingrese el genero de la novela";
    static String QUINTO_MENSAJE_GUARDAR_NOVELA = "Por favor ingrese la edad de lectura sugerida de la novela";
    public static String SEXTO_MENSAJE_GUARDAR_NOVELA = "Se ha guardado la novela exitosamente";
    public static String SEPTIMO_MENSAJE_MODIFICAR_NOVELA = "Se ha modificado la novela exitosamente";

    // Mensajes para modificar novela
    static String PRIMER_MENSAJE_MODIFICAR_NOVELA = "Ingrese el id de la novela";
    static String PRIMER_MENSAJE_BUSCAR_NOVELA_POR_AUTOR = "Ingrese el nombre del autor de la novela";

    // Mensajes para prestar novela
    static String PRIMER_MENSAJE_PRESTAR_NOVELA = "Ingrese el titulo de la novela";
    static String SEGUNDO_MENSAJE_PRESTAR_NOVELA = "Tu solicitud de prestamo ha sido aceptada. Por favor, dirigete a uno de nuestros asesores para completar el proceso.";


   //Mensajes para prestamos

    static String PRIMER_MENSAJE_PRESTAMO_SOLICITADO = "Ingrese el id del prestamo para realizarlo";
    static String PRIMER_MENSAJE_PRESTAMO_REALIZADO = "Listando prestamos en estado REALIZADO \nIngrese el correo del usuario";
    static String PRIMER_MENSAJE_PRESTAMO_FINALIZADO = "Ingrese el id del prestamo para finalizarlo";
    static String SEGUNDO_MENSAJE_PRESTAMO_FINALIZADO ="Prestamo finalizado con exito";
}

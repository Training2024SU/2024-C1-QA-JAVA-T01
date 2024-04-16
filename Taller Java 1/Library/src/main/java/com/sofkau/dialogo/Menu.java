package com.sofkau.dialogo;

import java.sql.SQLOutput;

import static com.sofkau.dialogo.ConstantesMenu.*;

public class Menu {

    public static void menuPrincipal() {
        // CustomLogger.info(MSN_UNO);
        System.out.println(MSN_UNO);
        System.out.println(MSN_CINCO);
        System.out.println(MSN_DOS);
        System.out.println(MSN_TRES);
        System.out.println(MSN_CUATRO);


    }

    public static void menuEmpleado() {
        System.out.println(MSNE_TRES);
        System.out.println(MSNE_UNO);
        System.out.println(MSNE_DOS);
        System.out.println(MSN_CUATRO);
    }
    public static void menuLoguearseEmpleado() {
        System.out.println(MSNE_TRES);
        System.out.println(MSNE_CUATRO);
        System.out.println(MSN_CINCO);
    }

    public static void menuUsuario() {
        System.out.println(MSN_BIENVENIDA);
        System.out.println(MSN_USUARIO_BUSCAR_LIBRO);
        System.out.println(MSN_USUARIO_BUSCAR_LIBROS_POR_AUTOR);
        System.out.println(MSN_CREATE_PRESTAMO);

    }

}

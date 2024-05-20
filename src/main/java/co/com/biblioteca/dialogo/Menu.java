package co.com.biblioteca.dialogo;

import static co.com.biblioteca.dialogo.ConstantesMenu.*;

public class Menu {
    public static void menuPrincipal() {
        System.out.println(MSJ_UNO);
        System.out.println(MSJ_DOS);
        System.out.println(MSJ_TRES);
        System.out.println(MSJ_CUATRO);
        System.out.println(MSJ_CINCO);
    }

    public static void menuUsuarioLogeado() {
        System.out.println(MSJ_UNO_LOG);
        System.out.println(MSJ_DOS_LOG);
        System.out.println(MSJ_TRES_LOG);
        System.out.println(MSJ_CUATRO_LOG);
        System.out.println(MSJ_CINCO_LOG);
        System.out.print(MSJ_SEIS_LOG);
    }

    public static void menuLibro() {
        System.out.println(LISTAR);
        System.out.println(AGREGAR);
        System.out.println(ACTUALIZAR);
        System.out.println(ELIMINAR);
        System.out.println(BUSCAR);
        System.out.println(VOLVER);
        System.out.println(INGRESAR_OPCION);
        }
    }


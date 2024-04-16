package com.sofkau.dialogo;

import static com.sofkau.dialogo.ConstantesMenu.*;
import static com.sofkau.dialogo.ConstantesRegistroUsuario.*;

public class Menu {

    public static void menuPrincipal(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_INICIOUSER);
        System.out.println(ConstantesMenu.MSN_INICIOEMP);
        System.out.println(ConstantesMenu.MSN_REGISTROUSUARIO);
        System.out.println(ConstantesMenu.MSN_SALIR);
    }

    public static void menuAdministrador(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_CREARPROPIETARIO);
        System.out.println(ConstantesMenu.MSN_CREARASIS);
    }

    public static void ingresoEmpleadoAdmin(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_CREARPROPIETARIO);
        System.out.println(ConstantesMenu.MSN_CREARASIS);
    }

    public static void ingresoPropietario(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_CREARASISP);
    }

    public static void ingresoAsistente(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_CREARLIBRO);
        System.out.println(ConstantesMenu.MSN_CREARNOVELA);
        System.out.println(ConstantesMenu.MSN_ACTUALIZARLIBRO);
        System.out.println(ConstantesMenu.MSN_ACTUALIZARNOVELA);
    }

    public static void prestamo(){
        System.out.println(MSN_PRESTAMO);
    }

    public static void ingresoFechaDevolucion(){
        System.out.println(MSN_FECHADEVOLUCION);
    }

    public static void ingresoLibro(){
        System.out.println(ConstantesMenu.MSN_INGRESILIBRO);
    }

    public static void ingresoNovela(){
        System.out.println(ConstantesMenu.MSN_INGRESNOVELA);
    }

    public static void actualizacionNovela(){
        System.out.println(MSN_ACTUALIZARNOVELA);
    }

    public static void actualizacionLibro(){
        System.out.println(MSN_ACTUALIZARLIBRO);
    }

    public static void ingresoTitulo(){
        System.out.println(MSN_TITULO);
    }

    public static void ingresoAutor(){
        System.out.println(MSN_ID_AUTOR);
    }
    public static void ingresoNumPaginas(){
        System.out.println(MSN_NUM_PAGINAS);
    }

    public static void ingresoCantEjemplar(){
        System.out.println(MSN_CANT_EJEMPLARES);
    }

    public static void ingresoCantPrestado(){
        System.out.println(MSN_CANT_PRESTADOS);
    }

    public static void ingresoAreaConocimiento(){
        System.out.println(MSN_AREACONOCIMIENTO);
    }

    public static void ingresoGenero(){
        System.out.println(MSN_GENERO);
    }

    public static void ingresoEdadSugerida(){
        System.out.println(MSN_EDADSUGERIDA);
    }

    public static void tituloActualizar() {
        System.out.println(MSN_TITULOACTULIZAR);
    }

    public static void nombre(){
        System.out.println(ConstantesMenu.MSN_NOMBREU );
    }

    public static void correo(){
        System.out.println(ConstantesMenu.MSN_CORREOU );
    }

    public static void contrasena(){
        System.out.println(ConstantesMenu.MSN_CONTRASENAU );
    }

    public static void menuUsuario() {
        System.out.println(MSN_REALIZARPRESTAMO);
        System.out.println(MSN_LISTARLIBROSU);
        System.out.println(MSN_LISTARNOVELASU);
        System.out.println(MSN_LISTARAUTORESU);
        System.out.println(MSN_LISTARPUBLICACIONAUTOR);
    }




}

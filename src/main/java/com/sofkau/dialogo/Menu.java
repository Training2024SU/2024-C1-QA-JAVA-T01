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

    public static void nombre(){
        System.out.println(ConstantesMenu.MSN_NOMBREU );
    }

    public static void correo(){
        System.out.println(ConstantesMenu.MSN_CORREOU );
    }

    public static void contrasena(){
        System.out.println(ConstantesMenu.MSN_CONTRASENAU );
    }



}

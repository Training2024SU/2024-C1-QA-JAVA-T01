package com.sofkau.dialogo;

import static com.sofkau.dialogo.ConstantesMenu.*;
import static com.sofkau.dialogo.ConstantesRegistroUsuario.*;

public class Menu {

    public static void menuPrincipal(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_INICIO);
        System.out.println(ConstantesMenu.MSN_REGISTROUSUARIO);
        System.out.println(ConstantesMenu.MSN_SALIR);
    }

    public static void nombreUsuario(){
        System.out.println(ConstantesMenu.MSN_NOMBREU );
    }

    public static void correoUsuario(){
        System.out.println(ConstantesMenu.MSN_CORREOU );
    }

    public static void contrasenaUsuario(){
        System.out.println(ConstantesMenu.MSN_CONTRASENAU );
    }

}

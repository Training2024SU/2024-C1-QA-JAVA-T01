package co.com.ejercicio.menu.gestionMenu;

import static co.com.ejercicio.menu.dialogos.MenuGestionPublicacion.menuPublicacion;
import static co.com.ejercicio.menu.gestionMenu.GestionEmpleado.mostrarMenuGestionEmpleado;
import static co.com.ejercicio.menu.gestionMenu.GestionPublicacion.mostrarMenuGestionPublicacion;
import static co.com.ejercicio.menu.gestionMenu.GestionUsuario.mostrarMenuGestionUsuarios;
import static co.com.ejercicio.menu.gestionMenu.GestionPrestamo.mostrarMenuGestionPrestamo;

public class GestionAdministrador {
    public static void mostrarMenuAdministrador() {

    }

        public  static  void gestionarEmpleados(){
            System.out.println(" ");
            System.out.println("Gestionando Empleado...");
            mostrarMenuGestionEmpleado();
        }
    public static void gestionarUsuarios() {
        //gestionar usuarios
        System.out.println(" ");
        System.out.println("Gestionando usuarios...");
        mostrarMenuGestionUsuarios();
    }

    public static void gestionarPublicacion(){
         //gestionar libros
        System.out.println("Gestionando Publicaciones...");
        menuPublicacion();
    }

    public static void gestionarPrestamos() {
        //gestionar préstamos
        System.out.println("Gestionando préstamos...");
        mostrarMenuGestionPrestamo();
    }
}

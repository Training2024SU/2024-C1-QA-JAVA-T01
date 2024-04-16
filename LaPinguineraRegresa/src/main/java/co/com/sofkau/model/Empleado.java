package co.com.sofkau.model;

import co.com.sofkau.integration.database.mysql.MySqlOperation;

import java.util.ArrayList;

public class Empleado {
    private  int id;
    private  String nombre;
    private  String correo;
    private  String Clave;
    private  String rol;

    private static MySqlOperation mySqlOperation ;

    public static ArrayList<Empleado> empleados = new ArrayList<>();

    public Empleado() {
    }
    
    public Empleado(int id, String nombre, String correo, String Clave, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.Clave = Clave;
        this.rol = rol;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public static ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public static MySqlOperation getMySqlOperation() {
        return mySqlOperation;
    }

    public static void setMySqlOperation(MySqlOperation mySqlOperation) {
        Empleado.mySqlOperation = mySqlOperation;
    }


}

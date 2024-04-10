package co.com.sofka.servicio;

import co.com.sofka.modelo.Usuario;
import co.com.sofka.repositorio.RepositorioUsuario;

public class ServicioUsuario {

    private RepositorioUsuario repositorioUsuario;

    public ServicioUsuario(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    public Usuario ingresarUsuario(String correo, String contrasena){
        return this.repositorioUsuario.obtenerPorCorreoYContrasena(correo, contrasena);
    }

}

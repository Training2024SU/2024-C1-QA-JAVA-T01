package co.com.sofka.servicio;

import co.com.sofka.util.RolUsuario;
import co.com.sofka.modelo.Usuario;
import co.com.sofka.repositorio.RepositorioUsuario;
import java.util.logging.Logger;
public class ServicioUsuario {

    private RepositorioUsuario repositorioUsuario;

    public ServicioUsuario(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    public void crearUsuarioLector(String nombre, String correo, String contrasena){
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setRol(RolUsuario.LECTORES);

        repositorioUsuario.guardar(usuario);
        System.out.println("Usuario lector creado exitosamente");
    }

    public void crearUsuarioAsistente(String nombre, String correo, String contrasena){
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setRol(RolUsuario.ASISTENTE);

        repositorioUsuario.guardar(usuario);
        System.out.println("Usuario asistente creado exitosamente");
    }

    public void listarUsuariosAsistentes(){
        repositorioUsuario.listarUsuarios()
                .stream()
                .filter(usuario -> usuario.getRol() == RolUsuario.ASISTENTE)
                .forEach(usuario -> System.out.println("Nombre: "+ usuario.getNombre() + " y correo " + usuario.getCorreo()));
    }

}

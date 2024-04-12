package co.com.sofka.servicio;

import co.com.sofka.modelo.*;
import co.com.sofka.repositorio.RepositorioLibro;
import co.com.sofka.repositorio.RepositorioNovela;
import co.com.sofka.repositorio.RepositorioPrestamo;
import co.com.sofka.util.EstadoPrestamo;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class ServicioPrestamo {

    private RepositorioPrestamo repositorioPrestamo;
    private RepositorioLibro repositorioLibro;

    private RepositorioNovela repositorioNovela;

    public ServicioPrestamo(RepositorioPrestamo repositorioPrestamo, RepositorioLibro repositorioLibro, RepositorioNovela repositorioNovela){
        this.repositorioPrestamo = repositorioPrestamo;
        this.repositorioLibro = repositorioLibro;
        this.repositorioNovela = repositorioNovela;
    }

    public void solicitarPrestamoLibro(Usuario usuario, String tituloDeLibro){
        try {
            Libro libro = repositorioLibro.listarLibros().stream()
                    .filter(libro1 -> tituloDeLibro.equals(libro1.getTitulo()))
                    .findFirst().orElseThrow();

            if(!libro.sePuedePrestar()){
                System.out.println("Libro con id" + libro.getTitulo() + "no esta disponible");
                return;
            }

            Prestamo prestamo = new Prestamo();
            prestamo.setUsuario(usuario);
            prestamo.setMaterial_id(libro.getId());
            prestamo.setEstadoPrestamo(EstadoPrestamo.SOLICITADO);

            System.out.println(prestamo);
            repositorioPrestamo.guardar(prestamo);
        } catch (NoSuchElementException e){
            System.out.println("Libro con nombre " + tituloDeLibro + "no existe.");
            return;
        }
    }

    public void solicitarPrestamoNovela(Usuario usuario, String tituloDeNovela){
        try {
            Novela novela = repositorioNovela.listarNovelas().stream()
                    .filter(novela1 -> tituloDeNovela.equals(novela1.getTitulo()))
                    .findFirst().orElseThrow();

            if(!novela.sePuedePrestar()){
                System.out.println("Novela con id" + novela.getTitulo() + "no esta disponible");
                return;
            }

            Prestamo prestamo = new Prestamo();
            prestamo.setUsuario(usuario);
            prestamo.setMaterial_id(novela.getId());
            prestamo.setEstadoPrestamo(EstadoPrestamo.SOLICITADO);

            System.out.println(prestamo);
            repositorioPrestamo.guardar(prestamo);
        } catch (NoSuchElementException e){
            System.out.println("Novela con nombre " + tituloDeNovela + "no existe.");
            return;
        }
    }

    public void listarPrestamosSolicitados(String correoUsuario){
        repositorioPrestamo.listarPrestamos().stream()
                .filter(prestamo -> correoUsuario.equals(prestamo.getUsuario().getCorreo()))
                .filter(prestamo -> EstadoPrestamo.SOLICITADO == prestamo.getEstadoPrestamo())
                .forEach(prestamo -> System.out.println(prestamo));
    }

    public void listarPrestamosRealizados(String correoUsuario){
        repositorioPrestamo.listarPrestamos().stream()
                .filter(prestamo -> correoUsuario.equals(prestamo.getUsuario().getCorreo()))
                .filter(prestamo -> EstadoPrestamo.REALIZADO == prestamo.getEstadoPrestamo())
                .forEach(prestamo -> System.out.println(prestamo));
    }

    public void realizarPrestamo(Long prestamoId){
        Prestamo prestamo = repositorioPrestamo.obtenerPrestamo(prestamoId);
        if(prestamo == null){
            System.out.println("Prestamo no existe");
            return;
        }

        prestamo.setFechaDePrestamo(LocalDateTime.now());
        prestamo.setFeachaDeEntrega(LocalDateTime.now().plusDays(15));
        prestamo.setEstadoPrestamo(EstadoPrestamo.REALIZADO);

        Libro libroDelPrestamo = repositorioLibro.obtenerLibro(prestamo.getMaterial_id());
        if(libroDelPrestamo != null){
            libroDelPrestamo.setCantidadPrestados(libroDelPrestamo.getCantidadPrestados() + 1);
            repositorioLibro.modificar(libroDelPrestamo);
        } else {
            Novela novelaDelPrestamo = repositorioNovela.obtenerNovela(prestamo.getMaterial_id());
            novelaDelPrestamo.setCantidadPrestados(novelaDelPrestamo.getCantidadPrestados() + 1);
            repositorioNovela.modificar(novelaDelPrestamo);
        }

        repositorioPrestamo.modificar(prestamo);
    }

    public void realizarDevolucion(Long prestamoId){
        Prestamo prestamo = repositorioPrestamo.obtenerPrestamo(prestamoId);
        if(prestamo == null){
            System.out.println("Prestamo no existe");
            return;
        }

        if(LocalDateTime.now().isAfter(prestamo.getFeachaDeEntrega())){
            System.out.println("Lector devuelve el libro despues de la fecha de entrega");
        }

        prestamo.setEstadoPrestamo(EstadoPrestamo.FINALIZADO);

        Libro libroDelPrestamo = repositorioLibro.obtenerLibro(prestamo.getMaterial_id());

        if(libroDelPrestamo != null){
            libroDelPrestamo.setCantidadPrestados(libroDelPrestamo.getCantidadPrestados() -1);
            repositorioLibro.modificar(libroDelPrestamo);
        } else {
            Novela novelaDelPrestamo = repositorioNovela.obtenerNovela(prestamo.getMaterial_id());
            novelaDelPrestamo.setCantidadPrestados(novelaDelPrestamo.getCantidadPrestados() - 1);
            repositorioNovela.modificar(novelaDelPrestamo);
        }
        repositorioPrestamo.modificar(prestamo);
    }
}

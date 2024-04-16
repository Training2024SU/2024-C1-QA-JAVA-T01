package co.com.sofka.servicio;

import co.com.sofka.modelo.*;
import co.com.sofka.repositorio.RepositorioLibro;
import co.com.sofka.repositorio.RepositorioNovela;
import co.com.sofka.repositorio.RepositorioPrestamo;
import co.com.sofka.util.EstadoPrestamo;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.logging.Logger;


public class ServicioPrestamo {
    private RepositorioPrestamo repositorioPrestamo;
    private RepositorioLibro repositorioLibro;
    private RepositorioNovela repositorioNovela;
    private final Logger logger = Logger.getLogger(ServicioPrestamo.class.getName());

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
                System.out.println("Libro: " + libro.getTitulo() + "no esta disponible");
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

    public void listarPorCorreoYEstado(String correoUsuario, EstadoPrestamo estadoPrestamo){
        repositorioPrestamo.listarPrestamos().stream()
                .filter(prestamo -> correoUsuario.equals(prestamo.getUsuario().getCorreo()))
                .filter(prestamo -> estadoPrestamo == prestamo.getEstadoPrestamo())
                .forEach(System.out::println);
    }

    public void realizarPrestamo(Long prestamoId){
        Prestamo prestamo = repositorioPrestamo.obtenerPrestamo(prestamoId);
        if(prestamo == null){
            System.out.println("Prestamo no existe");
            return;
        }

        prestamo.setFechaDePrestamo(LocalDateTime.now());
        prestamo.setFechaDeEntrega(LocalDateTime.now().plusDays(15));
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

        public void realizarDevolucion(Long prestamoId) {
            Prestamo prestamo = repositorioPrestamo.obtenerPrestamo(prestamoId);
            if (prestamo == null) {
                System.out.println("Prestamo no existe");
                return;
            }

            if (LocalDateTime.now().isAfter(prestamo.getFechaDeEntrega())) {
                logger.warning("Lector hace devolucion, despues de la fecha de entrega");
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

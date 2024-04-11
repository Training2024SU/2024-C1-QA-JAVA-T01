package co.com.pinguinera.interfaces;

import co.com.pinguinera.modelos.Novela;

import java.util.List;

public interface NovelaRepositorio {
    void agregarNovela(Novela novela);
    void actualizarNovela(Novela novela);
    void eliminarNovela(int novelaId);
    List<Novela> obtenerTodasLasNovelas();
    List<Novela> buscarNovelaPorTitulo(String titulo);
    List<String> listarAutoresDeNovelas();
}

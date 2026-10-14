package pe.uni.repositorio;

import java.util.List;

public interface Repositorio<T> {
    void guardar(String id, T item);
    T buscarPorId(String id);
    List<T> listarTodos();
    boolean existe(String id);
}

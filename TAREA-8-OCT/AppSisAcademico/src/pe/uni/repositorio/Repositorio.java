package pe.uni.repositorio;

import java.util.List;

/**
 * Contrato para guardar y buscar elementos de tipo T por una clave.
 */
public interface Repositorio<T> {
    // Comando
    void guardar(String clave, T elemento);

    // Consultas
    T obtener(String clave);

    List<T> listar();
}

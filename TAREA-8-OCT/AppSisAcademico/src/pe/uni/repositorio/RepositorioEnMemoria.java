package pe.uni.repositorio;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pe.uni.excepciones.ValidacionException;

/**
 * Implementación del repositorio que guarda todo en memoria.
 *
 * Función de abstracción: representa el conjunto de elementos registrados,
 * cada uno identificado por su clave y en orden de registro.
 *
 * Invariante de representación: no hay claves repetidas ni vacías,
 * y ningún elemento es null.
 */
public class RepositorioEnMemoria<T> implements Repositorio<T> {
    private final String nombreEntidad; // solo para los mensajes de error
    private final Map<String, T> elementos = new LinkedHashMap<>();

    public RepositorioEnMemoria(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    @Override
    public void guardar(String clave, T elemento) {
        if(clave == null || clave.isBlank() || elemento == null) { // protege el invariante
            throw new ValidacionException("No se puede guardar " + nombreEntidad + " sin código");
        }
        if(elementos.containsKey(clave)) {
            throw new ValidacionException("Ya existe " + nombreEntidad + " con código " + clave);
        }
        elementos.put(clave, elemento);
    }

    @Override
    public T obtener(String clave) {
        T elemento = elementos.get(clave);
        if(elemento == null) {
            throw new ValidacionException("No se encontró " + nombreEntidad + " con código " + clave);
        }
        return elemento;
    }

    @Override
    public List<T> listar() {
        return List.copyOf(elementos.values()); // copia inmutable, no se expone el mapa interno
    }
}

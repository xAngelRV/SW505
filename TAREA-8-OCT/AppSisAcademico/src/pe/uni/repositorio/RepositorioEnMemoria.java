package pe.uni.repositorio;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pe.uni.excepciones.ValidacionException;

public class RepositorioEnMemoria<T> implements Repositorio<T> {
    private final String nombreEntidad;
    private final Map<String, T> datos = new LinkedHashMap<>();

    public RepositorioEnMemoria(String nombreEntidad) {
        if (nombreEntidad == null || nombreEntidad.isBlank()) {
            throw new IllegalArgumentException("El nombre de la entidad no puede ser nulo o vacío.");
        }
        this.nombreEntidad = nombreEntidad;
        checkRep();
    }

    private void checkRep() {
        if (datos == null || nombreEntidad == null || nombreEntidad.isBlank()) {
            throw new IllegalStateException("RI violado: mapa de datos o nombre nulos.");
        }
        for (Map.Entry<String, T> entry : datos.entrySet()) {
            if (entry.getKey() == null || entry.getKey().isBlank() || entry.getValue() == null) {
                throw new IllegalStateException("RI violado: clave o valor nulos en el repositorio.");
            }
        }
    }

    @Override
    public void guardar(String id, T item) {
        if (id == null || id.trim().isEmpty()) {
            throw new ValidacionException("El identificador de " + nombreEntidad + " no puede ser nulo ni vacío.");
        }
        if (item == null) {
            throw new ValidacionException("No se permite registrar un valor nulo para " + nombreEntidad + ".");
        }
        if (datos.containsKey(id)) {
            throw new ValidacionException("Ya existe un(a) " + nombreEntidad + " con el código " + id + ".");
        }

        datos.put(id, item);
        checkRep();
    }

    @Override
    public T buscarPorId(String id) {
        if (id == null || !datos.containsKey(id)) {
            throw new ValidacionException("No se encontró " + nombreEntidad + " con código: " + id);
        }
        return datos.get(id);
    }

    @Override
    public List<T> listarTodos() {
        return List.copyOf(datos.values());
    }

    @Override
    public boolean existe(String id) {
        return id != null && datos.containsKey(id);
    }
}

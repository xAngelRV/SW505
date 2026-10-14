package pe.uni.dominio;

import pe.uni.excepciones.ValidacionException;

public record Periodo(
        String codigo,
        String descripcion) {

    public Periodo {
        if (codigo == null || codigo.isBlank()) {
            throw new ValidacionException("El código del período es obligatorio");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new ValidacionException("La descripción del período es obligatoria");
        }
    }
}
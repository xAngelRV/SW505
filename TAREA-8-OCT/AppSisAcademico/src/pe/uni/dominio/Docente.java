package pe.uni.dominio;

import pe.uni.excepciones.ValidacionException;

public record Docente(
        String codigo,
        String nombres,
        String apellidos,
        String especialidad) {

    public Docente {
        if (codigo == null || codigo.isBlank()) {
            throw new ValidacionException("El código del docente es obligatorio");
        }
        if (nombres == null || nombres.isBlank()) {
            throw new ValidacionException("Los nombres del docente son obligatorios");
        }
        if (apellidos == null || apellidos.isBlank()) {
            throw new ValidacionException("Los apellidos del docente son obligatorios");
        }
        if (especialidad == null || especialidad.isBlank()) {
            throw new ValidacionException("La especialidad del docente es obligatoria");
        }
    }

    public String nombreCompleto() {
        return nombres + " " + apellidos;
    }
}
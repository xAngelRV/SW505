package pe.uni.dominio;

import pe.uni.excepciones.ValidacionException;

public record Alumno(
        String codigo,
        String nombres,
        String apellidos,
        int edad,
        String carrera) {

    public Alumno {
        if (codigo == null || codigo.isBlank()) {
            throw new ValidacionException("El código del alumno es obligatorio");
        }
        if (nombres == null || nombres.isBlank()) {
            throw new ValidacionException("Los nombres del alumno son obligatorios");
        }
        if (apellidos == null || apellidos.isBlank()) {
            throw new ValidacionException("Los apellidos del alumno son obligatorios");
        }
        if (edad < 15 || edad > 100) {
            throw new ValidacionException("La edad del alumno debe estar entre 15 y 100 años");
        }
        if (carrera == null || carrera.isBlank()) {
            throw new ValidacionException("La carrera del alumno es obligatoria");
        }
    }

    public String nombreCompleto() {
        return nombres + " " + apellidos;
    }
}

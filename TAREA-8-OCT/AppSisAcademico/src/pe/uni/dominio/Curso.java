package pe.uni.dominio;

import pe.uni.excepciones.ValidacionException;

public record Curso(
        String codigo, 
        String nombre, 
        int creditos,
        boolean estado) {

    public Curso {
        if (codigo == null || codigo.isBlank()) {
            throw new ValidacionException("El código del curso es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new ValidacionException("El nombre del curso es obligatorio");
        }
        if (creditos <= 0) {
            throw new ValidacionException("El valor para créditos debe ser un valor mayor a cero");
        }
    }
}
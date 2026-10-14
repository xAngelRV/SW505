package pe.uni.dominio;

import pe.uni.excepciones.ValidacionException;

/**
 *
 * @author JHERENCIA
 */
public record Curso(
        String codigo, 
        String nombre, 
        int creditos,
        boolean estado  // true es curso activo, false es curso de baja
        ) {
    public Curso {
        if(codigo == null || codigo.isBlank()) {
            throw new ValidacionException("El código del curso es obligatorio");
        }
        
        if(nombre == null || nombre.isBlank()) {
            throw new ValidacionException("El nombre del curso obligatorios");
        }
        
        if(creditos <= 0) {
            throw new ValidacionException("El valor para créditos debe ser un valor mayor a cero");
        }
    }
}
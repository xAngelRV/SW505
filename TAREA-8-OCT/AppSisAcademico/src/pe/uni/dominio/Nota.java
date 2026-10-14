package pe.uni.dominio;

import pe.uni.excepciones.ValidacionException;

/**
 *
 * @author JHERENCIA
 */
public record Nota(
        Alumno alumno, 
        Curso curso, 
        Periodo periodo, 
        double calificacion) {
    public Nota {
        if(calificacion < 0 || calificacion > 20) {
            throw new ValidacionException("La calificacion debe estar entre cero y veinte");
        }
    }
    
    public boolean esAprobatoria() {
        return calificacion >= 10;
    }
    
}

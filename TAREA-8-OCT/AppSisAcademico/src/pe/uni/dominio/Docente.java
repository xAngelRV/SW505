package pe.uni.dominio;
import pe.uni.excepciones.ValidacionException;

/**
 *
 * @author JHERENCIA
 */
public record Docente(
        String codigo,  // los atributos necesarios
        String nombres,
        String apellidos,
        String especialidad) {        
    public Docente { // aquí las validaciones
        if(codigo == null || codigo.isBlank()) {
            throw new ValidacionException("El código del docente es obligatorio");
        }
        
        if(nombres == null || nombres.isBlank()) {
            throw new ValidacionException("Los nombres del docente son obligatorios");
        }
        
        if(apellidos == null || apellidos.isBlank()) {
            throw new ValidacionException("los apellidos del docente son obligatorios");
        }
        
        if(especialidad == null || especialidad.isBlank()) {
            throw new ValidacionException("El código del docente es obligatorio");
        }
    }
    
    public String nombreCompleto() {
        return nombres + " " + apellidos;
    }
    
}
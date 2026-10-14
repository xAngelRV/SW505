package pe.uni.dominio;
import pe.uni.excepciones.ValidacionException;

/**
 *
 * @author JHERENCIA
 */
public record Alumno( // estructura apara registrar datos
        String codigo,  // los atributos necesarios
        String nombres,
        String apellidos,
        int edad,
        String carrera) {
    public Alumno {  // validaciones para los argumentos
        if(codigo == null || codigo.isBlank()) {
            throw new ValidacionException("El código del alumno es obligatorio");
        }
        
        if(nombres == null || nombres.isBlank()) {
            throw new ValidacionException("Los nombres del alumno son obligatorios");
        }
        
        if(apellidos == null || apellidos.isBlank()) {
            throw new ValidacionException("los apellidos del alumno son obligatorios");
        }
        
        if(edad < 15 || edad > 100) {
            throw new ValidacionException("La edad del alumno debe estar entre 15 y 100 años");
        }
        
        if(carrera == null || carrera.isBlank()) {
            throw new ValidacionException("El código del alumno es obligatorio");
        }
    }
    
    public String nombreCompleto() {
        return nombres + " " + apellidos;
    }
    
}

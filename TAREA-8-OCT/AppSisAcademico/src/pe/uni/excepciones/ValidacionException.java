package pe.uni.excepciones;

/**
 *
 * @author JHERENCIA
 */
public class ValidacionException extends RuntimeException{
    public ValidacionException(String mensaje) {
        super(mensaje); // eleva el mensaje a su clase base
    }
}

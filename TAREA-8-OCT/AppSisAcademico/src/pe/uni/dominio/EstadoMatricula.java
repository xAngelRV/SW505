package pe.uni.dominio;

/**
 *
 * @author JHERENCIA
 */
public enum EstadoMatricula {  // Enumeración, para catalogar estados
    NO_MATRICULADO,
    ACTIVA,
    CANCELADA,
    RETIRADA,
    ANULADA;
    
    public boolean permiteCalificaciones() {
        return this == ACTIVA;
    }
}

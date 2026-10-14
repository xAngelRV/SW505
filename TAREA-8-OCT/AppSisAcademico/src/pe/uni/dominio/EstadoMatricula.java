package pe.uni.dominio;

public enum EstadoMatricula {
    NO_MATRICULADO,
    ACTIVA,
    CANCELADA,
    RETIRADA,
    ANULADA;

    public boolean permiteCalificaciones() {
        return this == ACTIVA;
    }
}

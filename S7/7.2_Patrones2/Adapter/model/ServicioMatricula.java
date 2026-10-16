package model;

public class ServicioMatricula {
    private final ProcesadorPago procesador;

    public ServicioMatricula(ProcesadorPago procesador) {
        this.procesador = procesador;
    }

    public void pagarMatricula(double monto) {
        procesador.pagar(monto);
    }
}

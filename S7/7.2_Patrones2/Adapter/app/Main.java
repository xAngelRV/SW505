package app;

import model.AdaptadorPago;
import model.ProcesadorPago;
import model.ServicioMatricula;
import sis_externo.PasarelaExterna;

public class Main {
    public static void main(String[] args) {
        PasarelaExterna pasarela = new PasarelaExterna();
        ProcesadorPago adaptador = new AdaptadorPago(pasarela);
        ServicioMatricula servicio = new ServicioMatricula(adaptador);
        servicio.pagarMatricula(850.00);
    }
}

package app;

import fachada.FachadaMatricula;

public class Main {
    public static void main(String[] args) {
        FachadaMatricula fachada = new FachadaMatricula();
        fachada.registrarMatricula(
                "20260001",
                "Construccion de Software",
                850.00);
    }
}
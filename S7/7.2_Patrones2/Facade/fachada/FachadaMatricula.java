package fachada;

import model.ServicioNotificacion;
import model.ServicioPago;
import model.ValidadorCursos;
import model.ValidadorEstudiante;
import model.RepositorioMatricula;

public class FachadaMatricula {
    private final ValidadorEstudiante validadorEstudiante;
    private final ValidadorCursos validadorCursos;
    private final ServicioPago servicioPago;
    private final RepositorioMatricula repositorio;
    private final ServicioNotificacion notificador;

    public FachadaMatricula() {
        this.validadorEstudiante = new ValidadorEstudiante();
        this.validadorCursos = new ValidadorCursos();
        this.servicioPago = new ServicioPago();
        this.repositorio = new RepositorioMatricula();
        this.notificador = new ServicioNotificacion();
    }

    public void registrarMatricula(
            String codigo,
            String curso,
            double monto) {
        validadorEstudiante.validar(codigo);
        validadorCursos.comprobarVacantes(curso);
        servicioPago.cobrar(monto);
        repositorio.guardar(codigo, curso);
        notificador.enviar("Matrícula confirmada");
    }
}

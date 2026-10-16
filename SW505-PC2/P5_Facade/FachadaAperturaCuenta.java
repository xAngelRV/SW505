public class FachadaAperturaCuenta {
    private final ValidadorIdentidad ValidadorIdentidad;
    private final ConsultaHistorial ConsultaHistorial;
    private final GeneradorCuentas GeneradorCuentas;
    private final ServicioFirmaDigital ServicioFirmaDigital;

    public FachadaAperturaCuenta() {
        this.ValidadorIdentidad = new ValidadorIdentidad();
        this.ConsultaHistorial = new ConsultaHistorial();
        this.GeneradorCuentas = new GeneradorCuentas();
        this.ServicioFirmaDigital = new ServicioFirmaDigital();
    }

    public void abrirCuenta(
            String dni,
            String nombre) {
        ValidadorIdentidad.validar(dni);
        ConsultaHistorial.comprobarDeudas(dni);
        GeneradorCuentas.generarCuenta(nombre);
        ServicioFirmaDigital.generarFirma(dni, nombre);
    }
}

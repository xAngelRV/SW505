public class PagoYape implements MetodoPago {

    @Override
    public void procesar(double monto) {
        System.out.println("[Yape] Procesando pago de S/ " + monto + " mediante numero de celular / QR.");
        System.out.println("[Yape] Pago completado con exito!");
    }
}

public class PagoTarjeta implements MetodoPago {

    @Override
    public void procesar(double monto) {
        System.out.println("[Tarjeta] Procesando pago de S/ " + monto + " con tarjeta de credito/debito.");
        System.out.println("[Tarjeta] Conectando con la pasarela bancaria... Pago aprobado!");
    }
}

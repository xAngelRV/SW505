public class Main {

    public static void main(String[] args) {
        MetodoPago pago1 = new PagoYape();
        MetodoPago pago2 = new PagoTarjeta();

        System.out.println("--- Procesando Pago 1 ---");
        pago1.procesar(1500);

        System.out.println("\n--- Procesando Pago 2 ---");
        pago2.procesar(12);
    }
}

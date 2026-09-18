package pe.empresa.pedidos.domain;

import pe.empresa.pedidos.impuesto.CalculadoraImpuesto;
import pe.empresa.pedidos.notificacion.Notificador;
import pe.empresa.pedidos.pago.MetodoPago;

public class Pedido {
    private final EstrategiaDescuento estrategiaDescuento;
    private final CalculadoraImpuesto calculadoraImpuesto;
    private final MetodoPago metodoPago;
    private final Notificador notificador;

    public Pedido(
            EstrategiaDescuento estrategiaDescuento,
            CalculadoraImpuesto calculadoraImpuesto,
            MetodoPago metodoPago,
            Notificador notificador) {
        this.estrategiaDescuento = estrategiaDescuento;
        this.calculadoraImpuesto = calculadoraImpuesto;
        this.metodoPago = metodoPago;
        this.notificador = notificador;
    }

    public double procesar(double subtotal, String contactoCliente) {
        double subtotalConDescuento = estrategiaDescuento.aplicar(subtotal);
        double impuesto = calculadoraImpuesto.calcularImpuesto(subtotalConDescuento);
        double totalFinal = subtotalConDescuento + impuesto;

        metodoPago.procesar(totalFinal);
        notificador.enviarNotificacion(contactoCliente, "Confirmacion de pedido por total S/ " + totalFinal);

        return totalFinal;
    }
}

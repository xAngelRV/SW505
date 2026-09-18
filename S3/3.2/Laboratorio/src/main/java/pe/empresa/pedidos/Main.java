package pe.empresa.pedidos;

import pe.empresa.pedidos.domain.DescuentoNavidad;
import pe.empresa.pedidos.domain.DescuentoVip;
import pe.empresa.pedidos.domain.Pedido;
import pe.empresa.pedidos.domain.SinDescuento;
import pe.empresa.pedidos.impuesto.ImpuestoArequipa;
import pe.empresa.pedidos.impuesto.ImpuestoLima;
import pe.empresa.pedidos.impuesto.ImpuestoSelva;
import pe.empresa.pedidos.notificacion.NotificadorEmail;
import pe.empresa.pedidos.notificacion.NotificadorSms;
import pe.empresa.pedidos.notificacion.NotificadorWhatsApp;
import pe.empresa.pedidos.pago.PagoTarjeta;
import pe.empresa.pedidos.pago.PagoYape;

public class Main {
    public static void main(String[] args) {
        Pedido pedidoLima = new Pedido(
                new DescuentoVip(),
                new ImpuestoLima(),
                new PagoYape(),
                new NotificadorEmail()
        );
        pedidoLima.procesar(200.0, "carlos@correo.com");

        Pedido pedidoSelva = new Pedido(
                new DescuentoNavidad(),
                new ImpuestoSelva(),
                new PagoTarjeta(),
                new NotificadorWhatsApp()
        );
        pedidoSelva.procesar(350.0, "+51987654321");

        Pedido pedidoArequipa = new Pedido(
                new SinDescuento(),
                new ImpuestoArequipa(),
                new PagoYape(),
                new NotificadorSms()
        );
        pedidoArequipa.procesar(120.0, "987654320");
    }
}

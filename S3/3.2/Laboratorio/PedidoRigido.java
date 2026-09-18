public class PedidoRigido{

    public double calcularTotal (String tipoCliente, double subtotal, String metodoPago){
        double total = subtotal;

        //Descuento segun cliente
        if (tipoCliente.equals("regular")) {
        // sin descuento
        } else if (tipoCliente.equals("vip")) {
        total = total * 0.85;
        } else if (tipoCliente.equals("navidad")) {
        total = total * 0.80;
        }

        // Procesamiento según método de pago
        if (metodoPago.equals("tarjeta")) {
        System.out.println("Cobrando S/ " + total + " con tarjeta");
        } else if (metodoPago.equals("yape")) {
        System.out.println("Cobrando S/ " + total + " con Yape");
        } else if (metodoPago.equals("efectivo")) {
        System.out.println("Cobrando S/ " + total + " en efectivo");
        }

        return total;
    }

}

/* 
1. ¿Cuántos puntos de variación hay? (McConnell, 2004 – Cap. 5)
    2 puntos de variacion (Uno de tipo de cliente y otro de metodo de pago).
    Alternativa de solucion para este codigo seria el patron Strategy.
    
2. ¿Qué pasa si el negocio agrega "descuento por Black Friday"?
3. ¿Qué pasa si se agrega "Plin" como método de pago?
4. ¿Qué pasa si el descuento VIP cambia de 15% a 20%?
5. ¿Qué pasa si el umbral de "cliente VIP" cambia según el monto? 
*/
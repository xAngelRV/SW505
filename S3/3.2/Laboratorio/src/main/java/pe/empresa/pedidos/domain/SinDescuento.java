package pe.empresa.pedidos.domain;

public class SinDescuento implements EstrategiaDescuento {
    @Override
    public double aplicar(double subtotal) {
        return subtotal;
    }

    @Override
    public String obtenerDescripcion() {
        return "Sin descuento";
    }
}

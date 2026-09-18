package pe.empresa.pedidos.domain;

public class DescuentoNavidad implements EstrategiaDescuento {
    @Override
    public double aplicar(double subtotal) {
        return subtotal * 0.80;
    }

    @Override
    public String obtenerDescripcion() {
        return "Navidad 20%";
    }
}

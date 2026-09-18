package pe.empresa.pedidos.impuesto;

public interface CalculadoraImpuesto {
    double calcularImpuesto(double subtotal);
    String obtenerRegion();
}

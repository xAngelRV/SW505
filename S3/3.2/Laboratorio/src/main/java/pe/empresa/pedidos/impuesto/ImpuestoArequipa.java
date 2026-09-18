package pe.empresa.pedidos.impuesto;

public class ImpuestoArequipa implements CalculadoraImpuesto {
    @Override
    public double calcularImpuesto(double subtotal) {
        return subtotal * 0.18;
    }

    @Override
    public String obtenerRegion() {
        return "Arequipa (18%)";
    }
}

package pe.empresa.pedidos.impuesto;

public class ImpuestoSelva implements CalculadoraImpuesto {
    @Override
    public double calcularImpuesto(double subtotal) {
        return subtotal * 0.10;
    }

    @Override
    public String obtenerRegion() {
        return "Selva (10%)";
    }
}

package pe.empresa.pedidos.impuesto;

public class ImpuestoLima implements CalculadoraImpuesto {
    @Override
    public double calcularImpuesto(double subtotal) {
        return subtotal * 0.18;
    }

    @Override
    public String obtenerRegion() {
        return "Lima (18%)";
    }
}

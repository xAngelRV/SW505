public class CalculoGeneral implements EstrategiaCalculoTributario {
    @Override
    public double calcular(double base) {
        return base * 0.20;
    }
}

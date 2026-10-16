public class CalculoRer implements EstrategiaCalculoTributario {
    @Override
    public double calcular(double base) {
        return base * 0.18;
    }
}

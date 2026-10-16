public interface EstrategiaCalculoTributario {
    public double calculoTributario(string tipo, double base){
        if (tipo.equals ("RER")){
            return 0; 
        }
        else if (tipo.equals ("RUS")){
            return base*0.18;
        }
    }
}

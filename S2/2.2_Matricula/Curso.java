public class Curso {
    private String codigo;
    private String nombre;
    private int creditos;
    private int cupoMaximo;
    private int cupoActual;

    public Curso(String codigo, String nombre, int creditos, int cupoMaximo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.cupoMaximo = cupoMaximo;
        this.cupoActual = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getCupoActual() {
        return cupoActual;
    }

    public int getCuposDisponibles() {
        return cupoMaximo - cupoActual;
    }

    public boolean tieneCupo() {
        return cupoActual < cupoMaximo;
    }


    public boolean inscribir() {
        if (tieneCupo()) {
            cupoActual++;
            return true;
        }
        return false;
    }

    public boolean desinscribir() {
        if (cupoActual > 0) {
            cupoActual--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-30s | Creditos: %d | Cupos: %d/%d",
                codigo, nombre, creditos, cupoActual, cupoMaximo);
    }
}

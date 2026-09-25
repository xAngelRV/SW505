public class ServicioAlumno {
    private final RepositorioAlumno repositorio;
    public ServicioAlumno(RepositorioAlumno r) {
        this.repositorio = r;
    }
    public void matricular(String codAlumno, String codCurso, String periodo) {
        if (!repositorio.existe(codAlumno)) {
            throw new IllegalArgumentException("El alumno " + codAlumno + " no existe.");
        }
    }
}

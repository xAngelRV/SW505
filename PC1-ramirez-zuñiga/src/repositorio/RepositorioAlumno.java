public interface RepositorioAlumno {
    void guardar(Alumno a);
    boolean existe(String codigoAlumno, String codigoCurso, String periodo);
}

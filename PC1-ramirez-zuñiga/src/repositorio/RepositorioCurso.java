public interface RepositorioCurso {
    void guardar(Curso c);
    boolean existe(String codigoAlumno, String codigoCurso, String periodo);
    List<Matricula> porPeriodo(String periodo);
    List<Matricula> porAlumno(String codigoAlumno);
    List<Matricula> porCurso(String codigoCurso);
}

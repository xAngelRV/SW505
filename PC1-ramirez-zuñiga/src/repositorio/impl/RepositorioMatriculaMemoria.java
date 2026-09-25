public interface RepositorioMatriculaMemoria {
  void guardar(Matricula m);
  boolean existe(String codigoAlumno, String codigoCurso, String periodo);
  List<Matricula> porPeriodo(String periodo);
  List<Matricula> porAlumno(String codigoAlumno);
  List<Matricula> porCurso(String codigoCurso);
}

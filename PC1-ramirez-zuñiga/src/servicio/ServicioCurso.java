import java.util.List;
import java.util.Optional;

public class ServicioCurso {
    private final RepositorioCurso repositorio;
    public ServicioCurso(RepositorioCurso r) {
        this.repositorio = r;
    }
    public Optional<Curso> buscarPorCodigo(String codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }
    public List<Curso> todos() {
        return repositorio.todos();
    }
    public void matricular(String codAlumno, String codCurso, String periodo) {
        if (!repositorio.existe(codCurso)) {
            throw new IllegalArgumentException("El curso " + codCurso + " no existe.");
        }
    }
}

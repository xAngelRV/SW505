package pe.uni.servicio;

import java.util.ArrayList;
import java.util.List;
import pe.uni.dominio.Alumno;
import pe.uni.dominio.Curso;
import pe.uni.dominio.Docente;
import pe.uni.dominio.Matricula;
import pe.uni.dominio.Periodo;
import pe.uni.excepciones.ValidacionException;
import pe.uni.repositorio.Repositorio;
import pe.uni.repositorio.RepositorioEnMemoria;

public class ServicioAcademico {
    private final Repositorio<Alumno> repoAlumnos = new RepositorioEnMemoria<>("alumno");
    private final Repositorio<Docente> repoDocentes = new RepositorioEnMemoria<>("docente");
    private final Repositorio<Curso> repoCursos = new RepositorioEnMemoria<>("curso");
    private final Repositorio<Periodo> repoPeriodos = new RepositorioEnMemoria<>("período");
    private final Repositorio<Matricula> repoMatriculas = new RepositorioEnMemoria<>("matrícula");

    private Matricula ultimaMatriculaRegistrada;

    public void registrarAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new ValidacionException("El alumno no puede ser nulo.");
        }
        repoAlumnos.guardar(alumno.codigo(), alumno);
    }

    public void registrarDocente(Docente docente) {
        if (docente == null) {
            throw new ValidacionException("El docente no puede ser nulo.");
        }
        repoDocentes.guardar(docente.codigo(), docente);
    }

    public void registrarCurso(Curso curso) {
        if (curso == null) {
            throw new ValidacionException("El curso no puede ser nulo.");
        }
        repoCursos.guardar(curso.codigo(), curso);
    }

    public void registrarPeriodo(Periodo periodo) {
        if (periodo == null) {
            throw new ValidacionException("El período no puede ser nulo.");
        }
        repoPeriodos.guardar(periodo.codigo(), periodo);
    }

    public void matricular(String codAlumno, String codCurso, String codDocente, String codPeriodo) {
        Alumno alumno = repoAlumnos.buscarPorId(codAlumno);
        Curso curso = repoCursos.buscarPorId(codCurso);
        Docente docente = repoDocentes.buscarPorId(codDocente);
        Periodo periodo = repoPeriodos.buscarPorId(codPeriodo);

        if (!curso.estado()) {
            throw new ValidacionException("No es posible matricular: el curso " + curso.codigo() + " no está activo");
        }

        verificarDuplicidadMatricula(codAlumno, codCurso, codPeriodo);

        Matricula nuevaMatricula = new Matricula(alumno, periodo, curso, docente);
        repoMatriculas.guardar(String.valueOf(nuevaMatricula.idMatricula()), nuevaMatricula);
        this.ultimaMatriculaRegistrada = nuevaMatricula;
    }

    public void registrarNota(int idMatricula, double nota) {
        Matricula matricula = buscarMatricula(idMatricula);
        matricula.registrarNota(nota);
    }

    public Matricula ultimaMatricula() {
        return ultimaMatriculaRegistrada;
    }

    public Alumno buscarAlumno(String codigo) {
        return repoAlumnos.buscarPorId(codigo);
    }

    public Periodo buscarPeriodo(String codigo) {
        return repoPeriodos.buscarPorId(codigo);
    }

    public Matricula buscarMatricula(int idMatricula) {
        return repoMatriculas.buscarPorId(String.valueOf(idMatricula));
    }

    public List<Alumno> listarAlumnos() {
        return repoAlumnos.listarTodos();
    }

    public List<Docente> listarDocentes() {
        return repoDocentes.listarTodos();
    }

    public List<Curso> listarCursos() {
        return repoCursos.listarTodos();
    }

    public List<Matricula> matriculasPorPeriodo(String codPeriodo) {
        repoPeriodos.buscarPorId(codPeriodo);
        List<Matricula> resultado = new ArrayList<>();
        for (Matricula m : repoMatriculas.listarTodos()) {
            if (m.codigoPeriodo().equalsIgnoreCase(codPeriodo)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    public List<Matricula> historialDeAlumno(String codAlumno) {
        repoAlumnos.buscarPorId(codAlumno);
        List<Matricula> historial = new ArrayList<>();
        for (Matricula m : repoMatriculas.listarTodos()) {
            if (m.codigoAlumno().equalsIgnoreCase(codAlumno)) {
                historial.add(m);
            }
        }
        return historial;
    }

    private void verificarDuplicidadMatricula(String codAlumno, String codCurso, String codPeriodo) {
        for (Matricula m : repoMatriculas.listarTodos()) {
            boolean mismoAlumno = m.codigoAlumno().equalsIgnoreCase(codAlumno);
            boolean mismoCurso = m.codigoCurso().equalsIgnoreCase(codCurso);
            boolean mismoPeriodo = m.codigoPeriodo().equalsIgnoreCase(codPeriodo);

            if (mismoAlumno && mismoCurso && mismoPeriodo && m.estaActiva()) {
                throw new ValidacionException("El alumno ya cuenta con una matrícula activa en este curso y período");
            }
        }
    }
}

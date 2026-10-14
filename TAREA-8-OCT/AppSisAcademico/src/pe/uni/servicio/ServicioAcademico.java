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

/**
 * Fachada del sistema académico: reúne las reglas y oculta los repositorios.
 * No sabe nada de la consola.
 *
 * Función de abstracción: representa el sistema académico con sus alumnos,
 * docentes, cursos, períodos y matrículas registrados.
 *
 * Invariante de representación: toda matrícula guardada apunta a un alumno,
 * curso, docente y período registrados, y no existen dos matrículas activas
 * iguales (mismo alumno, curso y período).
 */
public class ServicioAcademico {
    private final Repositorio<Alumno> alumnos = new RepositorioEnMemoria<>("alumno");
    private final Repositorio<Docente> docentes = new RepositorioEnMemoria<>("docente");
    private final Repositorio<Curso> cursos = new RepositorioEnMemoria<>("curso");
    private final Repositorio<Periodo> periodos = new RepositorioEnMemoria<>("período");
    private final Repositorio<Matricula> matriculas = new RepositorioEnMemoria<>("matrícula");
    private Matricula ultimaMatricula;

    // Comandos
    public void registrarAlumno(Alumno alumno) {
        alumnos.guardar(alumno.codigo(), alumno);
    }

    public void registrarDocente(Docente docente) {
        docentes.guardar(docente.codigo(), docente);
    }

    public void registrarCurso(Curso curso) {
        cursos.guardar(curso.codigo(), curso);
    }

    public void registrarPeriodo(Periodo periodo) {
        periodos.guardar(periodo.codigo(), periodo);
    }

    public void matricular(String codigoAlumno, String codigoCurso, String codigoDocente, String codigoPeriodo) {
        Alumno alumno = alumnos.obtener(codigoAlumno);
        Curso curso = cursos.obtener(codigoCurso);
        Docente docente = docentes.obtener(codigoDocente);
        Periodo periodo = periodos.obtener(codigoPeriodo);

        if(!curso.estado()) {
            throw new ValidacionException("El curso " + curso.codigo() + " está de baja");
        }
        validarNoMatriculado(alumno, curso, periodo);

        Matricula matricula = new Matricula(alumno, periodo, curso, docente);
        matriculas.guardar(String.valueOf(matricula.idMatricula()), matricula);
        ultimaMatricula = matricula;
    }

    public void registrarNota(int idMatricula, double calificacion) {
        buscarMatricula(idMatricula).registrarNota(calificacion);
    }

    // Consultas
    public Matricula ultimaMatricula() {
        return ultimaMatricula;
    }

    public List<Alumno> listarAlumnos() {
        return alumnos.listar();
    }

    public List<Docente> listarDocentes() {
        return docentes.listar();
    }

    public List<Curso> listarCursos() {
        return cursos.listar();
    }

    public Alumno buscarAlumno(String codigo) {
        return alumnos.obtener(codigo);
    }

    public Periodo buscarPeriodo(String codigo) {
        return periodos.obtener(codigo);
    }

    public Matricula buscarMatricula(int idMatricula) {
        return matriculas.obtener(String.valueOf(idMatricula));
    }

    public List<Matricula> matriculasPorPeriodo(String codigoPeriodo) {
        periodos.obtener(codigoPeriodo); // valida que el período exista
        List<Matricula> resultado = new ArrayList<>();
        for(Matricula matricula : matriculas.listar()) {
            if(matricula.codigoPeriodo().equals(codigoPeriodo)) {
                resultado.add(matricula);
            }
        }
        return resultado;
    }

    public List<Matricula> historialDeAlumno(String codigoAlumno) {
        alumnos.obtener(codigoAlumno); // valida que el alumno exista
        List<Matricula> resultado = new ArrayList<>();
        for(Matricula matricula : matriculas.listar()) {
            if(matricula.codigoAlumno().equals(codigoAlumno)) {
                resultado.add(matricula);
            }
        }
        return resultado;
    }

    // Regla privada
    private void validarNoMatriculado(Alumno alumno, Curso curso, Periodo periodo) {
        for(Matricula matricula : matriculas.listar()) {
            boolean mismoCaso = matricula.codigoAlumno().equals(alumno.codigo())
                    && matricula.codigoCurso().equals(curso.codigo())
                    && matricula.codigoPeriodo().equals(periodo.codigo());
            if(mismoCaso && matricula.estaActiva()) {
                throw new ValidacionException("El alumno ya está matriculado en ese curso y período");
            }
        }
    }
}

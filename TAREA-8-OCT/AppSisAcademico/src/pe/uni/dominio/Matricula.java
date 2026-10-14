package pe.uni.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.uni.excepciones.ValidacionException;

public class Matricula {
    private static int contadorMatricula = 0;

    private final int idMatricula;
    private final Alumno alumno;
    private final Periodo periodo;
    private final Curso curso;
    private final Docente docente;
    private final LocalDate fecha;
    private EstadoMatricula estado;
    private final List<Nota> notas;

    public Matricula(Alumno alumno, Periodo periodo, Curso curso, Docente docente) {
        if (alumno == null || alumno.codigo() == null || alumno.codigo().isBlank()) {
            throw new ValidacionException("El código del alumno es obligatorio");
        }
        if (periodo == null || periodo.codigo() == null || periodo.codigo().isBlank()) {
            throw new ValidacionException("El periodo de matricula es obligatorio");
        }
        if (curso == null) {
            throw new ValidacionException("El curso es obligatorio");
        }
        if (docente == null) {
            throw new ValidacionException("El docente es obligatorio");
        }

        contadorMatricula++;
        this.idMatricula = contadorMatricula;
        this.alumno = alumno;
        this.periodo = periodo;
        this.curso = curso;
        this.docente = docente;
        this.fecha = LocalDate.now();
        this.estado = EstadoMatricula.ACTIVA;
        this.notas = new ArrayList<>();

        checkRep();
    }

    private void checkRep() {
        if (idMatricula <= 0) {
            throw new IllegalStateException("RI violado: idMatricula debe ser positivo.");
        }
        if (alumno == null || periodo == null || curso == null || docente == null) {
            throw new IllegalStateException("RI violado: entidades asociadas no pueden ser nulas.");
        }
        if (estado == null || fecha == null || notas == null) {
            throw new IllegalStateException("RI violado: campos internos nulos.");
        }
    }

    public int idMatricula() {
        return idMatricula;
    }

    public String codigoAlumno() {
        return alumno.codigo();
    }

    public String NomApeAlumno() {
        return alumno.nombreCompleto();
    }

    public String codigoCurso() {
        return curso.codigo();
    }

    public String nombreCurso() {
        return curso.nombre();
    }

    public String codigoDocente() {
        return docente.codigo();
    }

    public String codigoPeriodo() {
        return periodo.codigo();
    }

    public String descripcionPeriodo() {
        return periodo.descripcion();
    }

    public LocalDate fecha() {
        return fecha;
    }

    public boolean estaActiva() {
        return estado == EstadoMatricula.ACTIVA;
    }

    public String estadoMatricula() {
        switch (estado) {
            case EstadoMatricula.ACTIVA: return "ACTIVA";
            case EstadoMatricula.CANCELADA: return "CANCELADA";
            case EstadoMatricula.ANULADA: return "ANULADA";
            case EstadoMatricula.NO_MATRICULADO: return "NO MATRICULADO";
            case EstadoMatricula.RETIRADA: return "RETIRADA";
        }
        return "";
    }

    public List<Nota> notas() {
        return List.copyOf(notas);
    }

    public double promedio() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double suma = 0.0;
        for (Nota n : notas) {
            suma += n.calificacion();
        }
        return suma / notas.size();
    }

    public void registrarNota(double calificacion) {
        agregarNota(new Nota(alumno, curso, periodo, calificacion));
    }

    public void agregarNota(Nota nota) {
        if (!estado.permiteCalificaciones()) {
            throw new ValidacionException("No se puede agregar notas a una matricula con estado " + estado);
        }
        if (nota == null) {
            throw new ValidacionException("La nota a registrar no puede ser nula");
        }
        notas.add(nota);
        checkRep();
    }

    public void RetirarMatricula() {
        if (estado != EstadoMatricula.ACTIVA) {
            throw new ValidacionException("Solo se puede retirar una matrícula activa");
        }
        this.estado = EstadoMatricula.RETIRADA;
        checkRep();
    }

    public void AnularMatricula() {
        if (estado != EstadoMatricula.ACTIVA) {
            throw new ValidacionException("Solo se puede anular una matrícula activa");
        }
        this.estado = EstadoMatricula.ANULADA;
        checkRep();
    }
}

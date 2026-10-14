package pe.uni.dominio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.uni.excepciones.ValidacionException;

/**
 *
 * @author JHERENCIA
 */
public class Matricula {
    private int id_matricula;
    private static int contador_matricula = 0; // variable contador de matricula
                                               // Esto es para tener el ultimo valor del autocorrelativo 
    private Alumno alumno;
    private Periodo periodo;
    private Curso curso;
    private Docente docente;
    private LocalDate fecha;
    private EstadoMatricula estado;
    private List<Nota> notas = new ArrayList<>();

    public Matricula(Alumno alumno, Periodo periodo, Curso curso, Docente docente) {
        if(alumno.codigo() == null || alumno.codigo().isBlank()) {
            throw new ValidacionException("El código del alumno es obligatorio");
        }
        if(periodo.codigo() == null || periodo.codigo().isBlank()) {
            throw new ValidacionException("El periodo de matricula es obligatorio");
        }
        // Entra en bruto los valores ya validados
        contador_matricula++; // Nueva matricula, => aumentar el contador
        this.id_matricula = contador_matricula; // asignar el valor del contador
        this.alumno = alumno;
        this.periodo = periodo;
        this.curso = curso;
        this.docente = docente;
        this.fecha = LocalDate.now();
        this.estado = EstadoMatricula.ACTIVA;
    }
    
    // Consultas (Query)
    public int idMatricula() {
        return id_matricula;
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
    
    public String codigoDocente() {
        return docente.codigo();
    }
    
    public String codigoPeriodo() {
        return periodo.codigo();
    }
    
    public String descripcionPeriodo() {
        return periodo.descripcion();
    }
    
    public String estadoMatricula() {
        switch(estado) {
            case EstadoMatricula.ACTIVA: return "ACTIVA";
            case EstadoMatricula.CANCELADA: return "ACTIVA";
            case EstadoMatricula.ANULADA: return "ANULADA";
            case EstadoMatricula.NO_MATRICULADO: return "NO MATRICULADO";
            case EstadoMatricula.RETIRADA: return "RETIRADO";   
        }
        return "";  // Aquí nunca se debería llegar
    }
    
    // ---- Agregado para la tarea ----
    public String nombreCurso() {
        return curso.nombre();
    }
    
    public boolean estaActiva() {
        return estado == EstadoMatricula.ACTIVA;
    }
    
    public List<Nota> notas() {
        return List.copyOf(notas); // copia inmutable, no se expone la lista interna
    }
    
    public double promedio() {
        if(notas.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for(Nota nota : notas) {
            suma += nota.calificacion();
        }
        return suma / notas.size();
    }
    
    // Tell, don't ask: la matrícula arma su propia nota
    public void registrarNota(double calificacion) {
        agregarNota(new Nota(alumno, curso, periodo, calificacion));
    }
    
    // Commands
    public void RetirarMatricula() {
        if(estado != EstadoMatricula.ACTIVA) {
            throw new ValidacionException("Solo se puede retirar una matrícula activa");
        }
        this.estado = EstadoMatricula.RETIRADA;
    }
    
    public void agregarNota(Nota nota) {
        if(!estado.permiteCalificaciones()) { // Si no permite calificaciones, salir de la función con una llamada de error
            throw new ValidacionException("No se puede agregar notas a una matricula con estado " + estado);
        }
        
        notas.add(nota); // agregar la nota
    }
    
    public void AnularMatricula() {
        if(estado != EstadoMatricula.ACTIVA) {
            throw new ValidacionException("Solo se puede anular una matrícula activa");
        }
        this.estado = EstadoMatricula.ANULADA;
    }
    
}

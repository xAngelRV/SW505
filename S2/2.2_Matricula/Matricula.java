import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private String codigoMatricula;
    private Estudiante estudiante;
    private String periodo;
    private List<Curso> cursos;
    private int limiteCreditos;
    private LocalDateTime fechaRegistro;
    private String estado;

    public static final int LIMITE_CREDITOS_DEFAULT = 22;

    public Matricula(String codigoMatricula, Estudiante estudiante, String periodo) {
        this(codigoMatricula, estudiante, periodo, LIMITE_CREDITOS_DEFAULT);
    }

    public Matricula(String codigoMatricula, Estudiante estudiante, String periodo, int limiteCreditos) {
        this.codigoMatricula = codigoMatricula;
        this.estudiante = estudiante;
        this.periodo = periodo;
        this.limiteCreditos = limiteCreditos;
        this.cursos = new ArrayList<>();
        this.fechaRegistro = LocalDateTime.now();
        this.estado = "ACTIVA";
    }

    public String getCodigoMatricula() {
        return codigoMatricula;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public String getPeriodo() {
        return periodo;
    }

    public List<Curso> getCursos() {
        return new ArrayList<>(cursos);
    }

    public int getLimiteCreditos() {
        return limiteCreditos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public int calcularTotalCreditos() {
        int total = 0;
        for (Curso c : cursos) {
            total += c.getCreditos();
        }
        return total;
    }


    public boolean yaEstaInscrito(Curso curso) {
        for (Curso c : cursos) {
            if (c.getCodigo().equalsIgnoreCase(curso.getCodigo())) {
                return true;
            }
        }
        return false;
    }


    public boolean agregarCurso(Curso curso) {
        if (curso == null) {
            System.out.println("[ERROR] El curso proporcionado no es valido.");
            return false;
        }

        if (yaEstaInscrito(curso)) {
            System.out.printf("[ERROR] El curso '%s' ya esta registrado en esta matricula.%n", curso.getNombre());
            return false;
        }

        if (calcularTotalCreditos() + curso.getCreditos() > limiteCreditos) {
            System.out.printf("[ERROR] No se puede matricular '%s'. Excede el limite de %d creditos (Total actual: %d, Curso: %d).%n",
                    curso.getNombre(), limiteCreditos, calcularTotalCreditos(), curso.getCreditos());
            return false;
        }

        if (!curso.tieneCupo()) {
            System.out.printf("[ERROR] No hay vacantes disponibles para el curso '%s' (%d/%d ocupados).%n",
                    curso.getNombre(), curso.getCupoActual(), curso.getCupoMaximo());
            return false;
        }

        curso.inscribir();
        cursos.add(curso);
        System.out.printf("[EXITO] Curso '%s' matriculado correctamente.%n", curso.getNombre());
        return true;
    }


    public boolean retirarCurso(String codigoCurso) {
        for (Curso c : cursos) {
            if (c.getCodigo().equalsIgnoreCase(codigoCurso)) {
                c.desinscribir();
                cursos.remove(c);
                System.out.printf("[EXITO] Curso '%s' retirado de la matricula.%n", c.getNombre());
                return true;
            }
        }
        System.out.printf("[ERROR] El curso con codigo '%s' no se encuentra en esta matricula.%n", codigoCurso);
        return false;
    }


    public void imprimirFichaMatricula() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("==========================================================================");
        System.out.println("                   FICHA OFICIAL DE MATRICULA                             ");
        System.out.println("==========================================================================");
        System.out.printf(" Nro. Matricula : %-20s Periodo Academico : %s%n", codigoMatricula, periodo);
        System.out.printf(" Fecha Emision  : %-20s Estado            : %s%n", fechaRegistro.format(dtf), estado);
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf(" Codigo Alumno  : %-20s Carrera           : %s%n", estudiante.getCodigo(), estudiante.getCarrera());
        System.out.printf(" Estudiante     : %-20s Correo Instituc.  : %s%n", estudiante.getNombreCompleto(), estudiante.getCorreo());
        System.out.println("==========================================================================");
        System.out.printf(" %-10s | %-36s | %-8s%n", "CODIGO", "ASIGNATURA", "CREDITOS");
        System.out.println("--------------------------------------------------------------------------");

        if (cursos.isEmpty()) {
            System.out.println("                     (No registra cursos matriculados)                    ");
        } else {
            for (Curso c : cursos) {
                System.out.printf(" %-10s | %-36s |    %02d   %n",
                        c.getCodigo(), c.getNombre(), c.getCreditos());
            }
        }

        System.out.println("==========================================================================");
        System.out.printf(" Total Asignaturas: %d  |  Total Creditos: %d / %d permitidos%n",
                cursos.size(), calcularTotalCreditos(), limiteCreditos);
        System.out.println("==========================================================================\n");
    }
}

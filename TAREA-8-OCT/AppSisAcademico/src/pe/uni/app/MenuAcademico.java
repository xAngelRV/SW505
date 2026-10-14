package pe.uni.app;

import java.util.List;
import pe.uni.dominio.Alumno;
import pe.uni.dominio.Curso;
import pe.uni.dominio.Docente;
import pe.uni.dominio.Matricula;
import pe.uni.dominio.Nota;
import pe.uni.dominio.Periodo;
import pe.uni.excepciones.ValidacionException;
import pe.uni.servicio.ServicioAcademico;
import pe.uni.vista.VistaConsola;

/**
 * Menú de la aplicación: pide datos, llama al servicio y muestra el resultado.
 */
public class MenuAcademico {
    private final VistaConsola vista = new VistaConsola();
    private final ServicioAcademico servicio = new ServicioAcademico();

    public void iniciar() {
        int opcion = -1;
        while(opcion != 0) {
            mostrarMenu();
            try {
                opcion = vista.leerEntero("Opción");
                ejecutar(opcion);
            } catch(ValidacionException e) {
                vista.mostrarError(e.getMessage());
            }
        }
    }

    private void mostrarMenu() {
        vista.mostrarTitulo("SISTEMA ACADÉMICO");
        System.out.println("1.  Registrar alumno");
        System.out.println("2.  Registrar docente");
        System.out.println("3.  Registrar curso");
        System.out.println("4.  Registrar período");
        System.out.println("5.  Matricular alumno");
        System.out.println("6.  Registrar nota");
        System.out.println("7.  Listar alumnos");
        System.out.println("8.  Listar docentes");
        System.out.println("9.  Listar cursos");
        System.out.println("10. Listar matrículas por período");
        System.out.println("11. Ver historial de un alumno");
        System.out.println("12. Ver promedio de una matrícula");
        System.out.println("0.  Salir");
    }

    private void ejecutar(int opcion) {
        switch(opcion) {
            case 1: registrarAlumno(); break;
            case 2: registrarDocente(); break;
            case 3: registrarCurso(); break;
            case 4: registrarPeriodo(); break;
            case 5: matricularAlumno(); break;
            case 6: registrarNota(); break;
            case 7: listarAlumnos(); break;
            case 8: listarDocentes(); break;
            case 9: listarCursos(); break;
            case 10: listarMatriculasPorPeriodo(); break;
            case 11: verHistorialAlumno(); break;
            case 12: verPromedioMatricula(); break;
            case 0: vista.mostrarMensaje("Hasta luego"); break;
            default: throw new ValidacionException("Opción no válida");
        }
    }

    // ---------- Registros (comandos) ----------
    private void registrarAlumno() {
        vista.mostrarTitulo("REGISTRAR ALUMNO");
        String codigo = vista.leerTexto("Código");
        String nombres = vista.leerTexto("Nombres");
        String apellidos = vista.leerTexto("Apellidos");
        int edad = vista.leerEntero("Edad");
        String carrera = vista.leerTexto("Carrera");
        Alumno alumno = new Alumno(codigo, nombres, apellidos, edad, carrera);
        servicio.registrarAlumno(alumno);
        vista.mostrarMensaje("Alumno registrado: " + alumno.nombreCompleto());
    }

    private void registrarDocente() {
        vista.mostrarTitulo("REGISTRAR DOCENTE");
        String codigo = vista.leerTexto("Código");
        String nombres = vista.leerTexto("Nombres");
        String apellidos = vista.leerTexto("Apellidos");
        String especialidad = vista.leerTexto("Especialidad");
        Docente docente = new Docente(codigo, nombres, apellidos, especialidad);
        servicio.registrarDocente(docente);
        vista.mostrarMensaje("Docente registrado: " + docente.nombreCompleto());
    }

    private void registrarCurso() {
        vista.mostrarTitulo("REGISTRAR CURSO");
        String codigo = vista.leerTexto("Código");
        String nombre = vista.leerTexto("Nombre");
        int creditos = vista.leerEntero("Créditos");
        Curso curso = new Curso(codigo, nombre, creditos, true); // nace activo
        servicio.registrarCurso(curso);
        vista.mostrarMensaje("Curso registrado: " + curso.nombre());
    }

    private void registrarPeriodo() {
        vista.mostrarTitulo("REGISTRAR PERÍODO");
        String codigo = vista.leerTexto("Código (ej. 2026-2)");
        String descripcion = vista.leerTexto("Descripción");
        servicio.registrarPeriodo(new Periodo(codigo, descripcion));
        vista.mostrarMensaje("Período registrado: " + codigo);
    }

    private void matricularAlumno() {
        vista.mostrarTitulo("MATRICULAR ALUMNO");
        String codigoAlumno = vista.leerTexto("Código del alumno");
        String codigoCurso = vista.leerTexto("Código del curso");
        String codigoDocente = vista.leerTexto("Código del docente");
        String codigoPeriodo = vista.leerTexto("Código del período");
        servicio.matricular(codigoAlumno, codigoCurso, codigoDocente, codigoPeriodo);
        Matricula matricula = servicio.ultimaMatricula();
        vista.mostrarMensaje("Matrícula registrada con id " + matricula.idMatricula());
    }

    private void registrarNota() {
        vista.mostrarTitulo("REGISTRAR NOTA");
        int idMatricula = vista.leerEntero("Id de la matrícula");
        double calificacion = vista.leerDecimal("Calificación (0 a 20)");
        servicio.registrarNota(idMatricula, calificacion);
        vista.mostrarMensaje("Nota registrada");
    }

    // ---------- Listados (consultas) ----------
    private void listarAlumnos() {
        vista.mostrarTitulo("ALUMNOS");
        List<Alumno> alumnos = servicio.listarAlumnos();
        if(alumnos.isEmpty()) {
            vista.mostrarMensaje("No hay alumnos registrados");
        }
        for(Alumno a : alumnos) {
            vista.mostrarMensaje(String.format("%-10s %-30s %3d años  %s",
                    a.codigo(), a.nombreCompleto(), a.edad(), a.carrera()));
        }
    }

    private void listarDocentes() {
        vista.mostrarTitulo("DOCENTES");
        List<Docente> docentes = servicio.listarDocentes();
        if(docentes.isEmpty()) {
            vista.mostrarMensaje("No hay docentes registrados");
        }
        for(Docente d : docentes) {
            vista.mostrarMensaje(String.format("%-10s %-30s %s",
                    d.codigo(), d.nombreCompleto(), d.especialidad()));
        }
    }

    private void listarCursos() {
        vista.mostrarTitulo("CURSOS");
        List<Curso> cursos = servicio.listarCursos();
        if(cursos.isEmpty()) {
            vista.mostrarMensaje("No hay cursos registrados");
        }
        for(Curso c : cursos) {
            String estado = c.estado() ? "Activo" : "De baja";
            vista.mostrarMensaje(String.format("%-10s %-35s %2d créditos  %s",
                    c.codigo(), c.nombre(), c.creditos(), estado));
        }
    }

    private void listarMatriculasPorPeriodo() {
        vista.mostrarTitulo("MATRÍCULAS POR PERÍODO");
        String codigoPeriodo = vista.leerTexto("Código del período");
        Periodo periodo = servicio.buscarPeriodo(codigoPeriodo);
        List<Matricula> matriculas = servicio.matriculasPorPeriodo(codigoPeriodo);
        vista.mostrarMensaje("Período " + periodo.codigo() + ": " + periodo.descripcion());
        if(matriculas.isEmpty()) {
            vista.mostrarMensaje("No hay matrículas en este período");
        }
        for(Matricula m : matriculas) {
            vista.mostrarMensaje(String.format("Id %-3d %-10s %-25s %-8s %-10s %s",
                    m.idMatricula(), m.codigoAlumno(), m.NomApeAlumno(),
                    m.codigoCurso(), m.codigoDocente(), m.estadoMatricula()));
        }
    }

    private void verHistorialAlumno() {
        vista.mostrarTitulo("HISTORIAL DE ALUMNO");
        String codigoAlumno = vista.leerTexto("Código del alumno");
        Alumno alumno = servicio.buscarAlumno(codigoAlumno);
        List<Matricula> historial = servicio.historialDeAlumno(codigoAlumno);
        vista.mostrarMensaje(alumno.nombreCompleto() + " - " + alumno.carrera());
        if(historial.isEmpty()) {
            vista.mostrarMensaje("El alumno no tiene matrículas");
        }
        for(Matricula m : historial) {
            vista.mostrarMensaje(String.format("Período %s | %s %s | %s | Notas: %s | Promedio: %.2f",
                    m.codigoPeriodo(), m.codigoCurso(), m.nombreCurso(),
                    m.estadoMatricula(), textoNotas(m), m.promedio()));
        }
    }

    private void verPromedioMatricula() {
        vista.mostrarTitulo("PROMEDIO DE UNA MATRÍCULA");
        int idMatricula = vista.leerEntero("Id de la matrícula");
        Matricula matricula = servicio.buscarMatricula(idMatricula);
        vista.mostrarMensaje(matricula.NomApeAlumno() + " - " + matricula.nombreCurso());
        vista.mostrarMensaje("Notas: " + textoNotas(matricula));
        vista.mostrarMensaje(String.format("Promedio: %.2f", matricula.promedio()));
    }

    // Convierte las notas de una matrícula en texto: "12.0  15.5"
    private String textoNotas(Matricula matricula) {
        List<Nota> notas = matricula.notas();
        if(notas.isEmpty()) {
            return "sin notas";
        }
        String texto = "";
        for(Nota nota : notas) {
            texto += nota.calificacion() + "  ";
        }
        return texto.trim();
    }
}

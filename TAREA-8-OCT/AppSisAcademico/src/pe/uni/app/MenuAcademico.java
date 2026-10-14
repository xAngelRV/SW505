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

public class MenuAcademico {
    private final VistaConsola vista = new VistaConsola();
    private final ServicioAcademico servicio = new ServicioAcademico();

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            try {
                opcion = vista.leerEntero("Seleccione una opción");
                ejecutar(opcion);
            } catch (ValidacionException e) {
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
        switch (opcion) {
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
            case 0: vista.mostrarMensaje("Saliendo del sistema académico. ¡Hasta pronto!"); break;
            default: throw new ValidacionException("Opción no válida. Ingrese un valor del 0 al 12.");
        }
    }

    private void registrarAlumno() {
        vista.mostrarTitulo("REGISTRO DE ALUMNO");
        String codigo = vista.leerTexto("Código del alumno");
        String nombres = vista.leerTexto("Nombres");
        String apellidos = vista.leerTexto("Apellidos");
        int edad = vista.leerEntero("Edad");
        String carrera = vista.leerTexto("Carrera");

        Alumno alumno = new Alumno(codigo, nombres, apellidos, edad, carrera);
        servicio.registrarAlumno(alumno);
        vista.mostrarMensaje("Alumno registrado exitosamente: " + alumno.nombreCompleto());
    }

    private void registrarDocente() {
        vista.mostrarTitulo("REGISTRO DE DOCENTE");
        String codigo = vista.leerTexto("Código del docente");
        String nombres = vista.leerTexto("Nombres");
        String apellidos = vista.leerTexto("Apellidos");
        String especialidad = vista.leerTexto("Especialidad");

        Docente docente = new Docente(codigo, nombres, apellidos, especialidad);
        servicio.registrarDocente(docente);
        vista.mostrarMensaje("Docente registrado exitosamente: " + docente.nombreCompleto());
    }

    private void registrarCurso() {
        vista.mostrarTitulo("REGISTRO DE CURSO");
        String codigo = vista.leerTexto("Código del curso");
        String nombre = vista.leerTexto("Nombre de la asignatura");
        int creditos = vista.leerEntero("Cantidad de créditos");

        Curso curso = new Curso(codigo, nombre, creditos, true);
        servicio.registrarCurso(curso);
        vista.mostrarMensaje("Curso registrado con éxito: " + curso.nombre());
    }

    private void registrarPeriodo() {
        vista.mostrarTitulo("REGISTRO DE PERÍODO");
        String codigo = vista.leerTexto("Código del período (ej. 2026-2)");
        String descripcion = vista.leerTexto("Descripción");

        Periodo periodo = new Periodo(codigo, descripcion);
        servicio.registrarPeriodo(periodo);
        vista.mostrarMensaje("Período registrado correctamente: " + periodo.codigo());
    }

    private void matricularAlumno() {
        vista.mostrarTitulo("MATRICULAR ALUMNO");
        String codAlumno = vista.leerTexto("Código de alumno");
        String codCurso = vista.leerTexto("Código de curso");
        String codDocente = vista.leerTexto("Código de docente");
        String codPeriodo = vista.leerTexto("Código de período");

        servicio.matricular(codAlumno, codCurso, codDocente, codPeriodo);
        Matricula m = servicio.ultimaMatricula();
        vista.mostrarMensaje("Matrícula realizada exitosamente con ID N° " + m.idMatricula());
    }

    private void registrarNota() {
        vista.mostrarTitulo("REGISTRAR NOTA");
        int idMatricula = vista.leerEntero("ID de la matrícula");
        double nota = vista.leerDecimal("Calificación (0 - 20)");

        servicio.registrarNota(idMatricula, nota);
        vista.mostrarMensaje("Nota agregada correctamente.");
    }

    private void listarAlumnos() {
        vista.mostrarTitulo("LISTADO DE ALUMNOS");
        List<Alumno> lista = servicio.listarAlumnos();
        if (lista.isEmpty()) {
            vista.mostrarMensaje("No hay alumnos registrados en el sistema.");
            return;
        }

        System.out.printf("%-12s %-32s %-6s %s%n", "CÓDIGO", "ALUMNO", "EDAD", "CARRERA");
        System.out.println("--------------------------------------------------------------------------------");
        for (Alumno a : lista) {
            System.out.printf("%-12s %-32s %-6d %s%n",
                    a.codigo(), a.nombreCompleto(), a.edad(), a.carrera());
        }
    }

    private void listarDocentes() {
        vista.mostrarTitulo("LISTADO DE DOCENTES");
        List<Docente> lista = servicio.listarDocentes();
        if (lista.isEmpty()) {
            vista.mostrarMensaje("No hay docentes registrados en el sistema.");
            return;
        }

        System.out.printf("%-12s %-32s %s%n", "CÓDIGO", "DOCENTE", "ESPECIALIDAD");
        System.out.println("--------------------------------------------------------------------------------");
        for (Docente d : lista) {
            System.out.printf("%-12s %-32s %s%n",
                    d.codigo(), d.nombreCompleto(), d.especialidad());
        }
    }

    private void listarCursos() {
        vista.mostrarTitulo("LISTADO DE CURSOS");
        List<Curso> lista = servicio.listarCursos();
        if (lista.isEmpty()) {
            vista.mostrarMensaje("No hay cursos registrados en el sistema.");
            return;
        }

        System.out.printf("%-10s %-35s %-10s %s%n", "CÓDIGO", "NOMBRE", "CRÉDITOS", "ESTADO");
        System.out.println("--------------------------------------------------------------------------------");
        for (Curso c : lista) {
            String estado = c.estado() ? "Activo" : "De baja";
            System.out.printf("%-10s %-35s %-10d %s%n",
                    c.codigo(), c.nombre(), c.creditos(), estado);
        }
    }

    private void listarMatriculasPorPeriodo() {
        vista.mostrarTitulo("MATRÍCULAS POR PERÍODO");
        String codPeriodo = vista.leerTexto("Ingrese el código del período");
        Periodo periodo = servicio.buscarPeriodo(codPeriodo);
        List<Matricula> matriculas = servicio.matriculasPorPeriodo(codPeriodo);

        vista.mostrarMensaje("Período: " + periodo.codigo() + " - " + periodo.descripcion());
        if (matriculas.isEmpty()) {
            vista.mostrarMensaje("No existen matrículas registradas para este período.");
            return;
        }

        System.out.printf("%-5s %-10s %-25s %-10s %-10s %s%n",
                "ID", "COD.ALU", "ALUMNO", "CURSO", "DOCENTE", "ESTADO");
        System.out.println("--------------------------------------------------------------------------------");
        for (Matricula m : matriculas) {
            System.out.printf("%-5d %-10s %-25s %-10s %-10s %s%n",
                    m.idMatricula(), m.codigoAlumno(), m.NomApeAlumno(),
                    m.codigoCurso(), m.codigoDocente(), m.estadoMatricula());
        }
    }

    private void verHistorialAlumno() {
        vista.mostrarTitulo("HISTORIAL ACADÉMICO DE ALUMNO");
        String codAlumno = vista.leerTexto("Ingrese el código del alumno");
        Alumno alumno = servicio.buscarAlumno(codAlumno);
        List<Matricula> historial = servicio.historialDeAlumno(codAlumno);

        vista.mostrarMensaje("Estudiante: " + alumno.nombreCompleto() + " (" + alumno.carrera() + ")");
        if (historial.isEmpty()) {
            vista.mostrarMensaje("El alumno no registra matrículas hasta el momento.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        for (Matricula m : historial) {
            System.out.printf("[%s] %s - %s | Estado: %s | Notas: [%s] | Prom: %.2f%n",
                    m.codigoPeriodo(), m.codigoCurso(), m.nombreCurso(),
                    m.estadoMatricula(), obtenerCadenaNotas(m), m.promedio());
        }
    }

    private void verPromedioMatricula() {
        vista.mostrarTitulo("PROMEDIO DE MATRÍCULA");
        int idMatricula = vista.leerEntero("Ingrese el ID de la matrícula");
        Matricula matricula = servicio.buscarMatricula(idMatricula);

        vista.mostrarMensaje("Alumno : " + matricula.NomApeAlumno());
        vista.mostrarMensaje("Curso  : " + matricula.nombreCurso() + " (" + matricula.codigoCurso() + ")");
        vista.mostrarMensaje("Notas  : [" + obtenerCadenaNotas(matricula) + "]");
        vista.mostrarMensaje(String.format("Promedio: %.2f", matricula.promedio()));
    }

    private String obtenerCadenaNotas(Matricula matricula) {
        List<Nota> notas = matricula.notas();
        if (notas.isEmpty()) {
            return "Sin notas";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < notas.size(); i++) {
            sb.append(notas.get(i).calificacion());
            if (i < notas.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("====================================================================");
        System.out.println("        DEMOSTRACION: SISTEMA DE MATRICULA UNIVERSITARIA (POO)      ");
        System.out.println("====================================================================\n");

        Estudiante est1 = new Estudiante(
                "U20231A101",
                "Carlos Alberto",
                "Mendoza Rios",
                "carlos.mendoza@universidad.edu.pe",
                "Ingenieria de Software"
        );

        Estudiante est2 = new Estudiante(
                "U20231B202",
                "Mariana Sofia",
                "Gomez Torres",
                "mariana.gomez@universidad.edu.pe",
                "Ingenieria de Software"
        );

        System.out.println("--- [1] Estudiantes Registrados ---");
        System.out.println("1: " + est1);
        System.out.println("2: " + est2);
        System.out.println();

        System.out.println("--- [2] Catalogo de Cursos Disponibles ---");
        Curso cursoSW = new Curso("SW505", "Construccion de Software", 4, 30);
        Curso cursoBD = new Curso("BD301", "Bases de Datos Avanzadas", 4, 25);
        Curso cursoAlg = new Curso("AL201", "Algoritmos y Estructuras", 5, 20);
        Curso cursoArq = new Curso("AR402", "Arquitectura Empresarial", 4, 15);
        Curso cursoTesis = new Curso("TE601", "Taller de Tesis I", 6, 10);
        Curso cursoRedes = new Curso("RD101", "Redes y Comunicaciones", 3, 1);

        System.out.println(cursoSW);
        System.out.println(cursoBD);
        System.out.println(cursoAlg);
        System.out.println(cursoArq);
        System.out.println(cursoTesis);
        System.out.println(cursoRedes);
        System.out.println();

        System.out.println("--- [3] Proceso de Matricula de Carlos Mendoza ---");
        Matricula matCarlos = new Matricula("MAT-2026-001", est1, "2026-I", 20);

        matCarlos.agregarCurso(cursoSW);   // 4 creditos (Total: 4)
        matCarlos.agregarCurso(cursoBD);   // 4 creditos (Total: 8)
        matCarlos.agregarCurso(cursoAlg);  // 5 creditos (Total: 13)
        matCarlos.agregarCurso(cursoArq);  // 4 creditos (Total: 17)
        System.out.println();

        System.out.println("--- [4] Pruebas de Validacion de Reglas de Negocio ---");

        System.out.println("Prueba 1: Intentar agregar curso duplicado:");
        matCarlos.agregarCurso(cursoSW);
        System.out.println();

        System.out.println("Prueba 2: Intentar exceder el limite maximo de creditos:");
        matCarlos.agregarCurso(cursoTesis);
        System.out.println();

        System.out.println("Prueba 3: Control de vacantes/cupos disponibles:");
        matCarlos.agregarCurso(cursoRedes); // 17 + 3 = 20 creditos (Llega al tope exacto de 20, cupo ocupado 1/1)
        System.out.println();

        System.out.println("Estudiante 2 (Mariana) intenta matricular el curso de Redes (sin cupos):");
        Matricula matMariana = new Matricula("MAT-2026-002", est2, "2026-I");
        matMariana.agregarCurso(cursoRedes);
        System.out.println();

        System.out.println("--- [5] Emision de Ficha Oficial de Matricula ---");
        matCarlos.imprimirFichaMatricula();

        System.out.println("--- [6] Demostracion de Retiro de Asignatura ---");
        matCarlos.retirarCurso("RD101");
        System.out.println("Cupos disponibles de Redes tras retiro: " + cursoRedes.getCuposDisponibles() + "/" + cursoRedes.getCupoMaximo());
        System.out.println("Nuevo total de creditos de Carlos: " + matCarlos.calcularTotalCreditos());
        System.out.println();

        System.out.println("Mariana vuelve a intentar matricularse en Redes tras el retiro de Carlos:");
        matMariana.agregarCurso(cursoRedes);
        matMariana.imprimirFichaMatricula();

        System.out.println("====================================================================");
        System.out.println("              FIN DE LA DEMOSTRACION DEL SISTEMA                    ");
        System.out.println("====================================================================");
    }
}

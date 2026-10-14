package pe.uni.vista;

import java.util.Scanner;
import pe.uni.excepciones.ValidacionException;

public class VistaConsola {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarTitulo(String titulo) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("   " + titulo);
        System.out.println("==================================================");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.out.println("[ERROR] " + error);
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine().trim();
    }

    public int leerEntero(String mensaje) {
        try {
            return Integer.parseInt(leerTexto(mensaje));
        } catch (NumberFormatException e) {
            throw new ValidacionException("Debe ingresar un número entero válido.");
        }
    }

    public double leerDecimal(String mensaje) {
        try {
            return Double.parseDouble(leerTexto(mensaje));
        } catch (NumberFormatException e) {
            throw new ValidacionException("Debe ingresar un valor numérico decimal válido (ej. 14.5).");
        }
    }
}

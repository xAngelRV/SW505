package pe.uni.vista;

import java.util.Scanner;
import pe.uni.excepciones.ValidacionException;

/**
 * Todo lo que entra y sale por la terminal pasa por aquí.
 */
public class VistaConsola {
    private final Scanner teclado = new Scanner(System.in);

    public void mostrarTitulo(String titulo) {
        System.out.println();
        System.out.println("=== " + titulo + " ===");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("ERROR: " + mensaje);
    }

    public String leerTexto(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return teclado.nextLine().trim();
    }

    public int leerEntero(String etiqueta) {
        try {
            return Integer.parseInt(leerTexto(etiqueta));
        } catch(NumberFormatException e) {
            throw new ValidacionException("Debe ingresar un número entero");
        }
    }

    public double leerDecimal(String etiqueta) {
        try {
            return Double.parseDouble(leerTexto(etiqueta));
        } catch(NumberFormatException e) {
            throw new ValidacionException("Debe ingresar un número (use punto decimal, ej. 14.5)");
        }
    }
}

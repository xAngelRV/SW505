package pe.empresa.pedidos;

import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
    private final Properties props = new Properties();

    public Configuracion() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String obtenerPropiedad(String clave, String valorPorDefecto) {
        return props.getProperty(clave, valorPorDefecto);
    }
}

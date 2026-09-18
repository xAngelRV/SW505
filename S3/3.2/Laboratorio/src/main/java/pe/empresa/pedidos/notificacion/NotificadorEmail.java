package pe.empresa.pedidos.notificacion;

import pe.empresa.pedidos.Configuracion;

public class NotificadorEmail implements Notificador {
    private final String emisor;

    public NotificadorEmail() {
        this(new Configuracion().obtenerPropiedad("notificacion.email.emisor", "ventas@empresa.pe"));
    }

    public NotificadorEmail(String emisor) {
        this.emisor = emisor;
    }

    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("Email enviado desde " + emisor + " a " + destinatario + ": " + mensaje);
    }
}

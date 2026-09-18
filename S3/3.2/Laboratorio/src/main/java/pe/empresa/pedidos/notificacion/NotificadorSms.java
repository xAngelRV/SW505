package pe.empresa.pedidos.notificacion;

import pe.empresa.pedidos.Configuracion;

public class NotificadorSms implements Notificador {
    private final String remitente;

    public NotificadorSms() {
        this(new Configuracion().obtenerPropiedad("notificacion.sms.remitente", "EMPRESA-PE"));
    }

    public NotificadorSms(String remitente) {
        this.remitente = remitente;
    }

    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("SMS enviado desde " + remitente + " a " + destinatario + ": " + mensaje);
    }
}

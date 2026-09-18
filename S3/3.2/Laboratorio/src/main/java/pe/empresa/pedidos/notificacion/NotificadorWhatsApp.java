package pe.empresa.pedidos.notificacion;

import pe.empresa.pedidos.Configuracion;

public class NotificadorWhatsApp implements Notificador {
    private final String numeroOrigen;

    public NotificadorWhatsApp() {
        this(new Configuracion().obtenerPropiedad("notificacion.whatsapp.numero_origen", "+51999111222"));
    }

    public NotificadorWhatsApp(String numeroOrigen) {
        this.numeroOrigen = numeroOrigen;
    }

    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("WhatsApp enviado desde " + numeroOrigen + " a " + destinatario + ": " + mensaje);
    }
}

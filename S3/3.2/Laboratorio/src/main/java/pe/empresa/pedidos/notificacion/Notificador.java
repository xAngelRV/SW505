package pe.empresa.pedidos.notificacion;

public interface Notificador {
    void enviarNotificacion(String destinatario, String mensaje);
}

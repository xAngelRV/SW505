public class AdaptadorTwilio implements Notificador {
    private TwilioSMSClient twilio;

    public AdaptadorTwilio() {
        this.twilio = new TwilioSMSClient();
    }

    @Override
    public void enviar(String destinatario, String mensaje) {
        twilio.sendSMS(destinatario, mensaje);
    }
}
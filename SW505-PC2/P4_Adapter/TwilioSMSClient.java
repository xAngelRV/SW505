public class TwilioSMSClient {
    public void sendSMS(String phoneNumber, String body) {
        System.out.println(
                "Mensaje enviado a" + phoneNumber + ": " + body);
    }
}
